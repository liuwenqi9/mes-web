

$(function() {
	
	//加载menu
	$.ajax({
		type : 'POST',
        url:basePath+'mainController/loadMenu',
        success: function(data) {
            $('#menuUl').append(data);
        }
	})
	
	// 修改密码
	$('#changePswd_menu').click(function() {
		//弹出界面
		$('#editPWDModal').modal({
			show : true,
			backdrop : false
		});
		//重置输入框
		$('#old_pwd').val('');
		$('#new_pwd').val('');
		$('#pwd_confim').val('');
	});

	// 退出
	$('#login_out_menu').click(function() {
		top.location.href = basePath + 'logout';
	});

	$('#tabTittles a').click(function(e) {
		e.preventDefault();// 阻止a链接的跳转行为
		$(this).tab('show');// 显示当前选中的链接及关联的content
	})

	//修改密码保存按钮
	$('#editPwd_save_btn').click(function() {
//		$('#editPWDForm').attr('action', basePath + 'mainController/changePswd');
//		// 提交
//		$("#editPWDForm").submit(function() {
//			$(this).ajaxSubmit(function() {
//				alert(1);
//				$('#editPWDModal').modal('hide');
//			});
//			return false; // 阻止表单默认提交
//		});
		var oldPswd = $('#old_pwd').val();
		var newPswd = $('#new_pwd').val();
		var pwdConfim = $('#pwd_confim').val(); 
		if(!(oldPswd&&newPswd&&pwdConfim)){
			showErrorMsg('参数不能为空！');
			return;
		}
		if(newPswd!=pwdConfim){
			showErrorMsg('新密码输入不一致！');
			return;
		}
		var params = { 
			oldPswd : $('#old_pwd').val(),
			newPswd : $('#new_pwd').val() 
		};
		var url = basePath + 'mainController/changePassword';
		var success = function(datas) { 
			if(datas.returnCode=='success'){
				showInfoMsg('修改密码成功！');
				/*
				 * 重置表单
				 */
				$('#editPWDModal').modal('hide');
			}else{
				showErrorMsg(datas.errMsg);
			}
		};
		var faiFn = function(datas){
			showErrorMsg(datas.errMsg);
		}
		eGdUtil.requestJsonAjax(url, params, success,faiFn);
	})  
	
});
function toFunction(functionCode, functionName, functionUrl) {
	var bodeHeight = $(document.body).height();
	$('#myTabs').find('.active').removeClass('active');
	if (!$("#FC_LI_" + functionCode)[0]) {
		var path = basePath;
		$("#tabTittles").append('<li role="presentation" id="FC_LI_'+functionCode+'"><a href="#FC_'+functionCode+'" aria-controls="home" role="tab" data-toggle="tab">'+functionName+'</a><i class=" close-tab glyphicon glyphicon-remove" onclick="closeTab(\''+functionCode+'\')"></i></li>');
		var html = '<div role="tabpanel" class="tab-pane" id="FC_'+functionCode+'"> <div class="embed-responsive" style="padding-bottom: '+(bodeHeight-140)+'px;"> <iframe class="embed-responsive-item" src="'+path+functionUrl+'"></iframe> </div> </div>';
		$("#tabContents").append(html);
	}
	$("#FC_LI_" + functionCode).addClass('active');
    $("#FC_" + functionCode).addClass('active');
}

function closeTab(id){
	//如果关闭的是当前激活的TAB，激活他的前一个TAB
    if ($('#myTabs').find("li.active").attr('id') == "FC_LI_" + id) {
        $("#FC_LI_" + id).prev().addClass('active');
        $("#FC_" + id).prev().addClass('active');
    }
    //关闭TAB
    $("#FC_LI_" + id).remove();
    $("#FC_" + id).remove();
}
