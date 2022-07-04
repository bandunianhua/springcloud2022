package com.zn.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface ILoadBalance {

    ServiceInstance instance();
}
