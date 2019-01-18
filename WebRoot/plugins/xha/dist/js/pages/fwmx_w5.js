var chart5 = echarts.init(document.getElementById("chart-5"));

/*$(function() {
	initChart4();
})*/
    
// 设置图1
function initChart4(cbbmId,cbbmName,year) {
	$.ajax({
		type:"post",
		url:"sphz/statistics/StaticMonthCountByYear",
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
			renderChart4(fwlxNameList,fwlxCountList,data.monthList,series);
			$("#box-5-text").html(data.html);
		},
		error:function(data){}
		
	});
	
}

function renderChart4(fwlxNameList,fwlxCountList,monthList,series){
	
	 var option5 = {
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
	            	interval:0,
	            	textStyle:{
	            		fontSize:'10'
	            	}
	            }
	        },
	        series: series
	    };
	    chart5.setOption(option5);
}

$(".box-chart").resize(function() {
    // 自适应
    chart5.resize();
});


