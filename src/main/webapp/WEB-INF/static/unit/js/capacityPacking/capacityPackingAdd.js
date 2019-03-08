/**
 * 
 */
$(function() {
	$.extend($.fn.validatebox.defaults.rules, {  
		checkNum: { 
		validator: function(value, param) { 
		return /^([0-9]+)$/.test(value); 
		}, 
		message: '请输入整数'
		}, 
		checkFloat: { 
		validator: function(value, param) { 
		return /^[+|-]?([0-9]+\.[0-9]+)|[0-9]+$/.test(value); 
		}, 
		message: '请输入合法数字'
		} 
		});
	 
	  

	// 用户名
	/*$('#loginName_add').validatebox({
		required : true,
		validType : [ "length[1,100]", "not_chinese", "remote[basePath + 'userController/loginNameUniqueCheck', 'loginName']" ]
	});*/

	// 物料编码
	$('#materialCode_add').validatebox({
		required : true,
		validType : ["checkNum['#materialCode_add']","length[1,30]" ]
	});
	// 小包装箱
	$('#capacityMin_add').validatebox({
		required : true,
		validType : [ "length[1,5]" ]
	});
	
	//弹出选择框
	$('#materialCode_add').click(function(){ 
			$('#table_bb3').window({
				title : '请选择物料编码1',
				collapsible : false,
				minimizable : false,
				maximizable : false,
				resizable : false,
				modal : true,
				width : 500,
				height : 200,
				href : basePath + 'packagecontentController/toSelect' //跳转去选择页面
			}); 
		
	});
	
     
	// 保存按钮
	$('#capacityPacking_add_btn').linkbutton({});

	// 绑定保存按钮事件
	$('#capacityPacking_add_btn').click(function() {
		save();
	});

	// 保存方法
	function save() {
		$('#capacityPacking_form_add').attr('action', basePath + 'packagecontentController/addCapacityPacking');
		// 提交
		$('#capacityPacking_form_add').form('submit', {
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
						$('#capacityPacking_window_add').window('close');
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
