function getCapacityPackings(load, params) {
	var getUsers_url = basePath
			+ 'packagecontentController/getCapacityPackings';
	if (eGdUtil.isEmpty(load)) {
		//初始化
		var getUsers_suc = function(datas) {
			$('#capacityPackingTable').bootstrapTable({
				data : strToJson(datas)
			});
		};
	} else {
		getUsers_suc = function(datass) {
			$('#capacityPackingTable').bootstrapTable('load', {
				data : strToJson(datass)
			});
		};
	}
	if (eGdUtil.isEmpty(params)) {
		eGdUtil.requestJsonAjax(getUsers_url, null, getUsers_suc);
	} else {
		eGdUtil.requestJsonAjax(getUsers_url, params, getUsers_suc);
	}

}
//加载物料选择列表
function getCapacityPackingselects(load, params) {
	var getUsers_url = basePath
			+ 'packagecontentController/getCapacityPackingselects';
	if (eGdUtil.isEmpty(load)) {
		//初始化
		var getUsers_suc = function(datas) {
			$('#addselectTable').bootstrapTable({
				data : strToJson(datas)
			});
		};
	} else {
		getUsers_suc = function(datass) {
			$('#addselectTable').bootstrapTable('load', {
				data : strToJson(datass)
			});
		};
	}
	if (eGdUtil.isEmpty(params)) {
		eGdUtil.requestJsonAjax(getUsers_url, null, getUsers_suc);
	} else {
		eGdUtil.requestJsonAjax(getUsers_url, params, getUsers_suc);
	}

}
//加载物料选择列表edit
function getCapacityPackingselectsedit(load, params) {
	var getUsers_url = basePath
			+ 'packagecontentController/getCapacityPackingselects';
	if (eGdUtil.isEmpty(load)) {
		//初始化
		var getUsers_suc = function(datas) {
			$('#editselectTable').bootstrapTable({
				data : strToJson(datas)
			});
		};
	} else {
		getUsers_suc = function(datass) {
			$('#editselectTable').bootstrapTable('load', {
				data : strToJson(datass)
			});
		};
	}
	if (eGdUtil.isEmpty(params)) {
		eGdUtil.requestJsonAjax(getUsers_url, null, getUsers_suc);
	} else {
		eGdUtil.requestJsonAjax(getUsers_url, params, getUsers_suc);
	}

}
$(function() {
	getCapacityPackings();

	$('#capacityPacking_search_btn').on('click', function() {
		var params = {
			DESCRIPTION : $('#DESCRIPTION_search').val()
		};
		getCapacityPackings(null, params);
	});
	//包装箱新增保存按钮事件
	$('#add_capacityPacking_save_btn').click(function() {
		var val = $('#SEGMENT1_add').val();
		if (val != '') {
			var url = basePath + 'packagecontentController/addCapacityPacking';
			var params = $('#addCapacityPackingForm').serializeObject();
			var success = function(datass) {
				$('#addCapacityPackingModal').modal('hide');
				$(':input', '#addCapacityPackingForm').not(':button, :submit, :reset, :hidden')
				.val('').removeAttr('checked').removeAttr('selected');
				getCapacityPackings('load');
			};
			eGdUtil.requestJsonAjax(url, params, success);
		} else {
			alert('请选择物料拜编码！');
		}
	});

	//edit_save_btn
	$('#edit_capacityPacking_save_btn').click(
			function() {
				//var params = $('#editCapacityPackingForm').serializeObject();
				var params = {  sQuantity:$('#sQuantity_edit').val(),
								bQuantity:$('#bQuantity_edit').val(),
								organizationId:$('#organization_id_edit').val(),
								inventoryItemId:$('#inventory_item_id_edit').val(),
								headerId:$('#HEADER_ID_edit').val()};
				var url = basePath + 'packagecontentController/editCapacityPacking';
				var success = function(datass) {
					$('#editCapacityPackingModal').modal('hide'); 
					getCapacityPackings('load');
				};
				eGdUtil.requestJsonAjax(url, params, success);
			});
	//删除确认
	$('#del_CapacityPacking_btn')
			.click(
					function() {
						var url = basePath
								+ 'packagecontentController/deleteCapacityPacking?headerId='
								+ $('#del_HEADER_ID').val();
						var success = function(data) {
							$('#deleteUserModal').modal('hide');
							getCapacityPackings('load');
						};
						eGdUtil.requestJsonAjax(url, null, success);
					});

	$('#capacityPacking_add_btn').click(function() {
		$('#addCapacityPackingModal').modal({
			show : true,
			backdrop : false
		});

	});
	//绑定选择物料事件（选择物料按钮）addCapacityPackingSelectModal
	$('#search_btn_2').on('click', function() {
		getCapacityPackingselects();
		$('#addCapacityPackingSelectModal').modal({
			show : true,
			backdrop : false
		});
	});
	// 选择物料界面键盘事件
	$('#segment1_addSelect').keydown(function(e) {
		if (e.keyCode == 13) {
			var params = {
				segment1 : $('#segment1_addSelect').val()
			};
			getCapacityPackingselects('load', params);
		}
	});
	//dbclick选中
	$('#addselectTable').on('click', function() {
		var row = $('#addselectTable').bootstrapTable('getSelections');
		if (row.length > 0) {
			//初始化add表单数据
			$('#SEGMENT1_add').val(row[0].SEGMENT1);
			$('#DESCRIPTION_add').val(row[0].DESCRIPTION);
			$('#PROD_TYPE_add').val(row[0].PROD_TYPE);
			$('#organization_id_add').val(row[0].ORGANIZATION_ID);
			$('#inventory_item_id_add').val(row[0].INVENTORY_ITEM_ID);
			$('#addCapacityPackingSelectModal').modal('hide');
			$('#addselectTable').bootstrapTable('destroy');
		}

	});
	//验证包装箱
	$('#sQuantity_add').keyup(function() {
		num(this);
	});
	$('#bQuantity_add').keyup(function() {
		num(this);
	});
	//验证包装箱
	$('#sQuantity_edit').keyup(function() {
		num(this);
	});
	$('#bQuantity_edit').keyup(function() {
		num(this);
	});

	//edit 
	$('#search_btn_edit').click(function() {
		getCapacityPackingselectsedit();
		$('#editCapacityPackingSelectModal').modal({
			show : true,
			backdrop : false
		});
	});
	// 选择物料界面键盘事件 edit
	$('#segment1_editSelect').keydown(function(e) {
		if (e.keyCode == 13) {
			var params = {
				segment1 : $('#segment1_editSelect').val()
			};
			getCapacityPackingselectsedit('load', params);
		}
	});
	
	// 物料界面键盘事件 
	$('#segment1_search').keydown(function(e) {
		if (e.keyCode == 13) {
			var params = {
				segment1 : $('#segment1_search').val()
			};
			getCapacityPackings('load', params);
		}
	});
	// edit
	$('#editselectTable').on('click', function() {
		var row = $('#editselectTable').bootstrapTable('getSelections');
		if (row.length > 0) {
			// 初始化add表单数据
			$('#SEGMENT1_edit').val(row[0].SEGMENT1);
			$('#DESCRIPTION_edit').val(row[0].DESCRIPTION);
			$('#PROD_TYPE_edit').val(row[0].PROD_TYPE);
			//$('#HEADER_ID_edit').val(row[0].HEADER_ID);
			$('#organization_id_edit').val(row[0].ORGANIZATION_ID);
			$('#inventory_item_id_edit').val(row[0].INVENTORY_ITEM_ID);
			$('#editCapacityPackingSelectModal').modal('hide');
			$('#editselectTable').bootstrapTable('destroy');
		}

	});

});
// 数字验证
function num(e) {
	var v1 = $(e).val();
	v1 = v1.replace(/\D/g, '');
	$(e).val(v1);
}

