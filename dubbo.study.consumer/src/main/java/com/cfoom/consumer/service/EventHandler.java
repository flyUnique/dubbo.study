package com.cfoom.consumer.service;

import org.springframework.stereotype.Component;

/**
 * Created by fly on 2017/9/4.
 */
@Component
public class EventHandler {

	public void onReturn() {
		System.out.println("调用返回了");
	}

	public void onThrow() {
		System.out.println("调用出异常了");
	}

}
