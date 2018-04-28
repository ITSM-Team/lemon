package com.mongodb.dao.task.impl;

import org.springframework.stereotype.Repository;

import com.mongodb.dao.base.impl.BaseMongoDaoImpl;
import com.mongodb.dao.task.TaskParticipantMongoDao;
import com.mossle.humantask.persistence.domain.TaskParticipant;

@Repository
public class TaskParticipantMongoDaoImpl extends BaseMongoDaoImpl<TaskParticipant> implements TaskParticipantMongoDao {

	@Override
	protected Class<TaskParticipant> getEntityClass() {
		// TODO Auto-generated method stub
		return TaskParticipant.class;
	}

}
