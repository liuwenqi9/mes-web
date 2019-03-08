	function getUsers(load){
		var getUsers_url = basePath + 'userController/getUsers';
		if(eGdUtil.isEmpty(load)){
			//初始化
			var getUsers_suc = function(datass) {
				$('#userTable').bootstrapTable({
					data : strToJson(datass)
				});
			}
		}else{
			var getUsers_suc = function(datass) {
				$('#userTable').bootstrapTable('load', {
					data : strToJson(datass)
				});
			}
		}
		eGdUtil.requestJsonAjax(getUsers_url,null,getUsers_suc);
	};

	//------------add_form init-----
	function formValidate(type){ 
		 var loginName = $('#loginName_'+type).val();
		 var userName = $('#userName_'+type).val();
		 var userRoleId = $('#userRoleId_'+type+' option:selected').val();
		 var userDeptId = $('#userDept_'+type+' option:selected').val();
		 var userPlanLine = $('#userPlanLine_'+type+' option:selected').val();
		 if(loginName==''){ 
			 $('#loginName_'+type+'_span_a').html('必填！');
			 $('#loginName_'+type+'_span_a').css("color","red");
			 $('#loginName_'+type+'_a').addClass('has-error'); 
			 return false;
		 } 
		 if(userName==''){
			 $('#userName_'+type+'_span_b').html('必填！');
			 $('#userName_'+type+'_span_b').css("color","red");
			 $('#userName_'+type+'_b').addClass('has-error');
			 return false;
		 } 
		 if(userRoleId=='0'){ 
			 $('#userRoleId_'+type+'_span_e').html('必选！');
			 $('#userRoleId_'+type+'_span_e').css("color","red");
			 $('#userRoleId_'+type+'_e').addClass('has-error');
			 return false;
		 }
		 //如果是生产部，则生产线为必选
		 if(userDeptId=='PRODUCT_DEPT'&&userPlanLine=='0'){ 
			 $('#userPlanLine_'+type+'_span_e').html('必选！');
			 $('#userPlanLine_'+type+'_span_e').css("color","red");
			 $('#userPlanLine_'+type+'_e').addClass('has-error');
			 return false;
		 }else{
			 $('#userPlanLine_'+type+'_span_e').html('');
			 $('#userPlanLine_'+type+'_e').removeClass('has-error'); 
		 }
		 return true;
	 }
	function myInit(){
		$('#loginName_add').blur(function(){
			var loginName = $('#loginName_add').val(); 
			//alert(loginName+'r');
			if(loginName!=''){
				$.ajax({
					type : 'POST',
					data : {'loginName':loginName},
					url : basePath +'userController/isLoginName',
					timeout:5000,
					success:function(data){ 
						var results = strToJson(data); 
						if(results.returnCode=='1'){
							$('#loginName_add_span_a').html('');
							$('#loginName_add_a').removeClass('has-error');
						}else{
							$('#loginName_add_span_a').html('该用户已存在');
							$('#loginName_add_span_a').css("color","red");
							$('#loginName_add_a').addClass('has-error');
						}
					} 
				});
			}
		});
		$('#userName_add').keyup(function(){
			if ($(this).val()!=''){  
				 $('#userName_add_span_b').html('');
				 $('#userName_add_b').removeClass('has-error'); 
			}else{
				 $('#userName_add_span_b').html('必填！');
				 $('#userName_add_span_b').css("color","red");
				 $('#userName_add_b').addClass('has-error');
			}
		});
		$('#userName_edit').keyup(function(){
			if ($(this).val()!=''){  
				 $('#userName_edit_span_b').html('');
				 $('#userName_edit_b').removeClass('has-error'); 
			}else{
				 $('#userName_edit_span_b').html('必填！');
				 $('#userName_edit_span_b').css("color","red");
				 $('#userName_edit_b').addClass('has-error');
			}
		});
		/*$('#userMPhome_add').keyup(function(){  
		 var mobile=/^((13[0-9]{1})|159|153)+\d{8}$/;
		 var us =$(this).val();
			if (mobile.test(us)){  
				 $('#userMPhome_add_span_c').html('');  
				 $('#userMPhome_add_c').removeClass('has-error'); 
				 c=true;
			}else if(us==''){
				 $('#userMPhome_add_span_c').html('必填！');
				 $('#userMPhome_add_span_c').css("color","red");
				 $('#userMPhome_add_c').addClass('has-error');
				 c=false; 
			}else{  
				 $('#userMPhome_add_span_c').html('请输入正确的手机号码！');
				 $('#userMPhome_add_span_c').css("color","red");
				 $('#userMPhome_add_c').addClass('has-error');
				 c=false; 
			}
		});*/
		/*$('#userEmail_add').keyup(function(){
			var email = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
			var valemail = $(this).val();
			if (email.test(valemail)){   
				$('#userEmail_add_span_d').html('');
				 $('#userEmail_add_d').removeClass('has-error');
				 d=true; 
			}else if(valemail==''){
				$('#userEmail_add_span_d').html('必填！');
				 $('#userEmail_add_span_d').css("color","red");
				 $('#userEmail_add_d').addClass('has-error');
				  d=false;  
			}else{
				$('#userEmail_add_span_d').html('请输入正确的邮箱地址！');
				 $('#userEmail_add_span_d').css("color","red");
				 $('#userEmail_add_d').addClass('has-error');
				  d=false;  
			}
		});*/

		$('#userRoleId_add').change(function(){
			var roleId=$("#userRoleId_add option:selected").val(); 
			if(roleId=='0'){
				$('#userRoleId_add_span_e').html('必选！');
				 $('#userRoleId_add_span_e').css("color","red");
				 $('#userRoleId_add_e').addClass('has-error');
			}else{
				$('#userRoleId_add_span_e').html(''); 
				$('#userRoleId_add_e').removeClass('has-error');
			}
		});
		$('#userRoleId_edit').change(function(){
			var roleId=$("#userRoleId_edit option:selected").val(); 
			if(roleId=='0'){
				$('#userRoleId_edit_span_e').html('必选！');
				$('#userRoleId_edit_span_e').css("color","red");
				$('#userRoleId_edit_e').addClass('has-error');
			}else{
				$('#userRoleId_edit_span_e').html(''); 
				$('#userRoleId_edit_e').removeClass('has-error');
			}
		});
		//部门
		$('#userDept_add').change(function(){
			$('#userPlanLine_add_span_e').html(''); 
			$('#userPlanLine_add_e').removeClass('has-error');
		});
		//部门
		$('#userDept_edit').change(function(){
			$('#userPlanLine_edit_span_e').html(''); 
			$('#userPlanLine_edit_e').removeClass('has-error');
		});
//		//生产线
//		$('#userPlanLine_add').change(function(){
//			var roleId=$("#userPlanLine_add option:selected").val(); 
//			if(roleId=='0'){
//				$('#userPlanLine_add_span_e').html('必选！');
//				 $('#userPlanLine_add_span_e').css("color","red");
//				 $('#userPlanLine_add_e').addClass('has-error');
//				 planLineFlg=false;
//			}else{
//				$('#userPlanLine_add_span_e').html(''); 
//				$('#userPlanLine_add_e').removeClass('has-error');
//				planLineFlg=true;
//			}
//		});
	 }
	 
	//-------------------	
	
	
