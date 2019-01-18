(function($) {
    $.fn.whmaptoolbar = function(options) {
        var defaultOptions = { // 默认设置
            width: "330px",
            height: "660px",
            top: "20px",
            right: "40px",
            btnlist: [{
                id: "whmaptoolbar-item-search",
                iclass: "fa-search"
            }, {
                id: "whmaptoolbar-item-identify",
                iclass: "fa-exclamation-circle"
            }, {
                id: "whmaptoolbar-item-tool",
                iclass: "fa-pie-chart"
            }, {
                id: "whmaptoolbar-item-legend",
                iclass: "fa-tachometer"
            }/*, {
                id: "whmaptoolbar-item-style",
                iclass: ""
            }*/],
            searchboxOptions: {
                placeholder: "请输入……",
                language: "all", // 中文：ch-ZN
                checktext: "输入内容格式不符合条件",
                url: "",
                ajaxtype: "POST",
                category: [{
                    id: 1,
                    name: "类别1"
                }, {
                    id: 2,
                    name: "类别2"
                }, {
                    id: 3,
                    name: "类别3"
                }, {
                    id: 4,
                    name: "类别4"
                }, {
                    id: 5,
                    name: "类别5"
                }],
                hasrights: [],
                noresult: "无查询结果",
                error: "网络错误",
                titleName: "",
                subtextName: {},
                searchboxClickfunction: function($th, title, index) {
                    alert("请定义搜索列表单元的click函数：参数1为选择的jQuery对象，参数2为title名称，参数3为当前选择的index");
                },
                searchboxEmptyfunction: function(title) {
                    alert("请定义清除搜索内容的函数:参数为选中的title名称");
                },
                searchboxImgfunction: function(url, title) {
                    alert("请定义图片的click函数:参数1为点击图片的url，参数2为title名称");
                }
            },
            idenboxOptions: {
            	btnClickFunction:function( panelId){
            		alert("请定义点击初始化函数：参数2为panel的id" + panelId);
            	},
            	idenRadioClickFunction: function(searchType, panelId) {
                    alert("请定义RadioClick函数：参数1为搜索类型" + searchType + "，参数2为panel的id" + panelId);
                },
                idenEmptyClickFunction: function() {
                    alert("清空导航函数");
                }
            },
            legendboxOptions: {
            	legendboxClickFunction: function(list, name, status) {
                    alert("请定义图层列表单元的click函数：参数1为所有选中的list，参数2为当前点击的name名称，参数3为修改后的状态");
                    console.log(list);
                    console.log(name);
                    console.log(status);
                }
            },
            toolboxOptions: {
                data: [{
                    id: 0,
                    name: "类别1",
                    sub: [{
                            id: 0,
                            name: "图层1-1"
                        },
                        {
                            id: 1,
                            name: "图层1-2"
                        },
                        {
                            id: 2,
                            name: "图层1-3"
                        },
                        {
                            id: 3,
                            name: "图层1-4"
                        }
                    ]
                }, {
                    id: 1,
                    name: "类别2",
                    sub: [{
                            id: 0,
                            name: "图层2-1"
                        },
                        {
                            id: 1,
                            name: "图层2-2"
                        },
                        {
                            id: 2,
                            name: "图层2-3"
                        },
                        {
                            id: 3,
                            name: "图层2-4"
                        }
                    ]
                }, {
                    id: 2,
                    name: "类别3",
                    sub: [{
                            id: 0,
                            name: "图层3-1"
                        },
                        {
                            id: 1,
                            name: "图层3-2"
                        },
                        {
                            id: 2,
                            name: "图层3-3"
                        },
                        {
                            id: 3,
                            name: "图层3-4"
                        }
                    ]
                }],
                toolEmptyClickFunction:function(){
                	
                }
            },
            layerControlButtonOptions: {
                imgsUrl: ["c1.jpg", "c2.jpg", "c3.jpg"],
                layersName: ["1", "2", "3"],
                layerControlClickFunction: function(index) {
                    alert("请定义地图图层控制单元的click函数：参数为当前点击的index");
                }
            }
        };
        var $this = $(this);
        options.searchboxOptions = $.extend(defaultOptions.searchboxOptions, options.searchboxOptions);
        options.idenboxOptions = $.extend(defaultOptions.idenboxOptions, options.idenboxOptions);
        options.legendboxOptions = $.extend(defaultOptions.legendboxOptions, options.legendboxOptions);
        options.toolboxOptions = $.extend(defaultOptions.toolboxOptions, options.toolboxOptions);
        options.layerControlButtonOptions = $.extend(defaultOptions.layerControlButtonOptions, options.layerControlButtonOptions);
        var option = $.extend(defaultOptions, options);
        var $searchbox, $idenbox, $legendbox, $toolbox;

        // 设置container
        $this.css("position", "absolute");
        $this.css("width", option.width);
        $this.css("height", option.height);
        $this.css("top", option.top);
        $this.css("right", option.right);
        $this.css("pointer-events", "none");

        // 设置按钮组
        setItemgroup($this, option.btnlist);

        // 设置搜索功能
        // 设置searchbox
        $searchbox = $("<div></div>");
        $searchbox.attr("id", "whmaptoolbar-searchbox");
        setSearchbox($searchbox, option.searchboxOptions);
        // 定义结果框
        var $searchboxresult = $("<div></div>");
        $searchboxresult.attr("id", "mapsearchbox-result");
        $searchboxresult.appendTo($searchbox);
        $searchboxresult.hide();
        $searchbox.appendTo($this);
        $searchbox.hide();

        // 设置identify功能
        $idenbox = $("<div></div>");
        $idenbox.attr("id", "whmaptoolbar-idenbox");
        setIdenbox($idenbox, option.idenboxOptions);
        // 定义结果框
        var $idenboxresult = $("<div></div>");
        $idenboxresult.addClass("whmaptoolbar-idenresult");
        $idenboxresult.attr("id", "whmaptoolbar-iden-result");
        $idenboxresult.appendTo($idenbox);
        $idenboxresult.hide();
        $idenbox.appendTo($this);
        $idenbox.hide();

        // 设置图例功能
        $legendbox = $("<div></div>");
        $legendbox.attr("id", "whmaptoolbar-legendbox");
        setLegendbox($legendbox, option.legendboxOptions);
        $legendbox.appendTo($this);
        $legendbox.hide();
        
        // 设置工具箱功能
        $toolbox = $("<div></div>");
        $toolbox.attr("id", "whmaptoolbar-toolbox");
        setToolbox($toolbox, option.toolboxOptions);
        // 定义结果框
        var $toolboxresult = $("<div></div>");
        $toolboxresult.addClass("whmaptoolbar-toolresult");
        $toolboxresult.attr("id", "whmaptoolbar-tool-result");
        $toolboxresult.append('<div class="result-title"></div>');
        $toolboxresult.append('<div class="result-content"></div>'); 
        $toolboxresult.appendTo($toolbox);
        $toolboxresult.hide();
        
        var $toolboxstatics = $("<div></div>");
        $toolboxstatics.addClass("whmaptoolbar-toolresult");
        $toolboxstatics.attr("id", "whmaptoolbar-tool-statics");
        $toolboxstatics.append('<div class="result-title"></div>');
        $toolboxstatics.append('<div class="result-content"></div>');  
        $toolboxstatics.appendTo($toolbox);
        $toolboxstatics.hide();
        
        $toolbox.appendTo($this);
        $toolbox.hide();
        
        // 添加按钮组监听 ----------------------------------------------------------------------------
        $(".whmaptoobar-btn").on("click", function() {
            var $that = $(this);
            var btnId = $that.parent("div").attr("id");
            var choosenId = "",
                $choosen, $choosenbox;
            var $box;
            switch (btnId) {
                case "whmaptoolbar-item-search":
                    $box = $searchbox;
                    break;
                case "whmaptoolbar-item-identify":
                    $box = $idenbox;
                    break;
                case "whmaptoolbar-item-legend":
                    $box = $legendbox;
                    break;
                case "whmaptoolbar-item-tool":
                    $box = $toolbox;
                    break;
                default:
                    $box = null;
            }

            if ($box != null) {
                $(".whmaptoobar-btn").each(function() {
                    if ($(this).hasClass("active")) {
                        choosenId = $(this).parent("div").attr("id");
                        $choosen = $(this);
                        switch (choosenId) {
                            case "whmaptoolbar-item-search":
                                $choosenbox = $searchbox;
                                break;
                            case "whmaptoolbar-item-identify":
                                $choosenbox = $idenbox;
                                break;
                            case "whmaptoolbar-item-legend":
                                $choosenbox = $legendbox;
                                break;
                            case "whmaptoolbar-item-tool":
                            	$choosenbox = $toolbox;
                                break;
                            default:
                                $choosenbox = null;
                        }
                    }
                });

                if (choosenId != "") {
                    if (btnId == choosenId) {
                        $that.removeClass("active");
                        if (!($box == null || $box === undefined)) {
                            $box.hide("normal");
                            if (btnId == "whmaptoolbar-item-identify") {
                                emptyIdenbox($idenboxresult);
                                option.idenboxOptions.idenEmptyClickFunction();
                            }
                            if(btnId == "whmaptoolbar-item-tool"){
                            	emptyTooboxResult($toolboxresult,$toolboxstatics);
                            	option.toolboxOptions.toolEmptyClickFunction();
                            }
                        }
                    } else {
                        $that.addClass("active");
                        $choosen.removeClass("active");
                        if (!($box == null || $box === undefined)) {
                            $box.show("normal");
                            if(btnId=="whmaptoolbar-item-identify"){
                            	option.idenboxOptions.btnClickFunction("whmaptoolbar-iden-result");
                            }else if(btnId=="whmaptoolbar-item-legend"){
                            	option.legendboxOptions.btnClickFunction("whmaptoolbar-legend")
                            }
                        }
                        if (!($choosenbox == null || $choosenbox === undefined)) {
                            $choosenbox.hide();
                            if (choosenId == "whmaptoolbar-item-identify") {
                                emptyIdenbox($idenboxresult);
                                option.idenboxOptions.idenEmptyClickFunction();
                            }
                            if(choosenId == "whmaptoolbar-item-tool"){
                            	emptyTooboxResult($toolboxresult,$toolboxstatics);
                            	option.toolboxOptions.toolEmptyClickFunction();
                            }
                        }
                    }
                } else {
                    $that.addClass("active");
                    if (!($box == null || $box === undefined)) {
                        $box.show("normal");
                        if(btnId=="whmaptoolbar-item-identify"){
                        	option.idenboxOptions.btnClickFunction("whmaptoolbar-iden-result");
                        }else if(btnId=="whmaptoolbar-item-legend"){
                        	option.legendboxOptions.btnClickFunction("whmaptoolbar-legend")
                        }
                    }
                }
            } else {
                if (layerControlIndex == 2) {
                    layerControlIndex = 0;
                } else {
                    layerControlIndex += 1;
                }
                setLayerControlImg(layerControlIndex, option.layerControlButtonOptions.imgsUrl, option.layerControlButtonOptions.layersName);
                option.layerControlButtonOptions.layerControlClickFunction(layerControlIndex);
            }
        });

        // 添加搜索监听 ----------------------------------------------------------------------------
        // 监听输入
        $("#mapsearchbox-input").on("input", function(e) {
            var value = e.delegateTarget.value;
            var category = $("#mapsearchbox-select").val();

            if (value == "") {
                if ($("#mapsearchbox-input").hasClass("mapsearchbox-value-selected")) {
                    var titlename = $("#mapsearchbox-input").attr("titlename");
                    option.searchboxOptions.searchboxEmptyfunction(titlename);
                    $("#mapsearchbox-input").removeClass("mapsearchbox-value-selected");
                    $searchboxresult.empty();
                    $searchboxresult.hide();
                } else {
                    $searchboxresult.empty();
                    $searchboxresult.hide();
                }
            } else {
                // 监测语言
                var languageFlag = checkLanguage(value, option.searchboxOptions.language);
                if (languageFlag) {
                	mapApp.queryAttByName(value,function(features){
                         var data = checkData(features, option.searchboxOptions.titleName, option.searchboxOptions.subtextName);
                         setSearchboxResult($searchboxresult, data, value, option.searchboxOptions.searchboxClickfunction, option.searchboxOptions.searchboxImgfunction);
                         $searchboxresult.show();
                	},function(){
                		setSearchboxNoresult($searchboxresult, option.searchboxOptions.error);
                        $searchboxresult.show();
                	})
                } else {
                    setSearchboxNoresult($searchboxresult, option.searchboxOptions.checktext);
                    $searchboxresult.show();
                }
            }
        });

        // 监听select
        $("#mapsearchbox-select").on("change", function() {
            var category = $(this).val();
            var value = $("#mapsearchbox-input").val();

            if (value == "") {
                $searchboxresult.empty();
                $searchboxresult.hide();
            } else {
                // 监测语言
                var languageFlag = checkLanguage(value, option.searchboxOptions.language);
                if (languageFlag) {
                	mapApp.queryAttByName(value,function(features){
                        var data = checkData(features, option.searchboxOptions.titleName, option.searchboxOptions.subtextName);
                        setSearchboxResult($searchboxresult, data, value, option.searchboxOptions.searchboxClickfunction, option.searchboxOptions.searchboxImgfunction);
                        $searchboxresult.show();
               	    },function(){
               		    setSearchboxNoresult($searchboxresult, option.searchboxOptions.error);
                        $searchboxresult.show();
               	    })
               } else {
                   setSearchboxNoresult($searchboxresult, option.searchboxOptions.checktext);
                   $searchboxresult.show();
               }
           
            }
        });

        // 添加identify监听 ----------------------------------------------------------------------------
        // 监听radio的click事件
        $(".whmaptoolbar-ideninput input").on("click", function() { 
        	  var searchType = $(this).val()
              option.idenboxOptions.idenRadioClickFunction(searchType, "whmaptoolbar-iden-result");
        });
        
        // 添加工具箱监听 ----------------------------------------------------------------------------
        $(".maptoolbox-table li").on("click",function(){
        	if(!$(this).hasClass("active")){
        		emptyTooboxResult($toolboxresult,$toolboxstatics);
        		option.toolboxOptions.toolEmptyClickFunction();
        		$(".maptoolbox-table li").removeClass("active");
            	$(this).addClass("active");
        	}
        	option.toolboxOptions.toolClickFunction($(this).attr("url"),$(this).html());
        })
        return this;
    };

    // ----- 按钮组 -----------------------------------------------------------------------------------------

    function setItemgroup($th, btnlist) {
        // 设置按钮组
        var $itemgroupBody = $("<div></div>");
        $itemgroupBody.attr("id", "whmaptoolbar-itemgroup");

        var $row = $("<div class='row'></div>");
        btnlist.forEach(function(ele) {
            var $item = $("<div class='col-xs-3 whmaptoolbar-item'></div>");
            $item.attr("id", ele.id);
            var $btn = $("<div class='whmaptoobar-btn'></div>");
            var $span = $("<span></span>");
            if (ele.iclass !== "") {
                var i = "<i class='fa " + ele.iclass + "'></i>";
                $span.html(i);
                $span.appendTo($btn);
                $btn.appendTo($item);
            } else {
                $span.appendTo($btn);
                $btn.appendTo($item);
            }
            $item.appendTo($row);
        });

        $row.appendTo($itemgroupBody);
        $itemgroupBody.appendTo($th);

        return $th;
    }

    // ----- 搜索功能 -----------------------------------------------------------------------------------------

    function setSearchbox($searchbox, option) {
        // 定义输入框
        var $box = $("<div></div>");
        $box.attr("id", "mapsearchbox-inputbox");
        // 设置input
        var input = $("<input type='text' id='mapsearchbox-input' placeholder='" + option.placeholder + "'>");
        input.appendTo($box);
        // 设置i
        var i = $("<i class='fa fa-search' id='mapsearchbox-i'></i>");
        i.appendTo($box);

        if (option.category.length !== 0) {
            var $childDiv = $("<div></div>");
            $childDiv.addClass("cd-select");
            var $select = $("<select name='category' id='mapsearchbox-select'></div>");
            var html = "";
            option.category.forEach(function(element) {
                html += "<option value='" + element.id + "'>" + element.name + "</option>";
            });
            $select.html(html);
            $select.appendTo($childDiv);
            $childDiv.appendTo($box);
        }
        $box.appendTo($searchbox);
    }

    function setSearchboxNoresult($result, noresult) {
        $result.empty();
        var html = "<div class='result-item no-result'><ul><li>" + noresult + "</li></ul></div>";
        $result.html(html);
        $result.show();
    }

    function setSearchboxResult($result, result, highlight, clickfunction, imgfunction) {
        $result.empty();
        var parentDiv = $("<div class='result-item has-result'></div>");
        var ul = $("<ul id='result-item-ul'></ul>");
        var html = "";
        result.forEach(function(element, index) {
            var a = "<li><a aurl='" + element.QJURL + "' class='image-wrapper' id=" + index + "></a>";
            var h4 = "<h4>" + element.title + "</h4>";
            var h4text = h4.split(highlight);
            var p = "<p>" + element.subtext + "</p>";
            // 设置高亮
            var ptext = p.split(highlight);
            var li = a + h4text.join("<b>" + highlight + "</b>") + ptext.join("<b>" + highlight + "</b>");
            html += li;
        });
        ul.html(html);
        ul.appendTo(parentDiv);

        parentDiv.appendTo($result);

        // 添加背景图片
        $(".image-wrapper").each(function() {
            var idx = $(this).attr("id");
            var IMGURL = "url(" + result[idx].IMGURL + ")";
            $(this).css("background-image", IMGURL);
        });

        // 监听li的click
        $("#result-item-ul").children("li").on("click", function() {
            var $th = $(this);
            var index = $th.index();
            var title = $th.children("h4").html();

            // 去除高亮
            var str1 = new RegExp("<b>", "g");
            var str2 = new RegExp("</b>", "g");
            if (str1.test(title)) {
                title = title.replace(str1, "");
            }
            if (str2.test(title)) {
                title = title.replace(str2, "");
            }
            title = $.trim(title);

            clickfunction($th, title, index);

            $("#mapsearchbox-input").val(title);
            if (!$("#mapsearchbox-input").hasClass("mapsearchbox-value-selected")) {
                $("#mapsearchbox-input").addClass("mapsearchbox-value-selected");
            }
            $("#mapsearchbox-input").attr("titlename", title);
            $result.empty();
            $result.hide();
        });
        // 监听a的click
        $("#result-item-ul").children("li").children("a").on("click", function() {
            event.stopPropagation(); // 阻止事件冒泡

            var $li = $(this).parent("li");
            var title = $li.children("h4").html();
            // 去除高亮
            var str1 = new RegExp("<b>", "g");
            var str2 = new RegExp("</b>", "g");
            if (str1.test(title)) {
                title = title.replace(str1, "");
            }
            if (str2.test(title)) {
                title = title.replace(str2, "");
            }
            title = $.trim(title);
            $("#mapsearchbox-input").val(title);
            $result.empty();
            $result.hide();

            var aurl = $(this).attr("aurl");
            imgfunction(aurl, title);
        });
    }

    function checkLanguage(value, language) {
        // 监测语言
        var flag;
        switch (language) {
            case "all":
                {
                    flag = true;
                    break;
                }
            case "ch-ZN":
                {
                    var reg = new RegExp("[0-9\\u4E00-\\u9FFF]+$", "g");
                    if (reg.test(value)) {
                        flag = true;
                    } else {
                        flag = false;
                    }
                    break;
                }
            default:
                flag = true;
        }
        return flag;
    }

    function checkData(list, titleName, subtextName) {
        // 整理数据
        var data = [];
        list.forEach(function(item) {
            var element = {};
            if (item.IMGURL == "" || item.IMGURL === undefined || item.IMGURL == null) {
                element["IMGURL"] = "/images/default360.png";
            } else {
                element["IMGURL"] = item.IMGURL;
            }

            if (item.QJURL == "" || item.QJURL === undefined || item.QJURL == null) {
                element["QJURL"] = "#";
            } else {
                element["QJURL"] = item.QJURL;
            }

            if (item[titleName] === undefined || item[titleName] == "" || item[titleName] == null) {
                element["title"] = "无标题";
            } else {
                element["title"] = item[titleName];
            }

            var str = "";
            for (var e in item) {
                for (var i in subtextName) {
                    if (e == i) {
                        str += subtextName[i] + "：" + item[e] + " ";
                    }
                }

            }
            element["subtext"] = str;

            data.push(element);
        });

        return data;
    }

    // ----- identify功能 -----------------------------------------------------------------------------------------

    function setIdenbox($idenbox, option) {
        // 查询范围
        var $conditionbox = $("<div class='whmaptoolbar-ideninput'></div>");
        var html="<label>请选择查询范围：</label>";
        html+='<input id="r1" type="radio" value="1" name="forSearch"/><span>单图层</span>';
        html+='<input id="r2" type="radio" value="0" name="forSearch" checked="checked"/><span>多图层</span>';
        $conditionbox.append(html);    
        $conditionbox.appendTo($idenbox);
    }
  
    function emptyIdenbox($idenboxresult) {
        $idenboxresult.empty();
        $idenboxresult.hide();
    }

    // ----- 图例功能 -----------------------------------------------------------------------------------------

    function setLegendbox($legendbox, option) {
        $panel = $("<div id='whmaptoolbar-legend'></div>");	
        $panel.appendTo($legendbox);
    }
    
    // ----- 工具箱功能 -----------------------------------------------------------------------------------------
    
    function setToolbox($toolbox, option) {
    	 $panel = $("<div class='panel-group'></div>");
         option.data.forEach(function(element, index) {
             if (element.name == "" || element.sub === undefined || element.sub == null) {

             } else {
                 var id = "maptoolbox-collapse" + index;
                 var $panelItem = $("<div class='panel maptoolbox-panel'></div>");
                 var html = "<a data-toggle='collapse' data-parent='#maptoolbox-layer' href='#" + id + "'><div class='panel-heading'><h4 class='panel-title'>" + element.name + "</h4></div></a>";             
                 if (element.sub&&element.sub.length>0) {
                	 html += "<div id='" + id + "' class='panel-collapse collapse'><div class='panel-body'><ul class='maptoolbox-table'>";
                     element.sub.forEach(function(item, idx) {
                         var iid = "maptoolbox-" + index + "-" + idx;
                         html += "<li class='maptoolbox-item' url="+item.url+">" + item.name + "</li>";
                     });
                 }
                 html += "</ul></div></div>";

                 $panelItem.html(html);
                 $panelItem.appendTo($panel);
             }
         });
         $panel.appendTo($toolbox);
    }
    
    function emptyTooboxResult($toolboxresult,$toolboxstatics) {
    	$toolboxresult.find(".result-title").html();
    	$toolboxresult.find(".result-content").empty();
    	$toolboxresult.hide();
    	
    	$toolboxstatics.find(".result-title").html();
    	$toolboxstatics.find(".result-content").empty();
    	$toolboxstatics.hide();
    	
    }
    // ----- 地图图层切换功能 -----------------------------------------------------------------------------------------

    function setLayerControlImg(index, imgs, names) {
        var img = "url('" + imgs[index] + "')";
        $("#whmaptoolbar-item-style").children(".whmaptoobar-btn").css("background-image", img);
        $("#whmaptoolbar-item-style").children(".whmaptoobar-btn").attr("title", names[index]);
    }

})(jQuery);