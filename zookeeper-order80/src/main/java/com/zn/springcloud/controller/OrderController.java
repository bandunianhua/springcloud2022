package com.zn.springcloud.controller;


import com.zn.springcloud.domain.CommentResult;
import com.zn.springcloud.domain.Payment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("zn/order")
public class OrderController {
   // private static final String PAYMENT_URL = "http://localhost:8081/zn/payment";

    //支持负载均衡的写法
    private static final String PAYMENT_URL = "http://cloud-applyment-service/zn/payment";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/insert")
    public CommentResult<Payment> insert(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL +
                "/insert", payment, CommentResult.class);
    }

    @GetMapping("/query/{id}")
    public CommentResult<Payment> query(@PathVariable("id") int id) {
        return restTemplate.getForObject(PAYMENT_URL +
                "/query/" + id, CommentResult.class);
    }
}
