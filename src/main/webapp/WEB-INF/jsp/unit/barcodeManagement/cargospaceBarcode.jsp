<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp" />
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/unit/js/barcodeManagement/cargospaceBarcode.js"></script>
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

<title>产品条码打印</title>
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
<style>
#search_bar_input>*{
margin:10px
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



	<!-- Content Wrapper. Contains page content -->

	<!-- Main content  inspection-->
	<section class="content">
	
		<div class="col-xs-12">
			
				<!-- /.box-header -->
				
					<div style=" vertical-align: middle;text-align:right;height:50px;" > 
					
					
					 
					
					<div id="search_bar_input" style="display:inline-block;float:left;text-align:left;width:50%;">  
												  
					<label>子库 </label>
					<input type="text" id="input_sublib" class="  form-control " styLe="width:30%;display:inline" placeholder="子库"  > 
					<label>货位 </label>
					<input type="text" id="input_cargospace" class="  form-control  " styLe="width:30%;display:inline" placeholder="货位" > 
					 </div> 							  
					<div  style="display:inline-block;height:100%;"> 
									<button type="button" class="btn btn-info btn-sm" style="margin-top:10px;" id='cargospaceBarcode_search_btn'
										data-backdrop="static"  />
										<i class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;查&nbsp;&nbsp;询&nbsp;&nbsp;
									</button> 
									<button type="button" class="btn btn-success btn-sm" style="margin-top:10px;" id='cargospaceBarcode_print_btn'
										data-backdrop="static" data-target="#cargospaceBarcode_generate_modal"/> &nbsp;&nbsp;条码打印&nbsp;&nbsp;
									</button>  
								      
								</div> 					 
					
					
					
					 
						
						      
			   		</div> 
				
				
					<table id="cargospaceBarcodeTable"  >
						
					</table>
				
				<!-- /.box-body -->
			</div>
			<!-- /.box -->

		
		<!-- /.col -->
	</div>
	<!-- /.row --> 
	</section>
	<!-- /.content -->
	<!-- 条码生成  预览 -->
	<div class="modal fade" id="cargospaceBarcode_generate_modal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<!-- <button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button> -->
					<h3 class="modal-title" id="exampleModalLabel">条码生成列表</h3>
				</div>
				<div class="modal-body">
					<div id="66_toolbar" class="btn-group">  
						<form action="" id='xx'>
							  <div class="form-group "> 
							  	<label for="line1" class="col-sm-2 control-label">打印机：</label>
						  		<label for="line1" class="col-sm-4 control-label"><select type='text' class="form-control" id='checkType' name="checkType" >
															<option value='0'>请选择</option>
															<option value='V1'>PDF V1</option>
															<option value='V2'>PDF V2</option>
															</select> </label> 
								<label for="line1" class="col-sm-2 control-label">份数:</label>
								<label for="line1" class="col-sm-4 control-label"><input type="text" class="form-control" value='1' placeholder="份数" id="checkNumber"
															name="checkNumber"> </label> 
							  </div>
							     
						</form>  
					</div>
				
				   <table id="1Table" data-toolbar="#66_toolbar" >
						<thead>
							<tr> 
								<th data-field="data0" >货位（条码）</th>   
							</tr>
						</thead>
					</table>  
				</div>
				<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary"
							id="cargospaceBarcode_generate_save_btn">条码打印</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 条码生成  预览 -->
	
	<!-- /.content-wrapper --> 
	 
	<!-- Add the sidebar's background. This div must be placed
           immediately after the control sidebar -->
	<div class="control-sidebar-bg"></div>
	<!-- ./wrapper -->


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
</body>
</html>