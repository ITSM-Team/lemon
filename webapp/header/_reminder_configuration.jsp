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
            	<i class="glyphicon glyphicon-list"></i> 发送邮件<b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="${tenantPrefix}/sendmail/sendmail-config-list.do"><i class="glyphicon glyphicon-list"></i> 邮件配置</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/sendmail/sendmail-template-list.do"><i class="glyphicon glyphicon-list"></i> 邮件模板</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/sendmail/sendmail-queue-list.do"><i class="glyphicon glyphicon-list"></i> 邮件队列</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/sendmail/sendmail-history-list.do"><i class="glyphicon glyphicon-list"></i> 邮件历史</a></li>	
					</ul></li>
			</ul>
			
			<ul class="nav navbar-nav navbar-left"  >
				<li class="dropdown"><a data-toggle="dropdown" class="dropdown-toggle" href="#">
            	<i class="glyphicon glyphicon-list"></i> 发送短信<b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="${tenantPrefix}/sendsms/sendsms-config-list.do"><i class="glyphicon glyphicon-list"></i> 短信配置</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/sendsms/sendsms-queue-list.do"><i class="glyphicon glyphicon-list"></i> 短信队列</a></li>
						<li class="divider"></li>
						<li><a href="${tenantPrefix}/sendsms/sendsms-history-list.do"><i class="glyphicon glyphicon-list"></i> 短信历史</a></li>
					</ul></li>
			</ul>
			<%@include file="_header_second.jsp"%>
		</div>

	</div>
</div>
