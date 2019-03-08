<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp" />
<jsp:include page="/WEB-INF/jsp/egdfrm/base/head_easyui_expand.jsp" />
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/egdfrm/common/tree.loadFilter.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/unit/js/commonSetting/dataDictionary/lookupMain.js"></script>
<title>数据字典</title>
</head>
<body class="easyui-layout" data-options="fit:true">

	<div data-options="region:'center'">

		<div id="lookup_type_table_param" style="padding: 5px; height: auto;">
			<div style="padding: 2px;">

				<a id="lookup_type_table_search_btn" href="#"
					class="easyui-linkbutton" data-options="plain:true">查询</a>
				<shiro:hasPermission name="lookupController:add">
					<a id="lookup_type_table_add_btn" href="#"
						class="easyui-linkbutton" data-options="plain:true">新增</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="lookupController:edit">
					<a id="lookup_type_table_edit_btn" href="#"
						class="easyui-linkbutton" data-options="plain:true">修改</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="lookupController:delete">
					<a id="lookup_type_table_delete_btn" href="#"
						class="easyui-linkbutton" data-options="plain:true">删除</a>
				</shiro:hasPermission>

			</div>
			数据类型: <input type="text" id="lookupTypeSearch" name="loginNameSearch"
				size="15" />

		</div>

		<table id="lookup_type_table"></table>

		<div id="lookup_type_window_add"></div>
		<div id="lookup_type_window_edit"></div>
	</div>
	<div data-options="region:'east',split:true" style="width: 600px;">
		<div id="lookup_table_param" style="padding: 5px; height: auto;">
			<div style="padding: 2px;">
				<shiro:hasPermission name="lookupController:add">
					<a id="lookup_table_add_btn" href="#" class="easyui-linkbutton"
						data-options="plain:true">新增</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="lookupController:edit">
					<a id="lookup_table_edit_btn" href="#" class="easyui-linkbutton"
						data-options="plain:true">修改</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="lookupController:delete">
					<a id="lookup_table_delete_btn" href="#" class="easyui-linkbutton"
						data-options="plain:true">删除</a>
				</shiro:hasPermission>
			</div>
		</div>
		<table id="lookup_table"></table>
		<div id="lookup_window_add"></div>
		<div id="lookup_window_edit"></div>

	</div>
</body>
</html>