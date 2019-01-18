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

<link rel="stylesheet" type="text/css" href="plugins/webuploader/webuploader.css" />
<link rel="stylesheet" type="text/css" href="plugins/webuploader/style.css" />
<!-- Custom CSS -->
<link rel="stylesheet" href="plugins/xha/dist/css/pages/index.css">
<link rel="stylesheet" href="plugins/xha/plugins/layer/theme/default/layer.css">


</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- <div id="container"> -->
	   <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper1">
        
        
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <h1>
                    ${title}
                    <small> 请按照格式规范输入以下信息</small>
                </h1>

                <div class="breadcrumb">
                    <button type="button" id="save" class="btn btn-block btn-primary btn-sm" data-widget=""><i class="fa fa-check"></i> 保存</button>
                </div>
            </section>

            <!-- Main content -->
            <section class="content container-fluid">
				<form role="form" action="sphz/fawen/edit.do" name="editForm" id="editForm" method="post" enctype="multipart/form-data">
				<input type="hidden" name="DRSJ" value="${pd.DRSJ}">
				<input type="hidden" name="FWLXID" value="${pd.FWLXID}">
				<input type="hidden" name="FAWEN_ID" value="${pd.FAWEN_ID}">
				<div class="row">
					<div class="col-md-6">
						<!-- 项目信息 -->
						<div class="box box-warning">
							<div class="box-header with-border">
								<h3 class="box-title text-yellow">项目信息</h3>

								<div class="box-tools pull-right">
									<button type="button" class="btn btn-box-tool"
										data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="box-body">
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">项目唯一码</label> <input type="text" id="XMWYM"
												name="XMWYM" value="${pd.XMWYM }" class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">项目编号</label> <input type="text" id="XMBH"
												name="XMBH" value="${pd.XMBH }" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">项目名称</label> <input type="text" id="XMMC"
												name="XMMC" value="${pd.XMMC }" class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">项目位置</label> <input type="text" id="XMWZ"
												name="XMWZ" value="${pd.XMWZ }" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">建设单位</label> <input type="text" id="JSDW"
												name="JSDW" value="${pd.JSDW }" class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">所属区划</label>
											<div class="box-select">
												<select id=SSQH name="SSQH" class="form-control select2"
													style="width: 100%;">
													<option selected="selected" value=""></option>
													<c:forEach items="${ssqhList}" var="item">
														<option value="${item.DICTIONARIES_ID }"
															<c:if test="${item.DICTIONARIES_ID == pd.SSQH }">selected</c:if>>${item.NAME }</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">规划用地性质</label> <input type="text" id="GHYDXZ"
												name="GHYDXZ" value="${pd.GHYDXZ }" class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">建筑物使用性质</label>
											<div class="box-select">
												<select id=JZWSYXZ name="JZWSYXZ"
													class="form-control select2" style="width: 100%;">
													<option selected="selected" value=""></option>
													<c:forEach items="${jzwsyxzList}" var="item">
														<option value="${item.DICTIONARIES_ID }"
															<c:if test="${item.DICTIONARIES_ID == pd.JZWSYXZ }">selected</c:if>>${item.NAME }</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label>备注</label>
									<textarea id="BZ" name="BZ" class="form-control input-textarea">${pd.BZ }</textarea>
								</div>
							</div>
						</div>

						<!-- 档案信息 -->
						<div class="box box-info">
							<div class="box-header with-border">
								<h3 class="box-title text-light-blue">档案信息</h3>

								<div class="box-tools pull-right">
									<button type="button" class="btn btn-box-tool"
										data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="box-body">
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">档案编号</label> <input type="text" id="DABH"
												name="DABH" value="${pd.DABH }" class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">档案盒号</label> <input type="text" id="DAHH"
												name="DAHH" value="${pd.DAHH }" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">档案来源</label>
											<div class="box-select">
												<select id=DALY name="DALY" class="form-control select2"
													style="width: 100%;">
													<option selected="selected" value=""></option>
													<c:forEach items="${dalyList}" var="item">
														<option value="${item.DICTIONARIES_ID }"
															<c:if test="${item.DICTIONARIES_ID == pd.DALY }">selected</c:if>>${item.NAME }</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">录入人员</label> <input type="text" id="LRRY"
												name="LRRY" value="${pd.LRRY }" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">审核人员</label> <input type="text" id="SHRY"
												name="SHRY" value="${pd.SHRY }" class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">录入日期</label>
											<div class="input-group date">
												<div class="input-group-addon">
													<i class="fa fa-calendar"></i>
												</div>
												<input type="text" name="LRRQ" value="${pd.LRRQ }"
													class="form-control pull-right datepicker" id="datepicker">
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">审核日期</label>
											<div class="input-group date">
												<div class="input-group-addon">
													<i class="fa fa-calendar"></i>
												</div>
												<input type="text" name="SHRQ" value="${pd.SHRQ }"
													class="form-control pull-right datepicker" id="datepicker">
											</div>
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">审核状态</label>
											<div class="box-select">
												<select id=SHZT name="SHZT" class="form-control select2"
													style="width: 100%;">
													<option selected="selected" value=""></option>
													<c:forEach items="${shztList}" var="item">
														<option value="${item.DICTIONARIES_ID }"
															<c:if test="${item.DICTIONARIES_ID == pd.SHZT }">selected</c:if>>${item.NAME }</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>


						<!-- 附件信息 -->
						<div class="box box-warning">
							<div class="box-header with-border">
								<h3 class="box-title text-yellow">附件信息</h3>

								<div class="box-tools pull-right">
									<button type="button" class="btn btn-box-tool"
										data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="box-body">
								<div class="row">
									<div class="col-lg-6">
										<p class="form-label">红线范围</p>
										<div class="form-group input-group input-group-sm">
											<label for=""></label> <input type="text"
												class="form-control" id="hx-upload-text"
												value="${pd.redFileName }" readonly="readonly"> <input
												type="file" class="form-control" name="redFile" id="redFile"
												style="display: none;"> <span
												class="input-group-btn">
												<button type="button" class="btn btn-info btn-flat"
													id="hx-upload-btn">
													<i class="fa fa-upload"></i>
												</button>
											</span>
										</div>
									</div>
								</div>
							</div>
							<div class="box-body">
								<p class="form-label">扫描文书</p>
								<div id="uploader" style="width: 100%;">
									<div class="queueList">
										<div id="dndArea" class="placeholder">
											<div id="filePicker"></div>
											<p>或将文件拖到这里</p>
										</div>
									</div>
									<div class="statusBar" style="display: none;">
										<div class="progress">
											<span class="text">0%</span> <span class="percentage"></span>
										</div>
										<div class="info"></div>
										<div class="btns">
											<div id="filePicker2"></div>
											<div class="uploadBtn">开始上传</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<!-- 发文信息 -->
						<div class="box box-danger">
							<div class="box-header with-border">
								<h3 class="box-title text-red">发文信息</h3>

								<div class="box-tools pull-right">
									<button type="button" class="btn btn-box-tool"
										data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="box-body">
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">文号唯一码</label> <input type="text" id="WHWYM"
												name="WHWYM" value="${pd.WHWYM }" class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">受理编号</label> <input type="text" id="SLBH"
												name="SLBH" value="${pd.SLBH }" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">受理日期</label>
											<div class="input-group date">
												<div class="input-group-addon">
													<i class="fa fa-calendar"></i>
												</div>
												<input type="text" name="SLRQ" value="${pd.SLRQ }"
													class="form-control pull-right datepicker" id="datepicker">
											</div>
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">发文文号</label> <input type="text" id="FWWH"
												name="FWWH" value="${pd.FWWH }" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">发文日期</label>
											<div class="input-group date">
												<div class="input-group-addon">
													<i class="fa fa-calendar"></i>
												</div>
												<input type="text" name="FWRQ" value="${pd.FWRQ }"
													class="form-control pull-right datepicker" id="datepicker">
											</div>
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">证书钢印号</label> <input type="text" id="ZSGYH"
												name="ZSGYH" value="${pd.ZSGYH }" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">申办类型</label>
											<div class="box-select">
												<select id=SBLX name="SBLX" class="form-control select2"
													style="width: 100%;">
													<option selected="selected" value=""></option>
													<c:forEach items="${sblxList}" var="item">
														<option value="${item.DICTIONARIES_ID }"
															<c:if test="${item.DICTIONARIES_ID == pd.SBLX }">selected</c:if>>${item.NAME }</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">审批阶段</label>
											<div class="box-select">
												<select id=SPJD name="SPJD" class="form-control select2"
													style="width: 100%;">
													<option selected="selected" value=""></option>
													<c:forEach items="${spjdList}" var="item">
														<option value="${item.DICTIONARIES_ID }"
															<c:if test="${item.DICTIONARIES_ID == pd.SPJD }">selected</c:if>>${item.NAME }</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">工程类别</label>
											<div class="box-select">
												<select id=GCLB name="GCLB" class="form-control select2"
													style="width: 100%;">
													<option selected="selected" value=""></option>
													<c:forEach items="${gclbList}" var="item">
														<option value="${item.DICTIONARIES_ID }"
															<c:if test="${item.DICTIONARIES_ID == pd.GCLB }">selected</c:if>>${item.NAME }</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">项目类型</label>
											<div class="box-select">
												<select id=XMLX name="XMLX" class="form-control select2"
													style="width: 100%;">
													<option selected="selected" value=""></option>
													<c:forEach items="${xmlxList}" var="item">
														<option value="${item.DICTIONARIES_ID }"
															<c:if test="${item.DICTIONARIES_ID == pd.XMLX }">selected</c:if>>${item.NAME }</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">承办部门</label>
											<div class="box-select">
												<select id=CBBM name="CBBM" class="form-control select2"
													style="width: 100%;">
													<option selected="selected" value=""></option>
													<c:forEach items="${cbbmList}" var="item">
														<option value="${item.DICTIONARIES_ID }"
															<c:if test="${item.DICTIONARIES_ID == pd.CBBM }">selected</c:if>>${item.NAME }</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">经办人</label> <input type="text" id="JBR"
												name="JBR" value="${pd.JBR }" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">签批人</label> <input type="text" id="QPR"
												name="QPR" value="${pd.QPR }" class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">发文类型</label>
											<div class="box-select">
												<select id=FWLX name="FWLX" class="form-control select2"
													style="width: 100%;">
													<option selected="selected" value=""></option>
													<c:forEach items="${fwlxList}" var="item">
														<option value="${item.DICTIONARIES_ID }"
															<c:if test="${item.DICTIONARIES_ID == pd.FWLX }">selected</c:if>>${item.NAME }</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">组织机构代码</label> <input type="text" id="ZZJGDM"
												name="ZZJGDM" value="${pd.ZZJGDM }" class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">报建人</label> <input type="text" id="BJR"
												name="BJR" value="${pd.BJR }" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">联系方式</label> <input type="text" id="LXFS"
												name="LXFS" value="${pd.LXFS }" class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">设计单位</label> <input type="text" id="SJDW"
												name="SJDW" value="${pd.SJDW }" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">规划用地性质</label> <input type="text" id="GHYDXZ"
												name="GHYDXZ" value="${pd.GHYDXZ }" class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">混合用地性质</label>
											<div class="box-select">
												<select id=HHYDXZ name="HHYDXZ" class="form-control select2"
													style="width: 100%;">
													<option selected="selected" value=""></option>
													<c:forEach items="${hhydxzList}" var="item">
														<option value="${item.DICTIONARIES_ID }"
															<c:if test="${item.DICTIONARIES_ID == pd.HHYDXZ }">selected</c:if>>${item.NAME }</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>


						<!-- 发证属性信息 -->
						<!-- ---            选址规条字(建筑)                    --- -->
						<div class="box box-success infoDiv unDisDiv"
							id=infoDiv_da2e8025b1454f999d1970b7de49c014
							style="display: none;">
							<div class="box-header with-border">
								<h3 class="box-title text-green">发证属性信息</h3>

								<div class="box-tools pull-right">
									<button type="button" class="btn btn-box-tool"
										data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="box-body">
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">用地面积（平方米）</label> <input type="text" id="YDMJ"
												name="YDMJ" value="${pd.YDMJ }" class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">容积率</label> <input type="text" id="RJL"
												name="RJL" value="${pd.RJL }" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">建筑密度</label> <input type="text" id="JZMD"
												name="JZMD" value="${pd.JZMD }" class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">绿地率</label> <input type="text" id="LDL"
												name="LDL" value="${pd.LDL }" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">停车泊位</label> <input type="text" id="TCBW"
												name="TCBW" value="${pd.TCBW }" class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">建筑高度</label> <input type="text" id="JZGD"
												name="JZGD" value="${pd.JZGD }" class="form-control">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label>海绵城市要求</label>
									<textarea id="HMCSYQ" name="HMCSYQ"
										class="form-control input-textarea">${pd.HMCSYQ }</textarea>
								</div>
							</div>
						</div>

						<!-- ---            选址规条字(道桥)                    --- -->
						<div class="box box-success infoDiv unDisDiv"
							id=infoDiv_4356c43d648d48208091793b50089340
							style="display: none;">
							<div class="box-header with-border">
								<h3 class="box-title text-green">发证属性信息</h3>

								<div class="box-tools pull-right">
									<button type="button" class="btn btn-box-tool"
										data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="box-body">
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">道路等级</label>
											<div class="box-select">
												<select id=DLDJ name="DLDJ" class="form-control select2"
													style="width: 100%;">
													<option selected="selected" value=""></option>
													<c:forEach items="${dldjList}" var="item">
														<option value="${item.DICTIONARIES_ID }"
															<c:if test="${item.DICTIONARIES_ID == pd.DLDJ }">selected</c:if>>${item.NAME }</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">道路长度（米）</label> <input type="text" id="DLCD"
												name="DLCD" value="${pd.DLCD }" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">红线宽度（米）</label> <input type="text" id="HXKD"
												name="HXKD" value="${pd.HXKD }" class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">断面形式</label>
											<div class="box-select">
												<select id=DMXS name="DMXS" class="form-control select2"
													style="width: 100%;">
													<option selected="selected" value=""></option>
													<c:forEach items="${dmxsList}" var="item">
														<option value="${item.DICTIONARIES_ID }"
															<c:if test="${item.DICTIONARIES_ID == pd.DMXS }">selected</c:if>>${item.NAME }</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">设计标准</label> <input type="text" id="SJBZ"
												name="SJBZ" value="${pd.SJBZ }" class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label>海绵城市要求</label>
											<textarea id="HMCSYQ" name="HMCSYQ"
												class="form-control input-textarea">${pd.HMCSYQ }</textarea>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- ---            选址规条字(管线)                    --- -->
						<div class="box box-success infoDiv unDisDiv"
							id=infoDiv_5cef408b328a4dfea51e259082bd7fb9
							style="display: none;">
							<div class="box-header with-border">
								<h3 class="box-title text-green">发证属性信息</h3>

								<div class="box-tools pull-right">
									<button type="button" class="btn btn-box-tool"
										data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="box-body">
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">管线类型</label>
											<div class="box-select">
												<select id=GXLX name="GXLX" class="form-control select2"
													style="width: 100%;">
													<option selected="selected" value=""></option>
													<c:forEach items="${gxlxList}" var="item">
														<option value="${item.DICTIONARIES_ID }"
															<c:if test="${item.DICTIONARIES_ID == pd.GXLX }">selected</c:if>>${item.NAME }</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">管线长度（米）</label> <input type="text" id="GXCD"
												name="GXCD" value="${pd.GXCD }" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">设计标准</label> <input type="text" id="SJBZ"
												name="SJBZ" value="${pd.SJBZ }" class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label>海绵城市要求</label>
											<textarea id="HMCSYQ" name="HMCSYQ"
												class="form-control input-textarea">${pd.HMCSYQ }</textarea>
										</div>
									</div>
								</div>
							</div>
						</div>


						<!-- ---            地字(建筑道桥管线)                    --- -->
						<div class="box box-success infoDiv unDisDiv"
							id=infoDiv_66c1002c0f7c46198f85caf4e785b108
							style="display: none;">
							<div class="box-header with-border">
								<h3 class="box-title text-green">发证属性信息</h3>

								<div class="box-tools pull-right">
									<button type="button" class="btn btn-box-tool"
										data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="box-body">
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">用地面积（平方米）</label> <input type="text" id="YDMJ"
												name="YDMJ" value="${pd.YDMJ }" class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">投资管理类型</label>
											<div class="box-select">
												<select id=TZGLLX name="TZGLLX" class="form-control select2"
													style="width: 100%;">
													<option selected="selected" value=""></option>
													<c:forEach items="${tzgllxList}" var="item">
														<option value="${item.DICTIONARIES_ID }"
															<c:if test="${item.DICTIONARIES_ID == pd.TZGLLX }">selected</c:if>>${item.NAME }</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">土地使用类型</label>
											<div class="box-select">
												<select id=TDSYLX name="TDSYLX" class="form-control select2"
													style="width: 100%;">
													<option selected="selected" value=""></option>
													<c:forEach items="${tdsylxList}" var="item">
														<option value="${item.DICTIONARIES_ID }"
															<c:if test="${item.DICTIONARIES_ID == pd.TDSYLX }">selected</c:if>>${item.NAME }</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label>海绵城市要求</label>
											<textarea id="HMCSYQ" name="HMCSYQ"
												class="form-control input-textarea">${pd.HMCSYQ }</textarea>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- ---            建字(建筑)                    --- -->
						<div class="box box-success infoDiv unDisDiv"
							id=infoDiv_f727c923cfb94566841812b4fb78e4b8
							style="display: none;">
							<div class="box-header with-border">
								<h3 class="box-title text-green">发证属性信息</h3>

								<div class="box-tools pull-right">
									<button type="button" class="btn btn-box-tool"
										data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="box-body">
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">用地面积（平方米）</label> <input type="text" id="YDMJ"
												name="YDMJ" value="${pd.YDMJ }" class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">容积率</label> <input type="text" id="RJL"
												name="RJL" value="${pd.RJL }" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">建筑密度</label> <input type="text" id="JZMD"
												name="JZMD" value="${pd.JZMD }" class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">绿地率</label> <input type="text" id="LDL"
												name="LDL" value="${pd.LDL }" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">建筑高度</label> <input type="text" id="JZGD"
												name="JZGD" value="${pd.JZGD }" class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">总建筑面积（平方米）</label> <input type="text"
												id="ZJZMJ" name="ZJZMJ" value="${pd.ZJZMJ }"
												class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">地上建筑面积（平方米）</label> <input type="text"
												id="DSJZMJ" name="DSJZMJ" value="${pd.DSJZMJ }"
												class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">地下建筑面积（平方米）</label> <input type="text"
												id="DXJZMJ" name="DXJZMJ" value="${pd.DXJZMJ }"
												class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">停车泊位（个）</label> <input type="text" id="TCBW"
												name="TCBW" value="${pd.TCBW }" class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">地上停车泊位（个）</label> <input type="text"
												id="DSTCBW" name="DSTCBW" value="${pd.DSTCBW }"
												class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">地下停车泊位（个）</label> <input type="text"
												id="DXTCBW" name="DXTCBW" value="${pd.DXTCBW }"
												class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">住宅面积（平方米）</label> <input type="text" id="ZZMJ"
												name="ZZMJ" value="${pd.ZZMJ }" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">户数</label> <input type="text" id="HS" name="HS"
												value="${pd.HS }" class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">建筑物使用性质</label>
											<div class="box-select">
												<select id=JZWSYXZ name="JZWSYXZ"
													class="form-control select2" style="width: 100%;">
													<option selected="selected" value=""></option>
													<c:forEach items="${jzwsyxzList}" var="item">
														<option value="${item.DICTIONARIES_ID }"
															<c:if test="${item.DICTIONARIES_ID == pd.JZWSYXZ }">selected</c:if>>${item.NAME }</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label>海绵城市要求</label>
									<textarea id="HMCSYQ" name="HMCSYQ"
										class="form-control input-textarea">${pd.HMCSYQ }</textarea>
								</div>
							</div>
						</div>

						<!-- ---            建字(道桥)                    --- -->
						<div class="box box-success infoDiv unDisDiv"
							id=infoDiv_af1cef1c384f4b5387987f0bdc830724
							style="display: none;">
							<div class="box-header with-border">
								<h3 class="box-title text-green">发证属性信息</h3>

								<div class="box-tools pull-right">
									<button type="button" class="btn btn-box-tool"
										data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="box-body">
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">道路类型</label>
											<div class="box-select">
												<select id=DLLX name="DLLX" class="form-control select2"
													style="width: 100%;">
													<option selected="selected" value=""></option>
													<c:forEach items="${dllxList}" var="item">
														<option value="${item.DICTIONARIES_ID }"
															<c:if test="${item.DICTIONARIES_ID == pd.DLLX }">selected</c:if>>${item.NAME }</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">道路长度（米）</label> <input type="text" id="DLCD"
												name="DLCD" value="${pd.DLCD }" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">红线宽度（米）</label> <input type="text" id="HXKD"
												name="HXKD" value="${pd.HXKD }" class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">折算建筑面积（平方米）</label> <input type="text"
												id="ZSJZMJ" name="ZSJZMJ" value="${pd.ZSJZMJ }"
												class="form-control">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label>海绵城市要求</label>
									<textarea id="HMCSYQ" name="HMCSYQ"
										class="form-control input-textarea">${pd.HMCSYQ }</textarea>
								</div>
							</div>
						</div>


						<!-- ---            建字(管线)                    --- -->
						<div class="box box-success infoDiv unDisDiv"
							id=infoDiv_424008dcbf0b4d80938883d31feafc93
							style="display: none;">
							<div class="box-header with-border">
								<h3 class="box-title text-green">发证属性信息</h3>

								<div class="box-tools pull-right">
									<button type="button" class="btn btn-box-tool"
										data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="box-body">
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">管线类型</label>
											<div class="box-select">
												<select id=GXLX name="GXLX" class="form-control select2"
													style="width: 100%;">
													<option selected="selected" value=""></option>
													<c:forEach items="${gxlxList}" var="item">
														<option value="${item.DICTIONARIES_ID }"
															<c:if test="${item.DICTIONARIES_ID == pd.GXLX }">selected</c:if>>${item.NAME }</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">管线长度（米）</label> <input type="text" id="GXCD"
												name="GXCD" value="${pd.GXCD }" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">折算建筑面积（平方米）</label> <input type="text"
												id="ZSJZMJ" name="ZSJZMJ" value="${pd.ZSJZMJ }"
												class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label>海绵城市要求</label>
											<textarea id="HMCSYQ" name="HMCSYQ"
												class="form-control input-textarea">${pd.HMCSYQ }</textarea>
										</div>
									</div>
								</div>
							</div>
						</div>


						<!-- ---            核字(建筑)                    --- -->
						<div class="box box-success infoDiv unDisDiv"
							id=infoDiv_35a5faa9f3554416b0283861b2ab283b
							style="display: none;">
							<div class="box-header with-border">
								<h3 class="box-title text-green">发证属性信息</h3>

								<div class="box-tools pull-right">
									<button type="button" class="btn btn-box-tool"
										data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="box-body">
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">总建筑面积（平方米）</label> <input type="text"
												id="ZJZMJ" name="ZJZMJ" value="${pd.ZJZMJ }"
												class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">地上建筑面积（平方米）</label> <input type="text"
												id="DSJZMJ" name="DSJZMJ" value="${pd.DSJZMJ }"
												class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">地下建筑面积（平方米）</label> <input type="text"
												id="DXJZMJ" name="DXJZMJ" value="${pd.DXJZMJ }"
												class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label>海绵城市要求</label>
											<textarea id="HMCSYQ" name="HMCSYQ"
												class="form-control input-textarea">${pd.HMCSYQ }</textarea>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- ---            核字(道桥)                    --- -->
						<div class="box box-success infoDiv unDisDiv"
							id=infoDiv_b64538bb9488464d95adef42eb29919d
							style="display: none;">
							<div class="box-header with-border">
								<h3 class="box-title text-green">发证属性信息</h3>

								<div class="box-tools pull-right">
									<button type="button" class="btn btn-box-tool"
										data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="box-body">
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">道路类型</label>
											<div class="box-select">
												<select id=DLLX name="DLLX" class="form-control select2"
													style="width: 100%;">
													<option selected="selected" value=""></option>
													<c:forEach items="${dllxList}" var="item">
														<option value="${item.DICTIONARIES_ID }"
															<c:if test="${item.DICTIONARIES_ID == pd.DLLX }">selected</c:if>>${item.NAME }</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">道路长度（米）</label> <input type="text" id="DLCD"
												name="DLCD" value="${pd.DLCD }" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">红线宽度（米）</label> <input type="text" id="HXKD"
												name="HXKD" value="${pd.HXKD }" class="form-control">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">折算建筑面积（平方米）</label> <input type="text"
												id="ZSJZMJ" name="ZSJZMJ" value="${pd.ZSJZMJ }"
												class="form-control">
										</div>
									</div>
								</div>

								<div class="form-group">
									<label>海绵城市要求</label>
									<textarea id="HMCSYQ" name="HMCSYQ"
										class="form-control input-textarea">${pd.HMCSYQ }</textarea>
								</div>
							</div>
						</div>

						<!-- ---            核字(管线)                    --- -->
						<div class="box box-success infoDiv unDisDiv"
							id=infoDiv_f8063ade57ca4f539e0007e3d4cfd8aa
							style="display: none;">
							<div class="box-header with-border">
								<h3 class="box-title text-green">发证属性信息</h3>

								<div class="box-tools pull-right">
									<button type="button" class="btn btn-box-tool"
										data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="box-body">
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">管线类型</label>
											<div class="box-select">
												<select id=GXLX name="GXLX" class="form-control select2"
													style="width: 100%;">
													<option selected="selected" value=""></option>
													<c:forEach items="${gxlxList}" var="item">
														<option value="${item.DICTIONARIES_ID }"
															<c:if test="${item.DICTIONARIES_ID == pd.GXLX }">selected</c:if>>${item.NAME }</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label for="">管线长度（米）</label> <input type="text" id="GXCD"
												name="GXCD" value="${pd.GXCD }" class="form-control">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label>海绵城市要求</label>
									<textarea id="HMCSYQ" name="HMCSYQ"
										class="form-control input-textarea">${pd.HMCSYQ }</textarea>
								</div>
							</div>
						</div>


						<!-- 发证信息结束 -->
					</div></form>   
            </section>
       
        </div>
        <!-- /.content-wrapper -->

        <!-- Main Footer -->
        <footer class="main-footer">
        </footer>
    </div>
    <!-- ./wrapper -->
   <!-- jQuery 3 -->
    <script src="plugins/xha/plugins/jquery/dist/jquery.min.js "></script>
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
   	<script type="text/javascript" src="plugins/webuploader/webuploader.js"></script>
   	<script type="text/javascript" src="plugins/webuploader/upload.js"></script>
   
    
    <script type="text/javascript">
    //初始化扫描文书文件列表
	var str='';
	str+='<table id="file-table" class="table table-striped table-bordered table-hover"  style="margin-top:0px;">'+
		'<tr>'+
			'<td class="center" style="width:50px;text-align:center;">序号</td>'+
			'<td class="center" style="text-align:center;">文件名</td>'+
			'<td class="center" style="text-align:center;">上传时间</td>'+
			'<td class="center" style="width:50px;text-align:center;">操作</td>'+
		'</tr>'+
	'</table>';
	$("#uploader").before(str);
	
	$.ajax({
			type: "POST",
			url: 'files/listFilesByFwwyh.do?tm='+new Date().getTime(),
	    	data: {WHWYM:$("#WHWYM").val()},
			dataType:'json',
			cache: false,
			success: function(data){
				var fileList=data.filesList;
				console.log(fileList);
				if(null==fileList||fileList.length==0){
					$("#file-table tr:last").after("<tr><td colspan='6' style='text-align:center;'>暂无文件</td></tr>");
				}
				for(var i=0;i<fileList.length;i++){
					var item=fileList[i];
					var row="<tr id="+item.FILE_ID+">";
					row +="<td class='center' style='width:50px;text-align:center;' >"+(i+1)+"</td>";
					row +="<td class='center' style='text-align:center;'>"+item.FILE_NAME+"</td>";
					row +="<td class='center' style='text-align:center;'>"+ item.CREATE_TIME+"</td>";
					row +="<td class='center' style='width:50px;text-align:center;'>";
					row +="<a style='cursor:pointer;' class='red' onclick='del(\""+item.FILE_ID+"\");' title='删除'>";
					row +="<i class='ace-icon fa fa-trash-o bigger-130'></i></a>"
					row +="</td></tr>";
					$("#file-table tr:last").after(row);
					
				}
				
			}
		});
				
		function del(id){
			if(confirm("确定要删除?")){ 
				var url = "<%=basePath%>files/deleteFile.do?FILE_ID="+id+"&tm="+new Date().getTime();
				$.get(url,function(data){
					$("#"+id).remove();
				});
			}
		}
		
		//初始化发文属性
		var fwlxId= "${pd.FWLXID }";
		$("#infoDiv_"+fwlxId).css("display","block");
		$("#infoDiv_"+fwlxId).removeClass("unDisDiv");
		$(".unDisDiv").remove();//删除display=none的发文信息Div
    	
    	$(function(){
    		$.ajax({//土地利用类型树状下拉选择框
				type: "POST",
				url: 'dictionaries/getTreeSelectData.do',
		    	data: {DICTIONARIES_ID:'c7fa42b047ef49bca1100d3fc636f533',tm:new Date().getTime()},
				dataType:'json',
				cache: false,
				success: function(data){
					var dicTreeSelectListData=data.dicTreeSelectList;
					
					$("#select-tree").select2ToTree({ treeData: { dataArr: dicTreeSelectListData }, maximumSelectionLength: 3, minimumResultsForSearch: -1 });
					$("#select-tree").on("select2:select", function() {
					    //console.log($("#select-tree").val());
					    $("#LAND_USE").val($("#select-tree").val());
					});
					$("#select-tree2").select2ToTree({ treeData: { dataArr: dicTreeSelectListData }, maximumSelectionLength: 3, minimumResultsForSearch: -1 });
					$("#select-tree2").on("select2:select", function() {
					    //console.log($("#select-tree2").val());
					     $("#LAND_USE").val($("#select-tree2").val());
					});
					$("#select-tree3").select2ToTree({ treeData: { dataArr: dicTreeSelectListData }, maximumSelectionLength: 3, minimumResultsForSearch: -1 });
					$("#select-tree3").on("select2:select", function() {
					   // console.log($("#select-tree3").val());
					     $("#LAND_USE").val($("#select-tree3").val());
					});
					 //编辑信息时带出name
					$("#select2-select-tree-container").html("${null==pd.LAND_USE_NAME||''==pd.LAND_USE_NAME?'请选择':pd.LAND_USE_NAME}");
					$("#select2-select-tree2-container").html("${null==pd.LAND_USE_NAME||''==pd.LAND_USE_NAME?'请选择':pd.LAND_USE_NAME}");
					$("#select2-select-tree3-container").html("${null==pd.LAND_USE_NAME||''==pd.LAND_USE_NAME?'请选择':pd.LAND_USE_NAME}");
				},
				error:function(data){
					console.log(data);
					alert(error);
				}
			});
    	
    		//保存按钮点击事件
    		$("#save").on("click",function(){
				$(".unDisDiv").remove();//删除display=none的发文信息Div
    			var layersubmit= layer.confirm('保存成功，是否提交？', {
				  btn: ['提交','取消'] //按钮
				}, function(){//提交
					layer.close(layersubmit);
					var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
					$("#editForm").ajaxSubmit(function(data){
		    			if(data.result=="success"){
		    				layer.msg('提交成功', {icon: 1});
		    				self.location.reload();
		    				//关闭父窗口弹窗
		    				if('${msg }'=='edit'){
			    				var index = parent.layer.getFrameIndex(window.name);
			    				parent.layer.close(index); 
			    				window.parent.location.reload();//刷新父页面
		    				}
		    			}
		    		});
				  
				}, function(){//取消
					layer.close(layersubmit);
					var index = parent.layer.getFrameIndex(window.name);
    				parent.layer.close(index); 
				});
			
    		})
    		
    		// 上传功能
		    $('#tz-upload-btn').click(function() {
		        $('#planFile').click();
		    });
		    $('#tz-upload-text').click(function() {
		        $('#planFile').click();
		    });
		    $('#planFile').on('change', function(e) {
		        // 上传图纸
		        var tzUrl = $("#planFile").val(); // 路径
		        var tzName = e.currentTarget.files[0].name; // 文件名
		        $("#tz-upload-text").val(tzName);
		    });
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
		    
    		//选择项目对话框
    		$("#searchProj").on("click",function(){
    			var indext = layer.open({
                    type: 2,
                    title: '请选择项目',
                    shadeClose: true,
                    shade: false,
                    maxmin: true, //开启最大化最小化按钮
                    btn:['确定','取消'],
                    area: ['750px', '650px'],
                    content: '<%=basePath%>sphz/projSelect.do',
                    yes:function(index,layero){
                        var ids=layero.find("iframe")[0].contentWindow.$(".ids");
                        var codesStr="";
           		 		for(var i=0; i<ids.length; i++) {
						    if(ids[i].checked) {
							  	codesStr += ids[i].value;
						    }
						}
						if(codesStr!=""){
							$.ajax({
								type: "POST",
								url: '<%=basePath%>sphz/getProjByNo.do',
						    	data: {PROJ_NO:codesStr},
								dataType:'json',
								cache: false,
								success: function(data){
									if(data.result=="success"){
										var	proj= data.proj;
										$("#PROJ_NO").val(proj.PROJ_NO);
										$("#PROJ_NAME").val(proj.PROJ_NAME);
										$("#PROJ_UNIT").val(proj.PROJ_UNIT);
										$("#PROJ_LOCATION").val(proj.PROJ_LOCATION);
										$("#ORG_CODE").val(proj.ORG_CODE);
										$("#APPLICANT_NAME").val(proj.APPLICANT_NAME);
										$("#APPLICANT_PHONE").val(proj.APPLICANT_PHONE);
										
									}
					           			
								}
						   });
						}
						layer.close(indext);
						console.log(codesStr);
                    },
                    cancel:function(){
                    	layer.close(indext);
                    }
                });
    		})
    	})
    	
    	//报建类型切换更换发证信息内容
    	function changeStage(stage){
   			var stageIndex= $("#stageSelect").val();
   			var infoDivs=$(".infoDiv");
   			for(var i=0;i<infoDivs.length;i++){
   				infoDivs[i].style.display="none";
   				$(".infoDiv:eq("+i+")").addClass("unDisDiv");
   			}
   			infoDivs[stageIndex-1].style.display="block";
   			$(".infoDiv:eq("+(stageIndex-1)+")").removeClass("unDisDiv");
   		}

    </script>

  
</body>

</html>