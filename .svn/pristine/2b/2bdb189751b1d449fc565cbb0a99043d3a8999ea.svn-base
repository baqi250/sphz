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
								<form action="knowledge/${msg }.do" name="knowledgeForm" id="knowledgeForm" method="post" enctype="multipart/form-data">
									<input type="hidden" name="knowledge_id" id="knowledge_id" value="${pd.knowledge_id }"/>
									<input type="hidden" name="dictionaries_id1" id="dictionaries_id1" value="${pd.dictionaries_id }"/>
									
									<div id="zhongxin" style="padding-top: 13px;">
									<table id="table_report" class="table table-striped table-bordered table-hover">
										<tr>
											<td style="width:90px;text-align: right;padding-top: 13px;">板块:</td>
											<%-- <td><input type="text" name="notice_forum" id="notice_forum" value="${pd.notice_forum }" maxlength="32" placeholder="这里输入板块" title="板块"  style="width:98%;"/></td> --%>
										
											<td><select class="chosen-select form-control"
											name="dictionaries_id" id="dictionaries_id" data-placeholder="请选择知识库板块" onchange="change(this.value)" 
											style="vertical-align:top;width:100%;">
												<c:forEach items="${knowledge_forumList}" var="item">
													<option value="${item.DICTIONARIES_ID }"
													<c:if test="${item.DICTIONARIES_ID == pd.dictionaries_id }">selected</c:if>>${item.NAME }</option>
												</c:forEach>
											</select></td>
										</tr>
										
										<tr>
											<td style="width:90px;text-align: right;padding-top: 13px;">标题:</td>
											<td><input type="text" name="title" id="title" value="${pd.title }" maxlength="100" placeholder="这里输入标题" title="标题" style="width:100%;"/></td>
										</tr>
										<tr>
											<td style="width:90px;text-align: right;padding-top: 13px;">发布日期:</td>
											<td>
												<div class="input-group bootstrap-timepicker">
												<input readonly="readonly" class="form-control" type="text" name="upload_date" id="upload_date" value="${pd.upload_date}" maxlength="100" placeholder="这里输入发布日期" title="发布日期:" style="width:100%;"/>
												<span class="input-group-addon"><i class="fa fa-clock-o bigger-110"></i></span>
												</div>
											</td>
										</tr>
										<tr id="sptr" style="display:none;" >
											<td style="width:90px;text-align: right;padding-top: 13px;">视频地址:</td>
											<td><input type="text" name="file_url" id="file_url" value="${pd.file_url }" maxlength="100" placeholder="这里输入视频地址" title="视频地址" style="width:100%;"/></td>
										</tr>
										
										<tr id="wjtr">
											<td style="width:90px;text-align: right;padding-top: 13px;">文件:</td>
											<td><input type="file" name="file" id="file"  accept="application/pdf, application/zip"  style="width:100%;"/></td>
										</tr>
										
									
										
									<!-- 	<tr id="uploadPannel">
											<td style="width:90px;text-align: right;padding-top: 13px;">附件:</td>
											<td style="padding-top: 3px;">
											    <div id="uploader" style="width:100%;">
									                <div class="queueList">
									                    <div id="dndArea" class="placeholder">
									                        <div id="filePicker"></div>
									                        <p>或将文件拖到这里</p>
									                    </div>
									                </div>
									                <div class="statusBar" style="display:none;">
									                    <div class="progress">
									                        <span class="text">0%</span>
									                        <span class="percentage"></span>
									                    </div><div class="info"></div>
									                    <div class="btns">
									                        <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
									                    </div>
									                </div>
								            	</div>
												
											</td>
										</tr> -->
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

	
	<!-- 编辑框-->
	<!--引入属于此页面的js -->
	<script type="text/javascript" src="static/js/myjs/fhsms.js"></script>
		<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
			<!-- 日期框(带小时分钟) -->
	<script src="static/ace/js/date-time/moment.js"></script>
	<script src="static/ace/js/date-time/locales.js"></script>
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
		console.log("${msg }");
		console.log("${pd.dictionaries_id }");
		if("${msg }"=="edit"){
	
			$("#dictionaries_id").attr("disabled","true");
			
			if("${pd.dictionaries_id }"=="b0904d76a9cf49b1bd10eb13e7f606ce"){
				$("#wjtr").css("display","none");
				$("#sptr").css("display","");
			}else{
				$("#wjtr").css("display","");
				$("#sptr").css("display","none");
			}
		}
		
		function save(){
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
			if($("#upload_date").val()==""){
				$("#upload_date").tips({
					side:3,
		            msg:'请选择发布日期',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#upload_date").focus();
				return false;
			}
		
			
			$("#knowledgeForm").submit();
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
		
		function del(id){
			if(confirm("确定要删除?")){ 
				var url = "<%=basePath%>knowledge/deleteFile.do?file_id="+id.id+"&tm="+new Date().getTime();
				$.get(url,function(data){
					$("#"+id.id).remove();
				});
			}
		}
		
		
		function change(value){
			if(value=="b0904d76a9cf49b1bd10eb13e7f606ce"){
				$("#wjtr").css("display","none");
				$("#sptr").css("display","");
			}else{
				$("#wjtr").css("display","");
				$("#sptr").css("display","none");
			}
		}
	</script>
</body>
</html>