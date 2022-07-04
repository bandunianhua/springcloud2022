package com.zn.springcloud.controller;

import com.zn.springcloud.domain.CommentResult;
import com.zn.springcloud.domain.Payment;
import com.zn.springcloud.service.PaymentService;
import org.bouncycastle.util.Longs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("zn/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/insert")
    public CommentResult<Payment> insert(@RequestBody Payment payment) {
        return paymentService.insert(payment);
    }

    @GetMapping("/query/{id}")
    public CommentResult<Payment> query(@PathVariable("id") int id) {
        String a = "";
        return paymentService.query(id);
    }

    @GetMapping("/query/timeOut/{id}")
    public CommentResult<Payment> queryTimeOut(@PathVariable("id") int id) {

        return paymentService.queryTimeOut(id);
    }
}
