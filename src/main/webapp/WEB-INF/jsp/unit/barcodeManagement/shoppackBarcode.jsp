<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp"/>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/unit/js/barcodeManagement/shoppackBarcode.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.css">
	<!-- jQuery 2.1.4 -->
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jquery-form/jquery-form.js"></script>
	<!-- Bootstrap 3.3.5 -->
	<script src="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/input-mask/jquery.inputmask.js"></script>
	<title>发运包装条码管理</title>
	<!-- Bootstrap 3.3.5 -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/css/bootstrap.min.css">
	<!-- Font Awesome -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/css/font-awesome.min.css">
	<!-- Ionicons -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/css/ionicons.min.css">
	<!-- Theme style -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/css/AdminLTE.min.css">
	<!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/css/skins/_all-skins.min.css">
	<!-- iCheck -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/iCheck/flat/blue.css">
	<!-- Morris chart -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/morris/morris.css">
	<!-- jvectormap -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
	<!-- Date Picker -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/datepicker/datepicker3.css">
	<!-- Daterange picker -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/daterangepicker/daterangepicker-bs3.css">
	<!-- bootstrap wysihtml5 - text editor -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">

	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
	<script src="dist/js/html5shiv.min.js"></script>
	<script src="dist/js/respond.min.js"></script>
	<![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<form class="form-inline" style="margin-top: 10px;">
					<div class="form-group">
						<label for="barcodeType">发运包装</label>
						<input type="text" class="form-control" id="barcodeType" value="发运包装条码" disabled="disabled"/>
					</div>
					<div class="form-group">
						<label for="productBarcode">当前条码</label>
						<input type="text" class="form-control" id="productBarcode" placeholder="当前条码" readonly="readonly"/>
					</div>
					<div class="form-group">
						<label for="printNum">打印张数</label>
						<input type="text" class="form-control" id="printNum" placeholder="打印张数" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
					</div>
					<button type="button" class="btn btn-info btn-sm" style="margin-left: 30px;" id='barcodeOK' data-backdrop="static"/>
					<i class="glyphicon glyphicon-search"></i>&nbsp;确&nbsp;认&nbsp;
					</button>
					<%--<button type="button" class="btn btn-info btn-sm" style="margin-left: 15px;" id='refreshBarcode' data-backdrop="static"/>
						<i class="glyphicon glyphicon-refresh"></i>&nbsp;刷新条码&nbsp;
					</button>--%>
				</form>
				<div style="width: 100%;margin-top: 50px;padding-bottom: 30px;">
					<span style="font-size:18px;font-weight: bold;">条码打印:</span>
					<div style="width: 100%;">
						<div class="form-inline" style="margin-top: 10px;">
							<div class="form-group">
								<label for="startCode">起始条码</label>
								<input type="text" class="form-control" id="startCode" placeholder="起始条码" readonly="readonly"/>
							</div>
							<div class="form-group">
								<label for="endCode">终止条码</label>
								<input type="text" class="form-control" id="endCode" placeholder="终止条码" readonly="readonly"/>
							</div>
							<button type="button" class="btn btn-success btn-sm" style="margin-left: 30px;" id='printBarcode' data-backdrop="static" data-target="#packageBarcode_generate_modal"/>
							&nbsp;&nbsp;打印(二维码)&nbsp;&nbsp;
							</button>
						</div>
					</div>
				</div>
				<div style="width: 100%;margin-top: 50px;padding-bottom: 30px;">
					<span style="font-size:18px;font-weight: bold;">条码补打:</span>
					<div style="width: 100%;">
						<form class="form-inline" style="margin-top: 10px;">
							<div class="form-group">
								<label for="fycode">发运条码</label>
								<input type="text" class="form-control" id="fycode" placeholder="发运条码" />
								<%--<select class="form-control" id="fycode">
									<option value="" selected="selected">---请选择---</option>
									<c:forEach items="${list}" var="code">
										<option>${code}</option>
									</c:forEach>
								</select>--%>
							</div>
							<button type="button" class="btn btn-success btn-sm" style="margin-left: 30px;" id='print_patch' data-backdrop="static" data-target="#packageBarcode_generate_modal"/>
							&nbsp;&nbsp;条码补打&nbsp;&nbsp;
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
</body>
</html>