package com.mossle.keyvalue.support;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.xml.bind.annotation.W3CDomHandler;

import com.mongodb.WriteResult;
import com.mongodb.dao.form.PropMongodbDao;
import com.mongodb.dao.form.RecordMongodbDao;
import com.mossle.api.keyvalue.KeyValueConnector;
import com.mossle.api.keyvalue.Prop;
import com.mossle.api.keyvalue.Record;
import com.mossle.core.id.IdGenerator;
import com.mossle.core.page.Page;
import com.mossle.core.query.PropertyFilter;
import com.mysql.jdbc.UpdatableResultSet;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class DatabaseKeyValueConnector implements KeyValueConnector {
    private static Logger logger = LoggerFactory
            .getLogger(DatabaseKeyValueConnector.class);
    public static final int STATUS_DRAFT_PROCESS = 0;
    private JdbcTemplate jdbcTemplate;
    private IdGenerator idGenerator;
    @Autowired
    private RecordMongodbDao recordMongodbDao;
    @Autowired
    private PropMongodbDao propMongodbDao;
    

    /**
     * xuan（新） 根据code获得记录.
     */
    public Record findByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        Record record = null;

        try {
        	
/*            Map<String, Object> map = jdbcTemplate.queryForMap(
                    "select * from KV_RECORD where id=?", code);
            record = convertRecord(map);*/
            record=recordMongodbDao.findById(Long.valueOf(code));
            record = convertRecord(record);
        } catch (EmptyResultDataAccessException ex) {
            logger.info("cannot find record by code : {}", code);

            return null;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        return record;
    }

    /**
     * xuan（新）  根据ref获得记录.
     */
    public Record findByRef(String ref) {
        if (StringUtils.isBlank(ref)) {
            return null;
        }

        Record record = null;

        try {
/*            Map<String, Object> map = jdbcTemplate.queryForMap(
                    "select * from KV_RECORD where ref=?", ref);
            record = convertRecord(map);*/
        	//xuan 根据ref查询
            record=recordMongodbDao.findOne(Query.query(Criteria.where("ref").is(ref)));
            record = convertRecord(record);
        } catch (EmptyResultDataAccessException ex) {
            logger.info("cannot find record by ref : {}", ref);

            return null;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        return record;
    }

    /**
     *xuan（新） 根据businessKey获得记录.
     */
    public Record findByBusinessKey(String businessKey) {
        if (StringUtils.isBlank(businessKey)) {
            return null;
        }

        Record record = null;

        try {
/*            Map<String, Object> map = jdbcTemplate
                    .queryForMap(
                            "select * from KV_RECORD where BUSINESS_KEY=?",
                            businessKey);
            record = this.convertRecord(map);*/
            record=recordMongodbDao.findOne(Query.query(Criteria.where("BUSINESS_KEY").is(businessKey)));
            record = this.convertRecord(record);
        } catch (EmptyResultDataAccessException ex) {
            logger.info("cannot find record by businessKey : {}", businessKey);

            return null;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        return record;
    }

    /**
     * 如果code为null，就执行insert，否则执行update.
     */
    public void save(Record record) {
        if (record.getCode() == null) {
            insert(record);
        } else {
            update(record);
        }
    }

    /**
     *xuan（新） 根据code删除记录.
     */
    public void removeByCode(String code) {    	
/*        jdbcTemplate.update("DELETE FROM KV_PROP WHERE RECORD_ID=?", code);
        jdbcTemplate.update("DELETE FROM KV_RECORD WHERE ID=?", code);*/
        
        propMongodbDao.remove(Query.query(Criteria.where("recordId").is(code))); 	
        recordMongodbDao.remove(Query.query(Criteria.where("_id").is(code)));
    }

    /**
     *xuan（新） 根据businessKey删除记录.
     */
    public void removeByBusinessKey(String businessKey) {
/*        Long id = jdbcTemplate.queryForObject(
                "SELECT ID FROM KV_RECORD WHERE BUSINESS_KEY=?", Long.class,
                businessKey);
        jdbcTemplate.update("DELETE FROM KV_PROP WHERE RECORD_ID=?", id);
        jdbcTemplate.update("DELETE FROM KV_RECORD WHERE ID=?", id);*/
        
        Record record=recordMongodbDao.findOne(Query.query(Criteria.where("businessKey").is(businessKey)));
        Long id=record.getId();
        propMongodbDao.remove(Query.query(Criteria.where("recordId").is(id))); 	
        recordMongodbDao.remove(Query.query(Criteria.where("_id").is(id)));
    }

    /**
     *xuan（新） 根据status查询记录.
     */
    public List<Record> findByStatus(int status, String userId, String tenantId) {
/*        List<Map<String, Object>> list = jdbcTemplate
                .queryForList(
                        "SELECT * FROM KV_RECORD WHERE STATUS=? AND USER_ID=? AND TENANT_ID=?",
                        status, userId, tenantId);
        

        for (Map<String, Object> map : list) {
            Record record = convertRecord(map);
            records.add(record);
        }*/
       List<Record> records = new ArrayList<Record>();
        records=recordMongodbDao.find(Query.query(Criteria.where("status").is(status)
        		.and("userId").is(userId)
        		.and("tenantId").is(tenantId)));
        for(Record record:records){
        	record= convertRecord(record);
        }
        return records;
    }
    /**
     *xuan（新） 分页.
     */
    public Page pagedQuery(Page page, int status, String userId, String tenantId) {
/*        long totalCount = jdbcTemplate
                .queryForObject(
                        "select count(*) from KV_RECORD WHERE STATUS=? AND USER_ID=? AND TENANT_ID=?",
                        Long.class, status, userId, tenantId);
        List<Map<String, Object>> list = jdbcTemplate
                .queryForList(
                        "SELECT * FROM KV_RECORD WHERE STATUS=? AND USER_ID=? AND TENANT_ID=? limit ?,?",
                        status, userId, tenantId, page.getStart(),
                        page.getPageSize());
        

        for (Map<String, Object> map : list) {
            Record record = convertRecord(map);
            records.add(record);
        }*/
        List<Record> records = new ArrayList<Record>();
        long totalCount=recordMongodbDao.count(Query.query(Criteria.where("status").is(status)
        		.and("userId").is(userId)
        		.and("tenantId").is(tenantId)));
        records=recordMongodbDao.find(Query.query(Criteria.where("status").is(status)
        		.and("userId").is(userId)
        		.and("tenantId").is(tenantId))
        		.skip((int)page.getStart())  //从什么地方开始
        		.limit((int)page.getPageSize()));//查询多少条
        for(Record record:records){
        	record= convertRecord(record);
        }
        page.setTotalCount(totalCount);
        page.setResult(records);

        return page;
    }

    // ~ ======================================================================
    /**
     * 把map转换成Record.
     */
    @Deprecated
    public Record convertRecord(Map<String, Object> recordMap) {
        Record record = new Record();
        record.setCode(getStringValue(recordMap, "id"));
        record.setBusinessKey(getStringValue(recordMap, "BUSINESS_KEY"));
        record.setName(getStringValue(recordMap, "name"));
        record.setFormTemplateCode(getStringValue(recordMap,
                "form_template_code"));
        record.setCategory(getStringValue(recordMap, "category"));
        record.setStatus(getIntValue(recordMap, "status"));
        record.setRef(getStringValue(recordMap, "ref"));
        record.setCreateTime(getDateValue(recordMap, "create_time"));
        record.setUserId(getStringValue(recordMap, "user_id"));
        record.setTenantId(getStringValue(recordMap, "tenant_id"));

        List<Map<String, Object>> list = jdbcTemplate.queryForList(
                "select * from KV_PROP where record_id=?", record.getCode());

        for (Map<String, Object> propMap : list) {
            Prop prop = new Prop();
            prop.setCode(getStringValue(propMap, "code"));
            prop.setType(getIntValue(propMap, "type"));
            prop.setValue(getStringValue(propMap, "value"));
            record.getProps().put(prop.getCode(), prop);
        }

        return record;
    }
    /**
     * xuan（新） 重写 convertRecord方法
     * 将props添加到Record中
     * */
    public Record convertRecord(Record record){
     if(record == null){ 
    	 return null;
    	 }
    	record.setCode(String.valueOf(record.getId()));
       List<Prop> list=propMongodbDao.find(Query.query(Criteria.where("recordId").is(Long.valueOf(record.getCode()))));
       for(Prop prop:list){
    	   record.getProps().put(prop.getCode(), prop);
       }
        return record;
		}

    /**
     * 获得string值.
     */
    public String getStringValue(Map<String, Object> map, String name) {
        Object value = map.get(name);

        if (value == null) {
            return null;
        }

        if (value instanceof String) {
            return (String) value;
        }

        return value.toString();
    }

    /**
     * 获得int值.
     */
    public Integer getIntValue(Map<String, Object> map, String name) {
        Object value = map.get(name);

        if (value == null) {
            return null;
        }

        if (value instanceof Integer) {
            return (Integer) value;
        }

        return Integer.parseInt(value.toString());
    }

    /**
     * 获得date值.
     */
    public Date getDateValue(Map<String, Object> map, String name) {
        Object value = map.get(name);

        if (value == null) {
            return null;
        }

        if (value instanceof Date) {
            return (Date) value;
        }

        return null;
    }

    /**
     *xuan（新） 新建一条数据.
     */
    public void insert(Record record) {
/*        String sqlRecordInsert = "insert into KV_RECORD(id,business_key,name,form_template_code,category,status,ref,create_time,user_id,tenant_id)"
                + " values(?,?,?,?,?,?,?,?,?,?)";
        String businessKey = record.getBusinessKey();
        String name = record.getName();
        String formTemplateCode = record.getFormTemplateCode();
        Date createTime = record.getCreateTime();
        String userId = record.getUserId();
        String tenantId = record.getTenantId();*/
        
        Long id = record.getId();
        String originalRef = record.getRef();
        String ref = originalRef;
        if (originalRef == null) {
            ref = UUID.randomUUID().toString();
        }
        record.setRef(ref);
        if(id ==null || "0".equals(String.valueOf(id))){
        	record.setId(idGenerator.generateId());
        }
        recordMongodbDao.save(record);
        
/*        jdbcTemplate.update(sqlRecordInsert, id, businessKey, name,
                formTemplateCode, record.getCategory(), record.getStatus(),
                ref, createTime, userId, tenantId);*/

        Record resultRecord = this.findByRef(ref);
        String code = resultRecord.getCode();

        if (originalRef == null) {
/*            String sqlRecordUpdate = "update KV_RECORD set ref=null where id=?";
            jdbcTemplate.update(sqlRecordUpdate, code);*/
            // xuan
            recordMongodbDao.update(Query.query(Criteria.where("_id").is(Long.valueOf(code))),Update.update("ref",null));
        }

        record.setCode(resultRecord.getCode());

        /*String sqlProp = "insert into KV_PROP(id,code,type,value,record_id) values(?,?,?,?,?)";*/

        for (Prop prop : record.getProps().values()) {
/*            jdbcTemplate.update(sqlProp, idGenerator.generateId(),
                    prop.getCode(), prop.getType(), prop.getValue(),
                    record.getCode());*/
            
        	//xuan
        	prop.setId(idGenerator.generateId());
        	prop.setRecordId(Long.valueOf(record.getCode()));
        	propMongodbDao.insert(prop);
        }
    }

    /**
     *xuan（新）  更新一条数据.
     */
    public void update(Record record) {
/*        String sqlRecord = "update KV_RECORD set business_key=?,name=?,form_template_code=?,category=?,status=?,ref=? where id=?";
        jdbcTemplate.update(sqlRecord, record.getBusinessKey(),
                record.getName(), record.getFormTemplateCode(),
                record.getCategory(), record.getStatus(), record.getRef(),
                record.getCode());
        Record resultRecord = findByCode(record.getCode());
       String sqlPropInsert = "insert into KV_PROP(id,code,type,value,record_id) values(?,?,?,?,?)";
        String sqlPropUpdate = "update KV_PROP set type=?,value=? where code=? and record_id=?";

        for (Prop prop : record.getProps().values()) {
            // only append, won't delete
            if (resultRecord.getProps().containsKey(prop.getCode())) {
                jdbcTemplate.update(sqlPropUpdate, prop.getType(),
                        prop.getValue(), prop.getCode(), record.getCode());
            } else {
                jdbcTemplate.update(sqlPropInsert, idGenerator.generateId(),
                        prop.getCode(), prop.getType(), prop.getValue(),
                        record.getCode());
            }
        }
        */
        recordMongodbDao.update(Query.query(Criteria.where("_id").is(Long.valueOf(record.getCode()))),Update.update("businessKey",record.getBusinessKey())
        		.set("name",record.getName())
        		.set("formTemplateCode", record.getFormTemplateCode())
        		.set("category", record.getCategory())
        		.set("status", record.getStatus())
        		.set("ref", record.getRef()));
        Record resultRecord = findByCode(record.getCode());
        for (Prop prop : record.getProps().values()) {
            // only append, won't delete
            if (resultRecord.getProps().containsKey(prop.getCode())) {
            	WriteResult  w=propMongodbDao.update(Query.query(Criteria.where("recordId")
            			.is(Long.valueOf(record.getCode())).and("code")
            			.is(prop.getCode())),Update.update("type", prop.getType())
            			.set("value", prop.getValue()));
            } else {
                prop.setId(idGenerator.generateId());
                prop.setRecordId(Long.valueOf(record.getCode()));
                propMongodbDao.save(prop);
            }
        }
    }

    public long findTotalCount(String category, String tenantId, String q) {
        List<PropertyFilter> propertyFilters = new ArrayList<PropertyFilter>();

        if (StringUtils.isNotBlank(q)) {
            for (String text : q.split("\\|")) {
                String name = text.split("=")[0];
                String value = text.split("=")[1];
                propertyFilters.add(new PropertyFilter("LIKES_" + name, value));
            }
        }

        return this.findTotalCount(category, tenantId, propertyFilters);
    }

    public List<Map<String, Object>> findResult(Page page, String category,
            String tenantId, Map<String, String> headers, String q) {
        List<PropertyFilter> propertyFilters = new ArrayList<PropertyFilter>();

        if (StringUtils.isNotBlank(q)) {
            for (String text : q.split("\\|")) {
                String name = text.split("=")[0];
                String value = text.split("=")[1];
                propertyFilters.add(new PropertyFilter("LIKES_" + name, value));
            }
        }

        return this.findResult(page, category, tenantId, headers,
                propertyFilters);
    }

    public long findTotalCount(String category, String tenantId,
            List<PropertyFilter> propertyFilters) {
        String sqlPrefix = null;
        List<Object> params = new ArrayList<Object>();

        if (propertyFilters.isEmpty()) {
            sqlPrefix = "select count(*) from KV_RECORD r where r.CATEGORY=? and r.TENANT_ID=?";
        } else {
            sqlPrefix = "select count(distinct r.ID) from KV_RECORD r";

            int index = 0;

            for (PropertyFilter propertyFilter : propertyFilters) {
                String propName = "p" + (index++);
                sqlPrefix += (" join KV_PROP " + propName + " on r.ID="
                        + propName + ".RECORD_ID and " + propName
                        + ".CODE=? and " + propName + ".VALUE like ?");
                params.add(propertyFilter.getPropertyName());
                params.add("%" + propertyFilter.getMatchValue() + "%");
            }

            sqlPrefix += " where r.CATEGORY=? and r.TENANT_ID=?";
        }

        params.add(category);
        params.add(tenantId);

        long totalCount = jdbcTemplate.queryForObject(sqlPrefix, Long.class,
                params.toArray(new Object[0]));

        return totalCount;
    }

    public List<Map<String, Object>> findResult(Page page, String category,
            String tenantId, Map<String, String> headers,
            List<PropertyFilter> propertyFilters) {
        String sqlPrefix = null;
        List<Object> params = new ArrayList<Object>();
        Map<String, String> usedFieldMap = new HashMap<String, String>();

        if (propertyFilters.isEmpty()) {
            sqlPrefix = "select r.ID from KV_RECORD r";
        } else {
            sqlPrefix = "select r.ID from KV_RECORD r";

            int index = 0;

            for (PropertyFilter propertyFilter : propertyFilters) {
                String propName = "p" + index;
                sqlPrefix += (" join KV_PROP " + propName + " on r.ID="
                        + propName + ".RECORD_ID and " + propName
                        + ".CODE=? and " + propName + ".VALUE like ?");
                params.add(propertyFilter.getPropertyName());
                params.add("%" + propertyFilter.getMatchValue() + "%");
                usedFieldMap.put(propertyFilter.getPropertyName(), propName);
                index++;
            }
        }

        String sqlOrder = null;

        if (page.isOrderEnabled()) {
            String orderBy = page.getOrderBy();
            String order = page.getOrder();

            if (usedFieldMap.containsKey(orderBy)) {
                String propName = usedFieldMap.get(orderBy);
                sqlOrder = " order by " + propName + ".VALUE " + order;
            } else {
                String propName = "p";
                sqlPrefix += (" join KV_PROP " + propName + " on r.ID="
                        + propName + ".RECORD_ID and " + propName + ".CODE='"
                        + orderBy + "'");
                sqlOrder = " order by " + propName + ".VALUE " + order;
            }
        }

        sqlPrefix += " where r.CATEGORY=? and r.TENANT_ID=?";
        params.add(category);
        params.add(tenantId);

        if (sqlOrder != null) {
            sqlPrefix += sqlOrder;
        }

        String sql = sqlPrefix + " limit " + page.getStart() + ","
                + page.getPageSize();
        logger.debug("sql : {}", sql);

        List<Map<String, Object>> records = jdbcTemplate.queryForList(sql,
                params.toArray(new Object[0]));
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        for (Map<String, Object> record : records) {
            Map<String, Object> map = new HashMap<String, Object>();
            list.add(map);

            Long recordId = (Long) record.get("id");
            List<Map<String, Object>> props = jdbcTemplate.queryForList(
                    "select * from KV_PROP where RECORD_ID=?", recordId);

            for (Map<String, Object> prop : props) {
                if (headers.containsKey(prop.get("code"))) {
                    map.put((String) prop.get("code"), prop.get("value"));
                }
            }
        }

        return list;
    }

    /**
     *xuan（新） 复制数据.
     */
    public Record copyRecord(Record original, List<String> fields) {
        Record record = new Record();
        //
        record.setBusinessKey(original.getBusinessKey());
        record.setName(original.getName());
        record.setFormTemplateCode(original.getFormTemplateCode());
        // bpmProcessId
        record.setCategory(original.getCategory());
        record.setStatus(STATUS_DRAFT_PROCESS);
        // processInstanceId
        record.setRef(null);
        record.setCreateTime(new Date());
        record.setUserId(original.getUserId());
        record.setTenantId(original.getTenantId());

/*        List<Map<String, Object>> list = jdbcTemplate.queryForList(
                "select * from KV_PROP where record_id=?", original.getCode());
        
        for (Map<String, Object> propMap : list) {
            logger.debug("prop map : {}", propMap);

            String code = getStringValue(propMap, "code");

            if (!fields.contains(code)) {
                continue;
            }

            Prop prop = new Prop();
            prop.setCode(code);
            prop.setType(getIntValue(propMap, "type"));
            prop.setValue(getStringValue(propMap, "value"));
            record.getProps().put(prop.getCode(), prop);
        }*/
        List<Prop> propList=propMongodbDao.find(Query.query(Criteria.where("recordId").is(original.getCode())));
        for (Prop prop : propList) {
            if (!fields.contains(prop.getCode())) {
                continue;
            }
            record.getProps().put(prop.getCode(), prop);
		}
        this.insert(record);

        return record;
    }

    @Resource
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Resource
    public void setIdGenerator(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }
}