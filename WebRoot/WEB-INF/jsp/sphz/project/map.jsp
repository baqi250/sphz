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
    
    <link href="plugins/ArcGIS/3.17/esri/css/esri.css" rel="stylesheet" type="text/css" />
    <link href="plugins/ArcGIS/3.17/dijit/themes/claro/claro.css" rel="stylesheet" type="text/css" /> 
</head>

<body class="hold-transition skin-blue sidebar-mini">
 <!-- Content Wrapper. Contains page content -->
		<div class="searchDiv">
           <!--  搜索框 -->
           <input type="hidden" id="isBack" value="${pd.isBack }">
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
		<div id="legendDiv1" style="position:absolute;"></div>
	    <div class="tundra" id="mapContent">
			<div id="mapDiv"></div>
		</div>
		
	<!-- REQUIRED JS SCRIPTS -->

      <!-- jQuery 3 -->
  
    <script type="text/javascript" src="plugins/ArcGIS/3.17/init.js"></script>
    <script src="plugins/jquery/jquery.js"></script>
    <script src="config/config.js"></script>
    <script src="plugins/xha/plugins/layer/layer.js"></script>
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
        "esri/SpatialReference",
        "esri/layers/ArcGISDynamicMapServiceLayer",
        "esri/renderers/SimpleRenderer",
        "esri/renderers/UniqueValueRenderer"
       
        ],function(Map,Draw,Graphic,ArcGISTiledMapServiceLayer,FeatureLayer,Query,QueryTask,SimpleFillSymbol,Point,Legend,SpatialReference,ArcGISDynamicMapServiceLayer,SimpleRenderer,UniqueValueRenderer){
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
        	var spxmCenterUrl="http://"+ip+":6080/arcgis/rest/services/cgcs2000/spxm_center/MapServer/0";
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
	        
	        var pictureMarkerSymbol=new esri.symbol.PictureMarkerSymbol("map/images/location.png",32,32).setOffset(0,13);
	        var render=new UniqueValueRenderer(pictureMarkerSymbol,"SPJD");
	        render.addValue("条",new esri.symbol.PictureMarkerSymbol("map/images/tj.png",32,32).setOffset(0,13));
	        render.addValue("地",new esri.symbol.PictureMarkerSymbol("map/images/yd.png",32,32).setOffset(0,13));
	        render.addValue("建",new esri.symbol.PictureMarkerSymbol("map/images/js.png",32,32).setOffset(0,13));
	        render.addValue("核",new esri.symbol.PictureMarkerSymbol("map/images/jg.png",32,32).setOffset(0,13));
	        
          	var myFeatureLayer1=new FeatureLayer(spxmCenterUrl,{
	        	mode:FeatureLayer.MODE_SNAPSHOT,
	        	outFields:["*"]
	        });
	        myFeatureLayer1.setRenderer(render);
	        myFeatureLayer1.setMaxScale(30000);
	        map.addLayer(myFeatureLayer1);
	       
          	$("#legendDiv1").css("position","absolute");
	        var gratemp=new esri.layers.GraphicsLayer();//记录绘制多边形图层
	        map.addLayer(gratemp);
	        var graresult=new esri.layers.GraphicsLayer();//记录绘制多边形图层
	        //graresult.opacity=0.5;
	        map.addLayer(graresult);
	        var graspatial=new esri.layers.GraphicsLayer();//记录绘制多边形图层
	        map.addLayer(graspatial);
	        
	        var click=dojo.connect(map,"onClick",excuteQueryTask);
	        
	        var fillSymbolSpatial=new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_NULL,new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID,
	        	new dojo.Color([255,165,0,1]),4),
	        	new dojo.Color([255,255,0,0.5]));
	        	
	       	var queryTask=new QueryTask(spxmUrl);
	        var query=new Query();
	        query.returnGeometry=true;
	        query.outFields=["XMWYM"];
	        var fillSymbol=new esri.symbol.SimpleFillSymbol();
	        fillSymbol.setColor(new dojo.Color([0,255,255,1]));
	        
	     /*    var fillSymbolSpatial=new esri.symbol.SimpleFillSymbol();
	        //fillSymbolSpatial.setColor(new dojo.Color([0,0,0,0]));
	        fillSymbolSpatial.setStyle(esri.symbol.SimpleLineSymbol.STYLE_NULL); */
  			
  			//几何查询
	        function excuteQueryTask(evt){
	        	query.geometry=evt.mapPoint;
	        	query.where=null;
	        	queryTask.execute(query,function(fs){
        			graresult.clear();
		        	//console.log(fs.features[0]);
		        	if(null!=fs.features&&fs.features.length>0){
			        	var feature=fs.features[0];
			        	feature.setSymbol(fillSymbolSpatial);
			        	graresult.add(feature);
			        	var xmwym= feature.attributes.XMWYM;
			        	var x=map.extent.getCenter().x;
					    var y=map.extent.getCenter().y;
					    var zoom=map.getZoom();
					    window.localStorage.setItem("map_center_x", x);
					    window.localStorage.setItem("map_center_y", y);
					    window.localStorage.setItem("map_zoom", zoom);
			        	var indext = layer.open({
					         type: 2,
					         title: '项目信息',
					         shadeClose: true,
					         shade: false,
					         maxmin: true, //开启最大化最小化按钮
					         area: ['500px', '350px'],
					         content: 'sphz/project/goPopInfo?xmwym='+xmwym
					     });
			        	//alert(xmwym);
			        	//通过项目唯一码查询项目基本信息
			       /*  	$.ajax({
				            type: "POST",
				            url: "sphz/project/getXiangmuByXmwym",
				            data: {
				                "XMWYM": xmwym
				            },
				            dataType: "json",
				            success: function(response) {
				            	if(response.result=="success"){
				            		var xiangmu= response.xiangmu;
				            		alert(xiangmu.PROJECT_NAME);
				            		
				            	}else{
				            		alert(response.result);
				            	}  
				            },
				            error: function() {
				                alert("服务器异常");
				            }
				        }); */
		        	}
	        	});
	        }
  			
  			//属性查询
	        function excuteQuery(xmwym){
       	       	var queryTask=new QueryTask(spxmUrl);
		        var query=new Query();
		        query.returnGeometry=true;
		        query.outFields=["XMWYM"];
		       /*  var fillSymbolSpatial=new esri.symbol.SimpleFillSymbol();
	        	fillSymbolSpatial.setColor(new dojo.Color([205,0,205,0.5])); */
	           	var whereStr="XMWYM  ='"+xmwym+"'";
	        	query.where=whereStr;
	        	query.geometry=null;
	        	queryTask.execute(query,function(fs){
	        		graresult.clear();
		        	var features= fs.features;
		        	if(null!=features&&features.length>0){
			        	for(var i=0;i<features.length;i++){
			        		var feature=features[i];
				        	feature.setSymbol(fillSymbolSpatial);
				        	graresult.add(feature);
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
		                            +'<button class="btn-info btn btn-block btn-primary btn-inverse " title="详情" mydata="'+projectList[i].PROJECT_NAME+'" id="'+projectList[i].XMWYM+'"><i class="fa fa-info"> &nbsp;详情</i></button>'
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
		
		$(document).on('click', '.btn-info', function() {
		    var projName = $(this).attr("mydata");
		    var xmwym = $(this).attr("id");
		    var x=map.extent.getCenter().x;
		    var y=map.extent.getCenter().y;
		    var zoom=map.getZoom();
		    window.localStorage.setItem("map_center_x", x);
		    window.localStorage.setItem("map_center_y", y);
		    window.localStorage.setItem("map_zoom", zoom);
		    
		    window.location='sphz/project/goSpatialDetail?xmwym='+xmwym+'&xmmc='+projName+'&searchValue='+$("#project_gl_name").val();
		});
		
		$(document).on('click', '.btn-position', function() {
		    var xmwym = $(this).attr("id");
		    excuteQuery(xmwym);
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
			if(!$("#project_gl_name").val()==""){
				$(".search-form-control").trigger("input"); 
			}
			if($("#isBack").val()=="back"){
			 	var x= window.localStorage.getItem("map_center_x");
			 	var y= window.localStorage.getItem("map_center_y");
		    	var zoom= window.localStorage.getItem("map_zoom");
		    	var point=new Point(x,y,new SpatialReference({wkid:4528}));
            	map.centerAndZoom(point,zoom);
			}
		})
		
		
     })
     </script>
</body>

</html>