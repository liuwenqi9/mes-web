function getRoleList(load){
	var url=basePath + 'roleController/getRoleList';
	var success;
	if(eGdUtil.isEmpty(load)){
		//初始化
		success = function(datas) {
			$('#roleTable').bootstrapTable({
				data : strToJson(datas)
			});
			$('#roleTable').on('click-row.bs.table', function (e, row, $element) {
				var url2 = basePath + 'roleController/loadRoleFunctionList?roleCode=' + row.ROLE_CODE;
				var success2 = function(datass) {
					$('#roleFunction_tree').treeview({
		                data: strToJson(datass)
		            });
				};
				eGdUtil.requestJsonAjax(url2,null,success2);
				
		    })
		}
	}else{
		success = function(datass) {
			$('#addRoleModal').modal('hide');
			$('#roleTable').bootstrapTable('load', {
				data : strToJson(datass)
			});
			var url2 = basePath + 'roleController/getRoleList';
			var success2 = function(datass) {
				$('#roleTable').bootstrapTable('load', {
					data : strToJson(datass)
				});
			};
			eGdUtil.requestJsonAjax(url2,null,success2);
		};
	}
	eGdUtil.requestJsonAjax(url,null,success);
}
var roleNameF=false,roleCodeF=false;
function addRoleV(){
	var roleName = $('#roleName_add').val();
	var roleCode = $('#roleCode_add').val();
	if(roleName==''){
		$('#roleName_add_span_a').html('必填！');
		$('#roleName_add_span_a').css('color','red');
		$('#roleName_add_a').addClass('has-error');roleNameF=false;
		}
	if(roleCode==''){
		$('#roleCode_add_span_b').html('必填！');
		$('#roleCode_add_span_b').css('color','red');
		$('#roleCode_add_b').addClass('has-error');roleCodeF=false;
		}
	if(roleNameF&&roleCodeF){return true;}else{return false;} 
}
function roleInit(){//removeClass('has-error');
	$('#roleName_add').keyup(function(){
		var v1= $(this).val();
		if(v1==''){
			$('#roleName_add_span_a').html('必填！');
			$('#roleName_add_span_a').css('color','red');
			$('#roleName_add_a').addClass('has-error');
			roleNameF=false;
			} else{ 
			$('#roleName_add_span_a').html(''); 
			$('#roleName_add_a').removeClass('has-error');
			roleNameF=true;}
	});
	$('#roleCode_add').keyup(function(){
		//添加角色代码只能英文
		var v2 = $(this).val(); 
		v2=v2.replace(/[^\w\.\/]/ig,'');
		$(this).val(v2);
		if(v2==''){
			$('#roleCode_add_span_b').html('必填！');
			$('#roleCode_add_span_b').css('color','red');
			$('#roleCode_add_b').addClass('has-error');
			roleCodeF=false;
		}else if(v2!=''){
			$.ajax({
				type : 'POST',
				data : {'roleCode':v2},
				url : basePath +'roleController/roleCodeUniqueCheck',
				timeout:5000,
				success:function(datas){ 
					if(strToJson(datas)){
						$('#roleCode_add_span_b').html('');
						$('#roleCode_add_b').removeClass('has-error');
						roleCodeF=true;
					}else {
						$('#roleCode_add_span_b').html('该角色代码已存在！');
						$('#roleCode_add_span_b').css("color","red");
						$('#roleCode_add_b').addClass('has-error');
						roleCodeF=false;
					}
				} 
			});
		}
	});
}
$(function() {
//初始化角色列表
	getRoleList();
	roleInit();
	 
	$('#addRole_save_btn').click(
			function() {
				if(addRoleV()){
					var params= $('#addRoleForm').serializeObject();
					var url = basePath + 'roleController/addRole';
					var success = function(datass) {
						$('#addRoleModal').modal('hide');
						$('#roleTable').bootstrapTable('load', {
							data : strToJson(datass)
						});

						getRoleList('load');
					};
					// 提交
					eGdUtil.requestJsonAjax(url,params,success);
				}
			});

	$('#editRole_save_btn').click(
		function() {
			var params= $('#editRoleForm').serializeObject();
			var url = basePath + 'roleController/editRoleNew';
			var success = function(datass) {
				$('#editRoleModal').modal('hide');
				$(':input', '#editRoleForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
	
				getRoleList('load');
			}
			// 提交
			eGdUtil.requestJsonAjax(url,params,success);
		});
	$('#delRole_btn').click(
			function() {
				var url = basePath + 'roleController/deleteRole?roleCode='+$('#del_roleCode').val();
				var success = function(datass) {
					$('#deleteRoleModal').modal('hide');
					getRoleList('load');
				}
				// 提交
				eGdUtil.requestJsonAjax(url,null,success);
			});
	

		// alert add purview window
	$('#addPurview_btn').click(function() {
		var row = $('#roleTable').bootstrapTable('getSelections');
		if (row.length > 0) {
			// alert($('#userTable').bootstrapTable('getSelections')[0].USER_NAME);
			$.ajax({
				type : 'POST',
				url : basePath + "roleController/getFunctionList?roleCode="
						+ row[0].ROLE_CODE,
				success : function(data) {
					var datas = strToJson(data);
					$('#addRolePurview_tree').treeview({
						data : strToJson(datas)
					});
					$('#addRolePurviewModal').modal({
						show : true,
						backdrop : false
					});

				}
			})

		} else {
			alert("请选择需要添加权限的角色");
		}
	});
	// alert delete purview window
	$('#deletePurview_btn').click(function() {
		var row = $('#roleFunction_tree')
		.treeview('getSelected');
		if (row.length > 0) {
			$('#del_purviewName').text(row[0].text);
			$('#deleteRolePurviewModal').modal({
				show : true
			});
		} else {
			alert("请选择需要删除的权限");
		}
	});
	
	
	// save roleFunctionPurview
	$('#addRolePurview_save_btn').click(function() {
		var row = $('#addRolePurview_tree')
		.treeview('getSelected');
		if (row.length > 0) {
			var rowTable = $('#roleTable').bootstrapTable('getSelections');
			$('#roleCode_addPur').val(rowTable[0].ROLE_CODE);
			$('#functionCode_addPur').val(row[0].FUNCTION_CODE_DISPLY);
			$('#purviewCodes_addPur').val(row[0].PURVIEW_CODE);
			
			var params= $('#addRolePurviewForm').serializeObject();
			var url = basePath + 'roleController/addRoleFunction';
			var success = function(datass) {
				var url2 = basePath + 'roleController/loadRoleFunctionList?roleCode=' + rowTable[0].ROLE_CODE;
				var success2 = function(datass) {
					$('#addRolePurviewModal').modal('hide');
					$('#roleFunction_tree').treeview({
		                data: strToJson(datass)
		            });
				}
				// 提交
				eGdUtil.requestJsonAjax(url2,null,success2);
			}
			// 提交
			eGdUtil.requestJsonAjax(url,params,success);
		} else {
			alert("请选择需要添加的权限");
		}
	});
	
	
	//删除权限
	$('#delRolePurview_btn').click(
			function() {
				var rowTable = $('#roleTable').bootstrapTable('getSelections');
				var row = $('#roleFunction_tree')
				.treeview('getSelected');
				var url;
				if (row[0].LEVEL_NO == 'PURVIEW') {
					url = basePath
							+ 'roleController/deleteRoleFunction?functionCode='
							+ row[0].FUNCTION_CODE_DISPLY + '&purviewCode='
							+ row[0].PURVIEW_CODE + '&roleCode='
							+ rowTable[0].ROLE_CODE;
				} else {
					url = basePath
							+ 'roleController/deleteRoleFunction?functionCode='
							+ row[0].FUNCTION_CODE + '&roleCode='
							+ rowTable[0].ROLE_CODE;
				}
				// 提交
				$.ajax({
					type : 'POST',
					url : url,
					success : function(data) {
						$('#deleteRolePurviewModal').modal('hide');
						$.ajax({
							type : 'POST',
							url : basePath + 'roleController/loadRoleFunctionList?roleCode=' + rowTable[0].ROLE_CODE,
							success : function(datass) {
								$('#roleFunction_tree').treeview({
					                data: strToJson(datass)
					            });
							}
						});
					}
				})
			});
});

function editRole() {
	var row = $('#roleTable').bootstrapTable('getSelections');
	if (row.length > 0) {
		// alert($('#userTable').bootstrapTable('getSelections')[0].USER_NAME);
		$.ajax({
			type : 'POST',
			url : basePath + "roleController/loadRole?roleCode="
					+ row[0].ROLE_CODE,
			success : function(data) {
				var datas = strToJson(data);
				$("#roleName_edit").val(datas.roleName);
				$("#roleCode_edit").val(datas.roleCode);
				$('#editRoleModal').modal({
					show : true,
					backdrop : false
				});

			}
		})

	} else {
		alert("请选择需要修改的数据");
	}

}
function deleteRole() {
	var row = $('#roleTable').bootstrapTable('getSelections');
	if (row.length > 0) {
		$('#del_roleName').text(row[0].ROLE_NAME);
		$('#del_roleCode').val(row[0].ROLE_CODE);
		$('#deleteRoleModal').modal({
			show : true
		});
	} else {
		alert("请选择需要删除的数据");
	}

}
