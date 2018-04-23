package com.mongodb.dao.bpm.impl;

import org.springframework.stereotype.Repository;

import com.mongodb.dao.base.impl.BaseMongoDaoImpl;
import com.mongodb.dao.bpm.BpmConfFormMongoDao;
import com.mossle.bpm.persistence.domain.BpmConfForm;

@Repository
public class BpmConfFormMongoDaoImpl extends BaseMongoDaoImpl<BpmConfForm> implements BpmConfFormMongoDao {

	@Override
	protected Class<BpmConfForm> getEntityClass() {
		// TODO Auto-generated method stub
		return BpmConfForm.class;
	}

}
