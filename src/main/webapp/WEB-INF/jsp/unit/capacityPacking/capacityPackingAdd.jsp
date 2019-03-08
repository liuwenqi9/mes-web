<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/unit/js/capacityPacking/capacityPackingAdd.js"></script>
<form id="capacityPacking_form_add" name="capacityPacking_form_add" method="post">
	<table>
		<tr>
			<td>&nbsp;物料编码:</td>
			<td>
				<input id="materialCode_add" name="materialCode" style="width: 150px;" />
			</td>
			<td>&nbsp;产品类型:</td>
			<td>
				<input id="packingType_add" name="packingType" readonly style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td>物料描述:</td>
			<td colspan="3">
				<input id="materialDes_add" name="materialDes" readonly style="width: 390px;" />
			</td> 
		</tr>
		<tr>
			<td>小包装容量:</td>
			<td>
				<input id="capacityMin_add" name="sQuantity" style="width: 150px;" />
			</td>
			<td>大包装容量:</td>
			<td>
				<input id="capacityMax_add" name="bQuantity" style="width: 150px;" />
			</td>
		</tr>
		 <input id="organization_id_add" name="organizationId" type="hidden"  />
		 <input id="inventory_item_id_add" name="inventoryItemId"  type="hidden"/>
		<tr>
			<td colspan="4" align="center">
				<a id="capacityPacking_add_btn" href="#">保存</a>
			</td>
		</tr>
	</table>
	
	<table id="table_bb3"></table>
</form>