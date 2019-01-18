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
	<!-- 日期框 (带小时分钟)-->
	<link rel="stylesheet" href="static/ace/css/bootstrap-datetimepicker.css" />

<!-- jsp文件头和头部 -->
 <%@ include file="../../system/index/top.jsp"%>
		<style type="text/css">
		#dialog-add,#dialog-message,#dialog-comment{width:100%; height:100%; position:fixed; top:0px; z-index:99999999; display:none;}
		.commitopacity{position:absolute; width:100%; height:700px; background:#7f7f7f; filter:alpha(opacity=50); -moz-opacity:0.5; -khtml-opacity: 0.5; opacity: 0.5; top:0px; z-index:99999;}
		.commitbox{width:100%; margin:0px auto; position:absolute; top:0px; z-index:99999;}
		.commitbox_inner{width:96%; height:255px;  margin:6px auto; background:#efefef; border-radius:5px;}
		.commitbox_top{width:100%; height:253px; margin-bottom:10px; padding-top:10px; background:#FFF; border-radius:5px; box-shadow:1px 1px 3px #e8e8e8;}
		.commitbox_top textarea{width:95%; height:195px; display:block; margin:0px auto; border:0px;}
		.commitbox_cen{width:95%; height:40px; padding-top:10px;}
		.commitbox_cen div.left{float:left;background-size:15px; background-position:0px 3px; padding-left:18px; color:#f77500; font-size:16px; line-height:27px;}
		.commitbox_cen div.left img{width:30px;}
		.commitbox_cen div.right{float:right; margin-top:7px;}
		.commitbox_cen div.right span{cursor:pointer;}
		.commitbox_cen div.right span.save{border:solid 1px #c7c7c7; background:#6FB3E0; border-radius:3px; color:#FFF; padding:5px 10px;}
		.commitbox_cen div.right span.quxiao{border:solid 1px #f77400; background:#f77400; border-radius:3px; color:#FFF; padding:4px 9px;}
		</style>
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
								<form action="todo/${msg }.do" name="todoForm" id="todoForm" method="post">
									<input type="hidden" name="todo_id" id="todo_id" value="${pd.todo_id }"/>
									
									<div id="zhongxin" style="padding-top: 13px;">
									<table id="table_report" class="table table-striped table-bordered table-hover">
										
										<%-- <tr>
											<td style="width:90px;text-align: right;padding-top: 13px;">发布人:</td>
											<td><input type="text" name="publisher" id="publisher" value="${pd.publisher }" maxlength="100" placeholder="这里输入标题" title="标题" style="width:100%;"/></td>
										</tr> --%>
										
										<tr>
											<td style="width:90px;text-align: right;padding-top: 13px;">标题:</td>
											<td><input type="text" name="title" id="title" value="${pd.title }" maxlength="100" placeholder="这里输入标题" title="标题" style="width:100%;"/></td>
										</tr>
										
										<tr>
											<td style="width:90px;text-align: right;padding-top: 13px;">发布日期:</td>
											<td>
												<div class="input-group bootstrap-timepicker">
												<input readonly="readonly" class="form-control" type="text" name="pub_time" id="pub_time" value="${pd.pub_time}" maxlength="100" placeholder="这里输入发布日期" title="发布日期:" style="width:100%;"/>
												<span class="input-group-addon"><i class="fa fa-clock-o bigger-110"></i></span>
												</div>
											</td>
										</tr>
										
										<%-- <tr>
											<td style="width:90px;text-align: right;padding-top: 13px;">待办开始日期:</td>
											<td>
												<div class="input-group bootstrap-timepicker">
												<input readonly="readonly" class="form-control" type="text" name="begin_time" id="begin_time" value="${pd.begin_time}" maxlength="100" placeholder="这里输入待办开始日期" title="待办开始日期:" style="width:100%;"/>
												<span class="input-group-addon"><i class="fa fa-clock-o bigger-110"></i></span>
												</div>
											</td>
										</tr>
										
										<tr>
											<td style="width:90px;text-align: right;padding-top: 13px;">待办结束日期:</td>
											<td>
												<div class="input-group bootstrap-timepicker">
												<input readonly="readonly" class="form-control" type="text" name="end_time" id="end_time" value="${pd.end_time}" maxlength="100" placeholder="这里输入待办结束日期" title="待办结束日期:" style="width:100%;"/>
												<span class="input-group-addon"><i class="fa fa-clock-o bigger-110"></i></span>
												</div>
											</td>
										</tr> --%>
										
										<tr>
											<td style="width:90px;text-align: right;padding-top: 13px;">待办内容:</td>
											<td style="padding-top: 3px;">
												<script name="content" id="editor"  type="text/plain" style="width:100%;height:500px;" >${pd.content }</script>
											</td>
										</tr>
									
										<tr>
											<td style="text-align: center;" colspan="10">
												<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
												<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
											</td>
										</tr>
									</table>
									</div>
									<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green"></h4></div>
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
	</div>
	<!-- /.main-container -->
	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
		<!-- 日期框(带小时分钟) -->
	<script src="static/ace/js/date-time/moment.js"></script>
	<script src="static/ace/js/date-time/locales.js"></script>
	<!-- 编辑框-->
	<script type="text/javascript" charset="utf-8">window.UEDITOR_HOME_URL = "<%=path%>/plugins/ueditor/";</script>
	<script type="text/javascript" charset="utf-8" src="plugins/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="plugins/ueditor/ueditor.all.js"></script>
	<!-- 编辑框-->
	<!--引入属于此页面的js -->
	<script type="text/javascript" src="static/js/myjs/fhsms.js"></script>
		<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>

	<script src="static/ace/js/date-time/bootstrap-datetimepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>

	<script>
	
		$(function() {
			//日期框(带时间)
			$('.form-control').datetimepicker().next().on(ace.click_event, function(){
				$(this).prev().focus();
			});
		});
		
	
		function save(){
			/* if($("#publisher").val()==""){
				$("#publisher").tips({
					side:3,
		            msg:'请输入发布人',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#publisher").focus();
				return false;
			}
		 */
			if($("#title").val()==""){
				$("#title").tips({
					side:3,
		            msg:'请输入标题',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#title").focus();
				return false;
			}
			if($("#pub_time").val()==""){
				$("#pub_time").tips({
					side:3,
		            msg:'请选择发布日期',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#pub_time").focus();
				return false;
			}
			
		
			$("#todoForm").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
		$(function() {
			//下拉框
			if(!ace.vars['touch']) {
				$('.chosen-select').chosen({allow_single_deselect:true}); 
				$(window)
				.off('resize.chosen')
				.on('resize.chosen', function() {
					$('.chosen-select').each(function() {
						 var $this = $(this);
						 $this.next().css({'width': $this.parent().width()});
					});
				}).trigger('resize.chosen');
				$(document).on('settings.ace.chosen', function(e, event_name, event_val) {
					if(event_name != 'sidebar_collapsed') return;
					$('.chosen-select').each(function() {
						 var $this = $(this);
						 $this.next().css({'width': $this.parent().width()});
					});
				});
				$('#chosen-multiple-style .btn').on('click', function(e){
					var target = $(this).find('input[type=radio]');
					var which = parseInt(target.val());
					if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
					 else $('#form-field-select-4').removeClass('tag-input-style');
				});
			}
		});
		
		
	</script>
</body>
</html>