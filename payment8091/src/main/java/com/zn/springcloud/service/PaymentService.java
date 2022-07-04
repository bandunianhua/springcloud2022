package com.zn.springcloud.service;

import com.zn.springcloud.domain.CommentResult;
import com.zn.springcloud.domain.Payment;
import com.zn.springcloud.manager.PaymentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Value("${server.port}")
    private String severPort;

    @Autowired
    private PaymentManager paymentManager;

    public CommentResult insert(Payment payment) {
        int count = paymentManager.insert(payment);

        if (count == 0) {
            return new CommentResult<Payment>("500",
                    severPort + "：插入失败！！！");
        } else {
            return new CommentResult<Payment>("SUC",
                    severPort + "：插入成功!!！！!", payment);
        }
    }

    public CommentResult query(int id) {

        Payment payment = paymentManager.query(id);
        if (payment == null) {
            return new CommentResult<Payment>("500",
                    severPort + "：查询失败");
        } else {
            return new CommentResult<Payment>("SUC",
                    severPort + "：查询成功！！", payment);
        }

    }
}
