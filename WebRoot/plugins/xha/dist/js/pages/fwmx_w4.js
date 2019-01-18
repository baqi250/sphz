var chart4 = echarts.init(document.getElementById("chart-4"));
/*$(function() {
	initChart3();
})*/
    
// 设置图1
function initChart3(cbbmId,cbbmName,year) {
	$.ajax({
		type:"post",
		url:"sphz/statistics/compareCountByHalfYear",
		data:{tm:new Date().getTime(),cbbmId:cbbmId,cbbmName:cbbmName,year:year},
		dataType:"json",
		cache:false,
		success:function(data){
			renderChart3(data.fwlxHalfThisYearCountList,data.fwlxHalfLastYearCountList,data.monthList,data.yearList);
			$("#box-4-text").html(data.html);
		},
		error:function(data){}
		
	});
	
}

function renderChart3(fwlxHalfThisYearCountList,fwlxHalfLastYearCountList,monthList,yearList){
	 var option4 = {
		        tooltip: {
		            trigger: 'axis',
		            axisPointer: {
		                type: 'cross'
		            }
		        },
		        legend: {
		            data: yearList
		        },
		        xAxis: {
		            type: 'category',
		            data: monthList
		        },
		        yAxis: {
		            type: 'value'
		        },
		        series: [{
		                name: yearList[0],
		                type: 'line',
		                itemStyle: {
		                    color: '#f39c12'
		                },
		                data: fwlxHalfLastYearCountList
		            },
		            {
		                name: yearList[1],
		                type: 'line',
		                itemStyle: {
		                    color: '#00a65a'
		                },
		                data: fwlxHalfThisYearCountList
		            }
		        ]
		    };
		    chart4.setOption(option4);
}

$(".box-chart").resize(function() {
    // 自适应
    chart4.resize();
});


