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

	// 来源系统类型MES/CRM
	$('#SOURCE_TYPE_add').validatebox({
		required : false,
		validType : [ "length[1,30]" ]
	});

	// 说明
	$('#DESCRIPTION_add').validatebox({
		required : false,
		validType : [ "length[1,100]" ]
	});

	// 保存按钮
	$('#lookup_type_add_btn').linkbutton({});

	// 绑定保存按钮事件
	$('#lookup_type_add_btn').click(function() {
		save();
	});

	// 保存方法
	function save() {
		$('#lookup_type_form_add').attr('action',
				basePath + 'lookupController/addLookup');
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
		$('#lookup_type_form_add')
				.form(
						'submit',
						{
							onSubmit : function() {// 提交前置事件
								
								var isValid = $(this).form('validate');
								if (isValid) {// 验证通过,弹出遮罩
									$.messager.progress({
										text : '保存中...',
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
										$('#lookup_type_window_add').window('close');
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
