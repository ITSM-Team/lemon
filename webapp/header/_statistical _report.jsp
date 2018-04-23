<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		 <c:set var="flag" value="1"/>
			<c:forEach items="${list}" var="item">
				<ul class="nav navbar-nav navbar-left" <c:if test="${flag =='1'}">style="margin-left: 195px;</c:if>">
					<li class="dropdown"><a data-toggle="dropdown" class="dropdown-toggle" href="#"> <i class="glyphicon glyphicon-list"></i> ${item.reportSubject.name} <b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<c:forEach items="${item.reportInfos}" var="reportInfo" varStatus="i">
								<li><a href="${tenantPrefix}/report/view.do?code=${reportInfo.code}"><i class="glyphicon glyphicon-list"></i> ${reportInfo.name}</a></li>
								<c:if test="${!i.last }">
									<li class="divider"></li>
								</c:if> 
							</c:forEach>
						</ul></li>
				</ul>
				<c:set var="flag" value="${flag+1}"/>
			</c:forEach>
			
			<%@include file="_header_second.jsp"%>
		</div>

	</div>
</div>
