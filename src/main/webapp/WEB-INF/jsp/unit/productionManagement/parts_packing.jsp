<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp" />
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/unit/js/productionManagement/parts_packing.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.css">
    <!-- jQuery 2.1.4 -->
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jquery-form/jquery-form.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/input-mask/jquery.inputmask.js"></script>
    <title>入库单打印 </title>
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
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
<body>
<div style="width: 100%;height: 100%;">
    <div style="width: 100%;height: 60%;">
        <div style="width: 60%;float: left;">
            <section class="content" style="padding: 10px;">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                            <div class="form-inline" style="margin-top: 10px;margin-bottom: 10px;">
                                <div class="form-group" style="margin-left: 20px;">
                                    <label for="workOrderNumber">工&nbsp;&nbsp;单&nbsp;号</label>
                                    <input type="text" class="form-control" id="workOrderNumber" placeholder="工单号" />
                                </div>
                                <button type="button" class="btn btn-info btn-sm" id='partsPacking_btn' data-backdrop="static"/>
                                <i class="glyphicon glyphicon-search"></i>查询
                                </button>
                            </div>
                            <table id="partsPackingTable"></table>
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <div style="width:40%;float: left;">
            <section class="content" style="padding: 10px;">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box" style="height: 405px;">
                            <table cellspacing="0" style="width: 100%;margin-top: 60px;">
                                <tr>
                                    <th style="text-align: center;height: 30px;">物料编码</th>
                                    <th style="text-align: center;height: 30px;">可包装数量</th>
                                    <th style="text-align: center;height: 30px;">包装数量</th>
                                </tr>
                            </table>
                            <div style="height: 270px;overflow: auto;" id="check_parts"></div>
                            <div style="position: absolute;bottom: 3px;right:3px;width: 100%;" align="right">
                                <button type="button" class="btn btn-info btn-sm" id='partsPacking_btn_ok' data-backdrop="static"/>
                                    <i class="glyphicon glyphicon-ok"></i>确认
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
    <div style="width: 100%;clear: both;position: relative;top: -10px;">
        <section class="content" style="padding: 10px;">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <table id="partsTable"></table>
                        <div style="width: 100%;height: 30px;" align="right">
                            <button type="button" class="btn btn-info btn-sm" id='print_btn' data-backdrop="static"/>
                            <i class="glyphicon glyphicon-print"></i>打印
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>
</body>
</html>