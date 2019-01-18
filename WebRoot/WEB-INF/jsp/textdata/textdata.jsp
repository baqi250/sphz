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
<!-- 下拉框 -->
<link rel="stylesheet" href="static/ace/css/chosen.css" />
<!-- <link rel="stylesheet" href="static/ace/css/bootstrap.min.css" /> -->
<link rel="stylesheet" href="plugins/xha/plugins/bootstrap/dist/css/bootstrap.css">
<!-- jsp文件头和头部 -->

<%@ include file="../system/index/top.jsp"%>
<script type="text/javascript">
		//刷新ztree
		function parentReload(){
			if(null != ${MSG} && 'change' == ${MSG}){
				parent.location.href="<%=basePath%>text/listAllText.do?ID="+${ID};
			}else{
				//什么也不干
			}
		}
		parentReload();
	</script>
<!-- 日期框 -->
<link rel="stylesheet" href="static/ace/css/datepicker.css" />
</head>
<body class="no-skin">

	<!-- /section:basics/navbar.layout -->
	<div class="main-container" id="main-container">
		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
						
						<form action="text/list.do?ID=${ID }" method="post" name="textDataForm" id="textDataForm">
						<table style="margin-top:5px;">
							<tr>
								<td>
									<div class="nav-search">
										<span class="input-icon">
											<input type="text" placeholder="这里输入关键词" class="nav-search-input" id="nav-search-input" autocomplete="off" name="keywords" value="${pd.keywords }" placeholder="这里输入关键词"/>
										</span>
									</div>
								</td>							
								<c:if test="${QX.cha == 1 }">
								<td style="vertical-align:top;padding-left:2px"><a class="btn btn-light btn-xs" onclick="toSearch();"  title="检索"><i id="nav-search-icon" class="ace-icon fa fa-search bigger-110 nav-search-icon blue"></i></a></td>
								<td style="vertical-align:top;padding-left:2px"><a class="btn btn-light btn-xs" onclick="reset();"  title="重置"><i id="nav-search-icon" class="menu-icon fa fa-credit-card"></i></a></td>
								</c:if>
								<!-- 
								<c:if test="${QX.toExcel == 1 }"><td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="ace-icon fa fa-download bigger-110 nav-search-icon blue"></i></a></td></c:if>
							   	-->
							</tr>
						</table>
					
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">	
							<thead>
								<tr>
									<th class="center" style="width:35px;">
									<label class="pos-rel"><input type="checkbox" class="ace" id="zcheckbox" /><span class="lbl"></span></label>
									</th>
									<th class="center">序号</th>										
									<th class="center" >名称</th>
									<th class="center" >数据类型</th>
									<th class="center" >数据来源</th>
									<th class="center" >入库时间</th>
									<th class="center" >印发单位</th>
									<th class="center" >印发时间</th>
									<th class="center">操作</th>
								</tr>
							</thead>
													
							<tbody>
							<!-- 开始循环 -->	
							<c:choose>
								<c:when test="${not empty dataList}">
									<%-- <c:if test="${QX.cha == 1 }"> --%>
									<c:forEach items="${dataList}" var="var" varStatus="vs">
										<tr>
											<td class='center'>
												<label class="pos-rel"><input type='checkbox' name='ids' value="${var.id}" class="ace" /><span class="lbl"></span></label>
											</td>												
											<td class='center' style="width: 50px;">${vs.index+1}</td>									
											<td class="center">${var.name}</td>
											<td class="center">${var.data_type}</td>													
											<td class="center">${var.data_source}</td>
											<td class="center">${var.in_time}</td>	
											<td class="center">${var.company}</td>
											<td class="center">${var.print_time}</td>						
											<td class="center" style="width:130px;">
												<%-- <c:if test="${QX.edit != 1 && QX.del != 1 }">
												<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
												</c:if>		 --%>
												<div class="hidden-sm hidden-xs btn-group">
													<%-- <c:if test="${QX.del == 1 }"> --%>
													<a class="red" onclick="del('${var.id}');">
														<i class="ace-icon fa fa-trash-o bigger-120" title="删除"></i>
													</a>
													<%-- </c:if> --%>
												</div>													
												<%-- <c:if test="${QX.cha == 1 }">    --%>          
													<a class="menu-icon fa fa-eye" onclick="viewDetail(${vs.index});"  title="查看"></a>																	
												<%-- </c:if>	 --%>
												<%-- <c:if test="${QX.edit == 1}"> --%>
													<a href="javascript:editmenu('${var.id }');" class="tooltip-success" data-rel="tooltip" title="编辑">
														<span class="green">
															<i class="ace-icon fa fa-pencil-square-o bigger-120" title="修改"></i>
														</span>
													</a>
												<%-- </c:if>		 --%>																																																																																																																																									
											</td>
										</tr>
									</c:forEach>
									<%-- </c:if>
									<c:if test="${QX.cha == 0 }">
										<tr>
											<td colspan="100" class="center">您无权查看</td>
										</tr>
									</c:if> --%>
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
								<%-- <td style="vertical-align:top;">
									<c:if test="${QX.add == 1 }"><a class="btn btn-sm btn-success" onclick="addmenu('${ID}');">新增</a></c:if>
									<c:if test="${QX.del == 1 }">
									<a class="btn btn-sm btn-danger" onclick="makeAll('确定要删除选中的数据吗?');" title="批量删除" ><i class='ace-icon fa fa-trash-o bigger-120'></i></a>
									</c:if>
								</td> --%>
								<td style="vertical-align:top;">
									<a class="btn btn-sm btn-success" onclick="addmenu('${ID}');">新增</a>
									
									<a class="btn btn-sm btn-danger" onclick="makeAll('确定要删除选中的数据吗?');" title="批量删除" ><i class='ace-icon fa fa-trash-o bigger-120'></i></a>
									
								</td>
								<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
							</tr>
						</table>
						</div>
						</form>												
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->
		
		<!-- 返回顶部 -->
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>
	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../system/index/foot.jsp"%>	
	<!-- 删除时确认窗口 -->
	<script src="static/ace/js/bootbox.js"></script>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	
	<script type="text/javascript">
		$(top.hangge());//关闭加载状态
		//检索
		 function toSearch(){
		 	top.jzts();
			var namereg=/[`~!@:?"|#￥$%^&*_+<>\\{}\/'[\]-]/im;
			if(namereg.test($("#nav-search-input").val())){
				$("#nav-search-input").tips({
					side:3,
		            msg:'存在特殊字符',
		            bg:'#AE81FF',
		            time:2
	        	});
	        $("#nav-search-input").val('');
				return;
			}
			$("#textDataForm").submit();
		} 
		
		//重置
		function reset() {
	        $("#nav-search-input").val('');		
		}	
		//删除
		function del(Id){
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>text/delete.do?id="+Id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						if("success" == data){
							nextPage(${page.currentPage});
							parent.location.href="<%=basePath%>text/listAllText.do?ID="+${ID};  // ${ID} 是父ID
						}
						
					});
				}
			});
		}
		
		//新增菜单
		function addmenu(ID){
			top.jzts();
			window.location.href="<%=basePath%>text/toAdd.do?ID="+ID;
		};
		
		//编辑菜单
		function editmenu(ID){
			top.jzts();
			window.location.href="<%=basePath%>text/toEdit.do?id="+ID;
		};
		
		function viewDetail(index){
			//top.jzts();
			var p_url = this.$.ajaxSettings.url;
			console.log(this);
			var PID="";
			var ISBOTTOMNODE = "";
			var FTP_URL = "";
			var ID = "";
			if(p_url.indexOf("?")!=-1){
			    var url=p_url.split("?")[1];
			    if(url.match(RegExp("(^|&)" + "id" + "=([^&]*)(&|$)", "i"))){
			        PID=decodeURI(url.match(RegExp("(^|&)" + "id" + "=([^&]*)(&|$)", "i"))[2]);
			    }
			    if(url.match(RegExp("(^|&)" + "ISBOTTOMNODE" + "=([^&]*)(&|$)", "i"))){
			        ISBOTTOMNODE=decodeURI(url.match(RegExp("(^|&)" + "ISBOTTOMNODE" + "=([^&]*)(&|$)", "i"))[2]);
			    }
			}			
			for(var i=0;i<parent.zTree.setting.root.nodes.length;i++){
				if(PID==parent.zTree.setting.root.nodes[i].id){
					FTP_URL = parent.zTree.setting.root.nodes[i].nodes[index].FTP_URL;
					ID = parent.zTree.setting.root.nodes[i].nodes[index].id;
				}
			} 
			//console.log(parent.zTree.setting.root);
			window.location.href="<%=basePath%>text/listdetail.do?ID="+ID+"&FTP_URL="+FTP_URL+"&ISBOTTOMNODE="+ISBOTTOMNODE;
		}
		
		//批量操作
		function makeAll(msg){
			bootbox.confirm(msg, function(result) {
				if(result) {
					var str = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++){
					  if(document.getElementsByName('ids')[i].checked){
					  	if(str=='') str += document.getElementsByName('ids')[i].value;
					  	else str += ',' + document.getElementsByName('ids')[i].value;
					  }
					}
					if(str==''){
						bootbox.dialog({
							message: "<span class='bigger-110'>您没有选择任何内容!</span>",
							buttons: 			
							{ "button":{ "label":"确定", "className":"btn-sm btn-success"}}
						});
						$("#zcheckbox").tips({
							side:1,
				            msg:'点这里全选',
				            bg:'#AE81FF',
				            time:8
				        });
						return;
					}else{
						if(msg == '确定要删除选中的数据吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '<%=basePath%>text/deleteAll.do?tm='+new Date().getTime(),
						    	data: {DATA_IDS:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
									 $.each(data.list, function(i, list){
											nextPage(${page.currentPage});
											parent.location.href="<%=basePath%>text/listAllText.do?ID="+${ID};
									 });
								}
							});
						}
					}
				}
			});
		};
		$(function() {
			//复选框全选控制
			var active_class = 'active';
			$('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
				var th_checked = this.checked;//checkbox inside "TH" table header
				$(this).closest('table').find('tbody > tr').each(function(){
					var row = this;
					if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
					else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
				});
			});
			
		});
	</script>


</body>
</html>