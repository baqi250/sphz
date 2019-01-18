/**
 * Created by wjj on 16-7-15.
 * 缩放类工具
 */
define(["dojo/_base/declare"],function(declare){
    var h = declare("ZoomTools",null,{
        map:null,
        constructor:function(map){
            this.map = map;
        },
        /**
         * 缩放至范围
         * @param extent,范围地理坐标
         *
         */
        zoomToExtent:function(extent,extentConfig){
            var zoomNum=extentConfig.length-1;
            var extent_x=extent.xmax-extent.xmin;
            var extent_y=extent.ymax-extent.ymin;
            var xFlag=extent_x-extentConfig[zoomNum][0];
            var yFlag=extent_y-extentConfig[zoomNum][1];
            while((xFlag>=0||yFlag>=0)&&zoomNum>0){
                zoomNum=zoomNum-1;
                xFlag=extent_x-extentConfig[zoomNum][0];
                yFlag=extent_y-extentConfig[zoomNum][1];
            }
            this.map.centerAndZoom(extent.getExtent().getCenter(),zoomNum);
        },
        centerToExtent:function(){

        }
    });
    return h;
})