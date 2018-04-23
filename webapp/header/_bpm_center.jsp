<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="_header_first.jsp"%>
<script type="text/javascript">
	$(function(){
		$("#collapse-body-myBpm ").attr("class","panel-collapse collapse in");
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
            	<i class="glyphicon glyphicon-list"></i> 我的流程 <b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="${tenantPrefix}/bpm/workspace-home.do"><i class="glyphicon glyphicon-list"></i> 发起流程</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/bpm/workspace-listRunningProcessInstances.do"><i class="glyphicon glyphicon-list"></i> 未结流程</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/bpm/workspace-listCompletedProcessInstances.do"><i class="glyphicon glyphicon-list"></i> 办结流程</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/operation/process-operation-listDrafts.do"><i class="glyphicon glyphicon-list"></i> 草稿箱</a></li>
					</ul></li>
			</ul>
			
			<ul class="nav navbar-nav navbar-left"  >
				<li class="dropdown"><a data-toggle="dropdown" class="dropdown-toggle" href="#">
            	<i class="glyphicon glyphicon-list"></i> 我的任务<b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="${tenantPrefix}/humantask/workspace-personalTasks.do"><i class="glyphicon glyphicon-list"></i> 待办任务</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/humantask/workspace-groupTasks.do"><i class="glyphicon glyphicon-list"></i> 待领任务</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/humantask/workspace-historyTasks.do"><i class="glyphicon glyphicon-list"></i> 已办任务</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/humantask/workspace-delegatedTasks.do"><i class="glyphicon glyphicon-list"></i> 经手任务</a></li>
					</ul></li>
			</ul>
			
			<ul class="nav navbar-nav navbar-left" >
				<li class="dropdown"><a data-toggle="dropdown" class="dropdown-toggle" href="#"> 
			    <i class="glyphicon glyphicon-list"></i> 规则设置<b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="${tenantPrefix}/delegate/delegate-listMyDelegateInfos.do"><i class="glyphicon glyphicon-list"></i> 代理规则</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/pim/pim-phrase-my-list.do"><i class="glyphicon glyphicon-list"></i> 常用语</a></li>
					</ul></li>
			</ul>
			<%@include file="_header_second.jsp"%>
		</div>

	</div>
</div>
