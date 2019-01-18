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
<html lang="zh-CN">

<head>
	<base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>蓝谷规划信息平台</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="plugins/bootstrap/dist/css/bootstrap.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="plugins/Ionicons/css/ionicons.min.css">
    <!-- bootstrap datepicker -->
    <link rel="stylesheet" href="plugins/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
    <!-- iCheck for checkboxes and radio inputs -->
    <link rel="stylesheet" href="plugins/iCheck/all.css">
    <!-- Select2 -->
    <link rel="stylesheet" href="plugins/select2/dist/css/select2.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="dist/css/AdminLTE.css">
    <!-- AdminLTE Skins. 色调更改 -->
    <link rel="stylesheet" href="dist/css/skins/skin-blue.min.css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="dist/css/pages/index.css">
</head>

<body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">

        <%@ include file="head.jsp"%>
        
        <%@ include file="left.jsp"%>

        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper">
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <h1>
                    信息登记
                    <small> 请按照格式规范输入以下信息</small>
                </h1>

                <div class="breadcrumb">
                    <button type="button" class="btn btn-block btn-primary btn-sm" data-widget=""><i class="fa fa-check"></i> 保存</button>
                </div>
            </section>

            <!-- Main content -->
            <section class="content container-fluid">
                <!-- 申报类型 -->
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title text-blue box-select-title">申报类型</h3>

                        <div class="box-select">
                            <select class="form-control select2" style="width: 100%;">
                                <option selected="selected">建设项目选址意见书</option>
                                <option>建设项目规划条件变更</option>
                                <option>建设用地规划许可证</option>
                                <option>建设工程规划许可证（建筑）</option>
                                <option>建设工程规划许可证（市政交通）</option>
                                <option>建设工程竣工规划验收合格证</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <!-- 报建信息 -->
                        <div class="box box-warning">
                            <div class="box-header with-border">
                                <h3 class="box-title text-yellow">报建信息</h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-warning btn-xs" data-widget=""><i class="fa fa-search"></i> 搜索</button>
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                                <form role="form">
                                    <div class="form-group">
                                        <label for="">项目编号</label>
                                        <input type="text" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">项目名称</label>
                                        <input type="text" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">建设位置</label>
                                        <input type="text" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">建设单位</label>
                                        <input type="text" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">组织机构代码</label>
                                        <input type="text" class="form-control">
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label for="">申请人姓名</label>
                                                <input type="text" class="form-control">
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label for="">申请人电话</label>
                                                <input type="text" class="form-control" data-inputmask="'mask': '999-9999-9999'">
                                            </div>
                                        </div>
                                    </div>
                                </form>
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
                                <form role="form">
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label for="">承办部门</label>
                                                <input type="text" class="form-control" disabled>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label for="">经办人</label>
                                                <input type="text" class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                </form>
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
                                <form role="form">
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">平面图纸</p>
                                            <div class="form-group input-group input-group-sm">
                                                <label for=""></label>
                                                <input type="text" class="form-control">
                                                <span class="input-group-btn">
                                                        <button type="button" class="btn btn-info btn-flat"><i class="fa fa-upload"></i></button>
                                                    </span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <p class="form-label">红线范围</p>
                                            <div class="form-group input-group input-group-sm">
                                                <label for=""></label>
                                                <input type="text" class="form-control">
                                                <span class="input-group-btn">
                                                        <button type="button" class="btn btn-info btn-flat"><i class="fa fa-upload"></i></button>
                                                    </span>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <!-- 发证信息 -->
                        <div class="box box-success">
                            <div class="box-header with-border">
                                <h3 class="box-title text-green">发证信息</h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                                <form role="form">
                                    <div class="form-group">
                                        <label for="">发文编号</label>
                                        <input type="text" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="">发文日期</label>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input type="text" class="form-control pull-right" id="datepicker">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="">用地性质名称</label>
                                        <input type="text" class="form-control">
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">用地面积</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon addon-w">公顷</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <p class="form-label">混合比例</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" class="form-control" data-inputmask="'mask': '0.99'">
                                                <span class="input-group-addon addon-w">.00</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">容积率</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" class="form-control" data-inputmask="'mask': '9.99'">
                                                <span class="input-group-addon">.00</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <p class="form-label">控制高度</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon">&nbsp;&nbsp;&nbsp;米</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">绿地率</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" class="form-control" data-inputmask="'mask': '99.99'">
                                                <span class="input-group-addon">&nbsp;&nbsp;%</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <p class="form-label">建筑密度</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" class="form-control" data-inputmask="'mask': '99.99'">
                                                <span class="input-group-addon">&nbsp;%</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <p class="form-label">停车位数</p>
                                            <div class="form-group input-group">
                                                <label for=""></label>
                                                <input type="text" class="form-control" data-inputmask="'alias': 'numeric','rightAlign': 'false'">
                                                <span class="input-group-addon">&nbsp;&nbsp;&nbsp;个</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">

                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label>海绵城市要求</label>
                                        <textarea class="form-control input-textarea"></textarea>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <!-- /.content-wrapper -->

        <!-- Main Footer -->
        <footer class="main-footer">
        </footer>
    </div>
    <!-- ./wrapper -->

    <!-- REQUIRED JS SCRIPTS -->

    <!-- jQuery 3 -->
    <script src="plugins/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap 3.3.7 -->
    <script src="plugins/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- Select2 -->
    <script src="plugins/select2/dist/js/select2.full.min.js"></script>
    <script src="plugins/select2/dist/js/i18n/zh-CN.js"></script>
    <!-- InputMask -->
    <script src="plugins/inputmask/dist/inputmask/inputmask.js"></script>
    <script src="plugins/inputmask/dist/inputmask/inputmask.extensions.js"></script>
    <script src="plugins/inputmask/dist/inputmask/inputmask.numeric.extensions.js"></script>
    <script src="plugins/inputmask/dist/inputmask/inputmask.date.extensions.js"></script>
    <script src="plugins/inputmask/dist/inputmask/inputmask.phone.extensions.js"></script>
    <script src="plugins/inputmask/dist/inputmask/jquery.inputmask.js"></script>
    <script src="plugins/inputmask/dist/inputmask/phone-codes/phone.js"></script>
    <script src="plugins/inputmask/dist/inputmask/phone-codes/phone-be.js"></script>
    <!-- bootstrap datepicker -->
    <script src="plugins/bootstrap-datepicker/dist/js/bootstrap-datepicker.js"></script>
    <!-- SlimScroll -->
    <script src="plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
    <!-- iCheck 1.0.1 -->
    <script src="plugins/iCheck/icheck.min.js"></script>
    <!-- FastClick -->
    <script src="plugins/fastclick/lib/fastclick.js"></script>
    <!-- AdminLTE App -->
    <script src="dist/js/adminlte.min.js"></script>

    <!-- Custom js -->
    <script src="dist/js/pages/index_g.js"></script>
    <script src="dist/js/pages/index_w.js"></script>
</body>

</html>