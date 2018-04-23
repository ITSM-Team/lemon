package com.mongodb.dao.bpm.impl;

import org.springframework.stereotype.Repository;

import com.mongodb.dao.base.impl.BaseMongoDaoImpl;
import com.mongodb.dao.bpm.BpmCategoryMongoDao;
import com.mossle.bpm.persistence.domain.BpmCategory;
@Repository
public class BpmCategoryMongoDaoImpl extends BaseMongoDaoImpl<BpmCategory> implements BpmCategoryMongoDao {

	@Override
	protected Class<BpmCategory> getEntityClass() {
		// TODO Auto-generated method stub
		return BpmCategory.class;
	}

}
