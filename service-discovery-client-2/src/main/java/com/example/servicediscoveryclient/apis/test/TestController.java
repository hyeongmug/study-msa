package com.example.servicediscoveryclient.apis.test;

import com.example.servicediscoveryclient.apis.test.client.TestDiscoveryClient;
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

    @GetMapping("/{clientType}")
    public Map<String, String> getTestDataOtherServiceWithClientType(@PathVariable String clientType) {

        Map<String, String> result = null;

        switch (clientType) {
            case "discovery":
                result = testDiscoveryClient.clientTest();
                break;
        }

        return result;
    }
}
