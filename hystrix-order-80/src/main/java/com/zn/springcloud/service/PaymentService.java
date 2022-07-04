package com.zn.springcloud.service;

import com.zn.springcloud.domain.CommentResult;
import com.zn.springcloud.domain.Payment;
import org.springframework.stereotype.Component;

/**
 * @描述
 * @返回值
 * @创建人 Administrator
 * @创建时间 2022/6/24
 */
@Component
public class PaymentService implements IPaymentService{
    @Override
    public CommentResult<Payment> query(int id) {
        return new CommentResult<Payment>(new Payment(0,"查询服默认降级"));
    }

    @Override
    public String timeOut() {
        return "超时服默认降级";
    }
}
