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
    $("#cms-article-imageForm").validate({
        submitHandler: function(form) {
			bootbox.animate(false);
			var box = bootbox.dialog('<div class="progress progress-striped active" style="margin:0px;"><div class="bar" style="width: 100%;"></div></div>');
            form.submit();
        },
        errorClass: 'validate-error'
    });
})
    </script>
    <!-- blueimp Gallery styles -->
    <link rel="stylesheet" href="${cdnPrefix}/jquery-file-upload/blueimp-gallery.min.css">
    <!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
    <link rel="stylesheet" href="${cdnPrefix}/jquery-file-upload/css/jquery.fileupload.css">
    <link rel="stylesheet" href="${cdnPrefix}/jquery-file-upload/css/jquery.fileupload-ui.css">
    <!-- CSS adjustments for browsers with JavaScript disabled -->
    <noscript><link rel="stylesheet" href="${cdnPrefix}/jquery-file-upload/css/jquery.fileupload-noscript.css"></noscript>
    <noscript><link rel="stylesheet" href="${cdnPrefix}/jquery-file-upload/css/jquery.fileupload-ui-noscript.css"></noscript>
  </head>

  <body>
    <%@include file="/header/_bulletin_management.jsp"%>

    <div class="row-fluid">
	  <%@include file="/menu/main-menu.jsp"%>

	<!-- start of main -->
      <section id="m-main" class="col-md-10" style="padding-top:65px;">

      <div class="panel panel-default">
        <div class="panel-heading">
		  <i class="glyphicon glyphicon-list"></i>
		  编辑
		</div>

		<div class="panel-body">


<form id="cmsArticleForm" method="post" action="cms-article-save.do" class="form-horizontal">
  <c:if test="${model != null}">
  <input id="cms-article_id" type="hidden" name="id" value="${model.id}">
  </c:if>
  <div class="form-group">
    <label class="control-label col-md-1" for="cms-article_cmsArticlename">栏目</label>
	<div class="col-sm-5">
      <select id="perm_resc" name="cmsCatalogId">
	    <c:forEach items="${cmsCatalogs}" var="item">
	    <option value="${item.id}" ${model.cmsCatalog.id==item.id ? 'selected' : ''}>${item.name}</option>
		</c:forEach>
	  </select>
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-md-1" for="cms-article_cmsArticlename">标题</label>
	<div class="col-sm-5">
	  <input id="cms-article_cmsArticlename" type="text" name="title" value="${model.title}" size="40" class="form-control required" minlength="2" maxlength="50">
    </div>
  </div>
<!--
  <div class="form-group">
    <label class="control-label col-md-1" for="cms-article_cmsArticlename">短标题</label>
	<div class="col-sm-5">
	  <input id="cms-article_cmsArticlename" type="text" name="shortTitle" value="${model.shortTitle}" size="40" class="text" minlength="2" maxlength="50">
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-md-1" for="cms-article_cmsArticlename">副标题</label>
	<div class="col-sm-5">
	  <input id="cms-article_cmsArticlename" type="text" name="subTitle" value="${model.subTitle}" size="40" class="text" minlength="2" maxlength="50">
    </div>
  </div>
-->
  <div class="form-group">
    <label class="control-label col-md-1" for="cmsArticle_summary">摘要</label>
	<div class="col-sm-5">
	  <textarea id="cmsArticle_summary" name="summary" maxlength="200">${model.summary}</textarea>
    </div>
  </div>
  <div class="form-group">
	<div class="col-sm-12">
      <div class="fileupload-buttonbar">
        <div class="col-lg-7">
          <!-- The fileinput-button span is used to style the file input field as button -->
          <span class="btn btn-success fileinput-button">
            <i class="glyphicon glyphicon-plus"></i>
            <span>添加</span>
            <input type="file" name="files[]" multiple data-no-uniform="true">
          </span>
          <button type="submit" class="btn btn-primary start">
            <i class="glyphicon glyphicon-upload"></i>
            <span>上传</span>
          </button>
          <button type="reset" class="btn btn-warning cancel">
            <i class="glyphicon glyphicon-ban-circle"></i>
            <span>取消</span>
          </button>
          <button type="button" class="btn btn-danger delete">
            <i class="glyphicon glyphicon-trash"></i>
            <span>删除</span>
          </button>
          <input type="checkbox" class="toggle">
          <!-- The global file processing state -->
          <span class="fileupload-process"></span>
        </div>
        <!-- The global progress state -->
        <div class="col-lg-5 fileupload-progress fade">
          <!-- The global progress bar -->
          <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
            <div class="progress-bar progress-bar-success" style="width:0%;"></div>
          </div>
          <!-- The extended global progress state -->
          <div class="progress-extended">&nbsp;</div>
        </div>
      </div>
      <!-- The table listing the files available for upload/download -->
      <table role="presentation" class="table table-striped"><tbody class="files"></tbody></table>
	</div>
  </div>

  <div class="form-group">
    <div class="col-sm-5">
      <button id="submitButton" class="btn a-submit"><spring:message code='core.input.save' text='保存'/></button>
      <button type="button" onclick="history.back();" class="btn a-cancel"><spring:message code='core.input.back' text='返回'/></button>
    </div>
  </div>
</form>

    <!-- blueimp图库小部件 -->
    <div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls" data-filter=":even">
      <div class="slides"></div>
      <h3 class="title"></h3>
      <a class="prev">‹</a>
      <a class="next">›</a>
      <a class="close">×</a>
      <a class="play-pause"></a>
      <ol class="indicator"></ol>
    </div>

		</div>
      </article>

    </section>
	</div>

  </body>

