<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/js/org/orgAdd.js"></script>
<form id="org_form_add" name="org_form_add" method="post">
	<table>
		<tr>
			<td>组织代码:</td>
			<td>
				<input id="orgCode_add" name="orgCode" style="width: 150px;" />
			</td>
			<td>组织名称:</td>
			<td>
				<input id="orgName_add" name="orgName" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td>上级代码:</td>
			<td>
				<input id="parentOrgCode_add" name="parentOrgCode" value="${parentOrgCode}" readonly="readonly" style="width: 150px;" />
			</td>
			<td>功能排序:</td>
			<td>
				<input id="org_sort_add" name="orgSort" value="1" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<a id="org_save_add_btn" href="#">保存</a>
			</td>
		</tr>
	</table>
</form>