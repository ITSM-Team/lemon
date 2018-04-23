package com.mossle.bpm.web;

import java.util.List;

import javax.annotation.Resource;
import com.mossle.bpm.persistence.domain.BpmConfNode;
import com.mossle.bpm.persistence.domain.BpmConfRule;
import com.mossle.bpm.persistence.manager.BpmConfNodeManager;
import com.mossle.bpm.persistence.manager.BpmConfRuleManager;
import com.mossle.bpm.persistence.manager.BpmProcessManager;
import com.mossle.core.mapper.BeanMapper;
import org.activiti.engine.ProcessEngine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("bpm")
public class BpmConfRuleController {
    private BpmConfNodeManager bpmConfNodeManager;
    private BpmConfRuleManager bpmConfRuleManager;
    private BeanMapper beanMapper = new BeanMapper();
    private ProcessEngine processEngine;
    private BpmProcessManager bpmProcessManager;
//    @Autowired
//    private BpmConfNodeMongoDao bpmConfNodeMongoDao;
//    @Autowired
//    private BpmConfRuleMongoDao bpmConfRuleMongoDao;
//    private IdGenerator idGenerator;

    @RequestMapping("bpm-conf-rule-list")
    public String list(@RequestParam("bpmConfNodeId") Long bpmConfNodeId,
            Model model) {
      BpmConfNode bpmConfNode = bpmConfNodeManager.get(bpmConfNodeId);
      //  BpmConfNode bpmConfNode = bpmConfNodeMongoDao.findById(bpmConfNodeId);
        Long bpmConfBaseId = bpmConfNode.getBpmConfBase().getId();
        List<BpmConfRule> bpmConfRules = bpmConfRuleManager.findBy(
                "bpmConfNode", bpmConfNode);
//        Query query=new Query();
//        query.addCriteria(Criteria.where("bpmConfNode").is(bpmConfNode));
//        List<BpmConfRule> bpmConfRules = bpmConfRuleMongoDao.find(query);

        model.addAttribute("bpmConfBaseId", bpmConfBaseId);
        model.addAttribute("bpmConfRules", bpmConfRules);

        return "bpm/bpm-conf-rule-list";
    }

    @RequestMapping("bpm-conf-rule-save")
    public String save(@ModelAttribute BpmConfRule bpmConfRule,
            @RequestParam("bpmConfNodeId") Long bpmConfNodeId) {
        if ((bpmConfRule.getValue() == null)
                || "".equals(bpmConfRule.getValue())) {
            return "redirect:/bpm/bpm-conf-rule-list.do?bpmConfNodeId="
                    + bpmConfNodeId;
        }
        //BpmConfNode bpmConfNode = bpmConfNodeMongoDao.findById(bpmConfNodeId);
        BpmConfNode bpmConfNode = bpmConfNodeManager.get(bpmConfNodeId);
        bpmConfRule.setBpmConfNode(bpmConfNode);
       bpmConfRuleManager.save(bpmConfRule);
//        Long id=bpmConfRule.getId();
//        if(id ==null){
//        	bpmConfRule.setId(idGenerator.generateId());
//        }
//        bpmConfRuleMongoDao.save(bpmConfRule);
        return "redirect:/bpm/bpm-conf-rule-list.do?bpmConfNodeId="
                + bpmConfNodeId;
    }

    @RequestMapping("bpm-conf-rule-remove")
    public String remove(@RequestParam("id") Long id) {
      BpmConfRule bpmConfRule = bpmConfRuleManager.get(id);
      //  BpmConfRule bpmConfRule =bpmConfRuleMongoDao.findById(id);
        Long bpmConfNodeId = bpmConfRule.getBpmConfNode().getId();
       bpmConfRuleManager.remove(bpmConfRule);
    //   bpmConfRuleMongoDao.remove(bpmConfRule.getId());
        return "redirect:/bpm/bpm-conf-rule-list.do?bpmConfNodeId="
                + bpmConfNodeId;
    }

    // ~ ======================================================================
    @Resource
    public void setBpmConfNodeManager(BpmConfNodeManager bpmConfNodeManager) {
        this.bpmConfNodeManager = bpmConfNodeManager;
    }

    @Resource
    public void setBpmConfRuleManager(BpmConfRuleManager bpmConfRuleManager) {
        this.bpmConfRuleManager = bpmConfRuleManager;
    }

    @Resource
    public void setBpmProcessManager(BpmProcessManager bpmProcessManager) {
        this.bpmProcessManager = bpmProcessManager;
    }

    @Resource
    public void setProcessEngine(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

//    @Resource
//	public void setIdGenerator(IdGenerator idGenerator) {
//		this.idGenerator = idGenerator;
//	}
    
}
