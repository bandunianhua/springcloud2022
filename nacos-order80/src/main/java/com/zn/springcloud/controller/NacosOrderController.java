package com.zn.springcloud.controller;

import com.zn.springcloud.domain.CommentResult;
import com.zn.springcloud.domain.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @描述
 * @返回值
 * @创建人 Administrator
 * @创建时间 2022/7/8
 */
@RestController
@RequestMapping("zn/order/nacos")
public class NacosOrderController {

    @Value("${service-url.nacos-user-service}")
    private String PAYMENT_URL;

    @Resource
    private RestTemplate restTemplate;


    /**
     * 自定义负载均衡
     *
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public CommentResult<Payment> query(@PathVariable("id") int id) {
        return restTemplate.getForObject(PAYMENT_URL +
                "/query/" + id, CommentResult.class);
    }

}
