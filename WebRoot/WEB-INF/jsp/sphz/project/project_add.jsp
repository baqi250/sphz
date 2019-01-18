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
<%-- 		<section class="content-header">
			<h1>
				${title} <small> 请按照格式规范输入以下信息</small>
			</h1>

			<div class="breadcrumb">
				<button type="button" id="save"
					class="btn btn-block btn-primary btn-sm" data-widget="">
					<i class="fa fa-check"></i> 保存
				</button>
			</div>
		</section> --%>

		<!-- Main content -->
		<section class="content container-fluid">
			<form role="form" action="sphz/project/add.do" name="addForm" id="addForm"
				method="post" autocomplete="off">
				
				<input type="hidden" name="XMSD" id="XMSD_SELECT" value="${pd.XMSD }" />
				<input type="hidden" name="XMWYM" id="XMWYM" value="${pd.XMWYM }" />
				<!-- 项目信息 -->
				<div class="box box-warning">
					<!-- <div class="box-header with-border">
						<h3 class="box-title text-yellow">项目信息</h3>

						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool"
								data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>
						</div>
					</div> -->
					<div class="box-body">
						<div class="row">
							<div class="col-lg-6">
								<div class="form-group">
									<label for="">申请单位</label> <input type="text" id="APPLICANT"
										name="APPLICANT" value="${pd.APPLICANT }" class="form-control">
								</div>
							</div>
							<div class="col-lg-6">
								<div class="form-group">
									<label for="">项目名称</label> <input type="text" id="PROJECT_NAME"
										name="PROJECT_NAME" value="${pd.PROJECT_NAME }" class="form-control" placeholder="必填项（*）">
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-lg-6">
								<div class="form-group">
									<label for="">项目编码</label> <input type="text" id="PROJECT_CODE"
										name="PROJECT_CODE" value="${pd.PROJECT_CODE }" class="form-control">
								</div>
							</div>
							<div class="col-lg-6">
								<div class="form-group">
									<label for="">项目地址</label> <input type="text" id="LOCATION"
										name="LOCATION" value="${pd.LOCATION }" class="form-control">
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-lg-6">
								<div class="form-group">
									<label for="">项目类型</label>
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
							</div>
							<div class="col-lg-6">
								<div class="form-group">
									<label for="">建设类型</label>
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
							</div>
						</div>
						
						<div class="row">
							<div class="col-lg-6">
								<div class="form-group">
									<label for="">项目属地</label>
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
							</div>
							<div class="col-lg-6">
								<div class="form-group">
									<label for="">设计（勘察）单位</label> <input type="text" id="SJDW"
										name="SJDW" value="${pd.SJDW }" class="form-control">
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-lg-6">
								<div class="form-group">
									<label for="">是否重点项目</label> 
									 <div class="box-select">
										 <select id=IS_IMPORTANT name="IS_IMPORTANT" class="form-control select2" style="width: 100%;">
											 <option selected="selected" value=""></option>
											 <c:forEach items="${isImportantList}" var="item">
												 <option value="${item.NAME }"
												 	<c:if test="${item.NAME == pd.IS_IMPORTANT }">selected</c:if>>${item.NAME }
												 </option>
											 </c:forEach>
										 </select>
									</div>
								</div>
							</div>
							<div class="col-lg-6"></div>
						</div>
						
						<div class="row">
							<div class="col-lg-6">
								<div class="form-group">
									<label for="">项目规模</label> <input type="text" id="SCALE"
										name="SCALE" value="${pd.SCALE }" class="form-control" placeholder="可输入文字">
								</div>
							</div>
							<div class="col-lg-6">
								<p class="form-label">项目总投资</p>
								<div class="form-group input-group">
									<label for=""></label> <input type="text" id="INVESTMENT" name="INVESTMENT"
											value="${pd.INVESTMENT }" class="form-control"
											oninput="value=value.replace(/[^\-?\d.]/g,'')"><span class="input-group-addon  addon-w">万元</span>
									</div>
								</div>
							</div>
						</div>
						
					</div>
					<!-- box-body -->
					<section class="content-header">
						<div class="breadcrumb">
							<button type="button" id="save"
								class="btn btn-block btn-primary btn-sm" data-widget="">
								<i class="fa fa-check"></i> 保存
							</button>
						</div>
					</section>
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
		
    	$("#XMSD").val('请选择项目属地').trigger("change");
   		var DataDeal = {
	        //将从form中通过$('#refer').serialize()获取的值转成json
	        formToJson: function (data) {
	            data=data.replace(/&/g,"\",\"");
	            data=data.replace(/=/g,"\":\"");
	            data="{\""+data+"\"}";
	            return data;
	        }
	    };
		//保存按钮点击事件
   		$("#save").on("click",function(){
   			$("#XMSD_SELECT").val($("#XMSD").val());
   			
   			var namereg=/[`~!@:?"|#￥$%^&*+<>\\{}\/'[\]]/im;
   			if(namereg.test($("#PROJECT_NAME").val())){
   				layer.tips("存在特殊字符","#PROJECT_NAME",{
   					tips:[1,'#3595CC'],
   					time:3000
   				});
   				return false;
   			}
   			
   			if($("#PROJECT_NAME").val().trim()==""){
   				layer.tips("请填写项目名称","#PROJECT_NAME",{
   					tips:[1,'#3595CC'],
   					time:3000
   				});
   				return false;
			}
			
			$.ajax({
		     	type: "POST",
				url: 'sphz/project/checkHas.do',
		    	data: {PROJECT_NAME:$("#PROJECT_NAME").val()},
				dataType:'json',
				cache: false,
				success: function(data){
					if(data.result=="has"){
					   layer.tips("项目名称有重复","#PROJECT_NAME",{
		   					tips:[1,'#3595CC'],
		   					time:3000
		   				});
		   				return false;
					}else{
						$("#save").attr("disabled","disabled");
						
						layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
				
						$("#addForm").ajaxSubmit(function(data){
			    			if(data.result=="success"){
			    				layer.msg('提交成功', {icon: 1});
			    				//console.log(window.parent);
			    				var index = parent.layer.getFrameIndex(window.name);
			    				parent.layer.close(index); 
			    				
			    				var dataCollect=$('#addForm').serialize();
			                    dataCollect= decodeURIComponent(dataCollect,true);//防止中文乱码
			                    var jsondata=DataDeal.formToJson(dataCollect);//转化为json
			    				window.parent.a.returnData(jsondata);
			    			}
			    		});
					}
				},
		     	error:function(data){
				}
		     });
			
			
   		})
	  
    </script>

  
</body>

</html>