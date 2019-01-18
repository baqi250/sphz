var chart2 = echarts.init(document.getElementById("chart-2"));

/*$(function() {
	initChart1();
})*/
    
// 设置图1
function initChart1(cbbmId,cbbmName,year) {
	$.ajax({
		type:"post",
		url:"sphz/statistics/StaticCountByYear",
		data:{tm:new Date().getTime(),cbbmId:cbbmId,cbbmName:cbbmName,year:year},
		dataType:"json",
		cache:false,
		success:function(data){
			renderChart1(data.fwlxNameList,data.fwlxYearCountList,data.fwlxNextYearCountList,data.riseCountList,data.yearList);
			$("#box-2-text").html(data.html);
		},
		error:function(data){}
		
	});
	
}

function renderChart1(fwlxNameList,fwlxYearCountList,fwlxNextYearCountList,riseCountList,yearList){
	
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
	            data:yearList
	        },
	        xAxis: [{
	            type: 'category',
	            axisLabel: {
	                interval: 0,
	                rotate: 40,
	                margin: 8
	            },
	            data: fwlxNameList
	        }],
	        yAxis: [{
	            type: 'value'
	        }],
	        series: [{
	                name: yearList[0],
	                type: 'bar',
	                itemStyle: {
	                    color: '#f39c12'
	                },
	                data: fwlxNextYearCountList
	            },
	            {
	                name: yearList[1],
	                type: 'bar',
	                itemStyle: {
	                    color: '#dd4b39'
	                },
	                data: fwlxYearCountList
	            }
	        ]
	    };
	    chart2.setOption(option2);
}

$(".box-chart").resize(function() {
    // 自适应
    chart2.resize();
});


