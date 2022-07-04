package com.zn.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @描述
 * @返回值
 * @创建人 Administrator
 * @创建时间 2022/6/29
 */
@Component
@Slf4j
public class GateWayGlobal implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("***********Come in myFilter*************");
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if (uname == null && !uname.equals("admin")) {
            log.warn("***********用户名：" + uname + "不是管理员*************");
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
