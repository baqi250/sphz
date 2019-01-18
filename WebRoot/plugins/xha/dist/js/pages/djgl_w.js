// 表格数据
// check: -2不完善待补充 -1已完善待提交 0待审核 1已通过 2已驳回 3驳回再提交
// 表格数据
// check:  -1待提交 0待审核 1已通过 2已驳回
 var parentTableNoSubmit;
 var parentTableNoCheck;
 var parentTablePass;
 var parentTableReject;
 var noSubmitList;
 var noCheckList;
 var passList;
 var rejectList;
 var childdata_opinions = ["经办人", "建设位置", "建设单位", "组织机构代码", "发文编号", "用地性质名称", "用地面积", "混合比例", "容积率", "控制高度", "绿地率", "建筑密度", "停车位数", "海绵城市要求"];
 var childdataname_opinions = ["OPERATOR", "PROJ_LOCATION", "PROJ_UNIT", "ORG_CODE", "OPINIONS_NO", "LAND_USE_NAME", "LAND_AREA", "MIX_RATIO", "PLOT_RATIO", "CONTROL_HEIGHT", "GREEN_RATIO", "BUILDING_DENSITY", "PARKING", "SPONGE_REQUIRE"];
 var childdata_change = ["经办人", "建设位置", "建设单位", "组织机构代码", "发文编号", "用地性质名称", "用地面积", "混合比例", "容积率", "控制高度", "绿地率", "建筑密度", "停车位数", "海绵城市要求"];
 var childdataname_change = ["OPERATOR", "PROJ_LOCATION", "PROJ_UNIT", "ORG_CODE", "OPINIONS_NO", "LAND_USE_NAME", "LAND_AREA", "MIX_RATIO", "PLOT_RATIO", "CONTROL_HEIGHT", "GREEN_RATIO", "BUILDING_DENSITY", "PARKING", "SPONGE_REQUIRE"];
 var childdata_ppermition = ["经办人", "建设位置", "建设单位", "组织机构代码", "发文编号", "用地性质名称", "用地面积","海绵城市要求"];
 var childdataname_ppermition = ["OPERATOR", "PROJ_LOCATION", "PROJ_UNIT", "ORG_CODE", "OPINIONS_NO", "LAND_USE_NAME", "LAND_AREA",  "SPONGE_REQUIRE"];
 var childdata_building = ["经办人", "建设位置", "建设单位", "组织机构代码", "发文编号", "总建筑面积", "地上建筑面积", "地下建筑面积", "建筑物使用性质", "地上停车位", "地下停车位", "户数",  "海绵城市要求"];
 var childdataname_building = ["OPERATOR", "PROJ_LOCATION", "PROJ_UNIT", "ORG_CODE", "OPINIONS_NO", "BUILDING_AREA", "GROUND_AREA", "UNDERGROUND_AREA", "BUILDING_PROPERTY", "GROUND_PARKING", "UNDERGROUND_PARKING", "HOUSE_NUM","SPONGE_REQUIRE"];
 var childdata_traffic= ["经办人", "建设位置", "建设单位", "组织机构代码", "发文编号", "管线类别", "管线长度", "道路等级", "道路长度", "折算面积", "海绵城市要求"];
 var childdataname_traffic = ["OPERATOR", "PROJ_LOCATION", "PROJ_UNIT", "ORG_CODE", "OPINIONS_NO", "PIEP_CATEGORY", "PIEP_LENGTH", "ROAD_LEVEL", "ROAD_LENGTH", "CONVERT_AREA", "SPONGE_REQUIRE"];
 var childdata_complete = ["经办人", "建设位置", "建设单位", "组织机构代码", "发文编号", "总建筑面积", "地上建筑面积", "地下建筑面积", "海绵城市要求"];
 var childdataname_complete = ["OPERATOR", "PROJ_LOCATION", "PROJ_UNIT", "ORG_CODE", "OPINIONS_NO", "BUILDING_AREA", "GROUND_AREA", "UNDERGROUND_AREA", "SPONGE_REQUIRE"];
 
 var stageList=["建设项目选址意见书","建设项目规划条件变更","建设用地规划许可证","建设工程规划许可证（建筑）","建设工程规划许可证（市政交通）","建设工程竣工规划验收合格证"];	
