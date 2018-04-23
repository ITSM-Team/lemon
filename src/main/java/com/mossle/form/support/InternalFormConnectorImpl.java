package com.mossle.form.support;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.dao.form.FormTemplateMongodbDao;
import com.mossle.core.id.IdGenerator;
import com.mossle.form.persistence.domain.FormTemplate;
import com.mossle.form.persistence.manager.FormTemplateManager;

import com.mossle.spi.form.InternalFormConnector;

public class InternalFormConnectorImpl implements InternalFormConnector {
   // private FormTemplateManager formTemplateManager;
    @Autowired
    private FormTemplateMongodbDao formTemplateMongodbDao;
    private IdGenerator idGenerator;

    public void save(String code, String content, Integer type) {
        FormTemplate formTemplate = new FormTemplate();
        formTemplate.setCode(code);
        formTemplate.setContent(content);
        formTemplate.setType(type);
        formTemplate.setId(idGenerator.generateId());
        //formTemplateManager.save(formTemplate);
        formTemplateMongodbDao.save(formTemplate);
    }

//    @Resource
//    public void setFormTemplateManager(FormTemplateManager formTemplateManager) {
//        this.formTemplateManager = formTemplateManager;
//    }

    @Resource
	public void setIdGenerator(IdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}
    
}
