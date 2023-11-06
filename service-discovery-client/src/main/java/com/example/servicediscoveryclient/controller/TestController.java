package com.example.servicediscoveryclient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("")
    public Map<String, String> getTest(HttpServletRequest httpServletRequest) {
        String correlation = httpServletRequest.getHeader("tmx-correlation-id");

        logger.debug("tmx-correlation-id : {}", correlation);

        return Map.of("test","test");
    }

}
