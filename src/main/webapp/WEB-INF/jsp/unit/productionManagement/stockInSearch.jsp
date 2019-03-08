<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp" />
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/unit/js/productionManagement/stockInSearch.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.css">
	<!-- jQuery 2.1.4 -->
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jquery-form/jquery-form.js"></script>
	<!-- Bootstrap 3.3.5 -->
	<script src="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/input-mask/jquery.inputmask.js"></script>

	<title>入库单查询</title>
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
						<label for="search_productionLine">生产线</label>
						<select type='text' class="form-control" id='search_productionLine' name="productionLine" style="margin-left: 13px;">
							<option value="" selected="selected">---请选择---</option>
							<c:forEach items="${planLines}" var="line">
								<option value="${line.FLEX_VALUE}">${line.PLANLINEDESC}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group" style="margin-left: 20px;">
						<label for="workOrderNumber">工&nbsp;&nbsp;单&nbsp;号</label>
						<input type="text" class="form-control" id="workOrderNumber" placeholder="工单号" />
					</div>
					<div style="width: 100%;margin-top: 10px;">
						<div class="form-group">
							<label for="stockMark">入库标识</label>
							<select class="form-control" id="stockMark">
								<option value='0'>---请选择---</option>
								<option value='Y'>已入库</option>
								<option value='WIP_COM'>未入库</option>
							</select>
						</div>
						<div class="form-group" style="margin-left: 20px;">
							<label for="stockInNumber">入库单号</label>
							<input type="text" class="form-control" id="stockInNumber" placeholder="工单子库" />
						</div>
						<button type="button" class="btn btn-info btn-sm" id='stock_search_btn' data-backdrop="static"/>
						<i class="glyphicon glyphicon-search"></i>查询
						</button>
						<button id="exportExcel" class="btn btn-success btn-sm" style="margin-left: 200px;" data-backdrop="static" style="margin-left: 100px;"/>
                        &nbsp;&nbsp;导出Excel&nbsp;&nbsp;
                    </button>
					</div>
				</div>
				<table id="stockInTable"></table>
			</div>
		</div>
	</div>
	</section>
	<div class="control-sidebar-bg"></div>
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