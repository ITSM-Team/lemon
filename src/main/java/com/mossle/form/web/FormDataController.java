package com.mossle.form.web;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.dao.form.FormTemplateMongodbDao;
import com.mossle.api.form.FormConnector;
import com.mossle.api.form.FormDTO;
import com.mossle.api.keyvalue.FormParameter;
import com.mossle.api.keyvalue.KeyValueConnector;
import com.mossle.api.keyvalue.Prop;
import com.mossle.api.keyvalue.Record;
import com.mossle.api.keyvalue.RecordBuilder;
import com.mossle.api.store.StoreConnector;
import com.mossle.api.tenant.TenantHolder;
import com.mossle.api.user.UserConnector;

import com.mossle.core.MultipartHandler;
import com.mossle.core.auth.CurrentUserHolder;
import com.mossle.core.export.Exportor;
import com.mossle.core.export.TableModel;
import com.mossle.core.mapper.BeanMapper;
import com.mossle.core.mapper.JsonMapper;
import com.mossle.core.page.Page;
import com.mossle.core.query.PropertyFilter;
import com.mossle.core.spring.MessageHelper;

import com.mossle.form.persistence.domain.FormTemplate;
import com.mossle.form.persistence.manager.FormTemplateManager;
import com.mossle.form.xform.Xform;
import com.mossle.form.xform.XformBuilder;

import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("form")
public class FormDataController {
    private static Logger logger = LoggerFactory
            .getLogger(FormDataController.class);
    public static final int TEST = -1;
   // private FormTemplateManager formTemplateManager;
    private Exportor exportor;
    private BeanMapper beanMapper = new BeanMapper();
    private JsonMapper jsonMapper = new JsonMapper();
    private MessageHelper messageHelper;
    private MultipartResolver multipartResolver;
    private TenantHolder tenantHolder;
    private CurrentUserHolder currentUserHolder;
    private KeyValueConnector keyValueConnector;
    private StoreConnector storeConnector;
    private FormConnector formConnector;
    private UserConnector userConnector;
    @Autowired
    private FormTemplateMongodbDao formTemplateMongodbDao;

    /**
     * 列出所有草稿.
     */
    @RequestMapping("form-data-list")
    public String list(@ModelAttribute Page page, Model model) throws Exception {
        String userId = currentUserHolder.getUserId();
        String tenantId = tenantHolder.getTenantId();
        page = keyValueConnector.pagedQuery(page, TEST, tenantId, tenantId);
        model.addAttribute("page", page);

        return "form/form-data-list";
    }

    @RequestMapping("form-data-input")
    public String input(
            @RequestParam(value = "businessKey", required = false) String businessKey,
            @RequestParam("category") String category, Model model)
            throws Exception {
        if (businessKey != null) {
            String tenantId = tenantHolder.getTenantId();
            FormParameter formParameter = new FormParameter();
            formParameter.setBpmProcessId(category);
            formParameter.setBusinessKey(businessKey);

            // FormDTO formDto = this.processConnector
            // .findStartForm(processDefinitionId);
            
//            FormTemplate formTemplate = formTemplateManager.findUniqueBy(
//                    "code", category);
            
            FormTemplate formTemplate = formTemplateMongodbDao.findOne(
            		Query.query(Criteria.where("code").is(category)));
            
            FormDTO formDto = new FormDTO();
            formDto.setContent(formTemplate.getContent());
            formDto.setCode(formTemplate.getCode());
            formParameter.setFormDto(formDto);

            if (formDto.isExists()) {
                if (formDto.isRedirect()) {
                    // 如果是外部表单，就直接跳转出去
                    String redirectUrl = formDto.getUrl()
                            + "?processDefinitionId="
                            + formDto.getProcessDefinitionId();

                    return "redirect:" + redirectUrl;
                }

                return this.doViewStartForm(formParameter, model, tenantId);
            }
        } else {
            String tenantId = tenantHolder.getTenantId();
            FormDTO formDto = formConnector.findForm(category, tenantId);

            Xform xform = new XformBuilder().setStoreConnector(storeConnector)
                    .setContent(formDto.getContent()).build();
            model.addAttribute("xform", xform);
            model.addAttribute("bpmProcessId", category);
        }

        return "form/form-data-input";
    }

    @RequestMapping("form-data-save")
    public String save(HttpServletRequest request,
            RedirectAttributes redirectAttributes) throws Exception {
        String userId = currentUserHolder.getUserId();
        String tenantId = tenantHolder.getTenantId();

        MultipartHandler multipartHandler = new MultipartHandler(
                multipartResolver);
        FormParameter formParameter = null;

        try {
            multipartHandler.handle(request);
            logger.debug("multiValueMap : {}",
                    multipartHandler.getMultiValueMap());
            logger.debug("multiFileMap : {}",
                    multipartHandler.getMultiFileMap());

            formParameter = this.buildFormParameter(multipartHandler);

            String businessKey = this.saveDraft(tenantId, tenantId,
                    formParameter);

            if ((formParameter.getBusinessKey() == null)
                    || "".equals(formParameter.getBusinessKey().trim())) {
                formParameter.setBusinessKey(businessKey);
            }

            Record record = keyValueConnector.findByCode(businessKey);

            if (record != null) {
                record = new RecordBuilder().build(record, multipartHandler,
                        storeConnector, tenantId);

                keyValueConnector.save(record);
            }
        } finally {
            multipartHandler.clear();
        }

        return "redirect:/form/form-data-list.do";
    }

    @RequestMapping("form-data-remove")
    public String remove(@RequestParam("code") String code,
            RedirectAttributes redirectAttributes) {
        keyValueConnector.removeByCode(code);
        messageHelper.addFlashMessage(redirectAttributes,
                "core.success.delete", "删除成功");

        return "redirect:/form/form-data-list.do";
    }

