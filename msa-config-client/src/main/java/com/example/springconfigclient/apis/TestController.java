package com.example.springconfigclient.apis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
@RefreshScope
public class TestController {

    @Value("${test}")
    private String test;

    @Value("${encryptTestMessage}")
    private String encryptTestMessage;

    @GetMapping("/test")
    public Map<String, String> test() {
        return Map.of("test", test, "encryptTestMessage", encryptTestMessage);
    }
}
