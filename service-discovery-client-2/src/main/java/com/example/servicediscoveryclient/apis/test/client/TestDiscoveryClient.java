package com.example.servicediscoveryclient.apis.test.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class TestDiscoveryClient {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    public Map<String, String> clientTest() {
        List<ServiceInstance> instances = discoveryClient.getInstances("service-discovery-client");
        if (instances.size() == 0) return null;
        String serviceUri = instances.get(0).getUri().toString() + "/test";
        ResponseEntity<Map> restExchange = restTemplate.exchange(
                serviceUri,
                HttpMethod.GET,
                null,
                Map.class
        );
        return restExchange.getBody();
    }

}
