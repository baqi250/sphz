/**
 * Created by wjj on 16-7-13.
 */
define(["dojo/_base/declare", "esri/InfoTemplate", "myModules/utils/utils"],
    function (declare, InfoTemplate, Utils) {
        var h = declare("Popups", null, {
            constructor: function () {	
            },
            createInfoTemplate: function(){
            	var infoTemplate = new InfoTemplate();
            	var infoContent = popupsHtml;
            	infoTemplate.setTitle("abc");
                infoTemplate.setContent(infoContent);
            	return infoTemplate;
            },
            /**
             * 创建点击查询气泡
             * @param layerConfig
             * @param featureSet
             */
            createInfoWinForQuery: function (map, featureSet,position) {
            	var infoContent="<div class='queryInfos'>";
            	for(var i=0;i<featureSet.length;i++){
            		var obj = featureSet[i].attributes;
            		infoContent+="<table class='info'>";
            		for(var key in obj){
            			infoContent+="<tr><td class='info-left'>"+key+"</td>";
            			infoContent+="<td class='info-right'>"+obj[key]+"</td><tr>";
            		}
            		infoContent+="</table>";
            	}
            	infoContent+="</div>";
                 
                if (map.infoWindow.isShowing) {
                    map.infoWindow.destroyDijits()
                }
                map.infoWindow.setTitle("查询结果");
                map.infoWindow.setContent(infoContent);
                map.infoWindow.show(position);
                map.centerAt(position);
            } 
            
        });
        return h;
    })