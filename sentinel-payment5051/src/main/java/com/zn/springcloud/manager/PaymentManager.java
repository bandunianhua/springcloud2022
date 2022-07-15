package com.zn.springcloud.manager;

import com.zn.springcloud.domain.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zn.springcloud.dao.PaymentDao;

@Component
@Slf4j
public class PaymentManager {

    @Autowired
    private PaymentDao paymentDao;

    public int insert(Payment payment) {

        int count = 0;
        try {
            count = paymentDao.insert(payment);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("插入数据失败");
        }
        return count;
    }

    public Payment query(int id) {

        Payment payment = null;
        try {
            payment = paymentDao.query(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("查询数据失败");
        }
        return payment;
    }
}
