<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="_header_first.jsp"%>
<script type="text/javascript">
	$(function(){
		$("#collapse-body-user ").attr("class","panel-collapse collapse in");
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
            	<i class="glyphicon glyphicon-list"></i> 权限配置 <b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="${tenantPrefix}/auth/user-connector-list.do"><i class="glyphicon glyphicon-list"></i> 用户管理</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/auth/role-viewList.do"><i class="glyphicon glyphicon-list"></i> 角色管理</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/auth/perm-type-list.do"><i class="glyphicon glyphicon-list"></i> 授权分类</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/auth/perm-list.do"><i class="glyphicon glyphicon-list"></i> 授权管理</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/auth/access-list.do"><i class="glyphicon glyphicon-list"></i> 访问权限</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/auth/menu-list.do"><i class="glyphicon glyphicon-list"></i> 菜单管理</a></li>
					</ul></li>
			</ul>
			<%@include file="_header_second.jsp"%>
		</div>

	</div>
</div>
