<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/unit/js/capacityPacking/capacityPackingedit.js"></script>
<form id="capacityPacking_form_edit" name="capacityPacking_form_edit" method="post">
	<table>
		<tr>
			<td>&nbsp;物料编码u:</td>
			<td>
				<input id="materialCode_edit" name="materialCode"  style="width: 150px;" />
			</td>
			<td>&nbsp;产品类型:</td>
			<td>
				<input id="packingType_edit" name="packingType"  readonly style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td>物料描述:</td>
			<td colspan="3">
				<input id="materialDes_edit" name="materialDes"  readonly style="width: 390px;" />
			</td> 
		</tr>
		<tr>
			<td>小包装容量:</td>
			<td>
				<input id="capacityMin_edit" name="sQuantity"  style="width: 150px;" />
			</td>
			<td>大包装容量:</td>
			<td>
				<input id="capacityMax_edit" name="bQuantity"  style="width: 150px;" />
			</td>
		</tr>
		 <input id="organization_id_edit" name="organizationId"  type="hidden"  />
		 <input id="inventory_item_id_edit" name="inventoryItemId"    type="hidden"/>
		 <input id="HEADER_ID_edit" name="headerId"  type="hidden"/>
		<tr>
			<td colspan="4" align="center">
				<a id="capacityPacking_edit_btn" href="#">保存</a>
			</td>
		</tr>
	</table>
	
	<table id="table_bb3_edit"></table>
</form>