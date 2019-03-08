<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp" />
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/unit/js/productionManagement/weigh.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.css">
	<!-- jQuery 2.1.4 -->
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jquery-form/jquery-form.js"></script>
	<!-- Bootstrap 3.3.5 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/input-mask/jquery.inputmask.js"></script>
	<title>成品工单标准包装箱重量维护</title>
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
			<!-- panel1 -->
			 
			<div class="panel panel-default">
				<div class="panel-heading"> <h3 class="panel-title">成品工单标准包装箱重量维护</h3></div>
				<div class="box">
					<div class="form-inline" style="margin-top: 20px; margin-left: 10px;">	
						<div class="form-group">
							<label for=xx style="margin-left: 5px;" >工单号</label>
							<input type="text" class="form-control" id="wip_entity_name1" style="width: 150px;"  />
						</div>
						<button class="btn btn-success btn-sm" id="comfrm" style="margin-left: 10px;" data-backdrop="static" data-toggle="modal">
							&nbsp;确&nbsp;&nbsp;&nbsp;&nbsp;认&nbsp;
						</button>
						<button class="btn btn-danger btn-sm" id="clear" style="margin-left: 10px;" data-backdrop="static" data-toggle="modal">
							&nbsp;清&nbsp;&nbsp;&nbsp;&nbsp;空&nbsp;
						</button>
					</div>
					 <div class="form-inline" style="margin-top: 20px; margin-left: 10px;">
						<div class="form-group">
							<label for=xx style="margin-left: 5px;" >工单号</label>
							<input type="text" class="form-control" id="wip_entity_name" style="width: 150px;" readonly />
						</div>
						
						 <div class="form-group">
							<label for="bb" style="margin-left: 20px;">标准毛重(KG)</label>
							<input type="text" class="form-control" id="s_gross_weight" style="width: 100px;" readonly />
						</div> 
						 <div class="form-group">
							<label for="bb" style="margin-left: 10px;">实际毛重(KG)</label>
							<input type="text" class="form-control" id="gross_weight" style="width: 100px;" /> 
						</div>
						<div class="form-group">
							<label for="bb" style="margin-left: 10px;">外箱重量</label>
							<input type="text" class="form-control" id="pack_weight" style="width: 100px;" /> 
						</div>
					 	 <input type="hidden"  id="wip_entity_id" />
					 	<shiro:hasPermission name="weighMaintenanceController:save">
						<button style="margin-left: 100px;" class="btn btn-success btn-sm" id="save_btn" data-backdrop="static" data-toggle="modal">
							&nbsp;保&nbsp;&nbsp;&nbsp;&nbsp;存&nbsp;
						</button>
						</shiro:hasPermission> 
						</br></br>
					</div>    
					 
				</div>
					<!-- /.box-body -->
				</div>
				<!-- /.box -->
			</div>
			
			<!-- panel2 -->
			 
			<div class="panel panel-default">
				<div class="panel-heading"> <h3 class="panel-title">成品工单标准包装箱重量查询</h3></div>
				<div class="box">
				  <div class="form-inline" style="margin-top: 25px; margin-left: 10px;">
						 <div class="form-group">
							<label for="name" style="margin-left: 5px;">工单号</label>
							<input type="text" class="form-control" id="name" style="margin-left: 10px; width:140px;" /> 	
						</div> 
						<div class="form-group">
							<label for="date" style="margin-left: 5px;" >维护日期：</label>
							<input type="text" class="form-control" id="start_date" style="margin-left: 10px; width:100px;" />
							<label for="date" style="margin-left: 2px;" >至</label>
							<input type="text" class="form-control" id="end_date" style="margin-left: 2px; width:100px;" />
						</div> 
						 <div class="form-group">
							<label for="name" style="margin-left: 5px;">物料编码</label>
							<input type="text" class="form-control" id="segment1" style="margin-left: 10px; width:130px;" /> 	
						</div>
						<button style="margin-left: 50px;" class="btn btn-success btn-sm" id="search_btn2" data-backdrop="static" data-toggle="modal">
							&nbsp;查&nbsp;&nbsp;&nbsp;&nbsp;询&nbsp;
						</button>
						<shiro:hasPermission name="weighMaintenanceController:export">
						<button style="margin-left: 100px;" class="btn btn-success btn-sm" id="export" data-backdrop="static" data-toggle="modal">
							&nbsp;导出excel&nbsp;
						</button>
						</shiro:hasPermission>
						<br/><br/><br/>
						
						<!-- table -->
						<table id="weighTable"></table>
				 </div>  
					<!-- /.box-body -->
				</div>
				<!-- /.box -->
			</div>
	
		
	</section>
	<!-- /.content -->

	


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