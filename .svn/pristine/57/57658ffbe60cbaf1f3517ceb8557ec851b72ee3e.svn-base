var mymapApp = new Object();
require([
    "esri/config",
    "esri/basemaps",
    "esri/map",
    "esri/layers/ArcGISTiledMapServiceLayer",
    "esri/SpatialReference",    
    "esri/geometry/Extent",     
    "esri/geometry/Point",
    "esri/graphic",
    "esri/geometry/Polygon",
    "esri/symbols/SimpleFillSymbol",
    "esri/layers/GraphicsLayer",
    "dojo/domReady!",
    "myModules/utils/colResizable-1.5.min"
],
    function(esriConfig,esriBaseMaps,Map,ArcGISTiledMapServiceLayer,SpatialReference,Extent,Point,Graphic,Polygon,SimpleFillSymbol,GraphicsLayer,domReady){
	var baseTiledLayer=new ArcGISTiledMapServiceLayer("http://192.168.109.44:6080/arcgis/rest/services/lsgg/lgygyx/MapServer");
	esriBaseMaps.basemap={
            baseMapLayers:[baseTiledLayer],
            title:"影像地图",
            id:"domMap"
        };
	var str = localStorage.getItem("values4");
	var myjson = JSON.parse(str);
    var bounds=new Extent({
    	"xmin": 208727.315,
    	"ymin": 88862.532,
    	"xmax": 219439.533,
    	"ymax": 100161.491,
    	"spatialReference": new SpatialReference('PROJCS["Xian_1980_3_Degree_GK_CM_120E",GEOGCS["GCS_Xian_1980",DATUM["D_Xian_1980",SPHEROID["Xian_1980",6378140.0,298.257]],PRIMEM["Greenwich",0.0],UNIT["Degree",0.0174532925199433]],PROJECTION["Gauss_Kruger"],PARAMETER["False_Easting",200048.066],PARAMETER["False_Northing",-3889958.45],PARAMETER["Central_Meridian",120.0],PARAMETER["Scale_Factor",1.0],PARAMETER["Latitude_Of_Origin",0.0],UNIT["Meter",1.0]]')
    	//"spatialReference": 2385
    });
    mymapApp.map=new Map("map_qiujiao",{
    	basemap:"basemap",
    	extent:bounds,
    	fitExtent:true,
        logo:false,
        nav:false,
        slider:false
    }); 
    
    //console.log(myjson);
    var attrs = myjson.attrs;
   // console.log(attrs);
   // debugger;
    var sum_area = 0;
    for(var i=0;i<attrs.length;i++){
    	sum_area+= attrs[i]["Shape_Area"];
    }
   // alert(mymapApp.map.spatialReference);
    var cadlayer=new GraphicsLayer({id:"cadlayer"});
	var myPolygon=myjson.geometry;
	var polygon=new Polygon(myPolygon);
	cadlayer.spatialReference = polygon.spatialReference;
	//polygon.setSpatialReference(mymapApp.map.spatialReference);
	var gra=new Graphic(polygon);
    var fillsymbol=new esri.symbol.SimpleFillSymbol();
    fillsymbol.setColor(new dojo.Color([255,255,60,1]));
	gra.setSymbol(fillsymbol);
	cadlayer.add(gra);
	mymapApp.map.addLayer(cadlayer);
	var center= mymapApp.map.getLayer("cadlayer").initialExtent.getCenter();
	var point=new Point(214955.947,91829.747,polygon.spatialReference);
	var html="<table><tr><th class='field'>字段名称</th><th class='value'>值</th></tr>";
	html+="<tr><td class='field'>相交面积：</td>";
	html+="<td class='value'>"+sum_area+"(m²)</td></tr>";
	html+="</table>";
	$("#valuesTable").append(html);
	//设置表格可拖动 
	$("#valuesTable table").colResizable({
		liveDrag:true, 
		gripInnerHtml:"<div class='grip'></div>", 
		draggingClass:"dragging"
	});
/*	mymapApp.map.on("load",function(){
		mymapApp.map.centerAt(point);
	});*/
	//console.log(cadlayer);
	//mymapApp.map.setScale(5);
	
    });