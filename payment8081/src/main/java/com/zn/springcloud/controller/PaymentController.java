package com.zn.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.zn.springcloud.domain.CommentResult;
import com.zn.springcloud.domain.Payment;
import com.zn.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

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

    @GetMapping("/query/timeOut")
    public String timeOut() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "SUC" + "8081" + IdUtil.randomUUID();
    }

    @GetMapping("/query/stream")
    public String stream() {

        return "SUC" + IdUtil.randomUUID();
    }


}
