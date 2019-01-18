/**
 * Created by zd on 18-4-16.
 */
define([
    "dojo/_base/declare",
    "jquery/jquery",
    "dojo/text!myModules/photos/photos.html",
    "dojo/dom-construct",
    "myModules/config/layerConfig",
    "esri/layers/FeatureLayer",
    "esri/geometry/Point",
    "esri/layers/GraphicsLayer"
],
    function(declare,jquery,photosHtml,domConstruct,LayerConfig,FeatureLayer,Point,GraphicsLayer){
	var h=declare("MapTool",null,{
		panel:null,
		constructor:function(){
			this.panel=photosHtml;
		},
		//初始化
		init: function(){ 
			var datas;
			$.ajax(
				url:"../photo/getPoint",
				dataType:"json",
				success:function(data){
					datas = data.rows;
				}
					
			);
			domConstruct.place(this.panel,"tools","last");
			//点击事件
			$("#tool-search").on("click",function(){
				var flag=$(this).hasClass("active");
				if(flag){
					mapApp.cancelClickQuery();
				}else{
					mapApp.queryLayersByClick();
				}
				$(this).toggleClass("active");
			})
			
		}
		
	});
	return h;
})