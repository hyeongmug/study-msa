package com.example.servicediscoveryclient.apis.test;

import com.example.servicediscoveryclient.apis.test.client.TestDiscoveryClient;
import com.example.servicediscoveryclient.apis.test.client.TestFeignClient;
import com.example.servicediscoveryclient.apis.test.client.TestRestTemplateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private TestDiscoveryClient testDiscoveryClient;

    @Autowired
    private TestRestTemplateClient testRestTemplateClient;

    @Autowired
    private TestFeignClient feignClient;

    @GetMapping("/{clientType}")
    public Map getTestDataOtherServiceWithClientType(@PathVariable String clientType) {

        Map result = null;

        switch (clientType) {
            case "discovery":
                result = testDiscoveryClient.clientTest();
                break;
            case "rest":
                result = testRestTemplateClient.clientTest();
                break;
            case "feign":
                result = feignClient.clientTest();
                break;
        }

        return result;
    }
}
