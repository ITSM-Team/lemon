<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglibs.jsp"%>
<!doctype html>
<html lang="en">

  <head>
    <%@include file="/common/meta.jsp"%>
    <title><spring:message code="dev.job-grade.list.title" text="列表"/></title>
    <%@include file="/common/s3.jsp"%>
    <script type="text/javascript">
var config = {
    id: 'job-gradeGrid',
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
	gridFormId: 'job-gradeGridForm',
	exportUrl: 'job-grade-export.do'
};

var table;

$(function() {
	table = new Table(config);
    table.configPagination('.m-pagination');
    table.configPageInfo('.m-page-info');
    table.configPageSize('.m-page-size');
});
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
		  	<li role="presentation" class="active"><a href="${tenantPrefix}/org/job-grade-list.do"> 职等管理</a></li>
		  	<li role="presentation"><a href="${tenantPrefix}/org/job-level-list.do"> 职级管理</a></li>
		  	<li role="presentation"><a href="${tenantPrefix}/org/job-type-list.do#"> 职务类型管理</a></li>
		  	<li role="presentation"><a href="${tenantPrefix}/org/job-title-list.do"> 职务名称管理</a></li>
		  	<li role="presentation"><a href="${tenantPrefix}/org/job-info-list.do"> 职务管理</a></li>
		  	<li role="presentation"><a href="${tenantPrefix}/org/job-user-list.do"> 人员职务管理</a></li>
		  	<li role="presentation"><a href="${tenantPrefix}/org/job-list.do"> 职等职级表</a></li>
		  </ul>
		  
<div class="panel panel-default">
  <div class="panel-heading">
	<i class="glyphicon glyphicon-list"></i>
    查询
	<div class="pull-right ctrl">
	  <a class="btn btn-default btn-xs"><i id="job-gradeSearchIcon" class="glyphicon glyphicon-chevron-up"></i></a>
    </div>
  </div>
  <div class="panel-body">

		  <form name="job-gradeForm" method="post" action="job-grade-list.do" class="form-inline">
		    <label for="job-grade_name"><spring:message code='job-grade.job-grade.list.search.name' text='名称'/>:</label>
		    <input type="text" id="job-grade_name" name="filter_LIKES_name" value="${param.filter_LIKES_name}" class="form-control">
			<button class="btn btn-default a-search" onclick="document.job-gradeForm.submit()">查询</button>&nbsp;
		  </form>

		</div>
	  </div>

      <div style="margin-bottom: 20px;">
	    <div class="pull-left btn-group" role="group">
		  <button class="btn btn-default a-insert" onclick="location.href='job-grade-input.do'">新建</button>
		  <button class="btn btn-default a-remove" onclick="table.removeAll()">删除</button>
		  <button class="btn btn-default a-export" onclick="table.exportExcel()">导出</button>
		</div>

		<div class="pull-right">
		  每页显示
		  <select class="m-page-size form-control" style="display:inline;width:auto;">
		    <option value="10">10</option>
		    <option value="20">20</option>
		    <option value="50">50</option>
		  </select>
		  条
        </div>

	    <div class="clearfix"></div>
	  </div>

<form id="job-gradeGridForm" name="job-gradeGridForm" method='post' action="job-grade-remove.do" class="m-form-blank">
      <div class="panel panel-default">
        <div class="panel-heading">
		  <i class="glyphicon glyphicon-list"></i>
		  <spring:message code="scope-info.scope-info.list.title" text="列表"/>
		</div>


  <table id="orgGrid" class="table table-hover">
    <thead>
      <tr>
        <th width="10" class="table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
        <th class="sorting" name="id"><spring:message code="org.org.list.id" text="编号"/></th>
        <th class="sorting" name="name">名称</th>
        <th width="80">&nbsp;</th>
      </tr>
    </thead>

    <tbody>
      <c:forEach items="${page.result}" var="item">
      <tr>
        <td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.id}"></td>
        <td>${item.id}</td>
        <td>${item.name}</td>
        <td>
          <a href="job-grade-input.do?id=${item.id}" class="a-update"><spring:message code="core.list.edit" text="编辑"/></a>
        </td>
      </tr>
      </c:forEach>
    </tbody>
  </table>


      </div>
</form>

	  <div>
	    <div class="m-page-info pull-left">
		  共100条记录 显示1到10条记录
		</div>

		<div class="btn-group m-pagination pull-right">
		  <button class="btn btn-default">&lt;</button>
		  <button class="btn btn-default">1</button>
		  <button class="btn btn-default">&gt;</button>
		</div>

	    <div class="clearfix"></div>
      </div>

      <div class="m-spacer"></div>

      </section>
	  <!-- end of main -->
	</div>

  </body>

</html>

