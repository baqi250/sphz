(function() {
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

    $.fn.rightSidebar = function(options) {
        var defaultOptions = { // 默认设置
            data: [{
                    id: 1,
                    name: "文号1",
                    childdata: {
                        id: 1,
                        title: "文号1",
                        info: [{
                            iname: "数据1",
                            idata: "1",
                            attidx: 0
                        }, {
                            iname: "数据2",
                            idata: "2"
                        }, {
                            iname: "数据3",
                            idata: "3"
                        }, {
                            iname: "数据4",
                            idata: "4",
                            attidx: 1
                        }, {
                            iname: "数据5",
                            idata: "5",
                            attidx: 2
                        }, {
                            iname: "数据6",
                            idata: "6",
                            attidx: 3
                        }, {
                            iname: "数据7",
                            idata: "7",
                            attidx: 4
                        }],
                        attachment: [{
                            attname: "扫描文书1",
                            atturl: ""
                        }, {
                            attname: "扫描文书2",
                            atturl: ""
                        }, {
                            attname: "扫描文书3",
                            atturl: ""
                        }, {
                            attname: "扫描文书4",
                            atturl: ""
                        }]
                    }
                },
                {
                    id: 2,
                    name: "文号2",
                    childdata: {
                        id: 1,
                        title: "文号2",
                        info: [{
                            iname: "数据1",
                            idata: "1",
                            attidx: 0
                        }, {
                            iname: "数据2",
                            idata: "2"
                        }],
                        attachment: [{
                            attname: "扫描文书1",
                            atturl: ""
                        }]
                    }
                },
                {
                    id: 3,
                    name: "文号3",
                    childdata: {
                        id: 1,
                        title: "文号3",
                        info: [{
                            iname: "数据1",
                            idata: "1",
                            attidx: 0
                        }, {
                            iname: "数据2",
                            idata: "2"
                        }],
                        attachment: [{
                            attname: "扫描文书1",
                            atturl: ""
                        }]
                    }
                },
                {
                    id: 4,
                    name: "文号4",
                    childdata: {
                        id: 1,
                        title: "文号4",
                        info: [{
                            iname: "数据1",
                            idata: "1",
                            attidx: 0
                        }, {
                            iname: "数据2",
                            idata: "2"
                        }],
                        attachment: [{
                            attname: "扫描文书1",
                            atturl: ""
                        }]
                    }
                },
                {
                    id: 5,
                    name: "文号5",
                    childdata: {
                        id: 1,
                        title: "文号5",
                        info: [{
                            iname: "数据1",
                            idata: "1",
                            attidx: 0
                        }, {
                            iname: "数据2",
                            idata: "2"
                        }],
                        attachment: [{
                            attname: "扫描文书1",
                            atturl: ""
                        }]
                    }
                },
                {
                    id: 6,
                    name: "文号6",
                    childdata: {
                        id: 1,
                        title: "文号6",
                        info: [{
                            iname: "数据1",
                            idata: "1",
                            attidx: 0
                        }, {
                            iname: "数据2",
                            idata: "2"
                        }],
                        attachment: [{
                            attname: "扫描文书1",
                            atturl: ""
                        }]
                    }
                }
            ],
            list1ClickFunction: function($th, id, childdata) {
                $th.rightChildSidebar(id, childdata);
            }
        };
        var $this = $(this);
        var option = $.extend(defaultOptions, options);

        // 创建tab-content
        var $tabContent = $('<div />', {
            'id': 'tab-content',
            'class': 'tab-content'
        });
        $this.append($tabContent);

        var $tabPane = $('<div />', {
            'id': 'control-sidebar-project-options-tab',
            'class': 'tab-pane active'
        });

        // 创建menu
        var $demoSettings = $('<div />');

        // 项目
        $demoSettings.append(
            '<h4 class="control-sidebar-heading">' +
            '项目信息' +
            '</h4>'
        );

        // 项目文档
        var $box1 = $('<div />', {
            'class': 'box box-solid'
        });
        $box1.boxWidget();

        var $boxheader1 = $('<div />', {
            'class': 'box-header with-border'
        });
        $boxheader1.append(
            '<h3 class="box-title">' +
            '项目文档' +
            '</h3>' +
            '<div class="box-tools">' +
            '<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>' +
            '</div>'
        );
        $box1.append($boxheader1);

        var $boxbody1 = $('<div />', {
            'class': 'box-body no-padding'
        });

        var $boxbodyul1 = $('<ul />', {
            'class': 'nav nav-pills nav-stacked',
            'id': 'nav-list1'
        });

        var html1 = '';
        option.data.forEach(function(e, idx) {
            if (idx < 3) {
                html1 += '<li><a id=' + e.id + '><i class="fa fa-circle-o text-light-blue"></i> ' + e.name + '</a></li>';
            } else {
                html1 += '<li><a id=' + e.id + '><i class="fa fa-circle-o text-gray"></i> ' + e.name + '</a></li>';
            }
        });
        $boxbodyul1.html(html1);

        $boxbody1.append($boxbodyul1);
        $box1.append($boxbody1);

        $boxbodyul1.children('li').on('click', function() {
            var id = $(this).children('a').attr('id');
            var childdata;
            option.data.forEach(function(e) {
                if (e.id == id) {
                    childdata = e.childdata;
                }
            });
            option.list1ClickFunction($this, id, childdata);
        });

        // 项目信息
        var $box2 = $('<div />', {
            'class': 'box box-solid'
        });
        $box2.boxWidget();

        var $boxheader2 = $('<div />', {
            'class': 'box-header with-border'
        });
        $boxheader2.append(
            '<h3 class="box-title">' +
            '项目信息' +
            '</h3>' +
            '<div class="box-tools">' +
            '<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>' +
            '</div>'
        );
        $box2.append($boxheader2);

        var $boxbody2 = $('<div />', {
            'class': 'box-body no-padding'
        });

        var $boxbodyul2 = $('<ul />', {
            'class': 'nav nav-pills nav-stacked',
            'id': 'nav-list2'
        });

        var html2 = '';
        html2 += '<li><a id="a"><i class="fa fa-circle-o text-red"></i> 数据1: 1</a></li>';
        html2 += '<li><a id="b"><i class="fa fa-circle-o text-yellow"></i> 数据2: 2156</a></li>';
        html2 += '<li><a id="b"><i class="fa fa-circle-o text-green"></i> 数据3: 3526559</a></li>';
        $boxbodyul2.html(html2);

        $boxbody2.append($boxbodyul2);
        $box2.append($boxbody2);

        $demoSettings.append($box1);
        $demoSettings.append($box2);
        $tabPane.append($demoSettings);
        $tabContent.append($tabPane);

        return $this;
    };

    $.fn.rightChildSidebar = function(id, data) {
        console.log(data);
        var $this = $(this);
        $this.children().detach();

        // 创建tab-ul
        var $tabs = $('<ul />', {
            'id': 'control-sidebar-tabs',
            'class': 'nav nav-tabs nav-justified control-sidebar-tabs'
        });
        $this.append($tabs);

        // 创建切换按钮
        var $tabButton1 = $('<li />', { 'class': 'active' })
            .html('<a href="#control-sidebar-list-tab" data-toggle="tab">' +
                '<i class="fa fa-th-list"></i>' +
                '<span> 发文信息</span>' +
                '</a>');

        var $tabButton2 = $('<li />')
            .html('<a href="#control-sidebar-attachment-tab" data-toggle="tab">' +
                '<i class="fa fa-paperclip"></i>' +
                '<span> 扫描文书</span>' +
                '</a>');

        // 将按钮添加到列表
        $tabs.append($tabButton1);
        $tabs.append($tabButton2);

        // 创建tab-content
        var $tabContent = $('<div />', {
            'id': 'tab-content',
            'class': 'tab-content'
        });
        $this.append($tabContent);

        var $tabPane1 = $('<div />', {
            'id': 'control-sidebar-list-tab',
            'class': 'tab-pane active'
        });

        // 创建menu
        var $demoContent1 = $('<div />');

        // 项目
        var $returnBtn1 = $('<button />', {
            'type': 'button',
            'id': 'btn-return',
            'class': 'btn btn-box-tool',
            'style': 'display:inline-block;'
        }).html('<i class="fa fa-reply"></i>');
        $demoContent1.append($returnBtn1);
        $demoContent1.append(
            '&nbsp;<h4 style="display:inline-block;" class="control-sidebar-heading">' +
            data.title +
            '</h4>'
        );

        $returnBtn1.on('click', function() {
            $this.children().detach();
            $this.rightSidebar();
        });

        // 发文信息
        var $box1 = $('<div />', {
            'class': 'box box-solid'
        });
        $box1.boxWidget();

        var $boxheader1 = $('<div />', {
            'class': 'box-header with-border'
        });
        $boxheader1.append(
            '<h3 class="box-title">' +
            '发文信息' +
            '</h3>' +
            '<div class="box-tools">' +
            '<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>' +
            '</div>'
        );
        $box1.append($boxheader1);

        var $boxbody1 = $('<div />', {
            'class': 'box-body no-padding'
        });

        var $boxbodyul1 = $('<ul />', {
            'class': 'nav nav-pills nav-stacked',
            'id': 'nav-child-list'
        });

        var html1 = '';
        data.info.forEach(function(e) {
            if (e.attidx === undefined) {
                html1 += '<li><a><i class="fa fa-circle-o text-gray"></i> ' + e.iname + ': ' + e.idata + '</a></li>';
            } else {
                html1 += '<li><a><i class="fa fa-circle-o text-light-blue"></i> ' + e.iname + ': ' + e.idata + '</a>' +
                    '<div class="box-tools">' +
                    '<button type="button" class="btn btn-box-tool"><i class="fa fa-paperclip"></i></button>' +
                    '</div>' +
                    '</li>';
            }
        });
        $boxbodyul1.html(html1);

        $boxbody1.append($boxbodyul1);
        $box1.append($boxbody1);

        $demoContent1.append($box1);
        $tabPane1.append($demoContent1);
        $tabContent.append($tabPane1);

        var $tabPane2 = $('<div />', {
            'id': 'control-sidebar-attachment-tab',
            'class': 'tab-pane'
        });

        // 创建menu
        var $demoContent2 = $('<div />');

        // 项目
        var $returnBtn2 = $('<button />', {
            'type': 'button',
            'id': 'btn-return',
            'class': 'btn btn-box-tool',
            'style': 'display:inline-block;'
        }).html('<i class="fa fa-reply"></i>');
        $demoContent2.append($returnBtn2);
        $demoContent2.append(
            '&nbsp;<h4 style="display:inline-block;" class="control-sidebar-heading">' +
            data.title +
            '</h4>'
        );

        $returnBtn2.on('click', function() {
            $this.children().detach();
            $this.rightSidebar();
        });

        // 附件信息
        var $box2 = $('<div />', {
            'class': 'box box-solid'
        });
        $box2.boxWidget();

        var $boxheader2 = $('<div />', {
            'class': 'box-header with-border'
        });
        $boxheader2.append(
            '<h3 class="box-title">' +
            '扫描文书' +
            '</h3>' +
            '<div class="box-tools">' +
            '<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>' +
            '</div>'
        );
        $box2.append($boxheader2);

        var $boxbody2 = $('<div />', {
            'class': 'box-body no-padding'
        });

        var $boxbodyul2 = $('<ul />', {
            'class': 'nav nav-pills nav-stacked',
            'id': 'nav-child-attachment'
        });

        var html2 = '';
        data.attachment.forEach(function(e) {
            html2 += '<li><a><i class="fa fa-paperclip text-light-blue"></i> ' + e.attname + '</a></li>';
        });
        $boxbodyul2.html(html2);

        $boxbody2.append($boxbodyul2);
        $box2.append($boxbody2);

        $demoContent2.append($box2);
        $tabPane2.append($demoContent2);
        $tabContent.append($tabPane2);
    }

    function drawClass() {
        var $box = $('<div />', {
            'class': 'box box-solid'
        });
        $box.boxWidget();

        var $boxheader = $('<div />', {
            'class': 'box-header with-border'
        });
        $boxheader.append(
            '<h3 class="box-title">' +
            '项目文档' +
            '</h3>' +
            '<div class="box-tools">' +
            '<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>' +
            '</div>'
        );
        $box1.append($boxheader1);

        var $boxbody1 = $('<div />', {
            'class': 'box-body no-padding'
        });

        var $boxbodyul1 = $('<ul />', {
            'class': 'nav nav-pills nav-stacked',
            'id': 'nav-list1'
        });

        var html1 = '';
        option.data.forEach(function(e, idx) {
            if (idx < 3) {
                html1 += '<li><a id=' + e.id + '><i class="fa fa-circle-o text-light-blue"></i> ' + e.name + '</a></li>';
            } else {
                html1 += '<li><a id=' + e.id + '><i class="fa fa-circle-o text-gray"></i> ' + e.name + '</a></li>';
            }
        });
        $boxbodyul1.html(html1);

        $boxbody1.append($boxbodyul1);
        $box1.append($boxbody1);

        $boxbodyul1.children('li').on('click', function() {
            var id = $(this).children('a').attr('id');
            var childdata;
            option.data.forEach(function(e) {
                if (e.id == id) {
                    childdata = e.childdata;
                }
            });
            option.list1ClickFunction($this, id, childdata);
        });
    }
})(jQuery);