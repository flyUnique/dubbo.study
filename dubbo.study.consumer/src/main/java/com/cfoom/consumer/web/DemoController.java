package com.cfoom.consumer.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cfoom.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

//    @Autowired
    @Reference(url = "dubbo://localhost:20880")
    private DemoService demoService;

    @GetMapping("/hello")
    public String hello() {
        return demoService.sayHello();
    }
}
