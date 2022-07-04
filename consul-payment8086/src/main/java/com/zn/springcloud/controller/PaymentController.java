package com.zn.springcloud.controller;

import com.zn.springcloud.domain.CommentResult;
import com.zn.springcloud.domain.Payment;
import com.zn.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("zn/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/insert")
    public CommentResult insert(@RequestBody Payment payment) {
        return paymentService.insert(payment);
    }

    @GetMapping("/query/{id}")
    public CommentResult query(@PathVariable("id") int id) {
        String a = "";
        return paymentService.query(id);
    }


    @GetMapping("/zk")
    public Object query() {
        return "SpringCloud with Zookeeper is start, 8085:" + UUID.randomUUID().toString();
    }
}
