<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp" />
 <script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/unit/js/productionManagement/invNumberPrinter.js"></script> 
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
<body> <!--  style="padding: 50px 0px 0px;" -->
<div id='print_div'>
 <div align="center" id='print_div1' style="padding: 50px  " >
 <table  border="0" cellspacing="0">
 	<tr >
       		 <tbody>
                <table width="100%" border="0" cellspacing="0">
                 <tr>
                     <td width="30%" height="41" align="left" ><img src="${pageContext.request.contextPath}/static/unit/img/p1.png"></td> 
                     <td width="50%" align="center" ></td>
                     <td width="20%"  align="center"><img src="${pageContext.request.contextPath}/static/unit/img/p2.png"></td>                        
                 </tr>
                 <tr height="30">                          
                 </tr>
                  
                </table>
              </tbody> 
       </tr> 
     <tr >
       		 <tbody>
                <table width="100%" border="0" cellspacing="0">
                  <tr>
                    <td align="center"><strong>入库通知单</strong></td>  
                  </tr>
                  <tr>
                    <td height="20" align="center"></td>  
                  </tr>
                </table>
              </tbody> 
       </tr>
    
 	   <tr > 
             <tbody>
                <table width="100%" border="0" cellspacing="0">
                  <tr>
                    <th width="15%" height="40"  >编号：</th>
                    <th width="15%" align="left">${INV_NUMBER}</th>  
                    <th width="10%" >子库：</th>
                    <th width="10%" align="left">${SUBINVENTORY_CODE}</th>  
                    <th width="15%"  >总箱数：</th>
                    <th width="10%" align="left">14</th>  
                    <th width="10%" >日期：</th>
                    <th width="15%" align="left" >${DATE}</th>   
                  </tr>
                </table>
              </tbody> 
       </tr>
       <tr> 
             <tbody> <input type="hidden" id='invNumber' value="${INV_NUMBER}"/>
                <table id='table' width="100%" border="1" cellspacing="0">
                  <tr>
                    <td  data-field='MO_ORDER' align="center" width="10%" height="35" >订单号</td>
                    <td  data-field='WIP_ENTITY_NAME' align="center" width="10%">任务号</td>
                    <td  data-field='PLAN_LINE' align="center" width="10%">线别</td>
                    <td  data-field='SEGMENT2' align="center" width="10%">型号</td>
                    <td  data-field='CLIENT' align="center" width="10%">客户机型</td>
                    <td  data-field='SEGMENT2' align="center" width="10%">物料编码</td>
                    <td  data-field='PACK_QUANTITY' align="center" width="10%">入库数量</td>
                    <td  data-field='PACK_QUANTITY' align="center" width="10%">建议货位</td>
                    <td  data-field='data' align="center" width="15%">备注</td> 
                  </tr>
             <%--  <c:forEach var="lists" items="$ {list}" >       
                  <tr>
                    <td align="center" height="30"><c:out value="${lists.MO_ORDER}" /></td>
                    <td align="center"><c:outvalue="${lists.WIP_ENTITY_NAME}"/> </td>
                    <td align="center"><c:out value="${lists.plan_line }" default="wang"/></td>
                    <td align="center"><c:out value="${lists.SEGMENT2}" default="wang"/></td>
                    <td align="center"><c:out value="${lists.CLIENT}" default="wang"/></td>
                    <td align="center"><c:out value="${lists.SEGMENT1}" default="wang"/></td>
                    <td align="center"><c:out value="${lists.PACK_QUANTITY}" default="wang"/></td>
                    <td align="center"><c:out value="${lists.LOCATTION_CODE}" default="wang"/></td>
                    <td align="center">&nbsp;</td>
                  </tr>
                 </c:forEach>    --%>
                </table>
              </tbody> 
       </tr>
       <tr >
       		 <tbody>
                <table width="100%" border="0" cellspacing="0">
                  <tr height="100">
                    <td height="100">&nbsp;</td>  
                  </tr>
                </table>
              </tbody> 
       </tr>	
       <tr > 
             <tbody>
                <table width="100%" border="0" cellspacing="0">
                  <tr>
                    <td width="15%" height="40">填表：</td>
                    <td width="10%">&nbsp;</td>  
                    <td width="15%">审批：</td>
                    <td width="10%">&nbsp;</td>  
                    <td width="15%">QA/QC：</td>
                    <td width="10%">&nbsp;</td>  
                    <td width="15%">收货：</td>
                    <td width="10%">&nbsp;</td>   
                  </tr>
                  <tr heigth="15"></tr> 
                </table>
              </tbody> 
       </tr>
 
 		<tr > 
             <tbody>
                <table width="100%" border="0" cellspacing="0">
                  <tr>
                    <td width="80%">&nbsp; </td>
                    <td width="20%">
                     <input type="button" class="btn btn-success btn-sm" onclick ='printdiv();' id="print_btn" value='&nbsp;&nbsp;打&nbsp;&nbsp;&nbsp;印&nbsp;&nbsp;'/>
                    </td>     
                  </tr> 
                </table>
              </tbody> 
       </tr>
   
</table>
</div>
</div>

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

