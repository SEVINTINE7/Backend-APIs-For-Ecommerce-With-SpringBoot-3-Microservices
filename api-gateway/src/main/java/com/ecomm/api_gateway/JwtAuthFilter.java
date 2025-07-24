package com.ecomm.api_gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JwtAuthFilter implements GatewayFilter {


//    private final AuthFeign authServiceFeignClient;
    private RestTemplate restTemplate;


    private static final List<String> UNSECURED_PATHS = List.of(
            "/api/admin/register",
            "/api/admin/login",
            "/api/staff/login",
            "/api/products/get-product-by-id/{{id}}",
            "/api/category/get-category-by-id/{{id}}",
            "/api/category/get-all-categories",
            "/api/products/get-all-products",
            "/api/auth/register",
            "/api/auth/login"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestPath = exchange.getRequest().getPath().toString();

        System.out.println(requestPath + "isUnsecurePath - "+isUnsecuredPath(requestPath));

        // Check if the request is for an unsecured endpoint
        if (isUnsecuredPath(requestPath)) {
            return chain.filter(exchange); // Allow the request without authentication
        }

        String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (token == null || !token.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        token = token.substring(7);

//        Boolean isValid = authServiceFeignClient.validatetokenwithusername(token);
        ResponseEntity<Boolean> isValid = restTemplate.exchange(
                "http://IDENTITY-SERVICE/api/user/validate?token=" + token,
                HttpMethod.GET,
                null,
                Boolean.class
        );

        if (Boolean.FALSE.equals(isValid.getBody())) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    private boolean isUnsecuredPath(String requestPath) {
        return UNSECURED_PATHS.stream().anyMatch(requestPath::startsWith);
    }
}
