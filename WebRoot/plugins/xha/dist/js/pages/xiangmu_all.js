$(function() {
    // 选择框
    $('.select2').select2({
        language: "zh-CN"
    });
}) 
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
              "bSort" : false, //是否支持排序功能 
              "bInfo" : true, //显示表格信息
              "scrollCollapse": true,
              "bLengthChange": false,//每页显示多少条数据
              "bAutoWidth" : true, //自适应宽度 
              "responsive": false,
              "bJQueryUI" : false,//是否开启主题 
              "bDestroy" : true, 
              "bProcessing" : true, //开启读取服务器数据时显示正在加载中……特别是大数据量的时候，开启此功能比较好 
              "bServerSide" : true,//服务器处理分页，默认是false，需要服务器处理，必须true 
              "sAjaxDataProp" : "xiangmuList",//是服务器分页的标志，必须有  
              "sAjaxSource" : "sphz/project/list",//通过ajax实现分页的url路径。  
              "aoColumns" : [//初始化要显示的列 
                  { 
                    "mDataProp" : "PROJECT_NAME"
                  },
                  	
                  { 
                    "mDataProp" : "LOCATION"
                  },
                  { 
                    "mDataProp" : "APPLICANT"
                  },
                  { 
                	 "mDataProp" : "ORG_NAME",
                	 "width":80
                  },
                  { 
                    "mDataProp" : "SPJD",
                    "width":80,
                    "render":function(data,type,full,meta){
		               	 if(full.SPJD=='条'){
		               		 return '条件/选址';
		               	 }else if(full.SPJD=='地'){
		               		 return '用地许可';
		               	 }else if(full.SPJD=='建'){
		               		 return '建设许可';
		               	 }else if(full.SPJD=='核'){
		               		 return '竣工核实';
		               	 }else{
		               		 return '';
		               	 }
                     }
                  },
                  {
                	"width":100,
                	"class":'text-center-td',
	                "data": null,
	                "render": function(data, type, row) {
                        var html = "<span class='badge table-badge bg-green table-detail' mydata='"+row.PROJECT_NAME+"' id='" + row.XMWYM + "'>&nbsp;<i class='fa fa-info-circle'></i>&nbsp;详情</span>";
	                    return html;
	                }
		          }
              ], 
              
			  fnDrawCallback: function(table) {
				  $(".text-center-td").css("text-align","center");
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
	

	function showDetail(XMWYM,PROJECT_NAME){
		var width=$(window).width();
	    var height=$(window).height();
	 	var indext = layer.open({
	         type: 2,
	         title: " ",
	         shadeClose: true,
	         shade: false,
	         maxmin: true, //开启最大化最小化按钮
	         //btn:['确定','取消'],
	         area: [width+'px', height+'px'],
	         content: 'sphz/project/goProjectDetail.do?XMWYM='+XMWYM+'&XMMC='+PROJECT_NAME,
	         success:function(layero,index){
	               	layerIndex=index;
	               	layerInitWidth  = $("#layui-layer"+layerIndex).width();
	               	layerInitHeight = $("#layui-layer"+layerIndex).height();
             },
	     });
	 	//layer.full(indext);
	}

	
		
	$(".table tbody").on("click", ".table-detail", function() {
		//console.log(this);
		var XMWYM = $(this).attr("id");
		var PROJECT_NAME=$(this).attr("mydata");
		showDetail(XMWYM,PROJECT_NAME);
	});
	
	 $("#table_project tbody").delegate("tr","dblclick",function (e) { //会为符合条件的现有标签和未来标签都绑定事件,防止分页无效
	    	var aData = parentTable.fnGetData(this);
			var XMWYM = aData.XMWYM;
			var PROJECT_NAME=aData.PROJECT_NAME;
			showDetail(XMWYM,PROJECT_NAME);
	    });
	 





 


 