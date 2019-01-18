/**
 * Created by zhl on 18-3-9.
 */
var mapApp = new Object();
//全局变量记录地图上的矢量图层url,用于点击查询
mapApp.dynamicLayersUrls=[];
mapApp.tiledLayersUrls=[];
//叠加的cad图层
mapApp.cadLayerIds=[];
require([
    "esri/config",
    "esri/basemaps",
    "esri/map",
    "esri/SpatialReference",
    "esri/layers/ArcGISTiledMapServiceLayer",
    "myModules/interface",
    "myModules/innerPubFun",
    "myModules/config/layerConfig",
    "esri/geometry/Extent",
    "dojo/domReady!"
],
    function(esriConfig,esriBaseMaps,Map,SpatialReference,ArcGISTiledMapServiceLayer,Interface,innerPubFun,LayerConfig,Extent,domReady){
	var baseTiledLayer=new ArcGISTiledMapServiceLayer(mapApp.layerConfig.domMapUrl);  
        esriBaseMaps.basemap={
            baseMapLayers:[],
            title:"影像地图",
            id:"domMap"
        };
	    var bounds=new Extent({
	    	"xmin": 208727.315,
	    	"ymin": 88862.532,
	    	"xmax": 219439.533,
	    	"ymax": 100161.491,
	    	"spatialReference": new SpatialReference('PROJCS["Xian_1980_3_Degree_GK_CM_120E",GEOGCS["GCS_Xian_1980",DATUM["D_Xian_1980",SPHEROID["Xian_1980",6378140.0,298.257]],PRIMEM["Greenwich",0.0],UNIT["Degree",0.0174532925199433]],PROJECTION["Gauss_Kruger"],PARAMETER["False_Easting",200048.066],PARAMETER["False_Northing",-3889958.45],PARAMETER["Central_Meridian",120.0],PARAMETER["Scale_Factor",1.0],PARAMETER["Latitude_Of_Origin",0.0],UNIT["Meter",1.0]]')
	    });
        mapApp.map=new Map("map",{
        	basemap:"",
        	/*minZoom:9,
        	maxZoom:11,*/
        	extent:bounds,
            logo:false,
            nav:false,
            slider:false
        });
        //显示Ztree,控制图层的显示与隐藏，及调整图层顺序
        mapApp.showTree();
        //显示图层工具条，暂用于搜索图层及显示图例
        mapApp.showTool();
        
    });