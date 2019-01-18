﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<!-- jsp文件头和头部 -->
<%@ include file="../system/index/top.jsp"%>
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
				
					<div class="page-header">
							<h1>
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									编辑文本数据树
								</small>
							</h1>
					</div><!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">

						<form  action="text/${MSG }.do" name="menuForm" id="menuForm" method="post" class="form-horizontal">
							<input type="hidden" name="ID" id="id" value="${pd.ID }"/>
							<input type="hidden" name="DATA_TYPE" id="DATA_TYPE"/>  <!-- 用来存储#DATA_TYPE值 -->
							<input type="hidden" name="ISBOTTOMNODE" id="ISBOTTOMNODE" value="${ISBOTTOMNODE }"/>
							<input type="hidden" name="PID" id="pid" value="${null == pd.PID ? ID:pd.PID}"/>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 上级 :</label>
								<div class="col-sm-9">
									<div style="padding-top:5px;">
										<div class="col-xs-4 label label-lg label-light arrowed-in arrowed-right">
											<b>${null == pds.DATA_TYPE ?'(无) 此项为顶级菜单':pds.DATA_TYPE}</b>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 数据名称 :</label>
								<div class="col-sm-9">
									<input type="text" name="NAME" id=name" value="${pd.NAME }" placeholder="这里输入数据名称" class="col-xs-10 col-sm-5" />
								</div>
							</div>
							<c:if test="${null == pds.DATA_TYPE}">
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 数据类型 :</label>
									<div class="col-sm-9">
										<input type="text" name="DATA_TYPE" id="DATA_TYPE" value="${pd.DATA_TYPE }" placeholder="这里输入数据类型" class="col-xs-10 col-sm-5" />
									</div>
								</div>
							</c:if>						
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 数据来源 :</label>
								<div class="col-sm-9">
									<input type="text" name="DATA_SOURCE" id="dataSource" value="${pd.DATA_SOURCE }" placeholder="这里输入数据来源" class="col-xs-10 col-sm-5" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 印发单位 :</label>
								<div class="col-sm-9">
									<input type="text" name="COMPANY" id="company" value="${pd.COMPANY }" placeholder="这里输入印发单位" class="col-xs-10 col-sm-5" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 入库时间 :</label>
								<div class="col-sm-9">
									<input class="col-xs-10 col-sm-5 date-picker" name="IN_TIME" id="inTime" value="${pd.IN_TIME }" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="入库时间" title="入库时间" />
									<%-- <input type="text" name="IN_TIME" id="inTime" value="${pd.IN_TIME }" placeholder="这里输入入库时间" class="col-xs-10 col-sm-5" /> --%>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 印发时间 :</label>
								<div class="col-sm-9">
									<input class="col-xs-10 col-sm-5 date-picker" name="PRINT_TIME" id="printTime" value="${pd.PRINT_TIME }" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="印发时间" title="印发时间" />
									<%-- <input type="text" name="PRINT_TIME" id="printTime" value="${pd.PRINT_TIME }" placeholder="这里输入印发时间" class="col-xs-10 col-sm-5" /> --%>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> FTP链接  :</label>
								<div class="col-sm-9">
									<c:if test="${null != pds.NAME}">
									<input type="text" name="FTP_URL" id="FTP_URL" value="${pd.ftp_url }" placeholder="这里输入FTP链接" class="col-xs-10 col-sm-5" />
									</c:if>
									<c:if test="${null == pds.NAME}">
									<input type="text" name="FTP_URL" id="FTP_URL" value="" readonly="readonly" placeholder="顶级菜单禁止输入" class="col-xs-10 col-sm-5" />
									</c:if>
								</div>
							</div>
							<%-- <div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 是否最下层节点 : </label>
								<div class="col-sm-9" id="bottom">
									<label style="float:left;padding-left: 8px;padding-top:7px;">
										<input name="form-field-radio2" type="radio" class="ace" id="form-field-radio3" <c:if test="${pd.ISBOTTOMNODE == 0 }">checked="checked"</c:if> onclick="setType('1',0);"/>
										<span class="lbl"> 是</span>
									</label>
									<label style="float:left;padding-left: 5px;padding-top:7px;">
										<input name="form-field-radio2" type="radio" class="ace" id="form-field-radio4" <c:if test="${pd.ISBOTTOMNODE == 1 }">checked="checked"</c:if> onclick="setType('1',1);"/>
										<span class="lbl"> 否</span>
									</label>
								</div>
							</div>	 --%>						
							
							<div class="clearfix form-actions">
								<div class="col-md-offset-3 col-md-9">
									<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
									<a class="btn btn-mini btn-danger" onclick="goback('${ID}');">取消</a>
								</div>
							</div>
							<div class="hr hr-18 dotted hr-double"></div>
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
		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>

	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../system/index/foot.jsp"%>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		$(top.hangge());
		
		//返回
		function goback(ID){
			top.jzts();
			window.location.href="<%=basePath%>/text/list.do?ID="+ID;
		}
		
		//保存
		function save(){
			if($("#name").val()==""){
				$("#name").tips({
					side:3,
		            msg:'请输入数据名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#name").focus();
				return false;
			}		
			/* if($("#ISBOTTOMNODE").val()==""){
				$("#bottom").tips({
					side:3,
		            msg:'请选择是否最下层节点',
		            bg:'#AE81FF',
		            time:2
		        });
			} */
			console.log("${pds.DATA_TYPE }");
			debugger;
			if($("#DATA_TYPE").val()==""){
				$("#DATA_TYPE").val("${pds.DATA_TYPE }");
				//$("#TYPE").val();
			}
			$("#menuForm").submit();
		}
		
		//设置是否是最下层节点
		function setType(type,value){
			if(type == '1'){
				$("#ISBOTTOMNODE").val(value);
			}else{
				
			}
		}
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});			
		});
	</script>


</body>
</html>