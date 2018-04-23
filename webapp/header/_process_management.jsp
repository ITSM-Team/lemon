<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="_header_first.jsp"%>
<script type="text/javascript">
	$(function(){
		$("#collapse-body-bpm ").attr("class","panel-collapse collapse in");
	});
</script>


<div class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="${tenantPrefix}/portal/index.do"> 
			<%-- <img src="${cdnPrefix}/logo32.png" class="img-responsive pull-left" style="margin-top: -5px; margin-right: 5px;"> Lemon <sub><small>1.8.0</small></sub> --%>
			</a>
		</div>

		<div class="navbar-collapse collapse" >
			<ul class="nav navbar-nav navbar-left" style="margin-left: 196px;">
				<li class="dropdown"><a data-toggle="dropdown" class="dropdown-toggle" href="#"> 
            	<i class="glyphicon glyphicon-list"></i> 分类管理<b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="${tenantPrefix}/bpm/bpm-category-list.do"><i class="glyphicon glyphicon-list"></i> 流程分类</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/bpm/bpm-process-list.do"><i class="glyphicon glyphicon-list"></i> 流程配置</a></li>
					</ul></li>
			</ul>
			
			<ul class="nav navbar-nav navbar-left"  >
				<li class="dropdown"><a data-toggle="dropdown" class="dropdown-toggle" href="#">
            	<i class="glyphicon glyphicon-list"></i> 流程管理<b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="${tenantPrefix}/modeler/modeler-list.do"><i class="glyphicon glyphicon-list"></i> 发布流程</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/bpm/console-listProcessDefinitions.do"><i class="glyphicon glyphicon-list"></i> 流程定义</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/bpm/console-listProcessInstances.do"><i class="glyphicon glyphicon-list"></i> 流程实例</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/bpm/console-listTasks.do"><i class="glyphicon glyphicon-list"></i> 任务</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/bpm/console-listDeployments.do"><i class="glyphicon glyphicon-list"></i> 部署</a></li>
					</ul></li>
			</ul>
			
			<ul class="nav navbar-nav navbar-left" >
				<li class="dropdown"><a data-toggle="dropdown" class="dropdown-toggle" href="#"> 
			    <i class="glyphicon glyphicon-list"></i> 流程历史<b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="${tenantPrefix}/bpm/console-listHistoricProcessInstances.do"><i class="glyphicon glyphicon-list"></i> 流程实例</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/bpm/console-listHistoricActivityInstances.do"><i class="glyphicon glyphicon-list"></i> 流程节点</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/bpm/console-listHistoricTasks.do"><i class="glyphicon glyphicon-list"></i> 流程任务</a></li>
					</ul></li>
			</ul>
			<%@include file="_header_second.jsp"%>
		</div>

	</div>
</div>
