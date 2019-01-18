<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>HTML报表测试</title>
</head>
<body>
<!-- 通过一个HTML链接来导出目标报表模版的excel格式报表 -->
<a href="<%=request.getContextPath() %>/ureport/excel?_u=file:城乡规划许可管理情况表.ureport.xml">导出excel</a>
<p></p>
${html}

<!-- jQuery 3 -->
    <script src="<%=request.getContextPath() %>/plugins/xha/plugins/jquery/dist/jquery.min.js "></script>
     <script type="text/javascript">
     	$("a").one("click",function(){
		  $(this).click(function (){return false;});
		});
     </script>
</body>
</html>