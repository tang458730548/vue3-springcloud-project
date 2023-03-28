package com.lihusoft.constants;

import lombok.Getter;

/**
 * 描述：
 *      http状态
 * @author tsc
 * @since 2023/2/10 14:59
 **/
@Getter
public enum HttpStatusEnum {

    //http请求200
    HTTP_200(200 , "请求成功") ,

    HTTP_400(400 , "业务内部错误") ,
    HTTP_403(403 , "您暂时没有权限,请联系管理员！") ,

    HTTP_404(404 , "您访问的资源不存在！") ,

    HTTP_500(500 , "系统错误，请联系系统管理员！") ;

    private final int code;

    private final String message;

    HttpStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
