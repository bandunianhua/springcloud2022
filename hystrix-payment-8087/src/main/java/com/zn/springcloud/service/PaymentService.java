package com.zn.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.zn.springcloud.domain.CommentResult;
import com.zn.springcloud.domain.Payment;
import com.zn.springcloud.manager.PaymentManager;
import org.bouncycastle.util.Longs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    @Value("${server.port}")
    private String severPort;

    @Autowired
    private PaymentManager paymentManager;

    public CommentResult<Payment> insert(Payment payment) {
        int count = paymentManager.insert(payment);

        if (count == 0) {
            return new CommentResult<Payment>("500",
                    severPort + "：插入失败！！！");
        } else {
            return new CommentResult<Payment>("SUC",
                    severPort + "：插入成功!!！！!", payment);
        }
    }


    /**
     * @date: 2022/6/23 16:40
     * @user: Administrator
     * @param:
     * @description: 查询
     */
    public CommentResult<Payment> query(int id) {
        Payment payment = paymentManager.query(id);
        if (payment == null) {
            return new CommentResult<Payment>("500",
                    severPort + "：查询失败");
        } else {
            return new CommentResult<Payment>("SUC",
                    severPort + "：查询成功！！", payment);
        }
    }

    /**
     * @date: 2022/6/23 16:40
     * @user: Administrator
     * @param:
     * @description: hystrix限流降级
     */
    @HystrixCommand(fallbackMethod = "queryFallBack", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public CommentResult<Payment> queryTimeOut(int id) {
        try {
            TimeUnit.SECONDS.sleep(Longs.valueOf(id / 2));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //int i=1/0;//运行异常或者超时都会导致降级
        Payment payment = paymentManager.query(id);
        if (payment == null) {
            return new CommentResult<Payment>("500",
                    severPort + "：查询失败");
        } else {
            return new CommentResult<Payment>("SUC",
                    severPort + "：查询成功！！", payment);
        }
    }

    /**
     * @date: 2022/6/23 16:39
     * @user: Administrator
     * @param:
     * @description: 查询服务降级后备用响应
     */
    public CommentResult<Payment> queryFallBack(int id) {

        return new CommentResult<Payment>("500",
                Thread.currentThread() + ":服务访问" + id/2 + "秒，限流降级处理！！！");
    }
}
