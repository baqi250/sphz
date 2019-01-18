var chart3 = echarts.init(document.getElementById("chart-3"));

/*$(function() {
	initChart2();
})*/
    
// 设置图1
function initChart2(cbbmId,cbbmName,year) {
	$.ajax({
		type:"post",
		url:"sphz/statistics/StaticCountByHalfYear",
		data:{tm:new Date().getTime(),cbbmId:cbbmId,cbbmName:cbbmName,year:year},
		dataType:"json",
		cache:false,
		success:function(data){
			var fwlxNameList=data.fwlxNameList;
			var fwlxCountList=data.fwlxCountList;
			var series=new Array();
			for(var i=0;i<fwlxNameList.length;i++){
				var json={
	                name: fwlxNameList[i],
	                type: 'bar',
	                stack: '总量',
	                label: {
	                    normal: {
	                        show: true,
	                        position: 'insideRight'
	                    }
	                },
	                data: fwlxCountList[i]
	            };
				series.push(json);
			}
			renderChart2(fwlxNameList,fwlxCountList,data.monthList,series);
			$("#box-3-text").html(data.html);
		},
		error:function(data){}
		
	});
	
}

function renderChart2(fwlxNameList,fwlxCountList,monthList,series){
	
	 var option3 = {
	        tooltip: {
	            trigger: 'axis',
	            axisPointer: {
	                type: 'shadow'
	            }
	        },
	        legend: {
	            data: fwlxNameList
	        },
	        xAxis: {
	            type: 'value'
	        },
	        yAxis: {
	            type: 'category',
	            data: monthList,
	            axisLabel:{
	            	margin:20,
	            	interval:0
	            }
	        },
	        series: series
	    };
	    chart3.setOption(option3);
}

$(".box-chart").resize(function() {
    // 自适应
    chart3.resize();
});

