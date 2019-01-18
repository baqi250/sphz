/**
 * Created by zhl on 18-3-19.
 */
define([
    "dojo/_base/declare",
    "jquery/jquery",
    "dojo/text!myModules/tool/tool.html",
    "dojo/dom-construct",
    "myModules/config/layerConfig",
    "esri/layers/GraphicsLayer",
    "esri/geometry/Point",
    "esri/graphic",
    "esri/symbols/PictureMarkerSymbol"
],
    function(declare,jquery,toolHtml,domConstruct,LayerConfig,GraphicsLayer,Point,Graphic,PictureMarkerSymbol){
	var h=declare("MapTool",null,{
		panel:null,//工具条
		constructor:function(){
			this.panel=toolHtml;
		},
		//初始化
		init: function(){ 
			var objects;
			//ajax获取点数据
			$.ajax({
				url:"../photos/pointslist",
				type:"post",
				async:false,
				dataType:"json",
				cache:false,
				success:function(data){
					//console.log(data);
					objects = data.photos;				
				}				
			});
			var graphicsLayer = new GraphicsLayer({id:"photos"});
			domConstruct.place(this.panel,"tools","last");		
			//点击事件
			$("#tool-search").on("click",function(){
				var flag=$(this).hasClass("active");
				if(flag){
					$("#tcSearch").removeClass("glyphicon glyphicon-info-sign");
					$("#tcSearch").addClass("glyphicon glyphicon-search");
					mapApp.cancelClickQuery();
				}else{
					$("#tcSearch").removeClass("glyphicon glyphicon-search");
					$("#tcSearch").addClass("glyphicon glyphicon-info-sign");
					mapApp.queryLayersByClick();
				}
				$(this).toggleClass("active");				
			})
			$("#tool-legend").on("click",function(){
				var flag=$(this).hasClass("active");
				if(flag){
					mapApp.removeLegend();
				}else{
					mapApp.showLegend();
				}
				$(this).toggleClass("active");
			})
			$("#tool-3d").on("click",function(){
	            var curWwwPath=window.document.location.href;
	            var pathName=window.document.location.pathname;
	            var pos=curWwwPath.indexOf(pathName);
	            var localhostPaht=curWwwPath.substring(0,pos);
	            var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
				window.open(localhostPaht+projectName+"/photogram/examples/langu3D.html");
			})
			$("#poimanage").on("click",function(){
				var map = mapApp.map;
				var graphic = null;  
				for (var i = 0, il = objects.length; i < il; i++) {
                    var geo = new Point(objects[i].lon,objects[i].lat,map.spatialReference);
                    //var graphic = null;                   
                    var pictureMarkerSymbol = new PictureMarkerSymbol("images/poi.png",32,32);                
                    graphic = new Graphic(geo, pictureMarkerSymbol, objects[i]);
                    
                    if (graphic != null) {
                        //featureSet.push(graphic);
                    	graphicsLayer.add(graphic);
                    }
                }
				map.addLayer(graphicsLayer);
				graphicsLayer.enableMouseEvents();
				$("#poiclear").css("display","");
				$("#poimanage").css("display","none");
			})
			$("#poiclear").on("click",function(){
				mapApp.removeLayer("photos");
				$("#poiclear").css("display","none");
				$("#poimanage").css("display","");
			})
        	graphicsLayer.on("click",function(data){
        		var pinyin = data.graphic.attributes.pinyin;
        		var url = photosUrl+"/"+pinyin+"/"+pinyin+".html";
        		var id = data.graphic.attributes.id;
        		var name = data.graphic.attributes.name;
        		top.mainFrame.tabAddHandler(id,name,url);
        	})
		}
		
	});
	return h;
})