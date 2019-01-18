 jQuery(function($) { 
  var keyArray=[
	  "PROJ_KEY",
	  "PROJ_NO",
	  "PROJ_NAME",
	  "PROJ_JSDW",
	  "PROJ_POSITION",
	  "PROJ_SSQH_NAME",
	  "PROJ_JZWSYXZ_NAME",
	  "PROJ_GHYDXZ_NAME",
	  "PROJ_SFHHYD_NAME",
	  "PROJ_HHBL",
	  "PROJ_TBRQ",
	  "PROJ_TBR",
	  "PROJ_TBBM",
	  "PROJ_BZ"
 ];
  var keycnArray=[
	  '项目唯一码',
	  '项目编号',
	  '项目名称',
	  '建设单位',
	  '项目位置',
	  '所属区划',
	  '建筑物使用性质',
	  '规划用地性质',
	  '是否混合用地',
	  '混合比率',
	  '填报日期',
	  '填报人',
	  '填报部门',
	  '备注'
 ];
    //初始化table 
    var parentTable = $('#table_project') 
        .dataTable( 
            { 
              "bPaginate" : true,//分页工具条显示 
              //"sPaginationType" : "full_numbers",//分页工具条样式 
              "bStateSave" : true, //是否打开客户端状态记录功能,此功能在ajax刷新纪录的时候不会将个性化设定回复为初始化状态  
              "bScrollCollapse" : true, //当显示的数据不足以支撑表格的默认的高度 
              //"bLengthChange" : false, //每页显示的记录数
              "lengthMenu": [ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100],
              "bFilter" : false, //搜索栏 
              "bSort" : true, //是否支持排序功能 
              "bInfo" : true, //显示表格信息 
              "bAutoWidth" : true, //自适应宽度 
              "bJQueryUI" : false,//是否开启主题 
              "bDestroy" : true, 
              "bProcessing" : true, //开启读取服务器数据时显示正在加载中……特别是大数据量的时候，开启此功能比较好 
              "bServerSide" : true,//服务器处理分页，默认是false，需要服务器处理，必须true 
              "sAjaxDataProp" : "projList",//是服务器分页的标志，必须有  
              "sAjaxSource" : "sphz/project/list",//通过ajax实现分页的url路径。  
              "aoColumns" : [//初始化要显示的列 
                  { 
                	"orderable": false,
                    "mDataProp" : "PROJ_KEY",//获取列数据，跟服务器返回字段一致 
                    "sClass" : "center",//显示样式 
                    "mRender" : function(data, type, full) {//返回自定义的样式 
                      return "<label><input type='checkbox' class='ace' /><span class='lbl'></span></label>"
                    } 
                  }, 
                  {  
            	  	"orderable": true,
                    "mDataProp" : "PROJ_KEY"
                  }, 
                  { 
                    "mDataProp" : "PROJ_NO"
                  }, 
                  { 
                    "mDataProp" : "PROJ_NAME"
                  }, 
                  { 
                    "mDataProp" : "PROJ_JSDW"
                  }, 
                  { 
                    "mDataProp" : "PROJ_POSITION"
                  }, 
                  { 
                    "mDataProp" : "PROJ_SSQH"
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
	                	//var index=row.ORDERBY;
	                    // 设定按钮
	                   
	                        var html = "<span class='badge table-badge bg-blue table-info' mydata='noSubmit' id='" + row.PROJ_KEY + "'>&nbsp;<i class='fa fa-info'></i>&nbsp;详情</span>"
	                       /* html += "<span class='badge table-badge bg-red table-edit' typedata='noSubmit' mydata='"+index+"' edittype='1'><i class='fa fa-pencil'></i>&nbsp;编辑</span>"
	                        html += "<span class='badge table-badge bg-green'><i class='fa fa-check'></i>&nbsp;提交</span>"*/
	                  
	                    return html;
	                }
		          }
              ], 
              
			  fnDrawCallback: function(table) {
				  appendSkipPage();
              },
              "oLanguage" : {//语言设置 
                "sProcessing" : "处理中...", 
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
    $('table th input:checkbox').on( 
        'click', 
        function() { 
          var that = this; 
          console.log( $(this).closest('table'));
          $(this).closest('table').find( 
              'tr > td:first-child input:checkbox').each( 
              function() { 
                this.checked = that.checked; 
                $(this).closest('tr').toggleClass('selected'); 
              }); 
  
        }); 
    
    function appendSkipPage() {  
    	$('.paginate_jump').remove();
        var table = $("#table_project").dataTable();   
        var template =
            $("<li class='paginate_button active'>" +
                "   <div class='input-group' style='margin-left:3px;'>" +
                "       <span class='input-group-addon' style='padding:0px 8px;background-color:#fff;font-size: 12px;'>跳转页</span>" +
                "       <input type='text' class='form-control' style='text-align:center;padding: 8px 2px;height:30px;width:40px;' />" +
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
	
	
    // 详情子表格  一定要注意this问题啊！！！！！！！！！！！！！
    $(".table tbody").on("click", ".table-info", function() {
    			var projKey = $(this).attr("id");
    		    var row = $('#table_project').DataTable().row($(this).closest("tr"));
    	        if (row.child() == undefined) {
    	        	//alert(1);
    	        	console.log($(this));
    	            $(this).removeClass("bg-blue").addClass("bg-disabled");
    	            var html = "<table id='example2' class='table table-condensed table-child'><tbody>";
    	            $.ajax({
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
    	            		
    	            });
    	         
    	        } else {
    	            $(this).removeClass("bg-disabled").addClass("bg-blue");
    	            row.child(false);
    	        }
    		});
    		
  }); 
 
 function fromExcel(){
		var indext = layer.open({
         type: 2,
         title: '上传',
         shadeClose: true,
         shade: false,
         maxmin: true, //开启最大化最小化按钮
         //btn:['确定','取消'],
         area: ['300px', '250px'],
         content: 'sphz/project/goUploadExcel.do'
     });
     
	}