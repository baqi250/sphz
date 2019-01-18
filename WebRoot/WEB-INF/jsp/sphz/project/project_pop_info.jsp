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
<link rel="stylesheet" href="plugins/xha/plugins/layer/theme/default/layer.css">
<link rel="stylesheet" href="plugins/xha/dist/css/pages/project_pop_info.css">

</head>
<body class="hold-transition skin-blue sidebar-mini">

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper1">

		<!-- Main content -->
		<section class="content container-fluid">
				<!-- 项目信息 -->
				<div class="box box-warning">
					
					<div class="box-body">
						<table class="table table-condensed table-child">
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">项目名称</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="PROJECT_NAME" readonly="readonly" class="form-control">
									</div>	
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">项目地址</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="LOCATION" readonly="readonly"  class="form-control">
									</div>	
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">申请单位</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="APPLICANT" readonly="readonly"  class="form-control">
									</div>	
								</td>
							</tr>
							<tr>
								<td class="column_name">
									<h6 class="box-title text-blue box-select-title">所属区划</h6>
								</td>
								<td>
									<div class="form-group">
										<input type="text" id="XMSD" readonly="readonly"  class="form-control">
									</div>	
								</td>
							</tr>
						</table>

					</div>
					<!-- box-body -->
					<section class="content-header">
						<div class="breadcrumb">
							<button type="button" id="infoBtn"
								class="btn btn-block btn-primary btn-sm" data-widget="">
								<i class="fa fa-check"></i>详情
							</button>
						</div>
					</section>
				</div>
				<!-- box box-warning -->
			</form>
		</section>

	</div>
	<!-- /.content-wrapper -->


  	<script src="plugins/xha/plugins/jquery/dist/jquery.min.js"></script>
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
    	var xmwym= "${pd.xmwym}";
   		$.ajax({
            type: "POST",
            url: "sphz/project/getXiangmuByXmwym",
            data: {
                "XMWYM": xmwym
            },
            dataType: "json",
            success: function(response) {
            	if(response.result=="success"){
            		var xiangmu= response.xiangmu;
            		$("#PROJECT_NAME").val(xiangmu.PROJECT_NAME);
            		$("#LOCATION").val(xiangmu.LOCATION);
            		$("#APPLICANT").val(xiangmu.APPLICANT);
            		$("#XMSD").val(xiangmu.XMSD);
            	}else{
            		alert(response.result);
            	}  
            },
            error: function() {
                alert("服务器异常");
            }
        });
        
       	$(document).on('click', '#infoBtn', function() {
		    window.parent.location='sphz/project/goSpatialDetail?xmwym='+xmwym;
		}); 
	  
    </script>

  
</body>

</html>