package com.mongodb.dao.bpm.impl;

import org.springframework.stereotype.Repository;

import com.mongodb.dao.base.impl.BaseMongoDaoImpl;
import com.mongodb.dao.bpm.BpmConfBaseMongoDao;
import com.mossle.bpm.persistence.domain.BpmConfBase;
@Repository
public class BpmConfBaseMongoDaoImpl extends BaseMongoDaoImpl<BpmConfBase> implements BpmConfBaseMongoDao {

	@Override
	protected Class<BpmConfBase> getEntityClass() {
		// TODO Auto-generated method stub
		return BpmConfBase.class;
	}

}
