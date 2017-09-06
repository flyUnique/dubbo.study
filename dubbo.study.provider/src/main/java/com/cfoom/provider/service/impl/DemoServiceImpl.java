package com.cfoom.provider.service.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.cfoom.service.DemoService;

public class DemoServiceImpl implements DemoService{

    @Override
    public String sayHello() {
        RpcContext.getContext().setAttachment("provider", "provider");
        String consumner = RpcContext.getContext().getAttachment("consumer");
        System.out.println(consumner);
        System.out.println("provider side being invoke!!!");
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "hello world";
    }
}
