<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp"/>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/unit/js/reportManagement/saot_report.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.css">
    <!-- jQuery 2.1.4 -->
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jquery-form/jquery-form.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/input-mask/jquery.inputmask.js"></script>
    <title>借机统计报表</title>
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
                    <div style="width: 100%;margin-top: 10px;">
                        <div style="width: 70%;float: left;">
                            <div class="form-group" >
                                <label for="startTime">借机日期</label>
                                <input type="text" class="form-control" style=" width: 110px;" id="startTime" placeholder="yyyy-MM-dd" />
                            </div>
                            <div class="form-group" >
                                <label for="endTime">至</label>
                                <input type="text" class="form-control" style=" width: 110px;" id="endTime" placeholder="yyyy-MM-dd" />
                            </div>
                            <div class="form-group">
                                <label for="state">状态</label>
                                <select class="form-control" style=" width: 100px;" id="state">
                                    <option>--请选择--</option>
                                    <option value="N">未归还</option>
                                </select>
                            </div> 
                            <div class="form-group" >
                                <label for="xx">物料编码</label>
                                  <input type="text" class="form-control" style=" width: 130px;" id="code" placeholder="物料编码" />
                            </div> 
                        </div> 
                        <!-- 计划归还日期 -->
                        <div style="width: 70%;float: left;margin-top: 10px">
                            <div class="form-group">
                                <label for="planReturnStartTime">计划归还日期</label>
                                <input type="text" class="form-control"  style=" width: 110px;" id="planReturnStartTime" placeholder="yyyy-MM-dd" />
                            </div>
                            <div class="form-group">
                                <label for="planReturnEndTime">至</label>
                                <input type="text" class="form-control"  style=" width: 110px;" id="planReturnEndTime" placeholder="yyyy-MM-dd" />
                            </div>
                             <div class="form-group" >
                                <label for="xx">借机单号</label>
                                  <input type="text" class="form-control" style=" width: 100px;" id="jjNumber" placeholder="借机单号" />
                            </div>
                            <button type="button" class="btn btn-info btn-sm" style=" width: 100px; margin-left: 135px;"id='search_btn' data-backdrop="static"/>
                            <i class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;查&nbsp;&nbsp;询&nbsp;&nbsp;
                            </button>
                        </div>
                        
                        <div style="width: 30%;float: left;" align="right">
                            <button type="button" class="btn btn-success btn-sm" id='exportExcel' data-backdrop="static"/>
                            &nbsp;&nbsp;导出Excel&nbsp;&nbsp;
                            </button>
                        </div>
                        
                    </div>
                </div>
                <table id="soatTable"></table>
            </div>
        </div>
    </div>
</section>
</body>
</html>