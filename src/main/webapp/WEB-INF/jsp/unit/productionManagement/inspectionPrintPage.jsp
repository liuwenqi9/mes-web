<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp" />
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/unit/js/productionManagement/inspectionPrintPage.js"></script>
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

<title>完工报检</title>
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
<body> <!--  style="padding: 50px 180px 0px;" -->
<div id='print_div'>
	<div align="center" id='print_div1' style="padding: 50px ;" >
    <table border="1" cellspacing="1" >
        <tr>   
                <table width="610" border="0" cellspacing="0" >
                		<tr>
                            <td width="200" height="41" align="left" ><img src="${pageContext.request.contextPath}/static/unit/img/p1.png"></td>
                            <td width="108" align="center"></td>
                            <th width="200"  align="center"> </th> 
                            <td width="108"  align="center"><img src="${pageContext.request.contextPath}/static/unit/img/p2.png"></td>
                      	</tr>
                      <tr>
                        <th width="160" height="43" align="left" >成品交检通知单</th>
                        <td width="200" align="center"></td>
                        <th width="108"  align="center"> </th> 
                        <th width="104" align="center" >${inspectionNumber}</th>
                      </tr>
                </table>
        </tr>
        <tr>
            
                 <table width="610" border="1" cellspacing="0">
                       <tr>
                        <th width="108" height="43" align="center" bgcolor="#CCCCCC">日&nbsp;&nbsp;&nbsp;&nbsp;期：</th>
                        <td width="200" align="center">${date }</td>
                        <th width="108" bgcolor="#CCCCCC" align="center">时&nbsp;&nbsp;&nbsp;&nbsp;间：</th>
                        <td width="200" align="center" >${hours}</td> 
                      </tr>
                      <tr>
                        <th width="108" height="43" align="center" bgcolor="#CCCCCC">标准机型：</th>
                        <td  align="center">${SEGMENT2}</td>
                        <th width="108" bgcolor="#CCCCCC" align="center">客户机型：</th>
                        <td width="200" align="center" >${CLIENT}</td> 
                      </tr>
                      <tr>
                       <th width="108" height="43" align="center" bgcolor="#CCCCCC">生产单号：</th>
                        <td width="200" align="center">${WIP_ENTITY_NAME }</td>
                        <th width="108" bgcolor="#CCCCCC" align="center">制造单位：</th>
                        <td width="200" align="center" >${PLAN_LINE}</td> 
                      </tr>
                      <tr>
                      <th width="108" height="43" align="center" bgcolor="#CCCCCC">数&nbsp;&nbsp;&nbsp;量：</th>
                        <td width="200" align="center">${packNumber}</td>
                        <th width="108" bgcolor="#CCCCCC" align="center">颜&nbsp;&nbsp;&nbsp;色：</th>
                        <td width="200" align="center" >&nbsp;</td> 
                      </tr>
                      <tr>
                        <th width="108" height="43" align="center" bgcolor="#CCCCCC">包装形式：</th>
                        <td width="200" align="center">&nbsp;</td>
                        <th width="108" bgcolor="#CCCCCC" align="center">交检人：</th>
                        <td width="200" align="center" >${loginName}</td> 
                      </tr>
                      <tr>
                        <th width="108" height="43" align="center" bgcolor="#CCCCCC">检验员：</th>
                        <td width="200" align="center">&nbsp;</td>
                        <th width="108" bgcolor="#CCCCCC" align="center">检验结果：</th>
                        <td width="200" align="center">&nbsp;</td> 
                      </tr>
                      <tr>
                        <th width="108" height="43" align="center" bgcolor="#CCCCCC">备&nbsp;&nbsp;&nbsp;注：</th>
                        <td width="200" align="center">&nbsp;</td>
                        <th width="108" bgcolor="#CCCCCC" align="center">&nbsp;</th>
                        <td width="200" align="center" >&nbsp;</td> 
                      </tr>  
                 </table>
            
        </tr>
        <tr><table  width="610" border="0" cellspacing="0" > 
        			<tr>
                        <th width="108" height="43" ></th>
                        <td width="200" align="center"></td>
                        <th width="108"  align="center"></th>
                        <td width="200" align="center" ><input type="button" class="btn btn-success btn-sm" id="print_btn" value='&nbsp;&nbsp;打&nbsp;&nbsp;&nbsp;印&nbsp;&nbsp;'/></td> 
                      </tr>
        
        </table></tr>
    </table>
	</div>
	</div>
</body>
</html>