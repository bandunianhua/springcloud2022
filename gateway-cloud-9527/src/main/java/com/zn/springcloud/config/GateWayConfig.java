package com.zn.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @描述
 * @返回值
 * @创建人 Administrator
 * @创建时间 2022/6/28
 */
//@Configuration
public class GateWayConfig {
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
//        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
//        routes.route("path_route_payment_8081_query",
//                r -> r.path("/query")
//                        .uri(" http://localhost:8081/zn/payment/query/**")).build();
//
//        routes.route( "path_route_payment_8081_timeOut",
//                r -> r.path("/timeOut")
//                        .uri(" http://localhost:8081/zn/payment/query/timeOut")).build();
//        return routes.build();
//    }
}
