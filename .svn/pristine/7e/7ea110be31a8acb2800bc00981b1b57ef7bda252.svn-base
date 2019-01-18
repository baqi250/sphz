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
<html lang="en">
<head>
<base href="<%=basePath%>">

<!-- jsp文件头和头部 -->
<%@ include file="../system/index/top.jsp"%>
</head>
<body class="no-skin">

	<!-- /section:basics/navbar.layout -->
	<div class="main-container" id="main-container">
		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12" id="mapDiv" style="margin:0 auto;width:100%;overflow: hidden;">
							<iframe name="mapFrame" id="mapFrame" frameborder="0" src="<%=basePath%>map/myvalues.html" style="width:100%;height:100%"></iframe>
							<!-- <iframe src="map.html"></iframe> -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->
	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../system/index/foot.jsp"%>
	

	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	
	
	<script type="text/javascript">
		$(top.hangge());
		$(function() {
			var window_height = document.documentElement.scrollHeight;	
			$("#mapDiv").css("height",window_height);
		});
		$(window).resize(function(){
			var window_height = document.documentElement.scrollHeight;	
			$("#mapDiv").css("height",window_height);
		});
	</script>


</body>
</html>