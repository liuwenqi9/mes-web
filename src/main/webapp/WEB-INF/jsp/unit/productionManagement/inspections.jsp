<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp" />
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/unit/js/productionManagement/inspections.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.css">
	<!-- jQuery 2.1.4 -->
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jquery-form/jquery-form.js"></script>
	<!-- Bootstrap 3.3.5 -->
	<script src="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/input-mask/jquery.inputmask.js"></script>
	<title>报检</title>
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
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
        <script src="dist/js/html5shiv.min.js"></script>
        <script src="dist/js/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- 检验模态框 -->
	<div class="modal fade" id="inspectionMsgModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<form  id="inspectionMsgForm" >
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="exampleModalLabel">
						    QA检验报告 
						</h4>
			<div class="col-xs-12">
		        <div style="margin-top:30px; height: 150px;  overflow: auto;">
		        <!-- class="table table-bordered" -->
		        	<table border="1" bordercolor="#DCDCDC" width=100% cellspacing="0" cellpadding="0">  
				        <tr>  
				            <td colspan="3" align="center" width=30% height=40px >检验不良数量</td>  
				            <td rowspan="2" align="center" width=40% height=80px >不良现象</td>  
				            <td rowspan="2" align="center" width=40% height=80px >备注</td>  
				        </tr>  
				        <tr>  
							<td align="center" width=10% height=40px >严重</td> 
							<td align="center" width=10% height=40px >主要</td> 
							<td align="center" width=10% height=40px >次要</td> 
				        </tr>  
				        <tr>  
				            <td align="center" width=10% height=40px><input type="text" onkeyup="value=value.replace(/[^\d]/g,'')" id='speQty' name='speQty' style="width: 50px; height:45px; border:0;" /></td>  
				            <td align="center" width=10% height=40px><input type="text" onkeyup="value=value.replace(/[^\d]/g,'')" id='majorQty' name='majorQty' style="width: 50px; height:45px; border:0;" /></td>
				            <td align="center" width=10% height=40px><input type="text" onkeyup="value=value.replace(/[^\d]/g,'')" id='secQty' name='secQty' style="width: 50px; height:45px; border:0;" /></td>
							<td align="center" width=40% ><textarea cols="20" rows="2"  style="resize:none; width: 200px;   " id ='checkMsg' name='checkMsg' ></textarea></td>
							<td align="center" width=40% ><textarea cols="20" rows="2"  style="resize:none; width: 150px;   "id ='checkRemark' name='checkRemark' ></textarea></td>
				        </tr>  
				    </table>
		            <!-- resize:none; width:200px; height:200px; border:0; -->
		        </div>
		    </div>
						 
					</div>
					<input type="hidden" id ='packingBarcodeId' name='packingBarcodeId'/>
					<input type="hidden" id ='checkStatus' name='checkStatus'/>
					<div class="modal-footer">
						<button type="button" class="btn btn-success" id="comfirm_OK_btn">&nbsp;&nbsp;&nbsp;O&nbsp;K&nbsp;&nbsp;&nbsp;</button>
						<button type="button" class="btn btn-danger" id="comfirm_NG_btn">&nbsp;&nbsp;&nbsp;N&nbsp;G&nbsp;&nbsp;&nbsp;</button>
						<button type="button" class="btn btn-danger" id="comfirm_back_btn">&nbsp;&nbsp;&nbsp;退&nbsp;回&nbsp;&nbsp;&nbsp;</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-body table-responsive">
					<div class="form-inline" style="margin-top: 10px;">
						<div class="form-group">
							<label for="search_productionLine">生产线</label>
							<select type='text' class="form-control" id='search_productionLine' name="productionLine" style="margin-left: 27px;">
								<option value="" selected="selected">---请选择---</option>
								<c:forEach items="${planLines}" var="line">
									<option value="${line.FLEX_VALUE}">${line.PLANLINEDESC}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group" style="margin-left: 20px;">
							<label for="workOrderNumber">工&nbsp;&nbsp;单&nbsp;&nbsp;号</label>
							<input type="text" class="form-control" id="workOrderNumber" placeholder="工单号" />
						</div>
						<div class="form-group" style="margin-left: 20px;">
							<label for="modelNum">型号</label>
							<input type="text" class="form-control" id="modelNum" placeholder="型号" style="margin-left: 19px;"/>
						</div>
						<div style="width: 100%;margin-top: 10px;">
							<div class="form-group">
								<label for="checkStatus_search">检验结果</label>
								<select type='text' class="form-control" id= 'checkStatus_search' style="margin-left: 15px;">
									<option value=''>---请选择---</option>
									<option value='R' selected>已提交</option>
									<option value='P'>已通过</option>
									<option value='E'>不通过</option>
									<option value='B'>退回</option>
								</select>
							</div>
							<div class="form-group" style="margin-left: 20px;">
								<label for="inspectNumber">报检单号</label>
								<input type="text" class="form-control"  placeholder="报检单号" id='inspectNumber'/>
							</div>
							<div class="form-group" style="margin-left: 20px;">
								<label for="moNum">MO单号</label>
								<input type="text" class="form-control" id="moNum" placeholder="MO单号" />
							</div>
							<button type="button" class="btn btn-info btn-sm" id='search_btn' data-backdrop="static" style="margin-left: 20px;"/>
								<i class="glyphicon glyphicon-search"></i>查询
							</button>
							<button type="button" id='inspection_btn' class="btn btn-info btn-sm" data-backdrop="static" data-target="#inspectionMsgModal"/>
								检验
							</button>
						</div>
					</div>
					<table id="inspectionsTable" />
				</div>
			</div>
		</div>
		</div>
	</div>
	</section>
</body>
</html>