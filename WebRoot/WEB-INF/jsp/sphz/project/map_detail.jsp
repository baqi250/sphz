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
 		<input type="hidden" id="XMWYM" value="${pd.xmwym}">
 		<div class="button-group">
 		<!-- <button id="clear" class="btn btn-block btn-primary btn-success">返回</button> -->
		<!-- 	<button id="draw" class="btn btn-block btn-primary btn-success">空间统计</button>
			<button id="clear" class="btn btn-block btn-primary btn-danger">清除</button> -->
		</div>
		<!-- <div id="legendDiv1" style="position:absolute;"></div> -->
	    <div class="tundra" id="mapContent">
			<div id="mapDiv"></div>
		</div>
		
	<!-- REQUIRED JS SCRIPTS -->

      <!-- jQuery 3 -->
  <!--   <script src="plugins/xha/plugins/jquery/dist/jquery.min.js "></script> -->
  
    <script type="text/javascript" src="plugins/ArcGIS/3.17/init.js"></script>
    <script src="plugins/jquery/jquery.js"></script>
     <script src="config/config.js"></script>
     <script type="text/javascript">
        dojo.require("esri.map");
        var map,toolbar,a={},legendLayers=[];
        require([
        "esri/map",
        "esri/graphic",
        "esri/layers/ArcGISTiledMapServiceLayer",
        "esri/layers/FeatureLayer",
        "esri/tasks/query",
        "esri/tasks/QueryTask",
        "esri/symbols/SimpleFillSymbol",
        "esri/geometry/Point",
        "esri/SpatialReference"
       
        ],function(Map,Graphic,ArcGISTiledMapServiceLayer,FeatureLayer,Query,QueryTask,SimpleFillSymbol,Point,SpatialReference){
	        map=new Map("mapDiv",{
	         	logo:false,
	            nav:false,
	            slider:false
	        });
	        var s=new SpatialReference({wkid:4528});
	        var point=new Point(40523642.271,4011989.065,s);
	        map.centerAndZoom(point,2);
        	//var ip="localhost";
        	var ip=$CONFIG.map_server_ip;
        	var baseUrl="http://"+ip+":6080/arcgis/rest/services/cgcs2000/ygyx_sy_cgcs2000/MapServer";
        	var baseUrl1="http://"+ip+":6080/arcgis/rest/services/cgcs2000/qhjx_sy_cgcs2000/MapServer";
        	var spxmUrl="http://"+ip+":6080/arcgis/rest/services/cgcs2000/spxm_zxcq_cgcs2000/MapServer/0";
	        var myTiledMapServiceLayer=new ArcGISTiledMapServiceLayer(baseUrl);
	        map.addLayer(myTiledMapServiceLayer);
	        var myTiledMapServiceLayer1=new ArcGISTiledMapServiceLayer(baseUrl1);
	        map.addLayer(myTiledMapServiceLayer1);
	        var myFeatureLayer=new FeatureLayer(spxmUrl,{
	        	mode:FeatureLayer.MODE_SNAPSHOT,
	        	outFields:["XMWYM"]
	        });
	        map.addLayer(myFeatureLayer);
           	var graspatial=new esri.layers.GraphicsLayer();//记录绘制多边形图层
	        map.addLayer(graspatial);
	        
	        var xmwym=$("#XMWYM").val();
	        excuteQuery('${pd.xmwym}');
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
    

        })
     </script>
</body>

</html>