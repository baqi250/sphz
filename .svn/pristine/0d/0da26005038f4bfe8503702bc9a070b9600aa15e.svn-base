/**
 * Created by zhl on 18-3-9.
 */
define([],function(){
	
	  /*
       * 移除图层
       * @param layerName 图层id,用于唯一标识图层
       */
	    mapApp.removeLayer=function(layerName){
     	    require(["myModules/query/staticLayer"],function(StaticLayer){
     		    new StaticLayer(mapApp.map).removeLayer(layerName);
     	    })	    	
        }
	    
       /*
        * 显示矢量图层
        * @param url featureLayer的服务地址   如 http://192.168.100.7:6080/arcgis/rest/services/lsgg/lggh/MapServer/1
        * @param layerName 图层id,用于唯一标识图层
        */
	    mapApp.showFeatureLayer=function(url,layerName){
        	require(["myModules/query/staticLayer"],function(StaticLayer){
        		new StaticLayer(mapApp.map).showFeatureLayer(url,layerName);
        	})	    	
        }
	    
	    /*
	    * 显示矢量地图服务
	    * @param url DynamicMapService的服务地址   如 http://192.168.100.7:6080/arcgis/rest/services/lsgg/lgszgc/MapServer
	    * @param layerName 图层id,用于唯一标识图层
	    */
		mapApp.showDynamicMapService=function(url,layerName){
	        require(["myModules/query/staticLayer"],function(StaticLayer){
	        	new StaticLayer(mapApp.map).showDynamicMapService(url,layerName);
	        })	    	
	    }
		
		/*
		 * 显示栅格地图服务
		 * @param url TiledMapService的服务地址   如 http://192.168.100.7:6080/arcgis/rest/services/lsgg/lgszgc/MapServer
		 * @param layerName 图层id,用于唯一标识图层
		 */
		mapApp.showTiledMapService=function(url,layerName){
		     require(["myModules/query/staticLayer"],function(StaticLayer){
		        new StaticLayer(mapApp.map).showTiledMapService(url,layerName);
		     })	    	
		}
		
		/*
		 * 使图层居中显示
		 * @param layerName 图层id,用于唯一标识图层
		 */
		mapApp.zoomLayerToCenter=function(layerName){
			 require(["myModules/query/staticLayer"],function(StaticLayer){
		        new StaticLayer(mapApp.map).zoomLayerToCenter(layerName);
			 })	 
		}
		
		/*
		 * 设置图层顺序
		 * @param index 图层顺序号
		 * @param layerName 图层id,用于唯一标识图层
		 */
		mapApp.setLayerOrder=function(layerName,index){
		     require(["myModules/query/staticLayer"],function(StaticLayer){
		        new StaticLayer(mapApp.map).setLayerOrder(layerName,index);
		     })	    	
		}
		
		/*
		 * 设置图层透明度
		 * @param opacity 图层透明度
		 * @param layerName 图层id,用于唯一标识图层
		 */
		mapApp.setLayerOpacity=function(layerName,opacity){
		     require(["myModules/query/staticLayer"],function(StaticLayer){
		        new StaticLayer(mapApp.map).setLayerOpacity(layerName,opacity);
		     })	    	
		}
		
		/*
		 * 设置图层是否可见
		 * @param layerUrl 图层url
		 * @param layerName 图层id,用于唯一标识图层
		 * @param layerType 图层类型，0，1，2
		 * @param isVisible true/false是否可见
		 */
		mapApp.setLayerVisible=function(layerUrl,layerName,layerType,isVisible){
		     require(["myModules/query/staticLayer"],function(StaticLayer){
		    	 new StaticLayer(mapApp.map).setLayerVisible(layerUrl,layerName,layerType,isVisible);
		     })	    	
		}
		/*
		 * 显示图层树
		 */
		mapApp.showTree=function(){
        	require(["myModules/tree/map_tree"],function(MapTree){
        		new MapTree().init();
        	})
	    	
        }
		/*
		 * 点击地图查找点击位置的所有图层
		 */
		mapApp.identifyLayers=function(panelId){
		     require(["myModules/query/queryLayer"],function(QueryLayer){
		       mapApp.QueryLayer=new QueryLayer(mapApp.map);
		       mapApp.QueryLayer.identifyLayers(panelId);
		     })	    	
		}
		mapApp.changeIdenType=function(searchValue){
		     require(["myModules/query/queryLayer"],function(QueryLayer){
		    	 mapApp.QueryLayer.changeSearchValue(searchValue);
		     })	    	
		}
		/*
		 * 求交查询结果展示
		 */
		mapApp.queryQiujiaoLayers=function(){
		     require(["myModules/query/queryLayer"],function(QueryLayer){
		        new QueryLayer(mapApp.map).getValues();
		     })	    	
		}
		/*
		 * 取消点击查询
		 */
		mapApp.cancelIdentify=function(){
		     require(["myModules/query/queryLayer"],function(QueryLayer){
		        new QueryLayer(mapApp.map).cancelClickQuery();
		     })	    	
		}
		/*
		 * 显示图例
		 */
		mapApp.showLegend=function(panelId){
		     require(["myModules/query/staticLayer"],function(StaticLayer){
		        new StaticLayer(mapApp.map).showLegend(panelId);
		     })	    	
		}
		/*
		 * 清除图例
		 */
		mapApp.removeLegend=function(){
			 require(["myModules/query/staticLayer"],function(StaticLayer){
			        new StaticLayer(mapApp.map).removeLegend();
			     })	 
		}
		/*
		 * 显示图层工具条
		 */
		mapApp.showTool=function(){
        	require(["myModules/tool/map_tool"],function(MapTool){
        		new MapTool().init();
        	})
        } 
		/*
		 * 根据地名地址数据查询并显示图层
		 */
		mapApp.showLayerByName=function(name,sucessCallback,errorCallback){
        	require(["myModules/query/queryLayer"],function(QueryLayer){
        		new QueryLayer(mapApp.map).showLayerByName(name,sucessCallback,errorCallback);
        	})
        }
		/*
		 * 根据地名地址数据查询属性信息
		 */
		mapApp.queryAttByName=function(name,sucessCallback,errorCallback){
        	require(["myModules/query/queryLayer"],function(QueryLayer){
        		new QueryLayer(mapApp.map).queryAttByName(name,sucessCallback,errorCallback);
        	})
        }
		/*
		 * 显示加载的cad图层
		 */
		mapApp.showCadLayer=function(geometry,fileName){
        	require(["myModules/query/staticLayer"],function(StaticLayer){
        		new StaticLayer(mapApp.map).showCadLayer(geometry,fileName);
        	})
        }
		/*
		 * 求图层与geometry的交集
		 */
		mapApp.getFeaturesByGeometry=function(geometry,layerUrl){
        	require(["myModules/query/staticLayer"],function(StaticLayer){
        		new StaticLayer(mapApp.map).getFeaturesByGeometry(geometry,layerUrl);
        	})
        }
})