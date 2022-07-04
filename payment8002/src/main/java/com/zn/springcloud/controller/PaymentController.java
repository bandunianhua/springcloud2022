package com.zn.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.zn.springcloud.domain.CommentResult;
import com.zn.springcloud.domain.Payment;
import com.zn.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("zn/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;


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
    public String timeOut()  {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "SUC"+"8082"+ IdUtil.randomUUID();
    }

    @GetMapping(value = "/discovery")
    public Object discovery() {

        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("*****element: " + element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t"
                    + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

}
