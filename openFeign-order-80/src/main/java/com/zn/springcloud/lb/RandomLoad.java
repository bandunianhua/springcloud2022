package com.zn.springcloud.lb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class RandomLoad implements ILoadBalance {

    @Autowired
    private DiscoveryClient discoveryClient;

    private AtomicInteger count = new AtomicInteger(0);


    @Override
    public ServiceInstance instance() {

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-APPLYMENT-SERVICE");

        int expect;
        int update;
        do {
            expect = count.get();
            update = expect % instances.size();
        } while (!this.count.compareAndSet(expect, expect+1));


        ServiceInstance instance = instances.get(update);
        log.info(LocalDateTime.now() + "    " + count.get() + "次访问：" +
                instance.getHost() + instance.getPort());

        return instance;
    }
}
