package com.school.common.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.school.config.Beans;

@Service
public class BeansService {

	@Autowired
	private ApplicationContext applicationContext;

	public Beans getBeans() {
		Beans beans = (Beans) applicationContext.getBean(Beans.class);
		return beans != null ? beans : null;
	}

}
