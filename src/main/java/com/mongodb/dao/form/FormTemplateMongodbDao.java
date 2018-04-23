package com.mongodb.dao.form;

import org.springframework.stereotype.Service;

import com.mongodb.dao.base.BaseMongoDao;
import com.mossle.form.persistence.domain.FormTemplate;
@Service
public interface FormTemplateMongodbDao extends BaseMongoDao<FormTemplate> {

}
