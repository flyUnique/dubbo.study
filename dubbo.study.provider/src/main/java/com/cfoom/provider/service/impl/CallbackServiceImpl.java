package com.cfoom.provider.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.cfoom.service.CallbackListener;
import com.cfoom.service.CallbackService;

/**
 * Created by fly on 2017/9/4.
 */
public class CallbackServiceImpl implements CallbackService {

	private final Map<String, CallbackListener> listeners = new ConcurrentHashMap();

	public CallbackServiceImpl() {
		Thread t = new Thread(() -> {
			while(true) {
				try {
					for(Map.Entry<String, CallbackListener> entry : listeners.entrySet()){
						try {
							entry.getValue().changed(getChanged(entry.getKey()));
						} catch (Throwable t1) {
							listeners.remove(entry.getKey());
						}
					}
					Thread.sleep(5000); // 定时触发变更通知
				} catch (Throwable t1) { // 防御容错
					t1.printStackTrace();
				}
			}
		});
		t.setDaemon(true);
		t.start();
	}

	public void addListener(String key, CallbackListener listener) {
		listeners.put(key, listener);
		listener.changed(getChanged(key)); // 发送变更通知
	}

	private String getChanged(String key) {
		return key + " Changed: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
}
