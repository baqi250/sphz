var colors = ["text-blue", "text-yellow", "text-red", "text-green"];
var regions = ["青岛市市区", "市南区", "市北区", "李沧区", "崂山区", "城阳区", "西海岸", "高新区", "保税区"];
var chart1 = echarts.init(document.getElementById("chart-1"));
// 图2
var chart2 = echarts.init(document.getElementById("chart-2"));
// 图3
var chart3 = echarts.init(document.getElementById("chart-3"));
// 图4
var chart4 = echarts.init(document.getElementById("chart-4"));
var chart1Data1;
var chart1Data2;
var countList1;
var countList2;
var countList3;
var countList4;
var spzlHtml;
$.ajax({
	type:"post",
	url:'sphz/statistics/getTotalCountByRegion',
	data:{tm:new Date().getTime()},
	dataType:'json',
	cache:false,
	success:function(data){
		console.log(data);
		chart1Data1=data.chart1Data1;
		chart1Data2=data.chart1Data2;
		spzlHtml=data.spzlHtml;
		// 图1
		var i = 0;
		initChart1(i);
		$(".chart1-region").click(function() {
		    var ind = Number($(this).attr("id"));
		    if (ind != i) {
		        i = ind;
		        initChart1(i);
		        $("#regions-btn").text(regions[i]);
		    }
		});
		initChart2();
		// 图3
		//initChart3();
		// 图4
		initChart4();

	},
	error:function(data){}
});

$.ajax({
	type:"post",
	url:'sphz/statistics/getTotalCountByDate',
	data:{tm:new Date().getTime()},
	dataType:'json',
	cache:false,
	success:function(data){
		console.log(data);
		countList1=data.countList1;
		countList2=data.countList2;
		countList3=data.countList3;
		countList4=data.countList4;
		// 图2
		initChart3();

	},
	error:function(data){}
});

