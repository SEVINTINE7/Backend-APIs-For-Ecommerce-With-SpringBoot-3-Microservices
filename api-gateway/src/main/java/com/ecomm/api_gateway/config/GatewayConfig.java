package com.ecomm.api_gateway.config;

import com.ecomm.api_gateway.JwtAuthFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder, JwtAuthFilter authFilter) {
        return builder.routes()
                // Identity Service Routes
                .route("identity_users", r -> r.path("/api/auth/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("http://IDENTITY-SERVICE"))

                .route("identity_user", r -> r.path("/api/user/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("http://IDENTITY-SERVICE"))

                .route("identity_admin", r -> r.path("/api/admin/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("http://IDENTITY-SERVICE"))

                // Product Service Routes
                .route("product_service", r -> r.path("/api/category/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("http://PRODUCT-SERVICE"))
                .route("product_service", r -> r.path("/api/products/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("http://PRODUCT-SERVICE"))

                .build();
    }
}

