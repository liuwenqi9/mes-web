<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp" />
 
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/unit/js/productionManagement/buyFinishedLCL.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.css">
<!-- jQuery 2.1.4 -->
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jquery-form/jquery-form.js"></script>
<!-- Bootstrap 3.3.5 -->
<script
	src="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/input-mask/jquery.inputmask.js"></script>

<title>采购成品拼箱</title>
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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/datepicker/datepicker3.css">
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
	<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="form-inline" style="margin-top: 10px;">
					<div class="form-group">
						<label class="col-sm-3 control-label" style="margin-top: 8px;">箱号:</label>
						<label class="col-sm-3 control-label">
							<input type="text" class="form-control" id="packageBarcodeId">
						</label>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" style="margin-top: 8px;">产品条码:</label>
						<label class="col-sm-3 control-label">
							<input type="text" class="form-control" id="wipBarcodeId">
						</label>
					</div>
					<div class="form-group">
						<label class="col-sm-6 control-label" style="margin-top: 8px;">可包装数量:</label>
						<label class="col-sm-3 control-label">
							<input type="text" class="form-control" id="packageCount" style="width: 80px;" readonly>
						</label>
					</div>
				</div>
				<div class="box-body table-responsive">
					<table id="buyFinishedLCLTable" data-click-to-select="true">
						<thead>
							<tr>
								<th data-field="check" data-checkbox="true"></th>
								<th data-field="BARCODE_TEXT">箱号</th>
								<th data-field="WIP_ENTITY_NAME">工单号</th>
								<th data-field="SEGMENT1">产品编码</th>
								<th data-field="PROD_TYPE">型号</th>
								<th data-field="WIPBARCODE">产品条码</th>
								<th data-field="DESCRIPTION">产品描述</th>
							</tr>
						</thead>
					</table>
					<label class="col-sm-12 control-label">&nbsp;</label>
					<label class="control-label">
						已扫描数量：<input style="border:none;background:none" type="text"  id="scanedCount" value="0" readonly>
					</label> 
					<label class="col-sm-12 control-label">&nbsp;</label>
					<label class="col-sm-1 control-label">
						<button type="button" class="btn btn-danger btn-sm"
							data-backdrop="static" onclick="deleteLCL()">
							<i class="glyphicon glyphicon-trash"></i>&nbsp;&nbsp;删&nbsp;除&nbsp;&nbsp;
						</button>
					</label>
					<label class="col-sm-4 control-label">&nbsp;</label> 
					<label class="col-sm-1 control-label">
						<button type="button" class="btn btn-primary btn-sm"
							data-backdrop="static" onclick="clean()">
							<i class="glyphicon  glyphicon-refresh"></i>&nbsp;&nbsp;清&nbsp;空&nbsp;&nbsp;
						</button>
					</label> 
					<label class="col-sm-4 control-label">&nbsp;</label>
					<label class="col-sm-1 control-label">
						<button type="button" class="btn btn-success btn-sm"
							data-backdrop="static"onclick="commit()">
							<i class="glyphicon glyphicon-ok"></i>&nbsp;&nbsp;提&nbsp;交&nbsp;&nbsp;
						</button>
					</label> 
				</div>
			</div>
		</div>
	</div>
	<!-- /.row --> </section>
	<!-- cleanModal -->
	<div class="modal fade" id="cleanModal" tabindex="-1"
		 role="dialog" aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">
						请确认是否清空？
					</h4>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-danger" id="confirmBtn">清空</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>