$(function() {
	//初始化光标定位
	$('#username').focus();
	// 表单提交
	$('#submitBtn').click(function() {
		login();
	});

	// 界面键盘事件
	$('#loginForm').keydown(function(e) {
		if (e.keyCode == 13) {
			$('#submitBtn').click();
		}
	});

	// 登录
	function login() {
		var usnm=$('#username').val();
		var pswd=$('#password').val();
		if(usnm==null ||usnm.trim()=='' ){
			alert('用户名不能为空');
			return;
		}
		if(pswd==null ||pswd.trim()=='' ){
			alert('密码不能为空');
			return;
		}
		$('#loginForm').submit();
	}
});