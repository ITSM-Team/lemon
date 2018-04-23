package com.mongodb.dao.form.impl;

import org.springframework.stereotype.Repository;

import com.mongodb.dao.base.impl.BaseMongoDaoImpl;
import com.mongodb.dao.form.PropMongodbDao;
import com.mossle.api.keyvalue.Prop;
@Repository
public class PropMongoDaoImpl extends BaseMongoDaoImpl<Prop> implements PropMongodbDao {

	@Override
	protected Class<Prop> getEntityClass() {
		// TODO Auto-generated method stub
		return Prop.class;
	}

}