$(function() {
	//初始化
	//MyValidator.init();
	getUsers();
	myInit();
	//用户新增保存按钮事件
	$('#addUser_save_btn').click(
		function() {
			var loginNameRepeatFlg=true;
			
			//验证用户名重复
			var loginName = $('#loginName_add').val(); 
			if(loginName!=''){
				$.ajax({
					type : 'POST',
					data : {'loginName':loginName},
					url : basePath +'userController/isLoginName',
					timeout:5000,
					success:function(data){ 
						var results = strToJson(data); 
						if(results.returnCode=='1'){
							$('#loginName_add_span_a').html('');
							$('#loginName_add_a').removeClass('has-error');
							loginNameRepeatFlg= true;
						}else{
							$('#loginName_add_span_a').html('该用户已存在');
							$('#loginName_add_span_a').css("color","red");
							$('#loginName_add_a').addClass('has-error');
							loginNameRepeatFlg= false;
						}
					}
				});
			}
			if(!loginNameRepeatFlg){
				return;
			}
			if(formValidate('add')){//
				var url = basePath + 'userController/addUserNew?userRoleId='+$("#userRoleId_add option:selected").val()
				+'&&userDept='+$("#userDept_add option:selected").val()
				+'&&userPlanLine='+$("#userPlanLine_add option:selected").val()
                +'&&userStatus='+$("#userStatus_add option:selected").val();
				var params = $('#addUserForm').serializeObject();
				var success = function(datass) {
					$('#addUserModal').modal('hide');
					$(':input', '#addUserForm').not(':button, :submit, :reset, :hidden')
					.val('').removeAttr('checked').removeAttr('selected');
					
					getUsers('load');
					
				};
				eGdUtil.requestJsonAjax(url,params,success); 
			}
		});
	
		
	//用户编辑保存按钮事件
	$('#editUser_save_btn').click(
			function() {
				if(formValidate('edit')){//
					var params = $('#editUserForm').serializeObject();
					var url = basePath + 'userController/editUserNew?userRoleId='+$("#userRoleId_edit option:selected").val()
					+'&&userDept='+$("#userDept_edit option:selected").val()
					+'&&userPlanLine='+$("#userPlanLine_edit option:selected").val()
                    +'&&userStatus='+$("#userStatus_edit option:selected").val();
					
					var success = function(datass) {
						$('#editUserModal').modal('hide')
						$(':input', '#editUserForm').not(':button, :submit, :reset, :hidden')
								.val('').removeAttr('checked').removeAttr('selected');
						getUsers('load');
					};
					eGdUtil.requestJsonAjax(url,params,success);
				}
			})
	$('#delUser_btn').click(
			function() {
				// 提交
				var url = basePath + 'userController/deleteUserNew?loginName='+$('#del_loginName').text();
				var success = function(data) {
					$('#deleteUserModal').modal('hide');
					getUsers('load');
				};
				eGdUtil.requestJsonAjax(url,null,success);
			});
	//密码重置 add by sjf 20170110
	$('#resetPassword_btn').click(
			function() {
				// 提交
				var url = basePath + 'userController/saveDefPswd?loginName='+$('#reset_loginName').text();
				var success = function(data) {
					$('#resetPasswordModal').modal('hide');
					getUsers('load');
					showInfoMsg('密码重置成功！');
				};
				eGdUtil.requestJsonAjax(url,null,success);
			});
		$('#addUser_btn').click(
			function() {
				$.ajax({
					type : 'POST',
					url : basePath + "userController/getUserLists",
					success : function(data) {
						var datas = strToJson(data);

						$('#userRoleId_add').empty();
						$('#userRoleId_add').append("<option value='0'>--请选择角色--</option>");
						$('#userDept_add').empty();
						$('#userDept_add').append("<option value='0'>--请选择部门--</option>");
						$('#userPlanLine_add').empty();
						$('#userPlanLine_add').append("<option value='0'>--请选择生产线--</option>");
                        $('#userStatus_add').empty();
                        $('#userStatus_add').append("<option value='1'>有效</option>");
                        $('#userStatus_add').append("<option value='0'>无效</option>");
						for(var i=0;i<datas.length;i++){
							if(datas[i].ROLE_CODE){
								$('#userRoleId_add').append("<option value='"+datas[i].ROLE_CODE+"'>"+datas[i].ROLE_NAME+"</option>");
							}
							if(datas[i].LOOKUP_CODE){
								$('#userDept_add').append("<option value='"+datas[i].LOOKUP_CODE+"'>"+datas[i].DESCRIPTION+"</option>");
							}
							if(datas[i].FLEX_VALUE){
								$('#userPlanLine_add').append("<option value='"+datas[i].FLEX_VALUE+"'>"+datas[i].PLANLINEDESC+"</option>");
							}
						} 
					}
				});
				$('#addUserModal').modal({
					show : true
				});
				
			});
			

});

