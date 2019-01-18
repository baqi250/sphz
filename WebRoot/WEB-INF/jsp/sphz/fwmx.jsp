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
    <link rel="stylesheet" href="plugins/xha/dist/css/pages/fwmx.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>

<body class="hold-transition skin-blue sidebar-mini">
  
      

        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper1 ">
            <!-- Content Header (Page header) -->
             <section class="content-header">
                <h1>
                    发文明细
                    <small> 统计图表</small>
                </h1>
            </section>

            <!-- Main content -->
            <section class="content container-fluid">
                <div class="row">
                    <div class="col-md-6">
                        <!-- 图1 -->
                        <div class="box box-primary box-charts">
                            <div class="box-header with-border">
                                <h3 class="box-title text-blue">规划审批证书核发统计</h3>

                                <div class="box-tools pull-right">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-primary btn-xs" id="regions-btn">全部</button>
                                        <button type="button" class="btn btn-primary btn-xs dropdown-toggle" data-toggle="dropdown">
                                            <span class="caret"></span>
                                            <span class="sr-only">Toggle Dropdown</span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu" id="cbbmMenu">
                                        </ul>
                                    </div>
                                    <button type="button" class="btn btn-box-tool info-hidden" data-widget="showinfo" id="box-1"><i class="fa fa-info-circle"></i></button>
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                                <div class="box-text" id="box-1-text" hidden>
                                  
                                </div>

                                <div class="box-chart" id="chart-1"></div>
                            </div>
                        </div>

                        <!-- 图3 -->
                        <div class="box box-danger box-charts">
                            <div class="box-header with-border">
                                <h3 class="box-title text-red">近6月各月核发情况对比</h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool info-hidden" data-widget="showinfo" id="box-3"><i class="fa fa-info-circle"></i></button>
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                                <div class="box-text" id="box-3-text" hidden>
                                   
                                </div>

                                <div class="box-chart" id="chart-3"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <!-- 图2 -->
                        <div class="box box-warning box-charts">
                            <div class="box-header with-border">
                                <h3 class="box-title text-yellow">规划审批证书核发同期对比</h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool info-hidden" data-widget="showinfo" id="box-2"><i class="fa fa-info-circle"></i></button>
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                                <div class="box-text" id="box-2-text" hidden></div>

                                <div class="box-chart" id="chart-2"></div>
                            </div>
                        </div>
                        <!-- 图4 -->
                        <div class="box box-success box-charts">
                            <div class="box-header with-border">
                                <h3 class="box-title text-green">近6月各月核发情况同期对比</h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool info-hidden" data-widget="showinfo" id="box-4"><i class="fa fa-info-circle"></i></button>
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                                <div class="box-text" id="box-4-text" hidden>
                                    
                                </div>

                                <div class="box-chart" id="chart-4"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <!-- /.content-wrapper -->

        <!-- Main Footer -->
        <footer class="main-footer ">
        </footer>



    <!-- REQUIRED JS SCRIPTS -->

    <!-- jQuery 3 -->
    <script src="plugins/xha/plugins/jquery/dist/jquery.min.js "></script>
    <!-- Bootstrap 3.3.7 -->
    <script src="plugins/xha/plugins/bootstrap/dist/js/bootstrap.min.js "></script>
    <!-- Select2 -->
    <script src="plugins/xha/plugins/select2/dist/js/select2.full.min.js "></script>
    <script src="plugins/xha/plugins/select2/dist/js/i18n/zh-CN.js "></script>
    <!-- InputMask -->
    <script src="plugins/xha/plugins/inputmask/dist/inputmask/inputmask.js "></script>
    <script src="plugins/xha/plugins/inputmask/dist/inputmask/inputmask.extensions.js "></script>
    <script src="plugins/xha/plugins/inputmask/dist/inputmask/inputmask.numeric.extensions.js "></script>
    <script src="plugins/xha/plugins/inputmask/dist/inputmask/inputmask.date.extensions.js "></script>
    <script src="plugins/xha/plugins/inputmask/dist/inputmask/inputmask.phone.extensions.js "></script>
    <script src="plugins/xha/plugins/inputmask/dist/inputmask/jquery.inputmask.js "></script>
    <script src="plugins/xha/plugins/inputmask/dist/inputmask/phone-codes/phone.js "></script>
    <script src="plugins/xha/plugins/inputmask/dist/inputmask/phone-codes/phone-be.js "></script>
    <!-- bootstrap datepicker -->
    <script src="plugins/xha/plugins/bootstrap-datepicker/dist/js/bootstrap-datepicker.js "></script>
    <!-- DataTables -->
    <script src="plugins/xha/plugins/datatables.net/js/jquery.dataTables.js"></script>
    <script src="plugins/xha/plugins/datatables.net-bs/js/dataTables.bootstrap.js"></script>
    <!-- SlimScroll -->
    <script src="plugins/xha/plugins/jquery-slimscroll/jquery.slimscroll.min.js "></script>
    <!-- iCheck 1.0.1 -->
    <script src="plugins/xha/plugins/iCheck/icheck.min.js "></script>
    <!-- FastClick -->
    <script src="plugins/xha/plugins/fastclick/lib/fastclick.js "></script>
    <!-- AdminLTE App -->
    <script src="plugins/xha/dist/js/adminlte.min.js "></script>
  	<!-- Echarts -->
    <script src="plugins/xha/plugins/Echarts/echarts.js"></script>
    <!-- Custom js -->
    <script src="plugins/xha/dist/js/changediv.js "></script>
    <script src="plugins/xha/dist/js/pages/fwmx_w1.js"></script>
    <script src="plugins/xha/dist/js/pages/fwmx_w2.js"></script>
    <script src="plugins/xha/dist/js/pages/fwmx_w3.js"></script>
    <script src="plugins/xha/dist/js/pages/fwmx_w4.js"></script>
    <script type="text/javascript">
	  
    </script>
</body>

</html>