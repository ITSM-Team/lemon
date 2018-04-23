package com.mongodb.dao.task.impl;

import org.springframework.stereotype.Repository;

import com.mongodb.dao.base.impl.BaseMongoDaoImpl;
import com.mongodb.dao.task.TaskDeadlineMongDao;
import com.mossle.humantask.persistence.domain.TaskDeadline;
@Repository
public class TaskDeadlineMongoDaoImpl extends BaseMongoDaoImpl<TaskDeadline> implements TaskDeadlineMongDao {

	@Override
	protected Class<TaskDeadline> getEntityClass() {
		// TODO Auto-generated method stub
		return TaskDeadline.class;
	}

}
