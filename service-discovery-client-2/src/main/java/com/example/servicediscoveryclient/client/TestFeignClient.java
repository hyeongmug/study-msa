package com.example.servicediscoveryclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient("service-discovery-client")
public interface TestFeignClient {
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/test"
    )
    Map clientTest();

}
