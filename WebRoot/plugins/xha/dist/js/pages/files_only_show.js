$(function() {
	/*s*/
    // 选择框
    $('.select2').select2({
        language: "zh-CN"
    });
}) 

jQuery(function($) { 
    //初始化table 
    var parentTable = $('#table_project') 
        .dataTable( 
            { 
              "bPaginate" : true,//分页工具条显示 
              //"sPaginationType" : "full_numbers",//分页工具条样式 
              "bStateSave" : false, //是否打开客户端状态记录功能,此功能在ajax刷新纪录的时候不会将个性化设定回复为初始化状态  
              "bScrollCollapse" : true, //当显示的数据不足以支撑表格的默认的高度 
              //"bLengthChange" : false, //每页显示的记录数
              //"lengthMenu": [ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100],
              "lengthMenu": [15],
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
              "sAjaxDataProp" : "fileList",//是服务器分页的标志，必须有  
              "sAjaxSource" : "sphz/files/list?businessId="+businessId,//通过ajax实现分页的url路径。  
              "aoColumns" : [//初始化要显示的列 
                  {  
                    "mDataProp" : "file_name"
                    
                  },
                  {
                	"width":80,
	                "data": null,
	                "render": function(data, type, row) {
	                    // 设定按钮
                        var html = "<span class='badge table-badge bg-blue table-info' mydata='"+row.path+"' id='" + row.id + "'>&nbsp;<i class='fa fa-info'></i>&nbsp;查看</span>"
                       // html += "<span class='badge table-badge bg-red table-delete' mydata='"+row.path+"' id='" + row.id + "'>&nbsp;<i class='fa fa-pencil'></i>&nbsp;删除</span>"
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
});


$(".table tbody").on("click", ".table-info", function() {
	var path=$(this).attr("mydata");
	window.open("sphz/files/getFileByPath?url="+path);
}); 


 


 