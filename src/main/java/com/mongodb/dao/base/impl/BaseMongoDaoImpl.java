package com.mongodb.dao.base.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.WriteResult;
import com.mongodb.Util.MongodbQueryUtil;
import com.mongodb.Util.ReflectionUtils;
import com.mongodb.dao.base.BaseMongoDao;
import com.mossle.core.page.Page;
import com.mossle.core.query.PropertyFilter;

public abstract class BaseMongoDaoImpl<T> implements BaseMongoDao<T>{
	protected abstract Class<T> getEntityClass();
	/**
	 * spring mongodb集成操作类
	 * */
	@Autowired
	protected MongoTemplate mongoTemplate;

	@Override
	public T save(T entity) {
		mongoTemplate.save(entity);
		return entity;
	}
	
	@Override
	public T insert(T entity){
		mongoTemplate.insert(entity);
		return entity;
	}

	@Override
	public T findById(Long id) {
		// TODO Auto-generated method stub
		return mongoTemplate.findById(id, this.getEntityClass());
	}

	@Override
	public T findById(String id, String collectionName) {
		// TODO Auto-generated method stub
		return mongoTemplate.findById(id, this.getEntityClass(),collectionName);
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(this.getEntityClass());
	}

	@Override
	public List<T> findAll(String collectionName) {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(this.getEntityClass(), collectionName);
	}

	@Override
	public List<T> find(Query query) {
		// TODO Auto-generated method stub
		return mongoTemplate.find(query, this.getEntityClass());
	}

	@Override
	public T findOne(Query query) {
		// TODO Auto-generated method stub
		return mongoTemplate.findOne(query, this.getEntityClass());
	}

	/**
	 * 按条件分页查询
	 * */
	public Page findPage(Page page,
            List<PropertyFilter> propertyFilters,Query query){				
	Map<String,List<Criteria>> map=MongodbQueryUtil.buildCriterion(propertyFilters);
	List<Criteria> andlist=new ArrayList<Criteria>();
	List<Criteria> orlist=new ArrayList<Criteria>();
	//获取and条件
	andlist=map.get("and");
	//获取or条件
	orlist=map.get("or");
	Criteria criteria=new Criteria();
	//判断条件
	if((andlist!=null && andlist.size()>0) &&(orlist!=null && orlist.size()>0)){		
		criteria.andOperator(andlist.toArray(new Criteria[andlist.size()]))
			.orOperator(orlist.toArray(new Criteria[orlist.size()]));
	}else if(andlist!=null && andlist.size()>0){
		criteria.andOperator(andlist.toArray(new Criteria[andlist.size()]));
	}else if(orlist!=null && orlist.size()>0){
		criteria.orOperator(orlist.toArray(new Criteria[orlist.size()]));
	}
	int pageNo=page.getPageNo();
	int pageSize=page.getPageSize();
	int no=(pageNo-1)*pageSize;
	//添加条件
	query.addCriteria(criteria);
	query=query.skip(no).limit(pageSize);
	List<T> list=mongoTemplate.find(query, this.getEntityClass());
	long count=mongoTemplate.count(query, this.getEntityClass());
	page.setResult(list);
	page.setTotalCount(count);
	map=null;
	andlist=null;
	orlist=null;
	return page;
		
	}

	@Override
	public long count(Query query) {
		// TODO Auto-generated method stub
		return mongoTemplate.count(query, this.getEntityClass());
	}

	@Override
	public WriteResult update(Query query, Update update) {
		if(update==null){ 
			return null;
			}
		return mongoTemplate.updateMulti(query, update, this.getEntityClass());
	}

	@Override
	public T updateOne(Query query, Update update) {
		if(update==null){
			return null;
		}
		return mongoTemplate.findAndModify(query, update, this.getEntityClass());
	}

	@Override
	public WriteResult update(T entity) {
		Field[]  fields=this.getEntityClass().getDeclaredFields();
		if(fields==null || fields.length<=0){
			return null;
		}
		Field idfield=null;
		//查找ID的field
		for(Field field : fields){
			if(field.getName()!=null && "id".equals(field.getName().toLowerCase())){
				idfield=field;
			    break;
			}
		}
		if(idfield==null){
			return null;
		}
		idfield.setAccessible(true);
		
		Object id=null;
		try{
			id=idfield.get(entity);
		}catch(IllegalArgumentException  e){
			e.printStackTrace();
		}catch (IllegalAccessException  e) {
			e.printStackTrace();
		}
		if(id==null || "".equals(id.toString().trim())){
			return null;
		}
		//根据ID更新
		Query query=new Query(Criteria.where("_id").is(id));
		//更新
		Update update=ReflectionUtils.getUpdateObj(entity);
		if(update==null){
			return null;
		}
		return mongoTemplate.updateFirst(query, update, this.getEntityClass());
	}

	@Override
	public void remove(Query query) {
		mongoTemplate.remove(query,this.getEntityClass());
		
	}

	/**
	 * 根据id删除
	 * */
	public void remove(Long id){
		Query query=new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		mongoTemplate.remove(query,this.getEntityClass());
	}
	
}