$.ajax({
		type: "POST",
		url: 'sphz/getAddList',
    	data: {tm:new Date().getTime()},
		dataType:'json',
		cache: false,
		//async:false,
		success: function(data){
			console.log(data);
			noSubmitList=data.noSubmitList;
			for(var i=0;i<noSubmitList.length;i++){
				noSubmitList[i].ORDERBY=i+1;
			}
			noCheckList=data.noCheckList;
			for(var i=0;i<noCheckList.length;i++){
				noCheckList[i].ORDERBY=i+1;
			}
			passList=data.passList;
			for(var i=0;i<passList.length;i++){
				passList[i].ORDERBY=i+1;
			}
			rejectList=data.rejectList;
			for(var i=0;i<rejectList.length;i++){
				rejectList[i].ORDERBY=i+1;
			}
			var data=[noSubmitList,noCheckList,passList,rejectList];
			var parentTable = [];
		    // 表格
			  parentTableNoSubmit = $('#tab-1').DataTable({
		        // 基础设定
		        paging: true,
		        ordering: true,
		        lengthChange: false,
		        searching: false,
		        info: true,
		        autoWidth: false,
		        // 汉化
		        language: {
		            sProcessing: "处理中...",
		            sLengthMenu: "显示 _MENU_ 项结果",
		            sZeroRecords: "没有匹配结果",
		            sInfo: "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
		            sInfoEmpty: "显示第 0 至 0 项结果，共 0 项",
		            sInfoFiltered: "(由 _MAX_ 项结果过滤)",
		            sInfoPostFix: "",
		            sSearch: "搜索:",
		            sUrl: "",
		            sEmptyTable: "表中数据为空",
		            sLoadingRecords: "载入中...",
		            sInfoThousands: ",",
		            oPaginate: {
		                "sFirst": "首页",
		                "sPrevious": "上页",
		                "sNext": "下页",
		                "sLast": "末页"
		            },
		            oAria: {
		                "sSortAscending": ": 以升序排列此列",
		                "sSortDescending": ": 以降序排列此列"
		            }
		        },
		        // 加载数据未提交
		        data: noSubmitList,
		        // 设定列属性
		        columns: [{
		                "width": 30,
		                "orderable": true,
		                "data": "ORDERBY"
		            },
		            {
		                "width": 60,
		                "orderable": true,
		                "data": "PROJ_NO"
		            },
		            {
		                "data": "PROJ_NAME"
		            },
		            {
		                "data": "DEPARTMENT"
		            },
		            {
		                "data": "ADD_TIME"
		            },
		            {
		                "data": "OPINIONS_DATE"
		            },
		            {
		                "width": 200,
		                "orderable": true,
		                "data": "STAGE_TYPE",
		                "targets": 2,
		                "render": function(data, type, row) {
		                    return stageList[row.STAGE_TYPE-1];
		                }
		            },
		            {
		            	"data": "STAGE_TYPE","visible":false
		            },
		            {
		            	"data": "PKID","visible":false
		            },
		            {
		                "width": 200,
		                "orderable": false,
		                "data": null,
		                "targets": 3,
		                "render": function(data, type, row) {
		                    // 记录index
		                	//var startIndex = meta.settings._iDisplayStart;
           	             
		                   // var index = startIndex+meta.row+1;
		                	var index=row.ORDERBY;
		                    // 设定按钮
		                    if (row.STATUS == 2) {
		                        var html = "<span class='badge table-badge bg-blue table-info' mydata='noSubmit' id='" + index + "'>&nbsp;<i class='fa fa-info'></i>&nbsp;详情</span>"
		                        html += "<span class='badge table-badge bg-red table-edit' typedata='noSubmit' mydata='"+index+"' edittype='1'><i class='fa fa-pencil'></i>&nbsp;编辑</span>"
		                        html += "<span class='badge table-badge bg-green'><i class='fa fa-check'></i>&nbsp;提交</span>"
		                    } else {
		                    	 var html = "<span class='badge table-badge bg-blue table-info' mydata='noSubmit' id='" + index + "'>&nbsp;<i class='fa fa-info'></i>&nbsp;详情</span>"
		                         html += "<span class='badge table-badge bg-red table-edit' typedata='noSubmit' mydata='"+index+"' edittype='1'><i class='fa fa-pencil'></i>&nbsp;补充</span>"
		                         html += "<span class='badge table-badge bg-disabled'><i class='fa fa-check'></i>&nbsp;提交</span>"
		                    }
		                    return html;
		                }
		            }
		        ],
		        "rowCallback": function(row, data) {
		        	 if (data.check == 1) {
		                 $(row).css("background-color", "rgba(221,75,57,0.1)");
		             }
		        }
		    });
		    parentTable.push(parentTableNoSubmit);
		    
		    // 待审核
		    parentTableNoCheck = $('#tab-2').DataTable({
		        // 基础设定
		        paging: true,
		        ordering: true,
		        lengthChange: false,
		        searching: false,
		        info: true,
		        autoWidth: false,
		        // 汉化
		        language: {
		            sProcessing: "处理中...",
		            sLengthMenu: "显示 _MENU_ 项结果",
		            sZeroRecords: "没有匹配结果",
		            sInfo: "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
		            sInfoEmpty: "显示第 0 至 0 项结果，共 0 项",
		            sInfoFiltered: "(由 _MAX_ 项结果过滤)",
		            sInfoPostFix: "",
		            sSearch: "搜索:",
		            sUrl: "",
		            sEmptyTable: "表中数据为空",
		            sLoadingRecords: "载入中...",
		            sInfoThousands: ",",
		            oPaginate: {
		                "sFirst": "首页",
		                "sPrevious": "上页",
		                "sNext": "下页",
		                "sLast": "末页"
		            },
		            oAria: {
		                "sSortAscending": ": 以升序排列此列",
		                "sSortDescending": ": 以降序排列此列"
		            }
		        },
		        // 加载数据
		        data: noCheckList,
		        // 设定列属性
		        columns: [{
	                "width": 30,
	                "orderable": true,
	                "data": "ORDERBY"
	            },
	            {
	                "width": 60,
	                "orderable": true,
	                "data": "PROJ_NO"
	            },
	            {
	                "data": "PROJ_NAME"
	            },
	            {
	                "data": "DEPARTMENT"
	            },
	            {
	                "data": "ADD_TIME"
	            },
	            {
	                "data": "OPINIONS_DATE"
	            },
	            {
	                "width": 200,
	                "orderable": true,
	                "data": "STAGE_TYPE",
	                "targets": 2,
	                "render": function(data, type, row) {
	                    return stageList[row.STAGE_TYPE-1];
	                }
	            },
	            {
	            	"data": "STAGE_TYPE","visible":false
	            },
	            {
	            	"data": "PKID","visible":false
	            },
	            {
	                "width": 200,
	                "orderable": false,
	                "data": null,
	                "targets": 3,
	                "render": function(data, type, row) {
	                    // 记录index
	                	//var startIndex = meta.settings._iDisplayStart;
       	             
	                   // var index = startIndex+meta.row+1;
	                	var index=row.ORDERBY;
	                    // 设定按钮
	                	var html = "<span class='badge table-badge bg-blue table-info' mydata='noCheck' id='" + index + "'>&nbsp;<i class='fa fa-info'></i>&nbsp;详情</span>"
	                    return html;
	                }
	            }
	        ]
		    });
		    parentTable.push(parentTableNoCheck);
		    
		    // 已通过
		    parentTablePass = $('#tab-3').DataTable({
		        // 基础设定
		        paging: true,
		        ordering: true,
		        lengthChange: false,
		        searching: false,
		        info: true,
		        autoWidth: false,
		        // 汉化
		        language: {
		            sProcessing: "处理中...",
		            sLengthMenu: "显示 _MENU_ 项结果",
		            sZeroRecords: "没有匹配结果",
		            sInfo: "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
		            sInfoEmpty: "显示第 0 至 0 项结果，共 0 项",
		            sInfoFiltered: "(由 _MAX_ 项结果过滤)",
		            sInfoPostFix: "",
		            sSearch: "搜索:",
		            sUrl: "",
		            sEmptyTable: "表中数据为空",
		            sLoadingRecords: "载入中...",
		            sInfoThousands: ",",
		            oPaginate: {
		                "sFirst": "首页",
		                "sPrevious": "上页",
		                "sNext": "下页",
		                "sLast": "末页"
		            },
		            oAria: {
		                "sSortAscending": ": 以升序排列此列",
		                "sSortDescending": ": 以降序排列此列"
		            }
		        },
		        // 加载数据
		        data: passList,
		        // 设定列属性
		        columns: [{
	                "width": 30,
	                "orderable": true,
	                "data": "ORDERBY"
	            },
	            {
	                "width": 60,
	                "orderable": true,
	                "data": "PROJ_NO"
	            },
	            {
	                "data": "PROJ_NAME"
	            },
	            {
	                "data": "DEPARTMENT"
	            },
	            {
	                "data": "ADD_TIME"
	            },
	            {
	                "data": "OPINIONS_DATE"
	            },
	            {
	                "width": 200,
	                "orderable": true,
	                "data": "STAGE_TYPE",
	                "targets": 2,
	                "render": function(data, type, row) {
	                    return stageList[row.STAGE_TYPE-1];
	                }
	            },
	            {
	            	"data": "STAGE_TYPE","visible":false
	            },
	            {
	            	"data": "PKID","visible":false
	            },
	            {
	                "width": 200,
	                "orderable": false,
	                "data": null,
	                "targets": 3,
	                "render": function(data, type, row) {
	                    // 记录index
	                	//var startIndex = meta.settings._iDisplayStart;
       	             
	                   // var index = startIndex+meta.row+1;
	                	var index=row.ORDERBY;
	                    // 设定按钮
	                	var html = "<span class='badge table-badge bg-blue table-info' mydata='pass' id='" + index + "'>&nbsp;<i class='fa fa-info'></i>&nbsp;详情</span>"
	                    return html;
	                }
	            }
	        ]
		    });
		    parentTable.push(parentTablePass);
		    
		    // 被驳回
		    parentTableReject = $('#tab-4').DataTable({
		        // 基础设定
		        paging: true,
		        ordering: true,
		        lengthChange: false,
		        searching: false,
		        info: true,
		        autoWidth: false,
		        // 汉化
		        language: {
		            sProcessing: "处理中...",
		            sLengthMenu: "显示 _MENU_ 项结果",
		            sZeroRecords: "没有匹配结果",
		            sInfo: "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
		            sInfoEmpty: "显示第 0 至 0 项结果，共 0 项",
		            sInfoFiltered: "(由 _MAX_ 项结果过滤)",
		            sInfoPostFix: "",
		            sSearch: "搜索:",
		            sUrl: "",
		            sEmptyTable: "表中数据为空",
		            sLoadingRecords: "载入中...",
		            sInfoThousands: ",",
		            oPaginate: {
		                "sFirst": "首页",
		                "sPrevious": "上页",
		                "sNext": "下页",
		                "sLast": "末页"
		            },
		            oAria: {
		                "sSortAscending": ": 以升序排列此列",
		                "sSortDescending": ": 以降序排列此列"
		            }
		        },
		        // 加载数据
		        data: rejectList,
		        // 设定列属性
		        columns: [{
	                "width": 30,
	                "orderable": true,
	                "data": "ORDERBY"
	            },
	            {
	                "width": 60,
	                "orderable": true,
	                "data": "PROJ_NO"
	            },
	            {
	                "data": "PROJ_NAME"
	            },
	            {
	                "data": "DEPARTMENT"
	            },
	            {
	                "data": "ADD_TIME"
	            },
	            {
	                "data": "OPINIONS_DATE"
	            },
	            {
	                "width": 200,
	                "orderable": true,
	                "data": "STAGE_TYPE",
	                "targets": 2,
	                "render": function(data, type, row) {
	                    return stageList[row.STAGE_TYPE-1];
	                }
	            },
	            {
	            	"data": "STAGE_TYPE","visible":false
	            },
	            {
	            	"data": "PKID","visible":false
	            },
	            {
	                "width": 200,
	                "orderable": false,
	                "data": null,
	                "targets": 3,
	                "render": function(data, type, row) {
	                    // 记录index
	                	//var startIndex = meta.settings._iDisplayStart;
       	             
	                   // var index = startIndex+meta.row+1;
	                	var index=row.ORDERBY;
	                    // 设定按钮
	                	
                         var html = "<span class='badge table-badge bg-blue table-info' mydata='reject' id='" + index + "'>&nbsp;<i class='fa fa-info'></i>&nbsp;详情</span>"
                 		 html += "<span class='badge table-badge bg-red table-edit' typedata='reject' mydata='"+index+"'  edittype='2'><i class='fa fa-pencil'></i>&nbsp;修改</span>"
                         html += "<span class='badge table-badge bg-green'>&nbsp;再提交</span>"
	                    
	                     return html;
	                }
	            }
	        ],
		        "rowCallback": function(row, data) {
		            if (data.check == 5) {
		                $(row).css("background-color", "rgba(221,75,57,0.1)");
		            }
		        }
		    });
		    parentTable.push(parentTableReject);

		      $(function() {
		    	    // 搜索按钮显示
		    	    var showinfoTrigger = '[data-widget="showinfo"]';
		    	    $(showinfoTrigger).click(function() {
		    	        if ($(this).hasClass("info-showed")) {
		    	            $(this).addClass("info-hidden").removeClass("info-showed");
		    	            $(this).children("i").removeClass("text-blue");
		    	            $("#xxsh-search").hide("slow");
		    	        } else {
		    	            $(this).addClass("info-showed").removeClass("info-hidden");
		    	            $(this).children("i").addClass("text-blue");
		    	            $("#xxsh-search").show("slow");
		    	        }
		    	    });

		    	    // 选择框
		    	    $('.select2').select2({
		    	        language: "zh-CN"
		    	    });

		    	 
		    	    // 详情子表格
		    	    $(".table tbody").on("click", ".table-info", function() {
		    	        var id = $(this).attr("id") - 1;
		    	        var type = $(this).attr("mydata");
		    	        var tableList;
		    	        var table;
		    	        if(type=="noSubmit"){
		    	        	table=parentTableNoSubmit;
		    	        	tableList=noSubmitList;
		    	        }
		    	        if(type=="noCheck"){
		    	        	table=parentTableNoCheck;
		    	        	tableList=noCheckList;
		    	        }
		    	        if(type=="pass"){
		    	        	table=parentTablePass;
		    	        	tableList=passList;
		    	        }
		    	        if(type=="reject"){
		    	        	table=parentTableReject;
		    	        	tableList=rejectList;
		    	        }
		    	        
		    	        //此处拿到隐藏列的id
		    	        var STAGE_TYPE = tableList[id].STAGE_TYPE;
		    	        var PKID = tableList[id].PKID;
		    	        var childdataname;
		    	        var childdata;
		    	        if(STAGE_TYPE==1){
		    	        	childdataname=childdataname_opinions;
		    	        	childdata=childdata_opinions;
		    	        }
		    	        if(STAGE_TYPE==2){
		    	        	childdataname=childdataname_change;
		    	        	childdata=childdata_change;
		    	        }
		    	        if(STAGE_TYPE==3){
		    	        	childdataname=childdataname_ppermition;
		    	        	childdata=childdata_ppermition;
		    	        }
		    	        if(STAGE_TYPE==4){
		    	        	childdataname=childdataname_building;
		    	        	childdata=childdata_building;
		    	        }
		    	        if(STAGE_TYPE==5){
		    	        	childdataname=childdataname_traffic;
		    	        	childdata=childdata_traffic;
		    	        }
		    	        if(STAGE_TYPE==6){
		    	        	childdataname=childdataname_complete;
		    	        	childdata=childdata_complete;
		    	        }
		    	        console.log(table);
		    	        var row = table.row($(this).closest("tr"));
		    	        if (row.child() == undefined) {
		    	            $(this).removeClass("bg-blue").addClass("bg-disabled");
		    	            var html = "<table id='example2' class='table table-condensed table-child'><tbody>";
		    	            var total=childdataname.length;
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
		    	                var name_l = childdataname[i];
		    	                html += "<tr><td>(" + idx_l + ")</td>";
		    	                html += "<td>" + childdata[i] + "</td>";
		    	                html += "<td>" + tableList[id][name_l] + "</td>";
		    	                html += "<td class='table-child-middle'></td>";
		    	                // 右
		    	                var idx_r = i + left+1;
		    	                var name_r = childdataname[i + left];
		    	                html += "<td>(" + idx_r + ")</td>";
		    	                html += "<td>" + childdata[i + left] + "</td>";
		    	                html += "<td>" + tableList[id][name_r] + "</td>";
		    	                html += "<td></td></tr>";
		    	            }
			    	        if ( Math.ceil(total%2) == 1 ) {
		                        var ss=childdataname[left-1];
		                        html+="<tr><td>("+left+")</td><td>"+childdata[left-1]+"</td><td>"+tableList[id][ss]+"</td><td class='table-child-middle'></td><td></td><td></td><td></td><td></td></tr>"
			    	        }

		    	            html += "</tbody></table>";
		    	            var $table = $(html);
		    	            $table.attr("id", "childTable_" + id);
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
		    	        } else {
		    	            $(this).removeClass("bg-disabled").addClass("bg-blue");
		    	            row.child(false);
		    	        }
		    	    });  
		    	    
		    	    $("#tab_4 tbody").delegate("tr","dblclick",function (e) { //会为符合条件的现有标签和未来标签都绑定事件,防止分页无效
		    	    	var iii= layer.alert(parentTableReject.row(this).data().REJECT_REASON, {
		    	    		title:'驳回原因',
		    	    	  skin: 'layui-layer-molv' //样式类名
		    	    	  ,closeBtn: 0
		    	    	}, function(){
		    	    		layer.close(iii);
		    	    	});
		    	    	
	                });
		    	    
		    	 
		    	    // 审核编辑
		    	    $(".table tbody").on("click", ".table-edit", function() {
		    	    	  var id = $(this).attr("mydata") - 1;
		    	    	  var type = $(this).attr("typedata");
		    	    	  var  edittype=$(this).attr("edittype");
		    	    	  alert(edittype);
			    	      var tableList;
			    	      var row;
			    	      if(type=="noSubmit"){
			    	    	row= $('#tab-1').DataTable().row(id);
			    	      	tableList=noSubmitList;
			    	      }
			    	      if(type=="noCheck"){
			    	    	row= $('#tab-2').DataTable().row(id);
			    	      	tableList=noCheckList;
			    	      }
			    	      if(type=="pass"){
			    	    	row= $('#tab-3').DataTable().row(id);
			    	      	tableList=passList;
			    	      }
			    	      if(type=="reject"){
			    	    	row= $('#tab-4').DataTable().row(id);
			    	      	tableList=rejectList;
			    	      }
		    	    
		    	        var orirowdate= tableList[id];
		    	        var STAGE_TYPE =orirowdate.STAGE_TYPE;
		    	        var PKID =orirowdate.PKID;
		    	        var DEPARTMENT=orirowdate.DEPARTMENT;
		    	        var PROJ_NAME=orirowdate.PROJ_NAME;
		    	        var width=$(window).width();
		    	        var height=$(window).height();
		    	    	var indext = layer.open({
		                    type: 2,
		                    title: '编辑',
		                    shadeClose: true,
		                    shade: false,
		                    maxmin: true, //开启最大化最小化按钮
		                    //btn:['确定','取消'],
		                    area: [width+'px', height+'px'],
		                    content: 'sphz/goEdit.do?PKID='+PKID+"&STAGE_TYPE="+STAGE_TYPE+"&EDIT_TYPE="+edittype
		                });
		    	        
		    	  
		    	    });
		    	    
		    	   
		    	})
			
		},
		error:function(data){
			
			console.log(data);
			alert(error);
		}
	});

