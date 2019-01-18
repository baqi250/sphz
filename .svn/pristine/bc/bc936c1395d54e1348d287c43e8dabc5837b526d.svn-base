// 表格数据
// check:  -1待提交 0待审核 1已通过 2已驳回
 var statusSelect;
 var parentTable;
 var checkList;
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
		url: 'sphz/getCheckList',
    	data: {tm:new Date().getTime()},
		dataType:'json',
		cache: false,
		//async:false,
		success: function(data){
			console.log(data);
			checkList=data.checkList;
			for(var i=0;i<checkList.length;i++){
				checkList[i].ORDERBY=i+1;
			}
			
		    // 表格
		      parentTable = $('#example1').DataTable({
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
		        data: checkList,
		        // 设定列属性
		        columns: [{
		                "width": 30,
		                "orderable": true,
		                "data": "ORDERBY",
		                "targets": 2,
		                "render": function(data, type, row) {
		                	var index = data;
		                    if (row.STATUS == 3) {
		                        var html = "<i class='fa fa-circle text-yellow table-check'></i>";
		                    } else
		                    if (row.STATUS == 4) {
		                        var html = "<i class='fa fa-circle text-success table-check'></i>";
		                    } else {
		                        var html = "<i class='fa fa-circle text-red table-check'></i>";
		                    }
		                    html += index; 
		                    return html;
		                }
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
		                    if (row.STATUS == 3) {
		                        var html = "<span class='badge table-badge bg-blue table-info' id='" + index + "'>&nbsp;<i class='fa fa-info'></i>&nbsp;详情</span>"
		                        html += "<span class='badge table-badge bg-green pass_info' mydata='"+index+"'><i class='fa fa-check'></i>&nbsp;通过</span>"
		                        html += "<span class='badge table-badge bg-red reject_info' mydata='"+index+"'><i class='fa fa-close'></i>&nbsp;驳回</span>"
		                    } else {
		                        var html = "<span class='badge table-badge bg-blue table-info' id='" + index + "'>&nbsp;<i class='fa fa-info'></i>&nbsp;详情</span>"
		                        html += "<span class='badge table-badge bg-disabled'><i class='fa fa-check'></i>&nbsp;通过</span>"
		                        html += "<span class='badge table-badge bg-disabled'><i class='fa fa-close'></i>&nbsp;驳回</span>"
		                    }
		                    return html;
		                }
		            }
		        ],
		        "rowCallback": function(row, data) {
		            if (data.STATUS == 5) {
		                $(row).css("background-color", "rgba(221,75,57,0.1)");
		            }
		        }
		    });
		      
		      
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
		    	       /* var param=$("td",this);
		    	        var STAGE_TYPE = $(param[6]).text();*/
		    	        //var STAGE_TYPE=$(this).attr("STAGE_TYPE");
		    	       // console.log($(this).parents("tr"));
		    	        //先拿到点击的行号
		    	        //var rowIndex = $(this).parents("tr").index();
		    	        
		    	        //此处拿到隐藏列的id
		    	        var STAGE_TYPE = checkList[id].STAGE_TYPE;
		    	        var PKID = checkList[id].PKID;
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
		    	        var row = parentTable.row($(this).closest("tr"));
		    	        if (row.child() == undefined) {
		    	            $(this).removeClass("bg-blue").addClass("bg-disabled");
		    	           // var html = "<table id='example2' class='table table-condensed table-child'><thead><tr><th>序号</th><th>项目</th><th>数值</th><th class='table-child-middle'></th><th>序号</th><th>项目</th><th>数值</th><th></th></tr></thead><tbody>";
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
		    	                html += "<td>" + checkList[id][name_l] + "</td>";
		    	                html += "<td class='table-child-middle'></td>";
		    	                // 右
		    	                var idx_r = i + left+1;
		    	                var name_r = childdataname[i + left];
		    	                html += "<td>(" + idx_r + ")</td>";
		    	                html += "<td>" + childdata[i + left] + "</td>";
		    	                html += "<td>" + checkList[id][name_r] + "</td>";
		    	                html += "<td></td></tr>";
		    	            }
			    	        if ( Math.ceil(total%2) == 1 ) {
		                        var ss=childdataname[left-1];
		                        html+="<tr><td>("+left+")</td><td>"+childdata[left-1]+"</td><td>"+checkList[id][ss]+"</td><td class='table-child-middle'></td><td></td><td></td><td></td><td></td></tr>"
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
		    	    
		    	   
		    	    // 审核通过
		    	    $(".table tbody").on("click", ".pass_info", function() {
		    	    	var id = $(this).attr("mydata") - 1;
		    	    	console.log(id);
		    	        //先拿到点击的行号
		    	       // var rowIndex = $(this).parents("tr").index();
		    	        var orirowdate= checkList[id];//这块有问题，如果是筛选条件
		    	        //此处拿到隐藏列的id
		    	        var row= $('#example1').DataTable().row(id);
		    	      //  var rowData= row.data();
		    	        var STAGE_TYPE =orirowdate.STAGE_TYPE;
		    	        var PKID =orirowdate.PKID;
		    	        var DEPARTMENT=orirowdate.DEPARTMENT;
		    	        var PROJ_NAME=orirowdate.PROJ_NAME;
		    			var indext=layer.confirm('通过后不可更改，是否确定通过？', {
		  				  btn: ['确定','取消'] //按钮
		  				}, function(){
		  					$.ajax({
		  						type: "POST",
		  						url: 'sphz/pass',
		  				    	data: {tm:new Date().getTime(),STAGE_TYPE:STAGE_TYPE,PKID:PKID,DEPARTMENT:DEPARTMENT,PROJ_NAME:PROJ_NAME},
		  						dataType:'json',
		  						cache: false,
		  						//async:false,
		  						success: function(data){
		  							console.log(data);
		  							orirowdate.STATUS=4;
		  							row.data(orirowdate).draw(false);//停留在当前页
		  							layer.close(indext);
		  							
		  						},
		  						error:function(data){
		  							console.log(data);
		  							alert(error);
		  						}
	  						});
		  				  
		  				}, function(){
		  					
		  				});
		    	        
		    	  
		    	    });
		    	    
		    	    // 审核驳回
		    	    $(".table tbody").on("click", ".reject_info", function() {
		    	    	var id = $(this).attr("mydata") - 1;
		    	    	console.log(id);
		    	        //先拿到点击的行号
		    	       // var rowIndex = $(this).parents("tr").index();
		    	        var orirowdate= checkList[id];
		    	        //此处拿到隐藏列的id
		    	        var row= $('#example1').DataTable().row(id);
		    	      //  var rowData= row.data();
		    	        var STAGE_TYPE =orirowdate.STAGE_TYPE;
		    	        var PKID =orirowdate.PKID;
		    	        var DEPARTMENT=orirowdate.DEPARTMENT;
		    	        var PROJ_NAME=orirowdate.PROJ_NAME;
		    	      /*  //先拿到点击的行号
		    	        var rowIndex = $(this).parents("tr").index();
		    	        var orirowdate= checkList[rowIndex];
		    	        //此处拿到隐藏列的id
		    	        var row= $('#example1').DataTable().row(rowIndex);
		    	        var rowData= row.data();
		    	        var STAGE_TYPE =rowData.STAGE_TYPE;
		    	        var PKID =rowData.PKID;*/
		    	       /* var STAGE_TYPE = $('#example1').DataTable().row(rowIndex).data().STAGE_TYPE;
		    	        var PKID = $('#example1').DataTable().row(rowIndex).data().PKID;*/
		    	        layer.prompt({title: '请写驳回原因', formType: 2}, function(text, index){
		    	            layer.close(index);
		    	            layer.msg('您的驳回原因是：'+'<br>'+text);
		    	        	$.ajax({
		  						type: "POST",
		  						url: 'sphz/reject',
		  				    	data: {tm:new Date().getTime(),STAGE_TYPE:STAGE_TYPE,PKID:PKID,DEPARTMENT:DEPARTMENT,PROJ_NAME:PROJ_NAME,REJECT_REASON:text},
		  						dataType:'json',
		  						cache: false,
		  						//async:false,
		  						success: function(data){
		  							console.log(data);
		  							orirowdate.STATUS=5;
		  							row.data(orirowdate).draw(false);
		  							layer.close(indext);
		  						},
		  						error:function(data){
		  							console.log(data);
		  							alert(error);
		  						}
	  						});
		    	        });
		    	        
		    	/*		var indext=layer.confirm('是否确定驳回？', {
		  				  btn: ['确定','取消'] //按钮
		  				}, function(){
		  					$.ajax({
		  						type: "POST",
		  						url: 'sphz/reject',
		  				    	data: {tm:new Date().getTime(),STAGE_TYPE:STAGE_TYPE,PKID:PKID,DEPARTMENT:DEPARTMENT,PROJ_NAME:PROJ_NAME},
		  						dataType:'json',
		  						cache: false,
		  						//async:false,
		  						success: function(data){
		  							console.log(data);
		  							orirowdate.STATUS=5;
		  							row.data(orirowdate).draw(false);
		  							layer.close(indext);
		  						},
		  						error:function(data){
		  							console.log(data);
		  							alert(error);
		  						}
	  						});
		  				  
		  				}, function(){
		  					
		  				});*/
		    	        
		    	  
		    	    });
		    	    
		    	    
		    	    
		    	    
		    	    
		    	    
		    	    
		    	})

			
		},
		error:function(data){
			
			console.log(data);
			alert(error);
		}
	});
	
	
	function changeCondition(){
		//alert(stage);
		var stage=$("#StageSelect").val();
		var department=$("#DepartmentSelect").val();
		$.ajax({
			type: "POST",
			url: 'sphz/search',
	    	data: {tm:new Date().getTime(),STAGE:stage,DEPARTMENT:department,STATUS:statusSelect},
			dataType:'json',
			cache: false,
			success:function(data){
				console.log(data);
				checkList=data.checkList;
				for(var i=0;i<checkList.length;i++){
					checkList[i].ORDERBY=i+1;
				}
			    var currentPage = parentTable.page()
			    parentTable.clear();
			    parentTable.rows.add(checkList);
			    parentTable.page(currentPage).draw( false );
				
			},
			error:function(data){
				console.log(data);
			}
		});
		
	}
	
	$(".statusselect").on('click',function(){
		$(".statusselect").removeClass('statusselected');
		$(this).addClass('statusselected');
		statusSelect= $(this).attr("mydata");
		var stage=$("#StageSelect").val();
		var department=$("#DepartmentSelect").val();
		$.ajax({
			type: "POST",
			url: 'sphz/search',
	    	data: {tm:new Date().getTime(),STAGE:stage,DEPARTMENT:department,STATUS:statusSelect},
			dataType:'json',
			cache: false,
			success:function(data){
				console.log(data);
				checkList=data.checkList;
				for(var i=0;i<checkList.length;i++){
					checkList[i].ORDERBY=i+1;
				}
			    var currentPage = parentTable.page()
			    parentTable.clear();
			    parentTable.rows.add(checkList);
			    parentTable.page(currentPage).draw( false );
				
			},
			error:function(data){
				console.log(data);
			}
		});
		
	})
	
	function findByStatus(thisdiv,status){
	/*	var statusDiv= $(".statusselect");
		for(var i=0;i<statusDiv;i++){
			$(".statusselect:eq("+i+")").removeClass("statusselected");
		}*/
		console.log( thisdiv);
		thisdiv.addClass("statusselected");
		var stage=$("#StageSelect").val();
		var department=$("#DepartmentSelect").val();
		$.ajax({
			type: "POST",
			url: 'sphz/search',
	    	data: {tm:new Date().getTime(),STAGE:stage,DEPARTMENT:department,STATUS:status},
			dataType:'json',
			cache: false,
			success:function(data){
				console.log(data);
				checkList=data.checkList;
				for(var i=0;i<checkList.length;i++){
					checkList[i].ORDERBY=i+1;
				}
			    var currentPage = parentTable.page()
			    parentTable.clear();
			    parentTable.rows.add(checkList);
			    parentTable.page(currentPage).draw( false );
				
			},
			error:function(data){
				console.log(data);
			}
		});
	}

// 详情子表格


