<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>编辑</title>
    <%@include file="/common/s3.jsp"%>
    <script type="text/javascript">
$(function() {
    $("#console-prepareJumpForm").validate({
        submitHandler: function(form) {
			bootbox.animate(false);
			var box = bootbox.dialog('<div class="progress progress-striped active" style="margin:0px;"><div class="bar" style="width: 100%;"></div></div>');
            form.submit();
        },
        errorClass: 'validate-error'
    });
})
    </script>
  </head>

  <body>
    <%@include file="/header/_process_management.jsp"%>

    <div class="row-fluid">
	  <%@include file="/menu/main-menu.jsp"%>

	<!-- start of main -->
      <section id="m-main" class="col-md-10" style="padding-top:65px;">

      <div class="panel panel-default">
        <div class="panel-heading">
		  <i class="glyphicon glyphicon-list"></i>
		  上传
		</div>

		<div class="panel-body">


<form id="demoForm" method="post" action="console-process-upload.do" class="form-horizontal" enctype="multipart/form-data">
  <div class="form-group">
    <label class="control-label col-md-1">流程定义</label>
	<div class="col-sm-5">
	  <input type="file" name="file">
    </div>
  </div>
  <div class="form-group">
    <div class="col-md-offset-1 col-md-11">
      <button id="submitButton" type="submit" class="btn btn-default">保存</button>
	  &nbsp;
      <button type="button" onclick="history.back();" class="btn btn-link">返回</button>
    </div>
  </div>
</form>

		</div>
      </article>

    </section>
	<!-- end of main -->
	</div>

  </body>

</html>

