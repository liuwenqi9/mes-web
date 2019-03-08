<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp"/>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/unit/js/barcodeManagement/inforeview_manage.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.css">
    <!-- jQuery 2.1.4 -->
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jquery-form/jquery-form.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/input-mask/jquery.inputmask.js"></script>

    <title>条码信息追溯</title>
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
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="form-inline" style="margin-top: 10px;">
                    <div class="form-group">
                        <label for="barcodeNum">产品条码</label>
                        <input type="text" class="form-control" id="barcodeNum" placeholder="条码" />
                    </div>
                    <div class="form-group" style="margin-left: 50px;">
                        <label for="workOrderNumber">工单</label>
                        <input type="text" class="form-control" id="workOrderNumber" placeholder="工单"/>
                    </div>
                    <button class="btn btn-info btn-xs" id="choose" data-backdrop="static" data-toggle="modal">
                        <i class="glyphicon glyphicon-search"></i>选择
                    </button>
                    <div style="width: 100%;margin-top: 10px;">
                        <div class="form-group">
                            <label for="moNumber">MO单</label>
                            <input type="text" class="form-control" id="moNumber" placeholder="MO单" style="margin-left: 15px;"/>
                        </div>
                        <button type="button" class="btn btn-primary btn-sm" id='inforeview_search_btn' data-backdrop="static" style="margin-left: 52px;"/>
                        <i class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;查&nbsp;询&nbsp;&nbsp;
                        </button>
                        <button id="exportExcel" class="btn btn-success btn-sm" data-backdrop="static" style="margin-left: 100px;"/>
                        &nbsp;&nbsp;导出Excel&nbsp;&nbsp;
                     </button>
                        
                    </div>
                </div>
                <div style="width: 100%;margin-top: 10px;">
                    <div style="width: 50%;float: left;">
                        <button type="button" class="btn btn-success btn-sm" selected="true" id='basic_btn' data-backdrop="static" />
                        &nbsp;&nbsp;基本信息&nbsp;&nbsp;
                        </button>
                        <button type="button" class="btn btn-info btn-sm" id='shipment_btn' data-backdrop="static" />
                        &nbsp;&nbsp;出货信息&nbsp;&nbsp;
                        </button>
                        <button type="button" class="btn btn-info btn-sm" id='back_btn' data-backdrop="static" />
                        &nbsp;&nbsp;退货信息&nbsp;&nbsp;
                        </button>
                        <button type="button" class="btn btn-info btn-sm" id='repair_btn' data-backdrop="static" />
                        &nbsp;&nbsp;返修信息&nbsp;&nbsp;
                        </button>
                        <button type="button" class="btn btn-info btn-sm" id='miscellaneous_btn' data-backdrop="static" />
                        &nbsp;&nbsp;杂项交易&nbsp;&nbsp;
                        </button>
                    </div>
                </div>
                <table id="basicTable" url="/info/review/basicPage"></table>
                <table id="shipmentTable" url="/info/review/shipmentPage" style="display: none;"></table>
                <table id="backTable" url="/info/review/backPage" style="display: none;"></table>
                <table id="repairTable" url="/info/review/repairPage" style="display: none;"></table>
                <table id="miscellaneousTable" url="/info/review/miscellaneousPage" style="display: none;"></table>
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
                    工单号查询
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
</body>
</html>