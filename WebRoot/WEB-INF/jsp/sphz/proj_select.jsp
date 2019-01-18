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
<title>${pd.SYSNAME}</title>
<script type="text/javascript" src="static/js/jquery-1.11.2.min.js"></script>
<!-- 下拉框 -->
<link rel="stylesheet" href="static/ace/css/chosen.css" />
<script src="static/ace/js/chosen.jquery.js"></script>
<!-- 页面顶部 -->
<%@ include file="../system/index/top_back.jsp"%>
<link rel="stylesheet" href="static/mycss/my.css"/>



</head>
<body class="no-skin">
	<div class="main-container" id="main-container">
		<div class="main-content">
			<div class="main-content-inner">
					<!-- 检索  -->
					<div class="row">
						<div class="col-xs-12">					
							<form action="sphz/projSelect.do" method="post" name="projForm" id="projForm"  >
								
								<div id="headercontaindanger" class="headcontainer">
									<div id="dangertiaojian" class="sometioajian">
									
										<div id="dangersearch" class="serach">
											<table id="navtable">
												<tr >
													<td>
														<div class="nav-search">
														<!-- <span class="input-icon"> -->
															<input width="300px" id="nav-search-input" type="text" name="keywords"  value="${pd.keywords}" placeholder="这里输入关键字" />
															<!-- <i class="ace-icon fa fa-search nav-search-icon"></i> -->
														<!-- </span> -->
														</div>
													</td>
					   
													<td id="query"><a class="btn btn-light btn-xs" onclick="searchs();"  title="查询">查询</a></td>
													
												</tr>
											</table>
										</div>
										
														
									</div>
								</div>					
								
								<!-- 列表  -->
								<table id="simple-table" class="table table-striped table-bordered table-hover"  style="margin-top:14px;margin-left:22px;
								<c:if test="${not empty projList}">table-layout:fixed;</c:if>
									<thead>
										<tr>
											<th class="center"  style="width:35px;">
											<label><input type="radio" id="zcheckbox" class="ace" disabled="true" /><span class="lblall"></span></label>
											</th>
											<th class="center" style="width: 30px;">选择</th>
											<th class="center" style="width: 50px;">序号</th>
											<th class='center'>项目编号</th>
											<th class='center'>项目名称</th>
											<th class='center'>建设位置</th>
											<th class='center'>建设单位</th>
											
										</tr>
									</thead>
									<tbody>
									<!-- 开始循环 -->	
									<c:choose>
										<c:when test="${not empty projList}">
											
											<c:forEach items="${projList}" var="proj" varStatus="vs">
									<tr>
									    <td class='center' style="width: 30px;">
											<label><input type='radio' name='ids' class="ids" value="${proj.PROJ_NO}"/><span class="lbl"></span></label>
									    </td>
										<td class='center'>${vs.index+1}</td>
										<td class='center'>${proj.PROJ_NO}</td>
										<td class='center'>${proj.PROJ_NAME}</td>
										<td class='center'>${proj.PROJ_LOCATION}</td>
										<td class='center'>${proj.PROJ_UNIT}</td>
									</tr>
									</c:forEach>
											
										</c:when>
										<c:otherwise>
											<tr class="main_info">
												<td colspan="100" class="center" >没有相关数据</td>
											</tr>
										</c:otherwise>
									</c:choose>
									</tbody>
								</table>
								<div class="page-header position-relative">
								<table style="width:100%;">
									<tr>						
										<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
									</tr>
								</table>
							</div>
						</form>
					</div>
				</div>
						
			</div>
		</div>
		<!-- /.main-content -->
		<!-- 返回顶部 -->
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>
	</div>
	<!-- /.main-container -->	
	</body>
	
	<!-- basic scripts -->
	<!-- 页面底部¨ -->
	<%@ include file="../system/index/foot_back.jsp"%>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- 删除时确认窗口 -->
	<script src="static/ace/js/bootbox.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript">		
		
		function searchs(){
			$("#projForm").submit();
		}
			
			
	</script>
</html>

