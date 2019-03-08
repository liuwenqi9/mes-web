<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp" />
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/unit/js/productionManagement/special_business.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.css">
    <!-- jQuery 2.1.4 -->
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jquery-form/jquery-form.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/input-mask/jquery.inputmask.js"></script>
    <title>特殊业务处理</title>
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/css/bootstrap.min.css">
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
<!-- Main content  inspection-->
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <shiro:hasPermission name="special_business:product_reset">
                    <div style="width: 100%;margin-top: 10px;padding-bottom: 30px;">
                        <span style="font-size:15px;font-weight: bold;">产品条码重置(用于内外销属性维护错误-仅限未包装工单):</span>
                        <div style="width: 100%;">
                            <div class="form-inline" style="margin-top: 10px;">
                                <div class="form-group">
                                    <label for="product_word_order">工单号:</label>
                                    <input type="text" class="form-control" id="product_word_order" placeholder="工单号" />
                                </div>
                                <button type="button" class="btn btn-success btn-sm" style="margin-left: 260px;" id='product_reset' data-backdrop="static"/>
                                产品条码重置
                                </button>
                            </div>
                        </div>
                    </div>
                </shiro:hasPermission>
                <shiro:hasPermission name="special_business:package_reset">
                    <div style="width: 100%;margin-top: 10px;padding-bottom: 30px;">
                        <span style="font-size:15px;font-weight: bold;">包装条码重置(用于标准包装容量维护错误-仅限未包装工单):</span>
                        <div style="width: 100%;">
                            <form class="form-inline" style="margin-top: 10px;">
                                <div class="form-group">
                                    <label for="package_word_order">工单号:</label>
                                    <input type="text" class="form-control" id="package_word_order" placeholder="工单号" />
                                </div>
                                <div class="form-group">
                                    <label for="package_type">类型:</label>
                                    <select class="form-control" id="package_type">
                                        <c:forEach items="${listTypes}" var="map">
                                            <option value="${map.LOOKUP_CODE}">${map.DESCRIPTION}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <button type="button" class="btn btn-success btn-sm" style="margin-left: 135px;" id='package_reset' data-backdrop="static"/>
                                包装条码重置
                                </button>
                            </form>
                        </div>
                    </div>
                </shiro:hasPermission>
                <shiro:hasPermission name="special_business:mantissa_reset">
                    <div style="width: 100%;margin-top: 10px;padding-bottom: 30px;">
                        <span style="font-size:15px;font-weight: bold;">包装尾数重置(用于工单数量调整导致尾数箱数量变化):</span>
                        <div style="width: 100%;">
                            <form class="form-inline" style="margin-top: 10px;">
                                <div class="form-group">
                                    <label for="packing_box">包装箱:</label>
                                    <input type="text" class="form-control" id="packing_box" placeholder="工单号" />
                                </div>
                                <div class="form-group">
                                    <label for="updateNumber">修正数量:</label>
                                    <input type="text" class="form-control" id="updateNumber" placeholder="修正数量" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                                </div>
                                <button type="button" class="btn btn-success btn-sm" style="margin-left: 30px;" id='mantissa_reset' data-backdrop="static"/>
                                包装数量重置
                                </button>
                            </form>
                        </div>
                    </div>
                </shiro:hasPermission>
                <shiro:hasPermission name="special_business:additional_ok">
                    <div style="width: 100%;margin-top: 10px;padding-bottom: 30px;">
                        <span style="font-size:15px;font-weight: bold;">额外新增工单包装箱:</span>
                        <form class="form-inline" style="margin-top: 10px;">
                            <div class="form-group">
                                <label for="additional_work_order">工单号:</label>
                                <input type="text" class="form-control" id="additional_work_order" placeholder="工单号" />
                            </div>
                            <div class="form-group">
                                <label for="additional_types">类型:</label>
                                <select class="form-control" id="additional_types">
                                    <c:forEach items="${listTypes}" var="map">
                                        <option value="${map.LOOKUP_CODE}">${map.DESCRIPTION}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button type="button" class="btn btn-success btn-sm" style="margin-left: 140px;" id='additional_ok' data-backdrop="static"/>
                            &nbsp;&nbsp;确&nbsp;认&nbsp;&nbsp;
                            </button>
                        </form>
                    </div>
                </shiro:hasPermission>
                <shiro:hasPermission name="special_business:the_single_turn_ok">
                    <div style="width: 100%;margin-top: 10px;padding-bottom: 30px;">
                        <span style="font-size:15px;font-weight: bold;">工单转产(用于工单数量调整导致尾数箱变化):</span>
                        <div style="width: 100%;">
                            <div class="form-inline" style="margin-top: 10px;">
                                <div class="form-group">
                                    <label for="the_single_turn">包装箱:</label>
                                    <input type="text" class="form-control" id="the_single_turn" placeholder="包装箱" />
                                </div>
                                <button type="button" class="btn btn-success btn-sm" style="margin-left: 265px;" id='the_single_turn_ok' data-backdrop="static"/>
                                转产确认
                                </button>
                            </div>
                        </div>
                    </div>
                </shiro:hasPermission>
                <shiro:hasPermission name="special_business:planLineReset">
                    <div style="width: 100%;margin-top: 10px;padding-bottom: 30px;">
                        <span style="font-size:15px;font-weight: bold;">包装箱线别重置（只能更改本线包装出的包装箱）:</span>
                        <div style="width: 100%;">
                            <div class="form-inline" style="margin-top: 10px;">
                                <div class="form-group">
                                    <label for="pack_barcode">包装箱:</label>
                                    <input type="text" class="form-control" id="pack_barcode" placeholder="包装箱" />
                                </div>
                                <div class="form-group">
                                    <label for="productionLine_id">生产线:</label>
                                    <select type='text' class="form-control" style="width:120px; " id="productionLine_id"></select> 	
                                </div>
                                <button type="button" class="btn btn-success btn-sm" style="margin-left: 100px;" id='lind_code_btn' data-backdrop="static"/>
                                线别重置
                                </button>
                            </div>
                        </div>
                    </div>
                </shiro:hasPermission>
                <shiro:hasPermission name="special_business:wipNumReset">
                    <div style="width: 100%;margin-top: 10px;padding-bottom: 30px;">
                        <span style="font-size:15px;font-weight: bold;">工单总箱数:</span>
                        <div style="width: 100%;">
                            <div class="form-inline" style="margin-top: 10px;">
                                <div class="form-group">
                                    <label for="wipName">工单号:</label>
                                    <input type="text" class="form-control" id="wipName" placeholder="工单号" />
                                </div>
                                <div class="form-group">
                                    <label for="number">数量:</label>
                                    <input type="text" class="form-control" id="number" onkeyup="this.value=this.value.replace(/\D/g,'')" />
                                </div>
                                <button type="button" class="btn btn-success btn-sm" style="margin-left: 100px;" id='cm_btn' data-backdrop="static"/>
                                确认
                                </button>
                            </div>
                        </div>
                    </div>
                </shiro:hasPermission>

            </div>
        </div>
    </div>
</section>
</body>
</html>