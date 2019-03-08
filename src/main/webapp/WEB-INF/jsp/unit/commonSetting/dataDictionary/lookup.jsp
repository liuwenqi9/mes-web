<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp" />
<script type="text/javascript" charset="UTF-8" src="/MES/static/egdfrm/js/validatebox_bootstrap.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/unit/js/commonSetting/dataDictionary/lookup.js"></script>
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

<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/daterangepicker/daterangepicker.js"></script>

<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/daterangepicker/moment.min.js"></script>
	
	
	
	
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>



<title>数据字典管理</title>
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

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/datetimepicker/bootstrap-datetimepicker.min.css">

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
		<div class=" col-sm-6">
			<div class="panel panel-default">
				<div class="panel-heading">数据字典类型</div>
				<div class="box">
					<!-- /.box-header -->
					<div class="box-body table-responsive">
						<div id="lookup_type_toolbar" class="btn-group">
							<button type="button" class="btn btn-success btn-sm"
								data-toggle="modal" data-backdrop="static"
								data-target="#addLookupTypeModal">
								<i class="glyphicon glyphicon-plus"></i>添加
							</button>
							<button type="button" class="btn btn-warning btn-sm"
								data-backdrop="static" data-target="#editRoleModal"
								onclick="editLookupType()">
								<i class="glyphicon glyphicon-edit"></i>修改
							</button>
							<button type="button" class="btn btn-danger btn-sm"
								data-backdrop="static" data-target="#deleteRoleModal"
								onclick="deleteLookupType()">
								<i class="glyphicon glyphicon-trash"></i>删除
							</button>
						</div>
						<table id="lookupTypeTable" data-pagination="true" data-page-size="5"
							data-page-list="[5,10,15]" data-pagination-first-text="First"
							data-pagination-pre-text="上一页" data-pagination-next-text="下一页"
							data-pagination-last-text="Last" data-locale="zh-CN"
							data-show-columns="true" data-click-to-select="true"
							data-single-select="true" data-toolbar="#lookup_type_toolbar">
							<thead>
								<tr>
									<th data-field="check" data-checkbox="true"></th>
									<th data-field="SOURCE_TYPE">来源系统类型MES/CRM</th>
									<th data-field="LOOKUP_TYPE">类型</th>
									<th data-field="DESCRIPTION">说明</th>
									<th data-field="CREATED_BY">创建人员</th>
									<th data-field="CREATION_DATE">创建日期</th>
									<th data-field="LAST_UPDATED_BY">最后修改人员</th>
									<th data-field="LAST_UPDATE_DATE">最后修改日期</th>
								</tr>
							</thead>
						</table>
					</div>
					<!-- /.box-body -->
				</div>
				<!-- /.box -->
			</div>
		</div>
		<!-- /.col -->
		<div class=" col-sm-6">
			<div class="panel panel-default">
				<div class="panel-heading">数据字典</div>
				<div style="width: 100%">
					<button type="button" class="btn btn-success btn-sm"
						id="addLookup_btn" style="margin-left: 5px;">添加</button>
					<button type="button" class="btn btn-warning btn-sm"
						id="editLookup_btn" style="margin-left: 5px;">修改</button>
					<button type="button" class="btn btn-danger btn-sm"
						id="deleteLookup_btn" style="margin-left: 5px;">删除</button>
				</div>
				<table id="lookupTable" data-pagination="true" data-page-size="5"
							data-page-list="[5,10,15]" data-pagination-first-text="First"
							data-pagination-pre-text="上一页" data-pagination-next-text="下一页"
							data-pagination-last-text="Last" data-locale="zh-CN"
							data-show-columns="true" data-click-to-select="true"
							data-single-select="true" data-toolbar="#">
							<thead>
								<tr>
									<th data-field="check" data-checkbox="true"></th>
									<th data-field="LOOKUP_TYPE">数据字典类型</th>
									<th data-field="LOOKUP_CODE">数据字典码</th>
									<th data-field="DESCRIPTION">描述</th>
									<th data-field="ENABLED_FLAG">是否有效</th>
									<th data-field="START_DATE_ACTIVE">开始生效日期</th>
									<th data-field="END_DATE_ACTIVE">结束日期</th>
									<th data-field="CREATED_BY">创建人员</th>
									<th data-field="CREATION_DATE">创建日期</th>
									<th data-field="LAST_UPDATED_BY">最后修改人员</th>
									<th data-field="LAST_UPDATE_DATE">最后修改日期</th>
								</tr>
							</thead>
						</table>
			</div>
		</div>
	</div>
	<!-- /.row --> </section>
	<!-- /.content -->

	<!-- /.content-wrapper -->

	<!-- example-modal -->
	
	
	<!-- lookupType add modal-->
	<div class="modal fade" id="addLookupTypeModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">添加数据字典类型</h4>
				</div>
				<form  class="form-horizontal" id="addLookupTypeForm" method="post">
					<div class="modal-body">
						<div class="form-group ">
							<label for="sourceType_add" class="col-sm-2 control-label">来源系统类型MES/CRM:</label>
							<div class="col-sm-10 ">
								<input type="text" class="form-control " id="sourceType_add"
									name="sourceType" placeholder="MES/CRM">
								<span id='sourceType_add_span_a'></span> 
							</div>
						</div>
						<div  class="form-group">
							<label for="lookupType_add" class="col-sm-2 control-label">类型:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="lookupType_add"
									name="lookupType" placeholder="类型">
									<span id='lookupType_add_span_b'></span> 
							</div>
						</div>
						<div class="form-group">
							<label for="description_add" class="col-sm-2 control-label">说明:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="description_add"
									name="description" placeholder="说明">
								<span id='description_add_span_c'></span>
							</div>
						</div>
						

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary"
							id="addLookupType_save_btn">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	
	<!-- lookupType edit modal-->
	<div class="modal fade" id="editLookupTypeModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">数据字典类型修改</h4>
				</div>
				<form class="form-horizontal" id="editLookupTypeForm" method="post">
					<div class="modal-body">
						<div class="form-group ">
							<label for="sourceType_edit" class="col-sm-2 control-label">来源系统类型MES/CRM:</label>
							<div class="col-sm-10 ">
								<input type="text" class="form-control " id="sourceType_edit"
									name="sourceType" placeholder="MES/CRM">
							</div>
						</div>
						<div  class="form-group">
							<label for="lookupType_add" class="col-sm-2 control-label">类型:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="lookupType_edit"
									name="lookupType" placeholder="类型">
							</div>
						</div>
						<div class="form-group">
							<label for="description_add" class="col-sm-2 control-label">说明:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="description_edit"
									name="description" placeholder="说明">
								<span id='description_add_span_c'></span>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary"
							id="editLookupType_save_btn">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	
	<!-- delete lookupType modal-->
	<div class="modal fade" id="deletelookupTypeModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<form action="" id="deletelookupTypeForm" method="post">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="exampleModalLabel">
							请确认是否删除数据字典类型为(<a id="del_lookupType" name="lookupType"></a>)的记录？
						</h4>
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-danger" id="delUser_btn">删除</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	
	<!-- lookup add modal-->
	<div class="modal fade" id="addLookupModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">添加数据字典</h4>
				</div>
				<form  class="form-horizontal" id="addLookupForm" method="post">
					<div class="modal-body">
						
						<div  class="form-group">
							<label for="lookup_lookupType_add" class="col-sm-2 control-label">数据字典类型:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="lookup_lookupType_add"
									name="lookupType" placeholder="数据字典类型">
									<span id='lookup_lookupType_add_span_a'></span> 
							</div>
						</div>
						<div  class="form-group">
							<label for="lookupCode_add" class="col-sm-2 control-label">数据字典码:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="lookupCode_add"
									name="lookupCode" placeholder="数据字典码">
									<span id='lookupCode_add_span_b'></span> 
							</div>
						</div>
						
						<div class="form-group ">
							<label for="enabledFlag_add" class="col-sm-2 control-label">是否有效:</label>
							<div class="col-sm-10 ">
								<select class="form-control" id="enabledFlag_edit" name="enabledFlag">
									<option value='Y'>Y</option>
									<option value='N'>N</option>
									</select>
								<span id='enabledFlag_add_span_c'></span> 
							</div>
						</div>
						
						<div class="form-group">
							<label for="lookup_description_add" class="col-sm-2 control-label">描述:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="lookup_description_add"
									name="description" placeholder="描述">
								<span id='lookup_description_add_span_d'></span>
							</div>
						</div>
						<div class="form-group">
							<label for="startDateActive_add" class="col-sm-2 control-label">开始生效日期:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="startDateActive_add"
									name="startDateActive" placeholder="开始生效日期">
								<span id='startDateActive_add_span_e'></span>
							</div>
						</div>
						<div class="form-group">
							<label for="endDateActive_add" class="col-sm-2 control-label">结束日期:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="endDateActive_add"
									name="endDateActive" placeholder="结束日期">
								<span id='endDateActive_add_span_f'></span>
							</div>
						</div>
						

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary"
							id="addLookup_save_btn">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<!-- lookup edit modal-->
	<div class="modal fade" id="editLookupModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">编辑数据字典</h4>
				</div>
				<form  class="form-horizontal" id="editLookupForm" method="post">
					<div class="modal-body">
						<div  class="form-group">
							<label for="lookup_lookupType_edit" class="col-sm-2 control-label">数据字典类型:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="lookup_lookupType_edit"
									name="lookupType" placeholder="数据字典类型">
									<span id='lookup_lookupType_edit_span_a'></span> 
							</div>
						</div>
						<div  class="form-group">
							<label for="lookupCode_edit" class="col-sm-2 control-label">数据字典码:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="lookupCode_edit"
									name="lookupCode" placeholder="数据字典码">
									<span id='lookupCode_edit_span_b'></span> 
							</div>
						</div>
						
						<div class="form-group ">
							<label for="enabledFlag_edit" class="col-sm-2 control-label">是否有效:</label>
							<div class="col-sm-10 ">
								
									<select class="form-control" id="enabledFlag_edit" name="enabledFlag">
									<option value='Y'>Y</option>
									<option value='N'>N</option>
									</select>
								<span id='enabledFlag_edit_span_c'></span> 
							</div>
						</div>
						
						<div class="form-group">
							<label for="lookup_description_edit" class="col-sm-2 control-label">描述:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="lookup_description_edit"
									name="description" placeholder="描述">
								<span id='lookup_description_edit_span_d'></span>
							</div>
						</div>
						<div class="form-group">
							<label for="startDateActive_edit" class="col-sm-2 control-label">开始生效日期:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="startDateActive_edit"
									name="startDateActive" placeholder="开始生效日期">
								<span id='startDateActive_edit_span_e'></span>
							</div>
						</div>
						<div class="form-group">
							<label for="endDateActive_edit" class="col-sm-2 control-label">结束日期:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="endDateActive_edit"
									name="endDateActive" placeholder="结束日期">
								<span id='endDateActive_edit_span_f'></span>
							</div>
						</div>
						

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary"
							id="editLookup_save_btn">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	
	<!-- delete lookup modal-->
	<div class="modal fade" id="deletelookupTypeModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<form action="" id="deletelookupForm" method="post">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="exampleModalLabel">
							请确认是否删除数据字典码为(<a id="del_lookupCode" name="lookupCode"></a>)的记录？
						</h4>
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-danger" id="delUser_btn">删除</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	
	
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