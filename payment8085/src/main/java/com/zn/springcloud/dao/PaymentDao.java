package com.zn.springcloud.dao;


import com.zn.springcloud.domain.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    int insert(Payment payment);

    Payment query(@Param("id") int id);
}
