var colors = ["text-blue", "text-yellow", "text-red", "text-green"];
var chart = echarts.init(document.getElementById("chart-1"));
var bianma=$("#BIANMA").val();
//console.log("bianma:"+bianma);
var date=new Date;
var yearSelect=date.getFullYear();
var bmId="";
var bmName="";
//初始化承办部门
function initCbbmSelect(){
	$.ajax({
		type:"post",
		url:"sphz/statistics/getCbbmList",
		data:{tm:new Date().getTime(),bianma:bianma},
		dataType:"json",
		cache:false,
		success:function(data){
			var cbbmList=data.cbbmList;
			var html="";
			for(var i=0;i<cbbmList.length;i++){
				html+="<li><a class='chart1-region' id="+cbbmList[i].bianma+" mydata="+cbbmList[i].name+">"+cbbmList[i].name+"</a></li>";
			}
			html+="<li class='divider'>";
			if(bianma==""){
				html+= "<li><a class='chart1-region' id='' mydata=''>全部</a></li>";
				$("#cbbmMenu").html(html);
				//initChart(bmId,bmName,yearSelect);
			}else{
				//html+= "<li><a class='chart1-region' id='' mydata=''>全部</a></li>";
				 $("#regions-btn").text(cbbmList[0].name);
				 $("#cbbmMenu").html(html);
				 bmId=cbbmList[0].bianma;
				 bmName=cbbmList[0].name;
			}
			initChart(bmId,bmName,yearSelect);
			initChart1(bmId,bmName,yearSelect);
			initChart2(bmId,bmName,yearSelect);
			initChart3(bmId,bmName,yearSelect);
			initChart4(bmId,bmName,yearSelect);
			initChart5(bmId,bmName,yearSelect);
			
		    $(".chart1-region").click(function() {
			    bmId=$(this)[0].id;
			    bmName=$(this).attr("mydata");
			    initChart(bmId,bmName,yearSelect);
			    initChart1(bmId,bmName,yearSelect);
			    initChart2(bmId,bmName,yearSelect);
			    initChart3(bmId,bmName,yearSelect);
			    initChart4(bmId,bmName,yearSelect);
			    initChart5(bmId,bmName,yearSelect);
			    $("#regions-btn").text(bmName);
			 });
		},
		error:function(data){}
	});
}

//初始化年份
function initYearSelect(){
	var html="";
	for(var i=yearSelect;i>yearSelect-10;i--){
		html+="<li><a class='chart1-year' id="+i+">"+i+"</a></li>";
	}
	html+="<li class='divider'>";
	$("#year-btn").text(yearSelect+"年");
	$("#yearMenu").html(html);
	$("#chart5_title").text(yearSelect+"年各月核发情况对比");
	$("#chart6_title").text(yearSelect+"年各月核发情况同期对比");
	$(".chart1-year").click(function() {
	    //var ind = $(this)[0].id;
	    yearSelect=$(this)[0].id;
	    $("#year-btn").text(yearSelect+"年");
	    $("#chart5_title").text(yearSelect+"年各月核发情况对比");
		$("#chart6_title").text(yearSelect+"年各月核发情况同期对比");
	    initChart(bmId,bmName,yearSelect);
	    initChart1(bmId,bmName,yearSelect);
	    //initChart2(bmId,bmName,yearSelect);
	    initChart4(bmId,bmName,yearSelect);
	    initChart5(bmId,bmName,yearSelect);
	 });
}

$(function() {
	initYearSelect();
	initCbbmSelect();
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
            $(textid).show("slow");
        }
    });
  
})
    
// 设置图1
function initChart(cbbmId,cbbmName,year) {
	console.log("cbbmId:"+cbbmId);
	$.ajax({
		type:"post",
		url:"sphz/statistics/StaticCountByCbbm",
		data:{tm:new Date().getTime(),cbbmId:cbbmId,cbbmName:cbbmName,year:year},
		dataType:"json",
		cache:false,
		success:function(data){
			renderChart(data.fwlxNameList,data.fwlxCountList,data.gapList);
			$("#box-1-text").html(data.html);
		},
		error:function(data){}
		
	});
	
}

function renderChart(fwlxNameList,fwlxCountList,gapList){
	
    var total = fwlxCountList[0];
    var changeper = function(num) {
        var result = (num / total) * 100;
        var str = result.toFixed(2) + "%";
        return str;
    };
    var option = {
        title: {
            text:$("#regions-btn").text()
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
            //data: ['总数', '选址意见书', '用地规划许可', '工程规划许可', '竣工验收']
            data: fwlxNameList
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
                data: gapList
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
                data: fwlxCountList
            }
        ]
    };
    chart.setOption(option);
}

$(".box-chart").resize(function() {
    // 自适应
    chart.resize();
});


