package com.mongodb.dao.bpm;

import org.springframework.stereotype.Service;

import com.mongodb.dao.base.BaseMongoDao;
import com.mossle.bpm.persistence.domain.BpmCategory;
@Service
public interface BpmCategoryMongoDao extends BaseMongoDao<BpmCategory> {

}
