package com.zn.ribbonRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonRule {

    /**
     *
     * @return 随机策略
     */
    @Bean
    public IRule randomStatistic(){
        return new RandomRule();
    }
}
