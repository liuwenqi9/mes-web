<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp"/>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/unit/js/productionManagement/workOrderProgress.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.css">
	<!-- jQuery 2.1.4 -->
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jquery-form/jquery-form.js"></script>
	<!-- Bootstrap 3.3.5 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/input-mask/jquery.inputmask.js"></script>
	<title>工单生产进度查询</title>
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
	<style type="text/css">
		#workOrderTable td{
			white-space: nowrap;
		}
	</style>
	<!--[if lt IE 9]>
	<script src="dist/js/html5shiv.min.js"></script>
	<script src="dist/js/respond.min.js"></script>
	<![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="form-inline" style="margin-top: 10px;">
					<div class="form-group">
						<label for="workOrderNumber">工单</label>
						<input type="text" class="form-control" id="workOrderNumber" style="margin-left: 62px;" placeholder="工单"/>
					</div>
					<button class="btn btn-success btn-sm" id="choose_won" data-backdrop="static" data-toggle="modal">
						...
					</button>
					<div class="form-group">
						<label for="production_line" style="margin-left: 20px;">生产线</label>
						<select class="form-control" id="production_line">
							<option value="" selected="selected">---请选择---</option>
							<c:forEach items="${lines}" var="map">
								<option>${map.FLEX_VALUE}</option>
							</c:forEach>
						</select>
					</div>
					<!-- 工单状态  -->
					<div class="form-group">
						<label for="wip_status" style="margin-left: 20px;">工单状态 </label>
						<select class="form-control" id="wip_status">
							<option value="" selected="selected">---请选择---</option>
							<option value="未发放" >未发放</option>
							<option value="已发放" >已发放</option>
							<option value="完成" >完成</option>
							<option value="已关闭" >已关闭 </option>
							<option value="无法关闭">无法关闭</option>
						</select>
					</div>
					
				</div>
				<div class="form-inline" style="margin-top: 10px;">
					<div class="form-group">
						<label for="parts">装配件</label>
						<input type="text" class="form-control" id="parts" style="margin-left: 50px;" placeholder="装配件"/>
					</div>
					<button class="btn btn-success btn-sm" id="choose_parts" data-backdrop="static" data-toggle="modal">
						...
					</button>
					<div class="form-group">
						<label for="mo_code" style="margin-left: 20px;">MO单</label>
						<input type="text" class="form-control" id="mo_code" style="margin-left: 3px;" placeholder="MO单" />
					</div>
				</div>
				<div class="form-inline" style="margin-top: 10px;">
					<div class="form-group">
						<label for="startTime">计划开工时间</label>
						<input type="text" class="form-control" id="startTime" style="margin-left: 10px;" />
					</div>
					<div class="form-group">
						<label for="endTime" style="margin-left: 41px;">到</label>
						<input type="text" class="form-control" id="endTime" style="margin-left: 41px;" />
					</div>
				</div>
				<!-- 实际开工时间
				<div class="form-inline" style="margin-top: 10px;">
					<div class="form-group">
						<label for="realStartTime">实际开工时间</label>
						<input type="text" class="form-control" id="realStartTime" style="margin-left: 10px;" readonly/>
					</div>
					<div class="form-group">
						<label for="realEndTime" style="margin-left: 75px;">到</label>
						<input type="text" class="form-control" id="realEndTime" style="margin-left: 7px;" readonly/>
					</div>
					<button type="button" class="btn btn-info btn-sm" id='progress_search_btn' data-backdrop="static"/>
					<i class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;查&nbsp;&nbsp;询&nbsp;&nbsp;
					</button>
					<button id="exportExcel" class="btn btn-success btn-sm" data-backdrop="static" style="margin-left: 100px;"/>
                        &nbsp;&nbsp;导出Excel&nbsp;&nbsp;
                    </button>
				</div> -->
				<!-- MO交期时间 -->
				<div class="form-inline" style="margin-top: 10px;">
					<div class="form-group">
						<label for="MOStartTime">MO交期</label>
						<input type="text" class="form-control" id="MOStartTime" style="margin-left: 41px;" />
					</div>
					<div class="form-group">
						<label for="MOEndTime" style="margin-left: 41px;">到</label>
						<input type="text" class="form-control" id="MOEndTime" style="margin-left: 41px;" />
					</div>
					<button type="button" class="btn btn-info btn-sm" style="margin-left: 30px;" id='progress_search_btn' data-backdrop="static"/>
					<i class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;查&nbsp;&nbsp;询&nbsp;&nbsp;
					</button>
					<button id="exportExcel" class="btn btn-success btn-sm" data-backdrop="static" style="margin-left: 100px;"/>
                        &nbsp;&nbsp;导出Excel&nbsp;&nbsp;
                    </button>
				</div>
				<table id="workOrderTable"></table>
			</div>
		</div>
	</div>
</section>
<!-- 模态框（Modal） -->
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
						<input type="text" class="form-control" id="workOrderNumSearch" placeholder="工单" />
					</div>
					<button type="button" class="btn btn-info btn-sm" id='search' data-backdrop="static"/>
						<i class="glyphicon glyphicon-search"></i>查询
					</button>
				</div>
				<table id="barcodeTable"></table>
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