package com.example.producer.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Component
@Service
public class ProducerServiceImpl implements ProducerService {
    @Override
    public String sayHello(String name) {
        return "hello," + name;
    }
}
