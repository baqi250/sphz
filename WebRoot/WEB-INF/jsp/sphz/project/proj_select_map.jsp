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
    <link rel="stylesheet" href="plugins/xha/dist/css/pages/map.css">
    <link rel="stylesheet" href="plugins/xha/plugins/layer/theme/default/layer.css">
    
    <!-- <link href="plugins/zTree/3.5/css/metroStyle/metroStyle.css" media="screen" rel="stylesheet" type="text/css" /> -->
    <link href="plugins/ArcGIS/3.17/esri/css/esri.css" rel="stylesheet" type="text/css" />
    <link href="plugins/ArcGIS/3.17/dijit/themes/claro/claro.css" rel="stylesheet" type="text/css" /> 
    
    <!--  <link rel="stylesheet" type="text/css" href="http://localhost:8081/arcgis_js_api/library/3.17/3.17/dijit/themes/tundra/tundra.css"/>
    <link rel="stylesheet" type="text/css" href="http://localhost:8081/arcgis_js_api/library/3.17/3.17/esri/css/esri.css" /> -->
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>

<body class="hold-transition skin-blue sidebar-mini">
 <!-- Content Wrapper. Contains page content -->
		<div class="searchDiv">
			<input type="hidden" value="${pd.XMWYM}" id="XMWYM" name="XMWYM">
			<input type="hidden" value="${pd.PROJECT_NAME}" id="PROJECT_NAME" name="PROJECT_NAME">
           <!--  搜索框 -->
		    <form class="search-form box-mutiselect" autocomplete="off">
		    	<input type="text" id="project_gl_name" name="project_gl_name" value="${pd.searchValue }" class="search-form-control form-control" placeholder="搜索（请输入项目名称/项目位置/建设单位/发文文号）" autocomplete="off">
		    	<i class="fa fa-search icon-search"></i>
		    </form>
		  	<!--    搜索结果框 -->
		    <div class="search-result text-dark" id="search_resut_div">
		        <ul class="search-result-contain">
		        </ul>
		    </div>
		</div>
		<div class="button-group">
			<button id="addProject" class="btn btn-block btn-primary btn-success"><i class="fa fa-plus">&nbsp;新增项目</i></button>
		</div>
		<div id="legendDiv1" style="position:absolute;"></div>
	    <div class="tundra" id="mapContent">
			<div id="mapDiv"></div>
		</div>
		
	<!-- REQUIRED JS SCRIPTS -->

      <!-- jQuery 3 -->
  <!--   <script src="plugins/xha/plugins/jquery/dist/jquery.min.js "></script> -->
  
    <script type="text/javascript" src="plugins/ArcGIS/3.17/init.js"></script>
    <script src="plugins/jquery/jquery.js"></script>
     <script src="plugins/xha/plugins/layer/layer.js"></script>
     <script src="config/config.js"></script>
     <script type="text/javascript">
        dojo.require("esri.map");
        var map,toolbar,a={},legendLayers=[];
        require([
        "esri/map",
        "esri/toolbars/draw",
        "esri/graphic",
        "esri/layers/ArcGISTiledMapServiceLayer",
        "esri/layers/FeatureLayer",
        "esri/tasks/query",
        "esri/tasks/QueryTask",
        "esri/symbols/SimpleFillSymbol",
        "esri/geometry/Point",
        "esri/dijit/Legend",
        "esri/SpatialReference"
       
        ],function(Map,Draw,Graphic,ArcGISTiledMapServiceLayer,FeatureLayer,Query,QueryTask,SimpleFillSymbol,Point,Legend,SpatialReference){
	        map=new Map("mapDiv",{
	         	logo:false,
	            nav:false,
	            slider:false
	        });
        	
        	//var ip="localhost";
        	var ip=$CONFIG.map_server_ip;
        	var baseUrl="http://"+ip+":6080/arcgis/rest/services/cgcs2000/ygyx_sy_cgcs2000/MapServer";
        	var baseUrl1="http://"+ip+":6080/arcgis/rest/services/cgcs2000/qhjx_sy_cgcs2000/MapServer";
        	var spxmUrl="http://"+ip+":6080/arcgis/rest/services/cgcs2000/spxm_zxcq_cgcs2000/MapServer/0";
	        var myTiledMapServiceLayer=new ArcGISTiledMapServiceLayer(baseUrl);
	        map.addLayer(myTiledMapServiceLayer);
          	var myTiledMapServiceLayer1=new ArcGISTiledMapServiceLayer(baseUrl1);
	        map.addLayer(myTiledMapServiceLayer1);
	        var s=new SpatialReference({wkid:4528});
	        var point=new Point(40523642.271,4011989.065,s);
	        map.centerAndZoom(point,2);
	        var myFeatureLayer=new FeatureLayer(spxmUrl,{
	        	mode:FeatureLayer.MODE_SNAPSHOT,
	        	outFields:["XMWYM"]
	        });
	        map.addLayer(myFeatureLayer);
	        legendLayers.push({layer:myFeatureLayer,title:'图例'});
	        var legend=new esri.dijit.Legend({
	        	map:map,
	        	layerInfos:legendLayers,
	        	arrangement:2
	        },"legendDiv1");
	      
	        legend.startup();
          	$("#legendDiv1").css("position","absolute");
	        var gratemp=new esri.layers.GraphicsLayer();//记录绘制多边形图层
	        map.addLayer(gratemp);
	        var graresult=new esri.layers.GraphicsLayer();//记录绘制多边形图层
	        map.addLayer(graresult);
	        var graspatial=new esri.layers.GraphicsLayer();//记录绘制多边形图层
	        map.addLayer(graspatial);
	       
	        	
	       	var queryTask=new QueryTask(spxmUrl);
	        var query=new Query();
	        query.returnGeometry=true;
	        query.outFields=["XMWYM"];
	        var fillSymbol=new esri.symbol.SimpleFillSymbol();
	        fillSymbol.setColor(new dojo.Color([0,255,255,1]));
	        
	       /*  var fillSymbolSpatial=new esri.symbol.SimpleFillSymbol();
	        fillSymbolSpatial.setColor(new dojo.Color([255,0,255,1])); */
  
	        function excuteQuery(xmwym){
       	       	var queryTask=new QueryTask(spxmUrl);
		        var query=new Query();
		        query.returnGeometry=true;
		        query.outFields=["XMWYM"];
        	 	var fillSymbolSpatial=new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_NULL,new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID,
		        	new dojo.Color([255,165,0,1]),4),
		        	new dojo.Color([255,255,0,0.5]));
	           	var whereStr="XMWYM  ='"+xmwym+"'";
	        	query.where=whereStr;
	        	query.geometry=null;
	        	queryTask.execute(query,function(fs){
	        		graspatial.clear();
		        	var features= fs.features;
		        	if(null!=features&&features.length>0){
			        	for(var i=0;i<features.length;i++){
			        		var feature=features[i];
				        	feature.setSymbol(fillSymbolSpatial);
				        	graspatial.add(feature);
			        	}
		            	var center= features[0].geometry.getExtent().getCenter();
		            	var point=new Point(center.x,center.y,new SpatialReference({wkid:4528}));
		            	map.centerAndZoom(point,7);
		        	}
	        	});
	        } 
	        
	        // 设置搜索框
		$(".search-form-control").on("input", function(e) {
		    // 过滤中文
		    //var reg = new RegExp("[\\u4E00-\\u9FFF]+$", "g");
		    var inputvalue = e.delegateTarget.value;
		    if (inputvalue != '') {
		        $(".search-btn").addClass("search-btn-spanded");
		    } else {
		        if ($(".search-btn").hasClass("search-btn-spanded")) {
		            $(".search-btn").removeClass("search-btn-spanded");
		        }
		        $(".search-result").css("visibility", "hidden");
		        $(".search-result-contain").empty();
		    }
		    
		        $.ajax({
		            type: "POST",
		            url: "sphz/project/searchByKeyWord",
		            data: {
		                "keyword": inputvalue
		            },
		            dataType: "json",
		            success: function(response) {
		                // console.log(response)
		                if (response !== null && response.projectList.length !== 0) {
		                    var projectList = response.projectList;
		                    $(".search-result").css("visibility", "visible");
		                    $(".search-result-contain").empty();
		                    var length=projectList.length;
		                    for (var i = 0; i < length; i++) {
		                        if (projectList[i].PROJECT_NAME != null) {
		                            $(".search-result-contain").append('<li class="search-result-name">'
		                            + '<table table table-condensed table-child>'
		                            +'<tr>'
		                            +'<td class="labelTd">项目名称：</td>'
		                            +'<td class="contentTd">'+projectList[i].PROJECT_NAME+'</td>'
		                            +'<td class="buttonTd">'
		                            +'<button class="btn-position btn btn-block btn-primary btn-inverse " title="定位" id="'+projectList[i].XMWYM+'"><i class="fa fa-map-marker">&nbsp;定位</i></button>'
		                            +'</td>'
		                            +'</tr>'
		                            +'<tr>'
		                            +'<td class="labelTd">建设单位：</td>'
		                            +'<td class="contentTd">'+projectList[i].APPLICANT+'</td>'
		                            +'<td class="buttonTd">'
		                            +'<button class="btn-select btn btn-block btn-primary btn-inverse " title="选择" mydata="'+projectList[i].PROJECT_NAME+'" id="'+projectList[i].XMWYM+'"><i class="fa fa-info"> &nbsp;选择</i></button>'
		                            +'</td>'
		                            +'</tr>'
		                            +'</table>'
		                            +'</li>');
		                        } else {
		                            $(".search-result-contain").append('<li>查询结果有误</li>');
		                        }
		                    }
		                } else {
		                    $(".search-result").css("visibility", "visible");
		                    $(".search-result-contain").empty();
		                    $(".search-result-contain").append('<li style="height:40px;">无查询结果</li>');
		                }
		            },
		            error: function() {
		                console.log("searcherror");
		            }
		        });
		    
		});
		
		$(document).on('click', '.btn-select', function() {
		    var projName = $(this).attr("mydata");
		    var xmwym = $(this).attr("id");
		   /*  $("#XMWYM").val(xmwym);
		    $("#PROJECT_NAME").val(projName); */
		    parent.$("#project_gl_name").val(projName);
		    parent.$("#XMWYM").val(xmwym);
		     $.ajax({
		            type: "POST",
		            url: "sphz/project/searchByXmwym",
		            data: {
		                "xmwym": xmwym
		            },
		            dataType: "json",
		            success: function(response) {
		    			//console.log(response.projectList);
		    			if(response.result=="success"){
		    				var proj=response.proj;
		    				parent.$("#XMLX").val(proj.XMLX).trigger("change");
		    				parent.$("#JSLX").val(proj.JSLX).trigger("change");
		    				if(proj.XMSD!=""){
	    						parent.$("#XMSD").val(proj.XMSD.split(',')).trigger("change");
		    				}
		    				//parent.$("#XMSD").val(proj.XMSD).trigger("change");
		    				if(parent.$("#INVESTMENT").val()==""){
		    					parent.$("#INVESTMENT").val(proj.INVESTMENT);
		    				}
		    				if(parent.$("#LOCATION").val()==""){
		    					parent.$("#LOCATION").val(proj.LOCATION);
		    				}
		    				if(parent.$("#PROJECT_NAME").val()==""){
		    					parent.$("#PROJECT_NAME").val(proj.PROJECT_NAME);
		    				}
		    				if(parent.$("#IS_IMPORTANT").val()==""){
		    					parent.$("#IS_IMPORTANT").val(proj.IS_IMPORTANT).trigger("change");
		    				}
		    				/* if(parent.$("#SCALE").val()==""){
		    					parent.$("#SCALE").val(proj.SCALE);
		    				} */
		    				
		    					
	    					if(proj.SCALE!=""){
		    					parent.$("#SCALE").val(proj.SCALE);
		    				}
		    				if(proj.PROJECT_CONTENT!=""){
		    					parent.$("#PROJECT_CONTENT").val(proj.PROJECT_CONTENT);
		    				}
		    				if(null!=proj.LAND_USE&&proj.LAND_USE!=""){
		    				console.log(proj.LAND_USE);
								//parent.$(".my_select-tree").val(proj.LAND_USE.split(',')).trigger("change");
								parent.$("#select-tree").val(proj.LAND_USE.split(',')).trigger("change");
								parent.$("#select-tree2").val(proj.LAND_USE.split(',')).trigger("change");
								parent.$("#select-tree3").val(proj.LAND_USE.split(',')).trigger("change");
								parent.$("#select-tree4").val(proj.LAND_USE.split(',')).trigger("change");
								parent.$("#select-tree5").val(proj.LAND_USE.split(',')).trigger("change");
							}
							parent.$("#SFHHYD").val(proj.SFHHYD).trigger("change");
							parent.$("#HHYDBL").val(proj.HHYDBL);
							parent.$("#HMCSYQ").val(proj.HMCSYQ);
							parent.$("#BZ").val(proj.BZ);
		    			}else{
		    				console.log(response.result);
		    			}
		            },
		            error: function() {
		                console.log("searcherror");
		            }
		        });
		    
		    
	        var index = parent.layer.getFrameIndex(window.name);  
    		parent.layer.close(index);//关闭当前页  
			//$(".search-result").css("visibility", "hidden");
		    //$(".search-result-contain").empty();
		  //  $(".search-form-control").val(projName);
		  	
		});
		
		a.returnData=function(json){
			var proj=eval('(' + json + ')');
			parent.$("#project_gl_name").val(proj.PROJECT_NAME);
		    parent.$("#XMWYM").val(proj.XMWYM);
			parent.$("#XMLX").val(proj.XMLX).trigger("change");
			parent.$("#JSLX").val(proj.JSLX).trigger("change");
			if(proj.XMSD!=""){
				parent.$("#XMSD").val(proj.XMSD.split(',')).trigger("change");
			}
			if(parent.$("#INVESTMENT").val()==""){
				parent.$("#INVESTMENT").val(proj.INVESTMENT);
			}
			if(parent.$("#LOCATION").val()==""){
				parent.$("#LOCATION").val(proj.LOCATION);
			}
			if(parent.$("#PROJECT_NAME").val()==""){
				parent.$("#PROJECT_NAME").val(proj.PROJECT_NAME);
			}
			if(parent.$("#IS_IMPORTANT").val()==""){
				parent.$("#IS_IMPORTANT").val(proj.IS_IMPORTANT).trigger("change");
			}
			if(proj.SCALE!=""){
				parent.$("#SCALE").val(proj.SCALE);
			}
			if(proj.PROJECT_CONTENT!=""){
				parent.$("#PROJECT_CONTENT").val(proj.PROJECT_CONTENT);
			}
			if(null!=proj.LAND_USE&&proj.LAND_USE!=""){
				parent.$(".my_select-tree").val(proj.LAND_USE.split(',')).trigger("change");
			}
			parent.$("#SFHHYD").val(proj.SFHHYD).trigger("change");
			parent.$("#HHYDBL").val(proj.HHYDBL);
			parent.$("#HMCSYQ").val(proj.HMCSYQ);
			parent.$("#BZ").val(proj.BZ);
			var index = parent.layer.getFrameIndex(window.name);  
    		parent.layer.close(index);//关闭当前页  
		}
		
		
		$(document).on('click', '.btn-position', function() {
		    //var projName = $(this).attr("mydata");
		    var xmwym = $(this).attr("id");
		    excuteQuery(xmwym);
		   // $("#XMWYM").val(xmwym);
		 /*    $(".search-result").css("visibility", "hidden");
		    $(".search-result-contain").empty();
		    $(".search-form-control").val(projName); */
		   // window.location='sphz/project/goSpatialDetail?xmwym='+xmwym+'&xmmc='+projName+'&searchValue='+$("#project_gl_name").val();
		});
		
		$(document).on('click', '#addProject', function() {
			var indext = layer.open({
		         type: 2,
		         title: '新增项目',
		         shadeClose: true,
		         shade: false,
		         maxmin: true, //开启最大化最小化按钮
		         area: ['500px', '600px'],
		         content: 'sphz/project/goAdd'
		     });
		});
		
		$(document).on('click', '#project_gl_name', function() {
			var vi= $('#search_resut_div').css('visibility');
			if(vi=="hidden"){
				$('#search_resut_div').css('visibility', 'visible');
				return;
			}else{
				$('#search_resut_div').css('visibility', 'hidden');
				return;
			}
		});
		$(function(){
			/* $("#project_gl_name").val('te');
			$(".search-form-control").trigger("input"); */
			if(!$("#project_gl_name").val()==""){
				$(".search-form-control").trigger("input"); 
			}
		})
		
		// 搜索内容
		/* $(document).on('click', '.search-result-name', function() {
		    var projName = $(this)[0].innerHTML;
		    var xmwym = $(this).attr("id");
		    $("#XMWYM").val(xmwym);
		    $(".search-result").css("visibility", "hidden");
		    $(".search-result-contain").empty();
		    $(".search-form-control").val(projName);
		    window.location='sphz/project/goSpatialDetail?xmwym='+xmwym+'&xmmc='+projName;
		}); */
		/* $(document).bind('click', function(e) {//点击DIV区域以外隐藏该DIV
            var e = e || window.event; //浏览器兼容性   
            var elem = e.target || e.srcElement;  
            while (elem) { //循环判断至跟节点，防止点击的是div子元素  
                if (elem.id && elem.id == 'project_gl_name') {
                  $('#search_resut_div').css('visibility', 'hidden'); //点击的不是div或其子元素  
                    return;  
                }  
                elem = elem.parentNode;  
            }  
           
        }); */
		
		/* $(document).bind('click', function(e) {//点击DIV区域以外隐藏该DIV
            var e = e || window.event; //浏览器兼容性   
            var elem = e.target || e.srcElement;  
            while (elem) { //循环判断至跟节点，防止点击的是div子元素  
                if (elem.id && elem.id == 'search_resut_div') {
                    return;  
                }  
                elem = elem.parentNode;  
            }  
            $('#search_resut_div').css('visibility', 'hidden'); //点击的不是div或其子元素   
        }); */
      	
	        
        })
     </script>
</body>

</html>