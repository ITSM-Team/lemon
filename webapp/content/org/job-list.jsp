<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglibs.jsp"%>
<!doctype html>
<html lang="en">

  <head>
    <%@include file="/common/meta.jsp"%>
    <title><spring:message code="dev.job.list.title" text="列表"/></title>
    <%@include file="/common/s3.jsp"%>
    <script type="text/javascript">
/*
var config = {
    id: 'jobGrid',
    pageNo: ${page.pageNo},
    pageSize: ${page.pageSize},
    totalCount: ${page.totalCount},
    resultSize: ${page.resultSize},
    pageCount: ${page.pageCount},
    orderBy: '${page.orderBy == null ? "" : page.orderBy}',
    asc: ${page.asc},
    params: {
        'filter_LIKES_name': '${param.filter_LIKES_name}'
    },
	selectedItemClass: 'selectedItem',
	gridFormId: 'jobGridForm',
	exportUrl: 'job-export.do'
};

var table;

$(function() {
	table = new Table(config);
    table.configPagination('.m-pagination');
    table.configPageInfo('.m-page-info');
    table.configPageSize('.m-page-size');
});
*/
    </script>
  </head>

  <body>
    <%@include file="/header/portal.jsp"%>

    <div class="row-fluid">
	  <%@include file="/menu/main-menu.jsp"%>

	  <!-- start of main -->
      <section id="m-main" class="col-md-10" style="padding-top:65px;">
		
		<!-- 职务管理 -->
		 <ul class="nav nav-tabs">
		  	<li role="presentation"><a href="${tenantPrefix}/org/org-company-list.do"> 公司</a></li>
		  	<li role="presentation"><a href="${tenantPrefix}/org/org-department-list.do"> 部门</a></li>
		  	<li role="presentation"><a href="${tenantPrefix}/org/job-grade-list.do"> 职等管理</a></li>
		  	<li role="presentation""><a href="${tenantPrefix}/org/job-level-list.do"> 职级管理</a></li>
		  	<li role="presentation"><a href="${tenantPrefix}/org/job-type-list.do#"> 职务类型管理</a></li>
		  	<li role="presentation"><a href="${tenantPrefix}/org/job-title-list.do"> 职务名称管理</a></li>
		  	<li role="presentation"><a href="${tenantPrefix}/org/job-info-list.do"> 职务管理</a></li>
		  	<li role="presentation"><a href="${tenantPrefix}/org/job-user-list.do"> 人员职务管理</a></li>
		  	<li role="presentation" class="active"><a href="${tenantPrefix}/org/job-list.do"> 职等职级表</a></li>
		  </ul>
		
      <div class="panel panel-default">
        <div class="panel-heading">
		  <i class="glyphicon glyphicon-list"></i>
		  职位矩阵
		</div>


<table class="table">
  <thead>
    <tr>
	  <th>职等</th>
	  <th>职级</th>
	  <c:forEach items="${jobTypes}" var="item">
      <th>${item.name}</th>
	  </c:forEach>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${list}" var="map">
    <tr>
	  <c:if test="${map.printJobGrade}">
      <td rowspan="${map.jobGradeSize}" style="vertical-align:middle">${map.jobGrade.name}</td>
	  </c:if>
	  <td>${map.jobLevel.name}</td>
	  <c:forEach items="${map.jobInfos}" var="jobInfo">
	  <td>${jobInfo.jobTitle.name}&nbsp;</td>
	  </c:forEach>
	</tr>
	</c:forEach>
  </tbody>
</table>

      </div>


      </section>
	  <!-- end of main -->
	</div>

  </body>

</html>

