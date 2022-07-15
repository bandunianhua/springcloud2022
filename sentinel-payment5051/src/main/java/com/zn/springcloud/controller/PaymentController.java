package com.zn.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.zn.springcloud.domain.CommentResult;
import com.zn.springcloud.domain.Payment;
import com.zn.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("zn/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @PostMapping("/insert")
    public CommentResult insert(@RequestBody Payment payment) {
        return paymentService.insert(payment);
    }

    @GetMapping("/query/{id}")
    public CommentResult query(@PathVariable("id") int id) {
        System.out.println(Thread.currentThread());
        return paymentService.query(id);
    }

    @GetMapping("/query/timeOut/{id}")
    public String timeOut(@PathVariable("id") int id) {
        if (id == 1) {
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "失败";
        }
        System.out.println(Thread.currentThread());
        return "SUC" + "8081" + IdUtil.randomUUID();
    }

    @GetMapping("/query/Rt/{id}")
    public String Rt(@PathVariable("id") int id) {

        try {
            TimeUnit.SECONDS.sleep(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread());
        return "SUC" + "8081" + IdUtil.randomUUID();
    }

    @GetMapping("/query/stream")
    public String stream() {

        return "服务" + port + "：SUC" + IdUtil.randomUUID();
    }


}
