<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>MES条码系统-登录</title>

    <!-- jQuery 2.1.4 -->
    <script src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/js/bootstrap.min.js"></script>
    <!-- iCheck -->
    <script src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/iCheck/icheck.min.js"></script>
    <script>
        $(function () {
            $('input').iCheck({
                checkboxClass: 'icheckbox_square-blue',
                radioClass: 'iradio_square-blue',
                increaseArea: '20%' // optional
            });
        });
    </script>
    <script type="text/javascript" charset="UTF-8"
            src="${pageContext.request.contextPath}/static/egdfrm/js/login.js"></script>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/css/AdminLTE.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/iCheck/square/blue.css">
</head>
<body>
<div style=" padding-left: 150px;padding-top: 30px">
    <img style="width:50%; height:15%; " src="${pageContext.request.contextPath}/static/unit/img/logoNew.jpg"> </br>
</div>
<div class="login-box">
    <div class="login-logo">
        <a><b>条码管理系统</b></a>
    </div>
    <div class="login-box-body">
        <form id="loginForm" name="loginForm" method="post">
            <div class="form-group has-feedback">
                <input type="user" id="username" name="username" class="form-control" placeholder="登录名">
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" id="password" name="password" class="form-control" placeholder="密码">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <span class="has-error " style="color:red">${rv}</span>
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <button type="button" id="submitBtn" class="btn btn-primary btn-block btn-flat">登录</button>
                </div>
            </div>
        </form>
        <div class="social-auth-links text-center">
            <p></p>
        </div>
    </div>
</div>
</body>
</html>
