<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp" />
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/egdfrm/js/function/function.js"></script>
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
	src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/treeview/js/bootstrap-treeview.js"></script>

<title>用户维护</title>
<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/css/bootstrap.min.css">
	
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/treeview/css/bootstrap-treeview.css">
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
	href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/touchspin/jquery.bootstrap-touchspin.min.css">
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
				<div class="panel-heading">系统栏目列表</div>
				<div style="width: 100%">
					<button type="button" class="btn btn-success btn-sm"
						style="margin-left: 5px;" id="functionAdd_btn">添加</button>
					<button type="button" class="btn btn-warning btn-sm" id="functionEdit_btn">修改</button>
					<button type="button" class="btn btn-danger btn-sm" id="functionDelete_btn">删除</button>
				</div>
				<div style="width: 100%" id="sysFunction_tree"></div>
			</div>
		</div>


		<!-- Default panel contents -->
		<div class=" col-sm-6">
			<div class="panel panel-default ">
				<div class="panel-heading">栏目详细信息</div>
				<div class="panel-body">
					<dl class="dl-horizontal" id="functionDets_dl">
						
					</dl>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">关联的权限列表</div>
				<div class="box">
					<!-- /.box-header -->
					<div class="box-body table-responsive">
						<div id="functionPurview_toolbar">
							<button type="button" class="btn btn-success btn-sm"
								 data-backdrop="static" id="function_purview_add_btn"
								data-target="#addPurviewModal">
								<i class="glyphicon glyphicon-plus" ></i>添加
							</button>
							<button type="button" class="btn btn-warning btn-sm"
								data-backdrop="static" data-target="#editPurviewModal"
								id="function_purview_edit_btn">
								<i class="glyphicon glyphicon-edit"></i>修改
							</button>
							<button type="button" class="btn btn-danger btn-sm"
								data-backdrop="static" data-target="#deletePurviewModal"
								id="function_purview_delete_btn">
								<i class="glyphicon glyphicon-trash"></i>删除
							</button>
						</div>
						<table id="functionPurviewTable" data-click-to-select="true"
       data-single-select="true" data-toolbar="#functionPurview_toolbar">
							<thead>
								<tr>
									<th data-field="check" data-checkbox="true"></th>
									<th data-field="purviewName">权限名称</th>
									<th data-field="purviewCode">权限代码</th>
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

	</div>
	<!-- /.row --> </section>
	<!-- /.content -->

	<!-- /.content-wrapper -->
