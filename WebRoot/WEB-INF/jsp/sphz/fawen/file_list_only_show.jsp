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
    <link rel="stylesheet" href="plugins/xha/dist/css/pages/demo.css">
    <link rel="stylesheet" href="plugins/xha/plugins/layer/theme/default/layer.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>

<body class="hold-transition skin-blue sidebar-mini">
	<div id="typeDiv" style="display:none;">
		<div class="radio_box" id="radio01">
			<!--隐藏表单 radioVal 的值即为选中项li的id值-->
			<input type="hidden" value="" class="radioVal" name="" />
			<ul>
				<li id="BA" class="selected">建设单位提报方案</li>
				<li id="BS">建设单位提报申请</li>
				<li id="GF">规划局发文</li>
				<li id="LZ">局内流转文件</li>
				<li id="ZW">其他政府文件</li>
				<li id="ZX">第三方咨询文件</li>
			</ul>
		</div>
	</div>
        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper1 ">
            <!-- Main content -->
            <section class="content container-fluid ">
             <!--    <div class="box box-primary">
                    <div class="box-header with-border">
                        <div class="row">
                       	 	<div class="col-sm-4">
                           		<div class="box-select-item">
                                    <h3 class="box-title text-blue box-select-title">文件分类&nbsp;&nbsp;&nbsp;&nbsp;</h3>
                                    <div class="box-mutiselect">
                                        <select class="form-control select2" onchange="changeCondition()" id="typeSelect" style="width: 100%;">
                                        	<option selected="selected" value="">全部</option>
											<option value="BA">建设单位提报方案</option>
											<option value="BS">建设单位提报申请</option>
											<option value="GF">规划局发文</option>
											<option value="LZ">局内流转文件</option>
											<option value="ZW">其他政府文件</option>
											<option value="ZX">第三方咨询文件</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4">
                            	 <div class="box-select-item">
                                    <h3 class="box-title text-blue box-select-title">关键字&nbsp;&nbsp;&nbsp;&nbsp;</h3>
                                    <div class="box-mutiselect">
                                        <input type="text" id="keyword" class="form-control">
                                    </div>
                                </div>
                            </div>
                           	<div class="col-sm-1">
                            	 <button type="button" id="search"  class="btn btn-block btn-primary btn-sm" data-widget="" style="width:50px;"> 查询</button>
                            </div>
                        </div>
                    </div>
                </div> -->

                <div class="row">
                    <div class="col-xs-12">
						<table id="table_project" class="table table-bordered" style="width: 100%">
							<thead>
								<tr>
									<th>文件名称</th>
									<!-- <th>文件分类</th> -->
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
                </div>
           		
           		
            </section>
            <!--  <div>
            	<button class="btn btn-block btn-primary btn-success" onclick="add();" style="width:100px; margin:10px;">新增</button>
            </div> -->
        </div>
        <!-- /.content-wrapper -->

        <!-- Main Footer -->
       <!--  <footer class="main-footer ">
        </footer> -->



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
    <script src="plugins/xha/plugins/datatables.net/js/fnReloadAjax.js"></script>
    <!-- SlimScroll -->
    <script src="plugins/xha/plugins/jquery-slimscroll/jquery.slimscroll.min.js "></script>
    <!-- iCheck 1.0.1 -->
    <script src="plugins/xha/plugins/iCheck/icheck.min.js "></script>
    <!-- FastClick -->
    <script src="plugins/xha/plugins/fastclick/lib/fastclick.js "></script>
    <!-- AdminLTE App -->
    <script src="plugins/xha/dist/js/adminlte.min.js "></script>

    <!-- Custom js -->
    <script src="plugins/xha/dist/js/pages/files_only_show.js "></script>
     <script src="plugins/xha/plugins/layer/layer.js"></script>
     <script type="text/javascript">
     	var businessId="${businessId}";
     </script>
</body>

</html>