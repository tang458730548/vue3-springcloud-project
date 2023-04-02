package com.lihusoft.feign;

import com.lihusoft.service.ModuleService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述：
 *
 * @author tsc
 * @since 2023/4/2 9:55
 **/
@FeignClient(name = "admin-service-base")
public interface TbModuleServiceFeign extends ModuleService {
}
