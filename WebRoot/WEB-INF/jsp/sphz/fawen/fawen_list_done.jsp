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
    <link rel="stylesheet" href="plugins/xha/dist/css/pages/demo.css">
    <link rel="stylesheet" href="plugins/xha/dist/css/pages/fawen_list_todo.css">
        <link rel="stylesheet" href="plugins/xha/dist/css/pages/tablezsy.css">
    <link rel="stylesheet" href="plugins/xha/plugins/layer/theme/default/layer.css">
</head>

<body class="hold-transition skin-blue sidebar-mini">
	
	<div id="dateSelectDiv" style="display:none;">
			<input type="text" class="datepicker" id="DCNY" placeholder="清选择日期">
	</div>
      

        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper1 ">
            <!-- Content Header (Page header) -->
           <section class="content-header ">
                <h1>
                                                                      发文管理
                    <small></small>
                </h1>
            </section>
            <!-- Main content -->
            <section class="content container-fluid ">
                <div class="box box-primary">
                    <!-- <div class="box-header with-border">
 						<div>
                               <h3 class="box-title text-blue box-select-title">关键字&nbsp;&nbsp;&nbsp;&nbsp;</h3>
                               <div class="box-mutiselect">
                                   <input type="text" id="keyword" class="form-control" autocomplete="off" placeholder="项目名称/项目地址/事项名称/发文文号/报建单位">
                               </div>
                               <button type="button" id="search"  class="btn btn-block btn-primary btn-sm" data-widget=""> 查询</button>
                          </div>                 
                    </div> -->
                     <div class="box-header with-border">
                         <div>
                       		   <h3 class="box-title text-blue box-select-title">经办部门&nbsp;&nbsp;&nbsp;&nbsp;</h3>
                       		   <div class="box-mutiselect jbbmDiv">
                                   <select class="form-control select2" onchange="changeCondition()" id="jbbmSelect" style="width: 100%;">
	                                   	<option selected="selected" value="">全部</option>
			                          	<c:forEach items="${jbbmList}" var="item">
											<option value="${item.BIANMA }">${item.NAME }</option>
										</c:forEach>
                                   </select>
                               </div>
                               
                               <h3 class="box-title text-blue box-select-title">发文类型&nbsp;&nbsp;&nbsp;&nbsp;</h3>
                       		   <div class="box-mutiselect jbbmDiv">
                                   <select class="form-control select2" onchange="changeConditionFwlx()" id="fwlxSelect" style="width: 100%;">
	                                   	<option selected="selected" value="">全部</option>
			                          	<c:forEach items="${fwlxList}" var="item">
											<option value="${item.NAME }">${item.NAME }</option>
										</c:forEach>
                                   </select>
                               </div>
                               
                               <h3 class="box-title text-blue box-select-title keyh">关键字&nbsp;&nbsp;&nbsp;&nbsp;</h3>
                               <div class="box-mutiselect keyDiv">
                                   <input type="text" id="keyword" class="form-control" autocomplete="off" placeholder="项目名称/项目地址/事项名称/发文文号/报建单位">
                               </div>
                               
                                <h3 class="box-title text-blue box-select-title keyh">发证日期&nbsp;&nbsp;&nbsp;&nbsp;</h3>
                                <div class="box-mutiselect timeDiv">
                                    <input type="text" id="beginTimeFz" class="form-control datepicker" autocomplete="off" placeholder="开始日期">
                                </div>
                                
                                 <h3 class="box-title text-blue box-select-title">—</h3>
                                 <div class="box-mutiselect timeDiv">
                                     <input type="text" id="endTimeFz" class="form-control datepicker" autocomplete="off" placeholder="结束日期">
                                 </div>
                                
                               <button type="button" id="search"  class="btn btn btn-block btn-primary btn-sm" data-widget=""> 查询</button>
                               <button type="button" id="reset"  class="btn btn-block btn-primary btn-sm" data-widget=""> 重置</button>
                          </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-12">
                      
                              <table id="table_project" class="table table-bordered" style="width:100%">
                                  <thead>
                                      <tr>
                                          <th>发文文号</th>
                                          <th>发文日期</th>
                                          <th>发文类型</th>
                                          <th>项目名称</th>
                                          <th>项目地址</th>
                                          <th>经办人</th>
                                          <th>经办部门</th>
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
    <script src="plugins/xha/dist/js/pages/fawen_done.js "></script>
     <script src="plugins/xha/dist/js/pages/JQRadio.js "></script>
     <script src="plugins/xha/plugins/layer/layer.js"></script>
       <script src="plugins/xha/dist/js/pages/colResizable-1.5.min.js "></script>
     <script type="text/javascript">
     
     var layerIndex=-20;
	    var layerInitWidth;
	    var layerInitHeight;
	     //弹出框自适应
        $(function(){
        	 $(window).resize(function() {
        	 //alert(12);
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
     
      	$('.datepicker').datepicker({autoclose: true,todayHighlight: true});//今天高亮显示		
    	$('#DCNY').datepicker({
		    format: 'yyyy-mm',
		    language: "zh-CN",
		    autoclose:true,
		    startView: 1,
		    minViewMode: 1,
		    maxViewMode: 1
		});

     	$(function() {
			$("#radio01").JQRadio({});
		});
     	var XMWYM="${XMWYM}";

	  
	   $("#search").click(function(){//奇怪的不行，行js文件死活不执行
	     	var jbbm = $("#jbbmSelect").val();
	     	var fwlx = $("#fwlxSelect").val();
	       	var keyword=$("#keyword").val();
       		var beginTimeFz=$("#beginTimeFz").val();
	       	var endTimeFz=$("#endTimeFz").val();
            var table = $("#table_project").dataTable();
            //清除表格数据
            table.fnClearTable();
            //重新请求数据（不写参数代表请求初始化时的默认接口数据）
            table.fnReloadAjax("sphz/fawen/listDone?keyword="+keyword+"&jbbm="+jbbm+"&fwlx="+fwlx+"&beginTimeFz="+beginTimeFz+"&endTimeFz="+endTimeFz);
	     })
	     
	     //重置
	      $("#reset").click(function(){
	         $("#jbbmSelect").val("").trigger('change');
	         $("#fwlxSelect").val("").trigger('change');
	         $("#keyword").val("");
	         $("#beginTimeFz").val("");
	         $("#endTimeFz").val("");
	     	var jbbm = $("#jbbmSelect").val();
	     	var fwlx = $("#fwlxSelect").val();
	       	var keyword=$("#keyword").val();
       		var beginTimeFz=$("#beginTimeFz").val();
	       	var endTimeFz=$("#endTimeFz").val();
            var table = $("#table_project").dataTable();
            //清除表格数据
            table.fnClearTable();
            //重新请求数据（不写参数代表请求初始化时的默认接口数据）
            table.fnReloadAjax("sphz/fawen/listDone?keyword="+keyword+"&jbbm="+jbbm+"&fwlx="+fwlx+"&beginTimeFz="+beginTimeFz+"&endTimeFz="+endTimeFz);
	     })
	     
	     function changeCondition(){
	     	var jbbm = $("#jbbmSelect").val();
	     	var fwlx = $("#fwlxSelect").val();
	       	var keyword=$("#keyword").val();
       		var beginTimeFz=$("#beginTimeFz").val();
	       	var endTimeFz=$("#endTimeFz").val();
            var table = $("#table_project").dataTable();
            //清除表格数据
            table.fnClearTable();
            //重新请求数据（不写参数代表请求初始化时的默认接口数据）
            table.fnReloadAjax("sphz/fawen/listDone?keyword="+keyword+"&jbbm="+jbbm+"&fwlx="+fwlx+"&beginTimeFz="+beginTimeFz+"&endTimeFz="+endTimeFz);
	     }
	     
	     function changeConditionFwlx(){
	     	var jbbm = $("#jbbmSelect").val();
	     	var fwlx = $("#fwlxSelect").val();
	       	var keyword=$("#keyword").val();
       		var beginTimeFz=$("#beginTimeFz").val();
	       	var endTimeFz=$("#endTimeFz").val();
            var table = $("#table_project").dataTable();
            //清除表格数据
            table.fnClearTable();
            //重新请求数据（不写参数代表请求初始化时的默认接口数据）
            table.fnReloadAjax("sphz/fawen/listDone?keyword="+keyword+"&jbbm="+jbbm+"&fwlx="+fwlx+"&beginTimeFz="+beginTimeFz+"&endTimeFz="+endTimeFz);
	     }
	     
	     
	     $(document).keydown(function(event){
			　　if(event.keyCode==13){
			　　		$("#search").click();
			　　}
		  }); 
	  
	     

	     $("#exportYSSZ").click(function(){
     		var indext = layer.open({
	            type: 1,
	            title: '选择要导出的月份',
	            shadeClose: true,
	            shade: false,
	            maxmin: false, //开启最大化最小化按钮
	            btn:['确定','取消'],
	            area: ['300px', '130px'],
	            content:$("#dateSelectDiv") ,
	            yes:function(index,layero){
	            	var time=$("#DCNY").val();
	            	if(time==''){
						alert("请选择要导出的月份！");
            			return false;
	            	} 
	               	window.location.href='sphz/fawen/exportYSSZ?param='+time;
	            },
	            cancel:function(){
	            	layer.close(indext);
	            }
	        });
		 })
		 
	       	$(function(){ 
				$("table").colResizable(); 
			});
			
			
		function add(){
			var width=$(window).width();
		    var height=$(window).height();
		 	var indext = layer.open({
		         type: 2,
		         title: '信息录入',
		         shadeClose: true,
		         shade: false,
		         maxmin: true, //开启最大化最小化按钮
		         //btn:['确定','取消'],
		         area: [width+'px', height+'px'],
		         content: 'sphz/fawen/goAddInfo'
		     });
		}
			
     </script>
</body>

</html>