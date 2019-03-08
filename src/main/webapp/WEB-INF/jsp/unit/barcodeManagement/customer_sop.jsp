<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp"/>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/unit/js/barcodeManagement/customer_sop.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.css">
    <!-- jQuery 2.1.4 -->
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jquery-form/jquery-form.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/input-mask/jquery.inputmask.js"></script>
    <title>mo查询</title>
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
        #shipmentTable td{
            white-space: nowrap;
        }
    </style>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="dist/js/html5shiv.min.js"></script>
    <script src="dist/js/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<section class="content">
    <div class="row">
        <div class="col-sm-12">
            <div class="box">
                <div class="form-inline" style="margin-top: 10px;">
                    <div class="form-group">
                        <label for="cust_po_number">MO号：</label>
                        <input type="text" class="form-control " id="cust_po_number" placeholder="MO号" />
                    </div>
                    <button type="button" class="btn btn-info btn-sm" id='search_btn' data-backdrop="static"/>
                    <i class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;查&nbsp;&nbsp;询&nbsp;&nbsp;
                    </button>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <button type="button" class="btn btn-info btn-sm" id='down_btn' data-backdrop="static" title="导出"/>
                    <i class="glyphicon glyphicon-arrow-down"></i>
                    </button>


                </div>
                <div class="form-inline" style=" width: 100% ;margin-top: 10px;">
                    <div class="form-group" >
                        <label for="party_number">客户编号</label>
                        <input type="text" class="form-control" id="party_number"  style="width: 150px"  readonly  />
                    </div>
                    <div class="form-group" style="margin-left: 50px;">
                        <label for="party_name">客户名称</label>
                        <input type="text" class="form-control " style="width: 320px" id="party_name" readonly/>
                    </div>
                </div>
                <div class="form-inline" style=" width: 100% ;margin-top: 5px;">
                    <div class="form-group">
                        <label for="sop_yh">验货标准</label>
                        <input type="text" class="form-control" id="sop_yh"style="width: 150px" readonly>
                    </div>
                    <div class="form-group" style="margin-left: 50px;">
                        <label for="yh_date">验货日期</label>
                        <input type="text" class="form-control" id="yh_date" style="width: 150px" readonly>
                    </div>
                    <div class="form-group" style=" margin-left: 38px;">
                        <label for="sop_rh">要求ROHS</label>
                        <input type="text" class="form-control" id="sop_rh" style="width: 150px" readonly/>
                    </div>
                    <div class="form-group" style="margin-left: 50px;">
                        <label for="sop_box">箱号连接</label>
                        <input type="text" class="form-control" id="sop_box" style="width: 150px" readonly/>
                    </div>
                </div>
                <div class="form-inline" style=" width: 100% ; margin-top: 5px;">
                    <div class="form-group" >
                        <label for="your_po" style="padding-left: 2.5em;">PO</label>
                        <input type="text" class="form-control" id="your_po" style="width: 150px" readonly>
                    </div>
                    <div class="form-group" style="margin-left: 50px;">
                        <label for="our_pi" style="padding-left: 3em;">PI</label>
                        <input type="text" class="form-control" id="our_pi" style="width: 150px" readonly/>
                    </div>
                    <div class="form-group" style="margin-left: 50px;">
                        <label for="sop_battery" style="padding-left: 1em;">带电池</label>
                        <input type="text" class="form-control" id="sop_battery"style="width: 150px" readonly>
                    </div>
                    <div class="form-group" style="margin-left: 50px;">
                        <label for="country">销售国家</label>
                        <input type="text" class="form-control" id="country" style="width: 150px" readonly/>
                    </div>
                </div>
                <div class="form-inline" style=" width: 100% ;margin-top: 5px;">
                    <div class="form-group">
                        <label for="ps_number">评审单号</label>
                        <input type="text" class="form-control" id="ps_number"style="width: 150px" readonly>
                    </div>
                    <div class="form-group" style="margin-left: 62px;">
                        <label for="ps_version">版本号</label>
                        <input type="text" class="form-control" id="ps_version" style="width: 150px" readonly/>
                    </div>
                    <div class="form-group" style="margin-left: 50px;">
                        <label for="vs_date">版本日期</label>
                        <input type="text" class="form-control" id="vs_date" style="width: 150px" readonly>
                    </div>
                    <div class="form-group" style="margin-left: 50px;">
                        <label for="creation_date">下单日期</label>
                        <input type="text" class="form-control" id="creation_date" style="width: 150px" readonly/>
                    </div>
                </div>
                <div class="form-inline" style=" width: 100% ;margin-top: 5px;">
                    <div class="form-group">
                        <label for="remark">升版备注</label>
                        <%--<input type="text" class="form-control" id="remark" style="width: 580px;" readonly>--%>
                        <textarea class="form-control" id="remark"  rows="2" cols="30" style="width: 740px; resize:none;"  readonly>
                        </textarea>
                    </div>
                </div>
                <div style="width: 100%;margin-top: 10px;">
                    <div style="width: 50%;float: left;">
                        <button type="button" class="btn btn-success btn-sm" selected="true" id='mo_btn' data-backdrop="static" />
                        &nbsp;&nbsp;MO要求&nbsp;&nbsp;
                        </button>
                        <button type="button" class="btn btn-info btn-sm" id='order_btn' data-backdrop="static" />
                        &nbsp;&nbsp;订单内容&nbsp;&nbsp;
                        </button>
                    </div>
                </div>
                <br/>
                <table id="moTable" url="/customerSopController/moPage"></table>
                <table id="orderTable" url="/customerSopController/orderPage" style="display: none;"></table>
            </div>
        </div>
    </div>
</section>

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