<!-- 显示可供上传的文件的模板 -->
<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade">
        <td>
            <span class="preview"></span>
        </td>
        <td>
            <p class="name">{%=file.name%}</p>
            <strong class="error text-danger"></strong>
        </td>
        <td>
            <p class="size">Processing...</p>
            <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
        </td>
        <td>
            {% if (!i && !o.options.autoUpload) { %}
                <button class="btn btn-primary start" disabled>
                    <i class="glyphicon glyphicon-upload"></i>
                    <span>上传</span>
                </button>
            {% } %}
            {% if (!i) { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>取消</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>
<!-- 显示可供下载的文件的模板-->
<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade">
        <td>
            <span class="preview">
                {% if (file.thumbnailUrl) { %}
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>
                {% } %}
            </span>
        </td>
        <td>
            <p class="name">
                {% if (file.url) { %}
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>
                {% } else { %}
                    <span>{%=file.name%}</span>
                {% } %}
            </p>
            {% if (file.error) { %}
                <div><span class="label label-danger">Error</span> {%=file.error%}</div>
            {% } %}
        </td>
        <td>
            <span class="size">{%=o.formatFileSize(file.size)%}</span>
        </td>
        <td>
            {% if (file.deleteUrl) { %}
                <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                    <i class="glyphicon glyphicon-trash"></i>
                    <span>删除</span>
                </button>
                <input type="checkbox" name="delete" value="1" class="toggle">
            {% } else { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>取消</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>

    <!-- 如果已经包含jQuery UI，则可以省略jQuery UI小部件工厂 -->
    <script src="${cdnPrefix}/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
    <!--  包含模板插件以呈现上传/下载列表  -->
    <script src="${cdnPrefix}/jquery-file-upload/tmpl.min.js"></script>
    <!-- 包含预览图像和图像调整大小功能的加载图像插件 -->
    <script src="${cdnPrefix}/jquery-file-upload/load-image.all.min.js"></script>
    <!-- 包含图像大小调整功能的画布到Blob插件  -->
    <script src="${cdnPrefix}/jquery-file-upload/canvas-to-blob.min.js"></script>
    <!-- blueimp图库脚本 -->
    <script src="${cdnPrefix}/jquery-file-upload/jquery.blueimp-gallery.min.js"></script>
    <!-- 不支持XHR文件上传的浏览器需要Iframe Transport  -->
    <script src="${cdnPrefix}/jquery-file-upload/js/jquery.iframe-transport.js"></script>
    <!-- 基本的文件上传插件 -->
    <script src="${cdnPrefix}/jquery-file-upload/js/jquery.fileupload.js"></script>
    <!-- 文件上传处理插件-->
    <script src="${cdnPrefix}/jquery-file-upload/js/jquery.fileupload-process.js"></script>
    <!-- 文件上传图片预览＆resize插件 -->
    <script src="${cdnPrefix}/jquery-file-upload/js/jquery.fileupload-image.js"></script>
    <!-- 文件上传音频预览插件-->
    <script src="${cdnPrefix}/jquery-file-upload/js/jquery.fileupload-audio.js"></script>
    <!-- 文件上传视频预览插件 -->
    <script src="${cdnPrefix}/jquery-file-upload/js/jquery.fileupload-video.js"></script>
    <!-- 文件上传验证插件 -->
    <script src="${cdnPrefix}/jquery-file-upload/js/jquery.fileupload-validate.js"></script>
    <!-- 文件上传用户界面插件 -->
    <script src="${cdnPrefix}/jquery-file-upload/js/jquery.fileupload-ui.js"></script>
    <!--  包含XDomainRequest传输，用于IE 8和IE 9 - >的跨域文件删除 -->
    <!--[if (gte IE 8)&(lt IE 10)]>
    <script src="${cdnPrefix}/jquery-file-upload/js/cors/jquery.xdr-transport.js"></script>
    <![endif]-->
  </body>
  <script>
$(function () {
    'use strict';

    // Initialize the jQuery File Upload widget:
    $('#cmsArticleForm').fileupload({
        // 取消注释以下内容以发送跨域Cookie:
        //xhrFields: {withCredentials: true},
        url: '${tenantPrefix}/cms/cms-article-upload.do?id=${model.id}'
    });

    // 通过重定向选项启用iframe跨域访问:
    $('#cmsArticleForm').fileupload(
        'option',
        'redirect',
        window.location.href.replace(
            /\/[^\/]*$/,
            '/cors/result.html?%s'
        )
    );

	$('#cmsArticleForm').fileupload('option', {
		// 启用图片大小调整，Android和Opera除外，实际上支持图片大小调整，但不能通过XHR请求发送Blob对象：
		disableImageResize: /Android(?!.*Chrome)|Opera/
			.test(window.navigator.userAgent),
		maxFileSize: 10000000,
		acceptFileTypes: /(\.|\/)(pdf)$/i
	});

	// 上传支持CORS的浏览器的服务器状态检查：
	if ($.support.cors) {
		$.ajax({
			url: '${tenantPrefix}/cms/cms-article-download.do?id=${model.id}',
			type: 'HEAD'
		}).fail(function () {
			$('<div class="alert alert-danger"/>')
				.text('服务器异常，暂时无法上传 - ' +
						new Date())
				.appendTo('#fileupload');
		});
	}

	// Load existing files:
	$('#cmsArticleForm').addClass('fileupload-processing');
	
	$.ajax({
		//取消注释以下内容以发送跨域cookie：
		//xhrFields: {withCredentials: true},
		url: '${tenantPrefix}/cms/cms-article-download.do?id=${model.id}',
		dataType: 'json',
		context: $('#fileupload')[0]
	}).always(function () {
		$(this).removeClass('fileupload-processing');
	}).done(function (result) {
		$(this).fileupload('option', 'done')
			.call(this, $.Event('done'), {result: result});
	});

});
  </script>

</html>
