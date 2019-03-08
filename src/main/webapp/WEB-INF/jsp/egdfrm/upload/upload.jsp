<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp" />
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/egdfrm/js/upload/upload.js"></script>
	<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/egdfrm/common/uploadify/jquery.uploadify.min.js"></script>
<!-- jQuery 2.1.4 -->
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jquery-form/jquery-form.js"></script>
<!-- Bootstrap 3.3.5 -->
<script
	src="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/input-mask/jquery.inputmask.js"></script>

<title>upload</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/common/uploadify/uploadify.css">
<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/css/skins/_all-skins.min.css">
<!-- iCheck -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/iCheck/flat/blue.css">
<!-- Morris chart -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/morris/morris.css">
<!-- jvectormap -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
<!-- Date Picker -->
<!-- Daterange picker -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/daterangepicker/daterangepicker-bs3.css">
<!-- bootstrap wysihtml5 - text editor -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="dist/js/html5shiv.min.js"></script>
        <script src="dist/js/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">



	<!-- Content Wrapper. Contains page content -->

	<!-- Main content -->
	<section class="content">
	<div id="uploader">
		<p>
			<input type="file" name="file_upload" id="file_upload" />
		</p>
		<a href="javascript:$('#file_upload').uploadify('upload','*')">上传</a>&nbsp;
		<a href="javascript:$('#file_upload').uploadify('stop')">取消上传</a>
		<div id="uploader_queue"></div>
		<div id="uploader_msg"></div>
		<div id="uploader_view"></div>
	</div>
	<!-- /.row --> </section>
	<!-- /.content -->

	<!-- /.content-wrapper -->

	<!-- example-modal -->
	
	
	<!-- Add the sidebar's background. This div must be placed
           immediately after the control sidebar -->
	<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->


	<!-- AdminLTE App -->
	<script
		src="${pageContext.request.contextPath}/static/egdfrm/dist/js/app.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script
		src="${pageContext.request.contextPath}/static/egdfrm/dist/js/demo.js"></script>
	<!-- page script -->
</body>
</html>