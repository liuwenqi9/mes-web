var loadTableCount = 1;
// 加载数据字典类型
function getLookupTypeList(load) {
	var url = basePath + 'lookupController/getLookupTypeList?rows=9999&page=1';
	var success;
	if (eGdUtil.isEmpty(load)) {
		// 初始化
		success = function(datas) {
			$('#lookupTypeTable').bootstrapTable({
				data : strToJson(datas.rows)
			});
			$('#lookupTypeTable').on(
					'click-row.bs.table',
					function(e, row, $element) {
						// 清空原来的列表//有bug
//						$('#lookupTable').bootstrapTable({
//							data : strToJson('[]')
//						});
						// 小心被勾选的行跟被点击的行不是同一行
						// var row2 =
						// $('#lookupTypeTable').bootstrapTable('getSelections');
						// if (row.length > 0) {

						var url2 = basePath
								+ 'lookupController/getLookupList?lookupType='
								+ row.LOOKUP_TYPE;
						// var url2 = basePath +
						// 'lookupController/getLookupList?lookupType=' +
						// 'PACKING_TYPE';
						// TODO 只在第一次执行时有效果，后面左侧面板再点击别的行，右侧列表中数据没发生改变
						var success2;
						if (loadTableCount == 1) {
							success2 = function(datass) {
								$('#lookupTable').bootstrapTable({
									data : strToJson(datass)
								});
							};
						} else {
							success2 = function(datass) {
								$('#lookupTable').bootstrapTable('load', {
									data : strToJson(datass)
								});
							};
						}
						loadTableCount++;
						eGdUtil.requestJsonAjax(url2, null, success2);
						// }
					});
		}
	} else {
		success = function(datass) {
			$('#addLookupTypeModal').modal('hide');
			$('#lookupTypeTable').bootstrapTable('load', {
				data : strToJson(datass.rows)
			});
		};
	}
	eGdUtil.requestJsonAjax(url, null, success);
}
// 加载当前选中类型的数据字典列表
function getLookupList() {

	var row2 = $('#lookupTypeTable').bootstrapTable('getSelections');
	if (row2.length > 0) {

		var url2 = basePath + 'lookupController/getLookupList?lookupType='
				+ row2[0].LOOKUP_TYPE;
		var success2 = function(datass) {
			$('#lookupTable').bootstrapTable('load',{
				data : strToJson(datass)
			});
		};
		eGdUtil.requestJsonAjax(url2, null, success2);
	}

}

