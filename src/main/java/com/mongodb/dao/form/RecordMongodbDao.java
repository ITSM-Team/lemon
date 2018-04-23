package com.mongodb.dao.form;

import org.springframework.stereotype.Service;

import com.mongodb.dao.base.BaseMongoDao;
import com.mossle.api.keyvalue.Record;
@Service
public interface RecordMongodbDao extends BaseMongoDao<Record> {

}
