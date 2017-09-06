package com.cfoom.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.cfoom.service.AnnotationService;

/**
 * Created by fly on 2017/9/4.
 */
@Service
public class AnnotationServiceImpl implements AnnotationService{

	@Override
	public String invokeAnnotation() {
		return "annotation service is invoked!!!";
	}
}
