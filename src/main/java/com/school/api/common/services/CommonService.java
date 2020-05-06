package com.school.api.common.services;

import java.util.List;

import com.school.api.common.vo.Filter;

public interface CommonService {

	public <T> T findById(Object id, Class<?> clazz);

	public <T> T findOne(List<Filter> filters, Class<?> clazz);

	public <T> List<T> findAll(Class<?> clazz);

	public <T> List<T> find(List<Filter> filters, Class<?> clazz);

	public <T> T save(Object entity);

	public void delete(Object entity);

}
