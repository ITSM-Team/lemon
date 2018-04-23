<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="_header_first.jsp"%>
<script type="text/javascript">
	$(function(){
		$("#collapse-body-system ").attr("class","panel-collapse collapse in");
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
            	<i class="glyphicon glyphicon-list"></i> 组织机构<b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="${tenantPrefix}/party/tree-list.do"><i class="glyphicon glyphicon-list"></i> 组织机构图</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/party/party-entity-list.do"><i class="glyphicon glyphicon-list"></i> 组织机构</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/party/party-struct-list.do"><i class="glyphicon glyphicon-list"></i> 组织机构结构</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/party/party-type-list.do"><i class="glyphicon glyphicon-list"></i> 组织机构类型</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/party/party-struct-type-list.do"><i class="glyphicon glyphicon-list"></i> 组织机构结构类型</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/party/party-struct-rule-list.do"><i class="glyphicon glyphicon-list"></i> 组织机构结构规则</a></li>
					</ul></li>
			</ul>
			
			<ul class="nav navbar-nav navbar-left"  >
				<li class="dropdown"><a data-toggle="dropdown" class="dropdown-toggle" href="#">
            	<i class="glyphicon glyphicon-list"></i> 模板<b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="${tenantPrefix}/template/template-info-list.do"><i class="glyphicon glyphicon-list"></i> 模板管理</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/template/template-field-list.do"><i class="glyphicon glyphicon-list"></i> 模板项管理</a></li>
					</ul></li>
			</ul>
			
			<ul class="nav navbar-nav navbar-left" >
				<li class="dropdown"><a data-toggle="dropdown" class="dropdown-toggle" href="#"> 
			    <i class="glyphicon glyphicon-list"></i> 附件<b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="${tenantPrefix}/store/store-info-list.do"><i class="glyphicon glyphicon-list"></i> 附件</a></li>
					</ul></li>
			</ul>
			
			<ul class="nav navbar-nav navbar-left" >
				<li class="dropdown"><a data-toggle="dropdown" class="dropdown-toggle" href="#"> 
			    <i class="glyphicon glyphicon-list"></i> 数据字典<b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="${tenantPrefix}/dict/dict-type-list.do"><i class="glyphicon glyphicon-list"></i> 数据字典</a></li>
					</ul></li>
			</ul>
			<%@include file="_header_second.jsp"%>
		</div>

	</div>
</div>
