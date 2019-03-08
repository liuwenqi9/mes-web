<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/unit/js/commonSetting/dataDictionary/editLookupType.js"></script>
<form id="lookup_type_form_edit" name="lookup_type_form_edit" method="post">
	<table>
		<tr>
			<td>来源系统类型MES/CRM:</td>
			<td>
				<input id="SOURCE_TYPE_edit" name="sourceType" style="width: 150px;" />
			</td>
			<td>类型:</td>
			<td>
				<input id="LOOKUP_TYPE_edit" name="lookupType" value="${param.lookupType}" readonly="readonly" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td>说明:</td>
			<td>
				<input id="DESCRIPTION_edit" name="description" style="width: 150px;" />
			</td>
			
		</tr>
		
		<tr>
			<td colspan="4" align="center">
				<a id="lookup_type_edit_btn" href="#">保存</a>
			</td>
		</tr>
	</table>
</form>