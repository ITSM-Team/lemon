package com.mossle.humantask.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.codahale.metrics.EWMA;
import com.mongodb.dao.task.TaskInfoMongoDao;
import com.mongodb.dao.task.TaskParticipantMongoDao;
import com.mossle.api.humantask.HumanTaskConstants;
import com.mossle.core.id.IdGenerator;
import com.mossle.humantask.persistence.domain.TaskInfo;
import com.mossle.humantask.persistence.domain.TaskParticipant;
import com.mossle.humantask.rule.ActivityAssigneeRule;
import com.mossle.humantask.rule.AssigneeRule;
import com.mossle.humantask.rule.InitiatorAssigneeRule;
import com.mossle.humantask.rule.PositionAssigneeRule;
import com.mossle.humantask.rule.PrefixRuleMatcher;
import com.mossle.humantask.rule.RuleMatcher;
import com.mossle.humantask.rule.SuperiorAssigneeRule;
import com.mossle.spi.humantask.TaskDefinitionConnector;
import com.mossle.spi.humantask.TaskUserDTO;
import com.mossle.spi.process.InternalProcessConnector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.instrument.classloading.weblogic.WebLogicLoadTimeWeaver;

public class TaskDefUserHumanTaskListener implements HumanTaskListener {
    private static Logger logger = LoggerFactory
            .getLogger(TaskDefUserHumanTaskListener.class);
    private TaskDefinitionConnector taskDefinitionConnector;
    //private TaskParticipantManager taskParticipantManager;
    @Autowired
    private TaskParticipantMongoDao taskParticipantMongoDao;
    private IdGenerator idGenerator;
    @Autowired
    private TaskInfoMongoDao taskInfoMongoDao;
    private InternalProcessConnector internalProcessConnector;

    @Override
    public void onCreate(TaskInfo taskInfo) throws Exception {
        if (HumanTaskConstants.CATALOG_COPY.equals(taskInfo.getCatalog())) {
            return;
        }
        String processInstanceId = taskInfo.getProcessInstanceId();
        String startUserId = internalProcessConnector
                .findInitiator(processInstanceId);
        AssigneeRule assigneeRule=new  InitiatorAssigneeRule();
       String userId =assigneeRule.process(startUserId);
        logger.debug("userId : {}", userId);
        String taskDefinitionKey = taskInfo.getCode();
        String processDefinitionId = taskInfo.getProcessDefinitionId();
        List<TaskUserDTO> taskUsers = taskDefinitionConnector.findTaskUsers(
                taskDefinitionKey, processDefinitionId);
        for (TaskUserDTO taskUser : taskUsers) {
            String catalog = taskUser.getCatalog();
            String type = taskUser.getType();
            String value = taskUser.getValue();

            if ("assignee".equals(catalog)) {
                taskInfo.setAssignee(value);
            } else if ("candidate".equals(catalog)) {
            	 List<String> idStrings=this.processPosition(taskInfo,value);
            	 TaskParticipant taskParticipant =null;
            	 //xuan 修改
            	 for (String string : idStrings) {
            		 taskParticipant=new TaskParticipant();
                     taskParticipant.setCategory(catalog);
                     taskParticipant.setRef(string);
                     taskParticipant.setType(type);
                     taskParticipant.setId(idGenerator.generateId());
                     taskParticipant.setTaskInfo(taskInfo);
                     //taskParticipantManager.save(taskParticipant);            
                     taskParticipantMongoDao.save(taskParticipant);
				}
              


            }
        }
    }
    
    public  List<String> processPosition(TaskInfo taskInfo,String access) throws Exception {
    	 Map<RuleMatcher, AssigneeRule> assigneeRuleMap = new HashMap<RuleMatcher, AssigneeRule>();
    	 PositionAssigneeRule positionAssigneeRule = new PositionAssigneeRule();
    	 assigneeRuleMap.put(new PrefixRuleMatcher("岗位"), positionAssigneeRule);
        String assignee = access;
        logger.debug("assignee : {}", assignee);
        List<String> userIds = new ArrayList<String>();
        if (assignee == null) {
            return userIds;
        }
       
        if (assignee.startsWith("${")) {
            assignee = (String) internalProcessConnector.executeExpression(
                    taskInfo.getTaskId(), assignee);
            userIds.add(assignee);

            return userIds;
        }

        for (Map.Entry<RuleMatcher, AssigneeRule> entry : assigneeRuleMap
                .entrySet()) {
            RuleMatcher ruleMatcher = entry.getKey();

            if (!ruleMatcher.matches(assignee)) {
                continue;
            }

            String value = ruleMatcher.getValue(assignee);
            AssigneeRule assigneeRule = entry.getValue();
            logger.debug("value : {}", value);
            logger.debug("assigneeRule : {}", assigneeRule);
            if (assigneeRule instanceof PositionAssigneeRule) {
                String processInstanceId = taskInfo.getProcessInstanceId();
                String startUserId = internalProcessConnector
                        .findInitiator(processInstanceId);
                userIds = assigneeRule.process(value, startUserId);
                logger.debug("userIds : {}", userIds);
                return userIds;
            }
        }
		return userIds;
    }

    @Override
    public void onComplete(TaskInfo taskInfo) throws Exception {
    }

    @Resource
    public void setTaskDefinitionConnector(
            TaskDefinitionConnector taskDefinitionConnector) {
        this.taskDefinitionConnector = taskDefinitionConnector;
    }

    @Resource
	public void setIdGenerator(IdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}
    @Resource
    public void setInternalProcessConnector(
            InternalProcessConnector internalProcessConnector) {
        this.internalProcessConnector = internalProcessConnector;
    }
    
    
    

//    @Resource
//    public void setTaskParticipantManager(
//            TaskParticipantManager taskParticipantManager) {
//        this.taskParticipantManager = taskParticipantManager;
//    }
}
