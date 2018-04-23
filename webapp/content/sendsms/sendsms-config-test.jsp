<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglibs.jsp"%>
<%
	pageContext.setAttribute("currentHeader", "main-menu");
%>
<%
	pageContext.setAttribute("currentMenu", "system");
%>
<!doctype html>
<html lang="en">

<head>
<%@include file="/common/meta.jsp"%>
<title>编辑</title>
<%@include file="/common/s3.jsp"%>
<script type="text/javascript">
	$( function(){$("#smsConfigForm").validate( {
			submitHandler : function(form) {
				bootbox.animate(false);
				var box = bootbox.dialog('<div class="progress progress-striped active" style="margin:0px;"><div class="bar" style="width: 100%;"></div></div>');
				form.submit();
			},
			errorClass : 'validate-error'
		});
	})
</script>
</head>

<body>
	<%@include file="/header/_reminder_configuration.jsp"%>
	<div class="row-fluid">
		<%@include file="/menu/main-menu.jsp"%>
		<section id="m-main" class="col-md-10" style="padding-top: 65px;">
			<div class="panel panel-default">
				<div class="panel-heading">
					<i class="glyphicon glyphicon-list"></i>
					<spring:message code="scope-info.scope-info.list.title" text="编辑" />
				</div>

				<form id="smsConfigGridForm" name="smsConfigGridForm" method='post' action="sendsms-config-remove.do" class="m-form-blank">
					<div class="panel-body">
						<div class="form-group">
							<input id="smsConfig_id" type="hidden" name="id" value="${param.id}">
							<div class="control-group">
								<label class="control-label" for="smsConfig_name">手机</label>
								<div class="controls">
									<input id="smsConfig_name" type="text" name="mobile" value="" class="required" minlength="11" maxlength="11">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="smsConfig_name">信息</label>
								<div class="controls">
									<input id="smsConfig_name" type="text" name="message" value="test" class="required" minlength="1" maxlength="70">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-5">
								<button type="submit" class="btn a-submit">
									<spring:message code='core.input.save' text='保存' />
								</button>
								&nbsp;
								<button type="button" class="btn a-cancel" onclick="history.back();">
									<spring:message code='core.input.back' text='返回' />
								</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</section>
	</div>
</body>

</html>
