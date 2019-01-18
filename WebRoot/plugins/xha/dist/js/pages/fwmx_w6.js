var chart6 = echarts.init(document.getElementById("chart-6"));
/*$(function() {
	initChart5();
})*/
    
// 设置图1
function initChart5(cbbmId,cbbmName,year) {
	$.ajax({
		type:"post",
		url:"sphz/statistics/compareMonthCountByYear",
		data:{tm:new Date().getTime(),cbbmId:cbbmId,cbbmName:cbbmName,year:year},
		dataType:"json",
		cache:false,
		success:function(data){
			renderChart5(data.fwlxHalfThisYearCountList,data.fwlxHalfLastYearCountList,data.monthList,data.yearList);
			$("#box-6-text").html(data.html);
		},
		error:function(data){}
		
	});
	
}

function renderChart5(fwlxHalfThisYearCountList,fwlxHalfLastYearCountList,monthList,yearList){
	 var option6 = {
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
		    chart6.setOption(option6);
}

$(".box-chart").resize(function() {
    // 自适应
    chart6.resize();
});


