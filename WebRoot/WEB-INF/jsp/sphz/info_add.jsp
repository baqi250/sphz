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
                <!-- 申报类型 -->
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title text-blue box-select-title">申报类型</h3>

                        <div class="box-select">
                            <select id="stageSelect" class="form-control select2" onchange="changeStage(this.value)" style="width: 100%;">
                            	<c:if test="${pd.STAGE_TYPE == 1 }">
									<option selected="selected" value="1">建设项目选址意见书</option>
								</c:if>
								<c:if test="${pd.STAGE_TYPE == 2 }">
									<option selected="selected" value="2">建设项目规划条件变更</option>
								</c:if>
								<c:if test="${pd.STAGE_TYPE == 3 }">
									<option selected="selected" value="3">建设用地规划许可证</option>
								</c:if>
								<c:if test="${pd.STAGE_TYPE == 4 }">
									<option selected="selected" value="4">建设工程规划许可证（建筑）</option>
								</c:if>
								<c:if test="${pd.STAGE_TYPE == 5 }">
									<option selected="selected" value="5">建设工程规划许可证（市政交通）</option>
								</c:if>
								<c:if test="${pd.STAGE_TYPE == 6 }">
									<option selected="selected" value="6">建设工程竣工规划验收合格证</option>
								</c:if>
								<c:if test="${null==pd.STAGE_TYPE||''==pd.STAGE_TYPE}">
								    <option selected="selected" value="1">建设项目选址意见书</option>
	                                <option value="2">建设项目规划条件变更</option>
	                                <option value="3">建设用地规划许可证</option>
	                                <option value="4">建设工程规划许可证（建筑）</option>
	                                <option value="5">建设工程规划许可证（市政交通）</option>
	                                <option value="6">建设工程竣工规划验收合格证</option>
								</c:if>
                              
                            </select>
                        </div>
                    </div>
                </div>
				<form role="form" action="sphz/${msg}.do" name="addForm" id="addForm" method="post" enctype="multipart/form-data">
				<input type="hidden" name="LAND_USE" id="LAND_USE" value="${pd.LAND_USE }" />
				<!-- <form role="form" action="sphz/add.do" name="addForm" id="addForm" method="post"> -->
                <div class="row">
                    <div class="col-md-6">
                        <!-- 项目信息 -->
                        <div class="box box-warning">
                            <div class="box-header with-border">
                                <h3 class="box-title text-yellow">项目信息</h3>

                                <div class="box-tools pull-right">
                                    <button type="button" id="searchProj" class="btn btn-warning btn-xs" data-widget=""><i class="fa fa-search"></i> 搜索</button>
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                           	 	<!-- <form role="form" action="sphz/addProj.do" name="projForm" id="projForm" method="post"> -->
                                <!-- <form role="form" > -->
                                  	<div class="form-group">
                                        <label for="">项目唯一码</label>
                                        <input type="text" id="PROJ_KEY" name="PROJ_KEY" value="${pd1.PROJ_KEY }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">项目编号</label>
                                        <input type="text" id="PROJ_NO" name="PROJ_NO" value="${pd1.PROJ_NO }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">项目名称</label>
                                        <input type="text" id="PROJ_NAME" name="PROJ_NAME" value="${pd.PROJ_NAME }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">建设位置</label>
                                        <input type="text" id="PROJ_POSITION" name="PROJ_POSITION" value="${pd.PROJ_POSITION }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">建设单位</label>
                                        <input type="text" id="PROJ_JSDW" name="PROJ_JSDW" value="${pd.PROJ_JSDW }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">所属区划</label>
                                        <input type="text" id="PROJ_SSQH" name="PROJ_SSQH" value="${pd.PROJ_SSQH }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">规划用地性质</label>
                                        <input type="text" id="PROJ_GHYDXZ" name="PROJ_GHYDXZ" value="${pd.PROJ_GHYDXZ }" class="form-control">
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label for="">是否混合用地</label>
                                                <input type="text" id="PROJ_SFHHYD" name="PROJ_SFHHYD" value="${pd.PROJ_SFHHYD }" class="form-control">
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label for="">混合比例</label>
                                                <input type="text" class="form-control" id="PROJ_HHBL" name="PROJ_HHBL" value="${pd.PROJ_HHBL }">
                                            </div>
                                        </div>
                                    </div>
                                     <div class="form-group">
                                        <label for="">建筑物使用性质</label>
                                        <input type="text" id="PROJ_JZWSYXZ" name="PROJ_JZWSYXZ" value="${pd.PROJ_JZWSYXZ }" class="form-control">
                                    </div>
                                     <div class="form-group">
                                        <label for="">备注</label>
                                        <input type="text" id="PROJ_BZ" name="PROJ_BZ" value="${pd.PROJ_BZ }" class="form-control">
                                    </div>
                               <!--  </form> -->
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
                                     <div class="form-group">
                                        <label for="">受理编号</label>
                                        <input type="text" id="SLBH" name="SLBH" value="${pd.SLBH }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">受理日期</label>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input type="text"  name="SLRQ" value="${pd.SLRQ }" class="form-control pull-right datepicker" id="datepicker">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="">发文文号</label>
                                        <input type="text" id="FWWH" name="FWWH" value="${pd.FWWH }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">发文日期</label>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input type="text"  name="FWRQ" value="${pd.FWRQ }" class="form-control pull-right datepicker" id="datepicker">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="">证书钢印号</label>
                                        <input type="text" id="ZSGYH" name="ZSGYH" value="${pd.ZSGYH }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">申办类型</label>
                                        <input type="text" id="SBLX" name="SBLX" value="${pd.SBLX }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">审批阶段</label>
                                        <input type="text" id="SPJD" name="SPJD" value="${pd.SPJD }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">工程类别</label>
                                        <input type="text" id="GCLB" name="GCLB" value="${pd.GCLB }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">项目类型</label>
                                        <input type="text" id="XMLX" name="XMLX" value="${pd.XMLX }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">承办部门</label>
                                        <input type="text" id="CBBM" name="CBBM" value="${pd.CBBM }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">经办人</label>
                                        <input type="text" id="JBR" name="JBR" value="${pd.JBR }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">签批人</label>
                                        <input type="text" id="QPR" name="QPR" value="${pd.QPR }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">发文类型</label>
                                        <input type="text" id="FWLX" name="FWLX" value="${pd.FWLX }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">组织机构代码证</label>
                                        <input type="text" id="ZZJGDMZ" name="ZZJGDMZ" value="${pd.ZZJGDMZ }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">报建人</label>
                                        <input type="text" id="BJR" name="BJR" value="${pd.BJR }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">联系方式</label>
                                        <input type="text" id="LXFS" name="LXFS" value="${pd.LXFS }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">设计单位</label>
                                        <input type="text" id="SJDW" name="SJDW" value="${pd.SJDW }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">规划用地性质</label>
                                        <input type="text" id="GHYDXZ" name="GHYDXZ" value="${pd.GHYDXZ }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">混合用地性质</label>
                                        <input type="text" id="HHYDXZ" name="HHYDXZ" value="${pd.HHYDXZ }" class="form-control">
                                    </div>
                            </div>
                        </div>
                        <!-- 附件信息 -->
                        <div class="box box-info">
                            <div class="box-header with-border">
                                <h3 class="box-title text-light-blue">附件信息</h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                               <!--  <form role="form" action="sphz/addFile.do" name="fileForm" id="fileForm" method="post"> -->
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">平面图纸</p >
                                            <div class="form-group input-group input-group-sm">
                                                <label for=""></label>
                                                <input type="text" class="form-control" id="tz-upload-text" value="${pd.planFileName }" readonly="readonly">
                                                <input type="file" class="form-control" name="planFile" id="planFile" style="display: none;">
                                                <span class="input-group-btn">
                                                    <button type="button" class="btn btn-info btn-flat" id="tz-upload-btn"><i class="fa fa-upload"></i></button>
                                                </span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <p class="form-label">红线范围</p >
                                            <div class="form-group input-group input-group-sm">
                                                <label for=""></label>
                                                <input type="text" class="form-control" id="hx-upload-text" value="${pd.redFileName }" readonly="readonly">
                                                <input type="file" class="form-control" name="redFile" id="redFile" style="display: none;">
                                                <span class="input-group-btn">
                                                    <button type="button" class="btn btn-info btn-flat" id="hx-upload-btn"><i class="fa fa-upload"></i></button>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                               <!--  </form> -->
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                    
                    	    <!-- 档案信息 -->
                        <div class="box box-info">
                            <div class="box-header with-border">
                                <h3 class="box-title text-light-blue">档案信息</h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                          		  <div class="form-group">
                                        <label for="">档案编号</label>
                                        <input type="text" id="DABH" name="DABH" value="${pd.DABH }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">档案盒号</label>
                                        <input type="text" id="DAHH" name="DAHH" value="${pd.DAHH }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">档案来源</label>
                                        <input type="text" id="DALY" name="DALY" value="${pd.DALY }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">录入人员</label>
                                        <input type="text" id="LRRY" name="LRRY" value="${pd.LRRY }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">审核人员</label>
                                        <input type="text" id="SHRY" name="SHRY" value="${pd.SHRY }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">录入日期</label>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input type="text"  name="LRRQ" value="${pd.LRRQ }" class="form-control pull-right datepicker" id="datepicker">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="">审核日期</label>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input type="text"  name="SHRQ" value="${pd.SHRQ }" class="form-control pull-right datepicker" id="datepicker">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="">审核状态</label>
                                        <input type="text" id="SHZT" name="SHZT" value="${pd.SHZT }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">备注</label>
                                        <input type="text" id="FWBZ" name="FWBZ" value="${pd.FWBZ }" class="form-control">
                                    </div>
                            </div>
                        </div>
                        
                        <!-- 发证属性信息 -->
                        <!-- ---            选址规条字(建筑)                    --- -->
                        <div class="box box-success infoDiv" id=infoDiv1 style="display: block;">
                            <div class="box-header with-border">
                                <h3 class="box-title text-green">发证属性信息</h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                                <!--   <form role="form" action="sphz/fzxx.do" name="fzxxForm" id="fzxxForm" method="post"> -->
                                    <div class="form-group">
                                        <label for="">用地面积（平方米）</label>
                                        <input type="text" id="YDMJ" name="YDMJ" value="${pd.YDMJ }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">容积率</label>
                                        <input type="text" id="RJL" name="RJL" value="${pd.RJL }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">建筑密度</label>
                                        <input type="text" id="JZMD" name="JZMD" value="${pd.JZMD }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">绿地率</label>
                                        <input type="text" id="LDL" name="LDL" value="${pd.LDL }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">停车泊位</label>
                                        <input type="text" id="TCBW" name="TCBW" value="${pd.TCBW }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">建筑高度</label>
                                        <input type="text" id="JZGD" name="JZGD" value="${pd.JZGD }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label>海绵城市要求</label>
                                        <textarea id="HMCSYQ" name="HMCSYQ"  class="form-control input-textarea">${pd.HMCSYQ }</textarea>
                                    </div>
                            </div>
                        </div>
                        
                        <!-- ---              规划条件变更                         ---- -->
                         <div class="box box-success infoDiv unDisDiv" id=infoDiv2 >
                            <div class="box-header with-border">
                                <h3 class="box-title text-green">发证信息</h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                                <!--   <form role="form" action="sphz/fzxx.do" name="fzxxForm" id="fzxxForm" method="post"> -->
                                    <div class="form-group">
                                        <label for="">发文编号</label>
                                        <input type="text" id="OPINIONS_NO" name="OPINIONS_NO" value="${pd.OPINIONS_NO }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">发文日期</label>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input type="text"  name="OPINIONS_DATE" value="${pd.OPINIONS_DATE }"  class="form-control pull-right datepicker" id="datepicker">
                                        </div>
                                    </div>
                                   <!--  <div class="form-group">
                                        <label for="">用地性质名称</label>
                                        <input type="text" id="LAND_USE" name="LAND_USE" class="form-control">
                                        <label for="">用地性质名称</label>
										<input type="hidden" name="LAND_USE" id="LAND_USE" />
										<div class="selectTree" id="selectTree1"></div>
                                    </div> -->
                                    <div class="form-group">
                                        <label for="">用地性质名称</label>
                                        <select id="select-tree2" style="width:100%">
                                            <option selected="selected" disabled="disabled" style="display: none" value=""></option>
                                        </select>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">用地面积</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="LAND_AREA" name="LAND_AREA" value="${'0'==pd.LAND_AREA?'':pd.LAND_AREA }" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">公顷</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <p class="form-label">混合比例</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="MIX_RATIO" name="MIX_RATIO" value="${'0'==pd.MIX_RATIO?'':pd.MIX_RATIO }" class="form-control" data-inputmask="'mask': '0.99'">
                                                <span class="input-group-addon addon-w">.00</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">容积率</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="PLOT_RATIO" name="PLOT_RATIO" value="${'0'==pd.PLOT_RATIO?'':pd.PLOT_RATIO }" class="form-control" data-inputmask="'mask': '9.99'">
                                                <span class="input-group-addon">.00</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <p class="form-label">控制高度</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="CONTROL_HEIGHT" name="CONTROL_HEIGHT" value="${'0'==pd.CONTROL_HEIGHT?'':pd.CONTROL_HEIGHT }" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon">&nbsp;&nbsp;&nbsp;米</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">绿地率</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="GREEN_RATIO" name="GREEN_RATIO" value="${'0'==pd.GREEN_RATIO?'':pd.GREEN_RATIO }" class="form-control" data-inputmask="'mask': '99.99'">
                                                <span class="input-group-addon">&nbsp;&nbsp;%</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <p class="form-label">建筑密度</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="BUILDING_DENSITY" name="BUILDING_DENSITY" value="${'0'==pd.BUILDING_DENSITY?'':pd.BUILDING_DENSITY }" class="form-control" data-inputmask="'mask': '99.99'">
                                                <span class="input-group-addon">&nbsp;%</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">停车位数</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="PARKING" name="PARKING" value="${'0'==pd.PARKING?'':pd.PARKING }" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon">&nbsp;&nbsp;&nbsp;个</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">

                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label>海绵城市要求</label>
                                        <textarea id="SPONGE_REQUIRE" name="SPONGE_REQUIRE" class="form-control input-textarea">${pd.SPONGE_REQUIRE }</textarea>
                                    </div>
                               <!--  </form> -->
                            </div>
                        </div>
                        
                        <!-- -----------建设用地规划许可----------------- -->
                         <div class="box box-success infoDiv unDisDiv" id=infoDiv3>
                            <div class="box-header with-border">
                                <h3 class="box-title text-green">发证信息</h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                                <!--   <form role="form" action="sphz/fzxx.do" name="fzxxForm" id="fzxxForm" method="post"> -->
                                    <div class="form-group">
                                        <label for="">发文编号</label>
                                        <input type="text" id="OPINIONS_NO" name="OPINIONS_NO" value="${pd.OPINIONS_NO }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">发文日期</label>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input type="text"  name="OPINIONS_DATE" value="${pd.OPINIONS_DATE }" class="form-control pull-right datepicker" id="datepicker">
                                        </div>
                                    </div>
                                   <!--  <div class="form-group">
                                        <label for="">用地性质名称</label>
                                        <input type="text" id="LAND_USE" name="LAND_USE" class="form-control">
                                        <label for="">用地性质名称</label>
										<input type="hidden" name="LAND_USE" id="LAND_USE" />
										<div class="selectTree" id="selectTree2"></div>
                                    </div> -->
                                   <div class="form-group">
                                        <label for="">用地性质名称</label>
                                        <select id="select-tree3" style="width:100%">
                                            <option selected="selected" disabled="disabled" style="display: none" value=""></option>
                                        </select>
                                    </div>
                                    
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">用地面积</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="LAND_AREA" name="LAND_AREA" value="${'0'==pd.LAND_AREA?'':pd.LAND_AREA }" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">公顷</span>
                                            </div>
                                        </div>
                                       <div class="col-lg-6"></div>
                                    </div>
                                    <div class="form-group">
                                        <label>海绵城市要求</label>
                                        <textarea id="SPONGE_REQUIRE" name="SPONGE_REQUIRE" class="form-control input-textarea">${pd.SPONGE_REQUIRE }</textarea>
                                    </div>
                               <!--  </form> -->
                            </div>
                        </div>
                        <!-- -----------建设工程规划许可（建筑）----------------- -->
                        
                         <div class="box box-success infoDiv unDisDiv" id=infoDiv4>
                            <div class="box-header with-border">
                                <h3 class="box-title text-green">发证信息</h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                                <!--   <form role="form" action="sphz/fzxx.do" name="fzxxForm" id="fzxxForm" method="post"> -->
                                    <div class="form-group">
                                        <label for="">发文编号</label>
                                        <input type="text" id="OPINIONS_NO" name="OPINIONS_NO" value="${pd.OPINIONS_NO }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">发文日期</label>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input type="text"  name="OPINIONS_DATE" value="${pd.OPINIONS_DATE }" class="form-control pull-right datepicker" id="datepicker">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="">建筑物使用性质</label>
                                        <input type="text" id="BUILDING_PROPERTY" name="BUILDING_PROPERTY" value="${pd.BUILDING_PROPERTY }" class="form-control">
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">总建筑面积</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="BUILDING_AREA" name="BUILDING_AREA" value="${'0'==pd.BUILDING_AREA?'':pd.BUILDING_AREA }" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">公顷</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <p class="form-label">地上建筑面积</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="GROUND_AREA" name="GROUND_AREA" value="${'0'==pd.GROUND_AREA?'':pd.GROUND_AREA }" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">公顷</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">地下建筑面积</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="UNDERGROUND_AREA" name="UNDERGROUND_AREA" value="${'0'==pd.UNDERGROUND_AREA?'':pd.UNDERGROUND_AREA }" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">公顷</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <p class="form-label">地上停车位</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="GROUND_PARKING" name="GROUND_PARKING" value="${'0'==pd.GROUND_PARKING?'':pd.GROUND_PARKING }" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">个</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                         <div class="col-lg-6">
                                            <p class="form-label">地下停车位</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="UNDERGROUND_PARKING" name="UNDERGROUND_PARKING" value="${'0'==pd.UNDERGROUND_PARKING?'':pd.UNDERGROUND_PARKING }" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">个</span>
                                            </div>
                                        </div>
                                         <div class="col-lg-6">
                                            <p class="form-label">户数</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="HOUSE_NUM" name="HOUSE_NUM" value="${'0'==pd.HOUSE_NUM?'':pd.HOUSE_NUM }" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">户</span>
                                            </div>
                                        </div>
                                    </div>
                                  
                                    <div class="form-group">
                                        <label>海绵城市要求</label>
                                        <textarea id="SPONGE_REQUIRE" name="SPONGE_REQUIRE" class="form-control input-textarea">${pd.SPONGE_REQUIRE }</textarea>
                                    </div>
                               <!--  </form> -->
                            </div>
                        </div>
                        <!-- -----------建设工程规划许可（交通）----------------- -->
                         <div class="box box-success infoDiv unDisDiv" id=infoDiv5>
                            <div class="box-header with-border">
                                <h3 class="box-title text-green">发证信息</h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                                <!--   <form role="form" action="sphz/fzxx.do" name="fzxxForm" id="fzxxForm" method="post"> -->
                                    <div class="form-group">
                                        <label for="">发文编号</label>
                                        <input type="text" id="OPINIONS_NO" name="OPINIONS_NO" value="${pd.OPINIONS_NO }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">发文日期</label>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input type="text"  name="OPINIONS_DATE" value="${pd.OPINIONS_DATE }" class="form-control pull-right datepicker" id="datepicker">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="">管线类别</label>
                                       <!--  <input type="text" id="PIEP_CATEGORY" name="PIEP_CATEGORY" class="form-control"> -->
                                        <div class="box-select">
				                            <select id=PIEP_CATEGORY name="PIEP_CATEGORY" class="form-control select2" style="width: 100%;">
				                            	<option selected="selected" value="">请选择管线类别</option>
				                            	<c:forEach items="${pipeCategoryList}" var="item">
													<option value="${item.DICTIONARIES_ID }" <c:if test="${item.DICTIONARIES_ID == pd.PIEP_CATEGORY }">selected</c:if>>${item.NAME }</option>
												</c:forEach>
				                              <!--   <option selected="selected" value="1">建设项目选址意见书</option>
				                                <option value="2">建设项目规划条件变更</option>
				                                <option value="3">建设用地规划许可证</option>
				                                <option value="4">建设工程规划许可证（建筑）</option>
				                                <option value="5">建设工程规划许可证（市政交通）</option>
				                                <option value="6">建设工程竣工规划验收合格证</option> -->
				                            </select>
				                        </div>
                                    </div>
                                     
                                     <div class="form-group">
                                        <label for="">道路等级</label>
                                        <input type="text" id="ROAD_LEVEL" name="ROAD_LEVEL" value="${pd.ROAD_LEVEL }" class="form-control">
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">管线长度</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="PIEP_LENGTH" name="PIEP_LENGTH" value="${'0'==pd.PIEP_LENGTH?'':pd.PIEP_LENGTH }" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">km</span>
                                            </div>
                                        </div>
                                         <div class="col-lg-6">
                                            <p class="form-label">道路长度</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="ROAD_LENGTH" name="ROAD_LENGTH" value="${'0'==pd.ROAD_LENGTH?'':pd.ROAD_LENGTH }" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">km</span>
                                            </div>
                                        </div>
                                    </div>
                                   
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">折算面积</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="CONVERT_AREA" name="CONVERT_AREA" value="${'0'==pd.CONVERT_AREA?'':pd.CONVERT_AREA }" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">km²</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                           
                                        </div>
                                    </div>
                                   
                                    <div class="form-group">
                                        <label>海绵城市要求</label>
                                        <textarea id="SPONGE_REQUIRE" name="SPONGE_REQUIRE" class="form-control input-textarea">${pd.SPONGE_REQUIRE }</textarea>
                                    </div>
                               <!--  </form> -->
                            </div>
                        </div>
                        <!-- -----------建设工程竣工规划核实----------------- -->
                         <div class="box box-success infoDiv unDisDiv" id=infoDiv6>
                            <div class="box-header with-border">
                                <h3 class="box-title text-green">发证信息</h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                                <!--   <form role="form" action="sphz/fzxx.do" name="fzxxForm" id="fzxxForm" method="post"> -->
                                    <div class="form-group">
                                        <label for="">发文编号</label>
                                        <input type="text" id="OPINIONS_NO" name="OPINIONS_NO" value="${pd.OPINIONS_NO }" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">发文日期</label>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input type="text"  name="OPINIONS_DATE" value="${pd.OPINIONS_DATE }" class="form-control pull-right datepicker" id="datepicker">
                                        </div>
                                    </div>
                                   
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">总建筑面积</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="LAND_AREA" name="LAND_AREA" value="${'0'==pd.LAND_AREA?'':pd.LAND_AREA }" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">公顷</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                           
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">地上建筑面积</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="GROUND_AREA" name="GROUND_AREA" value="${'0'==pd.GROUND_AREA?'':pd.GROUND_AREA }" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">公顷</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <p class="form-label">地下建筑面积</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="UNDERGROUND_AREA" name="UNDERGROUND_AREA" value="${'0'==pd.UNDERGROUND_AREA?'':pd.UNDERGROUND_AREA }" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">公顷</span>
                                            </div>
                                        </div>
                                    </div>
                                  
                                    <div class="form-group">
                                        <label>海绵城市要求</label>
                                        <textarea id="SPONGE_REQUIRE" name="SPONGE_REQUIRE" class="form-control input-textarea">${pd.SPONGE_REQUIRE }</textarea>
                                    </div>
                               <!--  </form> -->
                            </div>
                        </div>
                        
                   		<!-- 发证信息结束 -->
                    </div>
                </div>
                </form>   
            </section>
       
        </div>
        <!-- /.content-wrapper -->

        <!-- Main Footer -->
        <footer class="main-footer">
        </footer>
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
    	console.log("${documents2MapList}");
    	console.log("${pd}");
    	//编辑信息初始化报建类型选择框和发证信息页面内容
    	var STAGE_TYPE="${pd.STAGE_TYPE}";
    	
    	if(null==STAGE_TYPE||''==STAGE_TYPE){
    	
    	}else{
    		var infoDivs=$(".infoDiv");
   			for(var i=0;i<infoDivs.length;i++){
   				infoDivs[i].style.display="none";
   				$(".infoDiv:eq("+i+")").addClass("unDisDiv");
   			}
   			var index=parseInt(STAGE_TYPE)-1;
   			infoDivs[index].style.display="block";
   			$(".infoDiv:eq("+(index)+")").removeClass("unDisDiv");
    	}
    	
    	
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
    			//数据校验  数字类型包含"_"后台会报错
  				
    			
    			if($("#MIX_RATIO").val().indexOf("_")!=-1){
					$("#MIX_RATIO").tips({
						side:3,
			            msg:'请按格式进行补全',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#MIX_RATIO").focus();
					return false;
				}
    			if($("#PLOT_RATIO").val().indexOf("_")!=-1){
					$("#PLOT_RATIO").tips({
						side:3,
			            msg:'请按格式进行补全',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#PLOT_RATIO").focus();
					return false;
				}
				if($("#GREEN_RATIO").val().indexOf("_")!=-1){
					$("#GREEN_RATIO").tips({
						side:3,
			            msg:'请按格式进行补全',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#GREEN_RATIO").focus();
					return false;
				}
				
				if($("#BUILDING_DENSITY").val().indexOf("_")!=-1){
					$("#BUILDING_DENSITY").tips({
						side:3,
			            msg:'请按格式进行补全',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#BUILDING_DENSITY").focus();
					return false;
				}
				$(".unDisDiv").remove();//删除display=none的发文信息Div
				var messageContent=[];
				var stageSelect= $("#stageSelect").val();
				//根据报建类型再区分哪些信息需要判断
				if(stageSelect=="1"||stageSelect=="2"||stageSelect=="3"){
					if($("#LAND_AREA").val()==""){
						messageContent.push("用地面积");
					}
				}
				
				/* if($("#MIX_RATIO").val()==""){
					messageContent.push("混合比例");
				}
				if($("#PLOT_RATIO").val()==""){
					messageContent.push("容积率");
				}
				if($("#CONTROL_HEIGHT").val()==""){
					messageContent.push("控制高度");
				}
				if($("#GREEN_RATIO").val()==""){
					messageContent.push("绿地率");
				}
				if($("#BUILDING_DENSITY").val()==""){
					messageContent.push("建筑密度");
				}
				if($("#PARKING").val()==""){
					messageContent.push("停车位数");
				} */
				
				
				if(messageContent.length>0){
				
					var layersubmit1= layer.confirm('您填报的内容缺少'+messageContent.join(',')+'信息，请及时到登记管理页面进行补充！', {
						  btn: ['确定','取消'] //按钮
						}, function(){
						  //调用ajax
						    var newUrl='sphz/${msg }.do?ACTION=0&STAGE='+$("#stageSelect").val()+"&PKID=${pd.PKID }&EDIT_TYPE=${EDIT_TYPE }";
						//	$(".unDisDiv").remove();//删除display=none的发文信息Div
							$("#addForm").attr('action',newUrl);
							layer.close(layersubmit1);
							var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
							$("#addForm").ajaxSubmit(function(data){
				    			//console.log(data);
				    			if(data.result=="success"){
				    				layer.msg('保存成功', {icon: 1});
				    				self.location.reload();
				    				//关闭父窗口弹窗
				    				if('${msg }'=='edit'){
					    				var index = parent.layer.getFrameIndex(window.name);
					    				parent.layer.close(index); 
					    				window.parent.location.reload();//刷新父页面
				    				}
				    				
				    			}
				    		});
						}, function(){
						  	
					});
					return false;
				}
    			
    			
    			var layersubmit= layer.confirm('保存成功，是否提交？', {
				  btn: ['提交','暂存'] //按钮
				}, function(){//提交
					var newUrl='sphz/${msg }.do?ACTION=1&STAGE='+$("#stageSelect").val()+"&PKID=${pd.PKID }&EDIT_TYPE=${EDIT_TYPE }";
					//$(".unDisDiv").remove();//删除display=none的发文信息Div
					$("#addForm").attr('action',newUrl);
					layer.close(layersubmit);
					var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
					$("#addForm").ajaxSubmit(function(data){
		    			//console.log(data);
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
				  
				}, function(){//暂存
					var newUrl='sphz/${msg }.do?ACTION=2&STAGE='+$("#stageSelect").val()+"&PKID=${pd.PKID }&EDIT_TYPE=${EDIT_TYPE }";
					//$(".unDisDiv").remove();//删除display=none的发文信息Div
					$("#addForm").attr('action',newUrl);
					layer.close(layersubmit);
					var index1 = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
					$("#addForm").ajaxSubmit(function(data){
		    			//console.log(data);
		    			if(data.result=="success"){
		    				layer.msg('暂存成功', {icon: 1});
		    				self.location.reload();
		    				//关闭父窗口弹窗
		    				if('${msg }'=='edit'){
			    				var index = parent.layer.getFrameIndex(window.name);
			    				parent.layer.close(index); 
			    				window.parent.location.reload();//刷新父页面
		    				}
		    			}
		    		});
				  
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