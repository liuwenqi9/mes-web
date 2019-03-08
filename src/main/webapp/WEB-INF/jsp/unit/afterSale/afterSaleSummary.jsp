<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp" />
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/unit/js/afterSale/afterSaleSummary.js"></script>
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



<title>售后返修汇总</title>
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
                        <label for="cc">维修单</label>
                        <input type="text" style="margin-left: 15px; width:100px;" class="form-control" id="REP_WORK_ORDER" placeholder="维修单"/>
                    </div>
                    <div class="form-group" style="margin-left: 10px;">
                            <label for="xx">机身码</label>
                            <input type="text" style="margin-left: 0px; width:120px;" class="form-control" id="PRODUCT_BARCODE" placeholder="机身码" />
                    </div>
                    <div class="form-group" style="margin-left: 15px;">
                        <label for="cc">客户</label>
                        <input type="text" style="margin-left: 0px; width:200px;" class="form-control" id="CUSTOM_NAME" placeholder="客户" />
                    </div>
                    <div class="form-group" style="margin-left: 5px;">
                        <label for="cc">电话</label>
                        <input type="text" style="margin-left: 5px; width:140px;" class="form-control" id="PHONE" placeholder="电话" />
                    </div>
					<div class="form-group" style="margin-left: 5px;">
						<label for="cc">打印状态</label> <select class="form-control"
							id="PRINTER_STATUS" style="margin-left: 5px; width: 140px;">
							<option value="">----请选择----</option>
							<option value="Y">Y</option>
							<option value="N" >N</option>
						</select>
					</div>

					<div style="width: 100%;margin-top: 10px;">
                        <div class="form-group" >
                            <label for="xxx">退货日期</label>
                            <input type="text" class="form-control" id="RETURN_OPERATION_TIME_START" style=" width:100px;"  /><!-- readonly="readonly" -->
                        </div>
                        <div class="form-group" style="margin-left: 5px;">
                            <label for="xxx">至</label>
                            <input type="text" class="form-control" style="margin-left: 5px; width:100px;"  id="RETURN_OPERATION_TIME_END" />
                        </div> 
                        <div class="form-group" style="margin-left: 5px;">
                            <label for="xx">退货物流单号</label>
                            <input type="text" class="form-control" id="RE_EXP_NO" style="margin-left: 5px; width:200px;" placeholder="退货物流单号" />
                    	</div>
                        <div class="form-group" style="margin-left: 5px;">
	                        <label for="cc">状态</label>
	                        <select class="form-control" id="STATUS" style="margin-left: 5px; width:140px;" >
			                        <option value="" >----请选择----</option>
			                        <option value="Y">已出货</option>
			                        <option value="N" selected="selected">未出货</option>
	                        </select>
                    	</div>
                        <button id="search" type="button" class="btn btn-info btn-sm" id='productBarcode_search_btn' style=" margin-left: 26px;" data-backdrop="static"/>
                        <i class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;查&nbsp;&nbsp;询&nbsp;&nbsp;
                        </button>
                    </div>
                     <div style="width: 100%;margin-top: 10px;">
	                    <div style="width: 20%;float: left;">
	                        <button type="button" class="btn btn-success btn-sm" id='update_btn' data-backdrop="static"/>
	                        &nbsp;&nbsp;维修记录&nbsp;&nbsp;
	                        </button>
	                    </div> 
	                    <div style="width: 30%;float: left;">
	                        <button type="button" class="btn btn-success btn-sm" id='update_btn2' data-backdrop="static"/>
	                        &nbsp;&nbsp;更改接收信息&nbsp;&nbsp;
	                        </button>
	                    </div> 
	                    <div style="width: 20%;float: left;">
	                        <button type="button" class="btn btn-success btn-sm" id='generater' data-backdrop="static"/>
	                        &nbsp;&nbsp;生成维修单&nbsp;&nbsp;
	                        </button>
	                    </div>
	                    <div style="width: 10%;float: left;">
	                        <button type="button" class="btn btn-success btn-sm" id='hand_over' data-backdrop="static"/>
	                         &nbsp;交仓库日期&nbsp; 
	                        </button>
	                    </div>
	                    <div style="width: 10%;float: left; " align="right">
	                        <button type="button" class="btn btn-success btn-sm" id='export_excel' data-backdrop="static"/>
	                        &nbsp;&nbsp;导出excel&nbsp;&nbsp;
	                        </button></br>
	                    </div> 
	                    <div style="width: 10%;float: left; " align="right">
	                        <button type="button" class="btn btn-info btn-sm" id='print' data-backdrop="static"/>
	                        &nbsp;&nbsp;打印&nbsp;&nbsp;
	                        </button></br>
	                    </div> 
                	</div>  
                </div>
                &nbsp;&nbsp; 
                <table id="summaryTable"/>
            </div>
        </div>
    </div> 
	<!-- /.row --> </section> 

