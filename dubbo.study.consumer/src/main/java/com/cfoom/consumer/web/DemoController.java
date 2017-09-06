package com.cfoom.consumer.web;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.cfoom.service.AnnotationService;
import com.cfoom.service.CallbackListener;
import com.cfoom.service.CallbackService;
import com.cfoom.service.DemoService;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

	@Reference
	private AnnotationService annotationService;

	@Reference
	private CallbackService callbackService;

    @GetMapping("/hello")
    public String hello() throws ExecutionException, InterruptedException {
        // 所有的dubbo服务均是 EchoService子类
//        EchoService echoService = (EchoService) demoService; // 强制转型为EchoService
//        Object ok = echoService.$echo("ok");
//        System.out.println("echo :" + ok);

//        boolean isConsumerSide = RpcContext.getContext().isConsumerSide(); // 本端是否为消费端，这里会返回true
//		System.out.println("isConsumerSide:" + isConsumerSide);
//		String serverIP = RpcContext.getContext().getRemoteHost(); // 获取最后一次调用的提供方IP地址
//		System.out.println("provider ip is " + serverIP);
//		String application = RpcContext.getContext().getUrl().getParameter("application"); // 获取当前服务配置信息，所有配置信息都将转换为URL的参数
//		System.out.println("application:" + application);
		RpcContext.getContext().setAttachment("consumer", "consumer");
//		Future<String> future = RpcContext.getContext().asyncCall(() -> demoService.sayHello());
		String hello = demoService.sayHello();
//		String hello = future.get();
//		System.out.println(RpcContext.getContext().getAttachment("provider"));
		return (String) RpcContext.getContext().getFuture().get();
    }

    @RequestMapping("/annotation")
	public String annotation() {
		return annotationService.invokeAnnotation();
	}

	@RequestMapping("/callback")
	public String callback(String key) {
    	callbackService.addListener(key,msg -> System.out.println(msg));
		return key + " changed";
	}
}
