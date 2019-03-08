<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp" />
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/unit/js/afterSale/afterSalePeople.js"></script>
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



<title>维修人员维护</title>
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
        <div class="col-xs-12">
            <div class="box">
                <div class="form-inline" style="margin-top: 10px;">
                    <div class="form-group">
                        <label for="repPeople">维修人员</label>
                        <input type="text" style="margin-left: 15px; width:250px;" class="form-control" id="repPeople" placeholder="维修人员"/>
                    </div>
                        <button id="search" type="button" class="btn btn-info btn-sm" id='search' style=" margin-left: 26px;" data-backdrop="static"/>
                        <i class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;查&nbsp;&nbsp;询&nbsp;&nbsp;
                        </button>
                    </div>
					<div style="width: 100%;margin-top: 10px;">
						<div id="user_toolbar" class="btn-group">
							<button type="button" class="btn btn-success btn-sm"
									data-backdrop="static" id="add"
									data-target="#addModal">
								<i class="glyphicon glyphicon-plus"></i>添加
							</button>
							<button type="button" class="btn btn-warning btn-sm"
									id="edit" data-backdrop="static"
									data-target="#editModal">
								<i class="glyphicon glyphicon-edit"></i>修改
							</button>
							<%--<button type="button" class="btn btn-danger btn-sm"
									data-backdrop="static" data-target="#deleteModal" id="delete">
								<i class="glyphicon glyphicon-trash"></i>删除
							</button>--%>
						</div>
                	</div>  
                </div>
                &nbsp;&nbsp; 
                <table id="table"/>
            </div>
        </div>
    </div> 
	<!-- /.row --> </section>

	<%--添加模态框--%>
	<div class="modal fade bs-example-modal-lg" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel1">
		<div class="modal-dialog modal-lg" role="document" style="width: 600px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title">添加</h4>
				</div>
				<div class="modal-body" style="height: 150px;">
					<div class="row">
						<div class="col-xs-12">
							<div class="box" style="height: 150px;overflow: auto;">
								<div class="form-inline" style="margin-top: 10px;">
									<div style="width: 100%;margin-top: 10px;">
										<div class="form-group">
											<label for="flexValue_addModal" style="padding-left: 5em;">序号</label>
											<input  type="text"  class="form-control" rows="3" id="flexValue_addModal" onkeyup="clearNoNum(this)" style="width: 420px;"/>
										</div>
									</div>
									<div style="width: 100%;margin-top: 10px;">
										<div class="form-group">
											<label for="repPeople_addModal" style="padding-left: 3em;">维修人员</label>
											<input  type="text"  class="form-control" rows="3" id="repPeople_addModal" onkeyup="peopleValidation(this)" style="width: 420px;"/>
										</div>
									</div>
									<%--<div style="width: 100%;margin-top: 10px;">
										<div class="form-group">
											<label for="activeDate_addModal" style="padding-left: 3em;">无效时间</label>
											<input  type="text"  class="form-control" rows="3" id="activeDate_addModal" style="width: 420px;"/>
										</div>
									</div>--%>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="save1">保存</button>
				</div>
			</div>
		</div>
	</div>

	<%--更改模态框--%>
<div class="modal fade bs-example-modal-lg" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel1">
    <div class="modal-dialog modal-lg" role="document" style="width: 600px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">更新</h4>
            </div>
            <div class="modal-body" style="height: 150px;">
				<div class="row">
		    <div class="col-xs-12">
		        <div class="box" style="height: 150px;overflow: auto;">
		            <div class="form-inline" style="margin-top: 10px;">
						<div style="width: 100%;margin-top: 10px;">
							<div class="form-group">
								<div class="form-group">
									<label for="flexValue_editModal" style="padding-left: 5em;">序号</label>
									<input  readonly type="text"  class="form-control" rows="3" id="flexValue_editModal" onkeyup="clearNoNum(this)"  style="width: 420px;"/>
								</div>
							</div>
						</div>
		                <div style="width: 100%;margin-top: 10px;">
		                    <div class="form-group">
								<input type="hidden" name="id_editModal" id="id_editModal" value="">
		                        <label for="repPeople_editModal" style="padding-left: 3em;">维修人员</label>
		                         <input readonly type="text"  class="form-control" rows="3" id="repPeople_editModal" style="width: 420px;"/>
		                    </div>
		                </div>
						<div style="width: 100%;margin-top: 10px;">
							<div class="form-group">
								<label for="activeDate_editModal" style="padding-left: 3em;">无效时间</label>
								<input  type="text"  class="form-control" rows="3" id="activeDate_editModal" style="width: 420px;"/>
							</div>
						</div>

					</div>
		        </div>
		    </div>
		</div>
            </div>
            <div class="modal-footer">
            	<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="save2">保存</button>
            </div>
        </div>
    </div>
</div>	

</body>
</html>