$(function() {
	// 初始化数据字典类型列表
	getLookupTypeList();
	
	// 新增数据字典类型——保存
	$('#addLookupType_save_btn').click(
			function() {
				if(!check_input_look_type()){
					return;
				}
				var params = $('#addLookupTypeForm').serializeObject();
				var url = basePath + 'lookupController/addLookupType';
				var success = function(datass) {
					$('#addLookupTypeModal').modal('hide');
					$(':input', '#addLookupTypeForm').not(
							':button, :submit, :reset, :hidden').val('')
							.removeAttr('checked').removeAttr('selected');

					getLookupTypeList('load');
				}
				// 提交
				eGdUtil.requestJsonAjax(url, params, success);
			});
	// 编辑数据字典类型——保存
	$('#editLookupType_save_btn').click(
			function() {
				var params = $('#editLookupTypeForm').serializeObject();
				var url = basePath + 'lookupController/editLookupType';
				var success = function(datass) {
					$('#editLookupTypeModal').modal('hide');
					$(':input', '#addLookupTypeForm').not(
							':button, :submit, :reset, :hidden').val('')
							.removeAttr('checked').removeAttr('selected');

					getLookupTypeList('load');
				}
				// 提交
				eGdUtil.requestJsonAjax(url, params, success);
			});
	// 新增数据字典按钮监听
	$('#addLookup_btn').click(function() {
		var row = $('#lookupTypeTable').bootstrapTable('getSelections');
		if (row.length > 0) {
			$('#addLookupModal').modal('show');
			$('#lookup_lookupType_add').val(row[0].LOOKUP_TYPE);
			$('#lookup_lookupType_add').attr("readonly", "readonly");
			$('#startDateActive_add').datetimepicker({
		        language:  'zh-CN',
		        weekStart: 1,
		        todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				minView: 2,
				forceParse: 0
		    });
			$('#endDateActive_add').datetimepicker({
				language:  'zh-CN',
				weekStart: 1,
				todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				minView: 2,
				forceParse: 0
			});
		} else {
			alert("请选择数据字典类型");
		}
	});
	// 新增数据字典模太框保存按钮监听
	$('#addLookup_save_btn').click(
			function() {
				if(!check_input_lookup_code()){
					return;
				}
				var params = $('#addLookupForm').serializeObject();
				var url = basePath + 'lookupController/addLookup';
				var success = function(datass) {
					$('#addLookupModal').modal('hide');
					$(':input', '#addLookupForm').not(
							':button, :submit, :reset, :hidden').val('')
							.removeAttr('checked').removeAttr('selected');

					getLookupList();
				}
				// 提交
				eGdUtil.requestJsonAjax(url, params, success);
			});
	// 编辑数据字典按钮监听
	$('#editLookup_btn').click(
			function() {
				
				var row = $('#lookupTable').bootstrapTable('getSelections');
				if (row.length > 0) {
					// $('#editLookupModal').modal('show');
					
					$.ajax({
						type : 'POST',
						url : basePath
								+ 'lookupController/getLookup?lookupCode='
								+ row[0].LOOKUP_CODE + '&page=1&rows=25',
						success : function(datas) {
							// 此处需要手动解析json
							var one_data = strToJson(datas)[0];
							$('#editLookupModal').modal({
								show : true,
								backdrop : false
							});
							//加时间选择器
							$('#startDateActive_edit').datetimepicker({
						        language:  'zh-CN',
						        weekStart: 1,
						        todayBtn:  1,
								autoclose: 1,
								todayHighlight: 1,
								startView: 2,
								minView: 2,
								forceParse: 0
						    });
							$('#endDateActive_edit').datetimepicker({
								language:  'zh-CN',
								weekStart: 1,
								todayBtn:  1,
								autoclose: 1,
								todayHighlight: 1,
								startView: 2,
								minView: 2,
								forceParse: 0
							});
							// 加载数据
							$('#lookup_lookupType_edit').val(one_data.LOOKUP_TYPE);
							$('#lookup_lookupType_edit').attr("readonly", "readonly");
							$('#lookupCode_edit').val(one_data.LOOKUP_CODE);
							// 禁止修改类型名
							$('#lookupCode_edit').attr("readonly", "readonly");
							// 是否有效
							$(
									"#enabledFlag_edit option[value='"
											+ one_data.ENABLED_FLAG + "']")
									.attr("selected", true);
//							document.getElementById("enabledFlag_edit")[1].selected = true;
//							$('#enabledFlag_edit').val(data);
							
							
//							var enabled_flag=one_data.ENABLED_FLAG;
//							if(enabled_flag=='Y'){
//								
//							}else{
//								
//							}
							
							
						
							
							
							$('#lookup_description_edit').val(one_data.DESCRIPTION);
							$('#startDateActive_edit').val(
									one_data.START_DATE_ACTIVE);
							$('#endDateActive_edit').val(
									one_data.END_DATE_ACTIVE);
							
							
							
						}
					});

				} else {
					alert("请选择数据字典");
				}
			});
	$('#editLookupModal').on('hide.bs.modal', function () {
//		$(':input', '#editLookupForm').not(
//		':button, :submit, :reset, :hidden').val('')
//		.removeAttr('checked').removeAttr('selected');
		});
	// 编辑数据字典模太框保存按钮监听
	$('#editLookup_save_btn').click(
			function() {
				var params = $('#editLookupForm').serializeObject();
				var url = basePath + 'lookupController/editLookup';
				var success = function(datass) {
					$('#editLookupModal').modal('hide');
					$(':input', '#editLookupForm').not(
							':button, :submit, :reset, :hidden').val('')
							.removeAttr('checked').removeAttr('selected');

					getLookupList();
				}
				// 提交
				eGdUtil.requestJsonAjax(url, params, success);
			});
	add_check_input();

});
// 修改数据字典类型
function editLookupType() {
	var row = $('#lookupTypeTable').bootstrapTable('getSelections');
	if (row.length > 0) {

		$.ajax({
			type : 'POST',
			url : basePath + 'lookupController/getLookupTypeList?lookupType='
					+ row[0].LOOKUP_TYPE + '&page=1&rows=25',
			success : function(datas) {
				// 此处需要手动解析json
				// var one_data=strToJson(datas.rows)[0];
				// var one_data=datas.parseJSON().rows[0];
				var one_data = strToJson(datas).rows[0];
				$('#editLookupTypeModal').modal({
					show : true,
					backdrop : false
				});
				// 加载数据
				$('#lookupType_edit').val(one_data.LOOKUP_TYPE);
				// 禁止修改类型名
				$('#lookupType_edit').attr("readonly", "readonly");
				$('#sourceType_edit').val(one_data.SOURCE_TYPE);
				$('#description_edit').val(one_data.DESCRIPTION);
			}
		});

	} else {
		alert("请选择需要修改的数据字典类型");
	}
}
//添加检测类型名是否为中文
function add_check_input() {
	$("#lookupType_add").blur(function(){
		check_input_look_type();
	});
	$("#lookupCode_add").blur(function(){
		check_input_lookup_code();
	});
	
}
//检测类型名是否为中文
function  check_input_look_type() {
	if(!validatebox.not_chinese.validator($("#lookupType_add").val())){
		$("#lookupType_add_span_b").html(validatebox.not_chinese.message);
		$('#lookupType_add_span_b').css("color","red");
		return false;
	}else{
		$("#lookupType_add_span_b").html("");
		$('#lookupType_add_span_b').css("color","red");
		return true;
	}
	
}
//检测数据字典码是否为中文
function  check_input_lookup_code() {
	if(!validatebox.not_chinese.validator($("#lookupCode_add").val())){
		$("#lookupCode_add_span_b").html(validatebox.not_chinese.message);
		$('#lookupCode_add_span_b').css("color","red");
		return false;
	}else{
		$("#lookupCode_add_span_b").html("");
		$('#lookupCode_add_span_b').css("color","red");
		return true;
	}
	
}