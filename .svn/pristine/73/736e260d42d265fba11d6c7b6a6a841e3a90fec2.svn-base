/**
 * Created by zhl on 18-3-13.
 */

define(["dojo/_base/declare", "esri/tasks/query","esri/tasks/IdentifyTask","esri/tasks/IdentifyParameters","esri/tasks/QueryTask","esri/geometry/Extent","dojo/_base/array","esri/symbols/SimpleFillSymbol",
        "myModules/popups/popups","dojo/text!myModules/query/queryPanel.html","dojo/text!myModules/query/qiujiao.html","dojo/dom-construct","jquery/jquery-ui-1.12.0/jquery-ui","myModules/config/layerConfig",
        "myModules/utils/colResizable-1.5.min","esri/symbols/PictureMarkerSymbol","esri/geometry/Point"],
    function (declare, Query,IdentifyTask,IdentifyParameters,QueryTask,Extent,arrayUtils,SimpleFillSymbol,Popups,queryPanel,qiujiao,domConstruct,JqueryUi,LayerConfig,colResizable,PictureMarkerSymbol,Point) {
        	var searchValue = 0;
        	var childrenNode;
			var h = declare("QueryLayer", null, {
            map: new Object(),
            clickEvent:null,
            panel:null,//查询面板
            dragging: false,
            constructor: function (map) {
                this.map = map;
                this.panel=queryPanel;
                this.qiujiaopanel=qiujiao;
            },
            init:function(){
            	//清空dialog中的内容
            	$("#queryDiv").html("");
            	domConstruct.place(this.panel,"queryDiv","last");//在div中添加html
            	//弹出图层查询对话框
            	$("#queryDiv").dialog({
            		resizable:false,
            		width:"350px",
            		position:{
            			at:"right-300px top+400px",
            			my:"right",
            			of:window
            		}
            	});
            	//设置可拖动 
            	$(".line_h").bind('mousedown',function(){
            			dragging   = true;
            			document.onmousemove = function(e){
            				topOffset = $("#queryContainer").offset().top;
                    		if (dragging) {
                    			var clickY = e.pageY;
                    			if(clickY > (topOffset+75)&&clickY<=(topOffset+575)) {
                    				$(".line_h").css('top', clickY- topOffset+40+'px');//按钮移动
                    				$(".line_h").prev().css("height",clickY-topOffset + 'px');	
                    				$(".line_h").next().css("height",$("#queryContainer").height()-$(".line_h").prev().height()+'px');
                    			} else if(clickY > (topOffset+575)){
                    				$(".line_h").css('top', '615px');//按钮移动
                    				$(".line_h").prev().css("height",'575px');	
                    				$(".line_h").next().css("height",'75px');
                    			}  else {
                    				$(".line_h").css('top', '115px');//按钮移动
                    				$(".line_h").prev().css("height",'75px');	
                    				$(".line_h").next().css("height",'575px');
                    			} 
                    		}
                    		};
                    	document.onmouseup=function(e) {
                    	    dragging = false;
                    	    e.cancelBubble = true;
                    	};
            	});
            	$(":radio").bind('click',function(){
            		searchValue = $(this).val(); //获取单选框的值 0是对多图层查询  1是对单图层查询
            	});
            	this.identifyLayers();
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
            identifyLayers: function () {
                var map = this.map;
                map.setMapCursor("help");  //设置鼠标样式
                var _this=this;
                mapApp.clickEvent=map.on("click",function(e){
                	//若查询框已关闭，则将其打开
                	$("#queryDiv").dialog("open");
                	//获取select 选中的option的值，0-可见图层，1-所有图层
                	var queryFlag=$("#selectScope option:selected").val();
                	var taskList=[];
                    for(var i=0;i<mapApp.dynamicLayersUrls.length;i++){
                    	//if(searchValue=="0"){
                    		//针对可见的图层进行查询
                        	if(mapApp.dynamicLayersUrls[i].visible){
                        		var identifyTask=new IdentifyTask(mapApp.dynamicLayersUrls[i].layerUrl); 	
                                taskList.push(identifyTask);
                                mapApp.dynamicLayersUrls[i].layerName=map.getLayer(mapApp.dynamicLayersUrls[i].layerId).layerInfos[0].name;
                        	}
                    	/*}else{
                    		//针对所有的图层进行查询   --新需求下暂时取消该功能
                        	var identifyTask=new IdentifyTask(mapApp.dynamicLayersUrls[i].layerUrl);
                            taskList.push(identifyTask);
                            mapApp.dynamicLayersUrls[i].layerName=map.getLayer(mapApp.dynamicLayersUrls[i].layerId).layerInfos[0].name;
                    	}   */ 	
                    };
                    for(var i=0;i<mapApp.tiledLayersUrls.length;i++){
                    	//if(searchValue=="0"){
                    		//针对可见的图层进行查询
                        	if(mapApp.tiledLayersUrls[i].visible){
                        		var identifyTask_tile=new IdentifyTask(mapApp.tiledLayersUrls[i].layerUrl); 	
                                taskList.push(identifyTask_tile);
                                mapApp.tiledLayersUrls[i].layerName=map.getLayer(mapApp.tiledLayersUrls[i].layerId).layerInfos[0].name;
                        	}
                    	/*}else{
                    		//针对所有的图层进行查询   --新需求下暂时取消该功能      	
                        	var identifyTask_tile=new IdentifyTask(mapApp.tiledLayersUrls[i].layerUrl);                          
                            taskList.push(identifyTask_tile);
                            mapApp.tiledLayersUrls[i].layerName=map.getLayer(mapApp.tiledLayersUrls[i].layerId).layerInfos[0].name;
                    	} */ 
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
                	//console.log(taskList);//需要查询的服务数
                	if(searchValue=="0"){
                		for(var j=0;j<taskList.length;j++){
                        		taskList[j].execute(identifyParam,function(resultSet){
                        			if(resultSet.length>0){
                        				//console.log(resultSet);
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
                    	for(var j=taskList.length-1;j>taskList.length-2;j--){ //查询最上面一层
                    		taskList[j].execute(identifyParam,function(resultSet){
                    			if(resultSet.length>0){
                    				//console.log(resultSet);
                    				//高亮显示;
                    				arrayUtils.forEach(resultSet,function(result){
                    					result.feature.setSymbol(new SimpleFillSymbol());
                    					mapApp.map.graphics.add(result.feature);
                    				});
                    				resultSets=resultSets.concat(resultSet);	
                    			}
                    		});	
                    	}
                	}               	
                	setTimeout(function(){
                		_this.creatZtree(resultSets);
                	},500); //查询时间如果过长，不如1500后调用creatztree，有时候点击时不会绘制区域  500时间则不出现该问题
                })
            },
            //生成查询结果树
            creatZtree:function(resultSets){
            	//清空ztree
            	$("#result_tree").html(""); 
            	$("#attriTable").html("");
            	//结果树设置
            	var setting = {
        				view: {
        					showIcon:false
        				},
        				callback: {
        					onClick:this.zTreeClick
        					//onNodeCreated:this.zTreeOnNodeCreated
        				},
        				data: {
        					simpleData: {
        						enable: true
        					}
        				}
        		};
            	//处理数据
            	var zNodes=[];
            	var preNode=null;  //处理tree数据时用来记录父节点
            	for(var i=0;i<resultSets.length;i++){
            		//debugger;
            		//当前节点
            		var node={}; 
            		var attributes=resultSets[i].feature.attributes;
            		//console.log(resultSets[i]);
            		//console.log(resultSets[i].layerName);//图层的name值
            		//console.log(attributes[resultSets[i].displayFieldName]);
            		if(attributes[resultSets[i].displayFieldName]!=undefined && attributes[resultSets[i].displayFieldName]!="Null"){     
            			//console.log(node);
            			node.name=attributes[resultSets[i].displayFieldName];
            			//alert(node.name==undefined);
            		}else{
            			node.name="默认属性未定义";
            		}
            		node.attributes=resultSets[i].feature.attributes;
            		node.layerName = resultSets[i].layerName;
            		//父节点
            		var obj={};
            		if(i==0){
            			obj.name=resultSets[i].layerName;
            			obj.open=true;
            			obj.children=[node];
            			preNode=obj;
            			childrenNode=obj.children;
            			console.log(childrenNode);
            			var attributes=childrenNode[0].attributes;
            			var html="<table><tr><th class='field'>字段名称</th><th class='value'>值</th></tr>";
            			if(attributes){
                    		for(var key in attributes){
                    			//利用if过滤哪些字段显示
                        		if(attributes[key]!="Null"&&attributes[key]!=" "&&key !="OBJECTID"&&key !="SHAPE"&&key !="SHAPE_Length"&&key !="SHAPE_Area"){
                        			
                        			html+="<tr><td class='field'>"+key+"</td>";
                        			if(key=="所在图则"){
                        				html+="<td class='value'><a href='"+baseUrl+"/getfile?filefold="+encodeURI(encodeURI(attributes["控规名称"]))+"&filename="+encodeURI(encodeURI(attributes[key]+".pdf"))+"' target='_blank' style='cursor:pointer;'>"+attributes[key]+"</a></td></tr>";
                        				//console.log(html);
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
                    	$("#attriTable").html(html);
                		
            		}else{
            			if(resultSets[i].layerName==resultSets[i-1].layerName){
            				preNode.children.push(node);
            			}else{
            				zNodes.push(preNode);
            				obj.name=resultSets[i].layerName;
                			obj.open=true;
                			obj.children=[node];
                			preNode=obj;
            			}
            		}
            	}
            	if(preNode!=null){
            		zNodes.push(preNode);
            	}
            	$.fn.zTree.init($("#result_tree"), setting, zNodes); 
            },
            zTreeOnNodeCreated: function(event, treeId, treeNode){
    			//console.log(treeNode);
    			//var t_class = treeNode.tId+"_a";
    			//$("#"+t_class).addClass("ztreeActive");
    			//var s = "images/ghtc.png";
    			//var t_class = treeNode.tId+"_ico";
    			//$("#"+t_class).addClass("iconfont icon-ditu");
    			//$("#"+t_class).css("background-image","url("+s+")");
    		},
            zTreeClick:function(event,treeId,treeNode){
            	//console.log(treeNode);
            	$("#attriTable").html(""); 
            	//console.log("treeId"+treeId);
            	//debugger;
            	//动态生成属性列表
            	var attributes=treeNode.attributes;
            	var layerName = treeNode.layerName;
            	var layerConfig = mapApp.layerConfig;
            	//layerConfig[layerName].alias[0];   //取值方法
            	//console.log(treeNode);//getPreNode  parentTId
            	//console.log(attributes);
            	var html="<table><tr><th class='field'>字段名称</th><th class='value'>值</th></tr>";
            	/*if(attributes){
            		for(var i=0;i<layerConfig[layerName].formatFields.length;i++){
                		var key = layerConfig[layerName].formatFields[i];
                		if(attributes[key]!="Null"&&attributes[key]!=" "){
                			
                			html+="<tr><td class='field'>"+layerConfig[layerName].alias[i]+"</td>";
                			if(key=="所在图则"){
                				html+="<td class='value'><a href='"+baseUrl+"/getfile?filefold="+encodeURI(encodeURI(attributes["控规名称"]))+"&filename="+encodeURI(encodeURI(attributes[key]+".pdf"))+"' target='_blank' style='cursor:pointer;'>"+attributes[key]+"</a></td></tr>";
                				//console.log(html);
                			}else if(key.search("面积")!= -1){
                				html+="<td class='value'>"+attributes[key]+"(㎡)</td></tr>";
                			}else if(key.search("Pixel")!= -1){
                				html+="<td class='value'>"+attributes[key]+"(%)</td></tr>";
                			}else{
                				html+="<td class='value'>"+attributes[key]+"</td></tr>";
                			}
                		}
                	}
            	}else{
            		html+="<tr><td class='field'></td>";
            		html+="<td class='value'></td></tr>";
            	}    */        	
            	if(attributes){
            		for(var key in attributes){
            			//利用if过滤哪些字段显示
                		if(attributes[key]!="Null"&&attributes[key]!=" "&&key !="OBJECTID"&&key !="SHAPE"&&key !="SHAPE_Length"&&key !="SHAPE_Area"){
                			
                			html+="<tr><td class='field'>"+key+"</td>";
                			if(key=="所在图则"){
                				html+="<td class='value'><a href='"+baseUrl+"/getfile?filefold="+encodeURI(encodeURI(attributes["控规名称"]))+"&filename="+encodeURI(encodeURI(attributes[key]+".pdf"))+"' target='_blank' style='cursor:pointer;'>"+attributes[key]+"</a></td></tr>";
                				//console.log(html);
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
            	$("#attriTable").append(html);
            	//设置表格可拖动 
        		$("#attriTable table").colResizable({
        			liveDrag:true, 
        			gripInnerHtml:"<div class='grip'></div>", 
        			draggingClass:"dragging"
        		});
            },   
            /**
             * 取消点击查询
             */
            cancelClickQuery: function () {
                var map = this.map;
                map.graphics.clear();
                mapApp.clickEvent.remove();
                map.setMapCursor("default");
                mapApp.clickEvent=null;
                $("#queryDiv").dialog('close');
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