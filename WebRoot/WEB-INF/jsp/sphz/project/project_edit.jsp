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


  


</head>
<body class="hold-transition skin-blue sidebar-mini">

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper1">


		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				${title} <small> 请按照格式规范输入以下信息</small>
			</h1>

			<div class="breadcrumb">
				<button type="button" id="save"
					class="btn btn-block btn-primary btn-sm" data-widget="">
					<i class="fa fa-check"></i> 保存
				</button>
			</div>
		</section>

		<!-- Main content -->
		<section class="content container-fluid">
			<form role="form" action="sphz/${msg}.do" name="addForm" id="addForm"
				method="post" enctype="multipart/form-data">
				<input type="hidden" name="XIANGMU_ID" value="${pd.XIANGMU_ID}">
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
									<label for="">建设位置</label> <input type="text" id="JSWZ"
										name="JSWZ" value="${pd.JSWZ }" class="form-control">
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
									<label for="">所属区划</label> <input type="text" id="SSQH"
										name="SSQH" value="${pd.SSQH }" class="form-control">
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
									<label for="">建筑物使用性质</label> <input type="text" id=JZWSYXZ
										name="JZWSYXZ" value="${pd.JZWSYXZ }" class="form-control">
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-lg-6">
								<div class="form-group">
									<label for="">是否混合用地</label> <input type="text" id="SFHHYD"
										name="SFHHYD" value="${pd.SFHHYD }" class="form-control">
								</div>
							</div>
							<div class="col-lg-6">
								<div class="form-group">
									<label for="">混合比例</label> <input type="text" id="HHBL"
										name="HHBL" value="${pd.HHBL }" class="form-control">
								</div>
							</div>
						</div>


						<div class="row">
							<div class="col-lg-6">
								<p class="form-label">红线范围</p>
								<div class="form-group input-group input-group-sm">
									<label for=""></label> <input type="text" class="form-control"
										id="hx-upload-text" value="${pd.redFileName }"
										readonly="readonly"> <input type="file"
										class="form-control" name="redFile" id="redFile"
										style="display: none;"> <span class="input-group-btn">
										<button type="button" class="btn btn-info btn-flat"
											id="hx-upload-btn">
											<i class="fa fa-upload"></i>
										</button>
									</span>
								</div>
							</div>
							<div class="col-lg-6">
								<div class="form-group">
									<label>备注</label>
									<textarea id="BZ" name="BZ" class="form-control input-textarea">${pd.BZ }</textarea>
								</div>
							</div>
						</div>

					</div>
					<!-- box-body -->
				</div>
				<!-- box box-warning -->
			</form>
		</section>

	</div>
	<!-- /.content-wrapper -->

        <!-- Main Footer -->
        <footer class="main-footer">
        </footer>

        <!-- jQuery 3 -->
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
    	
    	$(function(){
    		/* $.ajax({//土地利用类型树状下拉选择框
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
					    $("#PROJ_GHYDXZ").val($("#select-tree").val());
					});
					 //编辑信息时带出name
					$("#select2-select-tree-container").html("${null==pd.PROJ_GHYDXZ_NAME||''==pd.PROJ_GHYDXZ_NAME?'':pd.PROJ_GHYDXZ_NAME}");
				},
				error:function(data){
					console.log(data);
					alert(error);
				}
			}); */
    	
    		
			//保存按钮点击事件
    		$("#save").on("click",function(){
    			var newUrl='sphz/project/${msg }.do';
				$("#addForm").attr('action',newUrl);
    			var layersubmit= layer.confirm('保存成功，是否提交？', {
				  btn: ['提交','取消'] //按钮
				}, function(){//提交
					layer.close(layersubmit);
					var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
					$("#addForm").ajaxSubmit(function(data){
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
	    })
		    
    </script>

  
</body>

</html>