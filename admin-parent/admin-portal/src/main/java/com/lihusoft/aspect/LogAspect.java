package com.lihusoft.aspect;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import com.lihusoft.util.RequestContext;
import com.lihusoft.util.SnowFlake;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 描述：
 * 日志切面，记录打印信息
 *
 * @author tsc
 * @since 2023/3/28 15:08
 **/
@Aspect
@Component
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Resource
    private SnowFlake snowFlake;

    @Pointcut("execution(public * com.lihusoft.controller..*Controller.*(..))")
    public void controllerPointCut() {
    }

    @Before("controllerPointCut()")
    public void doBefore(JoinPoint joinpoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (requestAttributes != null) {
            request = requestAttributes.getRequest();
        }
        Signature signature = joinpoint.getSignature();
        String name = signature.getName();
        // 增加日志流水号
        MDC.put("LOG_ID", String.valueOf(snowFlake.nextId()));
        // 打印请求信息
        LOGGER.info("------------- 开始 -------------");
        if (request != null) {
            LOGGER.info("请求地址: {} {}", request.getRequestURL().toString(), request.getMethod());
        }
        if (request != null) {
            LOGGER.info("请求远程地址: {} {}", getRemoteIp(request), request.getMethod());
        }
        LOGGER.info("类名方法: {}.{}", signature.getDeclaringTypeName(), name);
        if (request != null) {
            LOGGER.info("远程地址: {}", request.getRemoteAddr());
        }

        if (request != null) {
            RequestContext.setRemoteAddr(getRemoteIp(request));
        }

        // 打印请求参数
        Object[] args = joinpoint.getArgs();

        Object[] arguments = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest
                    || args[i] instanceof ServletResponse
                    || args[i] instanceof MultipartFile) {
                continue;
            }
            arguments[i] = args[i];
        }
        // 排除字段，敏感字段或太长的字段不显示
        String[] excludeProperties = {"password", "file"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludeFilter = filters.addFilter();
        excludeFilter.addExcludes(excludeProperties);
        LOGGER.info("请求参数: {}", JSONObject.toJSONString(arguments, excludeFilter));
    }

    @Around("controllerPointCut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 排除字段，敏感字段或太长的字段不显示
        String[] excludeProperties = {"password", "file"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludeFilter = filters.addFilter();
        excludeFilter.addExcludes(excludeProperties);
        LOGGER.info("返回结果: {}", JSONObject.toJSONString(result, excludeFilter));
        LOGGER.info("------------- 结束 耗时：{} ms -------------", System.currentTimeMillis() - startTime);
        return result;
    }

    /**
     * 使用nginx做反向代理，需要用该方法才能取到真实的远程IP
     *
     */
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public String getRemoteIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
