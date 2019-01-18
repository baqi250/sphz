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
						<div>
							<input type="text" value="${pd.ID }" style="visibility: hidden;">
							<input type="text" id="text_url" value="${pd.FTP_URL }" style="visibility: hidden;">
							<input type="text" value="${pd.ISBOTTOMNODE }" style="visibility: hidden;">
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
			var ftp_url = $("#text_url").val();
			$.ajax({
				type: "POST",
					url: '<%=basePath%>text/preview.do,
			    	data: {FTP_URL:ftp_url},
					dataType:'text',
					success: function(data){
						alert("ok");
					}
			});
			//alert(url);
			window.open(ftp_url);
			//top.mainFrame.tabAddHandler("1","2",url);
		});
	</script>


</body>
</html>