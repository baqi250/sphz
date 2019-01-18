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
    <link rel="stylesheet" href="plugins/xha/dist/css/pages/xiangmu.css">
    <link rel="stylesheet" href="plugins/xha/dist/css/pages/attachmentlist.css">
    <link rel="stylesheet" href="plugins/xha/plugins/layer/theme/default/layer.css">
    <link rel="stylesheet" href="plugins/xha/dist/css/pages/projectlist.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>

<body class="hold-transition skin-blue sidebar-mini">
  
      
		<input type="hidden" value="${pd.XMWYM}" id="xmwym">
         <%--  <input type="hidden" value="${pd.PROJECT_NAME}" id="PROJECT_NAME"> --%>
         <input type="hidden" value="${pd.XMMC}" id="xmmc">
        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper1 " id="detailDiv">
          
        </div>
     


    <!-- REQUIRED JS SCRIPTS -->

     <!-- jQuery 3 -->
    <script src="plugins/xha/plugins/jquery/dist/jquery.min.js "></script>
     <script src="plugins/xha/plugins/jquery/dist/jquerySession.js "></script>
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
  <!--   <script src="plugins/xha/dist/js/pages/xiangmu.js "></script> -->
    <script src="plugins/xha/dist/js/pages/attachmentlist.js "></script>
    <script src="plugins/xha/dist/js/pages/projectlist.js "></script>
     <script src="plugins/xha/plugins/layer/layer.js"></script>
     <script type="text/javascript">
      	var layerIndex=-20;
	    var layerInitWidth;
	    var layerInitHeight;
     			 $.ajax({
		            type: "POST",
		            url: "sphz/project/getProjectDetails",
		            data: {
		                "XMWYM": $("#xmwym").val(),
		                "XMMC":  $("#xmmc").val()
		            },
		            dataType: "json",
		            success: function(response) {
		            	var returnMap=response.returnMap;
		            	//console.log(returnMap);
		                $("#detailDiv").projectlist({title:returnMap.title,analysisUrl:returnMap.analysisUrl,attachmentlistOptions:returnMap.attachmentlistOptions,stageFlag:returnMap.stageFlag,stageData:returnMap.stageData,baseData:returnMap.baseData,documentData:returnMap.documentData});
		            },
		            error: function() {
		                console.log("searcherror");
		            }
		        });
		        //弹出框自适应
		        $(function(){
		        	 $(window).resize(function() {
			           	if(layerIndex!=-20){
			           	    var width=$(window).width();
	                		var height=$(document.body).height();
						    layer.style(layerIndex, {
						        width: width,
						        height:height
						    });
			           	}
				    });
		        })
		          
      
     </script>
</body>

</html>