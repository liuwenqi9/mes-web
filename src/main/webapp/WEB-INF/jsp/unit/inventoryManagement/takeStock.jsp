<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp" />
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/unit/js/inventoryManagement/takeStock.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.css">
	<!-- jQuery 2.1.4 -->
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jquery-form/jquery-form.js"></script>
	<!-- Bootstrap 3.3.5 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/input-mask/jquery.inputmask.js"></script>
	<title>盘点</title>
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
				<div class="panel-heading"> <h3 class="panel-title">生成盘点报表</h3></div>
				<div class="box">
				 <div class="form-inline" style="margin-top: 20px; margin-left: 10px;">
					<div class="form-group">
						<label for=subpool style="margin-left: 20px;" >子库：</label>
						<input type="text" class="form-control" id="subpool" style="width: 80px;" placeholder="子库"/>
					</div>
					<button class="btn btn-success btn-sm" id="subpool_btn" data-backdrop="static" data-toggle="modal">
						...
					</button> 
					 <div class="form-group">
						<label for="location_start" style="margin-left: 20px;">开始货位：</label>
						<input type="text" class="form-control" id="location_start" style="width: 80px;" placeholder="开始货位"/>
					</div>
					<button class="btn btn-success btn-sm" id="location_start_btn" data-backdrop="static" data-toggle="modal">
						...
					</button> 
					 <div class="form-group">
						<label for="location_end" style="margin-left: 10px;">至</label>
						<input type="text" class="form-control" id="location_end" style="margin-left: 10px;width: 80px;" placeholder="结束货位"/>
					</div>
					<button class="btn btn-success btn-sm" id="location_end_btn" data-backdrop="static" data-toggle="modal">
						...
					</button>
					
					<div class="form-group">
						<label for="item" style="margin-left: 20px;">物料编码：</label>
						<input type="text" class="form-control" id="item" style="width: 150px;" placeholder="物料编码"/>
					</div>
					<button class="btn btn-success btn-sm" id="item_btn" data-backdrop="static" data-toggle="modal">
						...
					</button>
					
					<button style="margin-left: 100px;" class="btn btn-success btn-sm" id="generate_btn" data-backdrop="static" data-toggle="modal">
						生成盘点报表
					</button>
				</div>    
					 
				</div>
					<!-- /.box-body -->
				</div>
				<!-- /.box -->
			</div>
			
			<!-- panel2 -->
			 
			<div class="panel panel-default">
				<div class="panel-heading"> <h3 class="panel-title">盘点历史记录：</h3></div>
				<div class="box">
				  <div class="form-inline" style="margin-top: 25px; margin-left: 10px;">
						<div class="form-group">
							<label for="date" style="margin-left: 5px;" >盘点日期：</label>
							<input type="text" class="form-control" id="begin_date" style="margin-left: 10px; width:120px;" />
							<label for="date" style="margin-left: 2px;" >至</label>
							<input type="text" class="form-control" id="end_date" style="margin-left: 2px; width:120px;" />
						</div> 
						 <div class="form-group">
							<label for="status" style="margin-left: 20px;">状态</label>
							<select class="form-control" id="status">
								<option value="" selected="selected">---请选择---</option>
								<option value="Y"  >Y</option>
								<option value="N"  >N</option> 
							</select>	
						</div>  
						<button style="margin-left: 70px;" class="btn btn-success btn-sm" id="search_btn" data-backdrop="static" data-toggle="modal">
							&nbsp;查&nbsp;&nbsp;&nbsp;&nbsp;询&nbsp;
						</button>
						<button style="margin-left: 70px;" class="btn btn-success btn-sm" id="pd_number_udpate" data-backdrop="static" data-toggle="modal">
							&nbsp;合&nbsp;&nbsp;&nbsp;&nbsp;并&nbsp;
						</button>
						<br/><br/><br/>
						
						<!-- table -->
						<table id="pdTable"></table>
				 </div>  
					<!-- /.box-body -->
				</div>
				<!-- /.box -->
			</div>
	
		
	</section>
	<!-- /.content -->

	<!--  （Modal）my -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">

				</h4>
			</div>
			<div class="modal-body">
				<div class="form-inline" style="margin-bottom: 10px;">
					<div class="form-group">
						<input type="text" class="form-control" id="subpoolSearch" placeholder="子库" />
					</div>
					<button type="button" class="btn btn-info btn-sm" id='search' data-backdrop="static"/>
						<i class="glyphicon glyphicon-search"></i>查询
					</button>
				</div>
				<table id="chooseTable"></table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" id="choose_ok">确认选择</button>
			</div>
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