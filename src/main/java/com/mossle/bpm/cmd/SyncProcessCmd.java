package com.mossle.bpm.cmd;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.mongodb.dao.bpm.BpmConfFormMongoDao;
import com.mossle.bpm.graph.Graph;
import com.mossle.bpm.graph.Node;
import com.mossle.bpm.persistence.domain.BpmConfBase;
import com.mossle.bpm.persistence.domain.BpmConfCountersign;
import com.mossle.bpm.persistence.domain.BpmConfForm;
import com.mossle.bpm.persistence.domain.BpmConfListener;
import com.mossle.bpm.persistence.domain.BpmConfNode;
import com.mossle.bpm.persistence.domain.BpmConfUser;
import com.mossle.bpm.persistence.manager.BpmConfBaseManager;
import com.mossle.bpm.persistence.manager.BpmConfCountersignManager;
import com.mossle.bpm.persistence.manager.BpmConfFormManager;
import com.mossle.bpm.persistence.manager.BpmConfListenerManager;
import com.mossle.bpm.persistence.manager.BpmConfNodeManager;
import com.mossle.bpm.persistence.manager.BpmConfUserManager;
import com.mossle.bpm.support.TaskDefinitionBuilder;
import com.mossle.core.id.IdGenerator;
import com.mossle.core.spring.ApplicationContextHelper;
import com.mossle.spi.humantask.TaskDefinitionConnector;
import com.mossle.spi.humantask.TaskDefinitionDTO;
import org.activiti.bpmn.model.ActivitiListener;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.impl.cmd.GetBpmnModelCmd;
import org.activiti.engine.impl.cmd.GetDeploymentProcessDefinitionCmd;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * 把xml解析的内存模型保存到数据库里.
 */
public class SyncProcessCmd implements Command<Void> {
    /** 流程定义id. */
    private String processDefinitionId;
    