function export2Excel(){
	window.location.href='ureport/preview?_u=file:sphz_test.ureport.xml';
}
function export2Excel1(){
	window.location.href='mongo/user';
}

function changeCondition(){
	//alert(stage);
	var stage=$("#StageSelect").val();
	//var department=$("#DepartmentSelect").val();
	$.ajax({
		type: "POST",
		url: 'sphz/getAddList',
    	data: {tm:new Date().getTime(),STAGE:stage},
		dataType:'json',
		cache: false,
		success:function(data){
			console.log(data);
			noSubmitList=data.noSubmitList;
			for(var i=0;i<noSubmitList.length;i++){
				noSubmitList[i].ORDERBY=i+1;
			}
			noCheckList=data.noCheckList;
			for(var i=0;i<noCheckList.length;i++){
				noCheckList[i].ORDERBY=i+1;
			}
			passList=data.passList;
			for(var i=0;i<passList.length;i++){
				passList[i].ORDERBY=i+1;
			}
			rejectList=data.rejectList;
			for(var i=0;i<rejectList.length;i++){
				rejectList[i].ORDERBY=i+1;
			}
			
		    var currentPageNoSubmit = parentTableNoSubmit.page();
		    parentTableNoSubmit.clear();
		    parentTableNoSubmit.rows.add(noSubmitList)
		    parentTableNoSubmit.page(currentPageNoSubmit).draw( false );
		    
		    var currentPageNoCheck = parentTableNoCheck.page();
		    parentTableNoCheck.clear();
		    parentTableNoCheck.rows.add(noCheckList)
		    parentTableNoCheck.page(currentPageNoCheck).draw( false );
		    
		    var currentPagePass = parentTablePass.page();
		    parentTablePass.clear();
		    parentTablePass.rows.add(passList)
		    parentTablePass.page(currentPagePass).draw( false );
		    
		    var currentPageReject = parentTableReject.page();
		    parentTableReject.clear();
		    parentTableReject.rows.add(rejectList)
		    parentTableReject.page(currentPageReject).draw( false );
			
		},
		error:function(data){
			console.log(data);
		}
	});
  
}

