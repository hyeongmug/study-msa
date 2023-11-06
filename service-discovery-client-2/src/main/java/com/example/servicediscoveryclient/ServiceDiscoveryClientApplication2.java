package com.example.servicediscoveryclient;

import com.example.servicediscoveryclient.utils.UserContextInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ServiceDiscoveryClientApplication2 {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDiscoveryClientApplication2.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {

        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();

        interceptors.add(new UserContextInterceptor());
        restTemplate.setInterceptors(interceptors);

        return restTemplate;
    }

}
