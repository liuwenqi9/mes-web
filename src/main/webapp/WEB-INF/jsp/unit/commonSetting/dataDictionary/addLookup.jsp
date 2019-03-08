<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/unit/js/commonSetting/dataDictionary/addLookup.js"></script>
<form id="lookup_form_add" name="lookup_form_add" method="post">
	<table>
		<tr>
			<td>代码:</td>
			<td>
				<input id="LOOKUP_CODE_add" name="lookupCode" style="width: 150px;" />
			</td>
			<td>是否有效:</td>
			<td>
				<input id="ENABLED_FLAG_add" name="lookupCode" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td>说明:</td>
			<td>
				<input id="DESCRIPTION_add" name="description" style="width: 150px;" />
			</td>
			<td>开始生效日期:</td>
			<td>
				<input id="START_DATE_ACTIVE_add" name="startDateActive" style="width: 150px;" />
			</td>
			
		</tr>
		<tr>
			
			<td>结束日期:</td>
			<td>
				<input id="END_DATE_ACTIVE_add" name="endDateActive" style="width: 150px;" />
			</td>
			
		</tr>
		
		<tr>
			<td colspan="4" align="center">
				<a id="lookup_add_btn" href="#">保存</a>
			</td>
		</tr>
	</table>
</form>