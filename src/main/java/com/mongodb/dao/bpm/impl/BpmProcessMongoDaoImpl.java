package com.mongodb.dao.bpm.impl;

import org.springframework.stereotype.Repository;

import com.mongodb.dao.base.impl.BaseMongoDaoImpl;
import com.mongodb.dao.bpm.BpmProcessMongoDao;
import com.mossle.bpm.persistence.domain.BpmProcess;
@Repository
public class BpmProcessMongoDaoImpl extends BaseMongoDaoImpl<BpmProcess> implements BpmProcessMongoDao {

	@Override
	protected Class<BpmProcess> getEntityClass() {
		// TODO Auto-generated method stub
		return BpmProcess.class;
	}

}
