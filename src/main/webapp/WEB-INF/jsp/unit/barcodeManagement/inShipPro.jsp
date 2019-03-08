<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp"/>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/unit/js/barcodeManagement/inShipPro.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.css">
    <!-- jQuery 2.1.4 -->
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jquery-form/jquery-form.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/input-mask/jquery.inputmask.js"></script>
    <title>导入ship条码</title>
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
                 <span style="font-size:18px;font-weight: bold;">导入产品条码:</span>
                <shiro:hasPermission name="inShipProController:findAll">
                <ul>
                   <%-- <span style="font-size:15px;font-weight:400;">说明:</span>--%>
                    <ol>
                        <li><span style="font-size:12px;font-weight:400;">无工单、编码与系统已有条码关联</span></li>
                        <li><span style="font-size:12px;font-weight:400;">外印条码与编码关联</span></li>
                    </ol>
                </ul>
                 </shiro:hasPermission>
                <table cellspacing="0" style="width: 100%;margin-top: 20px;" id="lineTable">
                <thead>
                <tr> 
                	<th style="text-align: center; width: 130px;height: 50px; font-size:14px;font-weight: bold;">产品条码</th>
                	<th style="text-align: center; width: 130px;height: 50px; font-size:14px;font-weight: bold;">物料描述</th>
                	<th style="text-align: center; width: 10px;height: 50px; font-size:14px;font-weight: bold;"></th> 
                    <th style="text-align: center; width: 130px;height: 50px; font-size:14px;font-weight: bold;">物料编码</th>
                    <th style="text-align: center; width: 130px;height: 50px; font-size:14px;font-weight: bold;">产品型号</th> 
                    <th style="text-align: center; width: 130px;height: 50px; font-size:14px;font-weight: bold;">操 &nbsp;&nbsp;作</th>  
                </tr>
                </thead>
                <tbody>
                <tr style="text-align: center; width: 150px;height: 30px;">
               		<td >
                        <input name="barcodeText" class="form-control" style="margin-left:80px;width: 150px;"/> 
                    </td>
                    <td> 
                        <input name="description" class="form-control" style="margin-left:50px;width: 300px;" readonly="readonly"/> 
                    	<input type="hidden" name="inventoryItemId"> 
                   		 
                    </td>
                    <td>
                    <button class="btn btn-success btn-sm" name="choose" onclick="clicks(this)"; data-backdrop="static" data-toggle="modal" readonly>
                          <i class="glyphicon glyphicon-search"></i>选择
                   		</button>
                    </td> 
                    <td>
                        <input name="segment1" class="form-control" style="margin-left:50px;width: 150px;"  readonly="readonly"/>  
                    </td>
                    <td>
                        <input name="prodType" class="form-control" style="margin-left:18px;width: 150px;"  readonly="readonly"/>                    	
                    </td>  
                    <td>
                        <a href="javascript:void(0);" onclick="plus(this)"><i class="glyphicon glyphicon-plus"></i></a>
                       &nbsp;
                        <a href="javascript:void(0);" onclick="minus(this)"><i class="glyphicon glyphicon-minus"></i></a>
                    </td> 
                </tr>
                </tbody>
            </table>
            <div style="width: 100%;margin-top: 50px;padding-bottom: 30px;">
	            <div  style="margin-left: 500px" >
	           		 <button type="button" class="btn btn-success btn-sm" style="margin-left: 500px;" id='update' data-backdrop="static" data-target="#packageBarcode_generate_modal"/>
	                            &nbsp;&nbsp;保存&nbsp;&nbsp;
	                 </button>
	            </div>
             </div>
            
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
                    查询
                </h4>
            </div>
            <div class="modal-body">
                <div class="form-inline" style="margin-bottom: 10px;">
                    <div class="form-group">
                        <input type="text" class="form-control" id="find_name" placeholder="输入型号，编码，描述" />
                    </div>
                    <button type="button" class="btn btn-info btn-sm" id='search' data-backdrop="static"/>
                        <i class="glyphicon glyphicon-search"></i>查询
                    </button>
                </div>
                <table id="modalTable"></table>
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