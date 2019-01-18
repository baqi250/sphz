/**
 * Created by zhl on 18-3-19.
 */
define([
    "dojo/_base/declare",
    "jquery/jquery",
    "dojo/text!myModules/tool/tool.html",
    "dojo/dom-construct",
    "myModules/config/layerConfig",
    "myModules/tool/whmaptoolbar",
    "esri/layers/GraphicsLayer",
    "esri/geometry/Point",
    "esri/graphic",
    "esri/symbols/PictureMarkerSymbol"
],
    function(declare,jquery,toolHtml,domConstruct,LayerConfig,Whmaptoolbar,GraphicsLayer,Point,Graphic,PictureMarkerSymbol){
	var h=declare("MapTool",null,{
		panel:null,//工具条
		constructor:function(){
			this.panel=toolHtml;
		},
		//初始化
		init: function(){
			//显示tool工具条
			$("#tools").whmaptoolbar({
                // 搜索功能参数
                searchboxOptions: {
                    url: "http://192.168.8.108:8180/cydt/js/1.txt",
                    language: "all",
                    category: [{
                        id: 0,
                        name: "地名地址"
                    }],
                    hasrights: [],
                    titleName: "NAME",
                    subtextName: {
                       /* "STREET": "街道",
                        "PROJECTAREA": "四至"*/
                    },
                    searchboxClickfunction: function($th, title, index) {
                    	mapApp.showLayerByName(title,null,function(e){
                    		console.log(e);
                    	});
                    },
                    searchboxEmptyfunction: function(title) {
                    	mapApp.map.graphics.clear();
                    },
                    searchboxImgfunction: function(url, title) {
                        alert("请定义图片的click函数:参数1为点击图片的url，参数2为title名称");
                    }
                },
                // identify功能参数
                idenboxOptions: {
                	btnClickFunction:function(panelId){
                		//初始化identify功能，多图层查询
                		mapApp.identifyLayers(panelId);
                	},
                	idenRadioClickFunction: function(searchType,panelId) {
                		//根据radio选项，进行identify
                		$("#"+panelId).hide();
                		mapApp.changeIdenType(searchType);
                    },
                    idenEmptyClickFunction:function(){
                    	//结束identify
                    	mapApp.cancelIdentify();
                    }
                },
                // 图例功能参数
                legendboxOptions: {
                	btnClickFunction:function(panelId){
                		//初始化图例功能
                		mapApp.showLegend(panelId);
                	}
                },
                // 工具箱功能参数
                toolboxOptions: {
                    data: [{
                        id: 0,
                        name: "CAD工具",
                        sub: [{
                            id: 0,
                            name: "CAD图层叠加",
                            url:"map/addcad"     
                        },{
                            id: 0,
                            name: "CAD叠加分析",
                            url:"map/cadjoin"     
                        }]
                    }],
                    toolClickFunction: function(url,caption) {
                    	 window.parent.layer.open({
                    	        type : 2,
                    	        title : caption,
                    	        maxmin : true,
                    	        shadeClose : false, // 点击遮罩关闭层
                    	        area : [ '400px', '500px' ],
                    	        content : url // iframe的url
                    	    });
                    },
                    toolEmptyClickFunction:function(){
                    	//清除地图上的cad图层
                    	for(var i=0;i<mapApp.cadLayerIds.length;i++){
                    		mapApp.removeLayer(mapApp.cadLayerIds[i]);
                    	}
                    	//清除分析结果
                    	mapApp.map.graphics.clear();
                    }
                },
                // 地图图层功能参数
                layerControlButtonOptions: {
                    imgsUrl: ["c1.jpg", "c2.jpg", "c3.jpg"],
                    layerControlClickFunction: function(index) {
                        alert("请定义地图图层控制单元的click函数：参数为当前点击的index");
                    }
                }
            });		
			
		}
		
	});
	return h;
})