function editCapacityPacking() {
	var row = $('#capacityPackingTable').bootstrapTable('getSelections');
	if (row.length > 0) { //初始化编辑页面
		$('#SEGMENT1_edit').val(row[0].SEGMENT1);
		$('#DESCRIPTION_edit').val(row[0].DESCRIPTION);
		$('#PROD_TYPE_edit').val(row[0].PROD_TYPE);
		$('#organization_id_edit').val(row[0].ORGANIZATION_ID);
		$('#inventory_item_id_edit').val(row[0].INVENTORY_ITEM_ID);
		$('#sQuantity_edit').val(row[0].S_QUANTITY);
		$('#bQuantity_edit').val(row[0].B_QUANTITY);
		$('#HEADER_ID_edit').val(row[0].HEADER_ID);
		$('#editCapacityPackingModal').modal({
			show : true,
			backdrop : false
		});
	} else {
		alert("请选择需要修改的数据");
	}

}
function deleteCapacityPacking() {
	var row = $('#capacityPackingTable').bootstrapTable('getSelections');
	if (row.length > 0) {//HEADER_ID,
		$('#del_sen').text(row[0].SEGMENT1);
		$('#del_HEADER_ID').val(row[0].HEADER_ID);
		$('#deleteUserModal').modal({
			show : true
		});
	} else {
		alert("请选择需要删除的数据");
	} 
}

//capacityPacking_search_btn
function searchCapacityPacking(){  
var params = { segment1 : $('#segment1_search').val() };
getCapacityPackings('load', params);  
}
