package com.lihusoft.feign;

import com.lihusoft.service.UserService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述：
 *
 * @author tsc
 * @since 2023/3/28 14:32
 **/
@FeignClient(name = "admin-service-base")
public interface TbUserServiceFeign extends UserService {
}
