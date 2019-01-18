/**
 * Created by wjj on 16-7-5.
 */
define(["dojo/_base/declare","esri/geometry/Point","esri/geometry/Polyline"],function(declare,Point,Polyline){

    var h = declare("Utils",null,{
        constructor:function(){

        }
    });
    /**
     * Method: polylineMidpoint
     * 计算polyline的中点
     */
    h.getPolylineMidpoint = function(polyline) {
        var midPoints=[];
        if (polyline && polyline.paths) {
            var path = polyline.paths[0];
            if (path) {
                var length = path.length;
                midPoints.push(path[parseInt(length / 2)]);
            }
        }
        return midPoints;

    }
    /**
     * Method: calculateMidpoint
     * 计算两个点所连成的线段的的中点
     *
     * Parameters:
     * pointA - {<SuperMap.Geometry.Point>} 第一个点
     * pointB -  {<SuperMap.Geometry.Point>} 第二个点
     *
     * Returns:
     * {<SuperMap.Geometry.Point>} 返回中点
     */
    h.calculateMidpoint = function(pointA, pointB) {
        var midPoint = new Point((pointA.x + pointB.x) / 2, (pointA.y + pointB.y) / 2);
        return midPoint;

    }

    /**
     * Method: calculateDistance
     * 计算两点间的距离
     *
     * Parameters:
     * pointA - {<SuperMap.Geometry.Point>} 第一个点
     * pointB -  {<Point>} 第二个点
     *
     * Returns:
     * {<Point>} 返回两点间的距离值
     */
    h.calculateDistance = function(pointA, pointB) {
        var distance =Math.sqrt(Math.pow(pointA.x - pointB.x, 2) + Math.pow(pointA.y - pointB.y, 2));
        return distance;

    }
    /**
     * Method: toVector
     * 计算两点间的向量
     *
     * Parameters:
     * pointA - {<SuperMap.Geometry.Point>} 起点
     * pointB -  {<SuperMap.Geometry.Point>} 终点
     *
     * Returns:
     * {<Point>} 返回两点间的向量
     */
    h.toVector = function (pointA,pointB)
    {
        return new Point(pointA.x-pointB.x,pointA.y-pointB.y);
    }
    /**
     * Method: calculateVector
     * 计算和基准向量v夹角为a、长度为d的目标向量（理论上有两个，一左一右）
     *
     * Parameters:
     * v - {<Point>} 基准向量
     * a - {Number} 目标向量和基准向量的夹角，默认为90度，这里的单位使用弧度
     * d - {Number} 目标向量的长度，即模，默认为1，即单位向量
     *
     * Returns:
     * {Array(<Point>)} 回目标向量数组（就两个向量，一左一右）
     */
    h.calculateVector = function(v, a, d) {
        if (!a) a = Math.PI / 2;
        if (!d) d = 1;

        //定义目标向量的头部   x 坐标
        var x_1;
        var x_2;
        //定义目标向量的头部   y 坐标
        var y_1;
        var y_2;
        //定义目标向量，一左一右
        var v_l;
        var v_r;

        //计算基准向量v的模
        var d_v = Math.sqrt(v.x * v.x + v.y * v.y);

        //基准向量的斜率为0时，y值不能作为除数，所以需要特别处理
        if (v.y == 0) {
            //计算x,会有两个值
            x_1 = x_2 = d_v * d * Math.cos(a) / v.x;
            //根据v.x的正负判断目标向量的左右之分
            if (v.x > 0) {
                //计算y
                y_1 = Math.sqrt(d * d - x_1 * x_1);
                y_2 = -y_1;
            }
            else if (v.x < 0) {
                //计算y
                y_2 = Math.sqrt(d * d - x_1 * x_1);
                y_1 = -y_2;
            }
            v_l = new Point(x_1, y_1);
            v_r = new Point(x_2, y_2);
        }
        //此为大多数情况
        else {
            //转换为y=nx+m形式
            var n = -v.x / v.y;
            var m = d * d_v * Math.cos(a) / v.y;
            //
            //x*x + y*y = d*d
            //转换为a*x*x + b*x + c = 0
            var a = 1 + n * n;
            var b = 2 * n * m;
            var c = m * m - d * d;
            //计算x,会有两个值
            x_1 = (-b - Math.sqrt(b * b - 4 * a * c)) / (2 * a);
            x_2 = (-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a);
            //计算y
            y_1 = n * x_1 + m;
            y_2 = n * x_2 + m;
            //当向量向上时
            if (v.y >= 0) {
                v_l = new Point(x_1, y_1);
                v_r = new Point(x_2, y_2);
            }
            //当向量向下时
            else if (v.y < 0) {
                v_l = new Point(x_2, y_2);
                v_r = new Point(x_1, y_1);
            }
        }
        return [v_l, v_r];
    };
    /**
     * Method: calculateIntersection
     * 计算两条直线的交点
     * 通过向量的思想进行计算，需要提供两个向量以及两条直线上各自一个点
     *
     * Parameters:
     * v_1 - {<Point>} 直线1的向量
     * v_2 - {<Point>} 直线2的向量
     * points1 - {<Point>} 直线1上的任意一点
     * points2 - {<Point>} 直线2上的任意一点
     *
     * Returns:
     * {Array(<Point>)} 返回交点
     */
    h.calculateIntersection = function(v_1, v_2, point1, point2) {
        //定义交点的坐标
        var x;
        var y;
        //如果向量v_1和v_2平行
        if (v_1.y * v_2.x - v_1.x * v_2.y == 0) {
            //平行也有两种情况
            //同向
            if (v_1.x * v_2.x > 0 || v_1.y * v_2.y > 0) {
                //同向直接取两个点的中点
                x = (point1.x + point2.x) / 2;
                y = (point1.y + point2.y) / 2;
            }
            //反向
            else {
                //如果反向直接返回后面的点位置
                x = point2.x;
                y = point2.y;
            }
        }
        else {
            //
            x = (v_1.x * v_2.x * (point2.y - point1.y) + point1.x * v_1.y * v_2.x - point2.x * v_2.y * v_1.x) / (v_1.y * v_2.x - v_1.x * v_2.y);
            if (v_1.x != 0) {
                y = (x - point1.x) * v_1.y / v_1.x + point1.y;
            }
            //不可能v_1.x和v_2.x同时为0
            else {
                y = (x - point2.x) * v_2.y / v_2.x + point2.y;
            }
        }
        return new Point(x, y);

    };

    /**
     * Method: calculateAngularBisector
     * 计算两个向量的角平分线向量
     *
     * Parameters:
     * v1 - {<Point>} 向量1
     * v2 - {<Point>} 向量2
     *
     * Returns:
     * {Array(<Point>)} 返回角平分线向量
     */
    h.calculateAngularBisector = function(v1, v2) {
        //计算角平分线的思想是取两个向量的单位向量，然后相加
        var d1 = Math.sqrt(v1.x * v1.x + v1.y * v1.y);
        var d2 = Math.sqrt(v2.x * v2.x + v2.y * v2.y);
        return new Point(v1.x / d1 + v2.x / d2, v1.y / d1 + v2.y / d2);
    };
    /**
     * Method: calculateIntersectionFromTwoCorner
     * 通过三角形的底边两端点坐标以及底边两夹角，计算第三个点坐标
     *
     * Parameters:
     * pointS - {<Point>} 底边第一个点
     * pointE - {<Point>} 底边第二个点
     * a_S - {Number} 底边和第一个点所在的另一条边的夹角
     * a_E - {Number} 底边和第二个点所在的另一条边的夹角
     *
     * Returns:
     * {Array(<Point>)} 返回顶点（理论上存在两个值）
     */
    h.calculateIntersectionFromTwoCorner = function(pointS, pointE, a_S, a_E) {
        if (!a_S) a_S = Math.PI / 4;
        if (!a_E) a_E = Math.PI / 4;

        //起始点、结束点、交点加起来三个点，形成一个三角形
        //斜边（起始点到结束点）的向量为
        var v_SE = new Point(pointE.x - pointS.x, pointE.y - pointS.y);
        //计算起始点、交点的单位向量
        var v_SI_lr = this.calculateVector(v_SE, a_S, 1);
        //获取
        var v_SI_l = v_SI_lr[0];
        var v_SI_r = v_SI_lr[1];
        //计算结束点、交点的单位向量
        var v_EI_lr = this.calculateVector(v_SE, Math.PI - a_S, 1);
        //获取
        var v_EI_l = v_EI_lr[0];
        var v_EI_r = v_EI_lr[1];
        //求左边的交点
        var pointI_l = this.calculateIntersection(v_SI_l, v_EI_l, pointS, pointE);
        //计算右边的交点
        var pointI_r = this.calculateIntersection(v_SI_r, v_EI_r, pointS, pointE);
        return [pointI_l, pointI_r];
    };

    /**
     * 根据点绘制圆
     * @param origin 起点
     * @param radius 半径
     * @param sides
     * @param r
     * @param angel倾角
     * @returns array[]
     */
    h.createCircleByPoint = function(origin, radius, sides,r,angel){
        var rR = r*Math.PI/(180*sides);
        var rotatedAngle, x, y;
        var points = [];
        for(var i=0; i<sides; ++i) {
            rotatedAngle = rR*i;
            x = origin.x + (radius * Math.cos(rotatedAngle));
            y = origin.y + (radius * Math.sin(rotatedAngle));
            points.push(new Point(x, y));
        }
        rotatedAngle = r*Math.PI/180;
        x = origin.x + (radius * Math.cos(rotatedAngle));
        y = origin.y + (radius * Math.sin(rotatedAngle));
        points.push(new Point(x, y));
        return points
    }
    /**
     * 绘制二次贝塞尔曲线
     * @param p0 带有xy属性的json或者arcgis点Point
     * @param p1
     * @param p2
     * @param t
     * @returns {Array} arcgis Point数组
     */
    h.createBezier2 = function(points,t){
        if(points.length!==3){
            return null;
        }
        if(t == null || t == undefined || t == ""){
            t = 20;
        }

        var p0 = points[0];//起点
        var p1 = points[1];//控制点
        var p2 = points[2];//终点

        var points = [];
        for(var i=0;i<t;i++){
            var m = i/t;
            var x = (1-m)*(1-m)*p0.x+2*m*(1-m)*p1.x+m*m*p2.x;
            var y = (1-m)*(1-m)*p0.y+2*m*(1-m)*p1.y+m*m*p2.y;
            points.push(new Point(x,y));
        }
        return points;
    }
    /**
     * 经纬度转墨卡托
     * @param lonlat
     * @returns {{x: number, y: number}}
     */
    h.lonlat2mercator = function(lonlat){
        var mercator={x:0,y:0};
        var x = lonlat.x *20037508.34/180;
        var y = Math.log(Math.tan((90+lonlat.y)*Math.PI/360))/(Math.PI/180);
        y = y *20037508.34/180;
        mercator.x = x;
        mercator.y = y;
        return mercator ;
    }

    /**
     * 墨卡托转经纬度
     * @param mercator
     * @returns {{x: number, y: number}}
     */
    h.mercator2lonlat = function(mercator){
        var lonlat={x:0,y:0};
        var x = mercator.x/20037508.34*180;
        var y = mercator.y/20037508.34*180;
        y= 180/Math.PI*(2*Math.atan(Math.exp(y*Math.PI/180))-Math.PI/2);
        lonlat.x = x;
        lonlat.y = y;
        return lonlat;
    }
    /**
     * 将json串转换成字符串
     */
    h.jsonToStr = function(jsonObj){
        var jStr = "{ ";
        for(var item in jsonObj){
            jStr += "'"+item+"':'"+jsonObj[item]+"',";
        }
        jStr += " }";
        return jStr;
    }
    /**
     * 判断属性，如果属性包含“.”的方法名，按照正确的属性返回
     * @param attr
     * @returns {Array}
     */
    h.correctAttrName = function (attr) {
        var newAttr = "";

        if (attr == null || typeof attr === 'undefined') {
            newAttr =  "";
        }else{
            if (attr.indexOf(".") != -1) {
                newAttr = attr.split(".")[1];
            } else {
                newAttr = attr;
            }
        }
        return newAttr;
    }
    /**
     * 补全桩号后三位 内部方法
     * @param stake
     * @returns {*}
     */
    function completion(stake){
        if(!stake && stake !== 0)
            return stake;
        var stakeType = getStakeType(stake),
            stake = String(stake).trim(),
            separ;

        if(stakeType === 1){//k20、K20
            separ = '+';
        } else if(stakeType === 2){//20、20.123
            separ = '.';
        }

        if(!separ || separ.trim().length === 0)
            return stake;

        var stakeArrs = stake.split(separ),
            miLength;

        if(stakeArrs && stakeArrs.length === 2){
            miLength = stakeArrs[1].length;
        } else if(stakeArrs && stakeArrs.length === 1){
            miLength = 0;
        }

        var newStake = stakeArrs[0] + separ + stakeArrs[1];
        if(miLength && String(miLength).trim().length != 0){
            for(var i = 0; ( i < (3 - Number(miLength)) && Number(miLength >= 0) ); i++ ){
                newStake += '0';
            }
            return newStake;
        }

        return stake;

    };
    /**
     * 获取桩号类型：
     *		0:		不识别的格式
     *		1:		K20、k20、k20+123、K20+123、k20-123、K20-123
     *		2:		20、20.123
     * @param stake
     * @returns {number}
     */
    function getStakeType(stake){
        if(/^[kK]\d+([\+\-]\d+)?$/.test(stake)){
            return 1;
        }else if(/^\d+(\.\d+)?$/.test(stake)){
            return 2;
        }else{
            return 0;
        }
    };
    /**
     * 格式化为可读桩号，输入20.123，输出K20+123
     * @param value
     * @returns {*}
     */
    h.stakeRenderer = function(value){
        var tempvalue=value.toString().split('.')[1];
        if(tempvalue&&tempvalue.length>3){
            value=value.toFixed(3);
        }
        var stakeType = getStakeType(value),
            stake = value + '';

        if(stakeType === 1){
            stake = stake.replace(/k/g, 'K');
        }else if(stakeType === 2){
            stake = 'K' + stake.replace(/\./g, '+');
        }
        else{
            return value;
        }
        return completion(stake).replace(/\+0*$/g, '');
    }
    /**
     * 格式化车道数
     * @param inflenceLaneNum
     * @returns {string}
     */
    h.laneNumRenderer = function(inflenceLaneNum){
        if(!inflenceLaneNum)
        {
            return "";
        }
        var result = "";
        var  laneNums = inflenceLaneNum.split(',');
        for(var i = 0; i < laneNums.length; i++)
        {
            if(laneNums[i].indexOf('d') != -1)
            {
                var nums = laneNums[i].split('d');
                var num = nums[1];
                if(num == "j")
                {
                    result += " LJ ↓ ";
                }
                else
                {
                    result += " L" + num + " ↓ ";
                }
            }
            else
            {
                if (laneNums[i] == "j"){
                    result += " LJ ↑ ";
                }else {
                    result += " L" + laneNums[i] + " ↑ ";
                }
            }
        }
        return result;
    }
    /**
     * 判断是否为同一个点
     * @param p1
     * @param p2
     * @returns {boolean}
     */
    h.atPoint = function(p1,p2){
        if(p1.x==p2.x&&p1.y==p2.y)
            return true;
        else
            return false;
    }
    h.deepClone = function(obj){
        //返回传递给他的任意对象的类
        function isClass(o){
            if(o===null) return "Null";
            if(o===undefined) return "Undefined";
            return Object.prototype.toString.call(o).slice(8,-1);
        }
        var result,oClass=isClass(obj);
        //确定result的类型
        if(oClass==="Object"){
            result={};
        }else if(oClass==="Array"){
            result=[];
        }else{
            return obj;
        }
        for(key in obj){
            var copy=obj[key];
            if(isClass(copy)=="Object"||"Array"){
                result[key]=arguments.callee(copy);//递归调用
            }else{
                result[key]=obj[key];
            }
        }
        return result;
    }
    h.strFormat = function (attributeValue) {
        if (typeof attributeValue === 'undefined' || attributeValue == null) {
            return "";
        } else {
            return attributeValue;
        }
    }
    h.UUID = function() {
        var s = [];
        var hexDigits = "0123456789abcdef";
        for (var i = 0; i < 36; i++) {
            s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
        }
        s[14] = "4";
        s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);

        s[8] = s[13] = s[18] = s[23] = "-";

        var uuid = s.join("");
        return uuid;
    }
    h.previewVms = function initEmulationVms(div_id,n_width,n_height,obj){
        var width = n_width*2+20;
        var height = n_height*2+20;
        var str = "<div style='width:"+width+"px;height:"+height+"px;border-style: solid; border-width: 5px;background-color:black;overflow: hidden;'><div name = 'vms_content_icon_div' style='position: absolute;z-index: 9;margin-left: 5px;margin-top: 5px;'></div>"+
            "<div name='vms_content_font_div'>";
        str += "<textarea style='border: none;color:red;background-color:black;font-size:96px;width:"+(width-10)+"px;height:"+(height-10)+"px;line-height:1;magin-top:-7px;font-family: Simsun !important;resize:none;overflow: hidden;' name='vms_content_str' readonly></textarea>";
        str += "</div></div>";
        $("#"+div_id).html(str);
        var i = 0;

        /*立即显示第一屏*/
        var item = obj[0];
        //添加图标
        var iconNum = item["icon_num"];
        if(iconNum!=""&&iconNum!=undefined){
            addcfmByIconNum(div_id,iconNum,true,item["icon_size"]);
            changeIconPosition(div_id,item["icon_x"],item["icon_y"]);//初始化图片位置
        }else{
            delcfm(div_id,true);
        }
        //添加文字
        $("#"+div_id+" [name='vms_content_str']").html(item["str"]);

        changeFontColor(div_id,item["font_color"]);//初始化颜色
        changeFontSize(div_id,item["font_size"]);//初始化字体大小
        changeFontPosition(div_id,item["font_x"],item["font_y"]);//初始化文字位置
        changeWordSpace(div_id,item["word_space"]);//改变字体间距
        /*********循环显示**********/
        var view = setInterval(function(){
            i++;
            if(i>=obj.length){
                i = 0;
            }
            if($("#"+div_id) == undefined || $("#"+div_id).html() == undefined) {
                clearInterval(view);
            }
            var item = obj[i];
            //添加图标
            var iconNum = item["icon_num"];
            if(iconNum!=""&&iconNum!=undefined){
                var width = n_width*2+20;
                addcfmByIconNum(div_id,iconNum,true,item["icon_size"]);
                changeIconPosition(div_id,item["icon_x"],item["icon_y"]);//初始化图片位置
            }else{
                delcfm(div_id,true);
            }
            //添加文字
            $("#"+div_id+" [name='vms_content_str']").html(item["str"]);

            changeFontColor(div_id,item["font_color"]);//初始化颜色
            changeFontSize(div_id,item["font_size"]);//初始化字体大小
            changeFontPosition(div_id,item["font_x"],item["font_y"]);//初始化文字位置
            changeWordSpace(div_id,item["word_space"]);//改变字体间距
        },3000);
    }
    return h;

})
