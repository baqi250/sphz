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
<!-- Theme style -->
<link rel="stylesheet" href="plugins/xha/dist/css/AdminLTE.css">
<!-- AdminLTE Skins. 色调更改 -->
<link rel="stylesheet" href="plugins/xha/dist/css/skins/skin-blue.min.css">

<!-- Custom CSS -->
<link rel="stylesheet" href="plugins/xha/dist/css/pages/index.css">
<link rel="stylesheet" href="plugins/xha/plugins/layer/theme/default/layer.css">

<!-- 树形下拉框start -->

  <!-- <script src="plugins/xha/plugins/jquery/dist/jquery.min.js"></script> -->
  <script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
  	<script type="text/javascript" src="plugins/selectZtree/selectTree.js"></script>
	<script type="text/javascript" src="plugins/selectZtree/framework.js"></script>
	<link rel="stylesheet" type="text/css" href="plugins/selectZtree/import_fh.css"/>
	<script type="text/javascript" src="plugins/selectZtree/ztree/ztree.js"></script>
	<link type="text/css" rel="stylesheet" href="plugins/selectZtree/ztree/ztree.css"></link>
   
<!-- 树形下拉框end -->

</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- <div id="container"> -->
	   <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper1">
        
        
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <h1>
                    信息登记
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
                                <option selected="selected" value="1">建设项目选址意见书</option>
                                <option value="2">建设项目规划条件变更</option>
                                <option value="3">建设用地规划许可证</option>
                                <option value="4">建设工程规划许可证（建筑）</option>
                                <option value="5">建设工程规划许可证（市政交通）</option>
                                <option value="6">建设工程竣工规划验收合格证</option>
                            </select>
                        </div>
                    </div>
                </div>
				<form role="form" action="sphz/add.do" name="addForm" id="addForm" method="post" enctype="multipart/form-data">
				<input type="hidden" name="LAND_USE" id="LAND_USE" />
				<!-- <form role="form" action="sphz/add.do" name="addForm" id="addForm" method="post"> -->
                <div class="row">
                    <div class="col-md-6">
                        <!-- 报建信息 -->
                        <div class="box box-warning">
                            <div class="box-header with-border">
                                <h3 class="box-title text-yellow">报建信息</h3>

                                <div class="box-tools pull-right">
                                    <button type="button" id="searchProj" class="btn btn-warning btn-xs" data-widget=""><i class="fa fa-search"></i> 搜索</button>
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                           	 	<!-- <form role="form" action="sphz/addProj.do" name="projForm" id="projForm" method="post"> -->
                                <!-- <form role="form" > -->
                                    <div class="form-group">
                                        <label for="">项目编号</label>
                                        <input type="text" id="PROJ_NO" name="PROJ_NO" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">项目名称</label>
                                        <input type="text" id="PROJ_NAME" name="PROJ_NAME" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">建设位置</label>
                                        <input type="text" id="PROJ_LOCATION" name="PROJ_LOCATION" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">建设单位</label>
                                        <input type="text" id="PROJ_UNIT" name="PROJ_UNIT" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">组织机构代码</label>
                                        <input type="text" id="ORG_CODE" name="ORG_CODE" class="form-control">
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label for="">申请人姓名</label>
                                                <input type="text" id="APPLICANT_NAME" name="APPLICANT_NAME" class="form-control">
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label for="">申请人电话</label>
                                                <input type="text" class="form-control" id="APPLICANT_PHONE" name="APPLICANT_PHONE" data-inputmask="'mask': '999-9999-9999'">
                                            </div>
                                        </div>
                                    </div>
                               <!--  </form> -->
                            </div>
                        </div>

                        <!-- 经办信息 -->
                        <div class="box box-danger">
                            <div class="box-header with-border">
                                <h3 class="box-title text-red">经办信息</h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                              <!--   <form role="form" action="sphz/addJbxx.do" name="jbxxForm" id="jbxxForm" method="post"> -->
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label for="">承办部门</label>
                                                <input type="text" name="DEPARTMENT" id="DEPARTMENT" value="${pd.DEPARTMENT }" class="form-control" disabled>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label for="">经办人</label>
                                                <input type="text" name="OPERATOR" id="OPERATOR" class="form-control">
                                            </div>
                                        </div>
                                    </div>
                              <!--   </form> -->
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
                                            <p class="form-label">平面图纸</p>
                                            <div class="form-group input-group input-group-sm">
                                                <label for=""></label>
                                                <input type="file" name="planFile" id="planFile" class="form-control">
                                                <span class="input-group-btn">
                                                        <button type="button" class="btn btn-info btn-flat"><i class="fa fa-upload"></i></button>
                                                    </span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <p class="form-label">红线范围</p>
                                            <div class="form-group input-group input-group-sm">
                                                <label for=""></label>
                                                <input type="file" name="redFile" id="redFile" class="form-control">
                                                <span class="input-group-btn">
                                                        <button type="button" class="btn btn-info btn-flat"><i class="fa fa-upload"></i></button>
                                                    </span>
                                            </div>
                                        </div>
                                    </div>
                               <!--  </form> -->
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <!-- 发证信息 -->
                        <!-- ---             选址意见书                    --- -->
                        <div class="box box-success infoDiv" id=infoDiv1 style="display: block;">
                            <div class="box-header with-border">
                                <h3 class="box-title text-green">发证信息1</h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                                <!--   <form role="form" action="sphz/fzxx.do" name="fzxxForm" id="fzxxForm" method="post"> -->
                                    <div class="form-group">
                                        <label for="">发文编号</label>
                                        <input type="text" id="OPINIONS_NO" name="OPINIONS_NO" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">发文日期</label>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input type="text"  name="OPINIONS_DATE" class="form-control pull-right datepicker" id="datepicker">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="">用地性质名称</label>
										<!-- <input type="hidden" name="LAND_USE" id="LAND_USE" /> -->
										<div class="selectTree" id="selectTree"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">用地面积</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="LAND_AREA" name="LAND_AREA" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">公顷</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <p class="form-label">混合比例</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="MIX_RATIO" name="MIX_RATIO" class="form-control" data-inputmask="'mask': '0.99'">
                                                <span class="input-group-addon addon-w">.00</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">容积率</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="PLOT_RATIO" name="PLOT_RATIO" class="form-control" data-inputmask="'mask': '9.99'">
                                                <span class="input-group-addon">.00</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <p class="form-label">控制高度</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="CONTROL_HEIGHT" name="CONTROL_HEIGHT" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon">&nbsp;&nbsp;&nbsp;米</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">绿地率</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="GREEN_RATIO" name="GREEN_RATIO" class="form-control" data-inputmask="'mask': '99.99'">
                                                <span class="input-group-addon">&nbsp;&nbsp;%</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <p class="form-label">建筑密度</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="BUILDING_DENSITY" name="BUILDING_DENSITY" class="form-control" data-inputmask="'mask': '99.99'">
                                                <span class="input-group-addon">&nbsp;%</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">停车位数</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="PARKING" name="PARKING" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon">&nbsp;&nbsp;&nbsp;个</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">

                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label>海绵城市要求</label>
                                        <textarea id="SPONGE_REQUIRE" name="SPONGE_REQUIRE" class="form-control input-textarea"></textarea>
                                    </div>
                               <!--  </form> -->
                            </div>
                        </div>
                        
                        <!-- ---              规划条件变更                         ---- -->
                         <div class="box box-success infoDiv unDisDiv" id=infoDiv2 >
                            <div class="box-header with-border">
                                <h3 class="box-title text-green">发证信息2</h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                                <!--   <form role="form" action="sphz/fzxx.do" name="fzxxForm" id="fzxxForm" method="post"> -->
                                    <div class="form-group">
                                        <label for="">发文编号</label>
                                        <input type="text" id="OPINIONS_NO" name="OPINIONS_NO" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">发文日期</label>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input type="text"  name="OPINIONS_DATE" class="form-control pull-right datepicker" id="datepicker">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                       <!--  <label for="">用地性质名称</label>
                                        <input type="text" id="LAND_USE" name="LAND_USE" class="form-control"> -->
                                        <label for="">用地性质名称</label>
										<!-- <input type="hidden" name="LAND_USE" id="LAND_USE" /> -->
										<div class="selectTree" id="selectTree1"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">用地面积</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="LAND_AREA" name="LAND_AREA" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">公顷</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <p class="form-label">混合比例</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="MIX_RATIO" name="MIX_RATIO" class="form-control" data-inputmask="'mask': '0.99'">
                                                <span class="input-group-addon addon-w">.00</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">容积率</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="PLOT_RATIO" name="PLOT_RATIO" class="form-control" data-inputmask="'mask': '9.99'">
                                                <span class="input-group-addon">.00</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <p class="form-label">控制高度</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="CONTROL_HEIGHT" name="CONTROL_HEIGHT" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon">&nbsp;&nbsp;&nbsp;米</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">绿地率</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="GREEN_RATIO" name="GREEN_RATIO" class="form-control" data-inputmask="'mask': '99.99'">
                                                <span class="input-group-addon">&nbsp;&nbsp;%</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <p class="form-label">建筑密度</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="BUILDING_DENSITY" name="BUILDING_DENSITY" class="form-control" data-inputmask="'mask': '99.99'">
                                                <span class="input-group-addon">&nbsp;%</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">停车位数</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="PARKING" name="PARKING" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon">&nbsp;&nbsp;&nbsp;个</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">

                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label>海绵城市要求</label>
                                        <textarea id="SPONGE_REQUIRE" name="SPONGE_REQUIRE" class="form-control input-textarea"></textarea>
                                    </div>
                               <!--  </form> -->
                            </div>
                        </div>
                        
                        <!-- -----------建设用地规划许可----------------- -->
                         <div class="box box-success infoDiv unDisDiv" id=infoDiv3>
                            <div class="box-header with-border">
                                <h3 class="box-title text-green">发证信息3</h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                                <!--   <form role="form" action="sphz/fzxx.do" name="fzxxForm" id="fzxxForm" method="post"> -->
                                    <div class="form-group">
                                        <label for="">发文编号</label>
                                        <input type="text" id="OPINIONS_NO" name="OPINIONS_NO" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">发文日期</label>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input type="text"  name="OPINIONS_DATE" class="form-control pull-right datepicker" id="datepicker">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <!-- <label for="">用地性质名称</label>
                                        <input type="text" id="LAND_USE" name="LAND_USE" class="form-control"> -->
                                        <label for="">用地性质名称</label>
										<!-- <input type="hidden" name="LAND_USE" id="LAND_USE" /> -->
										<div class="selectTree" id="selectTree2"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">用地面积</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="LAND_AREA" name="LAND_AREA" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">公顷</span>
                                            </div>
                                        </div>
                                       <div class="col-lg-6"></div>
                                    </div>
                                    <div class="form-group">
                                        <label>海绵城市要求</label>
                                        <textarea id="SPONGE_REQUIRE" name="SPONGE_REQUIRE" class="form-control input-textarea"></textarea>
                                    </div>
                               <!--  </form> -->
                            </div>
                        </div>
                        <!-- -----------建设工程规划许可（建筑）----------------- -->
                        
                         <div class="box box-success infoDiv unDisDiv" id=infoDiv4>
                            <div class="box-header with-border">
                                <h3 class="box-title text-green">发证信息4</h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                                <!--   <form role="form" action="sphz/fzxx.do" name="fzxxForm" id="fzxxForm" method="post"> -->
                                    <div class="form-group">
                                        <label for="">发文编号</label>
                                        <input type="text" id="OPINIONS_NO" name="OPINIONS_NO" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">发文日期</label>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input type="text"  name="OPINIONS_DATE" class="form-control pull-right datepicker" id="datepicker">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="">建筑物使用性质</label>
                                        <input type="text" id="BUILDING_PROPERTY" name="BUILDING_PROPERTY" class="form-control">
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">总建筑面积</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="BUILDING_AREA" name="BUILDING_AREA" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">公顷</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <p class="form-label">地上建筑面积</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="GROUND_AREA" name="GROUND_AREA" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">公顷</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">地下建筑面积</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="UNDERGROUND_AREA" name="UNDERGROUND_AREA" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">公顷</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <p class="form-label">地上停车位</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="GROUND_PARKING" name="GROUND_PARKING" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">个</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                         <div class="col-lg-6">
                                            <p class="form-label">地下停车位</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="UNDERGROUND_PARKING" name="UNDERGROUND_PARKING" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">个</span>
                                            </div>
                                        </div>
                                         <div class="col-lg-6">
                                            <p class="form-label">户数</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="HOUSE_NUM" name="HOUSE_NUM" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">户</span>
                                            </div>
                                        </div>
                                    </div>
                                  
                                    <div class="form-group">
                                        <label>海绵城市要求</label>
                                        <textarea id="SPONGE_REQUIRE" name="SPONGE_REQUIRE" class="form-control input-textarea"></textarea>
                                    </div>
                               <!--  </form> -->
                            </div>
                        </div>
                        <!-- -----------建设工程规划许可（交通）----------------- -->
                         <div class="box box-success infoDiv unDisDiv" id=infoDiv5>
                            <div class="box-header with-border">
                                <h3 class="box-title text-green">发证信息5</h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                                <!--   <form role="form" action="sphz/fzxx.do" name="fzxxForm" id="fzxxForm" method="post"> -->
                                    <div class="form-group">
                                        <label for="">发文编号</label>
                                        <input type="text" id="OPINIONS_NO" name="OPINIONS_NO" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">发文日期</label>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input type="text"  name="OPINIONS_DATE" class="form-control pull-right datepicker" id="datepicker">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="">管线类别</label>
                                       <!--  <input type="text" id="PIEP_CATEGORY" name="PIEP_CATEGORY" class="form-control"> -->
                                        <div class="box-select">
				                            <select id=PIEP_CATEGORY name="PIEP_CATEGORY" class="form-control select2" style="width: 100%;">
				                            	<option selected="selected" value="">请选择管线类别</option>
				                            	<c:forEach items="${pipeCategoryList}" var="item">
													<option value="${item.DICTIONARIES_ID }">${item.NAME }</option>
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
                                        <input type="text" id="ROAD_LEVEL" name="ROAD_LEVEL" class="form-control">
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">管线长度</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="PIEP_LENGTH" name="PIEP_LENGTH" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">km</span>
                                            </div>
                                        </div>
                                         <div class="col-lg-6">
                                            <p class="form-label">道路长度</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="ROAD_LENGTH" name="ROAD_LENGTH" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">km</span>
                                            </div>
                                        </div>
                                    </div>
                                   
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">折算面积</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="CONVERT_AREA" name="CONVERT_AREA" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">km²</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                           
                                        </div>
                                    </div>
                                   
                                    <div class="form-group">
                                        <label>海绵城市要求</label>
                                        <textarea id="SPONGE_REQUIRE" name="SPONGE_REQUIRE" class="form-control input-textarea"></textarea>
                                    </div>
                               <!--  </form> -->
                            </div>
                        </div>
                        <!-- -----------建设工程竣工规划核实----------------- -->
                         <div class="box box-success infoDiv unDisDiv" id=infoDiv6>
                            <div class="box-header with-border">
                                <h3 class="box-title text-green">发证信息6</h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                                <!--   <form role="form" action="sphz/fzxx.do" name="fzxxForm" id="fzxxForm" method="post"> -->
                                    <div class="form-group">
                                        <label for="">发文编号</label>
                                        <input type="text" id="OPINIONS_NO" name="OPINIONS_NO" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">发文日期</label>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input type="text"  name="OPINIONS_DATE" class="form-control pull-right datepicker" id="datepicker">
                                        </div>
                                    </div>
                                   
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">总建筑面积</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="LAND_AREA" name="LAND_AREA" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
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
                                                <input type="text" id="GROUND_AREA" name="GROUND_AREA" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">公顷</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <p class="form-label">地下建筑面积</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" id="UNDERGROUND_AREA" name="UNDERGROUND_AREA" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">公顷</span>
                                            </div>
                                        </div>
                                    </div>
                                  
                                    <div class="form-group">
                                        <label>海绵城市要求</label>
                                        <textarea id="SPONGE_REQUIRE" name="SPONGE_REQUIRE" class="form-control input-textarea"></textarea>
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
    
    <!-- Bootstrap 3.3.7 -->
    <script src="plugins/xha/plugins/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- Select2 -->
    <script src="plugins/xha/plugins/select2/dist/js/select2.full.min.js"></script>
    <script src="plugins/xha/plugins/select2/dist/js/i18n/zh-CN.js"></script>
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
    	//var stage=1;
    	$(function(){
    		$("#save").on("click",function(){
    			layer.confirm('保存成功，是否提交？', {
				  btn: ['提交','暂存'] //按钮
				}, function(){
					var newUrl='sphz/add.do?ACTION=1&STAGE='+$("#stageSelect").val();
					$(".unDisDiv").remove();//删除display=none的发文信息Div
					$("#addForm").attr('action',newUrl);
					$("#addForm").ajaxSubmit(function(data){
		    			console.log(data);
		    			if(data.result=="success"){
		    				layer.msg('提交成功', {icon: 1});
		    				self.location.reload();
		    			}
		    		});
				  
				}, function(){
					var newUrl='sphz/add.do?ACTION=2&STAGE='+$("#stageSelect").val();
					$(".unDisDiv").remove();//删除display=none的发文信息Div
					$("#addForm").attr('action',newUrl);
					$("#addForm").ajaxSubmit(function(data){
		    			console.log(data);
		    			if(data.result=="success"){
		    				layer.msg('暂存成功', {icon: 1});
		    				self.location.reload();
		    			}
		    		});
				  
				});
				
	    		/* $("#addForm").ajaxSubmit(function(data){
	    			console.log(data);
	    			if(data.result=="success"){
	    				self.location.reload();
	    			}
	    		}); */
    			//$("#addForm").submit();
    			//self.location.reload();
    		})
    		
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
								url: '<%=basePath%>sphz/getProjById.do',
						    	data: {ID:codesStr},
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
    	
    	function changeStage(stage){
   			
   			var stageIndex= $("#stageSelect").val();
   			var infoDivs=$(".infoDiv");
   			for(var i=0;i<infoDivs.length;i++){
   				infoDivs[i].style.display="none";
   				$(".infoDiv:eq("+i+")").addClass("unDisDiv");
   			}
   			infoDivs[stageIndex-1].style.display="block";
   			$(".infoDiv:eq("+(stageIndex-1)+")").removeClass("unDisDiv");
   			//stage=stage;
   			//console.log();
   			/* console.log(s);
   			alert(stage); */
   		}
   		
   			//下拉树
		var defaultNodes = {"treeNodes":${zTreeNodes}};
		//console.log(defaultNodes);
		function initComplete(){
			//alert(123);
			//绑定change事件
			$("#selectTree").bind("change",function(){
				if(!$(this).attr("relValue")){
			      //  top.Dialog.alert("没有选择节点");
			    }else{
					//alert("选中节点文本："+$(this).attr("relText")+"<br/>选中节点值："+$(this).attr("relValue"));
					$("#LAND_USE").val($(this).attr("relValue"));
					console.log($("#LAND_USE").val());
			    }
			});
			$("#selectTree1").bind("change",function(){
				if(!$(this).attr("relValue")){
			      //  top.Dialog.alert("没有选择节点");
			    }else{
					//alert("选中节点文本："+$(this).attr("relText")+"<br/>选中节点值："+$(this).attr("relValue"));
					$("#LAND_USE").val($(this).attr("relValue"));
					console.log($("#LAND_USE").val());
			    }
			});
			$("#selectTree2").bind("change",function(){
				if(!$(this).attr("relValue")){
			      //  top.Dialog.alert("没有选择节点");
			    }else{
					//alert("选中节点文本："+$(this).attr("relText")+"<br/>选中节点值："+$(this).attr("relValue"));
					$("#LAND_USE").val($(this).attr("relValue"));
					console.log($("#LAND_USE").val());
			    }
			});
			//赋给data属性
			$("#selectTree").data("data",defaultNodes);  
			$("#selectTree").render();
			$("#selectTree1").data("data",defaultNodes);  
			$("#selectTree1").render();
			$("#selectTree2").data("data",defaultNodes);  
			$("#selectTree2").render();
			//$("#selectTree2_input").val("${null==depname?'请选择':depname}");
		}
    </script>

  
</body>

</html>