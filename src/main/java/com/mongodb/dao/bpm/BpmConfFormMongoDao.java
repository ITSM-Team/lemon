package com.mongodb.dao.bpm;

import org.springframework.stereotype.Service;

import com.mongodb.dao.base.BaseMongoDao;
import com.mossle.bpm.persistence.domain.BpmConfForm;
@Service
public interface BpmConfFormMongoDao extends BaseMongoDao<BpmConfForm> {

}
