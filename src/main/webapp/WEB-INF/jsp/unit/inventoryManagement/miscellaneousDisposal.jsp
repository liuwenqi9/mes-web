<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp" />
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/unit/js/inventoryManagement/miscellaneousDisposal.js"></script>
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

<title>杂项事务处理</title>
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
<style type="text/css">
.no_padding{padding-left:1px;padding-right: 1px }
.char_center{text-align:center}
@media (min-width: 768px) {
  .my-col-sm-0-5,.my-col-sm-1-5,.my-col-sm-1,  .my-col-sm-2,  .my-col-sm-3,  .my-col-sm-4, .my-col-sm-5, .my-col-sm-6,  .my-col-sm-7,   .my-col-sm-8,   .my-col-sm-9,   .my-col-sm-10,   .my-col-sm-11,   .my-col-sm-12 {
    float: left;
  }
  .my-col-sm-0-5{width: 4.16666666%}

  .my-col-sm-1-5{width: 12.49999999%}

  .my-col-sm-12 {
    width: 100%;
  }
  .my-col-sm-11 {
    width: 91.66666667%;
  }
  .my-col-sm-10 {
    width: 83.33333333%;
  }
  .my-col-sm-9 {
    width: 75%;
  }
  .my-col-sm-8 {
    width: 66.66666667%;
  }
  .my-col-sm-7 {
    width: 58.33333333%;
  }
  .my-col-sm-6 {
    width: 50%;
  }
  .my-col-sm-5 {
    width: 41.66666667%;
  }
  .my-col-sm-4 {
    width: 33.33333333%;
  }
  .my-col-sm-3 {
    width: 25%;
  }
  .my-col-sm-2 {
    width: 16.66666667%;
  }
  .my-col-sm-1 {
    width: 8.33333333%;
  }
</style>
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
	<div  class="row">
		<div class="col-xs-12">
			<div class="box">
				<!-- /.box-header -->
				<div class="box-body table-responsive">
				<!--  toolbar-->
					<div id="miscellaneousDisposal_table" class="btn-group" style="width:100%">     
<!-- 						<form id='miscellaneousDisposal_form' > 
 -->					  				<label class="col-sm-10 control-label">&nbsp;</label>
					  				<label class="col-sm-1 control-label">
										<button type="button" class="btn btn-success btn-sm"
											data-backdrop="static" id="addRow_btn" >
											<i class="glyphicon glyphicon-plus"></i>添加行
										</button>
									</label>
					  				<label class="col-sm-1 control-label">
									    <button  type="button" class="btn btn-info btn-sm"
											 data-backdrop="static" id="miscellaneousDisposal_commit_btn">
											<i class="glyphicon glyphicon-floppy-disk" ></i>提交
										</button>
									</label>
					<div >
					<label class="my-col-sm-1-5 control-label char_center">事务处理类型</label> 
					<label class="col-sm-2 control-label char_center">来源</label> 
					<label class="my-col-sm-0-5 control-label char_center">子库</label> 
					<label class="my-col-sm-0-5 control-label char_center">货位</label> 
					<label class="my-col-sm-1-5 control-label char_center">产品条码</label> 
					<label class="my-col-sm-0-5 control-label char_center">数量</label> 
					<label class="my-col-sm-1-5 control-label char_center">编码</label> 
					<label class="col-sm-2 control-label char_center">描述</label> 
					<label class="col-sm-1 control-label char_center">备注</label> 
					<label class="col-sm-1 control-label char_center">删除</label>
					</div>

<!-- 										</form>   	  
 -->					</div> <!-- ./ toolbar-->
				
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->

		</div>
		<!-- /.col -->
	</div>
	<!-- /.row --> </section>
	<!-- /.content -->


	<!-- DataTables -->
	<script
		src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/datatables/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/datatables/dataTables.bootstrap.min.js"></script>
	<!-- SlimScroll -->
	<script
		src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	<script
		src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/fastclick/fastclick.min.js"></script>
	<!-- AdminLTE App -->
	<script
		src="${pageContext.request.contextPath}/static/egdfrm/dist/js/app.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script
		src="${pageContext.request.contextPath}/static/egdfrm/dist/js/demo.js"></script>
	<!-- page script -->
	<jsp:include page="/WEB-INF/jsp/unit/common/commonSelectModal.jsp" />
</body>
</html>