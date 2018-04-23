package com.mossle.form.support;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import com.mongodb.dao.form.FormTemplateMongodbDao;
import com.mossle.api.form.FormConnector;
import com.mossle.api.form.FormDTO;
import com.mossle.form.persistence.domain.FormTemplate;
import com.mossle.form.persistence.manager.FormTemplateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class FormConnectorImpl implements FormConnector {
    private static Logger logger = LoggerFactory
            .getLogger(FormConnectorImpl.class);
   // private FormTemplateManager formTemplateManager;
    @Autowired
    private FormTemplateMongodbDao  formTemplateMongodbDao;

    public List<FormDTO> getAll(String tenantId) {

/*      String hql = "from FormTemplate where tenantId=?";
        List<FormTemplate> formTemplates = formTemplateManager.find(hql,
                tenantId);*/
    	// xuan  根据条件查询
        List<FormTemplate> formTemplates=formTemplateMongodbDao.find(Query.query(Criteria.where("tenantId").is(tenantId)));
        List<FormDTO> formDtos = new ArrayList<FormDTO>();

        for (FormTemplate formTemplate : formTemplates) {
            FormDTO formDto = new FormDTO();
            formDtos.add(formDto);
            formDto.setId(formTemplate.toString());
            formDto.setCode(formTemplate.getCode());
            formDto.setName(formTemplate.getName());
        }

        return formDtos;
    }

    public FormDTO findForm(String code, String tenantId) {
//        String hql = "from FormTemplate where code=? and tenantId=?";
//        FormTemplate formTemplate = formTemplateManager.findUnique(hql, code,
//                tenantId);
    	//xuan 根据条件查询唯一值
       FormTemplate formTemplate=formTemplateMongodbDao.findOne(Query.query(Criteria.where("code").is(code).and("tenantId").is(tenantId)));
        if (formTemplate == null) {
            logger.error("cannot find form : {}, {}", code, tenantId);

            return null;
        }

        FormDTO formDto = new FormDTO();
        formDto.setId(formTemplate.getId().toString());
        formDto.setCode(formTemplate.getCode());
        formDto.setName(formTemplate.getName());

        if (Integer.valueOf(1).equals(formTemplate.getType())) {
            formDto.setRedirect(true);
            formDto.setUrl(formTemplate.getContent());
        } else {
            formDto.setContent(formTemplate.getContent());
        }

        return formDto;
    }

//    @Resource
//    public void setFormTemplateManager(FormTemplateManager formTemplateManager) {
//        this.formTemplateManager = formTemplateManager;
//    }
}
