<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp" />
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/egdfrm/js/user/user.js"></script>
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

<title>用户维护</title>
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
					<div id="user_toolbar" class="btn-group">
						<button type="button" class="btn btn-success btn-sm"
							data-backdrop="static" id="addUser_btn"
							data-target="#addUserModal">
							<i class="glyphicon glyphicon-plus"></i>添加
						</button>
						<button type="button" class="btn btn-warning btn-sm"
							id="edit_U_btn" data-backdrop="static"
							data-target="#editUserModal" onclick="editUser()">
							<i class="glyphicon glyphicon-edit"></i>修改
						</button>
						<button type="button" class="btn btn-danger btn-sm"
							data-backdrop="static" data-target="#deleteUserModal"
							onclick="deleteUser()">
							<i class="glyphicon glyphicon-trash"></i>删除
						</button>
						<button type="button" class="btn btn-primary btn-sm"
							data-backdrop="static" data-target="#resetPasswordModal"
							onclick="resetPassword()">
							<i class="glyphicon  glyphicon-refresh"></i>重置密码
						</button>
					</div>
					<table id="userTable" data-pagination="true" data-page-size="5"
						data-page-list="[5,10,15]" data-pagination-first-text="First"
						data-pagination-pre-text="上一页" data-pagination-next-text="下一页"
						data-pagination-last-text="Last" data-locale="zh-CN"
						data-show-columns="true" data-toolbar="#user_toolbar" data-click-to-select="true"
       data-single-select="true">
						<thead>
							<tr>
								<th data-field="check" data-checkbox="true"></th>
								<th data-field="USER_NAME">用户名</th>
								<th data-field="LOGIN_NAME">登录名</th>
								<th data-field="USER_STATUS">状态</th>
								<th data-field="ROLE_NAME">角色名</th>
								<th data-field="USER_M_PHOME">手机</th>
								<th data-field="USER_EMAIL">邮箱</th>
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

	<!-- /.content-wrapper -->

	<!-- example-modal -->
	<div class="modal fade" id="editUserModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">用户修改</h4>
				</div>
				<form class="form-horizontal" id="editUserForm" method="post">
					<div class="modal-body">
						<div class="form-group">
							<label for="loginName_edit" class="col-sm-2 control-label">登录名</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="loginName_edit"
									name="loginName" placeholder="登录名" readonly>
							</div>
						</div>
						<div class="form-group">
							<label for="userName_edit" class="col-sm-2 control-label">用户名</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="userName_edit"
									name="userName" placeholder="用户名">
								<span id='userName_edit_span_b'></span> 
							</div>
						</div>
						<div class="form-group">
							<label for="userStatus_edit" class="col-sm-2 control-label">用户状态</label>
							<div class="col-sm-10">
								<select class="form-control" id="userStatus_edit">
								</select>
								<span id='userStatus_edit_span_o'></span>
							</div>
						</div>
						<div class="form-group">
							<label for="userRoleId_edit" class="col-sm-2 control-label">角色</label>
							<div class="col-sm-10">
								<select class="form-control" id="userRoleId_edit">
								</select>
								<span id='userRoleId_edit_span_e'></span>
							</div>
						</div>
						<div class="form-group">
							<label for="userMPhome_edit" class="col-sm-2 control-label">手机号码</label>
							<div class="col-sm-10">
								<input type="tel" class="form-control" id="userMPhome_edit"
									name="userMPhome" placeholder="手机号码">
							</div>
						</div>
						<div class="form-group">
							<label for="userEmail_edit" class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10">
								<input type="email" class="form-control" id="userEmail_edit"
									name="userEmail" placeholder="如：XXX@163.com">
							</div>
						</div>
						<div id='userDeptId_edit_e' class="form-group">
							<label for="userDept_edit" class="col-sm-2 control-label">部门</label>
							<div class="col-sm-10">
								<select class="form-control" id="userDept_edit">
								</select>
								<span id='userDeptId_edit_span_e'></span>
							</div>
						</div>
						<div id='userPlanLine_edit_e' class="form-group">
							<label for="userPlanLine_edit" class="col-sm-2 control-label">生产线</label>
							<div class="col-sm-10">
								<select class="form-control" id="userPlanLine_edit">
								</select>
								<span id='userPlanLine_edit_span_e'></span>
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary"
							id="editUser_save_btn">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- user add -->
	<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">添加用户</h4>
				</div>
				<form  class="form-horizontal" id="addUserForm" method="post">
					<div class="modal-body">
						<div id = 'loginName_add_a' class="form-group ">
							<label for="loginName_add" class="col-sm-2 control-label">登录名</label>
							<div class="col-sm-10 ">
								<input type="text" class="form-control " id="loginName_add"
									name="loginName" placeholder="登录名">
								<span id='loginName_add_span_a'></span> 
							</div>
						</div>
						<div id ='userName_add_b' class="form-group">
							<label for="userName_add" class="col-sm-2 control-label">用户名</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="userName_add"
									name="userName" placeholder="用户名">
									<span id='userName_add_span_b'></span> 
							</div>
						</div>
						<div class="form-group">
							<label for="userStatus_add" class="col-sm-2 control-label">用户状态</label>
							<div class="col-sm-10">
								<select class="form-control" id="userStatus_add">
								</select>
								<span id='userStatus_add_span_o'></span>
							</div>
						</div>
						<div id='userRoleId_add_e' class="form-group">
							<label for="userRoleId_add" class="col-sm-2 control-label">角色</label>
							<div class="col-sm-10">
								<select class="form-control" id="userRoleId_add">
								</select>
								<span id='userRoleId_add_span_e'></span>
							</div>
						</div>
						<div id ='userMPhome_add_c' class="form-group">
							<label for="userMPhome_add" class="col-sm-2 control-label">手机号码</label>
							<div class="col-sm-10">
								<input type="tel" class="form-control" id="userMPhome_add"
									name="userMPhome" placeholder="手机号码">
									<span id='userMPhome_add_span_c'></span>
							</div>
						</div>
						<div id='userEmail_add_d' class="form-group">
							<label for="userEmail_add" class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10">
								<input type="email" class="form-control" id="userEmail_add"
									name="userEmail" placeholder="如：XXX@163.com">
									<span id='userEmail_add_span_d'></span>
							</div>
						</div>
						<div id='userDeptId_add_e' class="form-group">
							<label for="userDept_add" class="col-sm-2 control-label">部门</label>
							<div class="col-sm-10">
								<select class="form-control" id="userDept_add">
								</select>
								<span id='userDeptId_add_span_e'></span>
							</div>
						</div>
						<div id='userPlanLine_add_e' class="form-group">
							<label for="userPlanLine_add" class="col-sm-2 control-label">生产线</label>
							<div class="col-sm-10">
								<select class="form-control" id="userPlanLine_add">
								</select>
								<span id='userPlanLine_add_span_e'></span>
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary"
							id="addUser_save_btn">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- delete user -->
	<div class="modal fade" id="deleteUserModal" tabindex="-1"
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
							请确认是否删除登录名为(<a id="del_loginName" name="loginName"></a>)的用户？
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
	<!-- resetPassword user -->
	<div class="modal fade" id="resetPasswordModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">
						请确认是否重置登录名为(<a id="reset_loginName"></a>)的用户？
					</h4>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-danger" id="resetPassword_btn">重置密码</button>
				</div>
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