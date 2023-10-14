package com.example.servicediscoveryclient.apis.test.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class TestRestTemplateClient {

    @Autowired
    RestTemplate restTemplate;

    public Map clientTest() {
        ResponseEntity<Map> restExchange = restTemplate.exchange(
                "http://service-discovery-client/test",
                HttpMethod.GET,
                null,
                Map.class
        );

        return restExchange.getBody();
    }

}
