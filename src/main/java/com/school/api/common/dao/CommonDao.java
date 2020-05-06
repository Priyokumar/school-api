package com.school.api.common.dao;

import java.util.List;

import com.school.api.common.vo.Filter;

public interface CommonDao {

	public Object findById(Object id, Class<?> clazz);

	public Object findOne(List<Filter> filters, Class<?> clazz);

	public Object findAll(Class<?> clazz);

	public Object find(List<Filter> filters, Class<?> clazz);

	public Object save(Object entity);

	public void delete(Object entity);

}