<!-- example-modal -->
	<div class="modal fade" id="addFunctionModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">新增栏目</h4>
				</div>
				<form class="form-horizontal" id="addFunctionForm" method="post">
					<div class="modal-body">
						<div id='functionCode_add_a' class="form-group">
							<label for="functionCode_add" class="col-sm-2 control-label">功能代码</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="functionCode_add"
									name="functionCode" placeholder="功能代码">
									<span id='functionCode_add_span_a'></span>
							</div>
						</div>
						<div id='functionName_add_b' class="form-group">
							<label for="functionName_add" class="col-sm-2 control-label">功能名称</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="functionName_add"
									name="functionName" placeholder="功能名称">
									<span id='functionName_add_span_b'></span>
							</div>
						</div>
						<div class="form-group">
							<label for="parentFunctionCode_add" class="col-sm-2 control-label">上级代码</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="parentFunctionCode_add"
									name="parentFunctionCode" placeholder="上级代码" readonly>
							</div>
						</div>
						<div class="form-group">
							<label for="status_add" class="col-sm-2 control-label">功能状态</label>
							<div class="col-sm-10">
								<select class="form-control" id="status_add" name="status">
									<option value="1">启用</option>
									<option value="0">停用</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="sort_add" class="col-sm-2 control-label">功能排序</label>
							<div class="col-sm-10">
								<input type="text" id="sort_add" name="sort">
							</div>
						</div>
						<div class="form-group">
							<label for="levelNo_add" class="col-sm-2 control-label">功能级别</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="levelNo_add"
									name="levelNo" placeholder="功能级别" readonly>
							</div>
						</div>
						<div class="form-group">
							<label for="requestUrl_add" class="col-sm-2 control-label">请求路径</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="requestUrl_add"
									name="requestUrl" placeholder="请求路径">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary"
							id="addFunction_save_btn">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<!-- example-modal -->
	<div class="modal fade" id="editFunctionModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">修改栏目</h4>
				</div>
				<form class="form-horizontal" id="editFunctionForm" method="post">
					<div class="modal-body">
						<div class="form-group">
							<label for="functionCode_edit" class="col-sm-2 control-label">功能代码</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="functionCode_edit" readonly
									name="functionCode" placeholder="功能代码">
							</div>
						</div>
						<div class="form-group">
							<label for="functionName_edit" class="col-sm-2 control-label">功能名称</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="functionName_edit"
									name="functionName" placeholder="功能名称">
							</div>
						</div>
						<div class="form-group">
							<label for="parentFunctionCode_edit" class="col-sm-2 control-label">上级代码</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="parentFunctionCode_edit"
									name="parentFunctionCode" placeholder="上级代码" readonly>
							</div>
						</div>
						<div class="form-group">
							<label for="status_edit" class="col-sm-2 control-label">功能状态</label>
							<div class="col-sm-10">
								<select class="form-control" id="status_edit" name="status">
									<option value="0">停用</option>
									<option value="1">启用</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="sort_edit" class="col-sm-2 control-label">功能排序</label>
							<div class="col-sm-10">
								<input type="text"  id="sort_edit"
									name="sort">
							</div>
						</div>
						<div class="form-group">
							<label for="levelNo_edit" class="col-sm-2 control-label">功能级别</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="levelNo_edit"
									name="levelNo" placeholder="功能级别">
							</div>
						</div>
						<div class="form-group">
							<label for="requestUrl_edit" class="col-sm-2 control-label">请求路径</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="requestUrl_edit"
									name="requestUrl" placeholder="请求路径">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary"
							id="editFunction_save_btn">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="deleteFunctionModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<form action="" id="deleteFunctionForm" method="post">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="exampleModalLabel">
							请确认是否删除栏目(<a id="del_functionName" name="functionName"></a>)？该栏目子节点将被一起删除，删除后将无法恢复！
						</h4>
						<input type="hidden" id="del_functionCode" name="functionCode">
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-danger" id="delFunction_btn">删除</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<!-- example-modal -->
	<div class="modal fade" id="editPurviewModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">权限修改</h4>
				</div>
				<form class="form-horizontal" id="editPurviewForm" method="post">
					<div class="modal-body">
						<div class="form-group">
							<label for="pur_functionCode_edit" class="col-sm-2 control-label">功能代码</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="pur_functionCode_edit" readonly
									name="functionCode" placeholder="功能代码">
							</div>
						</div>
						<div class="form-group">
							<label for="purviewCode_edit" class="col-sm-2 control-label">权限代码</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="purviewCode_edit"
									name="purviewCode" placeholder="权限代码">
							</div>
						</div>
						<div class="form-group">
							<label for="purviewName_edit" class="col-sm-2 control-label">权限描述</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="purviewName_edit"
									name="purviewName" placeholder="权限描述">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary"
							id="editPurview_save_btn">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- Role add -->
	<div class="modal fade" id="addPurviewModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">添加权限</h4>
				</div>
				<form class="form-horizontal" id="addPurviewForm" method="post">
					<div class="modal-body">
						<div class="form-group">
							<label for="pur_functionCode_add" class="col-sm-2 control-label">功能代码</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="pur_functionCode_add" readOnly
									name="functionCode" placeholder="功能代码">
							</div>
						</div>
						<div class="form-group">
							<label for="purviewCode_add" class="col-sm-2 control-label">权限代码</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="purviewCode_add"
									name="purviewCode" placeholder="权限代码">
							</div>
						</div>
						<div class="form-group">
							<label for="purviewName_add" class="col-sm-2 control-label">权限描述</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="purviewName_add"
									name="purviewName" placeholder="权限描述">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary"
							id="addPurview_save_btn">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- delete user -->
	<div class="modal fade" id="deletePurviewModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<form action="" id="deletePurviewForm" method="post">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="exampleModalLabel">
							请确认是否删除该权限(<a id="del_purviewName" name="purviewName"></a>)？删除后将无法恢复！
						</h4>
						<input type="hidden" id="del_purviewCode" name="purviewCode">
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-danger" id="delPurview_btn">删除</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Add the sidebar's background. This div must be placed
           immediately after the control sidebar -->
	<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->
<script
		src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/touchspin/jquery.bootstrap-touchspin.min.js"></script>
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