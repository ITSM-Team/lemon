package com.mongodb.dao.form.impl;

import org.springframework.stereotype.Repository;

import com.mongodb.dao.base.impl.BaseMongoDaoImpl;
import com.mongodb.dao.form.FormTemplateMongodbDao;
import com.mossle.form.persistence.domain.FormTemplate;
@Repository
public class FormTemplateMongodbDaoImpl  extends BaseMongoDaoImpl<FormTemplate> implements FormTemplateMongodbDao {

	@Override
	protected Class<FormTemplate> getEntityClass() {
		// TODO Auto-generated method stub
		return FormTemplate.class;
	}

}
