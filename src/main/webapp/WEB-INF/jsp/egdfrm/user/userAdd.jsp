<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/js/user/userAdd.js"></script>
<form id="user_form_add" name="user_form_add" method="post">
	<table>
		<tr>
			<td>登录名称:</td>
			<td>
				<input id="loginName_add" name="loginName" style="width: 150px;" />
			</td>
			<td>用户名称:</td>
			<td>
				<input id="userName_add" name="userName" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td>用户座机:</td>
			<td>
				<input id="userPhone_add" name="userPhone" style="width: 150px;" />
			</td>
			<td>用户手机:</td>
			<td>
				<input id="userMPhome_add" name="userMPhome" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td>电子邮件:</td>
			<td>
				<input id="userEmail_add" name="userEmail" style="width: 150px;" />
			</td>
			<td>用户状态:</td>
			<td>
				<input id="userStatus_add" name="userStatus" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td>绑定供应商:</td>
			<td colspan="3">
				<input id="supplierId_add" name="supplierId" type="hidden"/>
				<input id="supplierName_add" name="supplierName" style="width: 150px;" />
				<a id="supplier_add_search_btn" class="easyui-linkbutton" iconCls="icon-search" href="#">查询</a>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<a id="user_add_btn" href="#">保存</a>
			</td>
		</tr>
	</table>
</form>