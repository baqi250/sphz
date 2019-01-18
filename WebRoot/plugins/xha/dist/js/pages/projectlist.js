(function($) {
    'use strict'

    $('[data-toggle="control-sidebar"]').controlSidebar();
    $('[data-toggle="push-menu"]').pushMenu();
    var $pushMenu = $('[data-toggle="push-menu"]').data('lte.pushmenu');
    var $controlSidebar = $('[data-toggle="control-sidebar"]').data('lte.controlsidebar');
    var $layout = $('body').data('lte.layout');
    $(window).on('load', function() {
            // 重新初始化
            $pushMenu = $('[data-toggle="push-menu"]').data('lte.pushmenu');
            $controlSidebar = $('[data-toggle="control-sidebar"]').data('lte.controlsidebar');
            $layout = $('body').data('lte.layout');
        })
        // documentData类型type说明：（1）TJXZ 条件选址；（2）YDXK 用地许可；（3）JSXK 建设许可；（4）JGXK 竣工许可。
    $.fn.projectlist = function(options) {
        var defaultOptions = { // 默认设置
            title: "青岛市应急救援战勤保障中心项目",
            analysisUrl: "http://www.baidu.com",
            attachmentlistOptions: {},
            baseData: [{
                id: 0,
                name: "建设单位",
                value: "青岛市公安消防局"
            }, {
                id: 1,
                name: "项目位置",
                value: "青岛市蓝色硅谷核心区温泉街道荆疃村以东、莱青路以西、温泉二路以南、规划路以北"
            }, {
                id: 2,
                name: "规划性质",
                value: "消防用地"
            }, {
                id: 3,
                name: "用地面积",
                value: "*****㎡"
            }, {
                id: 4,
                name: "建筑面积",
                value: "*****㎡"
            }, {
                id: 5,
                name: "总容积率",
                value: "0.35"
            }, {
                id: 6,
                name: "建筑密度",
                value: "25.5%"
            }],
            stageFlag: true,
            stageData: {
                name: "已有分期",
                value: [{
                    id: "pro-1",
                    name: "青岛市应急救援战勤保障中心项目一期",
                    url: ""
                }, {
                    id: "pro-2",
                    name: "青岛市应急救援战勤保障中心项目二期",
                    url: ""
                }, {
                    id: "pro-3",
                    name: "青岛市应急救援战勤保障中心项目三期",
                    url: ""
                }]
            },
            documentData:[],
//            documentData: [{
//                id: "0",
//                type: "TJXZ",
//                stage: "",
//                name: "选字第370282201406030101",
//                short: "选",
//                time: "2014.06.03",
//                url: "",
//                childAttachmentlistOptions: {}
//            }, {
//                id: "1",
//                type: "YDXK",
//                stage: "",
//                name: "地字第370282201406060101",
//                short: "地",
//                time: "2014.06.06",
//                url: "",
//                childAttachmentlistOptions: {}
//            }, {
//                id: "2",
//                type: "JSXK",
//                stage: "pro-1",
//                stagename: "青岛市应急救援战勤保障中心项目一期",
//                name: "建字第370282201510260201",
//                short: "建",
//                time: "2015.02.16",
//                url: "",
//                childAttachmentlistOptions: {}
//            }, {
//                id: "3",
//                type: "JSXK",
//                stage: "pro-1",
//                stagename: "青岛市应急救援战勤保障中心项目一期",
//                name: "建字第370282201510260201",
//                short: "建",
//                time: "2015.01.26",
//                url: "",
//                childAttachmentlistOptions: {}
//            }, {
//                id: "4",
//                type: "JSXK",
//                stage: "pro-2",
//                stagename: "青岛市应急救援战勤保障中心项目二期",
//                name: "建字第370282201510260201",
//                short: "建",
//                time: "2016.10.26",
//                url: "",
//                childAttachmentlistOptions: {}
//            }, {
//                id: "5",
//                type: "JSXK",
//                stage: "pro-3",
//                stagename: "青岛市应急救援战勤保障中心项目三期",
//                name: "建字第370282201510260201",
//                short: "建",
//                time: "2016.03.11",
//                url: "",
//                childAttachmentlistOptions: {}
//            }],
            noresult: "暂无",
            clickfunction: function(title) {
                //alert("请定义查看函数!返回值为当前选中的name:" + title);
            	var width=$(window).width();
                var height=$(window).height();
             	var indext = layer.open({
                     type: 2,
                     title: " ",
                     shadeClose: true,
                     shade: false,
                     zIndex:999,
                     maxmin: true, //开启最大化最小化按钮
                     //btn:['确定','取消'],
                     area: [width+'px', height+'px'],
                     content:'sphz/project/goFawenDetail?whwym='+title,
                     success:function(layero,index){
                    	 layerIndex=index;
                    	 layerInitWidth  = $("#layui-layer"+layerIndex).width();
                         layerInitHeight = $("#layui-layer"+layerIndex).height();

                     }
                 });
             	//layer.full(indext);
            	//window.open('sphz/project/goFawenDetail?fwwh='+title);
            }
        };
        var $this = $(this);
        options.attachmentlistOptions = $.extend(defaultOptions.attachmentlistOptions, options.attachmentlistOptions);
        options.documentData = $.extend(defaultOptions.documentData, options.documentData);
        var option = $.extend(defaultOptions, options);
        // 项目标题栏
        setProjectTitle($this, option.title, option.analysisUrl, option.attachmentlistOptions);

        // 内容
        setProjectContent($this, option);

        return this;
    };

    function setProjectTitle($this, title, analysisUrl, attachmentlistOptions) {
        // 项目标题栏
        var sec = $('<section />', {
                'class': 'content-header'
            })
            .html('<h1><i class="fa fa-folder-open"></i> ' + title + '</h1>');

      /*  var btn1 = $('<button />', {
                'type': 'hidden',
                'class': ''
            }).html('')
    		//}).html('<i class="fa fa-bar-chart fa-fw"></i> 指标统计')
            .on('click', function() {
                //$(window).attr('location', analysisUrl);
            });*/
        var btn2 = $('<button />', {
                'type': 'button',
                'class': 'btn btn-default btn-sm'
            }).html('<i class="fa fa-paperclip fa-fw"></i> 附件列表')
            .on('click', function() {
                $(this).attachmentlist(attachmentlistOptions);
            });
        var btngroup = $('<div />', {
                'class': 'btn-group'
            })
            .append(btn2);
        	//.append(btn1).append(btn2);
        var toolbar = $('<div />', {
                'class': 'box-tool'
            })
            .append(btngroup).appendTo(sec);

        sec.appendTo($this);
        return $this;
    }

    function setProjectContent($this, option) {
        // 内容
        var sec = $('<section />', {
            'class': 'content-info'
        });

        // tab页
        var navtabs = $('<div />', {
                'class': 'nav-tabs-custom'
            })
            .html('<ul class="nav nav-tabs">' +
                '<li class="active"><a href="#activity" data-toggle="tab">项目信息</a></li>' +
                '<li><a href="#timeline" data-toggle="tab">发证时间轴</a></li></ul>');
        var tabcontent = $('<div />', {
                'class': 'tab-content'
            })
            .appendTo(navtabs);

        // 项目信息
        var activity = $('<div />', {
                'class': 'tab-pane active',
                'id': 'activity'
            })
            .appendTo(tabcontent);
        // 项目信息-基本信息
        var box1 = $('<div />', {
                'class': 'box box-primary'
            })
            .appendTo(activity);

        var boxheader1 = $('<div />', {
                'class': 'box-header with-border bg-blue'
            })
            .html('<h3 class="box-title"><i class="fa fa-file-text-o"></i>基本信息</h3>')
            .appendTo(box1);
        var tool1 = $('<div />', {
                'class': 'box-tools'
            })
            .appendTo(boxheader1);
        var toolbtn1 = $('<button />', {
                'type': 'button',
                'class': 'btn btn btn-box-tool-w',
                'data-widget': 'collapse'
            }).html('<i class="fa fa-minus"></i>')
            .appendTo(tool1);
        var boxbody1 = $('<div />', {
                'class': 'box-body no-padding row'
            })
            .appendTo(box1);
        var html1 = '',
            html2 = '';
        option.baseData.forEach(function(e, index) {
            if (index < 4) {
                html1 += '<li><a><span>' + e.name + '</span><p>' + e.value + '</p></a></li>';
            } else {
                html2 += '<li><a><span>' + e.name + '</span><p>' + e.value + '</p></a></li>';
            }
        });
        var col1 = $('<div />', {
                'class': 'col-sm-6 col-xs-12'
            })
            .appendTo(boxbody1);
        var ul1 = $('<ul />', {
                'class': 'nav nav-pills nav-stacked'
            })
            .html(html1)
            .appendTo(col1);
        var col2 = $('<div />', {
                'class': 'col-sm-6 col-xs-12'
            })
            .appendTo(boxbody1);
        var ul2 = $('<ul />', {
                'class': 'nav nav-pills nav-stacked'
            })
            .html(html2)
            .appendTo(col2);
        if (option.stageFlag) {
            var col3 = $('<div />', {
                    'class': 'col-sm-12 col-xs-12'
                })
                .html('<hr />')
                .appendTo(boxbody1);
            var html3 = '';
            for (var i = 0; i < option.stageData.value.length; i++) {
                html3 += option.stageData.value[i].name + '<br />';
            }
            html3 += '已有分期共' + i + '期';
            var ul3 = $('<ul />', {
                    'class': 'nav nav-pills nav-stacked',
                    'id': 'nav-stages'
                })
                .html('<li><a><span>' + option.stageData.name + '</span><p>' + html3 + '</p></a></li>')
                .appendTo(col3);
        }
        // 折叠
        box1.boxWidget();
        // 项目信息-发证信息
        var box2 = $('<div />', {
                'class': 'box box-primary'
            })
            .appendTo(activity);
        var boxheader2 = $('<div />', {
                'class': 'box-header with-border bg-blue'
            })
            .html('<h3 class="box-title"><i class="fa fa-book"></i>发证信息</h3>')
            .appendTo(box2);
        var tool2 = $('<div />', {
                'class': 'box-tools'
            })
            .appendTo(boxheader2);
        var toolbtn2 = $('<button />', {
                'type': 'button',
                'class': 'btn btn btn-box-tool-w',
                'data-widget': 'collapse'
            }).html('<i class="fa fa-minus"></i>')
            .appendTo(tool2);
        var boxbody2 = $('<div />', {
                'class': 'box-body no-padding'
            })
            .appendTo(box2);
        var panelgroup = $('<div />', {
                'class': 'panel-group'
            })
            .appendTo(boxbody2);
        // 条件选址
        var panel1 = $('<div />', {
                'class': 'panel custom-panel border-blue'
            })
            .appendTo(panelgroup);
        var panela1 = $('<a />', {
                'data-toggle': 'collapse',
                'href': '#custom-right-collapse1',
                'aria-expanded': 'true'
            })
            .appendTo(panel1);
        var paneltitle1 = $('<div />', {
                'class': 'panel-heading'
            })
            .html('<h4 class="panel-title"><i class="fa fa-circle-o text-blue"></i>条件/选址</h4>')
            .appendTo(panela1);
        var panelcollapse1 = $('<div />', {
                'id': 'custom-right-collapse1',
                'class': 'panel-collapse collapse in'
            })
            .appendTo(panel1);
        var panelbody1 = $('<div />', {
                'class': 'panel-body'
            })
            .appendTo(panelcollapse1);
        var panelbodyul1 = $('<ul />', {
                'class': 'custom-table'
            })
            .appendTo(panelbody1);
        // 用地许可
        var panel2 = $('<div />', {
                'class': 'panel custom-panel border-yellow'
            })
            .appendTo(panelgroup);
        var panela2 = $('<a />', {
                'data-toggle': 'collapse',
                'href': '#custom-right-collapse2',
                'aria-expanded': 'true'
            })
            .appendTo(panel2);
        var paneltitle2 = $('<div />', {
                'class': 'panel-heading'
            })
            .html('<h4 class="panel-title"><i class="fa fa-circle-o text-yellow"></i>用地许可</h4>')
            .appendTo(panela2);
        var panelcollapse2 = $('<div />', {
                'id': 'custom-right-collapse2',
                'class': 'panel-collapse collapse in'
            })
            .appendTo(panel2);
        var panelbody2 = $('<div />', {
                'class': 'panel-body'
            })
            .appendTo(panelcollapse2);
        var panelbodyul2 = $('<ul />', {
                'class': 'custom-table'
            })
            .appendTo(panelbody2);
        // 建设许可
        var panel3 = $('<div />', {
                'class': 'panel custom-panel border-green'
            })
            .appendTo(panelgroup);
        var panela3 = $('<a />', {
                'data-toggle': 'collapse',
                'href': '#custom-right-collapse3',
                'aria-expanded': 'true'
            })
            .appendTo(panel3);
        var paneltitle3 = $('<div />', {
                'class': 'panel-heading'
            })
            .html('<h4 class="panel-title"><i class="fa fa-circle-o text-green"></i>建设许可</h4>')
            .appendTo(panela3);
        var panelcollapse3 = $('<div />', {
                'id': 'custom-right-collapse3',
                'class': 'panel-collapse collapse in'
            })
            .appendTo(panel3);
        var panelbody3 = $('<div />', {
                'class': 'panel-body'
            })
            .appendTo(panelcollapse3);
        var panelbodyul3 = $('<ul />', {
                'class': 'custom-table'
            })
            .appendTo(panelbody3);
        // 竣工许可
        var panel4 = $('<div />', {
                'class': 'panel custom-panel border-red'
            })
            .appendTo(panelgroup);
        var panela4 = $('<a />', {
                'data-toggle': 'collapse',
                'href': '#custom-right-collapse4',
                'aria-expanded': 'true'
            })
            .appendTo(panel4);
        var paneltitle4 = $('<div />', {
                'class': 'panel-heading'
            })
            .html('<h4 class="panel-title"><i class="fa fa-circle-o text-red"></i>竣工许可</h4>')
            .appendTo(panela4);
        var panelcollapse4 = $('<div />', {
                'id': 'custom-right-collapse4',
                'class': 'panel-collapse collapse in'
            })
            .appendTo(panel4);
        var panelbody4 = $('<div />', {
                'class': 'panel-body'
            })
            .appendTo(panelcollapse4);
        var panelbodyul4 = $('<ul />', {
                'class': 'custom-table'
            })
            .appendTo(panelbody4);
        // 数据装填
        var TJXZstageary = []; // 已展示的stage
        var YDXKstageary = []; // 已展示的stage
        var JSXKstageary = []; // 已展示的stage
        var JGXKstageary = []; // 已展示的stage
        option.documentData.forEach(function(item) {
            switch (item.type) {
                case 'TJXZ':
                    TJXZstageary = loadData(item, panelbodyul1, TJXZstageary, 'text-blue', option.clickfunction);
                    break;
                case 'YDXK':
                    YDXKstageary = loadData(item, panelbodyul2, YDXKstageary, 'text-yellow', option.clickfunction);
                    break;
                case 'JSXK':
                    JSXKstageary = loadData(item, panelbodyul3, JSXKstageary, 'text-green', option.clickfunction);
                    break;
                case 'JGXK':
                    JGXKstageary = loadData(item, panelbodyul4, JGXKstageary, 'text-red', option.clickfunction);
                    break;
            }
        });
        if (panelbodyul1.children('li').length == 0) {
            loadNullData(panelbodyul1, option.noresult);
        }
        if (panelbodyul2.children('li').length == 0) {
            loadNullData(panelbodyul2, option.noresult);
        }
        if (panelbodyul3.children('li').length == 0) {
            loadNullData(panelbodyul3, option.noresult);
        }
        if (panelbodyul4.children('li').length == 0) {
            loadNullData(panelbodyul4, option.noresult);
        }

        // 折叠
        box2.boxWidget();

        // 发证时间轴
        var timeline = $('<div />', {
                'class': 'tab-pane',
                'id': 'timeline'
            })
            .appendTo(tabcontent);
        var timeul = $('<ul />', {
                'class': 'timeline'
            })
            .appendTo(timeline);
        var sortdata = sortData(option.documentData);
        sortdata.forEach(function(e) {
        	//console.log(e)
            var li = $('<li />')
                .html('<i class="fa bg-gray">' + e.short + '</i>')
                .on('click', function() {
                    //option.clickfunction(e.name);
                    option.clickfunction(e.id);
                });

            var item = $('<div />', {
                    'class': 'timeline-item'
                })
                .appendTo(li);

            var time = $('<div />', {
                    'class': 'time'
                })
                .html('<span><i class="fa fa-clock-o"></i> ' + e.time + '</span>')
                .appendTo(item);
            var attach = $('<a />', {
                    'class': 'timeline-attachment'
                })
                .html('附件')
                .on('click', function(th) {
                    th.stopPropagation();
                    $(this).attachmentlist(e.childAttachmentlistOptions);
                })
                .appendTo(time);
            item.append('<h3 class="timeline-header">' + e.name + '</h3>')
            li.appendTo(timeul);
        });
        var liend = $('<li />')
            .html('<i class="fa fa-clock-o bg-gray"></i>')
            .appendTo(timeul);

        navtabs.appendTo(sec);
        sec.appendTo($this);
        return $this;
    }

    function loadData(item, panelbodyul, stageary, color, clickfunction) {
		//console.log(item);
        if ($.inArray(item.stage, stageary) < 0 && item.stage !== "") {
            var tli = $('<li />', {
                'class': 'title'
            });
            var ta = $('<a />')
                .html('<i class="fa fa-circle ' + color + '"></i><label>' + item.stagename + '</label>')
                .appendTo(tli);
            var ttime = $('<div />', {
                    'class': 'custom-time'
                })
                .appendTo(ta);
          /*  var tatt = $('<a />', {
                    'href': item.url
                })
                .html('详情')*/
               /* .appendTo(ttime);*/
            tli.appendTo(panelbodyul);
            stageary.push(item.stage);
        }
        var cli = $('<li />');
        var ca = $('<a />')
            .html('<label>' + item.name + '</label>')
            .on('click', function() {
                //clickfunction(item.name);
                clickfunction(item.id);
            })
            .appendTo(cli);
        var ctime = $('<div />', {
                'class': 'custom-time'
            })
            .html('<span><i class="fa fa-clock-o"></i>' + item.time + '</span>')
            .appendTo(ca);
        var cattach = $('<a />')
            .html('附件')
            .on('click', function(th) {
                th.stopPropagation();
               // console.log(item.childAttachmentlistOptions)
                $(this).attachmentlist(item.childAttachmentlistOptions);
            })
            .appendTo(ctime);
        cli.appendTo(panelbodyul);
        return stageary;
    }

    function loadNullData(panelbodyul, title) {
        var li = $('<li />');
        var a = $('<a />')
            .html('<label>' + title + '</label>')
            .appendTo(li);
        li.appendTo(panelbodyul);
    }

    function sortData(data) {
        data.sort(function(a, b) {
            var atime = parseInt(a.time.replace(/\-/g, ''));
            var btime = parseInt(b.time.replace(/\-/g, ''));
            return atime - btime;
        });
        return data;
    }
})(jQuery);