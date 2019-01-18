/**
 * Created by zhl on 18-3-13.
 */

define(["dojo/_base/declare", "esri/tasks/query","esri/tasks/IdentifyTask","esri/tasks/IdentifyParameters","esri/tasks/QueryTask","esri/geometry/Extent","dojo/_base/array","esri/symbols/SimpleFillSymbol",
        "myModules/popups/popups","dojo/text!myModules/query/queryPanel.html","dojo/text!myModules/query/qiujiao.html","dojo/dom-construct","jquery/jquery-ui-1.12.0/jquery-ui","myModules/config/layerConfig",
        "myModules/utils/colResizable-1.5.min","esri/symbols/PictureMarkerSymbol","esri/geometry/Point"],
    function (declare, Query,IdentifyTask,IdentifyParameters,QueryTask,Extent,arrayUtils,SimpleFillSymbol,Popups,queryPanel,qiujiao,domConstruct,JqueryUi,LayerConfig,colResizable,PictureMarkerSymbol,Point) {
		var h = declare("QueryLayer", null, {
            map: new Object(),
            clickEvent:null,
            panel:null,//查询面板
            dragging: false,
            searchValue:"0",
            constructor: function (map) {
                this.map = map;
                this.panel=queryPanel;
                this.qiujiaopanel=qiujiao;
            },
            getValues:function(){
            	//清空dialog中的内容
            	$("#qiujiaoDiv").html("");
            	domConstruct.place(this.qiujiaopanel,"qiujiaoDiv","last");
            	//弹出图层查询对话框
            	$("#qiujiaoDiv").dialog({
            		resizable:false,
            		width:"350px",
            		position:{
            			at:"right-300px top+400px",
            			my:"right",
            			of:window
            		}
            	});
            },
            /**
             * identify查询
             */
            identifyLayers: function (panelId) {
                var map = this.map;
                map.setMapCursor("help");  //设置鼠标样式
                var _this=this;
                mapApp.clickEvent=map.on("click",function(e){
                	var taskList=[];
                	//瓦片图层
                	for(var i=0;i<mapApp.tiledLayersUrls.length;i++){
                        if(mapApp.tiledLayersUrls[i].visible){
                        	var identifyTask_tile=new IdentifyTask(mapApp.tiledLayersUrls[i].layerUrl); 	
                            taskList.push(identifyTask_tile);
                            mapApp.tiledLayersUrls[i].layerName=map.getLayer(mapApp.tiledLayersUrls[i].layerId).layerInfos[0].name;
                        }     	
                    }
                	//dynamicLayers图层
                    for(var i=0;i<mapApp.dynamicLayersUrls.length;i++){    	
                    	//针对可见的图层进行多图层查询
                        if(mapApp.dynamicLayersUrls[i].visible){
                        	var identifyTask=new IdentifyTask(mapApp.dynamicLayersUrls[i].layerUrl); 	
                            taskList.push(identifyTask);
                            mapApp.dynamicLayersUrls[i].layerName=map.getLayer(mapApp.dynamicLayersUrls[i].layerId).layerInfos[0].name;
                        }
                    }
                    
                	var identifyParam= new IdentifyParameters();
                	identifyParam.tolerance=1;
                	identifyParam.layerIds=[0];
                	identifyParam.returnGeometry=true;
                	identifyParam.width=map.width;
                	identifyParam.height=map.height;
                	identifyParam.layerOption=IdentifyParameters.LAYER_OPTION_ALL;
                	identifyParam.geometry=e.mapPoint;
                	identifyParam.mapExtent=map.extent;	
                	mapApp.map.graphics.clear();
                	var resultSets=[];
                	//多图层查询
                	if(_this.searchValue=="0"){
                		for(var j=taskList.length-1;j>0;j--){
                        		taskList[j].execute(identifyParam,function(resultSet){
                        			if(resultSet.length>0){
                        				//高亮显示;
                        				arrayUtils.forEach(resultSet,function(result){
                        					result.feature.setSymbol(new SimpleFillSymbol());
                        					mapApp.map.graphics.add(result.feature);
                        				});
                        				resultSets=resultSets.concat(resultSet);	
                        			}
                        		});	
                        	}
                	}else{   
                		//单图层查询，只查询最上边一层
                    	taskList[taskList.length-1].execute(identifyParam,function(resultSet){
                    		if(resultSet.length>0){
                    			//高亮显示;
                    			arrayUtils.forEach(resultSet,function(result){
                    				result.feature.setSymbol(new SimpleFillSymbol());
                    				mapApp.map.graphics.add(result.feature);
                    			});
                    			resultSets=resultSets.concat(resultSet);	
                    		}
                    	});
                	}               	
                	setTimeout(function(){
                		_this.showIdenResult(resultSets,panelId);
                	},500); //查询时间如果过长，不如1500后调用creatztree，有时候点击时不会绘制区域  500时间则不出现该问题
                })
            },
            /**
             * 改变identify的searchValue
             */
            changeSearchValue: function (searchValue) {
            	this.searchValue=searchValue;
            },
            /**
             * 显示identify查询结果
             */
            showIdenResult:function(resultSets,panelId) {
            	$("#"+panelId).html("");
                $panel = $("<div class='panel-group'></div>");
                if(resultSets.length==0){
                	var $panelItem = $("<div class='panel'>无查询结果</div>");
                	$panelItem.appendTo($panel);
                }else{
                	resultSets.forEach(function(element, index) {
                    	var title="";
                    	var attributes=element.feature.attributes;       
                		if(attributes[element.displayFieldName]!=undefined && attributes[element.displayFieldName]!="Null"){     
                			title=attributes[element.displayFieldName];
                		}else{
                			title="默认属性未定义";
                		}
                		var attributes=element.feature.attributes;
                		var layerName = element.layerName;
                		var id = "mapidenbox-collapse" + index;
                        var $panelItem = $("<div class='panel mapidenbox-panel'></div>");
                        var html = "<a data-toggle='collapse' data-parent='' href='#" + id + "'><div class='panel-heading'><h4 class='panel-title'>" + title + "</h4></div></a>";
                        html += "<div id='" + id + "' class='panel-collapse collapse'><div class='panel-body'><ul class='mapidenbox-table'>"; 
                        html+="<table><tr><th class='field'>字段名称</th><th class='value'>值</th></tr>";
            			if(attributes){
                    		for(var key in attributes){
                    			//利用if过滤哪些字段显示
                        		if(attributes[key]!="Null"&&attributes[key]!=" "&&key !="OBJECTID"&&key !="SHAPE"&&key !="SHAPE_Length"&&key !="SHAPE_Area"&&key !="Shape_Area"&&key !="Shape_Length"){
                        			html+="<tr><td class='field'>"+key+"</td>";
                        			if(key=="所在图则"){
                        				html+="<td class='value'><a href='"+baseUrl+"/getfile?filefold="+encodeURI(encodeURI(attributes["控规名称"]))+"&filename="+encodeURI(encodeURI(attributes[key]+".pdf"))+"' target='_blank' style='cursor:pointer;'>"+attributes[key]+"</a></td></tr>";
                        			}else{
                        				html+="<td class='value'>"+attributes[key]+"</td></tr>";
                        			}
                        		}
                        	}
                    	}else{
                    		html+="<tr><td class='field'></td>";
                    		html+="<td class='value'></td></tr>";
                    	}
                		html+="</table>";    
                        html += "</ul></div></div>";

                        $panelItem.html(html);
                        $panelItem.appendTo($panel);
                        //设置表格可拖动 
                		$(".mapidenbox-table table").colResizable();
                    });
                }
                
                $panel.appendTo($("#"+panelId));
                $("#"+panelId).show();
            },
            
            /**
             * 取消identify
             */
            cancelClickQuery: function () {
                var map = this.map;
                map.graphics.clear();
                mapApp.clickEvent.remove();
                map.setMapCursor("default");
                mapApp.clickEvent=null;
            },
            /**
             * 根据输入的地址名称进行查询
             */
            showLayerByName:function(name,sucessCallback,errorCallback){
        		var query = new Query();
        		var queryTask = new QueryTask("http://192.168.109.111:6080/arcgis/rest/services/lsgg/POI_lgw/FeatureServer/0");
        	    query.returnGeometry = true;
        	    query.outFields =  ["*"];	
        	    query.where = "NAME ='"+name+"'";
        		queryTask.execute(query,function(resultset){
        			mapApp.map.graphics.clear();
        			var features=resultset.features;
                	for(var i=0;i<features.length;i++){
                		var point=new Point(features[i].geometry.x,features[i].geometry.y,mapApp.map.spatialReference);
                		mapApp.map.centerAndZoom(point,8);
                		features[i].setSymbol(new PictureMarkerSymbol('images/poi.png',30,30));
        				mapApp.map.graphics.add(features[i]);
                	}
                	sucessCallback;
        		},function(){
        			errorCallback("查询失败，请稍后再试!");
        		});
            },
            /**
             * 根据输入的地址名称进行查询
             * 不显示点位
             */
            queryAttByName:function(name,sucessCallback,errorCallback){
            	var features=[];
        		var query = new Query();
        		var queryTask = new QueryTask("http://192.168.109.111:6080/arcgis/rest/services/lsgg/POI_lgw/FeatureServer/0");
        	    query.returnGeometry = false;
        	    query.outFields =  ["*"];
        	    query.where = "NAME like '%"+name+"%'";
        		queryTask.execute(query,function(results){
        			var resultCount = results.features.length;
                	for(var i=0;i<resultCount;i++){
                		features.push(results.features[i].attributes);
                	}
                	sucessCallback(features);
        		},function(){
        			errorCallback;
        		});    	    	
            }
        });
        return h;
    })