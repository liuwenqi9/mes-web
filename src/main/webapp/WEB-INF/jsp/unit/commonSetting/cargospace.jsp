<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp" />
 
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/unit/js/commonSetting/cargospace.js"></script>
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
	

<title>货位管理</title>
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



	<!-- Content Wrapper. Contains page content -->

	<!-- Main content -->
	<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<!-- /.box-header -->
				<div class="box-body table-responsive">
					<div id="cargospace_toolbar" class="btn-group">
						  <div class="row-fluid"> 
							  	 <div  style="padding: 10px 0px 10px;"> 
																				
							  		<label for="line1" class="col-sm-1 control-label">子库:</label>
							  		<label  class="col-sm-2">
							  			<input type="text"  class="form-control"
												id="sublibraryNameCid" name="sublibraryName">
									</label> 
									<label for="line1" class="col-sm-1 control-label">&nbsp;</label>
							  		<label for="line1" class="col-sm-1 control-label">货位:</label>
							  		<label  class="col-sm-2">
							  			<input type="text"  class="form-control"
												id="cargospaceNameCid" name="cargospaceName">
									</label> 
							  		<label  class="col-sm-1">
										<button for="line1" type="button" class="btn btn-info btn-sm" id='cargospaceSearchBtn'
											data-backdrop="static"  />
											<i class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;查&nbsp;&nbsp;询&nbsp;&nbsp;
										</button> 
									</label> 
					  				<label for="line1" class="col-sm-1 control-label">&nbsp;</label>
					  				<label for="line1" class="col-sm-2 control-label">
									      <button for="line1"  type="button" class="btn btn-success btn-sm"
											 data-backdrop="static" id="cargospace_add_btn"
											data-target="#addCargospaceModal">
											<i class="glyphicon glyphicon-plus" ></i>添加
										</button>
										<button for="line1"  type="button" class="btn btn-warning btn-sm"
											data-backdrop="static" data-target="#editCargospaceModal"
											id="cargospace_edit_btn">
											<i class="glyphicon glyphicon-edit"></i>修改
										</button>
									</label>
							</div>
							</div>
					</div>
					<table id="cargospaceTable" data-pagination="true" data-page-size="10"
						data-page-list="[5,10,20,50,100]" data-pagination-first-text="First"
						data-pagination-pre-text="上一页" data-pagination-next-text="下一页"
						data-pagination-last-text="Last" data-locale="zh-CN"
						data-show-columns="true" data-toolbar="#cargospace_toolbar" data-click-to-select="true"
       data-single-select="true">
						<thead>
							<tr>
								<th data-field="check" data-checkbox="true"></th>
								<th data-field="LOCATTION_CODE">货位</th>
								<th data-field="SUBINVENTORY_CODE">ERP子库</th>
								<th data-field="DESCRIPTION">货位描述</th>
								<th data-field="FMT_CREATION_DATE">创建时间</th>
								<th data-field="CREATE_USER">创建者</th>
								<th data-field="FMT_DISABLE_DATE">失效日期</th>
								<th data-field="FMT_LAST_UPDATE_DATE">最后更新日期</th>
								<th data-field="UPDATE_USER">最后更新用户</th>
								<th data-field="STATUS">货位状态</th>
								<th data-field="ISENABLED">ERP子库货位控制</th>
								<th data-field="SUBINVENTORY_DESR" hidden=true>ERP子库描述</th>
							</tr>
						</thead>
					</table>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->

		</div>
		<!-- /.col -->
	</div>
	<!-- /.row --> </section>
	<!-- /.content -->

	<!-- Cargospace add -->
	<div class="modal fade" id="addCargospaceModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">新增货位</h4>
				</div>
				<form  class="form-horizontal" id="addCargospaceForm" method="post">
					<div class="modal-body">
						<div class="form-group ">
							<label for="SUBINVENTORY_CODE_add" class="col-sm-2 control-label">子库</label>
							<div class="col-sm-9 ">
								<input type="text" class="form-control " id="SUBINVENTORY_CODE_add"  name="subinventoryCode"> 
							</div>
							<label for="SUBINVENTORY_CODE_add" class="col-sm-1 control-label" id='subinventory_search_btn'>
							<i class="glyphicon glyphicon-search"></i></label> 
						</div>
						<div class="form-group">
							<label for="SUBINVENTORY_DESR_add" class="col-sm-2 control-label">子库描述</label>
							<div class="col-sm-10">
								<input type="text" class="form-control " id="SUBINVENTORY_DESR_add" readonly> 
							</div>
						</div> 
						<div class="form-group">
							<label for="LOCATTION_CODE_add" class="col-sm-2 control-label">货位</label>
							<div class="col-sm-10">
								<input type="text" class="form-control " id="LOCATTION_CODE_add"  name="locattionCode"  > 
							</div>
						</div>
						<div  class="form-group">
							<label for="DESCRIPTION_add" class="col-sm-2 control-label">货位描述</label>
							<div class="col-sm-10">
								<input type="text" class="form-control " id="DESCRIPTION_add"  name="description"> 
							</div>
						</div>
						<div class="form-group">
							<label for="DISABLE_DATE_add" class="col-sm-2 control-label">失效日期</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="DISABLE_DATE_add"
									name="disableDate" placeholder="失效日期">
								<span id='DISABLE_DATE_add_span_e'></span>
							</div>
						</div> 
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary"
							id="add_cargospace_save_btn">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	
	<!-- Cargospace edit -->
	<div class="modal fade" id="editCargospaceModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">修改货位</h4>
				</div>
				<form  class="form-horizontal" id="editCargospaceForm" method="post">
					<div class="modal-body">
						<div class="form-group ">
							<label for="SUBINVENTORY_CODE_edit" class="col-sm-2 control-label">子库</label>
							<div class="col-sm-9 ">
								<input type="text" class="form-control " id="SUBINVENTORY_CODE_edit"  name="subinventoryCode"   readonly> 
							</div>
						</div>
						<div class="form-group">
							<label for="SUBINVENTORY_DESR_edit" class="col-sm-2 control-label">子库描述</label>
							<div class="col-sm-10">
								<input type="text" class="form-control " id="SUBINVENTORY_DESR_edit"  readonly> 
							</div>
						</div> 
						<div class="form-group">
							<label for="LOCATTION_CODE_edit" class="col-sm-2 control-label">货位</label>
							<div class="col-sm-10">
								<input type="text" class="form-control " id="LOCATTION_CODE_edit"  name="locattionCode" readonly > 
							</div>
						</div>
						<div  class="form-group">
							<label for="DESCRIPTION_edit" class="col-sm-2 control-label">货位描述</label>
							<div class="col-sm-10">
								<input type="text" class="form-control " id="DESCRIPTION_edit"  name="description" > 
							</div>
						</div>
						<div class="form-group">
							<label for="DISABLE_DATE_edit" class="col-sm-2 control-label">失效日期</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="DISABLE_DATE_edit"
									name="disableDate" placeholder="失效日期">
							</div>
						</div> 
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary"
							id="edit_cargospace_save_btn">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
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