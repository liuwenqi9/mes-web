<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html >
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp"/>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/js/main.js"></script>
    <title>MES条码系统</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jquery-form/jquery-form.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/css/skins/_all-skins.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/iCheck/flat/blue.css">
    <!-- Morris chart -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/morris/morris.css">
    <!-- jvectormap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <!-- Date Picker -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/datepicker/datepicker3.css">
    <!-- Daterange picker -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/daterangepicker/daterangepicker-bs3.css">
    <!-- bootstrap wysihtml5 - text editor -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
    <style type="text/css">
        .close-tab {
            font-size: 10px;
            position: absolute;
            right: 5px;
            top: 5px;
            padding-left: 25px;
            z-index: 100;
            cursor: hand;
            cursor: pointer;
            color: red;
        }
    </style>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="dist/js/html5shiv.min.js"></script>
    <script src="dist/js/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
        <header class="main-header">
            <a class="logo">
                <span class="logo-mini">菜单</span>
                <span class="logo-lg">
            <img width="90px" height="40px" src='${pageContext.request.contextPath}/static/unit/img/logo.png'/>条码管理系统
        </span>
            </a>
            <nav class="navbar navbar-static-top" role="navigation">
                <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">Toggle navigation</span>
                </a>
                <div class="navbar-custom-menu">
                    <ul class="nav navbar-nav">
                        <li class="dropdown user user-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <img src="${pageContext.request.contextPath}/static/egdfrm/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                                <span class="hidden-xs">${userName}</span>
                            </a>
                            <ul class="dropdown-menu">
                                <li class="user-header">
                                    <img src="${pageContext.request.contextPath}/static/egdfrm/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                                    <p>${userName}</p></li>
                                <li class="user-footer">
                                    <div class="pull-left">
                                        <a href="#" id="changePswd_menu" class="btn btn-default btn-flat">修改密码</a>
                                    </div>
                                    <div class="pull-right">
                                        <a href="#" class="btn btn-default btn-flat" data-toggle="modal" data-target="#logOutModal">退出系统</a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
    <aside class="main-sidebar">
       <section class="sidebar">
           <ul class="sidebar-menu" id="menuUl">

           </ul>
       </section>
   </aside>
    <div class="content-wrapper" id="main_content">
        <div id="myTabs">
            <ul class="nav nav-tabs" role="tablist" id="tabTittles">
                <li role="presentation" class="active">
                    <a href="#home" aria-controls="home" role="tab" data-toggle="tab">首页</a>
                </li>
            </ul>
            <div class="tab-content" id="tabContents">
                <div role="tabpanel" class="tab-pane active" id="home">
                    <div class="embed-responsive embed-responsive-4by3">
                        <iframe src="${pageContext.request.contextPath}/mainController/toWelcome"></iframe>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 1.0
        </div>
        <strong>优利德科技（中国）股份有限公司 版权所有 Copyright (c) 2019 UNI-T. All Rights Reserved. | 反馈 | 与优利德科技联系 | 粤ICP备13089200号 <a href="#">egrid</a>.
        </strong>
    </footer>
    <div class="modal fade" id="editPWDModal" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="exampleModalLabel">密码修改</h4>
                </div>
                <div class="modal-body">
                    <label for="old_pwd" class="col-sm-2 control-label">旧密码</label>
                    <label class="col-sm-10 ">
                        <input type="passWord" class="form-control" id="old_pwd"
                               name="oldPswd" placeholder="旧密码">
                    </label>
                    <label for="new_pwd" class="col-sm-2 control-label">新密码</label>
                    <label class="col-sm-10">
                        <input type="passWord" class="form-control" id="new_pwd"
                               name="newPswd" placeholder="新密码">
                    </label>
                    <label for="pwd_confim" class="col-sm-2 control-label">密码确认</label>
                    <label class="col-sm-10">
                        <input type="passWord" class="form-control" id="pwd_confim"
                               name="pswfConfim" placeholder="请再次输入新密码">
                    </label>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary"
                            id="editPwd_save_btn">保存
                    </button>
                </div>
            </div>
        </div>
    </div>
    <!-- 退出弹出框 -->
    <div class="modal fade" role="dialog"
         aria-labelledby="gridSystemModalLabel" id="logOutModal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="gridSystemModalLabel">是否退出系统？</h4>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-4 col-md-offset-4">请确认是否退出系统</div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="login_out_menu">确认退出</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- jQuery 2.1.4 -->
<script src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<%-- <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script> --%>
<script src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jQueryUI/1.11.4/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.5 -->
<script src="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/js/bootstrap.min.js"></script>
<!-- Morris.js charts -->
<script src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/raphael/2.1.0/raphael-min.js"></script>
<script src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/morris/morris.min.js"></script>
<!-- Sparkline -->
<script src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/sparkline/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<script src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- jQuery Knob Chart -->
<script src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/knob/jquery.knob.js"></script>
<!-- daterangepicker -->
<script src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/moment/2.10.2/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/daterangepicker/daterangepicker.js"></script>
<!-- datepicker -->
<script src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/datepicker/bootstrap-datepicker.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<!-- Slimscroll -->
<script src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/fastclick/fastclick.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/static/egdfrm/dist/js/app.min.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="${pageContext.request.contextPath}/static/egdfrm/dist/js/pages/dashboard.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${pageContext.request.contextPath}/static/egdfrm/dist/js/demo.js"></script>
</body>

</html>
