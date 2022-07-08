package com.zn.springcloud.controller;


import com.zn.springcloud.domain.CommentResult;
import com.zn.springcloud.domain.Payment;
import com.zn.springcloud.lb.ILoadBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@RestController
@RequestMapping("zn/order")
public class OrderController {
    // private static final String PAYMENT_URL = "http://localhost:8081/zn/payment";

    //支持负载均衡的写法
    private static final String PAYMENT_URL = "http://CLOUD-APPLYMENT-SERVICE/zn/payment";

    @Autowired
    private ILoadBalance iLoadBalance;

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

    @GetMapping("/queryByEntity/{id}")
    public ResponseEntity<CommentResult> queryByEntity(@PathVariable("id") int id) {
        ServiceInstance instance = iLoadBalance.instance();

        return restTemplate.getForEntity(PAYMENT_URL +
                "/query/" + id, CommentResult.class);
    }

    /**
     * 自定义负载均衡
     *
     * @param id
     * @return
     */
    @GetMapping("/queryByMyLB/{id}")
    public Object queryByMyLB(@PathVariable("id") int id) {
        ServiceInstance instance = iLoadBalance.instance();

        return instance.getPort();
    }

    /**
     * @param id
     * @return
     * @deprecated 测试hystrix限流降级熔断
     */
    @GetMapping("/queryTimeOut/{id}")
    public List<CommentResult<Payment>> queryTimeOut(@PathVariable("id") int id) {
        List<CommentResult<Payment>> list = new ArrayList<>();

        CountDownLatch countDownLatch=new CountDownLatch(id*20);
        for (int i = 0; i < id * 20; i++) {
            new Thread(() -> {
                list.add(restTemplate.getForObject(PAYMENT_URL +
                        "/query/timeOut/" + id, CommentResult.class));
                countDownLatch.countDown();
            }, "").start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }
}