<%--交仓库日期 模态框--%>
<div class="modal fade bs-example-modal-lg" id="updateModal3" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel3">
    <div class="modal-dialog modal-lg" role="document" style="width: 600px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">更新 </h4>
            </div>
            <div class="modal-body" style="height: 200px;">
				<div class="row">
				    <div class="col-xs-12">
				     <div class="form-inline" style="margin-top: 10px;"> 
				       <div class="form-group" style="padding-left: 3em;">
                            <label for="xx3">交仓库日期 </label>
                            <input type="hidden" id="id" />
                            <input type="text" class="form-control"  readonly id="hand_over_date_modal" />
                        </div> 
                      </div>
				    </div>
				</div> 
            </div>
            <div class="modal-footer">
            	<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="summary_save3">保存</button>
            </div>
        </div>
    </div>
</div>	

 <%--更改 模态框--%>
<div class="modal fade bs-example-modal-lg" id="updateModal2" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel2">
    <div class="modal-dialog modal-lg" role="document" style="width: 600px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">更新</h4>
            </div>
            <div class="modal-body" style="height: 500px;">
				<div class="row">
		    <div class="col-xs-12">
		        <div class="box" style="height: 500px;overflow: auto;">
		            <div class="form-inline" style="margin-top: 10px;">  
		                <div style="width: 100%;margin-top: 10px;">
		                    <div class="form-group">
		                        <label for="DD" style="padding-left: 0em;">退货物流公司</label>
		                       <input type="text" class="form-control"   id="RE_LOGI_COM_MODEL2"  style="width: 420px;" /> 
		                    </div>
		                </div> 
		                <div style="width: 100%;margin-top: 10px;">
		                    <div class="form-group">
		                        <label for="CC" style="padding-left: 0em;">退货物流单号</label>
		                         <input type="text"  class="form-control" rows="3" id="RE_EXP_NO_MODEL2" style="width: 420px;"/> 
		                    </div>
		                </div> 
		                
		                <div style="width: 100%;margin-top: 10px;">
		                    <div class="form-group">
		                        <label for="DD" style="padding-left: 2em;">退货件数</label>
		                        <input type="text"  class="form-control" rows="3" id="RETURN_QTY_MODEL2" style="width: 420px;"/> 
		                    </div>
		                </div> 
		                <div style="width: 100%;margin-top: 10px;">
		                    <div class="form-group">
		                        <label for="CC" style="padding-left: 2em;">收货地址</label>
		                         <input type="text"  class="form-control" rows="3" id="ADDRESS_MODEL2" style="width: 420px;"/> 
		                    </div>
		                </div> 
		                <div style="width: 100%;margin-top: 10px;">
		                    <div class="form-group">
		                        <label for="CC" style="padding-left: 4em;">客户</label>
		                         <input type="text"  class="form-control" rows="3" id="CUSTOM_NAME_MODEL2" style="width: 420px;"/> 
		                    </div>
		                </div> 
		                <div style="width: 100%;margin-top: 10px;">
		                    <div class="form-group">
		                        <label for="CC" style="padding-left: 3em;">联系人</label>
		                         <input type="text"  class="form-control" rows="3" id="CONTACT_NAME_MODEL2" style="width: 420px;"/> 
		                    </div>
		                </div> 
		                <div style="width: 100%;margin-top: 10px;">
		                    <div class="form-group">
		                        <label for="CC" style="padding-left: 2em;">联系电话</label>
		                         <input type="text"  class="form-control" rows="3" id="PHONE_MODEL2" style="width: 420px;"/> 
		                    </div>
		                </div>  
		                 <div style="width: 100%;margin-top: 10px;">
		                    <div class="form-group">
		                        <label for="CC" style="padding-left: 2em;">其他客户</label>
		                         <input type="text"  class="form-control" rows="3" id="OTHER_CUSTOM_NAME_MODEL2" style="width: 420px;"/> 
		                    </div>
		                </div>  
		                <div style="width: 100%;margin-top: 10px;">
	                        <div class="form-group">
	                            <label for="sc" style="padding-left: 0em;">外箱联系方式</label>
		                        <select class="form-control" rows="3" id="OUTER_CONTACT_MODEL2" style="width:420px;" >
				                        <option value=""  selected="selected">--请选择--</option>
				                        <option value="有">有</option>
				                        <option value="无" >无</option>
		                        </select>
	                        </div>
                        </div>
                        <div style="width: 100%;margin-top: 10px;">
	                        <div class="form-group">
	                            <label for="ss" style="padding-left: 1em;">内箱送货单</label>
		                        <select class="form-control" rows="3" id="INTER_NOTE_MODEL2" style="width:420px;" >
				                        <option value=""  selected="selected">--请选择--</option>
				                        <option value="有">有</option>
				                        <option value="无" >无</option>
		                        </select>
	                        </div>
                        </div> 
		                 <div style="width: 100%;margin-top: 10px;">
		                    <div class="form-group">
		                        <label for="CC" style="padding-left: 4em;">备注</label>
		                         <input type="text"  class="form-control" rows="3" id="REMAKR_MODEL2" style="width: 420px;"/> 
		                    </div>
		                </div> 
		                 <div style="width: 100%;margin-top: 10px;">
		                    <div class="form-group">
		                        <label for="CC" style="padding-left: 2em;">产品条码</label>
		                         <input type="text"  class="form-control" rows="3" id="PRODUCT_BARCODE_MODEL2" style="width: 420px;"/> 
		                    </div>
		                </div> 
		            </div> 
		        </div>
		    </div>
		</div>
				
            </div>
            <div class="modal-footer">
            	<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="summary_save2">保存</button>
            </div>
        </div>
    </div>
