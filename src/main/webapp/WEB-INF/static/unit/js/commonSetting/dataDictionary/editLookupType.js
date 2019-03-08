/**
 * 
 */
$(function() {
	// 失去焦点时不远程验证，保存时才远程验证
//	$('#LOOKUP_TYPE_add').focus(function() {
//		// 类型
//		$('#LOOKUP_TYPE_add').validatebox({
//			required : true,
//			validType : [ "length[1,30]", "not_chinese" ]
//		});
//	});

	
	$('#lookup_type_form_edit').form({
		onLoadSuccess : function(data) { // 加载完毕后在格式化表单
			var one_data=data.rows[0];
			// 加载数据
			$('#SOURCE_TYPE_edit').val(one_data.SOURCE_TYPE);
			$('#DESCRIPTION_edit').val(one_data.DESCRIPTION);
		}
		});

	
	// 来源系统类型MES/CRM
	$('#SOURCE_TYPE_edit').validatebox({
		required : false,
		validType : [ "length[1,30]" ]
	});

	// 说明
	$('#DESCRIPTION_edit').validatebox({
		required : false,
		validType : [ "length[1,100]" ]
	});

	// 保存按钮
	$('#lookup_type_edit_btn').linkbutton({});

	// 绑定保存按钮事件
	$('#lookup_type_edit_btn').click(function() {
		save();
	});
	// 加载表单数据
	$('#lookup_type_form_edit').form('load', basePath + 'lookupController/getLookupTypeList?lookupType=' + $('#LOOKUP_TYPE_edit').val()+'&page=1&rows=25');

	// 保存方法
	function save() {
		$('#lookup_type_form_edit').attr('action',
				basePath + 'lookupController/editLookupType');
//		$('#LOOKUP_TYPE_add')
//		.validatebox(
//				{
//					required : true,
//					validType : [
//							"length[1,30]",
//							"not_chinese",
//							"remote[basePath + 'lookupController/lookupTypeUniqueCheck', 'lookupType']" ]
//				});
		// 提交
		$('#lookup_type_form_edit')
				.form(
						'submit',
						{
							onSubmit : function() {// 提交前置事件
								
								var isValid = $(this).form('validate');
								if (isValid) {// 验证通过,弹出遮罩
									$.messager.progress({
										text : '更新中...',
										interval : 200
									});
								}
								return isValid; // return false will stop the
												// form submission
							},
							success : function(data) {
								try {
									// 关闭遮罩
									$.messager.progress('close');

									// 解析数据
									var datas = strToJson(data);

									if (datas.returnCode == '1') {
										$('#lookup_type_window_edit').window('close');
										// $('#lookup_table_search_btn').click();
										$('#lookup_type_table').datagrid(
												'load', {});
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
