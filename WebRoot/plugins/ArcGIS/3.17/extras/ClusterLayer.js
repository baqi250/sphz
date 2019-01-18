define([
    "dojo/_base/declare",
    "dojo/_base/array",
    "esri/Color",
    "dojo/_base/connect",
    "myModules/popups/popups",
    "esri/SpatialReference",
    "esri/geometry/Point",
    "esri/graphic",
    "esri/symbols/SimpleMarkerSymbol",
    "esri/symbols/TextSymbol",
    "esri/symbols/PictureMarkerSymbol",
    "esri/dijit/PopupTemplate",
    "esri/layers/GraphicsLayer",
    "esri/symbols/Font","esri/geometry/geometryEngine",
    "myModules/utils/utils"
], function (declare, arrayUtils, Color, connect, Popups, SpatialReference, Point, Graphic, SimpleMarkerSymbol, TextSymbol, PictureMarkerSymbol, PopupTemplate, GraphicsLayer, Font, GeometryEngine,Utils) {
    return declare([GraphicsLayer], {
        constructor: function (options) {
            // options:
            //   data:  Object[]
            //     Array of objects. Required. Object are required to have properties named x, y and attributes. The x and y coordinates have to be numbers that represent a points coordinates.
            //   distance:  Number?
            //     Optional. The max number of pixels between points to group points in the same cluster. Default value is 50.
            //   labelColor:  String?
            //     Optional. Hex string or array of rgba values used as the color for cluster labels. Default value is #fff (white).
            //   labelOffset:  String?
            //     Optional. Number of pixels to shift a cluster label vertically. Defaults to -5 to align labels with circle symbols. Does not work in IE.
            //   resolution:  Number
            //     Required. Width of a pixel in map coordinates. Example of how to calculate:
            //     map.extent.getWidth() / map.width
            //   showSingles:  Boolean?
            //     Optional. Whether or graphics should be displayed when a cluster graphic is clicked. Default is true.
            //   singleSymbol:  MarkerSymbol?
            //     Marker Symbol (picture or simple). Optional. Symbol to use for graphics that represent single points. Default is a small gray SimpleMarkerSymbol.
            //   singleTemplate:  PopupTemplate?
            //     PopupTemplate</a>. Optional. Popup template used to format attributes for graphics that represent single points. Default shows all attributes as "attribute = value" (not recommended).
            //   maxSingles:  Number?
            //     Optional. Threshold for whether or not to show graphics for points in a cluster. Default is 1000.
            //   webmap:  Boolean?
            //     Optional. Whether or not the map is from an ArcGIS.com webmap. Default is false.
            //   spatialReference:  SpatialReference?
            //     Optional. Spatial reference for all graphics in the layer. This has to match the spatial reference of the map. Default is 102100. Omit this if the map uses basemaps in web mercator.

            this._clusterTolerance = options.distance || 50;
            this._clusterData = options.data || [];
            this._clusters = [];
            this._clusterLabelColor = options.labelColor || "#000";
            // labelOffset can be zero so handle it differently
            this._clusterLabelOffsetx = (options.hasOwnProperty("labelOffsetx")) ? options.labelOffsetx : -5;
            this._clusterLabelOffsety = (options.hasOwnProperty("labelOffsety")) ? options.labelOffsety : -5;
            // graphics that represent a single point
            this._singles = []; // populated when a graphic is clicked
            this._showSingles = options.hasOwnProperty("showSingles") ? options.showSingles : true;
            // symbol for single graphics
            var SMS = SimpleMarkerSymbol;
            this._singleSym = options.singleSymbol || new SMS("circle", 6, null, new Color("#888"));
            /*this._singleTemplate = options.singleTemplate || new PopupTemplate({ "title": "", "description": "{*}" });*/
            this._singleTemplate = options.singleTemplate || "";
            this._maxSingles = options.maxSingles || 1000;
            this._webmap = options.hasOwnProperty("webmap") ? options.webmap : false;
            this.layerConfig = options.layerConfig;
            this._sr = options.spatialReference || new SpatialReference({ "wkid": 102100 });

            this._zoomEnd = null;

            //设置字体样式
            this._setFontSize = (options.hasOwnProperty("fontSize")) ? options.fontSize : 14;
            this._setFontStyle = (options.hasOwnProperty("fontStyle")) ? options.fontStyle : Font.STYLE_NORMAL;
            this._setFontWeight = (options.hasOwnProperty("fontWeight")) ? options.fontWeight : Font.WEIGHT_BOLD;
            this._setFontFamily = (options.hasOwnProperty("fontFamily")) ? options.fontFamily : "微软雅黑";
            this._font = new Font(this._setFontSize, this._setFontStyle, Font.VARIANT_NORMAL, this._setFontWeight, this._setFontFamily);
            this.preClustePhic = null;
            this.phicsMap={};
        },

        // override esri/layers/GraphicsLayer methods
        _setMap: function (map, surface) {
            // calculate and set the initial resolution
            this._clusterResolution = map.extent.getWidth()/map.width; // probably a bad default...
            this._clusterGraphics();

            // connect to onZoomEnd so data is re-clustered when zoom level changes
            this._zoomEnd = connect.connect(map, "onZoomEnd", this, function () {
                // update resolution
                this._clusterResolution = this._map.extent.getWidth() / this._map.width;
                this.clear();
                //this._clusterData = [];
                map.infoWindow.hide();
                this._clusterGraphics();
                if (innerPubFun.symbolChange_layer) {
                    innerPubFun.symbolChange_layer.clear();
                    map.removeLayer(innerPubFun.symbolChange_layer);
                }
            });

            // GraphicsLayer will add its own listener here
            var div = this.inherited(arguments);
            return div;
        },

        _unsetMap: function () {
            this.inherited(arguments);
            connect.disconnect(this._zoomEnd);
        },

        remove: function (g) {
            this.inherited(arguments);
        },
        // public ClusterLayer methods
        add: function (p, hide) {

            if (p.declaredClass) {
                if (hide && hide == true) {
                    var data = {
                        "x": p.geometry.x,
                        "y": p.geometry.y,
                        "attributes": p.attributes,
                        "symbol": p.symbol
                    };
                    this._clusterData.push(data);

                }
                else {
                    return this.inherited(arguments);
                }


            }
            else {
                this._clusterData.push(p);
            }

        },

        clear: function () {
            // Summary:  Remove all clusters and data points.
            this.inherited(arguments);
            this._clusters.length = 0;
//        this._clusterData = [];
            this._clusters = [];
            this._singles = [];
            this.preClustePhic=null;
            this.phicsMap={};
        },

        clearSingles: function (singles) {
            // Summary:  Remove graphics that represent individual data points.
            var s = singles || this._singles;
            arrayUtils.forEach(s, function (g) {
                this.remove(g);
            }, this);
            this._singles.length = 0;
        },

        onClick: function (e) {
            var map = this._map;
            var layerConfig = this.layerConfig;
            if (e.graphic.attributes.isCluster && e.graphic.attributes.isCluster == true) {
                // remove any previously showing single features
                this.clearSingles(this._singles);
                if(this.preClustePhic!=null){
                    this._showCluster(this.preClustePhic);
                }

                // find single graphics that make up the cluster that was clicked
                // would be nice to use filter but performance tanks with large arrays in IE
                var singles = [];
                for (var i = 0, il = this._clusterData.length; i < il; i++) {
                    if (e.graphic.attributes.clusterId == this._clusterData[i].attributes.clusterId) {
                        singles.push(this._clusterData[i]);
                    }
                }
                if (singles.length > this._maxSingles) {
                    alert("Sorry, that cluster contains more than " + this._maxSingles + " points. Zoom in for more detail.");
                    return;
                } else {
                    // stop the click from bubbling to the map
                    e.stopPropagation();
                    if (innerPubFun.symbolChange_layer) {
                        innerPubFun.symbolChange_layer.clear();
                        this._map.removeLayer(innerPubFun.symbolChange_layer);
                    }
                    if (singles.length == 1) {
                        this.openinfowindow(e,map,layerConfig,singles);
                    }else if(singles.length<=6&&singles.length>1) {
                        this.preClustePhic = {
                            "x": e.graphic.geometry.x,
                            "y": e.graphic.geometry.y,
                            "attributes": e.graphic.attributes
                        };//记录前一个聚合graphic集合
                        this._removeCluster(e.graphic);//移除聚合效果
                        this._addSingles(singles);
                    }else {
                        if(this._map.getZoom()==this._map.getMaxZoom()){
                            this.preClustePhic = {
                                "x": e.graphic.geometry.x,
                                "y": e.graphic.geometry.y,
                                "attributes": e.graphic.attributes
                            };//记录前一个聚合graphic集合
                            this._removeCluster(e.graphic);//移除聚合效果
                            this._addSingles(singles);
                        }else{
                            this._map.centerAndZoom(e.graphic.geometry, this._map.getZoom() + 1);
                        }
                    }
                    //this._addSingles(singles);
                }
            } else {
//                if(e.graphic.attributes.isOffset == true){
//                    e.graphic.setGeometry(e.graphic.geometry.offset(10000,0));
//                }
                /*var multi = false;
                for(var i = 0; i < this.graphics.length; i++)
                {
                    if(e.graphic != this.graphics[i] && GeometryEngine.distance(e.graphic.geometry,this.graphics[i].geometry,"meters") < 50){
//                        this.graphics[i].setGeometry(this.graphics[i].geometry.offset(100000,0));
                        multi = true;
                    }
                }
                if(multi)
                    e.graphic.setGeometry(e.graphic.geometry.offset(map.getScale()*10000/1155583.4197444,0));*/
                this.openinfowindow(e,map,layerConfig);
            }
        },
        openinfowindow: function (e,map,layerConfig,singles) {
            if (!map.getLayer('symbolChange_layer')) {
                map.addLayer(innerPubFun.symbolChange_layer);
            } else {
                innerPubFun.symbolChange_layer.clear();
            }
            var url = null;
            if (e.graphic.symbol) {
                url = e.graphic.symbol.url;
                url = url.substring(0, url.length - 4) + '-hover.png';
            }
            pictureMarkerSymbol = new PictureMarkerSymbol(url, 45, 45);
            var point = e.graphic.geometry;
            graphic = new Graphic(point, pictureMarkerSymbol, e.graphic.attributes);
            graphic.setSymbol(pictureMarkerSymbol);
            innerPubFun.symbolChange_layer.add(graphic);
            if(singles){
                singles[0].geometry = new Point(singles[0].x, singles[0].y, this._sr);
                new Popups().createInfoWindow(map, layerConfig, singles[0]);
            }else{
                new Popups().createInfoWindow(map, layerConfig, e.graphic);
            }
            $(".close").click(function () {
                innerPubFun.symbolChange_layer.clear();
                map.removeLayer(innerPubFun.symbolChange_layer);
            });
        },
        // internal methods
        _clusterGraphics: function () {
            // first time through, loop through the points
            for (var j = 0, jl = this._clusterData.length; j < jl; j++) {
                // see if the current feature should be added to a cluster
                var point = this._clusterData[j];
                var clustered = false;
                var numClusters = this._clusters.length;
                for (var i = 0; i < this._clusters.length; i++) {
                    var c = this._clusters[i];
                    if (point.x&&this._clusterTest(point, c)) {
                        this._clusterAddPoint(point, c);
                        clustered = true;
                        break;
                    }
                }

                if (point.x&&!clustered) {
                    this._clusterCreate(point);
                }
            }
            this._showAllClusters();
        },

        _clusterTest: function (p, cluster) {
            var distance = (
                Math.sqrt(
                    Math.pow((cluster.x - p.x), 2) + Math.pow((cluster.y - p.y), 2)
                ) / this._clusterResolution
                );
            return (distance <= this._clusterTolerance);
        },

        // points passed to clusterAddPoint should be included
        // in an existing cluster
        // also give the point an attribute called clusterId
        // that corresponds to its cluster
        _clusterAddPoint: function (p, cluster) {
            // average in the new point to the cluster geometry
            var count, x, y;
            count = cluster.attributes.clusterCount;
            x = (p.x + (cluster.x * count)) / (count + 1);
            y = (p.y + (cluster.y * count)) / (count + 1);
            cluster.x = x;
            cluster.y = y;

            // build an extent that includes all points in a cluster
            // extents are for debug/testing only...not used by the layer
            if (p.x < cluster.attributes.extent[0]) {
                cluster.attributes.extent[0] = p.x;
            } else if (p.x > cluster.attributes.extent[2]) {
                cluster.attributes.extent[2] = p.x;
            }
            if (p.y < cluster.attributes.extent[1]) {
                cluster.attributes.extent[1] = p.y;
            } else if (p.y > cluster.attributes.extent[3]) {
                cluster.attributes.extent[3] = p.y;
            }

            // increment the count
            cluster.attributes.clusterCount++;
            // attributes might not exist
            if (!p.hasOwnProperty("attributes")) {
                p.attributes = {};
            }
            // give the graphic a cluster id
            p.attributes.clusterId = cluster.attributes.clusterId;
        },

        // point passed to clusterCreate isn't within the
        // clustering distance specified for the layer so
        // create a new cluster for it
        _clusterCreate: function (p) {
            var clusterId = this._clusters.length + 1;
            // console.log("cluster create, id is: ", clusterId);
            // p.attributes might be undefined
            if (!p.attributes) {
                p.attributes = {};
            }
            p.attributes.clusterId = clusterId;
            // create the cluster
            var cluster = {
                "x": p.x,
                "y": p.y,
                "attributes": {
                    "clusterCount": 1,
                    "clusterId": clusterId,
                    "extent": [ p.x, p.y, p.x, p.y ],
                    "isCluster": true
                },
                "symbol": p.symbol
            };
            this._clusters.push(cluster);
        },

        _showAllClusters: function () {
            for (var i = 0, il = this._clusters.length; i < il; i++) {
                var c = this._clusters[i];
                this._showCluster(c);
            }
        },

        _showCluster: function (c) {
            var clusterId = c.attributes.clusterId;
            var point = new Point(c.x, c.y, this._sr);
            var symbol=null;
            if(c.attributes.clusterCount==1){
               symbol= c.symbol;
            }
            var graphic_pic = this.add(
                new Graphic(
                    point,
                    symbol,
                    c.attributes
                )
            );
            // code below is used to not label clusters with a single point
            if (c.attributes.clusterCount == 1) {
                return;
            }
            // show number of points in the cluster
            var label = new TextSymbol(c.attributes.clusterCount.toString())
                .setColor(new Color(this._clusterLabelColor))
                .setOffset(this._clusterLabelOffsetx, this._clusterLabelOffsety)
                .setFont(this._font);
            var graphic_text = this.add(
                new Graphic(
                    point,
                    label,
                    c.attributes
                )
            );
            var data = {
                "graphic_pic":graphic_pic,
                "graphic_text":graphic_text
            }
            this.phicsMap[clusterId] = data;

        },
        _removeCluster: function (c) {
            if(c.attributes){
                var clusterId = c.attributes.clusterId;
                var phics = this.phicsMap[clusterId];
                var graphic_pic = phics["graphic_pic"];
                var graphic_text = phics["graphic_text"];
                this.remove(graphic_pic);
                this.remove(graphic_text);  //移除文本及图片graphic
            }
        },
        _addSingles: function (singles) {
            //判断singles是否有相同坐标的元素
            //若有，则将相同坐标的元素进行偏移
            var newArr = [],
                tempArr = [];
            for(var i=0;i<singles.length;i++){
                    if(singles[i+1]&&Utils.atPoint(singles[i],singles[i+1])){
                        tempArr.push(singles[i]);
                    } else {
                        tempArr.push(singles[i]);
                        newArr.push(tempArr.slice(0));
                        tempArr.length = 0;
                    }
            }
            for(var i=0;i<newArr.length;i++){
                // add single graphics to the map
                var index=0;
                arrayUtils.forEach(newArr[i], function (p) {
                    var g = new Graphic(
                        new Point(p.x+mapApp.map.getScale() / 9028 * 0.0008* index, p.y, this._sr),
                        p.symbol,
                        p.attributes
                    );
                    this._singles.push(g);
                    if (this._showSingles) {
                        this.add(g);
                    }
                    index++;
                }, this);
            }

            //this._map.infoWindow.setFeatures(this._singles);
        },

        _updateClusterGeometry: function (c) {
            // find the cluster graphic
            var cg = arrayUtils.filter(this.graphics, function (g) {
                return !g.symbol &&
                    g.attributes.clusterId == c.attributes.clusterId;
            });
            if (cg.length == 1) {
                cg[0].geometry.update(c.x, c.y);
            } else {
                console.log("didn't find exactly one cluster geometry to update: ", cg);
            }
        },

        _updateLabel: function (c) {
            // find the existing label
            var label = arrayUtils.filter(this.graphics, function (g) {
                return g.symbol &&
                    g.symbol.declaredClass == "esri.symbol.TextSymbol" &&
                    g.attributes.clusterId == c.attributes.clusterId;
            });
            if (label.length == 1) {
                // console.log("update label...found: ", label);
                this.remove(label[0]);
                var newLabel = new TextSymbol(c.attributes.clusterCount)
                    .setColor(new Color(this._clusterLabelColor))
                    .setOffset(this._clusterLabelOffsetx, this._clusterLabelOffsety)
                    .setFont(this._font);
                this.add(
                    new Graphic(
                        new Point(c.x, c.y, this._sr),
                        newLabel,
                        c.attributes
                    )
                );
                // console.log("updated the label");
            } else {
                console.log("didn't find exactly one label: ", label);
            }
        },

        // debug only...never called by the layer
        _clusterMeta: function () {
            // print total number of features
            console.log("Total:  ", this._clusterData.length);

            // add up counts and print it
            var count = 0;
            arrayUtils.forEach(this._clusters, function (c) {
                count += c.attributes.clusterCount;
            });
            console.log("In clusters:  ", count);
        }

    });
});

