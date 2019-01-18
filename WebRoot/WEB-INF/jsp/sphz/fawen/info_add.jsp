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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>蓝谷规划信息平台</title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link rel="stylesheet" href="plugins/xha/plugins/bootstrap/dist/css/bootstrap.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="plugins/xha/plugins/font-awesome/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="plugins/xha/plugins/Ionicons/css/ionicons.min.css">
<!-- bootstrap datepicker -->
<link rel="stylesheet" href="plugins/xha/plugins/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
<!-- iCheck for checkboxes and radio inputs -->
<link rel="stylesheet" href="plugins/xha/plugins/iCheck/all.css">
<!-- Select2 -->
<link rel="stylesheet" href="plugins/xha/plugins/select2/dist/css/select2.min.css">
<link rel="stylesheet" href="plugins/xha/plugins/select2/totree/select2totree.css">
<!-- Theme style -->
<link rel="stylesheet" href="plugins/xha/dist/css/AdminLTE.css">
<!-- AdminLTE Skins. 色调更改 -->
<link rel="stylesheet" href="plugins/xha/dist/css/skins/skin-blue.min.css">

<!-- Custom CSS -->
<link rel="stylesheet" href="plugins/xha/dist/css/pages/index.css">
<link rel="stylesheet" href="plugins/xha/dist/css/pages/infoAdd.css">
<link rel="stylesheet" href="plugins/xha/dist/css/pages/project_statistics.css">
<link rel="stylesheet" href="plugins/xha/plugins/layer/theme/default/layer.css">

<!-- 树形下拉框start -->

  <script src="plugins/xha/plugins/jquery/dist/jquery.min.js"></script>
 <!--  <script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
  	<script type="text/javascript" src="plugins/selectZtree/selectTree.js"></script>
	<script type="text/javascript" src="plugins/selectZtree/framework.js"></script>
	<link rel="stylesheet" type="text/css" href="plugins/selectZtree/import_fh.css"/>
	<script type="text/javascript" src="plugins/selectZtree/ztree/ztree.js"></script>
	<link type="text/css" rel="stylesheet" href="plugins/selectZtree/ztree/ztree.css"></link> -->
   
<!-- 树形下拉框end -->

