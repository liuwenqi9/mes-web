function getAppNode(){
	var url = basePath + 'functionController/getAppNode';
	var success = function(datas) {
		$('#sysFunction_tree').treeview({data : strToJson(datas)});
		// 树节点点击事件，刷新栏目信息和权限列表
		$('#sysFunction_tree').on('nodeSelected',
			function(event,data) {
				// 事件代码...
				$('#functionDets_dl').empty();
				$('#functionDets_dl').append("<dt>栏目名称:</dt> <dd>"+ data.FUNCTION_NAME
					+ "</dd> <dt>栏目代码:</dt> <dd>"+ data.FUNCTION_CODE+ "</dd>");
				$('#functionPurviewTable').bootstrapTable("refresh",{
					url : basePath + 'functionController/getPurviewList?functionCode='+ data.FUNCTION_CODE});
			});
	};
	// 提交
	eGdUtil.requestJsonAjax(url,null,success);
}
var  functionCodeF=false,functionNameF=false;
function addFunctionFormInit(){
	$('#sort_add').val('1');//默认排序1
	$('#functionCode_add').on('keyup blur', function(){
		//过滤中文
		var v1 = $('#functionCode_add').val(); 
		v1=v1.replace(/[^\w\.\/]/ig,'');
		$('#functionCode_add').val(v1); 
			if(v1==''){
				$('#functionCode_add_a').addClass('has-error');
				$('#functionCode_add_span_a').html('必填！');
				$('#functionCode_add_span_a').css('color','red');
				functionCodeF=false;
			}else if(v1!=''){
				$.ajax({
					type : 'POST',
					data : {'functionCode':v1},
					url : basePath +'functionController/functionCodeUniqueCheck',
					timeout:5000,
					success:function(datas){ 
						if(strToJson(datas)){
							$('#functionCode_add_a').removeClass('has-error');
							$('#functionCode_add_span_a').html(''); 
							functionCodeF=true; 
						}else {  
							$('#functionCode_add_a').addClass('has-error');
							$('#functionCode_add_span_a').html('该功能代码已存在！');
							$('#functionCode_add_span_a').css('color','red');
							functionCodeF=false; 
						}
					} 
				});
			}
		});
	$('#functionName_add').on('keyup',
			function(){ var v2 = $(this).val();
				if(v2==''){
					$('#functionName_add_b').addClass('has-error');
					$('#functionName_add_span_b').html('必填！');
					$('#functionName_add_span_b').css('color','red');
					functionNameF=false;
				}else{
					$('#functionName_add_b').removeClass('has-error');
					$('#functionName_add_span_b').html(''); 
					functionNameF=true;
				}
			});
	
}
function adddFunction(){
	var functionCode = $('#functionCode_add').val();
	var functionName = $('#functionName_add').val();
	if(functionCode==''){
		$('#functionCode_add_a').addClass('has-error');
		$('#functionCode_add_span_a').html('必填！');
		$('#functionCode_add_span_a').css('color','red');
		functionCodeF=false;
	}
	if(functionName==''){
		$('#functionName_add_b').addClass('has-error');
		$('#functionName_add_span_b').html('必填！');
		$('#functionName_add_span_b').css('color','red');
		functionNameF=false;
	}
	if(functionCodeF&&functionNameF){return true;}else{return false;}
} 
$(function() { 
	addFunctionFormInit();
	// 初始化TouchSpin
	$("input[name='sort']").TouchSpin({
		min : 1,
		max : 20
	});
	// 初始化栏目树
	$.ajax({
				type : 'POST',
				url : basePath + 'functionController/getAppNode',
				success : function(datas) {
					$('#sysFunction_tree').treeview({
						data : strToJson(datas)
					});
					// 树节点点击事件，刷新栏目信息和权限列表
					$('#sysFunction_tree')
							.on(
									'nodeSelected',
									function(event, data) {
										// 事件代码...
										$('#functionDets_dl').empty();
										$('#functionDets_dl')
												.append(
														"<dt>栏目名称:</dt> <dd>"
																+ data.FUNCTION_NAME
																+ "</dd> <dt>栏目代码:</dt> <dd>"
																+ data.FUNCTION_CODE
																+ "</dd>");
										$('#functionPurviewTable')
												.bootstrapTable(
														"refresh",
														{
															url : basePath
																	+ 'functionController/getPurviewList?functionCode='
																	+ data.FUNCTION_CODE
														});
									});
				}
			})
	// 初始化权限列表
	$('#functionPurviewTable').bootstrapTable();

	// 新增栏目节点
	$('#functionAdd_btn').click(function() {
		var row = $('#sysFunction_tree').treeview('getSelected');
		if (row.length > 0) {
			if (row[0].LEVEL_NO < 3) {
				$('#parentFunctionCode_add').val(row[0].FUNCTION_CODE);
				$('#levelNo_add').val(row[0].LEVEL_NO + 1);
				$('#addFunctionModal').modal({
					show : true
				});
			} else {
				alert("菜单栏目最多只能3级");
			}

		} else {
			$('#levelNo_add').val("1");
			$('#addFunctionModal').modal({
				show : true,
				backdrop : false
			});
		}
	});
	// 修改栏目节点
	$('#functionEdit_btn').click(function() {
		var row = $('#sysFunction_tree').treeview('getSelected');
		if (row.length > 0) {
			$('#functionCode_edit').val(row[0].FUNCTION_CODE);
			$('#functionName_edit').val(row[0].FUNCTION_NAME);
			$('#parentFunctionCode_edit').val(row[0].PARENT_FUNCTION_CODE);
			$('#status_edit').val(row[0].STATUS);
			$('#sort_edit').val(row[0].SORT);
			$('#levelNo_edit').val(row[0].LEVEL_NO);
			$('#requestUrl_edit').val(row[0].REQUEST_URL);
			$('#editFunctionModal').modal({
				show : true,
				backdrop : false
			});
		} else {
			alert("请选择需要修改的栏目");
		}
	});

	// 删除栏目节点
	$('#functionDelete_btn').click(function() {
		var row = $('#sysFunction_tree').treeview('getSelected');
		if (row.length > 0) {
			$('#del_functionCode').val(row[0].FUNCTION_CODE);
			$('#del_functionName').text(row[0].FUNCTION_NAME);
			$('#deleteFunctionModal').modal({
				show : true
			});
		} else {
			alert("请选择需要删除的栏目");
		}
	});

	// 添加权限
	$('#function_purview_add_btn').click(function() {
		var row = $('#sysFunction_tree').treeview('getSelected');
		if (row.length > 0) {
			if (row[0].LEVEL_NO > 2) {
				$('#pur_functionCode_add').val(row[0].FUNCTION_CODE);
				$('#addPurviewModal').modal({
					show : true,
					backdrop : false
				});
			} else {
				alert("权限只能添加在3级菜单上");
			}

		} else {
			alert("请选择需要添加权限的栏目");
		}
	});

	// 修改权限
	$('#function_purview_edit_btn').click(function() {
		var row = $('#functionPurviewTable').bootstrapTable('getSelections');
		if (row.length > 0) {
			$('#pur_functionCode_edit').val(row[0].functionCode);
			$('#purviewCode_edit').val(row[0].purviewCode);
			$('#purviewName_edit').val(row[0].purviewName);
			$('#editPurviewModal').modal({
				show : true,
				backdrop : false
			});
		} else {
			alert("请选择需要修改的权限");
		}
	});

	// 删除权限
	$('#function_purview_delete_btn').click(function() {
		var row = $('#functionPurviewTable').bootstrapTable('getSelections');
		if (row.length > 0) {
			$('#del_purviewCode').val(row[0].purviewCode);
			$('#del_purviewName').text(row[0].purviewName);

			$('#deletePurviewModal').modal({
				show : true,
				backdrop : false
			});
		} else {
			alert("请选择需要删除的权限");
		}
	});

	// 新增权限
	$('#addPurview_save_btn').click(
		function() {
			var params= $('#addPurviewForm').serializeObject();
			var url = basePath + 'functionController/purviewAdd';
			var success = function() {
				var row = $('#sysFunction_tree').treeview('getSelected');
				$('#addPurviewModal').modal('hide');
				$(':input','#addPurviewForm').not(':button, :submit, :reset, :hidden').val('')
					.removeAttr('checked').removeAttr('selected');
				$('#functionPurviewTable').bootstrapTable("refresh",
					{
						url : basePath
								+ 'functionController/getPurviewList?functionCode='+ row[0].FUNCTION_CODE
					});
			}
			// 提交
			eGdUtil.requestJsonAjax(url,params,success);
		});

	// 修改权限
	$('#editPurview_save_btn').click(
		function() {
			var params= $('#editPurviewForm').serializeObject();
			var url = basePath + 'functionController/purviewEdit';
			var success = function() {
				var row = $('#sysFunction_tree').treeview('getSelected');
				$('#editPurviewModal').modal('hide');
				$(':input','#editPurviewForm').not(':button, :submit, :reset, :hidden').val('')
					.removeAttr('checked').removeAttr('selected');
				$('#functionPurviewTable').bootstrapTable("refresh",
					{
						url : basePath+ 'functionController/getPurviewList?functionCode=' + row[0].FUNCTION_CODE
					});
			}
			// 提交
			eGdUtil.requestJsonAjax(url,params,success);
		});
	// 删除权限
	$('#delPurview_btn').click(
		function() {
			var row = $('#sysFunction_tree').treeview('getSelected');
			var url = basePath + 'functionController/purviewDelete?functionCode=' + row[0].FUNCTION_CODE
				+ '&purviewCode=' + $('#del_purviewCode').val();
			var success = function(data) {
				$('#deletePurviewModal').modal('hide');
				$('#functionPurviewTable').bootstrapTable("refresh",
					{
						url : basePath
								+ 'functionController/getPurviewList?functionCode='
								+ row[0].FUNCTION_CODE
					});
			}
			// 提交
			eGdUtil.requestJsonAjax(url,null,success);
		});

	// 新增栏目
	$('#addFunction_save_btn').click( 
		function() { 
			if(adddFunction()){
				var params= $('#addFunctionForm').serializeObject();
				var url = basePath + 'functionController/addFunction';
				var success = function() {
					$('#addFunctionModal').modal('hide');
					$(':input','#addFunctionForm').not(':button, :submit, :reset, :hidden')
							.val('').removeAttr('checked').removeAttr('selected');
					getAppNode();
				};
				// 提交
				eGdUtil.requestJsonAjax(url,params,success);
			}
			
		});
	// 修改栏目
	$('#editFunction_save_btn').click(
		function() {
			var params= $('#editFunctionForm').serializeObject();
			var url = basePath + 'functionController/editFunction';
			var success = function() {
				$('#editFunctionModal').modal('hide');
				$(':input','#editFunctionForm').not(':button, :submit, :reset, :hidden')
						.val('').removeAttr('checked').removeAttr('selected');
				getAppNode();
			}
			// 提交
			eGdUtil.requestJsonAjax(url,params,success);
		});

	// 删除栏目
	$('#delFunction_btn').click(
		function() {
			var row = $('#sysFunction_tree')
					.treeview('getSelected');

			var url = basePath + 'functionController/deleteFunction?functionCode=' + row[0].FUNCTION_CODE;
			var success = function(data) {
				$('#deleteFunctionModal').modal('hide');
				getAppNode();
			}
			// 提交
			eGdUtil.requestJsonAjax(url,null,success);
		});
});
