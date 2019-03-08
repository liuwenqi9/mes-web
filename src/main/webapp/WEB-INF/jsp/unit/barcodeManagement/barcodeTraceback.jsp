<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp"/>
    <script type="text/javascript" charset="UTF-8"
            src="${pageContext.request.contextPath}/static/unit/js/barcodeManagement/barcodeTraceback.js"></script>
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

    <title>产品条码打印</title>
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

<!-- Main content  inspection-->
<section class="content">

    <div class="form-inline" style="margin-top: 20px; margin-left: 10px;">
        <div class="form-group">
            <label for="mo" style="margin-left: 20px;">MO单号：</label> <input
                type="text" class="form-control" id="mo"
                style="margin-left: 10px;" placeholder="MO单"/>
        </div>
        <div class="form-group">
            <label for="workOrderNumber" style="margin-left: 20px;">工单：</label> <input
                type="text" class="form-control" id="workOrderNumber"
                style="margin-left: 10px;" placeholder="工单"/>
        </div>
    </div>

    <div class="form-inline" style="margin-top: 20px; margin-left: 10px;">
        <div class="form-group">
            <label for="productBarcode" style="margin-left: 20px;">产品条码：</label> <input
                type="text" class="form-control" id="productBarcode"
                style="margin-left: 10px;" placeholder="产品条码"/>
        </div>
        <button style="margin-left: 300px;" class="btn btn-success btn-sm"
                id="generate_btn">
            &nbsp;&nbsp;&nbsp;查&nbsp;&nbsp;&nbsp;询&nbsp;&nbsp;&nbsp;</button>
    </div>


    <div style="margin-top: 30px;margin-left:10px;">
        <ul id="myTab" class="nav nav-tabs ">
            <li class="active"><a href="#1" data-toggle="tab">基本信息</a></li>
            <li><a href="#2" data-toggle="tab">出货信息</a></li>
            <li><a href="#3" data-toggle="tab">退货信息</a></li>
            <li><a href="#4" data-toggle="tab">返修信息</a></li>
            <li><a href="#5" data-toggle="tab">杂项交易</a></li>
        </ul>
        <div class="tab-content">
            <!-- 基本信息 panel -->
            <div class="tab-pane fade in active" id='1'>
                <table id="baseInfoTable">1</table>
            </div>

            <!-- 出货信息 -->
            <div class="tab-pane fade" id='2'>
                <table id="shipmentInfoTable">2</table>
            </div> <!--./tb-pane.2 出货信息 -->

            <!-- 退货信息 -->
            <div class="tab-pane fade" id="3">
                <table id="refundInfoTable">3</table>
            </div><!-- 退货信息。end -->

            <!-- 返修信息 -->
            <div class="tab-pane fade" id="4">
                <table id="repairInfoTable">4</table>
            </div> <!-- 返修信息 。end-->

            <!-- 杂项交易-->
            <div class="tab-pane fade" id="5">
                <table id="sundryTransactionTable">5</table>
            </div>
        </div>

    </div>
    <!-- /.row -->
</section>
<!-- /.content -->


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