</div>	
	
	 <%--维修记录模态框--%>
<div class="modal fade bs-example-modal-lg" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg" role="document" style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">维修记录维护</h4>
            </div>
            <div class="modal-body" style="height: 300px;">
				<div class="row">
		    <div class="col-xs-12">
		        <div class="box" style="height: 300px;overflow: auto;">
		            <div class="form-inline" style="margin-top: 10px;">
		                <div class="form-group" style="width: 350px;">
		                    <label for="XX" style="padding-left: 1em;">维修单</label>
		                    <input type="text" id="REP_WORK_ORDER_MODEL"  style="width: 250px;" readonly class="form-control" placeholder="维修单" value="" />
		                </div>
		                <div class="form-group" style="width: 350px;">
			                   <label for="AA">处理类型</label>
		                        <select class="form-control" id="REP_TYPE_MODEL" style="margin-left:15px; width: 250px;">
		                            <option value="" selected="selected">-----请选择-----</option> 
		                            <option value="修复退回">修复退回</option>  
		                            <option value="半价换新">半价换新</option>
		                            <option value="无法维修退回">无法维修退回</option>
		                            <option value="免费换新">免费换新</option>
		                        </select>
			            </div>
		                
		                <div style="width: 100%;margin-top: 10px;">
		                    <div class="form-group" style="width: 350px;">
			                    <label for="AA" style="padding-left: 0em;">维修人员</label>
		                   		<select class="form-control" id="REP_PEOPLE_MODEL" style="margin-left:15px; width: 250px;">
		                            <option value="" >-----请选择-----</option>  
		                        </select> 
			                </div>
			               <div class="form-group" style="width: 350px;">
			                    <label for="XX" style="padding-left: 0em;">补货SO号</label>
			                    <input type="text" id="SO_NO_MODEL" style="margin-left:10px; width: 250px;" class="form-control" placeholder="补货SO号" value="" />
		                   </div>  
		                </div>
		                 <div style="width: 100%;margin-top: 10px;">
		                 	<div class="form-group">
		                        <label for="DDx" style="padding-left: 0em;">客户反馈</label>
		                        <textarea class="form-control" rows="2" id="CUSTOMER_FEEDBACK_MODEL" placeholder="" style="width: 620px;"></textarea>
		                    </div>
		                 </div>
		                <div style="width: 100%;margin-top: 10px;">
		                    <div class="form-group">
		                        <label for="DD" style="padding-left: 0em;">故障描述</label>
		                        <textarea class="form-control" rows="3" id="ISSUE_DESCRIPTION_MODEL" placeholder="" style="width: 620px;"></textarea>
		                    </div>
		                </div> 
		                <div style="width: 100%;margin-top: 10px;">
		                    <div class="form-group">
		                        <label for="CC" style="padding-left: 0em;">维修分析</label>
		                        <textarea class="form-control" rows="3" id="REP_REASON_MODEL" placeholder="" style="width: 620px;"></textarea>
		                    </div>
		                </div> 
		            </div> 
		        </div>
		    </div>
		</div>
				
            </div>
            <div class="modal-footer">
            	<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="summary_save">保存</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>