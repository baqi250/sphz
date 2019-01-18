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
              //"bLengthChange" : false, //每页显示的记录数
              //"lengthMenu": [ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100],
              "bFilter" : false, //搜索栏 
              "bSort" : false, //是否支持排序功能 
              "bInfo" : true, //显示表格信息
              "scrollX": true,
              "scrollCollapse": true,
              "bLengthChange": false,//每页显示多少条数据
              "bAutoWidth" : false, //自适应宽度 
              "bJQueryUI" : false,//是否开启主题 
              "bDestroy" : true, 
              "bProcessing" : true, //开启读取服务器数据时显示正在加载中……特别是大数据量的时候，开启此功能比较好 
              "bServerSide" : true,//服务器处理分页，默认是false，需要服务器处理，必须true 
              "sAjaxDataProp" : "xiangmuList",//是服务器分页的标志，必须有  
              "sAjaxSource" : "sphz/project/list",//通过ajax实现分页的url路径。  
              "createdRow":function(row,data,dataIndex){
            	  if(data.XMBH.length>maxShowLength){
            		  $(row).children('td').eq(1).attr('onclick','javascript:changeShow(this);');
            	  }
            	  $(row).children('td').eq(1).attr('content',data.XMBH);
            	  if(data.XMMC.length>maxShowLength){
            		  $(row).children('td').eq(2).attr('onclick','javascript:changeShow(this);');
            	  }
            	  $(row).children('td').eq(2).attr('content',data.XMMC);
            	  if(data.XMWZ.length>maxShowLength){
            		  $(row).children('td').eq(3).attr('onclick','javascript:changeShow(this);');
            	  }
            	  $(row).children('td').eq(3).attr('content',data.XMWZ);
            	  if(data.JSDW.length>maxShowLength){
            		  $(row).children('td').eq(4).attr('onclick','javascript:changeShow(this);');
            	  }
            	  $(row).children('td').eq(4).attr('content',data.JSDW);
            	 
              },
              "aoColumns" : [//初始化要显示的列 
                /*  {  
                	"width":100,
                    "mDataProp" : "XMWYM"
                    
                  }, 
                  { 
                    "mDataProp" : "XMBH",
                    "render":function(data,type,full,meta){
                     	 if(full.XMBH.length>maxShowLength){
                     		 return getPartHtml(full.XMBH);
                     	 }else{
                     		 return full.XMBH;
                     	 }
                   	return '<div id="a" style="width:20%; overflow:hidden;white-space:nowrap; text-overflow:ellipsis;">'+full.JSDW+'</div>';
                      }
                  }, */
                  { 
                    "mDataProp" : "XMMC",
                     "render":function(data,type,full,meta){
                    	 if(full.XMMC.length>maxShowLength){
                    		 return getPartHtml(full.XMMC);
                    	 }else{
                    		 return full.XMMC;
                    	 }
                    	/* return '<div id="a" style="width:20%;overflow:hidden;white-space:nowrap; text-overflow:ellipsis;">'+full.XMMC+'</div>';*/
                     }
                  }, 
                /*  { 
                    "mDataProp" : "XMWZ",
                    "render":function(data,type,full,meta){
                   	 if(full.XMWZ.length>maxShowLength){
                   		 return getPartHtml(full.XMWZ);
                   	 }else{
                   		 return full.XMWZ;
                   	 }
                    	 return '<div id="a" style="width:20%;overflow:hidden;white-space:nowrap; text-overflow:ellipsis;">'+full.XMWZ+'</div>';
                    }
                  }, 
                  { 
                    "mDataProp" : "JSDW"
                  }, 
                  { 
                	"width":100,
                    "mDataProp" : "SSQH",
                    "render":function(data,type,full,meta){
                     	 if(full.SSQH.length>maxShowLength){
                     		 return getPartHtml(full.SSQH);
                     	 }else{
                     		 return full.SSQH;
                     	 }
                   	return '<div id="a" style="width:20%; overflow:hidden;white-space:nowrap; text-overflow:ellipsis;">'+full.JSDW+'</div>';
                      }
                  },*/
                  {
                	"width":200,
	                "data": null,
	                "render": function(data, type, row) {
	                    // 记录index
	                	//var startIndex = meta.settings._iDisplayStart;
     	             
	                   // var index = startIndex+meta.row+1;
	                	//var index=row.ORDERBY;
	                    // 设定按钮
	                   
	                        var html = "<span class='badge table-badge bg-blue table-info' mydata='"+row.XMWYM+"' id='" + row.XMWYM + "'>&nbsp;<i class='fa fa-info'></i>&nbsp;详情</span>"
	                        html += "<span class='badge table-badge bg-green table-fawen' mydata='"+row.XMWYM+"' id='" + row.XMWYM + "'>&nbsp;<i class='fa fa-archive'></i>&nbsp;发文</span>"
	                        html += "<span class='badge table-badge bg-red table-edit' mydata='"+row.XMWYM+"' id='" + row.XMWYM + "'>&nbsp;<i class='fa fa-pencil'></i>&nbsp;编辑</span>"
	                        /* html += "<span class='badge table-badge bg-green'><i class='fa fa-check'></i>&nbsp;提交</span>"*/
	                  
	                    return html;
	                }
		          }
              ], 
              
			  fnDrawCallback: function(table) {
				  appendSkipPage();
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
  
    //全选
    
    
  /*  $('table th input:checkbox').on( 
        'click', 
        function() { 
          var that = this; 
          console.log( $(this).closest('table'))
          $(this).closest('table').find( 
              'tr > td:first-child input:checkbox').each( 
              function() { 
                this.checked = that.checked; 
                $(this).closest('tr').toggleClass('selected'); 
              }); 
  
        }); */
    

    
   
    
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
	
	
    // 详情子表格  一定要注意this问题啊！！！！！！！！！！！！！
   /* $(".table tbody").on("click", ".table-info", function() {
    	  //var id = $(this).attr("id") - 1;
         if ($(this).hasClass("bg-blue")) {
	    	 $(".table-info").removeClass("bg-disabled").addClass("bg-blue");
	         $("#search-details").hide();
            $(this).removeClass("bg-blue").addClass("bg-disabled");
			var XMWYM = $(this).attr("id");
			//var FWLXID = $(this).attr("mydata");

		
		} else {
          $(this).removeClass("bg-disabled").addClass("bg-blue");
          $("#search-details").hide();

      }
    			
    });
    		
  }); */
	
	
    // 详情子表格  一定要注意this问题啊！！！！！！！！！！！！！
    $(".table tbody").on("click", ".table-info", function() {
    			var projKey = $(this).attr("id");
    		    var row = $('#table_project').DataTable().row($(this).closest("tr"));
    	        if (row.child() == undefined) {
    	        	//alert(1);
    	        	console.log($(this));
    	            $(this).removeClass("bg-blue").addClass("bg-disabled");
    	            var html = "<table id='example2' class='table table-condensed table-child'><tbody>";
    	            html+="<tr><td>test1</td><td>"+23+"</td><td>"+"test2"+"</td><td class='table-child-middle'></td><td></td><td></td><td></td><td></td></tr>"
    	            html += "</tbody></table>";
    	            
    	            var $table = $(html);
    	            $table.attr("id", "childTable_" + projKey);
    	            var childTable = $table.DataTable({
    	                // 基础设定
    	                paging: false,
    	                ordering: false,
    	                lengthChange: false,
    	                searching: false,
    	                info: false,
    	                autoWidth: false,
    	                columns: [{
    	                        "width": 30
    	                    },
    	                    null,
    	                    null,
    	                    null,
    	                    {
    	                        "width": 30
    	                    },
    	                    null,
    	                    null,
    	                    null
    	                ]
    	            });
    	            row.child(childTable.table().container());
    	            row.child.show();
    	          /*  $.ajax({
    	            	type: "POST",
    	            	url:'sphz/project/getProjByKey',
    	            	data: {tm:new Date().getTime(),PROJ_KEY:projKey},
    	        		dataType:'json',
    	        		cache: false,
    	            	success:function(data){
    	            		var proj=data.projList[0];
    	            		delete proj["PROJ_ID"];
    	            		delete proj["PROJ_GHYDXZ"];
    	            		delete proj["PROJ_JZWSYXZ"];
    	            		delete proj["PROJ_SSQH"];
		    	            var total=getJsonLength(proj);
		    	            var left;
		    	            var right;
		    	            if ( Math.ceil(total%2) == 0 ) {
		    	            	left= Math.floor(total/2);
		    	            	right=Math.floor(total/2);
		    		            
		    	            }else{
		    	            	left=Math.floor(total/2)+1;
		    	            	right=Math.floor(total/2);
		    	            }
		    		        for (var i = 0; i < right; i++) {
		    	                // 左
		    	                var idx_l = i + 1;
		    	                var name_l = keyArray[i];
		    	                html += "<tr><td>(" + idx_l + ")</td>";
		    	                html += "<td>" + keycnArray[i] + "</td>";
		    	                html += "<td>" + proj[name_l] + "</td>";
		    	                html += "<td class='table-child-middle'></td>";
		    	                // 右
		    	                var idx_r = i + left+1;
		    	                var name_r = keyArray[i + left];
		    	                html += "<td>(" + idx_r + ")</td>";
		    	                html += "<td>" + keycnArray[i + left] + "</td>";
		    	                html += "<td>" + proj[name_r] + "</td>";
		    	                html += "<td></td></tr>";
		    	            }
		    		        if ( Math.ceil(total%2) == 1 ) {
		    	                var ss=keyArray[left-1];
		    	                html+="<tr><td>("+left+")</td><td>"+keycnArray[left-1]+"</td><td>"+proj[ss]+"</td><td class='table-child-middle'></td><td></td><td></td><td></td><td></td></tr>"
		    		        }
		
		    	            html += "</tbody></table>";
		    	            var $table = $(html);
		    	            $table.attr("id", "childTable_" + projKey);
		    	            var childTable = $table.DataTable({
		    	                // 基础设定
		    	                paging: false,
		    	                ordering: false,
		    	                lengthChange: false,
		    	                searching: false,
		    	                info: false,
		    	                autoWidth: false,
		    	                columns: [{
		    	                        "width": 30
		    	                    },
		    	                    null,
		    	                    null,
		    	                    null,
		    	                    {
		    	                        "width": 30
		    	                    },
		    	                    null,
		    	                    null,
		    	                    null
		    	                ]
		    	            });
		    	            row.child(childTable.table().container());
		    	            row.child.show();
    	            	},
    	            	error:function(data){
    	            		
    	            	}
    	            		
    	            });*/
    	         
    	        } else {
    	            $(this).removeClass("bg-disabled").addClass("bg-blue");
    	            row.child(false);
    	        }
    		});
    		
  }); 


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

$(".table tbody").on("click", ".table-fawen", function() {
	var XMWYM = $(this).attr("id");
	var width=$(window).width();
    var height=$(window).height();
 	var indext = layer.open({
         type: 2,
         title: XMWYM,
         shadeClose: true,
         shade: false,
         maxmin: true, //开启最大化最小化按钮
         //btn:['确定','取消'],
         area: [width+'px', height+'px'],
         content: 'sphz/fawen/goList.do?XMWYM='+XMWYM
     });
}); 


 


 