<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp" />
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/egdfrm/js/role/role.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.css">
<!-- jQuery 2.1.4 -->

<!-- Bootstrap 3.3.5 -->
<script
	src="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jquery-form/jquery-form.js"></script>
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
		<!-- Default panel contents -->
		<div class=" col-sm-6">
			<div class="panel panel-default">
				<div class="panel-heading">角色列表</div>
				<div class="box">
					<!-- /.box-header -->
					<div class="box-body table-responsive">
						<div id="role_toolbar" class="btn-group">
							<button type="button" class="btn btn-success btn-sm"
								data-toggle="modal" data-backdrop="static"
								data-target="#addRoleModal">
								<i class="glyphicon glyphicon-plus"></i>添加
							</button>
							<button type="button" class="btn btn-warning btn-sm"
								data-backdrop="static" data-target="#editRoleModal"
								onclick="editRole()">
								<i class="glyphicon glyphicon-edit"></i>修改
							</button>
							<button type="button" class="btn btn-danger btn-sm"
								data-backdrop="static" data-target="#deleteRoleModal"
								onclick="deleteRole()">
								<i class="glyphicon glyphicon-trash"></i>删除
							</button>
						</div>
						<table id="roleTable" data-pagination="true" data-page-size="5"
							data-page-list="[5,10,15]" data-pagination-first-text="First"
							data-pagination-pre-text="上一页" data-pagination-next-text="下一页"
							data-pagination-last-text="Last" data-locale="zh-CN"
							data-show-columns="true" data-click-to-select="true"
							data-single-select="true" data-toolbar="#role_toolbar">
							<thead>
								<tr>
									<th data-field="check" data-checkbox="true"></th>
									<th data-field="ROLE_NAME">角色名称</th>
									<th data-field="ROLE_CODE">角色代码</th>
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
				<div class="panel-heading">角色关联功能权限</div>
				<div style="width: 100%">
					<button type="button" class="btn btn-success btn-sm"
						id="addPurview_btn" style="margin-left: 5px;">添加</button>
					<button type="button" class="btn btn-danger btn-sm"
						id="deletePurview_btn">删除</button>
				</div>
				<div style="width: 100%" id="roleFunction_tree"></div>
			</div>
		</div>
	</div>
	<!-- /.row --> </section>
	<!-- /.content -->

	<!-- /.content-wrapper -->
	<!-- addRolePurview-modal -->
	<div class="modal fade" id="addRolePurviewModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">添加权限</h4>
				</div>
				<form class="form-horizontal" id="addRolePurviewForm" method="post">
					<div class="modal-body">
						<div style="width: 100%" id="addRolePurview_tree"></div>
						<input type="hidden" id="roleCode_addPur" name="roleCode">
						<input type="hidden" id="functionCode_addPur" name="functionCode">
						<input type="hidden" id="purviewCodes_addPur" name="purviewCodes">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary"
							id="addRolePurview_save_btn">确认</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- delete rolePurview -->
	<div class="modal fade" id="deleteRolePurviewModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<form action="" id="deleteRolePurviewForm" method="post">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="exampleModalLabel">
							请确认是否删除权限(<a id="del_purviewName" name="purviewName"></a>)？删除后将无法恢复
						</h4>
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-danger"
							id="delRolePurview_btn">删除</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- example-modal -->
	<div class="modal fade" id="editRoleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">角色修改</h4>
				</div>
				<form class="form-horizontal" id="editRoleForm" method="post">
					<div class="modal-body">
						<div class="form-group">
							<label for="roleName_edit" class="col-sm-2 control-label">角色名称</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="roleName_edit"
									name="roleName" placeholder="角色名称">
							</div>
						</div>
						<div class="form-group">
							<label for="roleCode_edit" class="col-sm-2 control-label">角色代码</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="roleCode_edit"
									name="roleCode" placeholder="角色代码" readonly>
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary"
							id="editRole_save_btn">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- Role add -->
	<div class="modal fade" id="addRoleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">添加角色</h4>
				</div>
				<form class="form-horizontal" id="addRoleForm" method="post">
					<div class="modal-body">
						<div id= 'roleName_add_a' class="form-group">
							<label for="roleName_add" class="col-sm-2 control-label">角色名称</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="roleName_add"
									name="roleName" placeholder="角色名称">
									<span id='roleName_add_span_a'></span>
							</div>
						</div>
						<div id='roleCode_add_b' class="form-group">
							<label for="roleCode_add" class="col-sm-2 control-label">角色代码</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="roleCode_add"
									name="roleCode" placeholder="不能输入中文">
									<span id='roleCode_add_span_b'></span>
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary"
							id="addRole_save_btn">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- delete user -->
	<div class="modal fade" id="deleteRoleModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<form action="" id="deleteUserForm" method="post">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="exampleModalLabel">
							请确认是否删除角色(<a id="del_roleName" name="roleName"></a>)？所有与该角色相关的权限信息将被删除，删除后将无法恢复
						</h4>
						<input type="hidden" id="del_roleCode" name="roleCode">
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-danger" id="delRole_btn">删除</button>
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