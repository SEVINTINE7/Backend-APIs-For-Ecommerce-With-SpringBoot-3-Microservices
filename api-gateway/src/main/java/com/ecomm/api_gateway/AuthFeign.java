package com.ecomm.api_gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "identity-service", url = "http://IDENTITY-SERVICE")
public interface AuthFeign {
    @GetMapping("/api/user/validate")
    Boolean validatetokenwithusername(@RequestParam String token);
}
