package com.zn.springcloud.myhandler;

import cn.hutool.core.util.IdUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @描述
 * @返回值
 * @创建人 Administrator
 * @创建时间 2022/7/18
 */
public class CustomerBlockHandler {

    public static String fallBack1(BlockException exception) {
        return "降级方法1兜底处理  " + exception + IdUtil.randomUUID();
    }

    public static String fallBack2(BlockException exception) {
        return "降级方法2兜底处理  " + exception + IdUtil.randomUUID();
    }
}
