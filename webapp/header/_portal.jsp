<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="_header_first.jsp"%>
<script type="text/javascript">
	$(function(){
		$("#collapse-body-org ").attr("class","panel-collapse collapse in");
	});
</script>

<div class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="${tenantPrefix}/portal/index.do"> <%-- <img src="${cdnPrefix}/logo32.png" class="img-responsive pull-left" style="margin-top: -5px; margin-right: 5px;"> Lemon <sub><small>1.8.0</small></sub> --%>
			</a>
		</div>

		<div class="navbar-collapse collapse">
			<!-- 隐藏顶部导航栏 -->
			<!-- <ul class="nav navbar-nav" id="navbar-menu">
		<tags:menuNav3 systemCode="pim"/>
      </ul> -->

			<ul class="nav navbar-nav navbar-right">
				<li>
					<form class="navbar-form navbar-search" action="${tenantPrefix}/pim/address-list-list.do" role="search">
						<div class="form-group">
							<input type="text" class="form-control search-query" placeholder="搜索" name="username">
						</div>
					</form>
				</li>

				<!-- 隐藏主菜单入口 -->
				<!-- <tags:menuSystem3/> -->

				<li class="dropdown"><a data-toggle="dropdown" class="dropdown-toggle" href="#"> <img src="${tenantPrefix}/rs/avatar?id=<tags:currentUserId/>&width=16" style="width: 16px; height: 16px;" class="img-circle"> <tags:currentUser /> <b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li class="text-center">&nbsp;<img src="${tenantPrefix}/rs/avatar?id=<tags:currentUserId/>&width=64" style="width: 64px; height: 64px;" class="img-rounded"></li>
						<li><a href="${tenantPrefix}/user/my-info-input.do"><i class="glyphicon glyphicon-list"></i> 个人信息</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/j_spring_security_logout"><i class="glyphicon glyphicon-list"></i> 退出</a></li>
					</ul></li>
				<li><a href="${tenantPrefix}/pim/address-list-list.do""> <i class="glyphicon glyphicon-bell"></i> <i id="unreadMsg" class="badge"></i>
				</a></li>
				<!-- 隐藏添加面板入口 -->
				<li>
					<!-- <button class="btn btn-default btn-sm navbar-btn" onclick="insertWidget()"><i class="glyphicon glyphicon-plus"></i></button> -->
				</li>
			</ul>
		</div>

	</div>
</div>

<style>
.feedback {
	bottom: 10px;
	height: 54px;
	left: 100%;
	margin-left: -60px;
	position: fixed;
	width: 54px;
	z-index: 998;
}

.feedback a {
	display: inline-block;
	border-radius: 50%;
	height: 48px;
	width: 48px;
	background-color: gray;
	padding: 10px;
	text-align: center;
	color: white;
	line-height: 14px;
	font-size: 12px;
}
</style>

