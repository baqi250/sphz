$(function() {
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
                "bLengthChange" : false, //每页显示的记录数
                "lengthMenu": [15],
                "bFilter" : false, //搜索栏 
                //"bSort" : true, //是否支持排序功能 
                "ordering": true, //是否支持排序功能 
                "bInfo" : true, //显示表格信息
                "scrollCollapse": true,
                "bLengthChange": false,//每页显示多少条数据
                "bAutoWidth" : true, //自适应宽度 
                "responsive": false,
                "bJQueryUI" : false,//是否开启主题 
                "bDestroy" : true, 
                "bProcessing" : true, //开启读取服务器数据时显示正在加载中……特别是大数据量的时候，开启此功能比较好 
                "bServerSide" : true,//服务器处理分页，默认是false，需要服务器处理，必须true 
              "sAjaxDataProp" : "fawenList",//是服务器分页的标志，必须有  
              "sAjaxSource" : "sphz/fawen/listDone",//通过ajax实现分页的url路径。  
              "aoColumns" : [//初始化要显示的列 
                  {  
                	  "width":200,
                    "mDataProp" : "FWWH"
                  }, 
                  { 
                	  "width":80,
                    "mDataProp" : "FZRQ"
                  },
                  { 
                	"width":80,
                    "mDataProp" : "FWLX"
                  },
                  { 
                    "mDataProp" : "PROJECT_NAME"
                  },
                  { 
                	  "width":320,
                    "mDataProp" : "LOCATION"
                  }, 
                  { 
                	  "width":60,
                      "mDataProp" : "JBR"
                  },
                  { 
                	  "width":80,
                      "mDataProp" : "ORG_NAME"
                   },
                  {
                	"width":120,
                	"orderable":false,
	                "data": null,
	                "render": function(data, type, row) {
	                    // 设定按钮
                        /*var html = "<span class='badge table-badge bg-blue table-info' mydata='"+row.FWLXID+"' id='" + row.WHWYM + "'>&nbsp;<i class='fa fa-info'></i>&nbsp;查看</span>"
                        html += "<span class='badge table-badge bg-red table-edit' mydata='"+row.FWLXID+"' id='" + row.WHWYM + "'>&nbsp;<i class='fa fa-pencil'></i>&nbsp;编辑</span>"*/
	                	var html = "<span class='badge table-badge bg-red table-edit' mydata='"+row.FWLXID+"' id='" + row.WHWYM + "'>&nbsp;<i class='fa fa-pencil'></i>&nbsp;编辑</span>"
                        html += "<span class='badge table-badge bg-green table-files' id='" + row.BSNUM + "'>&nbsp;<i class='fa fa-file'></i>&nbsp;附件</span>"
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
	
	function showEdit(FWLXID,WHWYM){
		var width=$(window).width();
	    var height=$(window).height();
		if(FWLXID!=""){
			var indext = layer.open({
		         type: 2,
		         title: '编辑',
		         shadeClose: true,
		         shade: false,
		         zIndex:999,
		         maxmin: true, //开启最大化最小化按钮
		         area: [width+'px', height+'px'],
		         content: 'sphz/fawen/goEdit.do?WHWYM='+WHWYM+"&FWLXID="+FWLXID,
		         success:function(layero,index){
	               	layerIndex=index;
	               	layerInitWidth  = $("#layui-layer"+layerIndex).width();
	               	layerInitHeight = $("#layui-layer"+layerIndex).height();
                },
		     });
		}
	}
	
    $("#table_project tbody").delegate("tr","dblclick",function (e) { //会为符合条件的现有标签和未来标签都绑定事件,防止分页无效
    	var aData = parentTable.fnGetData(this);
		var WHWYM = aData.WHWYM;
		var FWLXID=aData.FWLXID;
		showEdit(FWLXID,WHWYM);
    	
    });

	$(".table tbody").on("click", ".table-edit", function() {
		var WHWYM = $(this).attr("id");
		var FWLXID=$(this).attr("mydata");
		showEdit(FWLXID,WHWYM);
		
	}); 
	$(".table tbody").on("click", ".table-info", function() {
		var FAWEN_ID = $(this).attr("id");
		var FWLXID=$(this).attr("mydata");
		var width=$(window).width();
	    var height=$(window).height();
	 	var indext = layer.open({
	         type: 2,
	         title: '查看',
	         shadeClose: true,
	         shade: false,
	         maxmin: true, //开启最大化最小化按钮
	         //btn:['确定','取消'],
	         area: [width+'px', height+'px'],
	         content: 'sphz/fawen/goShow.do?FAWEN_ID='+FAWEN_ID+"&FWLXID="+FWLXID
	     });
	}); 
	
	$(".table tbody").on("click", ".table-files", function() {
		var businessId = $(this).attr("id");
		var width=$(window).width();
	    var height=$(window).height();
	 	var indext = layer.open({
	         type: 2,
	         title: '附件列表',
	         shadeClose: true,
	         shade: false,
	         maxmin: true, //开启最大化最小化按钮
	         //btn:['确定','取消'],
	         area: [width+'px', height+'px'],
	         content: 'sphz/fawen/goFiles.do?businessId='+businessId,
	         success:function(layero,index){
	               	layerIndex=index;
	               	layerInitWidth  = $("#layui-layer"+layerIndex).width();
	               	layerInitHeight = $("#layui-layer"+layerIndex).height();
             },
	     });
	}); 
});


 