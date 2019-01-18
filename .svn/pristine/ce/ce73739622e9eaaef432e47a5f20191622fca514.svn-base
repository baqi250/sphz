/**
 * Created by zhl on 18-3-9.
 */

define([
    "dojo/_base/declare",
    "jquery/jquery",
    "jquery/jqueryform",
    "dojo/text!myModules/tree/tree.html",
    "ztree/js/jquery.ztree.core",
    "ztree/js/jquery.ztree.exedit",
    "ztree/js/jquery.ztree.excheck",
    "ztree/js/jquery.ztree.exhide",
    "dijit/Dialog",
    "jquery/jquery-ui-1.12.0/jquery-ui.min",
    "esri/graphic",
    "esri/geometry/Polygon",
    "esri/symbols/SimpleFillSymbol",
    "esri/layers/GraphicsLayer",
    "esri/tasks/QueryTask",
    "esri/tasks/query",
    "esri/layers/FeatureLayer",
    "esri/tasks/Geoprocessor",
    "esri/tasks/JobInfo",
    "dojo/dom-construct",
    "dojo/dom",
    "dojo/_base/xhr",
    "myModules/utils/utils",
    "myModules/utils/colResizable-1.5.min"
],
    function(declare,jquery,jqueryform,treeHtml,core,exedit,excheck,exhide,Dialog,JqueryUi,Graphic,Polygon,SimpleFillSymbol,GraphicsLayer,QueryTask,Query,FeatureLayer,Geoprocessor,JobInfo,domConstruct,dom,xhr,Utils){
	var polygon1;
	var polygon2;
	var myresults;
	var h=declare("MapTree",null,{
		nodeList:[],//用于存储搜索到的节点 
		treeObj:null,//图层树
		rMenu:null,//右键菜单
		panel:null,//树页面
		dragId:0,//拖拽id
		base:this,
		slider:null,//图层透明度滑块
		constructor:function(){
			this.panel=treeHtml;
		},
		 /**
         * 查询节点
         */
		searchNode: function (){//查询节点
			var _this=this;
			_this.updateNodes(false);
			this.nodeList=[];
			var keywords= $.trim($("#keyword").val());
			if(keywords==null||keywords==''){
				
			}
			else{
				this.nodeList=treeObj.getNodesByParamFuzzy("name", keywords);
			}
			_this.updateNodes(true);
			//console.log(this.nodeList);
		},
		 /**
         * 获取节点的根节点
         * @param node tree节点
         */
		getRootParent: function(node){//获取根节点
			var _this=this;
			var node1=node.getParentNode();
		 	if(node1==null||typeof(node1)==undefined){
		 		return node;
			}else{
				return _this.getRootParent(node1);
			}
		},
		 /**
         * 根据搜索结果重置节点显示状态
         * @param highlight 是否高亮显示
         */
		updateNodes: function(highlight){//根据搜索结果重置节点显示状态
			var _this=this;
			for( var i=0, l=this.nodeList.length; i<l; i++) {
				this.nodeList[i].highlight = highlight;
				treeObj.updateNode(this.nodeList[i]);
				treeObj.expandNode(_this.getRootParent(this.nodeList[i]),true,true,true);
			}
		},
		 /**
         * 设置节点颜色
         * @param treeId 
         * @param treeNode
         */
		getFontCss: function(treeId, treeNode) {//设置节点颜色
			//console.log(treeNode);
			var font;
			//console.log(font);
			if(!!treeNode.highlight){
				font={"font-weight":"bold","color":"130cf9"};
			}else{
				switch(treeNode.level){
					case 0:
					font={"font-weight":"bold","color":"#3c8dbc"};
					break;
					case 1:
					font={"font-weight":"normal","color":"#333"};
					break;
				}
				//font={"font-weight":"bold","color":"red"};
			}
			<!-- 给初始化时选中的节点，添加背景颜色-->
			/*if(treeNode.hasBgColor==true){
				if(!!treeNode.highlight){
					font={"font-weight":"bold","color":"130cf9"};
				}else{
					switch(treeNode.level){
						case 0:
						font={"font-weight":"bold","color":"red","background-color": "green"};
						break;
						case 1:
						font={"font-weight":"normal","color":"#333","background-color": "green"};
						break;
					}
					//font={"font-weight":"bold","color":"red"};
				}
			}else{
				if(!!treeNode.highlight){
					font={"font-weight":"bold","color":"130cf9"};
				}else{
					switch(treeNode.level){
						case 0:
						font={"font-weight":"bold","color":"red"};
						break;
						case 1:
						font={"font-weight":"normal","color":"#333"};
						break;
					}
					//font={"font-weight":"bold","color":"red"};
				}
			}*/
			//console.log(font);
			return font;
			//return (!!treeNode.highlight) ? {color:"#130cf9", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
		},
		/**
         * 选中节点事件
         */
		zTreeOnCheck: function(event, treeId, treeNode){//选中节点事件
			var _this=this;
			var nodes = treeObj.getChangeCheckedNodes();
			for (var i=0, l=nodes.length; i<l; i++) {
				if(new MapTree().urlIsNull(nodes[i].url)){
					
				}else{
					mapApp.setLayerVisible(nodes[i].url,nodes[i].layer_name,nodes[i].layer_type,nodes[i].checked);					
				}
				nodes[i].checkedOld = nodes[i].checked;
			}
		},
		/**
         * 拖动节点事件
         */
		zTreeOnDrop: function(event, treeId, treeNodes, targetNode, moveType){//拖动节点事件
			var nodes = treeObj.transformToArray(treeObj.getNodes());
			var len=nodes.length;
			var index=0;
			/*mapApp.setLayerOrder("sjsthx",13);
			mapApp.setLayerOrder("ldbhgh",7);*/
			for(var i=len-1;i>=0;i--){
				if(new MapTree().urlIsNull(nodes[i].url)){
					
				}else{
					index+=1;
					mapApp.setLayerOrder(nodes[i].layer_name,index);
				}
			}
			//console.log(nodes);
		},
		 /**
         * 判断tree节点url是否为空
         * @param url 
         */
		urlIsNull: function(url){
			if(url==null||url=="null"||url=="undefined"||url==""){
				return true;
			}else{
				return false;
			}
		},
		 /**
         * ztree节点拖拽之前，记住拖拽节点的父Id
         * @param url 
         */
		zTreeBeforeDrag: function(treeId, treeNodes) {
			if(treeNodes[0].pId==null){
				this.dragId=0;
			}else{
				this.dragId=treeNodes[0].pId;
			}
		},
		 /**
         * ztree节点拖拽之后，判断目标节点和拖拽节点的父节点是否相同，来进一步做同级拖拽处理
         * @param url 
         */
		zTreeBeforeDrop: function(treeId, treeNodes, targetNode, moveType) {
			var targetId=0;
		    if(targetNode.pId==null){
		    	targetId=0;
		    }else{
		    	targetId=targetNode.pId;
		    }
			if(targetId==this.dragId){
		    //	alert("拖拽成功");
		    	return true;
		    }else{
		    	alert("只允许同级拖拽");
		    	return false;
		    }
		},
		zTreeOnNodeCreated: function(event, treeId, treeNode){
			//console.log(treeNode.tId);
			//var t_class = treeNode.tId+"_a";
			//$("#"+t_class).addClass("ztreeActive");
			//var s = "images/ghtc.png";
			//var t_class = treeNode.tId+"_ico";
			//$("#"+t_class).addClass("iconfont icon-ditu");
			//$("#"+t_class).css("background-image","url("+s+")");
		},
		showDialog: function(){
			//alert(1);
			pannel=document.createElement("div");
			pannel.setAttribute("id","diaDiv");
			document.body.appendChild(pannel);
			var dia=new dijit.Dialog({title:"test"},pannel);
			dia.show();
		},
		/**
         * 右键菜单
         */
		OnRightClick: function(event, treeId, treeNode){//右键菜单
			var _this=this;
			this.rMenu = $("#rMenu");//右键菜单
			if (treeNode&&!treeNode.isParent) {//noR属性为true表示禁止右键菜单
				treeObj.selectNode(treeNode);
				//_this.showRMenu(event.clientX, event.clientY);
				$("#rMenu ul").show();
				var x=event.clientX-50;
				var y=event.clientY;
			    y += document.body.scrollTop;
			    x += document.body.scrollLeft;
			    this.rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});
				$("body").bind("mousedown", function(event1){
					if (!(event1.target.id == "rMenu" || $(event1.target).parents("#rMenu").length>0)) {
						$("#rMenu").css({"visibility" : "hidden"});
					}
				});
			}
		},
		/**
         * 初始化ztree,ztree顺序和图层加载顺序相反
         * @param trees 节点数据
         */
		renderTree: function(trees){//初始化tree
			var _this=this;
			var setting = {//ztree属性设置
				view: {
					dblClickExpand: false,
					selectedMulti: false,
					fontCss: _this.getFontCss,
					showLine:false
				},
				check: {
					enable: true,
					chkboxType:{ "Y" : "ps", "N" : "ps" }
				},
				callback: {
					onRightClick: _this.OnRightClick,
					onCheck: _this.zTreeOnCheck,
					onDrop: _this.zTreeOnDrop,
					beforeDrag: _this.zTreeBeforeDrag,
					beforeDrop: _this.zTreeBeforeDrop
					//onNodeCreated:_this.zTreeOnNodeCreated
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				edit:{
					enable: true,
					showRemoveBtn:false,
					showRenameBtn:false,
					drag:{
						prev:true,
						next:true,
						inner:false
					}
				}
			};
			var i;
			var len=trees.length;
			//var ids=localNodes.split(",");
			var json='[';//ztree标准格式数据
			var tree1,tree2;
			for(i=0;i<len;i++){
				tree1=trees[i];//ztree数据
				tree2=trees[len-i-1];//图层数据
				//if(ids.indexOf(trees[i].id.toString())>-1){
				if(tree1.status==1){//初始化时节点是否被选中
					json+='{ id:'+tree1.id+', pId:'+tree1.pid +', name:"'+tree1.name+'",  url:"'+tree1.url+'", type:"'+tree1.type+'", layer_type:'+tree1.layer_type+', layer_name:"'+tree1.layer_name+
					'", sort:'+tree1.sort+',  checked:true, hasBgColor:true},';
					//console.log(tree2);
					if(_this.urlIsNull(tree2.url)){
						
					}else{
						if(tree2.status==1)
							//mapApp.setLayerVisible(tree2.url,tree2.layer_name,tree2.layer_type,true,tree2.dataurl);
							mapApp.setLayerVisible(tree2.url,tree2.layer_name,tree2.layer_type,true);
						else
							//mapApp.setLayerVisible(tree2.url,tree2.layer_name,tree2.layer_type,false,tree2.dataurl);
							mapApp.setLayerVisible(tree2.url,tree2.layer_name,tree2.layer_type,false);
					}
				}
				else{
					json+='{ id:'+tree1.id+', pId:'+tree1.pid +', name:"'+tree1.name+'",  url:"'+tree1.url+'", type:"'+tree1.type+'", layer_type:'+tree1.layer_type+', layer_name:"'+tree1.layer_name+'", sort:'+tree1.sort+'},';
						if(_this.urlIsNull(tree2.url)){
							
						}else{
							if(tree2.status==1)
								//mapApp.setLayerVisible(tree2.url,tree2.layer_name,tree2.layer_type,true,tree2.dataurl);
								mapApp.setLayerVisible(tree2.url,tree2.layer_name,tree2.layer_type,true);
							else
								//mapApp.setLayerVisible(tree2.url,tree2.layer_name,tree2.layer_type,false,tree2.dataurl);
								mapApp.setLayerVisible(tree2.url,tree2.layer_name,tree2.layer_type,false);
						}
				}
			}
			json=json.substring(0, json.lastIndexOf(',')); 
			json+=']';
			treeObj = $.fn.zTree.init($("#tree"), setting, eval('('+json+')'));
			treeObj.expandAll(true);
			//console.log(treeObj.getCheckedNodes(true)[0].hasBgColor);
		},
		/**
         * 初始化透明度滑块
         */
		initSlider: function(){
			this.slider=$("#slider");
	   		var s= this.slider.slider({
	   			min:0,
	   			max:1,
	   			range:"min",
	   			value:1,
	   			step:0.1,
	   			slide:function(event,ui){
	   				$("#num2").text(ui.value);
	   			}
	   		});
	   		//console.log(this.slider);
		},		
		init: function(){//初始化ztree
			var _this=this;		
			/*polygon2=new FeatureLayer({
				url:"http://192.168.109.44:6080/arcgis/rest/services/lsgg/lglygh/MapServer/0",
	            id: "lygh",
	            outFields: ["*"],
	            visible: false
        	});*/					
			rMenu = $("#rMenu");
			domConstruct.place(this.panel,"treeDiv","last");
			$.get("../map/maplist",function (rc){
				var trees=rc.maps;//树数据
				_this.renderTree(trees);//初始化ztree				
				/*polygon1=new FeatureLayer("http://192.168.109.111:6080/arcgis/rest/services/gp/polygon1/MapServer/0",{
			          mode:FeatureLayer.MODE_SNAPSHOT,
			          outFields:["*"]
			        });
				mapApp.map.addLayer(polygon1);
				
				polygon2=new FeatureLayer("http://192.168.109.44:6080/arcgis/rest/services/qxxq/kg_yd/MapServer/1",{
					  id:"testPolygon",
			          mode:FeatureLayer.MODE_SNAPSHOT,
			          outFields:["*"]
			        });
				mapApp.map.addLayer(polygon2)*/;
			});
			
			_this.initSlider();
			
			//查找树节点
			$("#search_tree").on('click',function(){_this.searchNode()});
			$(document).keypress(function(e) {
			       var eCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
			        if (eCode == 13){
			        	_this.searchNode();
			        }
			});
			
			//切换图层树面板显示隐藏
			$("#switch").click(function(){
				var status=$("#switch").attr("status");
				//$("#treeDiv").fadeToggle();
				if(status=="open"){
					//$("#treeDiv").css("display","none");
					$("#treeDiv").hide();
					$("#switch").attr("status","close");
					$("#switch").css("background-image","url('../resources/images/close.png')");
					//console.log($("#switch").attr("status"));
					return;
				}
				if(status=="close"){
					//$("#treeDiv").css("display","block");
					$("#treeDiv").show();
					$("#switch").attr("status","open");
					$("#switch").css("background-image","url('../resources/images/open.png')");
					//console.log($("#switch").attr("status"));
					return;
				}
				
			});
			
			//设置图层透明度弹窗
			$("#opacity").click(function(){
				var _this=$(this).parent;
				//console.log(_this);
				$("#rMenu").css({"visibility" : "hidden"});
				var layerName=treeObj.getSelectedNodes()[0].layer_name;
				var opacity=mapApp.map.getLayer(layerName).opacity;
				//console.log(opacity);
				$("#slider").slider("value",opacity);
				$("#num2").text(opacity);
				
				dijit.byId("myDialog").show();
			});
			
			//设置图层透明度
			$("#ok").click(function(){//校验透明度输入的是否为0-1之间的一位小数
				var opv=$("#num2").text();
				var selectNode=treeObj.getSelectedNodes()[0];
				mapApp.setLayerOpacity(selectNode.layer_name,parseFloat(opv));
				dijit.byId("myDialog").hide();
			});
			$("#zoom").click(function(){
				$("#rMenu").css({"visibility" : "hidden"});
				var selectNode=treeObj.getSelectedNodes()[0];
				mapApp.zoomLayerToCenter(selectNode.layer_name);
				
				/*console.log("111");
				
				mapApp.zoomLayerToCenter("zhjtgh");*/
			});
			$("#datainfo").click(function(){
				var _this=$(this).parent;
				//console.log(_this);
				$("#rMenu").css({"visibility" : "hidden"});	
				$("#infoDiv").html("");
				var html="<table><tr><th class='field'>名称</th><th class='value'>描述信息</th></tr>";
				$.ajax({
					url:"../layer/layerinfos",
					type:"post",
					async:false,
					dataType:"json",
					cache:false,
					success:function(data){
						//console.log(JSON.stringify(data.layerinfos));
						var jdata = eval('('+data.layerinfos+')');
						//var jdata = JSON.stringify(data.layerinfos);
						var layerName=treeObj.getSelectedNodes()[0].layer_name;
						if(jdata[layerName].数据名称!=null && jdata[layerName].数据名称!=""){
							html+="<tr><td class='field'>"+"数据名称"+"</td>";
							html+="<td class='value'>"+jdata[layerName].数据名称+"</td></tr>";	
						}						
						if(jdata[layerName].数据版本!=null && jdata[layerName].数据版本!=""){							
							html+="<tr><td class='field'>"+"数据版本"+"</td>";
							html+="<td class='value'>"+jdata[layerName].数据版本+"</td></tr>";
						}
						if(jdata[layerName].字段内容!=null && jdata[layerName].字段内容!=""){
							html+="<tr><td class='field'>"+"字段内容"+"</td>";
							html+="<td class='value'>"+jdata[layerName].字段内容+"</td></tr>";
						}
						if(jdata[layerName].数据来源!=null && jdata[layerName].数据来源!=""){
							html+="<tr><td class='field'>"+"数据来源"+"</td>";
							html+="<td class='value'>"+jdata[layerName].数据来源+"</td></tr>";	
						}
						if(jdata[layerName].数据精度!=null && jdata[layerName].数据精度!=""){
							html+="<tr><td class='field'>"+"数据精度"+"</td>";
							html+="<td class='value'>"+jdata[layerName].数据精度+"</td></tr>";
						}
						if(jdata[layerName].数据格式!=null && jdata[layerName].数据格式!=""){
							html+="<tr><td class='field'>"+"数据格式"+"</td>";
							html+="<td class='value'>"+jdata[layerName].数据格式+"</td></tr>";
						}
						if(jdata[layerName].入库时间!=null && jdata[layerName].入库时间!=""){
							html+="<tr><td class='field'>"+"入库时间"+"</td>";
							html+="<td class='value'>"+jdata[layerName].入库时间+"</td></tr>";
						}
					}
				});				
				html+="</table>";
				//alert(html);
				$("#infoDiv").append(html);
				//弹出图层查询对话框
            	$("#infoDiv").dialog({
            		resizable:false,
            		width:"400px",
            		position:{
            			at:"left+650px top+150px",
            			my:"right",
            			of:window
            		}
            	});
            	//设置表格可拖动 
        		$("#infoDiv table").colResizable({
        			liveDrag:true, 
        			gripInnerHtml:"<div class='grip'></div>", 
        			draggingClass:"dragging"
        		});
				//dijit.byId("infoDialog").show();
			});
			/*$("#uploadcad").click(function(){
				//alert(1);
					$("#cadForm").ajaxSubmit(function(data){
						//console.log(data.geometry);
         				esriConfig.defaults.io.proxyUrl = "DotNet/proxy.ashx";
         				esriConfig.defaults.io.alwaysUseProxy = false;			    	
						var cadlayer=new esri.layers.GraphicsLayer({id:"cadlayer"});
						var graresult=new esri.layers.GraphicsLayer({id:"graresult"});
						var map = mapApp.map;
						//debugger;
						var myPolygon=data.geometry;
				    	var polygon=new Polygon(myPolygon);
				    	//console.log(polygon);				    	
				    	polygon.setSpatialReference(map.spatialReference);
				    	var gra=new Graphic(polygon);
				        var fillsymbol=new esri.symbol.SimpleFillSymbol();
					    fillsymbol.setColor(new dojo.Color([255,255,60,1]));
				    	gra.setSymbol(fillsymbol);
				    	cadlayer.add(gra);
				    	//console.log(gra);
				    	map.addLayer(cadlayer);
				    	map.addLayer(graresult);
				    	//add by zd  得到了polygon 根据它得到featureset
				    	var features1=[];
				    	for (var i = 0; i < cadlayer.graphics.length; i++) {
				    		features1.push(cadlayer.graphics[i]);
				    	}
				    	for (var i = 0; i < polygon1.graphics.length; i++) {
         					features1.push(polygon1.graphics[i]);
         				}
				    	var featureSet1 = new esri.tasks.FeatureSet();
          				featureSet1.features = features1;
         				featureSet1.spatialReference = map.spatialReference;
         				var features2=[];
         				for (var i = 0; i < polygon2.graphics.length; i++) {
         					features2.push(polygon2.graphics[i]);
         				}
         				var featureSet2 = new esri.tasks.FeatureSet();
          				featureSet2.features = features2;
         				featureSet2.spatialReference = map.spatialReference;
         				//console.log(featureSet1);
         				//console.log(featureSet2);
         				//---------------求交------------------//
						var gptask = new esri.tasks.Geoprocessor("http://192.168.109.111:6080/arcgis/rest/services/gp/intersect2/GPServer/intersect1");
						var gpParams = {"lvdi":featureSet1,"guotu":featureSet2};
						gptask.submitJob(gpParams, gpfunc1);
						function gpfunc1(result) {
							
				           // console.log(result);
				           // debugger;
				            var jobId = result.jobId;
				            var status = result.jobStatus;
				           // if(status ===JobInfo.STATUS_SUCCEEDED) {
				              gptask.getResultData(jobId, "h", addResults);    //第二个参数要和发服务的结果集名称一致
				            //}
				          }
						function addResults(results){	
							//myresults = results;
							//myresults = Utils.jsonToStr(results);
							console.log(results);
							var fillsymbol1=new esri.symbol.SimpleFillSymbol();
						    fillsymbol1.setColor(new dojo.Color([255,100,160,1]));
					            var features = results.value.features;
					            if(features.length>0) {
					              for (var i = 0, length = features.length; i != length; ++i) {
					                var feature = features[i];
					                feature.setSymbol(fillsymbol1);
					                graresult.add(feature);
					              }
					              //alert("提示计算成功！");
					            }
					            else{
					             // alert("提示计算失败！");
					            }
				            var attrs = [];
				            var geos = [];
				            for(var i=0;i<features.length;i++){
				            	attrs.push(features[i].attributes);
				            	geos.push(features[i].geometry.rings[0]);
				            }
				            //debugger;
				           // console.log(map.spatialReferenc);//通过打断点，发现先执行的是mapqiujiao.js后执行这里。。。
				            var j = {
			            		"attrs":attrs,
			            		"geometry":{"rings":geos,"spatialReference":{"wkid":map.spatialReference}}			            		
				            };
				            myresults = JSON.stringify(j);
					        localStorage.removeItem("values4");					        
					        localStorage.setItem("values4",myresults);
					        //console.log(j);
					        setTimeout('top.mainFrame.tabAddHandler("1","查询结果","map/saveresults.do")',2000);
					     }
						//mapApp.queryQiujiaoLayers();
						//console.log(localStorage.getItem("values"));
						//setTimeout('top.mainFrame.tabAddHandler("1","aa","map/saveresults.do")',3000);
						//top.mainFrame.tabAddHandler("1","aa","map/saveresults.do");
			    	});   
				});*/
		}
		
	});
	return h;
})