</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- <div id="container"> -->
	   <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper1">
        
        
            <!-- Content Header (Page header) -->
    <!--         <section class="content-header">
               
            </section> -->

            <!-- Main content -->
            <section class="content container-fluid">
                <!-- 申报类型 -->
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-bjjdlx box-title text-blue box-select-title">报建阶段类型</h3>

                        <div class="box-select">
                            <select id="FWLXID" class="form-control select2" onchange="changeStage()" style="width: 100%;">
                            	<option selected="selected" value="选址、条件（建筑工程）">选址、条件（建筑工程）</option>
                                <option value="选址、条件（市政道桥）">选址、条件（市政道桥）</option>
                                <option value="选址、条件（市政管线）">选址、条件（市政管线）</option>
                                <option value="用地许可">用地许可</option>
                                <option value="建设许可（建筑工程）">建设许可（建筑工程）</option>
                                <option value="建设许可（市政道桥）">建设许可（市政道桥）</option>
                                <option value="建设许可（市政管线）">建设许可（市政管线）</option>
                                <option value="竣工（建筑工程）">竣工（建筑工程）</option>
                                <option value="竣工（市政道桥）">竣工（市政道桥）</option>
                                <option value="竣工（市政管线）">竣工（市政管线）</option>
                                <option value="其它">其它</option>
                            </select>
                        </div>
                        <div style="display:inline-block;margin-left:10px;">
                        	<button type="button" id="save" class="btn-save btn btn-block btn-primary btn-sm" data-widget=""><i class="fa fa-check"></i> 保存</button>
                        </div>
                    </div>
                </div>
				<form role="form" action="sphz/fawen/save.do" name="editForm" id="editForm" method="post" enctype="multipart/form-data" autocomplete="off">
				<input type="hidden" name="XMWYM" id="XMWYM" value="${pd.XMWYM}" ><!-- 用来记录属于哪一个项目 -->
				<input type="hidden" name="WHWYM" id="WHWYM" value="${pd.WHWYM }" />
				<input type="hidden" name="LAND_USE" id="LAND_USE" value="${pd.LAND_USE }" />
				<input type="hidden" name="LAND_USE_NAME" id="LAND_USE_NAME" value="${pd.LAND_USE_NAME }" />
				<input type="hidden" name="BSNUM" id="BSNUM" value="${pd.BSNUM }" />
				<input type="hidden" name="FWLXID" id="FWLXID_SELECT" value="${pd.FWLXID }" />
				<input type="hidden" name="JZWSYXZ" id="JZWSYXZ_SELECT" value="${pd.JZWSYXZ }" />
				<input type="hidden" name="XMSD" id="XMSD_SELECT" value="${pd.XMSD }" />
                     <!-- 报建信息 -->
					<div class="box box-warning">
				       <div class="box-header with-border bjjd">
				        	<div class="box-tools pull-left">
						        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
				    		</div>
				           <h3 class="box-title text-yellow">报建信息</h3>
				          
						</div>
					<div class="box-body">
						<table class="table table-condensed table-child">
							<tr id="project_gl_tr">
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">关联项目</h6>
								</td>
								<td colspan="3">
									<!--  搜索框 -->
								    <form class="search-form box-mutiselect"><input type="text" id="project_gl_name" name="project_gl_name" value="${pd.project_gl_name }" class="search-form-control form-control" placeholder="点击跳转到项目选择页面进行选择"></form>
								  	<!--    搜索结果框 -->
								    <div class="search-result text-dark" id="search_resut_div">
								        <ul class="search-result-contain"></ul>
								    </div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">申请事项</h6>
								</td>
								<td colspan="3">
									<div class="form-group">
										<input type="text" id="ITEM_NAME" name="ITEM_NAME" value="${pd.ITEM_NAME }" class="form-control">
									</div>	
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">申请单位</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="APPLICANT" name="APPLICANT" value="${pd.APPLICANT }" class="form-control">
									</div>	
								</td>
								
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">统一社会信用代码</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="TYSHXYDM" name="TYSHXYDM" value="${pd.TYSHXYDM }" class="form-control" placeholder="或组织机构代码">
									</div>	
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">申请时间</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="SUBMIT_TIME" name="SUBMIT_TIME" value="${pd.SUBMIT_TIME }" class="form-control">
									</div>	
								</td>
								
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">项目类型</h6>
								</td>
								<td>
									<div class="form-group">
										 <div class="box-select">
											 <select id=XMLX name="XMLX" class="form-control select2" style="width: 100%;">
												 <option selected="selected" value=""></option>
												 <c:forEach items="${xmlxList}" var="item">
													 <option value="${item.NAME }"
													 	<c:if test="${item.NAME == pd.XMLX }">selected</c:if>>${item.NAME }
													 </option>
												 </c:forEach>
											 </select>
										</div>
									</div>	
								</td>
							</tr>
							
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">委托代理人</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="AGENT_PERSON" name="AGENT_PERSON" value="${pd.AGENT_PERSON }" class="form-control">
									</div>	
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">联系电话</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="AGENT_PHONE" name="AGENT_PHONE" value="${pd.AGENT_PHONE }" class="form-control">
									</div>	
								</td>
							</tr>
							
							
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">项目名称</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="PROJECT_NAME" name="PROJECT_NAME" value="${pd.PROJECT_NAME }" class="form-control">
									</div>	
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">项目编码</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="PROJECT_CODE" name="PROJECT_CODE" value="${pd.PROJECT_CODE }" class="form-control">
									</div>	
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">项目地址</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="LOCATION" name="LOCATION" value="${pd.LOCATION }" class="form-control">
									</div>	
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">项目属地</h6>
								</td>
								<td>
									<div class="form-group">
										 <div class="box-select">
											 <select id="XMSD" multiple="multiple" class="form-control select2" style="width: 100%;">
												 <option selected="selected" value=""></option>
												 <c:forEach items="${xmsdList}" var="item">
													 <option value="${item.NAME }"
													 	<c:if test="${item.NAME == pd.XMSD }">selected</c:if>>${item.NAME }
													 </option>
												 </c:forEach>
											 </select>
										</div>
									</div>	
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">建设类型</h6>
								</td>
								<td>
									<div class="form-group">
										 <div class="box-select">
											 <select id=JSLX name="JSLX" class="form-control select2" style="width: 100%;">
												 <option selected="selected" value=""></option>
												 <c:forEach items="${jslxList}" var="item">
													 <option value="${item.NAME }"
													 	<c:if test="${item.NAME == pd.JSLX }">selected</c:if>>${item.NAME }
													 </option>
												 </c:forEach>
											 </select>
										</div>
									</div>	
								</td>
								
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">是否重点项目</h6>
								</td>
								<td>
									<div class="form-group">
										 <div class="box-select">
											 <select id=IS_IMPORTANT name="IS_IMPORTANT" class="form-control select2" style="width: 100%;">
												 <option selected="selected" value=""></option>
												 <c:forEach items="${isImportantList}" var="item">
													 <option value="${item.BIANMA }"
													 	<c:if test="${item.BIANMA == pd.IS_IMPORTANT }">selected</c:if>>${item.NAME }
													 </option>
												 </c:forEach>
											 </select>
										</div>
									</div>	
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">项目总投资</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="INVESTMENT" name="INVESTMENT"
											value="${pd.INVESTMENT }" class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">万元</span>
									</div>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">项目规模</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="SCALE" name="SCALE" value="${pd.SCALE }" class="form-control" placeholder="可输入文字">
									</div>	
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">设计（勘察）单位</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="SJDW" name="SJDW" value="${pd.SJDW }" class="form-control">
									</div>	
								</td>
								
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">项目红线</h6>
								</td>
								<td>
									<div class="form-group input-group input-group-sm">
                                        <input type="text" class="form-control" id="hx-upload-text" value="${pd.redFileName }" readonly="readonly">
                                        <input type="file" class="form-control" name="redFile" id="redFile" style="display: none;">
                                        <span class="input-group-btn">
                                            <button type="button" class="btn btn-info btn-flat" id="hx-upload-btn"><i class="fa fa-upload"></i></button>
                                        </span>
                                    </div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">项目内容</h6>
								</td>
								<td colspan="3">
									<div class="form-group">
										<textarea id="PROJECT_CONTENT" name="PROJECT_CONTENT" class="form-control input-textarea">${pd.PROJECT_CONTENT }</textarea>
									</div>	
								</td>
							</tr>
						</table>
					</div>
				</div>
					
					<!-- 发文信息 -->
					<div class="box box-danger">
					       <div class="box-header with-border">
					           <h3 class="box-title text-red">发文信息</h3>
					           <div class="box-tools pull-right">
					        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
					    </div>
					</div>
					<div class="box-body">
						<table class="table table-condensed table-child">
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">发文类型</h6>
								</td>
								<td>
									<div class="form-group">
										<div class="box-select">
											<select id=FWLX name="FWLX" class="form-control select2"
												style="width: 100%;">
												<option selected="selected" value=""></option>
												<c:forEach items="${fwlxList}" var="item">
													<option value="${item.NAME }"
														<c:if test="${item.NAME == pd.FWLX }">selected</c:if>>${item.NAME }</option>
												</c:forEach>
											</select>
										</div>
									</div>	
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">发文文号</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="FWWH" name="FWWH" value="${pd.FWWH }" class="form-control" placeholder="例如：建字第37xxxxxxxxxx号">
									</div>	
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">发文日期</h6>
								</td>
								<td>
									<div class="form-group">
										 <div class="input-group date">
											<div class="input-group-addon">
												<i class="fa fa-calendar"></i>
											</div>
											<input type="text" name="FZRQ" value="${pd.FZRQ }"
												class="form-control pull-right datepicker" id="FZRQ">
										</div>
									</div>	
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">经办部门</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="ORG_NAME" name="ORG_NAME" value="${pd.ORG_NAME }" class="form-control">
									</div>	
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">经办人</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="JBR" name="JBR" value="${pd.JBR }" class="form-control">
									</div>	
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">签批人</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="QPR" name="QPR" value="${pd.QPR }" class="form-control">
									</div>	
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">证书钢印号</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="ZSGYH" name="ZSGYH" value="${pd.ZSGYH }" class="form-control" placeholder="例如：GCxxxxxxxx">
									</div>	
								</td>
								
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">发文附件</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="button" class="form-control" onclick="showFiles();" value="查看附件">
									</div>	
								</td>
							</tr>
						</table>
					</div>
					</div>
					
				<!-- 指标信息（选址、条件（建筑工程））-->
				<div class="box box-success infoDiv" id=infoDiv1 style="display: block;">
					<div class="box-header with-border">
						<h3 class="box-title text-green">指标信息</h3>
						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool"
								data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>
						</div>
					</div>
					<div class="box-body">
						<table class="table table-condensed table-child">
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">是否混合用地</h6>
								</td>
								<td>
									<div class="form-group">
										<div class="box-select">
											<select id=SFHHYD name="SFHHYD" class="form-control select2 SFHHYD"
												style="width: 100%;">
												<option selected="selected" value=""></option>
												<c:forEach items="${sfhhydList}" var="item">
													<option value="${item.NAME }"
														<c:if test="${item.NAME == pd.SFHHYD }">selected</c:if>>${item.NAME }</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">混合用地比例</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="HHYDBL" name="HHYDBL"
											value="${pd.HHYDBL }" class="form-control HHYDBL" readonly="readonly">
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">规划用地性质</h6>
								</td>
								<td>
									<div class="form-group">
									  	 <select id="select-tree" class="my_select-tree" multiple="multiple" style="width:100%" >
                                            <option selected="selected" disabled="disabled" style="display: none" value=""></option>
                                        </select>
									</div>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">用地性质代码</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="GHYDXZDM" name="GHYDXZDM"
											value="${pd.GHYDXZDM }" class="form-control ghydxzdm" readonly="readonly">
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">用地面积</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="YDMJ" name="YDMJ" value="${pd.YDMJ }"
											class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">平方米</span>
									</div>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">容积率（≤）</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="RJL" name="RJL" value="${pd.RJL }"
											class="form-control" oninput="value=value.replace(/[^\-?\d.]/g,'')">
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">建筑密度（≤）</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="JZMD" name="JZMD" value="${pd.JZMD }"
											class="form-control" oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">%</span>
									</div>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">绿地率（≥）</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="LDL" name="LDL" value="${pd.LDL }"
											class="form-control" oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">%</span>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">停车位</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="TCW" name="TCW" value="${pd.TCW }"
											class="form-control">
									</div>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">控制高度</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="KZGD" name="KZGD" value="${pd.KZGD }"
											class="form-control">
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">海绵城市要求</h6>
								</td>
								<td colspan="3">
									<div class="form-group">
										 <textarea id="HMCSYQ" name="HMCSYQ" class="form-control input-textarea">${pd.HMCSYQ }</textarea>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">备注</h6>
								</td>
								<td colspan="3">
									<div class="form-group">
										<textarea id="BZ" name="BZ" class="form-control input-textarea">${pd.BZ }</textarea>
									</div>	
								</td>
							</tr>
						</table>
					</div>
				</div>
				
				<!-- 指标信息（选址、条件（市政道桥））-->
				<div class="box box-success infoDiv unDisDiv" id=infoDiv2>
					<div class="box-header with-border">
						<h3 class="box-title text-green">指标信息</h3>
						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool"
								data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>
						</div>
					</div>
					<div class="box-body">
						<table class="table table-condensed table-child">
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">道路性质</h6>
								</td>
								<td>
									<div class="form-group">
										<div class="box-select">
											<select id="DLXZ" name="DLXZ" class="form-control select2"
												style="width: 100%;">
												<option selected="selected" value=""></option>
												<c:forEach items="${dlxzList}" var="item">
													<option value="${item.NAME }"
														<c:if test="${item.NAME == pd.DLXZ }">selected</c:if>>${item.NAME }</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">道路长度</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="DLCD" name="DLCD" value="${pd.DLCD }" class="form-control"
										 oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">米</span>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">红线宽度</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="HXKD" name="HXKD" value="${pd.HXKD }" class="form-control"
										 oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">米</span>
									</div>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">断面形式</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="DMXS" name="DMXS" value="${pd.DMXS }" class="form-control">
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">设计标准</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="SJBZ" name="SJBZ" value="${pd.SJBZ }" class="form-control">
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">海绵城市要求</h6>
								</td>
								<td colspan="3">
									<div class="form-group">
										 <textarea id="HMCSYQ" name="HMCSYQ" class="form-control input-textarea">${pd.HMCSYQ }</textarea>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">备注</h6>
								</td>
								<td colspan="3">
									<div class="form-group">
										<textarea id="BZ" name="BZ" class="form-control input-textarea">${pd.BZ }</textarea>
									</div>	
								</td>
							</tr>
						</table>
					</div>
				</div>
				<!-- 指标信息（选址、条件（市政管线））-->
				<div class="box box-success infoDiv unDisDiv" id=infoDiv3>
					<div class="box-header with-border">
						<h3 class="box-title text-green">指标信息</h3>
						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool"
								data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>
						</div>
					</div>
					<div class="box-body">
						<table class="table table-condensed table-child">
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">管线类型</h6>
								</td>
								<td>
									<div class="form-group">
										<div class="box-select">
											<select id="GXLX" name="GXLX" class="form-control select2"
												style="width: 100%;">
												<option selected="selected" value=""></option>
												<c:forEach items="${gxlxList}" var="item">
													<option value="${item.NAME }"
														<c:if test="${item.NAME == pd.GXLX }">selected</c:if>>${item.NAME }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<%-- <div class="form-group">
										 <input type="text" id="GXLX" name="GXLX" value="${pd.GXLX }" class="form-control">
									</div> --%>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">管线长度</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="GXCD" name="GXCD" value="${pd.GXCD }" class="form-control" oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">千米</span>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">设计标准</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="SJBZ" name="SJBZ" value="${pd.SJBZ }" class="form-control">
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">海绵城市要求</h6>
								</td>
								<td colspan="3">
									<div class="form-group">
										<textarea id="HMCSYQ" name="HMCSYQ" class="form-control input-textarea">${pd.HMCSYQ }</textarea>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">备注</h6>
								</td>
								<td colspan="3">
									<div class="form-group">
										<textarea id="BZ" name="BZ" class="form-control input-textarea">${pd.BZ }</textarea>
									</div>	
								</td>
							</tr>
						</table>
					</div>
				</div>

				<!-- 指标信息（用地许可）-->
				<div class="box box-success infoDiv unDisDiv" id=infoDiv4>
					<div class="box-header with-border">
						<h3 class="box-title text-green">指标信息</h3>
						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool"
								data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>
						</div>
					</div>
					<div class="box-body">
						<table class="table table-condensed table-child">
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">是否混合用地</h6>
								</td>
								<td>
									<div class="form-group">
										<div class="box-select">
											<select id=SFHHYD name="SFHHYD" class="form-control select2 SFHHYD"
												style="width: 100%;">
												<option selected="selected" value=""></option>
												<c:forEach items="${sfhhydList}" var="item">
													<option value="${item.NAME }"
														<c:if test="${item.NAME == pd.SFHHYD }">selected</c:if>>${item.NAME }</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">混合用地比例</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="HHYDBL" name="HHYDBL"
											value="${pd.HHYDBL }" class="form-control HHYDBL" readonly="readonly">
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">规划用地性质</h6>
								</td>
								<td>
									<div class="form-group">
									 	<select id="select-tree2" class="my_select-tree" multiple="multiple" style="width:100%">
                                            <option selected="selected" disabled="disabled" style="display: none" value=""></option>
                                        </select>
									</div>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">用地性质代码</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="GHYDXZDM" name="GHYDXZDM"
											value="${pd.GHYDXZDM }" class="form-control ghydxzdm" readonly="readonly">
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">用地面积</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="YDMJ" name="YDMJ" value="${pd.YDMJ }"
											class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">平方米</span>
									</div>
								</td>
							
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">投资管理类型</h6>
								</td>
								<td>
									<div class="form-group">
										<div class="box-select">
											<select id=TZGLLX name="TZGLLX" class="form-control select2"
												style="width: 100%;">
												<option selected="selected" value=""></option>
												<c:forEach items="${tzgllxList}" var="item">
													<option value="${item.NAME }"
														<c:if test="${item.NAME == pd.TZGLLX }">selected</c:if>>${item.NAME }</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">土地使用类型</h6>
								</td>
								<td>
									<div class="form-group">
										<div class="box-select">
											<select id=TDSYLX name="TDSYLX" class="form-control select2"
												style="width: 100%;">
												<option selected="selected" value=""></option>
												<c:forEach items="${tdsylxList}" var="item">
													<option value="${item.NAME }"
														<c:if test="${item.NAME == pd.TDSYLX }">selected</c:if>>${item.NAME }</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">海绵城市要求</h6>
								</td>
								<td colspan="3">
									<div class="form-group">
										<textarea id="HMCSYQ" name="HMCSYQ" class="form-control input-textarea">${pd.HMCSYQ }</textarea>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">备注</h6>
								</td>
								<td colspan="3">
									<div class="form-group">
										<textarea id="BZ" name="BZ" class="form-control input-textarea">${pd.BZ }</textarea>
									</div>	
								</td>
							</tr>
						</table>
					</div>
				</div>

				<!-- 指标信息（建设工程规划许可（建筑））-->
				<div class="box box-success infoDiv unDisDiv" id=infoDiv5>
					<div class="box-header with-border">
						<h3 class="box-title text-green">指标信息</h3>
						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool"
								data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>
						</div>
					</div>
					<div class="box-body">
						<table class="table table-condensed table-child">
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">是否混合用地</h6>
								</td>
								<td>
									<div class="form-group">
										<div class="box-select">
											<select id=SFHHYD name="SFHHYD" class="form-control select2 SFHHYD"
												style="width: 100%;">
												<option selected="selected" value=""></option>
												<c:forEach items="${sfhhydList}" var="item">
													<option value="${item.NAME }"
														<c:if test="${item.NAME == pd.SFHHYD }">selected</c:if>>${item.NAME }</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">混合用地比例</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="HHYDBL" name="HHYDBL" 
											value="${pd.HHYDBL }" class="form-control HHYDBL" readonly="readonly">
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">规划用地性质</h6>
								</td>
								<td>
									<div class="form-group">
									 	<select id="select-tree3" class="my_select-tree" multiple="multiple" style="width:100%">
                                            <option selected="selected" disabled="disabled" style="display: none" value=""></option>
                                        </select>
									</div>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">用地性质代码</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="GHYDXZDM" name="GHYDXZDM"
											value="${pd.GHYDXZDM }" class="form-control ghydxzdm" readonly="readonly">
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">用地面积</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="YDMJ" name="YDMJ" value="${pd.YDMJ }"
											class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">平方米</span>
									</div>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">容积率</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="RJL" name="RJL" value="${pd.RJL }"
											class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')">
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">建筑密度</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="JZMD" name="JZMD" value="${pd.JZMD }"
											class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">%</span>
									</div>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">绿地率</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="LDL" name="LDL" value="${pd.LDL }"
											class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">%</span>
									</div>
								</td>
							</tr>
						
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">总建筑面积</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="ZJZMJ" name="ZJZMJ"
											value="${pd.ZJZMJ }" class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">平方米</span>
									</div>
								</td>
								
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">停车位</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="TCW" name="TCW" value="${pd.TCW }"
											class="form-control"
											oninput="value=value.replace(/[^\d]/g,'')"><span class="input-group-addon  addon-w">个</span>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">地上建筑面积</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="DSJZMJ" name="DSJZMJ"
											value="${pd.DSJZMJ }" class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">平方米</span>
									</div>
								</td>
								
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">地上停车位</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="DSTCW" name="DSTCW"
											value="${pd.DSTCW }" class="form-control"
											oninput="value=value.replace(/[^\d]/g,'')"><span class="input-group-addon  addon-w">个</span>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">地下建筑面积</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="DXJZMJ" name="DXJZMJ"
											value="${pd.DXJZMJ }" class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">平方米</span>
									</div>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">地下停车位</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="DXTCW" name="DXTCW"
											value="${pd.DXTCW }" class="form-control"
											oninput="value=value.replace(/[^\d]/g,'')"><span class="input-group-addon  addon-w">个</span>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">住宅建筑面积</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="ZZJZMJ" name="ZZJZMJ"
											value="${pd.ZZJZMJ }" class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">平方米</span>
									</div>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">办公建筑面积</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="BGJZMJ" name="BGJZMJ"
											value="${pd.BGJZMJ }" class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">平方米</span>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">配套建筑面积</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="PTJZMJ" name="PTJZMJ"
											value="${pd.PTJZMJ }" class="form-control">
									</div>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">建设栋数</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="JZDS" name="JZDS" value="${pd.JZDS }"
											class="form-control"
											oninput="value=value.replace(/[^\d]/g,'')"><span class="input-group-addon  addon-w">栋</span>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">户数</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="HS" name="HS" value="${pd.HS }"
											class="form-control"
											oninput="value=value.replace(/[^\d]/g,'')"><span class="input-group-addon  addon-w">户</span>
									</div>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">建筑物使用性质</h6>
								</td>
								<td>
									<div class="form-group">
										<div class="box-select">
											<select id=JZWSYXZ multiple="multiple"
												class="form-control select2" style="width: 100%;">
												<option selected="selected" value=""></option>
												<c:forEach items="${jzwsyxzList}" var="item">
													<option value="${item.NAME }"
														<c:if test="${item.NAME == pd.JZWSYXZ }">selected</c:if>>${item.NAME }</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">海绵城市要求</h6>
								</td>
								<td colspan="5">
									<div class="form-group">
										<textarea id="HMCSYQ" name="HMCSYQ"
											class="form-control input-textarea">${pd.HMCSYQ }</textarea>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">备注</h6>
								</td>
								<td colspan="3">
									<div class="form-group">
										<textarea id="BZ" name="BZ" class="form-control input-textarea">${pd.BZ }</textarea>
									</div>	
								</td>
							</tr>
						</table>
					</div>
				</div>
				<!-- 指标信息（建设许可（市政道桥））-->
				<div class="box box-success infoDiv unDisDiv" id=infoDiv6>
					<div class="box-header with-border">
						<h3 class="box-title text-green">指标信息</h3>
						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool"
								data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>
						</div>
					</div>
					<div class="box-body">
						<table class="table table-condensed table-child">
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">规划用地性质</h6>
								</td>
								<td>
									<div class="form-group">
									 	<select id="select-tree4" class="my_select-tree" multiple="multiple" style="width:100%">
                                            <option selected="selected" disabled="disabled" style="display: none" value=""></option>
                                        </select>
									</div>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">用地性质代码</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="GHYDXZDM" name="GHYDXZDM"
											value="${pd.GHYDXZDM }" class="form-control ghydxzdm" readonly="readonly">
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">用地面积</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="YDMJ" name="YDMJ" value="${pd.YDMJ }"
											class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">平方米</span>
									</div>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">道路总长度</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="DLZCD" name="DLZCD" value="${pd.DLZCD }"
											class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">米</span>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">道路宽度</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="DLKD" name="DLKD" value="${pd.DLKD }" class="form-control"
										 oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">米</span>
									</div>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">道路级别</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="DLJB" name="DLJB" value="${pd.DLJB }" class="form-control">
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">断面形式</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="DMXS" name="DMXS" value="${pd.DMXS }" class="form-control">
									</div>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">设计标准</h6>
								</td>
								<td>
									<div class="form-group">
										 <input type="text" id="SJBZ" name="SJBZ" value="${pd.SJBZ }" class="form-control">
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">折算建筑面积</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="ZSJZMJ" name="ZSJZMJ" value="${pd.ZSJZMJ }"
											class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">平方米</span>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">海绵城市要求</h6>
								</td>
								<td colspan="3">
									<div class="form-group">
										 <textarea id="HMCSYQ" name="HMCSYQ" class="form-control input-textarea">${pd.HMCSYQ }</textarea>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">备注</h6>
								</td>
								<td colspan="3">
									<div class="form-group">
										<textarea id="BZ" name="BZ" class="form-control input-textarea">${pd.BZ }</textarea>
									</div>	
								</td>
							</tr>
						</table>
					</div>
				</div>
				<!-- 指标信息（建设许可（市政管线））-->
				<div class="box box-success infoDiv unDisDiv" id=infoDiv7>
					<div class="box-header with-border">
						<h3 class="box-title text-green">指标信息</h3>
						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool"
								data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>
						</div>
					</div>
					<div class="box-body">
						<table class="table table-condensed table-child">
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">规划用地性质</h6>
								</td>
								<td>
									<div class="form-group">
									 	<select id="select-tree5" class="my_select-tree" multiple="multiple" style="width:100%">
                                            <option selected="selected" disabled="disabled" style="display: none" value=""></option>
                                        </select>
									</div>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">用地性质代码</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="GHYDXZDM" name="GHYDXZDM"
											value="${pd.GHYDXZDM }" class="form-control ghydxzdm" readonly="readonly">
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">用地面积</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="YDMJ" name="YDMJ" value="${pd.YDMJ }"
											class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">平方米</span>
									</div>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">管线类型</h6>
								</td>
								<td>
									<div class="form-group">
										<div class="box-select">
											<select id="GXLX" name="GXLX" class="form-control select2"
												style="width: 100%;">
												<option selected="selected" value=""></option>
												<c:forEach items="${gxlxList}" var="item">
													<option value="${item.NAME }"
														<c:if test="${item.NAME == pd.GXLX }">selected</c:if>>${item.NAME }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<%-- <div class="form-group">
										<input type="text" id="GXLX" name="GXLX" value="${pd.GXLX }" class="form-control">
									</div> --%>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">管线总长度</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="GXZCD" name="GXZCD" value="${pd.GXZCD }"
											class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">千米</span>
									</div>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">折算建筑面积</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="ZSJZMJ" name="ZSJZMJ" value="${pd.ZSJZMJ }"
											class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">平方米</span>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">海绵城市要求</h6>
								</td>
								<td colspan="3">
									<div class="form-group">
										 <textarea id="HMCSYQ" name="HMCSYQ" class="form-control input-textarea">${pd.HMCSYQ }</textarea>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">备注</h6>
								</td>
								<td colspan="3">
									<div class="form-group">
										<textarea id="BZ" name="BZ" class="form-control input-textarea">${pd.BZ }</textarea>
									</div>	
								</td>
							</tr>
						</table>
					</div>
				</div>
				<!-- 指标信息（竣工（建筑工程））-->
				<div class="box box-success infoDiv unDisDiv" id=infoDiv8>
					<div class="box-header with-border">
						<h3 class="box-title text-green">指标信息</h3>
						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool"
								data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>
						</div>
					</div>
					<div class="box-body">
						<table class="table table-condensed table-child">
							
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">用地面积</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="YDMJ" name="YDMJ" value="${pd.YDMJ }"
											class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">平方米</span>
									</div>
								</td>
								
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">总建筑面积</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="ZJZMJ" name="ZJZMJ" value="${pd.ZJZMJ }"
											class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">平方米</span>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">建筑占地面积</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="JZZDMJ" name="JZZDMJ" value="${pd.JZZDMJ }"
											class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">平方米</span>
									</div>
								</td>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">地上建筑面积</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="DSJZMJ" name="DSJZMJ" value="${pd.DSJZMJ }"
											class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">平方米</span>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">绿化面积</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="LHMJ" name="LHMJ" value="${pd.LHMJ }"
											class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">平方米</span>
									</div>
								</td>
								
								
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">地下建筑面积</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="DXJZMJ" name="DXJZMJ" value="${pd.DXJZMJ }"
											class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">平方米</span>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">建筑高度</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="JZGD" name="JZGD" value="${pd.JZGD }"
											class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">米</span>
									</div>
								</td>
								
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">层数</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="CS" name="CS" value="${pd.CS }"
											class="form-control"
											oninput="value=value.replace(/[^\d]/g,'')"><span class="input-group-addon  addon-w">层</span>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">备注</h6>
								</td>
								<td colspan="3">
									<div class="form-group">
										<textarea id="BZ" name="BZ" class="form-control input-textarea">${pd.BZ }</textarea>
									</div>	
								</td>
							</tr>
						</table>
					</div>
				</div>
				<!-- 指标信息（竣工（市政道桥））-->
				<div class="box box-success infoDiv unDisDiv" id=infoDiv9>
					<div class="box-header with-border">
						<h3 class="box-title text-green">指标信息</h3>
						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool"
								data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>
						</div>
					</div>
					<div class="box-body">
						<table class="table table-condensed table-child">
							
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">用地面积</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="YDMJ" name="YDMJ" value="${pd.YDMJ }"
											class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">平方米</span>
									</div>
								</td>
								
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">道路总长度</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="DLZCD" name="DLZCD" value="${pd.DLZCD }"
											class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">米</span>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">道路宽度</h6>
								</td>
								<td>
									<div class="form-group input-group">
										 <input type="text" id="DLKD" name="DLKD" value="${pd.DLKD }" class="form-control"
										  oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">米</span>
									</div>
								</td>
								
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">断面形式</h6>
								</td>
								<td>
									<div class="form-group">
										 <input type="text" id="DMXS" name="DMXS" value="${pd.DMXS }" class="form-control">
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">折算建筑面积</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="ZSJZMJ" name="ZSJZMJ" value="${pd.ZSJZMJ }"
											class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">平方米</span>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">备注</h6>
								</td>
								<td colspan="3">
									<div class="form-group">
										<textarea id="BZ" name="BZ" class="form-control input-textarea">${pd.BZ }</textarea>
									</div>	
								</td>
							</tr>
						</table>
					</div>
				</div>
				<!-- 指标信息（竣工（市政管线））-->
				<div class="box box-success infoDiv unDisDiv" id=infoDiv10>
					<div class="box-header with-border">
						<h3 class="box-title text-green">指标信息</h3>
						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool"
								data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>
						</div>
					</div>
					<div class="box-body">
						<table class="table table-condensed table-child">
							
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">用地面积</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="YDMJ" name="YDMJ" value="${pd.YDMJ }"
											class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">平方米</span>
									</div>
								</td>
								
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">管线类型</h6>
								</td>
								<td>
									<div class="form-group">
										<div class="box-select">
											<select id="GXLX" name="GXLX" class="form-control select2"
												style="width: 100%;">
												<option selected="selected" value=""></option>
												<c:forEach items="${gxlxList}" var="item">
													<option value="${item.NAME }"
														<c:if test="${item.NAME == pd.GXLX }">selected</c:if>>${item.NAME }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<%-- <div class="form-group">
										 <input type="text" id="GXLX" name="GXLX" value="${pd.GXLX }" class="form-control">
									</div> --%>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">管线总长度</h6>
								</td>
								<td>
									<div class="form-group input-group">
										<input type="text" id="GXZCD" name="GXZCD" value="${pd.GXZCD }"
											class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">千米</span>
									</div>
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">备注</h6>
								</td>
								<td colspan="3">
									<div class="form-group">
										<textarea id="BZ" name="BZ" class="form-control input-textarea">${pd.BZ }</textarea>
									</div>	
								</td>
							</tr>
						</table>
					</div>
				</div>
				<!-- 指标信息（其它）-->
				<div class="box box-success infoDiv unDisDiv" id=infoDiv11>
					<div class="box-header with-border">
						<h3 class="box-title text-green">指标信息</h3>
						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool"
								data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>
						</div>
					</div>
					<div class="box-body">
						<table class="table table-condensed table-child">
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">审批内容</h6>
								</td>
								<td class="column_spnr">
									<div class="form-group">
										<textarea id="SPNR" name="SPNR" class="form-control input-textarea">${pd.SPNR }</textarea>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			
			</form>   
            </section>
       
        </div>
        <!-- /.content-wrapper -->
		
    </div>
    <!-- ./wrapper -->
        <!-- jQuery 3 -->
  
    <script src="plugins/xha/plugins/jquery/dist/jquery-form.js"></script>
    <script src="static/js/jquery.tips.js"></script>
    
    <!-- Bootstrap 3.3.7 -->
    <script src="plugins/xha/plugins/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- Select2 -->
    <script src="plugins/xha/plugins/select2/dist/js/select2.full.min.js"></script>
    <script src="plugins/xha/plugins/select2/dist/js/i18n/zh-CN.js"></script>
    <script src="plugins/xha/plugins/select2/totree/select2totree.js"></script>
    <!-- InputMask -->
    <script src="plugins/xha/plugins/inputmask/dist/inputmask/inputmask.js"></script>
    <script src="plugins/xha/plugins/inputmask/dist/inputmask/inputmask.extensions.js"></script>
    <script src="plugins/xha/plugins/inputmask/dist/inputmask/inputmask.numeric.extensions.js"></script>
    <script src="plugins/xha/plugins/inputmask/dist/inputmask/inputmask.date.extensions.js"></script>
    <script src="plugins/xha/plugins/inputmask/dist/inputmask/inputmask.phone.extensions.js"></script>
    <script src="plugins/xha/plugins/inputmask/dist/inputmask/jquery.inputmask.js"></script>
    <script src="plugins/xha/plugins/inputmask/dist/inputmask/phone-codes/phone.js"></script>
    <script src="plugins/xha/plugins/inputmask/dist/inputmask/phone-codes/phone-be.js"></script>
    <!-- bootstrap datepicker -->
    <script src="plugins/xha/plugins/bootstrap-datepicker/dist/js/bootstrap-datepicker.js"></script>
    <!-- SlimScroll -->
    <script src="plugins/xha/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
    <!-- iCheck 1.0.1 -->
    <script src="plugins/xha/plugins/iCheck/icheck.min.js"></script>
    <!-- FastClick -->
    <script src="plugins/xha/plugins/fastclick/lib/fastclick.js"></script>
    <!-- AdminLTE App -->
	<script src="plugins/xha/dist/js/adminlte.min.js"></script>

    <script src="plugins/xha/dist/js/pages/index_g.js"></script>
    <script src="plugins/xha/dist/js/pages/index_w.js"></script>
    	
    <script src="plugins/xha/plugins/layer/layer.js"></script>
   
    
    <script type="text/javascript">
    
    	
		$('.datepicker').datepicker({autoclose: true,todayHighlight: true});//今天高亮显示			
    	
    	//报建类型切换更换发证信息内容
    	function changeStage(){
   			var stageIndex= $("#FWLXID").get(0).selectedIndex;//获取下拉框选中项的索引
   			var infoDivs=$(".infoDiv");
   			for(var i=0;i<infoDivs.length;i++){
   				infoDivs[i].style.display="none";
   				$(".infoDiv:eq("+i+")").addClass("unDisDiv");
   			}
   			infoDivs[stageIndex].style.display="block";
   			$(".infoDiv:eq("+(stageIndex)+")").removeClass("unDisDiv");
   		}
   	
      //项目红线文件初始化
	  	$('#hx-upload-btn').click(function() {
	        $('#redFile').click();
	    });
	    $('#hx-upload-text').click(function() {
	        $('#redFile').click();
	    });
	    $('#redFile').on('change', function(e) {
	        // 上传红线
	        var hxUrl = $("#redFile").val(); // 路径
	        var hxName = e.currentTarget.files[0].name; // 文件名
	        $("#hx-upload-text").val(hxName);
	    });
	    
	    $("#JZWSYXZ").val('请选择建筑物使用性质').trigger("change");
		$("#XMSD").val('请选择项目属地').trigger("change"); 
	    var selectTrees= $(".my_select-tree");
	   	selectTrees.each(function(){
	   		$(this).val('请选择用地性质').trigger("change");
		});
	    
	   
        $.ajax({//土地利用类型树状下拉选择框
				type: "POST",
				url: 'dictionaries/getTreeSelectData.do',
		    	data: {DICTIONARIES_ID:'c7fa42b047ef49bca1100d3fc636f533',tm:new Date().getTime()},
				dataType:'json',
				cache: false,
				success: function(data){
					var dicTreeSelectListData=data.dicTreeSelectList;
					var selectTrees= $(".my_select-tree");
				   	selectTrees.each(function(){
				   		$(this).select2ToTree({ treeData: { dataArr: dicTreeSelectListData }, maximumSelectionLength: 100, minimumResultsForSearch: -1 });
						$(this).on("select2:select", function() {
					   	 	$("#LAND_USE").val($(this).val());
						});
					});
				},
				error:function(data){
					console.log(data);
					alert(error);
				}
			});
			
		//是否混合用地改变选项触发事件
		$('.SFHHYD').change(function(){ 
			var sfhhyd=$(this).val();
			if(sfhhyd=="是"){
				$('.HHYDBL').removeAttr("readonly");
			}else{
				$('.HHYDBL').attr("readonly","readonly");
			}
		}); 	
		//规划用地性质改变自动更改用地规划性质代码	
		$('.my_select-tree').change(function(){ 
			var ids=$(this).val().join(',');
			$.ajax({
		     	type: "POST",
				url: 'dictionaries/getNameEnByIds.do',
		    	data: {ids:ids,tm:new Date().getTime()},
				dataType:'json',
				cache: false,
				success: function(data){
					console.log(data);
					var names='';
					var namesCN='';
					if(null!=data&&data.result=='success'){
						var nameEns = data.nameEns;
						for(var i=0;i<nameEns.length;i++){
							if(names==''){
								names+=nameEns[i].name_EN;
								namesCN+=nameEns[i].name;
							}else{
								names+=','+nameEns[i].name_EN;
								namesCN+=','+nameEns[i].name;
							}
							
						}
					}
					 var ghydxzdms= $(".ghydxzdm");
			   		 ghydxzdms.each(function(){
				   		$(this).val(names);
					 });
					//$("#GHYDXZDM").val(names);
					$("#LAND_USE_NAME").val(namesCN);
				},
		     	error:function(data){
					console.log(data);
					alert(error);
				}
		     }); 
		}); 
		
	
		
		$("#project_gl_name").on("click",function(){
			//alert(111);
			var width=$(window).width();
		    var height=$(window).height();
			var indext = layer.open({
		         type: 2,
		         title: '请选择项目',
		         shadeClose: true,
		         shade: false,
		         maxmin: true, //开启最大化最小化按钮
		         area: [width+'px', height+'px'],
		         content: 'sphz/project/goProjSelect?XMWYM='+$("#XMWYM").val()+"&PROJECT_NAME="+$("#project_gl_name").val(),
		     });
		     //layer.full(indext);//全屏
		})

		//保存按钮点击事件
   		$("#save").on("click",function(){
			//提交之前需要校验表单，非选址条件阶段 或非其它阶段的需要选择关联项目，以保证发文能够按项目串联起来
			var FWLXID=$("#FWLXID").val();
			$("#FWLXID_SELECT").val(FWLXID);
			/* if(FWLXID.indexOf('选址、条件')>-1||FWLXID.indexOf('其它')>-1){
				//什么都不做
			}else{ */
			//关联项目
			if($("#project_gl_name").val()==""){
				layer.tips("请选择关联项目","#project_gl_name",{
   					tips:[1,'#3595CC'],
   					time:3000
   				});
   				return false;
			}
			/* } */
			
			if($("#FWLX").val()==""){
   				layer.tips("请选择发文类型","#FWLX",{
   					tips:[1,'#3595CC'],
   					time:3000
   				});
   				return false;
			}
			  			
			if($("#FWWH").val().trim()==""){
   				layer.tips("请填写发文文号","#FWWH",{
   					tips:[1,'#3595CC'],
   					time:3000
   				});
   				return false;
			}
			
			if($("#FZRQ").val().trim()==""){
   				layer.tips("请填写发文日期","#FZRQ",{
   					tips:[1,'#3595CC'],
   					time:3000
   				});
   				return false;
			}
			$("#save").attr("disabled","disabled");//避免重复提交
   			var newUrl='sphz/fawen/save.do?FWLXID='+FWLXID;
   			if(FWLXID=="建设许可（建筑工程）"){
   				//newUrl='sphz/fawen/edit.do?FWLXID='+FWLXID+'&JZWSYXZ='+$("#JZWSYXZ").val();
   				$("#JZWSYXZ_SELECT").val($("#JZWSYXZ").val());
   			}
   			$("#XMSD_SELECT").val($("#XMSD").val());
   			$("#addForm").attr('action',newUrl);
			$(".unDisDiv").remove();//删除display=none的发文信息Div
			var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
			$("#editForm").ajaxSubmit(function(data){
    			if(data.result=="success"){
    				layer.msg('保存成功', {icon: 1});
    				alert(data.uuid);
    				console.log("项目唯一码："+data.uuid)
    				/* var index = parent.layer.getFrameIndex(window.name);
    				parent.layer.close(index); 
    				window.parent.changeCondition(); */
    				window.location.reload();//刷新父页面
    				
    			}else{
    				layer.msg('保存失败,'+data.result, {icon: 1});
    			}
    		});
   		})
   		
   		//查看发文附件
   		function showFiles(){
   			var businessId=$("#BSNUM").val();
			var width=800;
		    var height=400;
		 	var indext = layer.open({
		         type: 2,
		         title: '附件列表',
		         shadeClose: true,
		         shade: false,
		         maxmin: true, //开启最大化最小化按钮
		         //btn:['确定','取消'],
		         area: [width+'px', height+'px'],
		         content: 'sphz/fawen/goFilesOnlyShow.do?businessId='+businessId
		     });
   		}
   		
  		
        
      
    </script>

  
</body>

</html>