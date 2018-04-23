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
            	<i class="glyphicon glyphicon-list"></i> 人工任务<b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="${tenantPrefix}/humantask/task-info-list.do"><i class="glyphicon glyphicon-list"></i> 任务列表</a></li>
					</ul></li>
			</ul>
			<%@include file="_header_second.jsp"%>
		</div>

	</div>
</div>
