<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp"/>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/unit/js/stock/seizeAnOpportunity_manage.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.css">
    <!-- jQuery 2.1.4 -->
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jquery-form/jquery-form.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/input-mask/jquery.inputmask.js"></script>
    <title>成品借机</title>
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
                        <label for="jj_summary_number">借机单号</label>
                        <input type="text" class="form-control" id="jj_summary_number" placeholder="借机单号"/>
                    </div>
                    <div style="width: 100%;margin-top: 10px;">
                        <div class="form-group">
                            <label for="startTime">创建日期</label>
                            <input type="text" class="form-control" id="startTime" placeholder="yyyy-MM-dd" />
                        </div>
                        <div class="form-group">
                            <label for="endTime">至</label>
                            <input type="text" class="form-control" id="endTime" placeholder="yyyy-MM-dd" />
                        </div>
                        <button type="button" class="btn btn-info btn-sm" id='jj_summary_search_btn' data-backdrop="static"/>
                        <i class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;查&nbsp;&nbsp;询&nbsp;&nbsp;
                        </button>
                    </div>
                    <div style="width: 100%;margin-top: 10px;">
                        <div style="width: 50%;float: left;">
                            <shiro:hasPermission name="seizeAnOpportunity:add">
                                <button id="saopAdd" class="btn btn-primary btn-sm" data-backdrop="static"/>
                                    <i class="glyphicon glyphicon-plus"></i>&nbsp;新增&nbsp;
                                </button>
                            </shiro:hasPermission>
                            &nbsp;
                            <shiro:hasPermission name="seizeAnOpportunity:deleteHeaders">
                                <button id="delete_header" class="btn btn-danger btn-sm" data-backdrop="static"/>
                                    <i class="glyphicon glyphicon-trash"></i>&nbsp;删除&nbsp;
                                </button>
                            </shiro:hasPermission>
                            &nbsp;
                            <shiro:hasPermission name="seizeAnOpportunity:express">
                                <button id="expressBtn" class="btn btn-primary btn-sm" data-backdrop="static"/>物流管理</button>
                            </shiro:hasPermission>
                        </div>
                        <div style="width: 50%;float: left;" align="right">
                            <button type="button" class="btn btn-success btn-sm" id='printer_btn' data-backdrop="static"/>
                            &nbsp;&nbsp;打印&nbsp;&nbsp;
                            </button>
                        </div>
                    </div>
                </div>
                <table id="seizeAnOpportunitySummaryTable"></table>
            </div>
        </div>
    </div>
</section>
<!-- 模态框（Modal） 新增 -->
<div class="modal fade bs-example-modal-lg" id="myModalAdd" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg" role="document" style="width:1050px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">借机新增</h4>
            </div>
            <div class="modal-body">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="jj_add_save">保存</button>
            </div>
        </div>
    </div>
</div>
<!-- 模态框（Modal） 详情 -->
<div class="modal fade bs-example-modal-lg" id="myModalDetailed" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg" role="document" style="width:1050px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">借机详情</h4>
            </div>
            <div class="modal-body" id="myModalDetailedBody">

            </div>
        </div>
    </div>
</div>
<%--物料管理模态框--%>
<div class="modal fade bs-example-modal-lg" id="expressModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg" role="document" style="width: 500px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">物流管理</h4>
            </div>
            <div class="modal-body" style="height: 200px;">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="express_save">保存</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>