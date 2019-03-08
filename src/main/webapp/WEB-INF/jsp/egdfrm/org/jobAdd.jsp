<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/js/org/jobAdd.js"></script>
<form id="job_form_add" name="job_form_add" method="post">
	<table>
		<tr>
			<td>岗位代码:</td>
			<td>
				<input id="jobCode_add" name="jobCode" style="width: 150px;" />
			</td>
			<td>岗位名称:</td>
			<td>
				<input id="jobName_add" name="jobName" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td>所属组织:</td>
			<td colspan="3">
				<input id="job_rogCode_add" name="orgCode" value="${orgCode}" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<a id="job_save_add_btn" href="#">保存</a>
			</td>
		</tr>
	</table>
</form>