    // ~ ======================================================================
    /**
     * 通过multipart请求构建formParameter.
     */
    public FormParameter buildFormParameter(MultipartHandler multipartHandler) {
        FormParameter formParameter = new FormParameter();
        formParameter.setMultiValueMap(multipartHandler.getMultiValueMap());
        formParameter.setMultiFileMap(multipartHandler.getMultiFileMap());
        formParameter.setBusinessKey(multipartHandler.getMultiValueMap()
                .getFirst("businessKey"));
        formParameter.setBpmProcessId(multipartHandler.getMultiValueMap()
                .getFirst("bpmProcessId"));
        formParameter.setHumanTaskId(multipartHandler.getMultiValueMap()
                .getFirst("humanTaskId"));

        return formParameter;
    }

    /**
     * 保存草稿.
     */
    public String saveDraft(String userId, String tenantId,
            FormParameter formParameter) {
        String humanTaskId = formParameter.getHumanTaskId();
        String businessKey = formParameter.getBusinessKey();
        String bpmProcessId = formParameter.getBpmProcessId();

        if (StringUtils.isNotBlank(businessKey)) {
            // 如果是流程草稿，直接通过businessKey获得record，更新数据
            Record record = keyValueConnector.findByCode(businessKey);

            record = new RecordBuilder().build(record, TEST, formParameter);
            keyValueConnector.save(record);
        } else if (StringUtils.isNotBlank(bpmProcessId)) {
            // 如果是第一次保存草稿，肯定是流程草稿，先初始化record，再保存数据
            Record record = new RecordBuilder().build(bpmProcessId, TEST,
                    formParameter, userId, tenantId);
            // ProcessDTO processDto = processConnector.findProcess(bpmProcessId);
            // record.setName(processDto.getProcessDefinitionName());
            record.setName(bpmProcessId);
            keyValueConnector.save(record);
            businessKey = record.getCode();

            if (record.getBusinessKey() == null) {
                record.setBusinessKey(businessKey);
                keyValueConnector.save(record);
            }
        } else {
            logger.error(
                    "humanTaskId, businessKey, bpmProcessId all null : {}",
                    formParameter.getMultiValueMap());
            throw new IllegalArgumentException(
                    "humanTaskId, businessKey, bpmProcessId all null");
        }

        return businessKey;
    }

    /**
     * 实际显示开始表单.
     */
    public String doViewStartForm(FormParameter formParameter, Model model,
            String tenantId) throws Exception {
        model.addAttribute("formDto", formParameter.getFormDto());
        model.addAttribute("bpmProcessId", formParameter.getBpmProcessId());
        model.addAttribute("businessKey", formParameter.getBusinessKey());
        model.addAttribute("nextStep", formParameter.getNextStep());

        // List<ButtonDTO> buttons = new ArrayList<ButtonDTO>();
        // buttons.add(buttonHelper.findButton("saveDraft"));
        // buttons.add(buttonHelper.findButton(formParameter.getNextStep()));
        // model.addAttribute("buttons", buttons);
        model.addAttribute("formDto", formParameter.getFormDto());

        String json = this.findStartFormData(formParameter.getBusinessKey());

        if (json != null) {
            model.addAttribute("json", json);
        }

        Record record = keyValueConnector.findByCode(formParameter
                .getBusinessKey());
        FormDTO formDto = formConnector.findForm(formParameter.getFormDto()
                .getCode(), tenantId);

        if (record != null) {
            Xform xform = new XformBuilder().setStoreConnector(storeConnector)
                    .setUserConnector(userConnector)
                    .setContent(formDto.getContent()).setRecord(record).build();
            model.addAttribute("xform", xform);
        } else {
            Xform xform = new XformBuilder().setStoreConnector(storeConnector)
                    .setUserConnector(userConnector)
                    .setContent(formDto.getContent()).build();
            model.addAttribute("xform", xform);
        }

        return "form/form-data-input";
    }

    /**
     * 读取草稿箱中的表单数据，转换成json.
     */
    public String findStartFormData(String businessKey) throws Exception {
        Record record = keyValueConnector.findByCode(businessKey);

        if (record == null) {
            return null;
        }

        Map map = new HashMap();

        for (Prop prop : record.getProps().values()) {
            map.put(prop.getCode(), prop.getValue());
        }

        String json = jsonMapper.toJson(map);

        return json;
    }

    // ~ ======================================================================
//    @Resource
//    public void setFormTemplateManager(FormTemplateManager formTemplateManager) {
//        this.formTemplateManager = formTemplateManager;
//    }

    @Resource
    public void setExportor(Exportor exportor) {
        this.exportor = exportor;
    }

    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }

    @Resource
    public void setMultipartResolver(MultipartResolver multipartResolver) {
        this.multipartResolver = multipartResolver;
    }

    @Resource
    public void setTenantHolder(TenantHolder tenantHolder) {
        this.tenantHolder = tenantHolder;
    }

    @Resource
    public void setCurrentUserHolder(CurrentUserHolder currentUserHolder) {
        this.currentUserHolder = currentUserHolder;
    }

    @Resource
    public void setKeyValueConnector(KeyValueConnector keyValueConnector) {
        this.keyValueConnector = keyValueConnector;
    }

    @Resource
    public void setStoreConnector(StoreConnector storeConnector) {
        this.storeConnector = storeConnector;
    }

    @Resource
    public void setFormConnector(FormConnector formConnector) {
        this.formConnector = formConnector;
    }

    @Resource
    public void setUserConnector(UserConnector userConnector) {
        this.userConnector = userConnector;
    }
}
