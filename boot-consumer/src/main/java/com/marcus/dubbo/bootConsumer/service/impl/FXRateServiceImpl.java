package com.marcus.dubbo.bootConsumer.service.impl;

import com.marcus.dubbo.bootConsumer.service.FXRateService;
import org.springframework.stereotype.Service;

@Service
public class FXRateServiceImpl implements FXRateService {
    @Override
    public double getRateViaCcyPair(String ccyPair) {
        double rate;
        if ("USD|CNH".equals(ccyPair)) {
            rate = 6.666;
        } else if ("CNH|USD".equals(ccyPair)) {
            rate = 0.15;
        } else rate = 1;
        return rate;
    }
}
