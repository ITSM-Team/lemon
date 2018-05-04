package com.mongodb.Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

public class DOM4JUtil {
	private ProcessEngine processEngine;

	enum spiltenum {
		spilt("$"), start("{"), equal("=="), noequal("!=");

		private String code;

		spiltenum(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

	}
	
	/**
	 * 直接完成任务
	 * */
	public static  Map<String, Object>  completemission(String taskId){
		DOM4JUtil dom4jUtil=new DOM4JUtil();
		return dom4jUtil.complete(taskId);
		
	}
	
	
	/**
	 * 根据taskid设置流程变量值
	 * */
	private  Map<String, Object> complete(String taskId) {
		Map<String, Object> variables = new HashMap<String, Object>();
		TaskService taskService = processEngine.getTaskService();
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		// 获得流程定义ID
		String processDefinitionId = task.getProcessDefinitionId();

		RepositoryService repositoryService = processEngine.getRepositoryService();
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		String resourceName = processDefinition.getResourceName();
		InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
				resourceName);
		try {
			if (variables == null || variables.size() <= 0) {
				variables = DOM4JUtil.quequdom4j(resourceAsStream);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resourceAsStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return variables;
	}

	/**
	 * 通过dom4j解析activiti部署xml文件
	 */
	private  static Map<String, Object> quequdom4j(InputStream in) {
		// 创建SAXReader的对象reader
		SAXReader reader = new SAXReader();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 通过reader对象的read方法加载xml文件,获取docuemnt对象。
			Document document = reader.read(in);
			// 设置命名空间
			map.put("design", "http://www.omg.org/spec/BPMN/20100524/MODEL");
			XPath x = document.createXPath("//design:conditionExpression");
			x.setNamespaceURIs(map);
			List<Node> nodelist = x.selectNodes(document);
			map.clear();
			String string = null;
			for (Node node : nodelist) {
				string = substringUtilx(node.getText());
				map.put(string, "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 截取字符串 获取变量名
	 */
	public static String substringUtilx(String value) {
		String result = null;
		// 如果存在$符号 则执行 截取
		if (value.contains(spiltenum.spilt.getCode())) {
			// 起始位置
			int startlenth = value.indexOf(spiltenum.start.getCode());
			int endlenth = 0;
			int lenth = spiltenum.start.getCode().length();
			// 判断是否有==符号
			if (value.contains(spiltenum.equal.getCode())) {
				endlenth = value.indexOf(spiltenum.equal.getCode());
			} else if (value.contains(spiltenum.noequal.getCode())) { // 判断是否有!=符号
				endlenth = value.indexOf(spiltenum.noequal.getCode());
			}
			// 截取
			int startindex = startlenth + lenth;
			if (startindex >= 0 && endlenth >= 0) {
				result = value.substring(startlenth + lenth, endlenth);
			}
		}
		return result;

	}

	// ~ ==================================================
	@Resource
	public void setProcessEngine(ProcessEngine processEngine) {
		this.processEngine = processEngine;
	}

}
