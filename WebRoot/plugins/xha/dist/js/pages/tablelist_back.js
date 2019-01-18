(function($) {
    'use strict'

    // documentData类型type说明：（1）TJXZ 条件选址；（2）YDXK 用地许可；（3）JSXK 建设许可；（4）JGXK 竣工许可。
    $.fn.tablelist = function(options) {
        var defaultOptions = { // 默认设置
            title: "青岛市应急救援战勤保障中心项目",
            BaojianData: ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11","12","13"],
            FawenData: ["0", "1", "2", "3", "4", "5", "6"],
            ZhibiaoType: "",
            ZhibiaoData: [""],
            noresult: "-"
        };
        var $this = $(this);
        var option = $.extend(defaultOptions, options);

        // 标题
        setTableTitle($this, option.title);

        // 报建信息
        setBaojian($this, option.BaojianData);

        // 发文信息
        setFawen($this, option.FawenData);

        // 指标信息
        if (option.ZhibiaoType !== "" && option.ZhibiaoData.length > 0) {
            setZhibiao($this, option.ZhibiaoType, option.ZhibiaoData);
        }

      

        $('.table-title').on('click', function() {
            if ($(this).hasClass('open')) {
                $(this).removeClass('open').addClass('closed');
                $(this).children('i').removeClass('fa-circle').addClass('fa-circle-o');
                $(this).parent('section').children('table').hide('normal');
            } else {
                $(this).removeClass('closed').addClass('open');
                $(this).children('i').removeClass('fa-circle-o').addClass('fa-circle');
                $(this).parent('section').children('table').show();
            }
        });

        return this;
    };

    function setTableTitle($this, title) {
        // 标题
        var sec = $('<section />', {
                'class': 'content-header'
            })
            .html('<h1><i class="fa fa-book"></i> ' + title + '</h1>')
            .appendTo($this);
        return $this;
    }

    function setBaojian($this, BaojianData) {
        // 报建信息
        var sec = $('<section />', {
                'class': 'content-table',
                'id': 'BJXX'
            })
            .html('<h3 class="table-title text-blue open"><i class="fa fa-circle"></i>报建信息</h3>')
            .appendTo($this);
        var table = $('<table />', {
                'class': 'table table-bordered'
            })
            .appendTo(sec);
        var tbody = $('<tbody />')
            .appendTo(table);
        var tr1 = $('<tr />')
            .html('<td class="labelTd">申请事项</td><td colspan="3">' + BaojianData[0] + '</td>')
            .appendTo(tbody);
        var tr2 = $('<tr />')
            .html('<td class="labelTd">申请单位</td><td>' + BaojianData[1] + '</td>' +
                '<td class="labelTd">社会统一信用代码</td><td>' + BaojianData[2] + '</td>')
            .appendTo(tbody);
        var tr3 = $('<tr />')
             .html('<td class="labelTd">申请时间</td><td>' + BaojianData[3] + '</td>' +
                '<td class="labelTd">委托代理人</td><td>' + BaojianData[4] + '</td>')
            .appendTo(tbody);
        var tr4 = $('<tr />')
            .html('<td class="labelTd">联系电话</td><td>' + BaojianData[5] + '</td>' +
                '<td class="labelTd">项目名称</td><td>' + BaojianData[6] + '</td>')
            .appendTo(tbody);
        var tr5 = $('<tr />')
            .html('<td class="labelTd">项目编码</td><td>' + BaojianData[7] + '</td>' +
                '<td class="labelTd">项目地址</td><td>' + BaojianData[8] + '</td>')
            .appendTo(tbody);
        var tr6 = $('<tr />')
             .html('<td class="labelTd">项目属地</td><td>' + BaojianData[9] + '</td>' +
                '<td class="labelTd">设计单位</td><td>' + BaojianData[10] + '</td>')
            .appendTo(tbody);
        var tr7 = $('<tr />')
            .html('<td class="labelTd">是否重点项目</td><td>' + BaojianData[11] + '</td>' +
                '<td class="labelTd">项目总投资（万元）</td><td>' + BaojianData[12] + '</td>')
            .appendTo(tbody);
        var tr8 = $('<tr />')
        .html('<td class="labelTd">项目规模</td><td colspan="3">' + BaojianData[13] + '</td>')
        .appendTo(tbody);
        return $this;
    }

    function setFawen($this, FawenData) {
        // 发文信息
        var sec = $('<section />', {
                'class': 'content-table',
                'id': 'FWXX'
            })
            .html('<h3 class="table-title text-yellow open"><i class="fa fa-circle"></i>发文信息</h3>')
            .appendTo($this);
        var table = $('<table />', {
                'class': 'table table-bordered'
            })
            .appendTo(sec);
        var tbody = $('<tbody />')
            .appendTo(table);
        var tr1 = $('<tr />')
        .html('<td class="labelTd">发文类型</td><td>' + FawenData[0] + '</td>' +
	            '<td class="labelTd">发文文号</td><td>' + FawenData[1] + '</td>')
	        .appendTo(tbody);
	    var tr2 = $('<tr />')
	        .html('<td class="labelTd">发文日期</td><td>' + FawenData[2] + '</td>' +
	            '<td class="labelTd">经办部门</td><td>' + FawenData[3] + '</td>')
	        .appendTo(tbody);
	    var tr3 = $('<tr />')
	         .html('<td class="labelTd">经办人</td><td>' + FawenData[4] + '</td>' +
	            '<td class="labelTd">签批人</td><td>' + FawenData[5] + '</td>')
	        .appendTo(tbody);
	    var tr4 = $('<tr />')
        .html('<td class="labelTd">证书钢印号</td><td colspan="3">' + FawenData[6] + '</td>')
       .appendTo(tbody);

        return $this;
    }

   
    function setZhibiao($this, ZhibiaoType, ZhibiaoData) {
        // 指标信息
        var sec = $('<section />', {
                'class': 'content-table'
            })
            .html('<h3 class="table-title text-red open"><i class="fa fa-circle"></i>指标信息</h3>')
            .appendTo($this);
        var table = $('<table />', {
                'class': 'table table-bordered'
            })
            .appendTo(sec);
        var tbody = $('<tbody />')
            .appendTo(table);
        switch (ZhibiaoType) {
            case "ZBXX-XZTJ-JZGC":
                {
                    // 指标信息-选址条件
                    //ZhibiaoData = ["0", "1", "2", "3", "4", "5", "6", "7", "8"];
                    sec.attr('id', 'ZBXX-XZTJ-JZGC');
                    var tr1 = $('<tr />')
                    .html('<td class="labelTd">是否混合用地</td><td>' + ZhibiaoData[0] + '</td>' +
            	            '<td class="labelTd">混合用地比例</td><td>' + ZhibiaoData[1] + '</td>')
            	        .appendTo(tbody);
                    var tr2 = $('<tr />')
                    .html('<td class="labelTd">规划用地性质</td><td>' + ZhibiaoData[2] + '</td>' +
            	            '<td class="labelTd">用地性质代码</td><td>' + ZhibiaoData[3] + '</td>')
            	        .appendTo(tbody);
                    var tr3 = $('<tr />')
                    .html('<td class="labelTd">用地面积</td><td>' + ZhibiaoData[4] + '</td>' +
            	            '<td class="labelTd">容积率</td><td>' + ZhibiaoData[5] + '</td>')
            	        .appendTo(tbody);
                    var tr4 = $('<tr />')
                    .html('<td class="labelTd">建筑密度</td><td>' + ZhibiaoData[6] + '</td>' +
            	            '<td class="labelTd">绿地率</td><td>' + ZhibiaoData[7] + '</td>')
            	        .appendTo(tbody);
                    var tr5 = $('<tr />')
                    .html('<td class="labelTd">停车位</td><td>' + ZhibiaoData[8] + '</td>' +
            	            '<td class="labelTd">控制高度</td><td>' + ZhibiaoData[9] + '</td>')
            	        .appendTo(tbody);
                    var tr6 = $('<tr />')
                    .html('<td class="labelTd">备注</td><td colspan="3">' + ZhibiaoData[10] + '</td>')
            	        .appendTo(tbody);
                    break;
                }
            case "ZBXX-XZTJ-SZDQ":
            {
                // 指标信息-选址条件
                //ZhibiaoData = ["0", "1", "2", "3", "4", "5", "6", "7", "8"];
                sec.attr('id', 'ZBXX-XZTJ-SZDQ');
                var tr1 = $('<tr />')
                .html('<td class="labelTd">道路性质</td><td>' + ZhibiaoData[0] + '</td>' +
        	            '<td class="labelTd">道路长度（米）</td><td>' + ZhibiaoData[1] + '</td>')
        	        .appendTo(tbody);
                var tr2 = $('<tr />')
                .html('<td class="labelTd">红线宽度（米）</td><td>' + ZhibiaoData[2] + '</td>' +
        	            '<td class="labelTd">断面形式</td><td>' + ZhibiaoData[3] + '</td>')
        	        .appendTo(tbody);
                var tr3 = $('<tr />')
                .html('<td class="labelTd">设计标准</td><td colspan="3">' + ZhibiaoData[4] + '</td>')
        	        .appendTo(tbody);
                var tr4 = $('<tr />')
                .html('<td class="labelTd">海绵城市要求</td><td colspan="3">' + ZhibiaoData[5] + '</td>')
        	        .appendTo(tbody);
                var tr6 = $('<tr />')
                .html('<td class="labelTd">备注</td><td colspan="3">' + ZhibiaoData[6] + '</td>')
        	        .appendTo(tbody);
                break;
            }
            case "ZBXX-XZTJ-SZGX":
            {
                // 指标信息-选址条件
                //ZhibiaoData = ["0", "1", "2", "3", "4", "5", "6", "7", "8"];
                sec.attr('id', 'ZBXX-XZTJ-SZGX');
                var tr1 = $('<tr />')
                .html('<td class="labelTd">管线类型</td><td>' + ZhibiaoData[0] + '</td>' +
        	            '<td class="labelTd">管线长度（米）</td><td>' + ZhibiaoData[1] + '</td>')
        	        .appendTo(tbody);
                var tr2 = $('<tr />')
                .html('<td class="labelTd">设计标准</td><td>' + ZhibiaoData[2] + '</td>')
        	        .appendTo(tbody);
                var tr3 = $('<tr />')
                .html('<td class="labelTd">海绵城市要求</td><td colspan="3">' + ZhibiaoData[3] + '</td>')
        	        .appendTo(tbody);
                var tr4 = $('<tr />')
                .html('<td class="labelTd">备注</td><td colspan="3">' + ZhibiaoData[4] + '</td>')
        	        .appendTo(tbody);
                break;
            }
            case "ZBXX-YDXK":
                {
                    // 指标信息-用地许可
                    //ZhibiaoData = ["0", "1", "2", "3", "4", "5"];
                    sec.attr('id', 'ZBXX-YDXK');
                    var tr1 = $('<tr />')
                    .html('<td class="labelTd">是否混合用地</td><td>' + ZhibiaoData[0] + '</td>' +
            	            '<td class="labelTd">混合用地比例</td><td>' + ZhibiaoData[1] + '</td>')
            	        .appendTo(tbody);
                    var tr2 = $('<tr />')
                    .html('<td class="labelTd">规划用地性质</td><td>' + ZhibiaoData[2] + '</td>' +
            	            '<td class="labelTd">用地性质代码</td><td>' + ZhibiaoData[3] + '</td>')
            	        .appendTo(tbody);
                    var tr3 = $('<tr />')
                    .html('<td class="labelTd">用地面积（平方米）</td><td>' + ZhibiaoData[4] + '</td>' +
            	            '<td class="labelTd">投资管理类型</td><td>' + ZhibiaoData[5] + '</td>')
            	        .appendTo(tbody);
                    var tr4 = $('<tr />')
                    .html('<td class="labelTd">土地使用类型</td><td>' + ZhibiaoData[6] + '</td>')
            	        .appendTo(tbody);
                    var tr5 = $('<tr />')
                    .html('<td class="labelTd">海绵城市要求</td><td colspan="3">' + ZhibiaoData[7] + '</td>')
            	        .appendTo(tbody);
                    var tr6 = $('<tr />')
                    .html('<td class="labelTd">备注</td><td colspan="3">' + ZhibiaoData[8] + '</td>')
            	        .appendTo(tbody);
                    break;
                }
            case "ZBXX-JSXK-JZGC":
                {
                    // 指标信息-建设许可-建筑工程
                   // ZhibiaoData = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"];
                    sec.attr('id', 'ZBXX-JSXK-JZGC');
                    var tr1 = $('<tr />')
                    .html('<td class="labelTd"是否混合用地</td><td>' + ZhibiaoData[0] + '</td>' +
            	            '<td class="labelTd">混合用地比例</td><td>' + ZhibiaoData[1] + '</td>')
            	        .appendTo(tbody);
                    var tr2 = $('<tr />')
                    .html('<td class="labelTd">规划用地性质</td><td>' + ZhibiaoData[2] + '</td>' +
            	            '<td class="labelTd">用地性质代码</td><td>' + ZhibiaoData[3] + '</td>')
            	        .appendTo(tbody);
                    var tr3 = $('<tr />')
                    .html('<td class="labelTd">用地面积（平方米）</td><td>' + ZhibiaoData[4] + '</td>' +
            	            '<td class="labelTd">容积率</td><td>' + ZhibiaoData[5] + '</td>')
            	        .appendTo(tbody);
                    var tr4 = $('<tr />')
                    .html('<td class="labelTd">建筑密度</td><td>' + ZhibiaoData[6] + '</td>' +
            	            '<td class="labelTd">绿地率</td><td>' + ZhibiaoData[7] + '</td>')
            	        .appendTo(tbody);
                    var tr5 = $('<tr />')
                    .html('<td class="labelTd">总建筑面积（平方米）</td><td>' + ZhibiaoData[8] + '</td>' +
            	            '<td class="labelTd">停车位（个）</td><td>' + ZhibiaoData[9] + '</td>')
            	        .appendTo(tbody);
                    var tr6 = $('<tr />')
                    .html('<td class="labelTd">地上建筑面积（平方米）</td><td>' + ZhibiaoData[10] + '</td>' +
            	            '<td class="labelTd">地上停车位（个）</td><td>' + ZhibiaoData[11] + '</td>')
            	        .appendTo(tbody);
                    var tr7 = $('<tr />')
                    .html('<td class="labelTd">地下建筑面积（平方米）</td><td>' + ZhibiaoData[12] + '</td>' +
            	            '<td class="labelTd">地下停车位（个）</td><td>' + ZhibiaoData[13] + '</td>')
            	        .appendTo(tbody);
                    var tr8 = $('<tr />')
                    .html('<td class="labelTd">住宅建筑面积（平方米）</td><td>' + ZhibiaoData[14] + '</td>' +
            	            '<td class="labelTd">办公建筑面积（平方米）</td><td>' + ZhibiaoData[15] + '</td>')
            	        .appendTo(tbody);
                    var tr9 = $('<tr />')
                    .html('<td class="labelTd">配套建筑面积</td><td>' + ZhibiaoData[16] + '</td>' +
            	            '<td class="labelTd">建设栋数（栋）</td><td>' + ZhibiaoData[17] + '</td>')
            	        .appendTo(tbody);
                    var tr10 = $('<tr />')
                    .html('<td class="labelTd">户数（户）</td><td>' + ZhibiaoData[18] + '</td>' +
            	            '<td class="labelTd">建筑物使用性质</td><td>' + ZhibiaoData[19] + '</td>')
            	        .appendTo(tbody);
                    var tr11 = $('<tr />')
                    .html('<td class="labelTd">海绵城市要求</td><td colspan="3">' + ZhibiaoData[20] + '</td>')
            	        .appendTo(tbody);
                    var tr12 = $('<tr />')
                    .html('<td class="labelTd">备注</td><td colspan="3">' + ZhibiaoData[21] + '</td>')
            	        .appendTo(tbody);
                    break;
                }
            case "ZBXX-JSXK-SZDQ":
                {
                    // 指标信息-建设许可-市政道桥
                    //ZhibiaoData = ["0", "1", "2", "3", "4", "5", "6", "7", "8"];
                    sec.attr('id', 'ZBXX-JSXK-SZDQ');
                    var tr1 = $('<tr />')
                    .html('<td class="labelTd">规划用地性质</td><td>' + ZhibiaoData[0] + '</td>' +
            	            '<td class="labelTd">用地性质代码</td><td>' + ZhibiaoData[1] + '</td>')
            	        .appendTo(tbody);
                    var tr2 = $('<tr />')
                    .html('<td class="labelTd">用地面积（平方米）</td><td>' + ZhibiaoData[2] + '</td>' +
            	            '<td class="labelTd">道路总长度（米）</td><td>' + ZhibiaoData[3] + '</td>')
            	        .appendTo(tbody);                    var tr3 = $('<tr />')
                    .html('<td class="labelTd">道路宽度</td><td>' + ZhibiaoData[4] + '</td>' +
            	            '<td class="labelTd">道路级别</td><td>' + ZhibiaoData[5] + '</td>')
            	        .appendTo(tbody);
                    var tr4 = $('<tr />')
                    .html('<td class="labelTd">断面形式</td><td>' + ZhibiaoData[6] + '</td>' +
            	            '<td class="labelTd">设计标准</td><td>' + ZhibiaoData[7] + '</td>')
            	        .appendTo(tbody);
                    var tr5 = $('<tr />')
                    .html('<td class="labelTd">折算建筑面积（平方米）</td><td>' + ZhibiaoData[8] + '</td>')
            	        .appendTo(tbody);
                    var tr6 = $('<tr />')
                    .html('<td class="labelTd">海绵城市要求</td><td colspan="3">' + ZhibiaoData[9] + '</td>')
            	        .appendTo(tbody);
                    var tr7 = $('<tr />')
                    .html('<td class="labelTd">备注</td><td colspan="3">' + ZhibiaoData[10] + '</td>')
            	        .appendTo(tbody);
                    
                    break;
                }
            case "ZBXX-JSXK-SZGX":
                {
                    // 指标信息-建设许可-市政管线
                    //ZhibiaoData = ["0", "1", "2", "3", "4", "5", "6", "7", "8"];
                    sec.attr('id', 'ZBXX-JSXK-SZGX');
                    var tr1 = $('<tr />')
                    .html('<td class="labelTd">规划用地性质</td><td>' + ZhibiaoData[0] + '</td>' +
            	            '<td class="labelTd">用地性质代码</td><td>' + ZhibiaoData[1] + '</td>')
            	        .appendTo(tbody);
                    var tr2 = $('<tr />')
                    .html('<td class="labelTd">用地面积（平方米）</td><td>' + ZhibiaoData[2] + '</td>' +
            	            '<td class="labelTd">管线类型</td><td>' + ZhibiaoData[3] + '</td>')
            	        .appendTo(tbody);
                    var tr3 = $('<tr />')
                    .html('<td class="labelTd">管线总长度（千米）</td><td>' + ZhibiaoData[4] + '</td>' +
            	            '<td class="labelTd">折算建筑面积（平方米）</td><td>' + ZhibiaoData[5] + '</td>')
            	        .appendTo(tbody);
                    var tr4 = $('<tr />')
                    .html('<td class="labelTd">海绵城市要求</td><td colspan="3">' + ZhibiaoData[6] + '</td>')
            	        .appendTo(tbody);
                    var tr5 = $('<tr />')
                    .html('<td class="labelTd">备注</td><td colspan="3">' + ZhibiaoData[7] + '</td>')
            	        .appendTo(tbody);
                    break;
                }
            case "ZBXX-JGXK-JZGC":
                {
                    // 指标信息-竣工许可-建筑工程
                    //ZhibiaoData = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"];
                    sec.attr('id', 'ZBXX-JGXK-JZGC');
                    var tr4 = $('<tr />')
                        .html('<td class="labelTd">用地面积（平方米）</td><td>' + ZhibiaoData[0] + '</td>' +
                        		'<td class="labelTd">总建筑面积（平方米）</td><td>' + ZhibiaoData[1] + '</td>')
                        .appendTo(tbody);
                    var tr5 = $('<tr />')
                        .html('<td class="labelTd">地上建筑面积（平方米）</td><td>' + ZhibiaoData[2] + '</td>' +
                        		'<td class="labelTd">地下建筑面积（平方米）</td><td>' + ZhibiaoData[3] + '</td>')
                        .appendTo(tbody);
                    var tr7 = $('<tr />')
                        .html('<td class="labelTd">建筑占地面积（平方米）</td><td>' + ZhibiaoData[4] + '</td>' +
                            '<td class="labelTd">建筑高度（米）</td><td>' + ZhibiaoData[5] + '</td>')
                        .appendTo(tbody);
                    var tr8 = $('<tr />')
                        .html('<td class="labelTd">层数（层）</td><td>' + ZhibiaoData[6] + '</td>')
                        .appendTo(tbody);
                    var tr9 = $('<tr />')
	                    .html('<td class="labelTd">备注</td><td colspan="3">' + ZhibiaoData[7] + '</td>')
	                    .appendTo(tbody);
                    break;
                }
            case "ZBXX-JGXK-SZDQ":
                {
                    // 指标信息-竣工许可-市政道桥
                    //ZhibiaoData = ["0", "1", "2", "3", "4", "5", "6", "7"];
                    sec.attr('id', 'ZBXX-JGXK-SZDQ');
                  
                    var tr4 = $('<tr />')
                        .html('<td class="labelTd">用地面积（平方米）</td><td>' + ZhibiaoData[0] + '</td>'+
                        		'<td class="labelTd">道路总长度（米）</td><td>' + ZhibiaoData[1] + '</td>')
                        .appendTo(tbody);
                    var tr6 = $('<tr />')
                        .html('<td class="labelTd">道路宽度</td><td >' + ZhibiaoData[2] + '</td>' +
                            '<td class="labelTd">断面形式</td><td>' + ZhibiaoData[3] + '</td>')
                        .appendTo(tbody);
                    var tr7 = $('<tr />')
                        .html('<td class="labelTd">折算建筑面积（平方米）</td><td>' + ZhibiaoData[4] + '</td>')
                        .appendTo(tbody);
                    var tr8 = $('<tr />')
	                    .html('<td class="labelTd">备注</td><td colspan="3">' + ZhibiaoData[5] + '</td>')
	                    .appendTo(tbody);
                    break;
                }
            case "ZBXX-JGXK-SZGX":
                {
                    // 指标信息-竣工许可-市政管线
                    //ZhibiaoData = ["0", "1", "2", "3", "4", "5", "6", "7"];
                    sec.attr('id', 'ZBXX-JGXK-SZDQ');
                   
                    var tr4 = $('<tr />')
                        .html('<td class="labelTd">用地面积（平方米）</td><td>' + ZhibiaoData[0] + '</td>' +
                            '<td class="labelTd">管线类型</td><td>' + ZhibiaoData[1] + '</td>')
                        .appendTo(tbody);
                    var tr5 = $('<tr />')
                        .html('<td class="labelTd">管线总长度（千米）</td><td>' + ZhibiaoData[2] + '</td>')
                        .appendTo(tbody);
                    var tr6 = $('<tr />')
	                    .html('<td class="labelTd">备注</td><td colspan="3">' + ZhibiaoData[3] + '</td>')
	                    .appendTo(tbody);
                    break;
                }
            case "ZBXX-QT":
            {
                // 指标信息-竣工许可-市政管线
                //ZhibiaoData = ["0", "1", "2", "3", "4", "5", "6", "7"];
                sec.attr('id', 'ZBXX-QT');
                var tr1 = $('<tr />')
                .html('<td class="labelTd">审批内容</td><td>' + ZhibiaoData[0] + '</td>'+
                		'<td></td><td></td>')
        	        .appendTo(tbody);
                break;
            }
        }
        return $this;
    }
})(jQuery);