<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
	<base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${pd.SYSNAME}</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="plugins/xha/plugins/bootstrap/dist/css/bootstrap.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="plugins/xha/plugins/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="plugins/xha/plugins/Ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="plugins/xha/plugins/layer/theme/default/layer.css">
    <link rel="stylesheet" href="plugins/xha/dist/css/pages/xmkj.css">
    <link rel="stylesheet" href="plugins/xha/dist/css/pages/map.css">
</head>

<body class="hold-transition skin-blue sidebar-mini">
 <!-- Content Wrapper. Contains page content -->
		<input type="hidden" value="${pd.searchValue}" id="searchValue">
		<div class="button-group">
 	 		<button id="btnBack" class="btn btn-block btn-primary btn-inverse " title="返回"><i class="fa fa-mail-reply"></i></button>
		</div>
	    <iframe id="mapIframe" src="sphz/project/goMapDetail?xmwym=${pd.xmwym}"></iframe>
		<iframe id="dataIframe" src="sphz/project/goProjectDetail.do?XMWYM=${pd.xmwym}&XMMC=${pd.xmmc}"></iframe>


	<!-- REQUIRED JS SCRIPTS -->

      <!-- jQuery 3 -->
    <script src="plugins/xha/plugins/jquery/dist/jquery.min.js "></script>
    <script src="plugins/xha/plugins/layer/layer.js"></script>
     <script type="text/javascript">
       $(document).on('click', '#btnBack', function() {
		    window.location='sphz/project/goSpatial?searchValue='+$("#searchValue").val()+'&isBack=back';
		});
     </script>
</body>

</html>