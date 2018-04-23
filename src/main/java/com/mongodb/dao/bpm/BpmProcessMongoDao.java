package com.mongodb.dao.bpm;

import org.springframework.stereotype.Service;

import com.mongodb.dao.base.BaseMongoDao;
import com.mossle.bpm.persistence.domain.BpmProcess;
@Service
public interface BpmProcessMongoDao extends BaseMongoDao<BpmProcess> {

}
