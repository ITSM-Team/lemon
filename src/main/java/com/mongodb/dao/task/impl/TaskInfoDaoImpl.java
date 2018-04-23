package com.mongodb.dao.task.impl;
import org.springframework.stereotype.Repository;
import com.mongodb.dao.base.impl.BaseMongoDaoImpl;
import com.mongodb.dao.task.TaskInfoMongoDao;
import com.mossle.humantask.persistence.domain.TaskInfo;
@Repository
public class TaskInfoDaoImpl extends BaseMongoDaoImpl<TaskInfo> implements TaskInfoMongoDao{

	@Override
	protected Class<TaskInfo> getEntityClass() {
		// TODO Auto-generated method stub
		return TaskInfo.class;
	}

}
