$(function() {
    // 选择框
    $('.select2').select2({
        language: "zh-CN"
    });
}) 

 var keyMap;
 var jbxxMap;
 var xmxxMap;
 var fwsxMap;
 var daxxMap;
 var maxShowLength=10;
jQuery(function($) { 
	 jbxxMap={
		 "FWLXID_NAME":"发文类型名称",
		  "WHWYM":"文号唯一码",
		  "SLBH":"受理编号",
		  "SLRQ":"受理日期",
		  "FWWH":"发文文号",
		  "FWRQ":"发文日期",
		  "ZSGYH":"证书钢印号",
		  "SBLX_NAME":"申办类型",
		  "SPJD_NAME":"审批阶段",
		  "GCLB_NAME":"工程类别",
		  "CBBM_NAME":"承办部门",
		  "JBR":"经办人",
		  "QPR":"签批人",
		  "FWLX_NAME":"发文类型",
		  "BJR":"报建人",
		  "LXFS":"联系方式",
		  "SJDW":"设计单位",
		  "HHYDXZ_NAME":"混合用地性质",
		  "BZ":"备注",
		};
	 daxxMap={
		  "DABH":"档案编号",
		  "DAHH":"档案盒号",
		  "DALY_NAME":"档案来源",
		  "LRRY":"录入人员",
		  "SHRY":"审核人员",
		  "LRRQ":"录入日期",
		  "SHRQ":"审核日期",
		  "SHZT_NAME":"审核状态",
		};
	 xmxxMap={
		  "XMWYM":"项目唯一码",
		  "XMBH":"项目编号",
		  "XMMC":"项目名称",
		  "XMWZ":"项目位置",
		  "JSDW":"建设单位",
		  "ZZJGDM":"所属区划",
		  "GHYDXZ":"规划用地性质",
		  "SFHHYD":"是否混合用地",
		  "HHBL":"混合比例",
		  "JZWSYXZ":"建筑物使用性质",
		  "BZ":"备注"
	  };
	 
	 fwsxMap={
		  "YDMJ":"用地面积（平方米）",
		  "RJL":"容积率",
		  "JZMD":"建筑密度",
		  "LDL":"绿地率",
		  "TCBW":"停车泊位",
		  "JZGD":"建筑高度",
		  "HMCSYQ":"海绵城市要求",
		  "DLDJ_NAME":"道路等级",
		  "DLCD":"道路长度（米）",
		  "HXKD":"红线宽度（米）",
		  "DMXS_NAME":"断面形式",
		  "SJBZ":"设计标准",
		  "GXLX_NAME":"管线类型",
		  "GXCD":"管线长度（米）",
		  "TZGLLX_NAME":"投资管理类型",
		  "TDSYLX_NAME":"土地使用类型",
		  "ZJZMJ":"总建筑面积（平方米）",
		  "DSJZMJ":"地上建筑面积（平方米）",
		  "DXJZMJ":"地下建筑面积（平方米）",
		  "DSTCBW":"地上停车泊位（个）",
		  "DXTCBW":"地下停车泊位（个）",
		  "ZZMJ":"住宅面积（平方米）",
		  "HS":"户数",
		  "JZWSYXZ_NAME":"建筑物使用性质（多个）",
		  "DLLX_NAME":"道路类型",
		  "ZSJZMJ":"折算建筑面积（平方米）"
	  };
		
	keyMap={
		  "FWLXID_NAME":"发文类型名称",
		  "XMWYM":"项目唯一码",
		  "WHWYM":"文号唯一码",
		  "XMBH":"项目编号",
		  "SLBH":"受理编号",
		  "SLRQ":"受理日期",
		  "FWWH":"发文文号",
		  "FWRQ":"发文日期",
		  "ZSGYH":"证书钢印号",
		  "SBLX_NAME":"申办类型",
		  "SPJD_NAME":"审批阶段",
		  "GCLB_NAME":"工程类别",
		  "XMLX_NAME":"项目类型",
		  "CBBM_NAME":"承办部门",
		  "JBR":"经办人",
		  "QPR":"签批人",
		  "FWLX_NAME":"发文类型",
		  "XMMC":"项目名称",
		  "XMWZ":"项目位置",
		  "JSDW":"建设单位",
		  "ZZJGDM":"组织机构代码",
		  "BJR":"报建人",
		  "LXFS":"联系方式",
		  "SJDW":"设计单位",
		  "GHYDXZ_NAME":"规划用地性质",
		  "HHYDXZ_NAME":"混合用地性质",
		  "DABH":"档案编号",
		  "DAHH":"档案盒号",
		  "DALY_NAME":"档案来源",
		  "LRRY":"录入人员",
		  "SHRY":"审核人员",
		  "LRRQ":"录入日期",
		  "SHRQ":"审核日期",
		  "SHZT_NAME":"审核状态",
		  "BZ":"备注",
		  "YDMJ":"用地面积（平方米）",
		  "RJL":"容积率",
		  "JZMD":"建筑密度",
		  "LDL":"绿地率",
		  "TCBW":"停车泊位",
		  "JZGD":"建筑高度",
		  "HMCSYQ":"海绵城市要求",
		  "DLDJ_NAME":"道路等级",
		  "DLCD":"道路长度（米）",
		  "HXKD":"红线宽度（米）",
		  "DMXS_NAME":"断面形式",
		  "SJBZ":"设计标准",
		  "GXLX_NAME":"管线类型",
		  "GXCD":"管线长度（米）",
		  "TZGLLX_NAME":"投资管理类型",
		  "TDSYLX_NAME":"土地使用类型",
		  "ZJZMJ":"总建筑面积（平方米）",
		  "DSJZMJ":"地上建筑面积（平方米）",
		  "DXJZMJ":"地下建筑面积（平方米）",
		  "DSTCBW":"地上停车泊位（个）",
		  "DXTCBW":"地下停车泊位（个）",
		  "ZZMJ":"住宅面积（平方米）",
		  "HS":"户数",
		  "JZWSYXZ_NAME":"建筑物使用性质（多个）",
		  "DLLX_NAME":"道路类型",
		  "ZSJZMJ":"折算建筑面积（平方米）"
	  };
	
    //初始化table 
    var parentTable = $('#table_project') 
        .dataTable( 
            { 
              "bPaginate" : true,//分页工具条显示 
              //"sPaginationType" : "full_numbers",//分页工具条样式 
              "bStateSave" : true, //是否打开客户端状态记录功能,此功能在ajax刷新纪录的时候不会将个性化设定回复为初始化状态  
              "bScrollCollapse" : true, //当显示的数据不足以支撑表格的默认的高度 
              "bLengthChange" : false, //每页显示的记录数
              "lengthMenu": [15],
              "bFilter" : false, //搜索栏 
              "bSort" : false, //是否支持排序功能 
              "bInfo" : true, //显示表格信息
              "scrollX": true,
              "scrollCollapse": true,
              "bLengthChange": false,//每页显示多少条数据
              "bAutoWidth" : true, //自适应宽度 
              "bJQueryUI" : false,//是否开启主题 
              "bDestroy" : true, 
              "bProcessing" : true, //开启读取服务器数据时显示正在加载中……特别是大数据量的时候，开启此功能比较好 
              "bServerSide" : true,//服务器处理分页，默认是false，需要服务器处理，必须true 
              "sAjaxDataProp" : "xiangmuList",//是服务器分页的标志，必须有  
              "sAjaxSource" : "sphz/project/list",//通过ajax实现分页的url路径。  
              "aoColumns" : [//初始化要显示的列 
            	  {
            		  "width":50,
            		  "class":'details-control',
            		  "id":"XMWYM",
            		  "orderable":false,
            		  "data":null,
            		  "defaultContent":''
            	  },
              
                  { 
                    "mDataProp" : "XMMC"
                   
                  }, 
               
                  {
                	"width":250,
                	"class":'text-center-td',
	                "data": null,
	                "render": function(data, type, row) {
                        var html = "<span class='badge table-badge bg-green table-detail' mydata='"+row.SSXMWYM+"' myname='"+row.XMMC+"' id='" + row.XMWYM + "'>&nbsp;<i class='fa fa-info-circle'></i>&nbsp;详情</span>";
                        html += "<span class='badge table-badge bg-red table-huizong' mydata='"+row.SSXMWYM+"' myname='"+row.XMMC+"' id='" + row.XMWYM + "'>&nbsp;<i class='fa fa-server'></i>&nbsp;汇总</span>";
                        html += "<span class='badge table-badge bg-blue table-spatial' mydata='"+row.SSXMWYM+"' myname='"+row.XMMC+"' id='" + row.XMWYM + "'>&nbsp;<i class='fa fa-map-marker'></i>&nbsp;空间</span>";
	                    return html;
	                }
		          }
              ], 
              
			  fnDrawCallback: function(table) {
				  $(".text-center-td").css("text-align","center");
				  appendSkipPage();
				  //alert($("#subXmwym").val());
				  if($("#subXmwym").val()!=""&&$("#subSsxmwym").val()!=""&&$("#subXmwym").val().indexOf(",")<0){
			        //  console.log($("#"+$("#subSsxmwym").val()));
					  $(".details-control").trigger('click');
					/*  $("#subXmwym").val("");
					  $("#subSsxmwym").val("");*/
				  }
				  
              },
              fnInitComplete:function(oSettings,json){
            	 // alert("初始化完成");
              },
              "oLanguage" : {//语言设置 
                "sProcessing" : "加载中...", 
                "sLengthMenu" : "显示 _MENU_ 项结果", 
                "sZeroRecords" : "没有匹配结果", 
                "sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项", 
                "sInfoEmpty" : "显示第 0 至 0 项结果，共 0 项", 
                "sInfoFiltered" : "(由 _MAX_ 项结果过滤)", 
                "sInfoPostFix" : "", 
                "sSearch" : "搜索:", 
                "sUrl" : "", 
                "sEmptyTable" : "表中数据为空", 
                "sLoadingRecords" : "载入中...", 
                "sInfoThousands" : ",", 
                "oPaginate" : { 
                "sFirst" : "首页", 
                "sPrevious" : "上页", 
                "sNext" : "下页", 
                "sLast" : "末页"
                }, 
                "oAria" : { 
                  "sSortAscending" : ": 以升序排列此列", 
                  "sSortDescending" : ": 以降序排列此列"
                } 
              } 
            }); 
  
    function appendSkipPage() {  
    	$('.paginate_jump').remove();
        var table = $("#table_project").dataTable();   
        var template =
            $("<li class='paginate_button active'>" +
                "   <div class='input-group' style='margin-left:3px;'>" +
                "       <span class='input-group-addon' style='padding:10px 8px;background-color:#fff;font-size: 12px;'>跳转页</span>" +
                "       <input type='text' class='form-control' style='text-align:center;padding: 16px 2px;height:30px;width:40px;' />" +
                "       <span class='input-group-addon active' style='padding:0px 8px;'><a href='javascript:void(0)'> Go </a></span>" +
                "   </div>" +
                "</li>");
     
        template.find("a").click(function () {
            var pn = template.find("input").val();
            if (pn > 0) {
                --pn;
            } else {
                pn = 0;
            }
            table.fnPageChange(pn);
        });
     
        $("ul.pagination").append(template);
	}; 
	
	function getJsonLength(jsonData){

		var jsonLength = 0;

		for(var item in jsonData){

		jsonLength++;

		}

		return jsonLength;

	}
	
	/*function getFwxxData(jsonData){
		
	}*/
	
	var keyArray_fawen=[];
	function json2Array(jsonData){
		keyArray_fawen=[];
		for(var key in jsonData){//遍历json对象的每个key/value对,p为key
			keyArray_fawen.push(key);
		}
	}
	
	
    function format ( subProjectList ) {
    	// `subProjectList is the original data object for the row
    	var html='<div style="font-weight: bold; font-size:10px; padding-bottom: 10px; padding-left: 20px;">已有分期</div>';
    	html+='<table class="table table-condensed table-child" style="padding-left:50px;background-color:#f1efef;">';
    	//html+='<thead><tr><th style="width:50px; border: 1px solid #f4f4f4;text-align:center;">序号</th><th style="border: 1px solid #f4f4f4;text-align:center;">分期名称</th><th style="width:200px;border: 1px solid #f4f4f4;text-align:center;">操作</th></tr></thead>'
    	if(null==subProjectList||subProjectList.length==0){
	 		 html+='<tr><td colspan="3" style="text-align: center;">无数据</td></tr>';
	 	}
    	for(var i=0;i<subProjectList.length;i++){
    		var subProject=subProjectList[i];
    		if($("#subXmwym").val().indexOf(subProject["XMWYM"])>-1){
    			html+='<tr style="cursor:pointer;background-color:#dc4c5987">'+
	    			'<td style="text-align:center;width:50px;">'+(i+1)+'</td>'+
					'<td>'+subProject["XMMC"]+'</td>'+
					'<td style="text-align:center;width:200px;">'+'<span class="badge table-badge bg-green table-detail" mydata="'+subProject.SSXMWYM+'" myname="'+subProject.XMMC+'" id="'+subProject.XMWYM+'">&nbsp;<i class="fa fa-info-circle"></i>&nbsp;详情</span><span class="badge table-badge bg-blue table-spatial" id="'+subProject.XMWYM+'">&nbsp;<i class="fa fa-map-marker"></i>&nbsp;空间</span></td>'+
				'</tr>'
    		}else{
	    		html+='<tr style="cursor:pointer">'+
	    			'<td style="text-align:center;width:50px;">'+(i+1)+'</td>'+
					'<td>'+subProject["XMMC"]+'</td>'+
					'<td style="text-align:center;width:200px;">'+'<span class="badge table-badge bg-green table-detail" mydata="'+subProject.SSXMWYM+'" myname="'+subProject.XMMC+'" id="'+subProject.XMWYM+'">&nbsp;<i class="fa fa-info-circle"></i>&nbsp;详情</span><span class="badge table-badge bg-blue table-spatial" id="'+subProject.XMWYM+'">&nbsp;<i class="fa fa-map-marker"></i>&nbsp;空间</span></td>'+
				'</tr>'
    		}
    	}
    	html+='</table>';
    	$("#subXmwym").val("");
    	$("#subSsxmwym").val("");
    	return html;
    }
	// Add event listener for opening and closing details
	$('.table tbody').on('click', 'td.details-control', function () {
		
		var tr = $(this).closest('tr');
		var row = $('#table_project').DataTable().row( tr );
		//console.log(row.data());
		if ( row.child.isShown() ) {
			// This row is already open - close it
			row.child.hide();
			tr.removeClass('shown');
		}
		else {
			// Open this row
	         $.ajax({
	            	type: "POST",
	            	url:'sphz/project/getSubProjectByXMWYM',
	            	data: {tm:new Date().getTime(),XMWYM:row.data().XMWYM,subxmwym:$("#subxmwym").val()},
	        		dataType:'json',
	        		cache: false,
	            	success:function(data){
	            	 	var subProjectList=data.subProjectList;
	            		row.child( format(subProjectList) ).show();
	        			tr.addClass('shown');
	            	},
	            	error:function(data){
	            		
	            	}
	            		
	            });//end of ajax
		}//end of else
	} );	
	
  
    		



$(".table tbody").on("click", ".table-edit", function() {
	var XMWYM = $(this).attr("id");
	var width=$(window).width();
    var height=$(window).height();
 	var indext = layer.open({
         type: 2,
         title: '编辑'+XMWYM,
         shadeClose: true,
         shade: false,
         maxmin: true, //开启最大化最小化按钮
         //btn:['确定','取消'],
         area: [width+'px', height+'px'],
         content: 'sphz/project/goEdit.do?XMWYM='+XMWYM
     });
}); 

$(".table tbody").on("click", ".table-detail", function() {
	//console.log(this);
	var XMWYM = $(this).attr("id");
	var SSXMWYM=$(this).attr("mydata");
	var XMMC=$(this).attr("myname");
	var width=$(window).width();
    var height=$(window).height();
 	var indext = layer.open({
         type: 2,
         title: " ",
         shadeClose: true,
         shade: false,
         maxmin: false, //开启最大化最小化按钮
         //btn:['确定','取消'],
         area: [width+'px', height+'px'],
         content: 'sphz/project/goProjectDetail.do?XMWYM='+XMWYM+'&SSXMWYM='+SSXMWYM+'&XMMC='+XMMC
     });
}); 

$(".table tbody").on("click", ".table-spatial", function() {
	var XMWYM = $(this).attr("id");
	//alert(XMWYM);
	$(window.parent.document).contents().find("#mapIframe")[0].contentWindow.a.excuteQueryTaskByXMWYM(XMWYM);
}); 

$(".table tbody").on("click", ".table-huizong", function() {
/*	var XMWYM = $(this).attr("id");
	 $.ajax({
		  	type: "POST",
		  	url:'sphz/project/statisticsProject',
		  	data: {tm:new Date().getTime(),xmwym:XMWYM},
				dataType:'json',
				cache: false,
		  	success:function(data){
		  		layer.open({
		  			type:1,
		  			area:['600px','600px'],
		  			content:data.html
		  		});
		  	},
		  	error:function(data){
		  		
		  	}
		  		
		  });//end of ajax
*/		  

	var XMWYM = $(this).attr("id");
	var XMMC=$(this).attr("myname");
	var width=$(window).width();
    var height=$(window).height();
 	var indext = layer.open({
         type: 2,
         title: " ",
         shadeClose: true,
         shade: false,
         maxmin: false, //开启最大化最小化按钮
         //btn:['确定','取消'],
         area: [width+'px', height+'px'],
         content:'sphz/project/goProjectStatistics.do?XMWYM='+XMWYM+'&XMMC='+XMMC
     });
	//$(this).projectlist();
}); 


}); 


 


 