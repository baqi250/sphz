<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${pd.SYSNAME}-系统登录</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="sphz/plugins/bootstrap/dist/css/bootstrap.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="sphz/plugins/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="sphz/plugins/Ionicons/css/ionicons.min.css">
    <!-- iCheck for checkboxes and radio inputs -->
    <link rel="stylesheet" href="sphz/plugins/iCheck/all.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="sphz/dist/css/AdminLTE.css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="sphz/dist/css/pages/login.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>

<body class="hold-transition login-page">
    <div class="login-box">
        <div class="login-logo">
            <a href="#">${pd.SYSNAME}</a>
        </div>
        <!-- /.login-logo -->
        <div id="loginbox" class="login-box-body">
            <p class="login-box-msg">系统登录</p>

          <!--   <form action="" method="post"> -->
                <div class="form-group has-feedback">
                    <input type="text" class="form-control" id="username" placeholder="用户名">
                    <span class="glyphicon glyphicon-user form-control-feedback"></span>
                </div>
                <div class="form-group has-feedback">
                    <input type="password" class="form-control" id="password" placeholder="密码">
                    <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                </div>
                <div class="row">
                    <div class="col-xs-8">
                        <div class="checkbox icheck">
                            <label>
                              <input id="saveid" type="checkbox" onclick="savePaw();"> 记住我？
                            </label>
                        </div>
                    </div>
                    <!-- /.col -->
                    <div class="col-xs-4">
                        <button id="login" class="btn btn-primary btn-block btn-flat">登录</button>
                    </div>
                    <!-- /.col -->
                </div>
           <!--  </form> -->
            <!-- /.social-auth-links -->
            <div class="row login-footer">
                <div class="col-xs-6">
                    <a href="#">忘记用户名</a>
                </div>
                <div class="col-xs-6 login-register">
                    <a href="register.html" class="text-center">注册新用户</a>
                </div>
            </div>
            <!-- /.login-box-body -->
        </div>
        <!-- /.login-box -->

        <!-- jQuery 3 -->
        <script src="sphz/plugins/jquery/dist/jquery.min.js"></script>
        <script type="text/javascript" src="static/js/jquery.cookie.js"></script>
        <script src="static/js/jquery.tips.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="sphz/plugins/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- iCheck 1.0.1 -->
        <script src="sphz/plugins/iCheck/icheck.min.js"></script>
	    <script type="text/javascript">
            $(function() {
                $('input').iCheck({
                    checkboxClass: 'icheckbox_square-blue',
                    radioClass: 'iradio_square-blue',
                    increaseArea: '20%' /* optional */
                });
                
                $('#login').on('click',function(){
	               	if(check()){
	                	var loginname = $("#username").val();
						var password = $("#password").val();
						var code = "qq313596790fh"+loginname+",fh,"+password+"QQ978336446fh"+",fh,"+"4S56";
						$.ajax({
							type: "POST",
							url: 'login_login',
					    	data: {KEYDATA:code,tm:new Date().getTime()},
							dataType:'json',
							cache: false,
							//async:false,
							success: function(data){
								console.log(data);
								if("success" == data.result){
									//alert(1);
									saveCookie();
									window.location.href="main/index";
								}else if("usererror" == data.result){
									$("#username").tips({
										side : 1,
										msg : "用户名或密码有误",
										bg : '#FF5080',
										time : 1
									});
									//showfh();
									$("#username").focus();
								}
							},
							error:function(data){
								
								console.log(data);
								alert(error);
							}
						});
					}
                })
                
                function saveCookie() {
              
					if ($("#saveid").attr("checked")){
						$.cookie('username', $("#username").val(), {
							expires : 7
						});
						$.cookie('password', $("#password").val(), {
							expires : 7
						});
					}
				}
					//键盘回车事件，执行登录
					$(document).keyup(function(event) {
						if (event.keyCode == 13) {
							$("#login").trigger("click");
						}
					});
				
					jQuery(function() {
						var username = $.cookie('username');
						var password = $.cookie('password');
						if (typeof(username) != "undefined"
								&& typeof(password) != "undefined") {
							$("#username").val(username);
							$("#password").val(password);
							$("#saveid").attr("checked", true);
							$("#code").focus();
						}
					});
				
				function savePaw() {
					if (!$("#saveid").attr("checked")) {
						$.cookie('username', '', {
							expires : -1
						});
						$.cookie('password', '', {
							expires : -1
						});
						$("#username").val('');
						$("#password").val('');
					}
				}
				
				//客户端校验
				function check() {
		
					if ($("#username").val() == "") {
						$("#username").tips({
							side : 2,
							msg : '用户名不得为空',
							bg : '#AE81FF',
							time : 1
						});
						$("#username").focus();
						return false;
					} else {
						$("#username").val(jQuery.trim($('#username').val()));
					}
					if ($("#password").val() == "") {
						$("#password").tips({
							side : 2,
							msg : '密码不得为空',
							bg : '#AE81FF',
							time : 1
						});
						$("#password").focus();
						return false;
					}
					
					return true;
				}
					
            });
            
           
        </script>
</body>

</html>