package com.mongodb.dao.base;

import java.util.List;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.WriteResult;
import com.mossle.core.page.Page;
import com.mossle.core.query.PropertyFilter;

/**
 * 实现Mongodb CRUD简单封装
 * */
public interface BaseMongoDao<T> {

	/**
	 * 插入
	 * */
	public T save(T entity);
	
	/**
	 * 新插入
	 * */
	public T insert(T entity);
	/**
	 * 根据ID查询
	 * */
	public T findById(Long id);
	
	/**
	 * 通过ID获取记录，并且指定了集合名（表）
	 * */
	public T findById(String id,String collectionName);
	
	/**
	 * 获取所有该类型记录
	 * */
	public List<T> findAll();
	
	/**
	 * 获取该类型所有记录，并且指定类集合名（表）
	 * */
	public List<T> findAll(String collectionName);
	
	/**
	 * 根据条件查询
	 * */
	public List<T> find(Query query);
	
	/**
	 * 根据条件查询一个
	 * */
	public T findOne(Query query);
	
	/**
	 * 分页查询
	 * */
	public Page findPage(Page page,
            List<PropertyFilter> propertyFilters,Query query);
	
	/**
	 * 根据条件 获得总数
	 * */
	public long count(Query query);
	
	/**
	 * 根据条件更新
	 * */
	public WriteResult update(Query query,Update update);
	
	/**
	 * 更新符合条件并sort之后的一个文档，并返回更新后的文档
	 * */
	public T updateOne(Query query,Update update);
	
	/**
	 * 根据传入实体ID更新
	 * */
	public WriteResult update(T entity);
	
	/**
	 * 根据条件删除
	 * */
	public void remove(Query query);
	
	/**
	 * 根据id删除
	 * */
	public void remove(Long id);
}
