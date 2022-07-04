package com.zn.springcloud.service;

import com.zn.springcloud.domain.CommentResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @描述
 * @返回值
 * @创建人 Administrator
 * @创建时间 2022/6/22
 */
@Component
@FeignClient("CLOUD-APPLYMENT-SERVICE")
public interface IPaymentService {

    /**
     * @date:
     */
    @GetMapping("zn/payment/query/{id}")
    public CommentResult query(@PathVariable("id") int id);

    /**
     * @date: 超时测试
     */
    @GetMapping("zn/payment/query/timeOut")
    public String timeOut();

}