$(function() {
	//alert(data.spzlHtml);
    var showinfoTrigger = '[data-widget="showinfo"]';
    $(showinfoTrigger).click(function() {
        var id = $(this).attr("id");
        var index = Number(id.charAt(id.length - 1)) - 1;
        var textid = "#" + id + "-text";
        if ($(this).hasClass("info-showed")) {
            $(this).addClass("info-hidden").removeClass("info-showed");
            $(this).children("i").removeClass(colors[index]);
            $(textid).hide("slow");
        } else {
            $(this).addClass("info-showed").removeClass("info-hidden");
            $(this).children("i").addClass(colors[index]);
            if( $("#box-1-text").html() == null || $.trim($("#box-1-text").html()).length == 0){
            	$("#box-1-text").html(spzlHtml);
            }
            $(textid).show("slow");
        }
    });
})
    
 // 设置图1
 		function initChart1(i) {
 		    var total = chart1Data1[i][0];
 		    var changeper = function(num) {
 		        var result = (num / total) * 100;
 		        var str = result.toFixed(2) + "%";
 		        return str;
 		    };
 		    var option1 = {
 		        title: {
 		            text: regions[i]
 		        },
 		        tooltip: {
 		            trigger: 'axis',
 		            axisPointer: {
 		                type: 'shadow'
 		            },
 		            formatter: function(params) {
 		                var tar = params[1];
 		                return tar.name + '<br/>' + tar.seriesName + '：' + tar.value + '<br/>比例：' + changeper(tar.value, total);
 		            }
 		        },
 		        xAxis: [{
 		            type: 'category',
 		            axisLabel: {
 		                interval: 0,
 		                rotate: 40,
 		                margin: 8
 		            },
 		            data: ['总数', '选址意见书', '用地规划许可', '工程规划许可', '竣工验收']
 		        }],
 		        yAxis: [{
 		            type: 'value'
 		        }],
 		        series: [{
 		                name: '辅助',
 		                type: 'bar',
 		                stack: '总量',
 		                itemStyle: {
 		                    normal: {
 		                        barBorderColor: 'rgba(0,0,0,0)',
 		                        color: 'rgba(0,0,0,0)'
 		                    },
 		                    emphasis: {
 		                        barBorderColor: 'rgba(0,0,0,0)',
 		                        color: 'rgba(0,0,0,0)'
 		                    }
 		                },
 		                data: chart1Data2[i]
 		            },
 		            {
 		                name: '数量',
 		                type: 'bar',
 		                stack: '总量',
 		                itemStyle: {
 		                    color: '#3c8dbc'
 		                },
 		                label: {
 		                    normal: {
 		                        show: true,
 		                        position: 'inside'
 		                    }
 		                },
 		                data: chart1Data1[i]
 		            }
 		        ]
 		    };
 		    chart1.setOption(option1);
 		}

 		// 设置图2
 		function initChart2() {
 		    var checkdata = function(lastyear, thisyear) {
 		        var n = thisyear - lastyear;
 		        if (n >= 0) {
 		            return "增加：" + n + " 件 ";
 		        } else {
 		            return "减少：" + Math.abs(n) + " 件 ";
 		        }
 		    };
 		    var option2 = {
 		        tooltip: {
 		            trigger: 'axis',
 		            axisPointer: {
 		                type: 'shadow'
 		            },
 		            formatter: function(params) {
 		                var lastyear = params[0];
 		                var thisyear = params[1];
 		                return lastyear.name + '<br/>' + lastyear.seriesName + '：' + lastyear.value + '<br/>' + thisyear.seriesName + '：' + thisyear.value + '<br/>' + checkdata(lastyear.value, thisyear.value);
 		            }
 		        },
 		        legend: {
 		            data: ['2017年', '2018年']
 		        },
 		        xAxis: [{
 		            type: 'category',
 		            axisLabel: {
 		                interval: 0,
 		                rotate: 40,
 		                margin: 8
 		            },
 		            data: ['总数', '选址意见书', '用地规划许可', '工程规划许可', '竣工验收']
 		        }],
 		        yAxis: [{
 		            type: 'value'
 		        }],
 		        series: [{
 		                name: '2017年',
 		                type: 'bar',
 		                itemStyle: {
 		                    color: '#f39c12'
 		                },
 		                data: [51, 21, 14, 5, 11]
 		            },
 		            {
 		                name: '2018年',
 		                type: 'bar',
 		                itemStyle: {
 		                    color: '#dd4b39'
 		                },
 		                data: [60, 24, 13, 7, 16]
 		            }
 		        ]
 		    };
 		    chart2.setOption(option2);
 		}

 		// 设置图3
 		function initChart3() {
 		    var option3 = {
 		        tooltip: {
 		            trigger: 'axis',
 		            axisPointer: {
 		                type: 'shadow'
 		            }
 		        },
 		        legend: {
 		            data: ['选址意见书', '用地规划许可', '工程规划许可', '竣工验收']
 		        },
 		        xAxis: {
 		            type: 'value'
 		        },
 		        yAxis: {
 		            type: 'category',
 		            data: ['1月', '2月', '3月', '4月', '5月', '6月']
 		        },
 		        series: [{
 		                name: '选址意见书',
 		                type: 'bar',
 		                stack: '总量',
 		                label: {
 		                    normal: {
 		                        show: true,
 		                        position: 'insideRight'
 		                    }
 		                },
 		                data: countList1
 		            },
 		            {
 		                name: '用地规划许可',
 		                type: 'bar',
 		                stack: '总量',
 		                label: {
 		                    normal: {
 		                        show: true,
 		                        position: 'insideRight'
 		                    }
 		                },
 		                data: countList2
 		            },
 		            {
 		                name: '工程规划许可',
 		                type: 'bar',
 		                stack: '总量',
 		                label: {
 		                    normal: {
 		                        show: true,
 		                        position: 'insideRight'
 		                    }
 		                },
 		                data: countList3
 		            },
 		            {
 		                name: '竣工验收',
 		                type: 'bar',
 		                stack: '总量',
 		                label: {
 		                    normal: {
 		                        show: true,
 		                        position: 'insideRight'
 		                    }
 		                },
 		                data: countList4
 		            }
 		        ]
 		    };
 		    chart3.setOption(option3);
 		}

 		// 设置图4
 		function initChart4() {
 		    var option4 = {
 		        tooltip: {
 		            trigger: 'axis',
 		            axisPointer: {
 		                type: 'cross'
 		            }
 		        },
 		        legend: {
 		            data: ['2017年', '2018年']
 		        },
 		        xAxis: {
 		            type: 'category',
 		            data: ['1月', '2月', '3月', '4月', '5月', '6月']
 		        },
 		        yAxis: {
 		            type: 'value'
 		        },
 		        series: [{
 		                name: '2017年',
 		                type: 'line',
 		                itemStyle: {
 		                    color: '#f39c12'
 		                },
 		                data: [9, 6, 10, 10, 8, 8]
 		            },
 		            {
 		                name: '2018年',
 		                type: 'line',
 		                itemStyle: {
 		                    color: '#00a65a'
 		                },
 		                data: [12, 5, 10, 15, 8, 10]
 		            }
 		        ]
 		    };
 		    chart4.setOption(option4);
 		}
 		
		$(".box-chart").resize(function() {
		    // 自适应
		    chart1.resize();
		    chart2.resize();
		    chart3.resize();
		    chart4.resize();
		});


