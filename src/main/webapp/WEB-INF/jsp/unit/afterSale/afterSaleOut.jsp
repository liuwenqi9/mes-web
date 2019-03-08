<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp" />
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/unit/js/afterSale/afterSaleOut.js"></script>
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



<title>售后出货</title>
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
                        <label for="cc" style="margin-left: 0em;" >维修单号</label>
                        <input type="text"  class="form-control" id="REP_WORK_ORDER" />
                    </div>
                    <div class="form-group" style="margin-left: 10px;">
                            <label for="xxx" style="margin-left: 0em;" >产品条码</label>
                            <input type="text"  class="form-control" id="PRODUCT_BARCODE"  />
                     </div> 
                     <div class="form-group" style="margin-left: 10px;">
                            <label for="xxx" style="margin-left: 2em;">型号</label>
                            <input type="text"  class="form-control" id="MODEL"  />
                     </div>  
                    <div style="width: 100%;margin-top: 10px;">  
                    	<div class="form-group" >
                        <label for="cc" style="margin-left: 2em;" >客户</label>
                        <input type="text"  class="form-control" id="CUSTOM_NAME"  />
                        </div>
                        <div class="form-group" style="margin-left: 10px;">
                            <label for="xxx" style="margin-left: 1em;">联系人</label>
                            <input type="text"  class="form-control" id="CONTACT_NAME" />
                      </div>
                      <div class="form-group" style="margin-left: 10px;">
	                        <label for="cc" style="margin-left: 2em;">状态</label>
	                        <select class="form-control" id="STATUS"  >
			                        <option value="" >----请选择----</option>
			                        <option value="Y">已出货</option>
			                        <option value="N" selected="selected">未出货</option>
	                        </select>
                    	</div>
                        
	                    <button  type="button" class="btn btn-info btn-sm" id='search_btn' style=" margin-left: 26px;" data-backdrop="static"/>
	                        <i class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;查&nbsp;&nbsp;询&nbsp;&nbsp;
	                    </button>
                    </div> 
                    <div style="width: 100%;margin-top: 10px;">
	                    <div style="width: 50%;float: left;">
	                        <button type="button" class="btn btn-success btn-sm" id='out_info_btn' data-backdrop="static"/>
	                        &nbsp;&nbsp;出货详情信息&nbsp;&nbsp;
	                        </button>
	                    </div> 
	                    <div style="width: 30%;float: left;">
	                       <!--  <button type="button" class="btn btn-success btn-sm" id='generater' data-backdrop="static"/>
	                        &nbsp;&nbsp;生成维修单&nbsp;&nbsp;
	                        </button> -->
	                    </div>
	                    <div style="width: 20%;float: left; " align="right">
	                       <!--  <button type="button" class="btn btn-info btn-sm" id='print' data-backdrop="static"/>
	                        &nbsp;&nbsp;打印&nbsp;&nbsp;
	                        </button></br> -->
	                    </div> 
                	</div>    
                    
                </div> 
                    <table id="outTable">
											</table> 
                
            </div>
        </div>
    </div>
	
	
	<!-- /.row --> </section>
	<!-- /.content -->

	<!-- /.content-wrapper -->

	<!-- example-modal -->
 <%--出货详情信息模态框--%>
<div class="modal fade bs-example-modal-lg" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg" role="document" style="width: 800px;">
        <div class="modal-content"> 
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">出货详细信息</h4>
            </div>
            <div class="modal-body" style="height: 250px;">
				<div class="row">
		    <div class="col-xs-12">
		        <div class="box" style="height: 250px;overflow: auto;">
		       <form id='sh'>
		            <div class="form-inline" style="margin-top: 10px;">
		                <div class="form-group" style="width: 350px;">
		                    <label for="XX" style="padding-left: 0em;">物流单号</label>
		                    <input type="text" id="SH_EXP_NO_MODEL" name='SH_EXP_NO' style="width: 250px;" class="form-control"  />
		                </div>
		                <div class="form-group" style="width: 350px;">
		                    <label for="XX" style="padding-left: 0em;">物流公司</label>
		                    <input type="text" id="SH_LOGI_COM_MODEL" name='SH_LOGI_COM' style="width: 250px;" class="form-control"  />
		                </div>
		                
		                <div style="width: 100%;margin-top: 20px;">
		                    <div class="form-group" style="width: 350px;">
			                    <label for="AA" style="padding-left: 1em;">联系人</label>
			                    <input type="text" id="CONTACT_NAME_MODEL"  name='CONTACT_NAME' style="width: 250px;" class="form-control"/>
			                </div>
			               <div class="form-group" style="width: 350px;">
			                    <label for="XX" style="padding-left: 0em;">联系电话</label>
			                    <input type="text" id="PHONE_MODEL"  name='PHONE' style="width: 250px;" class="form-control"/>
		                   </div>  
		                </div>
		                 <div style="width: 100%;margin-top: 20px;">
		                    <div class="form-group" >
			                    <label for="gg" style="padding-left: 0em;">收货地址</label>
			                    <input type="text" id="ADDRESS_MODEL" name='ADDRESS' style="width: 600px;" class="form-control"/>
			                </div> 
			                <input type="text" id="IDS_MODEL" name='IDs' hidden/>
		                </div>
		                <div style="width: 100%;margin-top: 20px;">
		                    <div class="form-group" >
			                    <label for="xxx" style="padding-left: 0em;">供应商快递信息</label>
			                    <input type="text" id="SUPPLIER_DELIVERY_INFO_MODEL" name='SUPPLIER_DELIVERY_INFO' style="width: 600px;" class="form-control"/>
			                </div>  
		                </div>
		            </div> 
		            </form>
		        </div>
		    </div>
		</div>
		</div>
            <div class="modal-footer">
            	<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="save">保存</button>
                <button type="button" class="btn btn-primary" id="comfirm">确认出货</button>
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