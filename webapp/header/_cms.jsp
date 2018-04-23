<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="_header_first.jsp"%>
<script type="text/javascript">
	$(function(){
		$("#collapse-body-cms ").attr("class","panel-collapse collapse in");
	});
</script>

<div class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="${tenantPrefix}/portal/index.do">
	    <%-- <img src="${cdnPrefix}/logo32.png" class="img-responsive pull-left" style="margin-top:-5px;margin-right:5px;">
	    Lemon <sub><small>1.8.0</small></sub> --%>
      </a>
    </div>
	<!-- 隐藏顶部导航菜单 -->
    <!-- <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav" id="navbar-menu">
		<tags:menuNav3 systemCode="cms"/>
      </ul> -->

	  <%@include file="_header_second.jsp"%>
    </div>

  </div>
</div>
