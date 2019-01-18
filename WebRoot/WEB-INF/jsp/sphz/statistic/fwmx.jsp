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
    <link rel="stylesheet" href="plugins/xha/dist/css/pages/xxsh.css">
    <link rel="stylesheet" href="plugins/xha/dist/css/pages/fwmx.css">
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
  
      	<div id="dateSelectDiv" style="display:none;">
			<input type="text" class="datepicker" id="DCNY" placeholder="清选择日期">
		</div>

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

    		 	<!-- <div class="box box-primary">
                    <div class="box-header with-border">
                         <div>
                              <h3 class="box-title text-blue box-select-title keyh">统计年份&nbsp;&nbsp;&nbsp;&nbsp;</h3>
                              <div class="box-mutiselect timeDiv">
                                  <input type="text" id="beginTime" class="form-control datepicker" autocomplete="off" placeholder="统计年份">
                              </div>
                          </div>
                    </div>
                </div> -->
                <input type="hidden" value="${pd.BIANMA}" id="BIANMA">
		
                <div class="row">
                    <div class="col-md-6">
                        <!-- 图1 -->
                        <div class="box box-primary box-charts">
                            <div class="box-header with-border">
                                <h3 class="box-title text-blue">规划审批证书核发统计</h3>
								
                                <div class="box-tools pull-right">
                          	 		<div class="btn-group">
                                        <button type="button" class="btn btn-primary btn-xs" id="year-btn">2019</button>
                                        <button type="button" class="btn btn-primary btn-xs dropdown-toggle" data-toggle="dropdown">
                                            <span class="caret"></span>
                                            <span class="sr-only">Toggle Dropdown</span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu" id="yearMenu">
                                        </ul>
                                    </div>
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
                        
                         <!-- 图5 -->
                        <div class="box box-danger box-charts">
                            <div class="box-header with-border">
                                <h3 class="box-title text-red" id="chart5_title">当年各月核发情况对比</h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool info-hidden" data-widget="showinfo" id="box-5"><i class="fa fa-info-circle"></i></button>
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                                <div class="box-text" id="box-5-text" hidden>
                                   
                                </div>

                                <div class="box-chart" id="chart-5"></div>
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
                        
                        <!-- 图6 -->
                        <div class="box box-success box-charts">
                            <div class="box-header with-border">
                                <h3 class="box-title text-green" id="chart6_title">当年各月核发情况同期对比</h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool info-hidden" data-widget="showinfo" id="box-6"><i class="fa fa-info-circle"></i></button>
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                                <div class="box-text" id="box-6-text" hidden>
                                    
                                </div>

                                <div class="box-chart" id="chart-6"></div>
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
            <div class="reportDiv">
            	 <button type="button" id="exportYSSZ"  class="btn btn-block btn-primary btn-sm" data-widget="" > 一书三证发放明细月报表</button>
            	 <button type="button" id="exportXMXX"  class="btn btn-block btn-primary btn-sm" data-widget="" >项目信息全</button>
            </div>
           
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
    <script src="plugins/xha/dist/js/pages/fwmx_w5.js"></script>
    <script src="plugins/xha/dist/js/pages/fwmx_w6.js"></script>
    <script src="plugins/xha/dist/js/pages/JQRadio.js "></script>
    <script src="plugins/xha/plugins/layer/layer.js"></script>
    <script type="text/javascript">
	  /* $('#DCNY').datepicker({
		    format: 'yyyy-mm',
		    language: "zh-CN",
		    autoclose:true,
		    startView: 1,
		    minViewMode: 1,
		    maxViewMode: 1
		}); */
		var dateMonthConfig = {
			  language: "zh-CN", //语言
			  todayHighlight: true, //是否今日高亮
			  format: 'yyyy-mm', //点击月份后显示在input中的格式
			  autoclose: true, //是否开启自动关闭
			  startView: 'months', //开始视图层，为月视图层
			  maxViewMode:'years', //最大视图层，为年视图层
			  minViewMode:'months' //最小视图层，为月视图层
		};
		$('#DCNY').datepicker(dateMonthConfig);
		
		$('#beginTime').datepicker({
			format: 'yyyy',
			autoclose: true,
			startView: 2,
			maxViewMode: 2,
			minViewMode:2,
			language: 'zh-CN'
		});
	
    	$(function() {
			$("#radio01").JQRadio({});
		});
		
		
		
		  $("#exportYSSZ").click(function(){
		  	//$("#exportYSSZ").attr("disabled","disabled");//避免重复提交
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
		 
   		$("#exportXMXX").click(function(){
   			$("#exportXMXX").attr("disabled","disabled");//避免重复提交
  			window.location.href='sphz/fawen/exportXMXX';
	 	})
    </script>
</body>

</html>