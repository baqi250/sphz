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
    var parentTable = $('#table_project').dataTable({ 
            "language": {
                "aria": {
                    "sortAscending": ": activate to sort column ascending",
                    "sortDescending": ": activate to sort column descending"
                },
                "emptyTable": "没有数据",
                "info": "显示   _START_ -  _END_  共  _TOTAL_ 行",
                "infoEmpty": "没有找对应的记录",
                "infoFiltered": "(filtered1 from _MAX_ total entries)",
                "lengthMenu": "每页显示   _MENU_ 行",
                "search": "查询:",
                "zeroRecords": "没有相应的行"
            },
            'bFilter': false,
            //开启服务端分页
            "serverSide": true,
            //进度条
            "processing": true,
            "ajax": {
                'url':'sphz/project/list',//请求路径
                'type':'POST',
                /**
                 * DataTable 默认关键字 是data
                 * 我这里后台返回是price
                 * 但是由于如果后台只返回一条记录是不是数组[]
                 * 所以需要重新判断一下，所以可以用function
                 */     
                'dataSrc':function(data){
                	return data.projList
                },
                error:function(){
                }
            },            
            "columns": [
            	 { 
                 	 "orderable": false,
                     "data" : "PROJ_KEY",//获取列数据，跟服务器返回字段一致
                     "targets": 2,
                     "render" : function(data, type, row) {//返回自定义的样式 
                       return "<label><input type='checkbox' class='ace' /><span class='lbl'></span></label>"
                     } 
                   }, 
                   {  
             	  	"orderable": true,
                     "data" : "PROJ_KEY"
                   }, 
                   { 
                     "data" : "PROJ_NO"
                   }, 
                   { 
                     "data" : "PROJ_NAME"
                   }, 
                   { 
                     "data" : "PROJ_JSDW"
                   }, 
                   { 
                     "data" : "PROJ_POSITION"
                   }, 
                   { 
                     "data" : "PROJ_SSQH"
                   },
                   {
 	                "width": 200,
 	                "orderable": false,
 	                "data": null,
 	                "targets": 3,
 	                "render": function(data, type, row) {
 	                        var html = "<span class='badge table-badge bg-blue table-info' mydata='noSubmit' id='" + row.PROJ_KEY + "'>&nbsp;<i class='fa fa-info'></i>&nbsp;详情</span>"
 	                    return html;
 	                }
 		          }
            ],

            "order": [
                [1, 'asc']
            ],
            "lengthMenu": [
            	[ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100]
            ],
            // set the initial value
            "pageLength": 10,
            "fnDrawCallback": function(table) {
				  appendSkipPage();
            }
             
        }); 
  
    //全选 
    $('table th input:checkbox').on( 
        'click', 
        function() { 
          var that = this; 
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
	
	
	   // 详情子表格
    $(".table tbody").on("click", ".table-info", function() {
        var projKey = $(this).attr("id");
        console.log(parentTable);
        console.log($("#table_project").dataTable());
        var row = $("#table_project").dataTable().row($(this).closest("tr"));
        if(row.child() == undefined){
            $(this).html("-");
            var $table = $("<table><thead><tr><th>InnerV1</th><th>InnerV2</th><th>InnerV3</th><th>InnerV4</th></tr></thead><tbody><tr><td>foo</td><td>bar</td><td>biz</td><td>baz</td></tr><tr><td>baz</td><td>biz</td><td>bar</td><td>foo</td></tr><tr><td>bar</td><td>foo</td><td>baz</td><td>biz</td></tr></tbody></table>");
            $table.attr("id", "childTable_" + row.index());
            var childTable = $table.DataTable({
                order: [0, "desc"],
                columnDefs: [{
                    sortable: false,
                    targets: [1, 2]
                }]
            });
            
            row.child(childTable.table().container());
            console.log(row.child);
            row.child.show();
        } else {
            $(this).html("+");
            row.child(false);
        }
    });
  }); 