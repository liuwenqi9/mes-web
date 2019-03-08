<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/js/user/userEdit.js"></script>
<form id="user_form_edit" name="user_form_edit" method="post">
<span hidden="true">
	<input id="userId_edit" name="userId" style="width: 150px;" />
</span>
	<table>
		<tr>
			<td>登录名称:</td>
			<td>
				<input id="loginName_edit" name="loginName" value="${param.loginName}" readonly="readonly" style="width: 150px;" />
			</td>
			<td>用户名称:</td>
			<td>
				<input id="userName_edit" name="userName" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td>用户座机:</td>
			<td>
				<input id="userPhone_edit" name="userPhone" style="width: 150px;" />
			</td>
			<td>用户手机:</td>
			<td>
				<input id="userMPhome_edit" name="userMPhome" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td>电子邮件:</td>
			<td>
				<input id="userEmail_edit" name="userEmail" style="width: 150px;" />
			</td>
			<td>用户状态:</td>
			<td>
				<input id="userStatus_edit" name="userStatus" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td>绑定供应商:</td>
			<td colspan="3">
				<input id="supplierId_edit" name="supplierId" type="hidden"/>
				<input id="supplierName_edit" name="supplierName" style="width: 150px;" />
				<a id="supplier_edit_search_btn" class="easyui-linkbutton" iconCls="icon-search" href="#">查询</a>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<a id="user_edit_btn" href="#">保存</a>
			</td>
		</tr>
	</table>
</form>