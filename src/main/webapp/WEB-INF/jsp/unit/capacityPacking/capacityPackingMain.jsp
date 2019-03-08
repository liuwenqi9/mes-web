<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp" />
<jsp:include page="/WEB-INF/jsp/egdfrm/base/head_easyui_expand.jsp" />
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/unit/js/capacityPacking/capacityPackingMain.js"></script>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/common/tree.loadFilter.js"></script>
<title>包装箱容量设置</title>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center'">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'">
				<div id="capacityPacking_table_param" style="padding: 5px; height: auto;">
					<div style="padding: 2px;"> 
						<a id="capacityPacking_table_search_btn" href="#" class="easyui-linkbutton" data-options="plain:true">查询</a>
						<shiro:hasPermission name="packagecontentController:add">
							<a id="capacityPacking_table_add_btn" href="#" class="easyui-linkbutton" data-options="plain:true">新增</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="userController:edit">
							<a id="capacityPacking_table_edit_btn" href="#" class="easyui-linkbutton" data-options="plain:true">修改</a>
						</shiro:hasPermission> 
					</div>
					物料编码:
					<input type="text" id="SEGMENT1" name="SEGMENT1" size="15" /> 
				</div>
				<table id="capacityPacking_table"></table>
				<div id="capacityPacking_window_add"></div>
				<div id="capacityPacking_window_edit"></div>
			</div> 
		</div>
	</div> 
</body>
</html>