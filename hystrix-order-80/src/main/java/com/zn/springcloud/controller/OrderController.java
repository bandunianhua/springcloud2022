package com.zn.springcloud.controller;


import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zn.springcloud.domain.CommentResult;
import com.zn.springcloud.domain.Payment;
import com.zn.springcloud.lb.ILoadBalance;
import com.zn.springcloud.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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

    @Resource
    private IPaymentService iPaymentService;

    @PostMapping("/insert")
    public CommentResult<Payment> insert(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL +
                "/insert", payment, CommentResult.class);
    }

    @GetMapping("/query/{id}")
    public CommentResult<Payment> query(@PathVariable("id") int id) {
        try {
            TimeUnit.SECONDS.sleep(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return restTemplate.getForObject(PAYMENT_URL +
                "/query/" + id, CommentResult.class);
    }

    @GetMapping("/queryByEntity/{id}")
    public ResponseEntity<CommentResult> queryByEntity(@PathVariable("id") int id) {
        ServiceInstance instance = iLoadBalance.instance();

        try {
            TimeUnit.SECONDS.sleep(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return restTemplate.getForEntity(PAYMENT_URL +
                "/query/" + id, CommentResult.class);
    }

    /**
     * 自定义负载均衡
     *
     * @param id
     * @return
     */
    @HystrixCommand
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
    @HystrixCommand(fallbackMethod = "queryFallBack", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public List<CommentResult<Payment>> queryTimeOut(@PathVariable("id") int id) {
        List<CommentResult<Payment>> list = new ArrayList<>();

        try {
            TimeUnit.MILLISECONDS.sleep(id * 1000 / 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list.add(restTemplate.getForObject(PAYMENT_URL +
                "/query/timeOut/" + id, CommentResult.class));
        return list;
    }

    /**
     * @description: 自定义服务降级方法
     */
    private List<CommentResult<Payment>> queryFallBack(int id) {
        List<CommentResult<Payment>> list = new ArrayList<>();
        list.add(new CommentResult<>("501", "订单端服务调用超时："));
        return list;
    }

    private List<CommentResult<Payment>> defaultFallback(int id) {
        List<CommentResult<Payment>> list = new ArrayList<>();
        list.add(new CommentResult<>("501", "默然降级方法处理，订单端服务调用超时："));
        return list;
    }


    /**
     * @date: 2022/6/24 17:18
     * @user: Administrator
     * @param:
     * @description: feign默认降级处理
     */
    @GetMapping("/queryFeign/{id}")
    public CommentResult<Payment> queryFeign(@PathVariable("id") int id) {
        return iPaymentService.query(id);
    }

    /**
     * @date: 2022/6/24 17:18
     * @user: Administrator
     * @param:
     * @description: feign默认降级处理
     */
    @GetMapping("/timeOut")
    public String timeOut() {
        return iPaymentService.timeOut();
    }

    /**
     * @date: 2022/6/24 17:18
     * @user: Administrator
     * @param:
     * @description: hystrix熔断方法
     */
    @GetMapping("/queryBreak/{id}")
    @HystrixCommand(fallbackMethod = "fallbackMethod", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间窗口
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),//失败率达到多少后跳闸
    })
    public CommentResult<Payment> queryBreak(@PathVariable("id") int id) {
        if (id < 0) {
            throw new RuntimeException("id:" + id + "不能为负数！！！！");
        }

        CommentResult<Payment> commentResult = iPaymentService.query(id);

        commentResult.setMessage(commentResult.getMessage() + IdUtil.randomUUID());
        return commentResult;
    }

    private CommentResult<Payment> fallbackMethod(int id) {
        return new CommentResult<>(new Payment(id, "id:" + id + "不能为负数！！！！,熔断方法启动"));
    }
}
