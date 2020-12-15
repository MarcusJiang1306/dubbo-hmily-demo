package com.marcus.dubbo.bootConsumer.config;

import org.dromara.hmily.spring.annotation.RefererAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class HmilyConfiguration {

    @Bean
    @Primary
    public BeanPostProcessor refererAnnotationBeanPostProcessor() {
        return new RefererAnnotationBeanPostProcessor();
    }

}
