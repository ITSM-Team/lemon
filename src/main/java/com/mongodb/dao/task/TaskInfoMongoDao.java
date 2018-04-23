package com.mongodb.dao.task;

import org.springframework.stereotype.Service;

import com.mongodb.dao.base.BaseMongoDao;
import com.mossle.humantask.persistence.domain.TaskInfo;
@Service
public interface TaskInfoMongoDao extends BaseMongoDao<TaskInfo> {

}
