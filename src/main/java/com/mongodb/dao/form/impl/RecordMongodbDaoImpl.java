package com.mongodb.dao.form.impl;

import org.springframework.stereotype.Repository;

import com.mongodb.dao.base.impl.BaseMongoDaoImpl;
import com.mongodb.dao.form.RecordMongodbDao;
import com.mossle.api.keyvalue.Record;
@Repository
public class RecordMongodbDaoImpl extends BaseMongoDaoImpl<Record> implements RecordMongodbDao {

	@Override
	protected Class<Record> getEntityClass() {
		// TODO Auto-generated method stub
		return Record.class;
	}

}
