<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp"/>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/unit/js/barcodeManagement/productBarcode.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.css">
    <!-- jQuery 2.1.4 -->
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jquery-form/jquery-form.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/input-mask/jquery.inputmask.js"></script>

    <title>产品条码打印</title>
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
                        <label for="workOrderNumber">工单</label>
                        <input type="text" class="form-control" id="workOrderNumber" placeholder="工单" style="margin-left: 27px;"/>
                    </div>
                    <button class="btn btn-success btn-sm" id="choose" data-backdrop="static" data-toggle="modal">
                        <i class="glyphicon glyphicon-search"></i>选择
                    </button>
                    <div class="form-group" style="margin-left: 50px;">
                        <label for="productBarcode">区域条码或单个条码</label>
                        <input type="text" class="form-control" id="productBarcode" style="width: 220px;" placeholder="如：C171280027-C171280040" />
                    </div>
                    <div style="width: 100%;margin-top: 10px;">
                        <div class="form-group">
                            <label for="workStatus">条码状态</label>
                            <select class="form-control" id="workStatus" style="width: 170px;">
                                <option value="" selected="selected">---请选择---</option>
                                <c:forEach items="${codeStatus}" var="statu">
                                    <option value='${statu}'>${statu}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group" style="margin-left: 87px;">
                            <label for="printStatus">打印状态</label>
                            <select class="form-control" id="printStatus" style="width: 170px;">
                                <option value="" selected="selected">---请选择---</option>
                                <option value='Y'>Y</option>
                                <option value='N'>N</option>
                            </select>
                        </div>
                        <button type="button" class="btn btn-info btn-sm" id='productBarcode_search_btn' data-backdrop="static"/>
                        <i class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;查&nbsp;&nbsp;询&nbsp;&nbsp;
                        </button>
                    </div>
                </div>
                <div style="width: 100%;margin-top: 10px;">
                    <div style="width: 70%;float: left;">
                        <button type="button" class="btn btn-success btn-sm" id='productBarcode_abolish_btn' data-backdrop="static" data-target="#packageBarcode_generate_modal"/>
                        &nbsp;&nbsp;条码作废&nbsp;&nbsp;
                        </button>
                    </div>
                    <div style="width: 30%;float: left;" align="right">
                        <button type="button" class="btn btn-success btn-sm" id='productBarcode_generate_btn' data-backdrop="static" data-target="#packageBarcode_generate_modal"/>
                        &nbsp;&nbsp;条码生成&nbsp;&nbsp;
                        </button>
                        <!-- <button type="button" class="btn btn-success btn-sm" style="margin-left: 87px;"id='productBarcode_print_btn' data-backdrop="static" data-target="#packageBarcode_generate_modal"/>
                        &nbsp;&nbsp;打印(二维码)&nbsp;&nbsp;
                        </button> -->
                        <div class="btn-group">
							<button type="button" class="btn btn-success dropdown-toggle btn-sm" style="margin-left: 87px;" data-toggle="dropdown">
								打印(二维码) <span class="caret"></span>
							</button>
							<ul class="dropdown-menu " role="menu"> 
							<li><button type="button" class="btn btn-success btn-sm"   id='productBarcode_print_btn1' data-backdrop="static" data-target="#packageBarcode_generate_modal"/>
				                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;打印一份&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                        </button></li>
								<!-- <li><a href="#"  class=" " id='productBarcode_print_btn1'>打印一份</a></li> -->
								<li class="divider"></li>
								<li><button type="button" class="btn btn-success btn-sm"   id='productBarcode_print_btn' data-backdrop="static" data-target="#packageBarcode_generate_modal"/>
				                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;打印两份&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                        </button></li>
								<!-- <li><a href="#" class=" "  id='productBarcode_print_btn'>打印两份</a></li> -->
							</ul>
						</div>
                    </div>
                </div>
                <table id="productBarcodeTable"></table>
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
<%--对话框--%>
<div class="modal fade" id="confirm" tabindex="-1"
     role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="" id="deleteUserForm" method="post">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="exampleModalLabel">
                        确认作废
                    </h4>
                </div>
                <div class="modal-body">
                    <form role="form">
                        <div class="form-group">
                            <label for="invalidReason">作废原因:</label>
                            <textarea class="form-control" rows="3" id="invalidReason"></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-danger" id="confirm_ok">确认</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>