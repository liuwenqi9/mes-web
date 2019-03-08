<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp" />
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/unit/js/afterSale/afterSaleBack.js"></script>
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



<title>售后退货</title>
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
                        <label for="cc">客户</label>
                        <input type="text" style="margin-left: 25px;" class="form-control" id="CUSTOM_NAME" placeholder="客户" readonly style="margin-left: 1em;"/>
                    </div>
                    <button class="btn btn-success btn-sm" id="choose" data-backdrop="static" data-toggle="modal">
						...
					</button>
                    <div class="form-group" style="margin-left: 20px;">
                        <label for="cc">退货物流公司</label>
                        <input type="text" class="form-control" id="RE_LOGI_COM" placeholder="退货物流公司" />
                    </div>
                    <div class="form-group" style="margin-left: 20px;">
                        <label for="cc">退货物流单号</label>
                        <input type="text" class="form-control" id="RE_EXP_NO" placeholder="退货物流单号"/>
                    </div>
                    <div style="width: 100%;margin-top: 10px;">
                        <div class="form-group">
                            <label for="xxx">退货件数</label>
                            <input type="text" class="form-control" id="RETURN_QTY" placeholder="退货件数" />
                        </div>
                        <div class="form-group" style="margin-left: 57px;">
                            <label for="xxx">联系人</label>
                            <input type="text" class="form-control" style="margin-left: 38px;"  id="CONTACT_NAME" placeholder="联系人" />
                        </div>
                        <div class="form-group" style="margin-left: 20px;">
                            <label for="xxx">联系电话</label>
                            <input type="text" class="form-control" style="margin-left: 25px;" id="PHONE" placeholder="联系电话" />
                        </div> 
                    </div> 
                    <div style="width: 100%;margin-top: 10px;">
                        <div class="form-group">
                            <label for="xx">收货地址</label>
                            <input type="text" class="form-control"  style="width: 520px;" id="ADDRESS"  />
                        </div>
                        <div class="form-group" style="margin-left: 20px;">
                            <label for="xx">其他客户</label>
                            <input type="text" class="form-control"  style="margin-left: 25px; " id="OTHER_CUSTOM_NAME"  />
                        </div> 
                    </div>
                    <div style="width: 100%;margin-top: 10px;">
                        <div class="form-group">
                            <label for="cc">外箱联系方式</label>
	                        <select class="form-control" id="OUTER_CONTACT" style="margin-left: 5px; width:100px;" >
			                        <option value=""  selected="selected">--请选择--</option>
			                        <option value="有">有</option>
			                        <option value="无" >无</option>
	                        </select>
                        </div>
                        <div class="form-group" style="margin-left: 5px; ">
                            <label for="cc">内箱送货单</label>
	                        <select class="form-control" id="INTER_NOTE" style="margin-left: 5px; width:100px;" >
			                        <option value=""  selected="selected">--请选择--</option>
			                        <option value="有">有</option>
			                        <option value="无" >无</option>
	                        </select>
                        </div> 
                         <div class="form-group" style="margin-left: 10px;">
                            <label for="xx">备注</label>
                            <input type="text" class="form-control"  style=" margin-left: 5px;width: 450px;"  id="REMAKR"  />
                        </div> 
                    </div> 
                    <div style="width: 100%;margin-top: 10px;">
                        <div class="form-group">
                            <label for="xx">产品条码</label>
                            <input type="text" class="form-control" id="PRODUCT_BARCODE_ALL" placeholder="输入产品条码 回车" />
                        </div>
                         <div class="form-group" style="margin-left: 20px;">
                            <label for="xx">退货日期</label>
                            <input type="text" class="form-control"  style="width: 130px;" readonly id="DELIVERY_OPERATION_TIME" placeholder="退货日期" />
                        </div> 
                    </div>
                </div>
                <div class="box-body table-responsive">
                    <label class="col-sm-12 control-label">&nbsp;</label>
                    <label class="col-sm-1 control-label">
                        <button type="button" class="btn btn-danger btn-sm" data-backdrop="static" id="delete" >
                            <i class="glyphicon glyphicon-trash"></i>&nbsp;&nbsp;删&nbsp;除&nbsp;&nbsp;
                        </button>
                    </label>
                    <label class="col-sm-4 control-label">&nbsp;</label>
                    <label class="col-sm-1 control-label">
                        <button type="button" class="btn btn-primary btn-sm" data-backdrop="static" id="resetAll">
                            <i class="glyphicon  glyphicon-refresh"></i>&nbsp;&nbsp;清&nbsp;空&nbsp;&nbsp;
                        </button>
                    </label>
                    <label class="col-sm-4 control-label">&nbsp;</label>
                    <label class="col-sm-1 control-label">
                        <button type="button" class="btn btn-success btn-sm" data-backdrop="static" id="commit">
                            <i class="glyphicon glyphicon-ok"></i>&nbsp;&nbsp;保&nbsp;存&nbsp;&nbsp;
                        </button>
                    </label>
                </div>
                    <table id="backTable" data-click-to-select="true">
						<thead>
							<tr>
								<th data-field="check" data-checkbox="true"></th> 
								<th data-field="WIP_BARCODE_ID" data-visible='false'>条码id</th>
								<th data-field="MATERIAL_ID" data-visible='false'>物料id</th>
								<th data-field="PRODUCT_BARCODE">产品条码</th>
								<th data-field="SEGMENT1">物料编码</th>
								<th data-field="MODEL">型号</th>
								<th data-field="DESCRIPTION">描述</th>
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
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    客户查询
                </h4>
            </div>
            <div class="modal-body">
                <div class="form-inline" style="margin-bottom: 10px;">
                    <div class="form-group">
                        <input type="text" class="form-control" id="CUSTOM_NAME_model" placeholder="客户" />
                    </div>
                    <button type="button" class="btn btn-info btn-sm" id='search' data-backdrop="static"/>
                        <i class="glyphicon glyphicon-search"></i>查询
                    </button>
                </div>
                <table id="customNameTable"></table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="choose_ok">确认选择</button>
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