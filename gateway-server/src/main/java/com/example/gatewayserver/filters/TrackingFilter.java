package com.example.gatewayserver.filters;

import com.example.gatewayserver.util.FilterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * GlobalFilter 인터페이스를 구현하고 filter() 메서드를 재정의
 */
@Component
@Order(1)
public class TrackingFilter implements GlobalFilter {

    private static final Logger logger = LoggerFactory.getLogger(TrackingFilter.class);

    @Autowired
    FilterUtils filterUtils;


    /**
     * filter 메서드 재정의
     * 요청이 필터를 통과할 때마다 실행되는 메서드
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // ServerWebExchanger 객체를 이용해서 HTTP 헤더를 추출
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();

        if (isCorrelationIdPresent(requestHeaders)) {
            logger.debug("tmx-correlation-id found in tracking filter: {}", filterUtils.getCorrelationId(requestHeaders));
        } else {
            String correlationId = this.generateCorrelationId();
            exchange = filterUtils.setCorrelationId(exchange, correlationId);
            logger.debug("tmx-correlation-id generated in tracking filter: {}", correlationId);
        }

        return chain.filter(exchange);
    }

    /**
     * 요청 헤더에 상관관계 ID가 있는지 확인하는 메서드
     */
    private boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
        if (filterUtils.getCorrelationId(requestHeaders) != null) {
            return true;
        }
        return false;
    }

    /**
     * 상관관계 ID를 생성하는 메서드
     */
    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }

}
