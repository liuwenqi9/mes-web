<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp" />
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/unit/js/inventoryManagement/pdDetail.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.css">
	<!-- jQuery 2.1.4 -->
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jquery-form/jquery-form.js"></script>
	<!-- Bootstrap 3.3.5 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/input-mask/jquery.inputmask.js"></script>
	<title>盘点汇总信息</title>
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

<!-- <style type="text/css"> table {border:1px #cccccc dotted;} </style> -->

	<!-- Content Wrapper. Contains page content -->

	<!-- Main content -->
	<section class="content">
		<!-- panel2 --> 
			<div class="panel panel-default">
					<div class="panel-heading"> <h3 class="panel-title">盘点明细信息</h3></div>
					<div class="box"> 
						<!-- detailTable -->
						
						<button style="margin-left: 700px; margin-top: 20px;" class="btn btn-success btn-sm" onclick="history.go(-1);" data-backdrop="static" data-toggle="modal">
							&nbsp;&nbsp;&nbsp;返&nbsp;&nbsp;&nbsp;回&nbsp;&nbsp;&nbsp;
						</button>
						<!-- <button style="margin-left: 800px; margin-top: 30px;" class="btn btn-success btn-sm" id="differ_btn1" data-backdrop="static" data-toggle="modal">
							&nbsp;差异处理&nbsp;
						</button> -->
						<button style="margin-left: 20px; margin-top: 20px;" class="btn btn-success btn-sm" id="excel_btn1" data-backdrop="static" data-toggle="modal">
							&nbsp;导出EXCEL&nbsp;
						</button><br/>
						<table id="detailTable"></table>
						<input type="hidden" name='headerId' id='headerId' value=${ headerId} >
						<input type="hidden" name='organizationId' id='organizationId' value=${ organizationId} >
						
						
						<button style="margin-left: 700px; margin-top: 20px;" class="btn btn-success btn-sm" onclick="history.go(-1);" data-backdrop="static" data-toggle="modal">
							&nbsp;&nbsp;&nbsp;返&nbsp;&nbsp;&nbsp;回&nbsp;&nbsp;&nbsp;
						</button>
						<!-- <button style="margin-left: 800px; margin-top: 30px;" class="btn btn-success btn-sm" id="differ_btn" data-backdrop="static" data-toggle="modal">
							&nbsp;差异处理&nbsp;
						</button> -->
						<button style="margin-left: 20px; margin-top: 20px;" class="btn btn-success btn-sm" id="excel_btn" data-backdrop="static" data-toggle="modal">
							&nbsp;导出EXCEL&nbsp;
						</button><br/>
					<!-- /.box-body -->
				   
			</div>
		
	</section>
	<!-- /.content --> 
	
	<!-- confirm  cancel -->
	<div class="modal fade" id="confirmAndCancelModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<form action="" id="xxx" method="post">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="exampleModalLabel">
							请确认是否导出所有盘点明细？
						</h4>
					</div> 
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary" id="confirm_btn">确定</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	
	
<!-- DataTables -->
<script src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/datatables/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/fastclick/fastclick.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/static/egdfrm/dist/js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${pageContext.request.contextPath}/static/egdfrm/dist/js/demo.js"></script>
<!-- page script -->
</body>
</html>