    /**
     * 构造方法.
     */
    public SyncProcessCmd(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public Void execute(CommandContext commandContext) {
        ProcessDefinitionEntity processDefinitionEntity = new GetDeploymentProcessDefinitionCmd(
                processDefinitionId).execute(commandContext);
        String processDefinitionKey = processDefinitionEntity.getKey();
        int processDefinitionVersion = processDefinitionEntity.getVersion();
        BpmConfBaseManager bpmConfBaseManager = getBpmConfBaseManager();
        BpmConfBase bpmConfBase = bpmConfBaseManager
                .findUnique(
                        "from BpmConfBase where processDefinitionKey=? and processDefinitionVersion=?",
                        processDefinitionKey, processDefinitionVersion);

//        BpmConfBaseMongoService  BpmConfFormMongoDao=getBpmConfBaseMongoService();
//        
//        Query query=new Query();
//        query.addCriteria(Criteria.where("processDefinitionKey").is(processDefinitionKey)
//        		.and("processDefinitionVersion").is(processDefinitionVersion));
//        BpmConfBase bpmConfBase=BpmConfFormMongoDao.findOne(query);
//        
//        IdGenerator idGenerator= getIdGenerator();
//        long id=idGenerator.generateId();
        if (bpmConfBase == null) {
            bpmConfBase = new BpmConfBase();
          //  bpmConfBase.setId(id);
            bpmConfBase.setProcessDefinitionId(processDefinitionId);
            bpmConfBase.setProcessDefinitionKey(processDefinitionKey);
            bpmConfBase.setProcessDefinitionVersion(processDefinitionVersion);
           bpmConfBaseManager.save(bpmConfBase);
           // BpmConfFormMongoDao.save(bpmConfBase);
        } else if (bpmConfBase.getProcessDefinitionId() == null) {
//        	if(String.valueOf(bpmConfBase.getId()).isEmpty()){
//        		bpmConfBase.setId(id);
//        	}
            bpmConfBase.setProcessDefinitionId(processDefinitionId);
            // BpmConfFormMongoDao.save(bpmConfBase);
           bpmConfBaseManager.save(bpmConfBase);
        }

        BpmnModel bpmnModel = new GetBpmnModelCmd(processDefinitionId)
                .execute(commandContext);
        Graph graph = new FindGraphCmd(processDefinitionId)
                .execute(commandContext);
        this.processGlobal(bpmnModel, 1, bpmConfBase);

        int priority = 2;

        for (Node node : graph.getNodes()) {
            if ("exclusiveGateway".equals(node.getType())) {
                continue;
            } else if ("userTask".equals(node.getType())) {
                this.processUserTask(node, bpmnModel, priority++, bpmConfBase);
            } else if ("startEvent".equals(node.getType())) {
                this.processStartEvent(node, bpmnModel, priority++, bpmConfBase);
            } else if ("endEvent".equals(node.getType())) {
                this.processEndEvent(node, bpmnModel, priority++, bpmConfBase);
            }
        }

        return null;
    }

    /**
     * 全局配置.
     */
    public void processGlobal(BpmnModel bpmnModel, int priority,
            BpmConfBase bpmConfBase) {
        Process process = bpmnModel.getMainProcess();
        BpmConfNodeManager bpmConfNodeManager = getBpmConfNodeManager();
        BpmConfNode bpmConfNode = bpmConfNodeManager.findUnique(
                "from BpmConfNode where code=? and bpmConfBase=?",
                process.getId(), bpmConfBase);

//        BpmConfNodeMongoService bpmConfNodeMongoService=getBpmConfNodeMongoService();
//        Query query=new Query();
//        query.addCriteria(Criteria.where("code").is(process.getId()).and("bpmConfBase").is(bpmConfBase));
//        BpmConfNode bpmConfNode =bpmConfNodeMongoService.findOne(query);
//        long id=getIdGenerator().generateId();
        if (bpmConfNode == null) {
            bpmConfNode = new BpmConfNode();
            //bpmConfNode.setId(id);
            bpmConfNode.setCode(process.getId());
            bpmConfNode.setName("全局");
            bpmConfNode.setType("process");
            bpmConfNode.setConfUser(2);
            bpmConfNode.setConfListener(0);
            bpmConfNode.setConfRule(2);
            bpmConfNode.setConfForm(0);
            bpmConfNode.setConfOperation(2);
            bpmConfNode.setConfNotice(2);
            bpmConfNode.setPriority(priority);
            bpmConfNode.setBpmConfBase(bpmConfBase);
           bpmConfNodeManager.save(bpmConfNode);
            
        //  bpmConfNodeMongoService.save(bpmConfNode);
        }

        // 配置监听器
        processListener(process.getExecutionListeners(), bpmConfNode);
    }

    /**
     * 配置用户任务.
     */
    public void processUserTask(Node node, BpmnModel bpmnModel, int priority,
            BpmConfBase bpmConfBase) {
        BpmConfNodeManager bpmConfNodeManager = getBpmConfNodeManager();
        BpmConfNode bpmConfNode = bpmConfNodeManager.findUnique(
                "from BpmConfNode where code=? and bpmConfBase=?",
                node.getId(), bpmConfBase);

//        BpmConfNodeMongoService bpmConfNodeMongoService=getBpmConfNodeMongoService();
//        Query query=new Query();
//        query.addCriteria(Criteria.where("code").is(node.getId()).and("bpmConfBase").is(bpmConfBase));
//        BpmConfNode bpmConfNode =bpmConfNodeMongoService.findOne(query);
//        long id=getIdGenerator().generateId();
        if (bpmConfNode == null) {
            bpmConfNode = new BpmConfNode();
            //bpmConfNode.setId(id);
            bpmConfNode.setCode(node.getId());
            bpmConfNode.setName(node.getName());
            bpmConfNode.setType(node.getType());
            bpmConfNode.setConfUser(0);
            bpmConfNode.setConfListener(0);
            bpmConfNode.setConfRule(0);
            bpmConfNode.setConfForm(0);
            bpmConfNode.setConfOperation(0);
            bpmConfNode.setConfNotice(0);
            bpmConfNode.setPriority(priority);
            bpmConfNode.setBpmConfBase(bpmConfBase);
          bpmConfNodeManager.save(bpmConfNode);
            
          // bpmConfNodeMongoService.save(bpmConfNode);
        }

        // 配置参与者
        UserTask userTask = (UserTask) bpmnModel.getFlowElement(node.getId());
        int index = 1;
        index = this.processUserTaskConf(bpmConfNode, userTask.getAssignee(),
                0, index);

        for (String candidateUser : userTask.getCandidateUsers()) {
            index = this.processUserTaskConf(bpmConfNode, candidateUser, 1,
                    index);
        }

        for (String candidateGroup : userTask.getCandidateGroups()) {
            this.processUserTaskConf(bpmConfNode, candidateGroup, 2, index);
        }

        // 配置监听器
        this.processListener(userTask.getExecutionListeners(), bpmConfNode);
        this.processListener(userTask.getTaskListeners(), bpmConfNode);
        // 配置表单
        this.processForm(userTask, bpmConfNode);

        // 会签
        if (userTask.getLoopCharacteristics() != null) {
        	//long bpmConfCountersignid=getIdGenerator().generateId();
            BpmConfCountersign bpmConfCountersign = new BpmConfCountersign();
            //bpmConfCountersign.setId(bpmConfCountersignid);
            bpmConfCountersign.setType(0);
            bpmConfCountersign.setRate(100);
            bpmConfCountersign.setBpmConfNode(bpmConfNode);
            bpmConfCountersign.setSequential(userTask.getLoopCharacteristics()
                    .isSequential() ? 1 : 0);
           // getBpmConfCountersignMongoService().save(bpmConfCountersign);
            getBpmConfCountersignManager().save(bpmConfCountersign);
        }

        // 更新TaskDefinition
        TaskDefinitionConnector taskDefinitionConnector = this
                .getTaskDefinitionConnector();
        TaskDefinitionDTO taskDefinitionDto = new TaskDefinitionBuilder()
                .setUserTask(userTask)
                .setProcessDefinitionId(bpmConfBase.getProcessDefinitionId())
                .build();
        taskDefinitionConnector.create(taskDefinitionDto);
    }

    /**
     * 配置参与者.
     */
    public int processUserTaskConf(BpmConfNode bpmConfNode, String value,
            int type, int priority) {
        if (value == null) {
            return priority;
        }

        BpmConfUserManager bpmConfUserManager = getBpmConfUserManager();
        BpmConfUser bpmConfUser = bpmConfUserManager
                .findUnique(
                        "from BpmConfUser where value=? and type=? and priority=? and bpmConfNode=?",
                        value, type, priority, bpmConfNode);

//        BpmConfUserMongoService bpmConfUserMongoService=getBpmConfUserMongoService();
//        Query query=new Query();
//        query.addCriteria(Criteria.where("value").is(value)
//        		.and("type").is(type)
//        		.and("priority").is(priority)
//        		.and("bpmConfNode").is(bpmConfNode));
//        BpmConfUser bpmConfUser = bpmConfUserMongoService.findOne(query);
//        long id=getIdGenerator().generateId();
        if (bpmConfUser == null) {
            bpmConfUser = new BpmConfUser();
        	//bpmConfUser.setId(id);
            bpmConfUser.setValue(value);
            bpmConfUser.setType(type);
            bpmConfUser.setStatus(0);
            bpmConfUser.setPriority(priority);
            bpmConfUser.setBpmConfNode(bpmConfNode);
           bpmConfUserManager.save(bpmConfUser);
            
           // bpmConfUserMongoService.save(bpmConfUser);
        }

        return priority + 1;
    }

    /**
     * 配置开始事件.
     */
    public void processStartEvent(Node node, BpmnModel bpmnModel, int priority,
            BpmConfBase bpmConfBase) {
       BpmConfNodeManager bpmConfNodeManager = getBpmConfNodeManager();
        BpmConfNode bpmConfNode = bpmConfNodeManager.findUnique(
                "from BpmConfNode where code=? and bpmConfBase=?",
                node.getId(), bpmConfBase);
        
//        BpmConfNodeMongoService bpmConfNodeMongoService=getBpmConfNodeMongoService();
//        Query query=new Query();
//        query.addCriteria(Criteria.where("code").is(node.getId()).and("bpmConfBase").is(bpmConfBase));
//        BpmConfNode bpmConfNode =bpmConfNodeMongoService.findOne(query);
//        long id=getIdGenerator().generateId();
        if (bpmConfNode == null) {
            bpmConfNode = new BpmConfNode();
          //  bpmConfNode.setId(id);
            bpmConfNode.setCode(node.getId());
            bpmConfNode.setName(node.getName());
            bpmConfNode.setType(node.getType());
            bpmConfNode.setConfUser(2);
            bpmConfNode.setConfListener(0);
            bpmConfNode.setConfRule(2);
            bpmConfNode.setConfForm(0);
            bpmConfNode.setConfOperation(2);
            bpmConfNode.setConfNotice(0);
            bpmConfNode.setPriority(priority);
            bpmConfNode.setBpmConfBase(bpmConfBase);
           bpmConfNodeManager.save(bpmConfNode);
            
           //bpmConfNodeMongoService.save(bpmConfNode);
        }

        FlowElement flowElement = bpmnModel.getFlowElement(node.getId());
        // 配置监听器
        this.processListener(flowElement.getExecutionListeners(), bpmConfNode);

        StartEvent startEvent = (StartEvent) flowElement;
        // 配置表单
        this.processForm(startEvent, bpmConfNode);
    }

    /**
     * 配置结束事件.
     */
    public void processEndEvent(Node node, BpmnModel bpmnModel, int priority,
            BpmConfBase bpmConfBase) {
        BpmConfNodeManager bpmConfNodeManager = getBpmConfNodeManager();
        BpmConfNode bpmConfNode = bpmConfNodeManager.findUnique(
                "from BpmConfNode where code=? and bpmConfBase=?",
                node.getId(), bpmConfBase);
        
//        BpmConfNodeMongoService bpmConfNodeMongoService=getBpmConfNodeMongoService();
//        Query query=new Query();
//        query.addCriteria(Criteria.where("code").is(node.getId()).and("bpmConfBase").is(bpmConfBase));
//        BpmConfNode bpmConfNode =bpmConfNodeMongoService.findOne(query);
//        long id=getIdGenerator().generateId();
        if (bpmConfNode == null) {
            bpmConfNode = new BpmConfNode();
           // bpmConfNode.setId(id);
            bpmConfNode.setCode(node.getId());
            bpmConfNode.setName(node.getName());
            bpmConfNode.setType(node.getType());
            bpmConfNode.setConfUser(2);
            bpmConfNode.setConfListener(0);
            bpmConfNode.setConfRule(2);
            bpmConfNode.setConfForm(2);
            bpmConfNode.setConfOperation(2);
            bpmConfNode.setConfNotice(0);
            bpmConfNode.setPriority(priority);
            bpmConfNode.setBpmConfBase(bpmConfBase);
           bpmConfNodeManager.save(bpmConfNode);
            
        //  bpmConfNodeMongoService.save(bpmConfNode);
        }

        FlowElement flowElement = bpmnModel.getFlowElement(node.getId());
        // 配置监听器
        this.processListener(flowElement.getExecutionListeners(), bpmConfNode);
    }

    /**
     * 配置监听器.
     */
    public void processListener(List<ActivitiListener> activitiListeners,
            BpmConfNode bpmConfNode) {
        Map<String, Integer> eventTypeMap = new HashMap<String, Integer>();
        eventTypeMap.put("start", 0);
        eventTypeMap.put("end", 1);
        eventTypeMap.put("take", 2);
        eventTypeMap.put("create", 3);
        eventTypeMap.put("assignment", 4);
        eventTypeMap.put("complete", 5);
        eventTypeMap.put("delete", 6);

        BpmConfListenerManager bpmConfListenerManager = getBpmConfListenerManager();

        //  BpmConfListenerMongoService bpmConfListenerMongoService=getBpmConfListenerMongoService();
        for (ActivitiListener activitiListener : activitiListeners) {
            String value = activitiListener.getImplementation();
            int type = eventTypeMap.get(activitiListener.getEvent());
            BpmConfListener bpmConfListener = bpmConfListenerManager
                    .findUnique(
                            "from BpmConfListener where value=? and type=? and status=0 and bpmConfNode=?",
                            value, type, bpmConfNode);

           
//            Query query=new Query();
//            query.addCriteria(Criteria.where("value").is(value)
//            		.and("type").is(type)
//            		.and("status").is(0)
//            		.and("bpmConfNode").is(bpmConfNode));
//            BpmConfListener bpmConfListener = bpmConfListenerMongoService.findOne(query);
//            long id=getIdGenerator().generateId();
            if (bpmConfListener == null) {
                bpmConfListener = new BpmConfListener();
              //  bpmConfListener.setId(id);
                bpmConfListener.setValue(value);
                bpmConfListener.setType(type);
                bpmConfListener.setBpmConfNode(bpmConfNode);
             bpmConfListenerManager.save(bpmConfListener);
                
             //   bpmConfListenerMongoService.save(bpmConfListener);
            }
        }
    }

    /**
     * 配置表单，userTask.
     */
    public void processForm(UserTask userTask, BpmConfNode bpmConfNode) {
        if (userTask.getFormKey() == null) {
            return;
        }

        BpmConfFormManager bpmConfFormManager = getBpmConfFormManager();
        BpmConfForm bpmConfForm = bpmConfFormManager.findUnique(
                "from BpmConfForm where bpmConfNode=?", bpmConfNode);

//        BpmConfFormMongoDao BpmConfFormMongoDao=getBpmConfFormMongoDao();
//        Query query=new Query();
//        query.addCriteria(Criteria.where("bpmConfNode").is(bpmConfNode));
//        BpmConfForm bpmConfForm =BpmConfFormMongoDao.findOne(query);
//        long id=getIdGenerator().generateId();
        if (bpmConfForm == null) {
            bpmConfForm = new BpmConfForm();
           // bpmConfForm.setId(id);
            bpmConfForm.setValue(userTask.getFormKey());
            bpmConfForm.setType(0);
            bpmConfForm.setOriginValue(userTask.getFormKey());
            bpmConfForm.setOriginType(0);
            bpmConfForm.setStatus(0);
            bpmConfForm.setBpmConfNode(bpmConfNode);
           bpmConfFormManager.save(bpmConfForm);
            
           // BpmConfFormMongoDao.save(bpmConfForm);
        }
    }

    /**
     * 配置表单，startEvent.
     */
    public void processForm(StartEvent startEvent, BpmConfNode bpmConfNode) {
        if (startEvent.getFormKey() == null) {
            return;
        }

//        BpmConfFormManager bpmConfFormManager = getBpmConfFormManager();
//        BpmConfForm bpmConfForm = bpmConfFormManager.findUnique(
//                "from BpmConfForm where bpmConfNode=?", bpmConfNode);

        BpmConfFormMongoDao BpmConfFormMongoDao=getBpmConfFormMongoDao();
        Query query=new Query();
        query.addCriteria(Criteria.where("bpmConfNode").is(bpmConfNode));
        BpmConfForm bpmConfForm =BpmConfFormMongoDao.findOne(query);
        long id=getIdGenerator().generateId();
        if (bpmConfForm == null) {
            bpmConfForm = new BpmConfForm();
            bpmConfForm.setId(id);
            bpmConfForm.setValue(startEvent.getFormKey());
            bpmConfForm.setType(0);
            bpmConfForm.setOriginValue(startEvent.getFormKey());
            bpmConfForm.setOriginType(0);
            bpmConfForm.setStatus(0);
            bpmConfForm.setBpmConfNode(bpmConfNode);
          // bpmConfFormManager.save(bpmConfForm);
            
           BpmConfFormMongoDao.save(bpmConfForm);
        }
    }

    // ~ ======================================================================
    public BpmConfBaseManager getBpmConfBaseManager() {
        return ApplicationContextHelper.getBean(BpmConfBaseManager.class);
    }

    public BpmConfNodeManager getBpmConfNodeManager() {
        return ApplicationContextHelper.getBean(BpmConfNodeManager.class);
    }

    public BpmConfUserManager getBpmConfUserManager() {
        return ApplicationContextHelper.getBean(BpmConfUserManager.class);
    }

    public BpmConfListenerManager getBpmConfListenerManager() {
        return ApplicationContextHelper.getBean(BpmConfListenerManager.class);
    }

    public BpmConfFormManager getBpmConfFormManager() {
        return ApplicationContextHelper.getBean(BpmConfFormManager.class);
    }

    public BpmConfCountersignManager getBpmConfCountersignManager() {
        return ApplicationContextHelper
                .getBean(BpmConfCountersignManager.class);
    }

    public TaskDefinitionConnector getTaskDefinitionConnector() {
        return ApplicationContextHelper.getBean(TaskDefinitionConnector.class);
    }
//    
//    public BpmConfNodeMongoService getBpmConfNodeMongoService(){
//    	return ApplicationContextHelper.getBean(BpmConfNodeMongoService.class);
//    }
//    
    public BpmConfFormMongoDao getBpmConfFormMongoDao(){
    	return ApplicationContextHelper.getBean(BpmConfFormMongoDao.class);
    }
//    
//    public BpmConfUserMongoService getBpmConfUserMongoService(){
//    	return ApplicationContextHelper.getBean(BpmConfUserMongoService.class);
//    }
//    
//    public BpmConfListenerMongoService getBpmConfListenerMongoService(){
//    	return ApplicationContextHelper.getBean(BpmConfListenerMongoService.class);
//    }
//    
//    public BpmConfBaseMongoService getBpmConfBaseMongoService(){
//    	return ApplicationContextHelper.getBean(BpmConfBaseMongoService.class);
//    }
//    
    public IdGenerator getIdGenerator(){
    	return ApplicationContextHelper.getBean(IdGenerator.class);
    }
//    
//    public BpmConfCountersignMongoService getBpmConfCountersignMongoService(){
//    	return ApplicationContextHelper.getBean(BpmConfCountersignMongoService.class);
//    }
    
}
