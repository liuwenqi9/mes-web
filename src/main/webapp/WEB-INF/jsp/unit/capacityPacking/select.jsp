<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/unit/js/capacityPacking/select.js"></script>
<div id = 'dg'></div>
<div data-options="region:'center'">
	<div id="select_table_param" style="padding: 5px; height: auto;">
		<div style="padding: 2px;">
			物料编码: <input type="text" id="select_SEGMENT1" name="select_SEGMENT1" size="15" />
			产品类型: <input type="text" id="select_PROD_TYPE" name="select_PROD_TYPE" size="15" />
			<a id="select_table_search_btn" href="#" class="easyui-linkbutton"
				data-options="plain:true">查询</a>
		</div>
	</div>