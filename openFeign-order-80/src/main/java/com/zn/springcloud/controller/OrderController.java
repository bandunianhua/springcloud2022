package com.zn.springcloud.controller;


import com.zn.springcloud.domain.CommentResult;
import com.zn.springcloud.domain.Payment;
import com.zn.springcloud.lb.ILoadBalance;
import com.zn.springcloud.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("zn/order")
public class OrderController {

    @Resource
    private IPaymentService iPaymentService;

    @GetMapping("/query/{id}")
    public CommentResult<Payment> query(@PathVariable("id") int id) {
        return iPaymentService.query(id);
    }

    @GetMapping("/timeOut")
    public String timeOut() {
        return iPaymentService.timeOut();
    }

}
