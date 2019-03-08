<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp"/>
    <script type="text/javascript" charset="UTF-8"
            src="${pageContext.request.contextPath}/static/unit/js/reportManagement/productionStatisticsDaliyReport.js"></script>
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

    <title>生产统计日报表</title>
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
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-body table-responsive">
                    <div id="productionStatistics_toolbar" class="btn-group">
                        <label class="col-sm-1 control-label">生产线:</label>
                        <label class="col-sm-2 control-label">
                            <select type='text' class="form-control" id="productionLine_id"></select>
                        </label>

                        <label for="date_from_id" class="col-sm-1 control-label">日期:</label>
                        <label for="date_from_id" class="col-sm-2 control-label">
                            <input type="text" class="form-control" id="date_from_id" data-options="required:true">
                        </label>
                        <label for="date_to_id" class="col-sm-1 control-label">至:</label>
                        <label for="date_from_id" class="col-sm-2 control-label">
                            <input type="text" class="form-control" id="date_to_id" data-options="required:true">
                        </label>
                        <label class="col-sm-1 control-label">&nbsp;</label>
                        <label class="col-sm-1 ">
                            <button type="button" class="btn btn-info btn-sm" id='productionStatistics_search_btn' data-backdrop="static"/>
                                <i class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;查&nbsp;&nbsp;询&nbsp;&nbsp;
                            </button>
                        </label>
                        <label class="col-sm-1 ">
                            <button type="button" class="btn btn-success btn-sm" id='productionStatistics_export_btn' data-backdrop="static"/>
                                <i class="glyphicon glyphicon-download-alt"></i>&nbsp;&nbsp;导出EXCEL&nbsp;&nbsp;
                            </button>
                        </label>
                    </div>
                    <table id="productionStatisticsTable" data-pagination="true"
                           data-page-size="10" data-page-list="[5,10,20,50,100]"
                           data-pagination-first-text="First" data-pagination-pre-text="上一页"
                           data-pagination-next-text="下一页" data-pagination-last-text="Last"
                           data-locale="zh-CN" data-show-columns="true"
                           data-toolbar="#productionStatistics_toolbar" data-click-to-select="true"
                           data-single-select="true">
                        <thead>
                        <tr>
                            <th data-field="check" data-checkbox="true"></th>
                            <th data-field="PLAN_LINE_CODE">生产线</th>
                            <th data-field="WIP_ENTITY_NAME">工单号</th>
                            <th data-field="SEGMENT1">物料编号</th>
                            <th data-field="SEGMENT2">型号</th>
                            <th data-field="MO_ORDER">MO号</th>
                            <th data-field="START_QUANTITY">工单数量</th>
                            <th data-field="TRANSACTION_QUANTITY">生产数量</th>
                            <th data-field="CHECK_QUANTITY">总检验数</th>
                            <th data-field="INV_QUANTITY">总入库数</th>
                            <th data-field="DESCRIPTION">描述</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>