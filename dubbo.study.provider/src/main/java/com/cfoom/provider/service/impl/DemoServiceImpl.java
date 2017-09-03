package com.cfoom.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.cfoom.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService{

    @Override
    public String sayHello() {
        System.out.println("provider side being invoke!!!");
        return "hello world";
    }
}
