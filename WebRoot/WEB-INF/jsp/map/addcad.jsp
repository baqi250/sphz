<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style>
.fileinput-remove{
height:34px;
}
</style>
</head>

<body>
	<div class="content" style="width:80%; margin-left:10%;margin-top:50px;">
		<form class="form-horizontal m-t" id="signupForm">
			<div class="form-group" style="margin-bottom:20px;">
				<label class="col-sm-3 control-label">上传CAD文件</label>
				<div class="col-sm-8">
					<input id="file" name="file" type="file" accept=".dxf">
				</div>
			</div>
			<div class="form-group" style="margin-top:50px;">
				<div style="width:100%;text-align:center;">
					<button type="submit" class="btn btn-primary submit">提交</button>
				</div>
			</div> 
		</form>
	</div>
	<link rel="stylesheet" href="plugins/xha/plugins/bootstrap/dist/css/bootstrap.css">
	<link rel="stylesheet" href="plugins/bootstrap/fileinput/css/fileinput.css">
    <script src="plugins/xha/plugins/jquery/dist/jquery.min.js"></script>
    <script src="plugins/jquery/jquery.validate.min.js"></script>
    <script src="plugins/xha/plugins/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="plugins/bootstrap/fileinput/js/fileinput.js"></script>
    <script src="static/js/myjs/onemap/addcad.js"></script>
</body>
</html>