function editUser() {
	var row = $('#userTable').bootstrapTable('getSelections');
	if (row.length > 0) {
		// alert($('#userTable').bootstrapTable('getSelections')[0].USER_NAME);
		$.ajax({
			type : 'POST',
			url : basePath + "userController/getUserLists",
			success : function(data) {
				var datas = strToJson(data);

				$('#userRoleId_edit').empty();
				$('#userRoleId_edit').append("<option value='0'>--请选择角色--</option>");
				$('#userDept_edit').empty();
				$('#userDept_edit').append("<option value='0'>--请选择部门--</option>");
				$('#userPlanLine_edit').empty();
				$('#userPlanLine_edit').append("<option value='0'>--请选择生产线--</option>");
                $('#userStatus_edit').empty();
                $('#userStatus_edit').append("<option value='1'>有效</option>");
                $('#userStatus_edit').append("<option value='0'>无效</option>");
				for(var i=0;i<datas.length;i++){
					if(datas[i].ROLE_CODE){
						$('#userRoleId_edit').append("<option value='"+datas[i].ROLE_CODE+"'>"+datas[i].ROLE_NAME+"</option>");
					}
					if(datas[i].LOOKUP_CODE){
						$('#userDept_edit').append("<option value='"+datas[i].LOOKUP_CODE+"'>"+datas[i].DESCRIPTION+"</option>");
					}
					if(datas[i].FLEX_VALUE){
						$('#userPlanLine_edit').append("<option value='"+datas[i].FLEX_VALUE+"'>"+datas[i].PLANLINEDESC+"</option>");
					}
				} 
			}
		})
		$.ajax({
			type : 'POST',
			url : basePath + "userController/loadUser?loginName="
					+ row[0].LOGIN_NAME,
			success : function(data) {
				var datas = strToJson(data);
				$("#userName_edit").val(datas.userName);
				$("#loginName_edit").val(datas.loginName);
				$("#userMPhome_edit").val(datas.userMPhome);
				$("#userEmail_edit").val(datas.userEmail);
                $("#userStatus_edit").val(datas.userStatus);
				$('#editUserModal').modal({
					show : true,
					backdrop : false,
				}); 
				/**
				 * 初始化下拉框值 sjf
				 */
				$("#userRoleId_edit").val(datas.roleCode);
				$("#userDept_edit").val(datas.deptCode);
				$("#userPlanLine_edit").val(datas.planLineCode);

			}
		})
		

	} else {
		alert("请选择需要修改的数据");
	}

}
function deleteUser() {
	var row = $('#userTable').bootstrapTable('getSelections');
	if (row.length > 0) {
		$('#del_loginName').text(row[0].LOGIN_NAME);
		$('#deleteUserModal').modal({
			show : true
		});
	} else {
		alert("请选择需要删除的数据");
	}
}
//重置密码框弹出 add by sjf 20170110
function resetPassword() {
	var row = $('#userTable').bootstrapTable('getSelections');
	if (row.length > 0) {
		$('#reset_loginName').text(row[0].LOGIN_NAME);
		$('#resetPasswordModal').modal({
			show : true
		});
	} else {
		alert("请选择需要重置密码的用户");
	}
}
