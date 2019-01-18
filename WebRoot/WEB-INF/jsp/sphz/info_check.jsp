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
    <!-- DataTables -->
    <link rel="stylesheet" href="plugins/xha/plugins/datatables.net-bs/css/dataTables.bootstrap.css">
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
    <link rel="stylesheet" href="plugins/xha/dist/css/pages/xxsh.css">
    <link rel="stylesheet" href="plugins/xha/plugins/layer/theme/default/layer.css">
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
            <section class="content-header ">
                <h1>
                    信息审核
                    <small></small>
                </h1>
            </section>

            <!-- Main content -->
            <section class="content container-fluid ">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="box-select-item">
                                    <h3 class="box-title text-blue box-select-title">报建类型&nbsp;&nbsp;&nbsp;&nbsp;</h3>
                                    <div class="box-mutiselect">
                                        <select class="form-control select2" onchange="changeCondition()" id="StageSelect" style="width: 100%;">
                                       		<option value="" selected="selected">全部</option>
                                            <option value="1">建设项目选址意见书</option>
                                            <option value="2">建设项目规划条件变更</option>
                                            <option value="3">建设用地规划许可证</option>
                                            <option value="4">建设工程规划许可证（建筑）</option>
                                            <option value="5">建设工程规划许可证（市政交通）</option>
                                            <option value="6">建设工程竣工规划验收合格证</option>
                                           
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="box-select-item">
                                    <h3 class="box-title text-blue box-select-title">承办部门&nbsp;&nbsp;&nbsp;&nbsp;</h3>
                                    <div class="box-mutiselect">
                                        <select class="form-control select2" onchange="changeCondition()" id="DepartmentSelect" style="width: 100%;">
                                         	<option selected="selected" value="">全部</option>
                                            <c:forEach items="${departmentList}" var="item">
													<option value="${item.NAME}">${item.NAME }</option>
											</c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                            <div class="box-header">
                                <h3 class="box-title">数据结果</h3>

                                <div class="box-tools pull-right">
                                    <div class="btn btn-box-tool">状态说明:</div>
                                    <div class="btn btn-box-tool text-yellow statusselect" mydata=3><i class="fa fa-circle"></i> 待审核</div>
                                    <div class="btn btn-box-tool text-green statusselect" mydata=4><i class="fa fa-circle"></i> 已通过</div>
                                    <div class="btn btn-box-tool text-red statusselect" mydata=5><i class="fa fa-circle"></i> 已驳回&nbsp;&nbsp;&nbsp;&nbsp;</div>
                                    <button type="button" class="btn btn-box-tool info-hidden" data-widget="showinfo"><i class="fa fa-search"></i></button>
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-search" id="xxsh-search" hidden>
                                <form class="form-horizontal">
                                    <div class="form-group">
                                        <label for="" class="col-sm-2 control-label">搜索</label>

                                        <div class="col-sm-10">
                                            <div class="has-feedback">
                                                <input type="text" class="form-control" id="" placeholder="请输入……">
                                                <span class="glyphicon glyphicon-search form-control-feedback"></span>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <!-- /.box-header -->
                            <div class="box-body">
                                <table id="example1" class="table table-bordered" style="width:100%">
                                    <thead>
                                        <tr>
                                            <th>序号</th>
                                            <th>项目编号</th>
                                            <th>项目名称</th>
                                            <th>承办部门</th>
                                            <th>提交日期</th>
                                            <th>发证日期</th>
                                            <th>报建类型</th>
                                            <th>操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                    </tbody>
                                    <!-- <tfoot>
                                        <th>序号</th>
                                        <th>项目编号</th>
                                        <th>项目名称</th>
                                        <th>承办部门</th>
                                        <th>提交日期</th>
                                        <th>发证日期</th>
                                        <th>操作</th>
                                    </tfoot> -->
                                </table>
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

    <!-- Custom js -->
    <script src="plugins/xha/dist/js/pages/xxsh_w.js "></script>
     <script src="plugins/xha/plugins/layer/layer.js"></script>
</body>

</html>