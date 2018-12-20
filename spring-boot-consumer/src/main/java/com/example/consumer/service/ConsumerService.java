package com.example.consumer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.producer.service.ProducerService;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @Reference
    ProducerService producerService;

    public void sayHello() {
        String s = producerService.sayHello("world");
        System.out.println(s);
    }
}
