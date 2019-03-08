/**
 * 
 */
$(function() {   
	// 用户名
	/*$('#loginName_add').validatebox({
		required : true,
		validType : [ "length[1,100]", "not_chinese", "remote[basePath + 'userController/loginNameUniqueCheck', 'loginName']" ]
	});*/
	//构建编辑数据
	var s=HEADER_ID_EDIT.split('--');
	$('#HEADER_ID_edit').val(s[0]);
	$('#materialCode_edit').val(s[1]);
	$('#materialDes_edit').val(s[2]);
	$('#packingType_edit').val(s[3]);
	$('#capacityMin_edit').val(s[4]);
	$('#capacityMax_edit').val(s[5]);
	$('#organization_id_edit').val(s[6]);
	$('#inventory_item_id_edit').val(s[7]);
	
	
	// 物料编码
	$('#materialCode_edit').validatebox({
		required : true,
		validType : ["length[1,30]" ]
	});
	// 小包装箱
	$('#capacityMin_edit').validatebox({
		required : true,
		validType : [ "length[1,5]" ]
	});
	
	//弹出选择框
	$('#materialCode_edit').click(function(){ 
			$('#table_bb3_edit').window({
				title : '请选择物料编码',
				collapsible : false,
				minimizable : false,
				maximizable : false,
				resizable : false,
				modal : true,
				width : 500,
				height : 200,
				href : basePath + 'packagecontentController/toSelectEdit' //跳转去选择页面
			}); 
		
	});
	
     
	// 保存按钮
	$('#capacityPacking_edit_btn').linkbutton({});

	// 绑定保存按钮事件
	$('#capacityPacking_edit_btn').click(function() {
		save();
	});

	// 保存方法
	function save() {
		$('#capacityPacking_form_edit').attr('action', basePath + 'packagecontentController/editCapacityPacking');
		// 提交
		$('#capacityPacking_form_edit').form('submit', {
			onSubmit : function() {// 提交前置事件
				var isValid = $(this).form('validate');
				if (isValid) {// 验证通过,弹出遮罩
					$.messager.progress({
						text : '保存中...',
						interval : 200
					});
				}
				return isValid; // return false will stop the form submission
			},
			success : function(data) {
				try {
					// 关闭遮罩
					$.messager.progress('close'); 
					// 解析数据
					var datas = strToJson(data); 
					if (datas.returnCode == '1') {
						$('#capacityPacking_window_edit').window('close');
						$('#capacityPacking_table_search_btn').click();
						showInfoMsg(datas.result);
					} else {
						showErrorMsg(datas.result);
					}
				} catch (e) {
					showErrorWindow(data);
				}
			}
		});
	}
});
