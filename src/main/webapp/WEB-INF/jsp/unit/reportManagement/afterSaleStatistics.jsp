<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp"/>
    <script type="text/javascript" charset="UTF-8"
            src="${pageContext.request.contextPath}/static/unit/js/reportManagement/afterSaleStatistics.js"></script>
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

    <title>售后服务机品质分析报表 </title>
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

<div style="width: 100%;height: 100%;">
   <section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="form-inline" style="margin-top: 10px;">
                	<div class="form-group">
                        <label for="xx1">出货状态</label>
                    	<select type='text' class="form-control" id='status' style=" width:130px;">
                    		<option value="">---请选择---</option>
                    		<option value="delivery">已出货</option>
                    		<option value="return">未出货</option>
                    	 </select>
                    </div>  
                    <div class="form-group" style="margin-left: 25px;">
                        <label for="xx1">退货日期</label>
                        <input type="text" class="form-control"  style=" width:100px;" id="start_time" >
                    </div>
                    <div class="form-group" style="margin-left: 0px;">
                        <label for="xx1">至</label>
                        <input type="text" class="form-control" style=" width:100px;" id="end_time" >
                    </div>
                    <div class="form-group" style="margin-left: 34px;">
                        <label for="xx1">客户</label>
                        <input type="text" class="form-control" style=" width:150px;" id=custom_name >
                    </div> 
                    <div style="width: 100%;margin-top: 10px;">
                    	<div class="form-group" style="margin-left: 25px;">
                        <label for="xx2">型号</label>
                        <input type="text" class="form-control" style=" width:130px;" id="model" >
                    	</div>
                    	<div class="form-group" style="margin-left: 20px;">
                        <label for="xx2">退货物流单号</label>
                        <input type="text" class="form-control" style=" width:200px;" id="re_exp_no" >
                    	</div>
                    	<div class="form-group" style="margin-left: 20px;">
                        <label for="xx2">机身码</label>
                        <input type="text" class="form-control" style=" width:150px;" id="product_barcode" >
                    	</div> 
                    </div>
                     <div style="width: 100%;margin-top: 10px;"> 
	                    <div class="form-group" style="margin-left: 10px;">
	                        <label for="xx3">维修单</label>
	                        <input type="text" class="form-control" style=" width:130px;" id=rep_work_order >
	                    </div>
                    	<div class="form-group" style="margin-left: 20px;">
                        <label for="xx3">出货物流单号</label>
                        <input type="text" class="form-control" style=" width:202px;" id="sh_exp_no" >
                    	</div> 
                    	<div class="form-group" style="margin-left: 20px;">
                        <label for="xx2">维修员</label>
                        <input type="text" class="form-control" style=" width:150px;" id="rep_people" >
                    	</div>  
                     </div>
                     
                     <div style="width: 100%;margin-top: 10px;"> 
	                     <div class="form-group" style="margin-left: 0px;">
	                        <label for="xx4">电话号码</label>
	                        <input type="text" class="form-control" style=" width:128px;" id="phone" >
	                     </div> 
	                     <div class="form-group" style="margin-left: 25px;">
	                        <label for="xx1">出货时间</label>
	                        <input type="text" class="form-control"  style=" width:100px;" id="ship_date_start" >
	                   	  </div>
	                      <div class="form-group" style="margin-left: 0px;">
	                        <label for="xx1">至</label>
	                        <input type="text" class="form-control" style=" width:100px;" id="ship_date_end" >
	                     </div> 
	                     <div class="form-group" style="margin-left: 20px;">
	                        <label for="xx2">联系人</label>
	                        <input type="text" class="form-control" style=" width:150px;" id="contact_name" >
	                    </div>
	                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <button type="button" class="btn btn-info btn-sm" id='search_btn' data-backdrop="static"/>
                        <i class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;查&nbsp;&nbsp;询&nbsp;&nbsp;
                        </button>
                     </div>
                    
                    <div style="width: 100%;margin-top: 10px;" align="right">
                        <button id="exportExcel" class="btn btn-success btn-sm" data-backdrop="static"/>
                        &nbsp;&nbsp;导出Excel&nbsp;&nbsp;
                        </button>
                    </div>
                </div>
                <table id="table"></table>
            </div>
        </div>
    </div>
</section>
</div>
</body>
</html>