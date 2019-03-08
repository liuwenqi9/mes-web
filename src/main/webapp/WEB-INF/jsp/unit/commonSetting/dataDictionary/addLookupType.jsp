<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/unit/js/commonSetting/dataDictionary/addLookupType.js"></script>
<form id="lookup_type_form_add" name="lookup_type_form_add" method="post">
	<table>
		<tr>
			<td>来源系统类型MES/CRM:</td>
			<td>
				<input id="SOURCE_TYPE_add" name="sourceType" style="width: 150px;" />
			</td>
			<td>类型:</td>
			<td>
				<input id="LOOKUP_TYPE_add" name="lookupType" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td>说明:</td>
			<td>
				<input id="DESCRIPTION_add" name="description" style="width: 150px;" />
			</td>
			
		</tr>
		
		<tr>
			<td colspan="4" align="center">
				<a id="lookup_type_add_btn" href="#">保存</a>
			</td>
		</tr>
	</table>
</form>