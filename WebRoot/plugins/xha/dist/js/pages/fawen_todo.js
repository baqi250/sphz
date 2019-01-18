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
                "bSort" : true, //是否支持排序功能 
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
              "sAjaxSource" : "sphz/fawen/listTodo",//通过ajax实现分页的url路径。  
              "aoColumns" : [//初始化要显示的列 
                  {  
                	  "width":200,
                    "mDataProp" : "SXMCJC"
                    
                  }, 
                  { 
                	  "width":200,
                    "mDataProp" : "APPLICANT"
                  },
                  { 
                	 
                    "mDataProp" : "PROJECT_NAME"
                  },
                  { 
                	  "width":300,
                    "mDataProp" : "LOCATION"
                  }, 
                  { 
                	  "width":80,
                      "mDataProp" : "SQRQ"
                   }, 
                  { 
                	  "width":100,
                      "mDataProp" : "ORG_NAME"
                   }, 
                  {
                	"width":60,
                	"orderable":false,
	                "data": null,
	                "render": function(data, type, row) {
                        var html = "<span class='badge table-badge bg-green table-edit' mydata='"+row.ITEM_NAME+"' id='" + row.WHWYM + "'>&nbsp;<i class='fa fa-pencil'></i>&nbsp;补充</span>"
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
	
	//使用col插件实现表格头宽度拖拽
	$("#table_project").colResizable();
	
	function showEdit(ITEM_NAME,WHWYM){
		//处理ITEM_NAME
		var FWLXID="";
		var DL="";
		//判断逻辑：1、根据事项类型判断属于哪一类，如果FWLXID为""，则弹出对话框让用户选择是那种类型，将选择的类型和DL进行合并充当FWLXID，如果不为空，则直接到表单页
		if(ITEM_NAME.indexOf('建设工程规划许可')>-1||ITEM_NAME.indexOf('修建性详细规划')>-1){
			if(ITEM_NAME.indexOf('建筑')>-1){
				FWLXID="建设许可（建筑工程）";
			}else if(ITEM_NAME.indexOf('城市雕塑')>-1){
				FWLXID="其它";
			}else{
				DL="建设许可";
			}
		}else if(ITEM_NAME.indexOf('建设用地规划许可')>-1||ITEM_NAME.indexOf('临时用地')>-1){
			FWLXID="用地许可";
		}else if(ITEM_NAME.indexOf('建设项目规划条件')>-1||ITEM_NAME.indexOf('建设项目选址意见书')>-1){
			DL="选址、条件";
		}else if(ITEM_NAME.indexOf('竣工规划')>-1){
			DL="竣工";
		}else{
			FWLXID="其它";
		}
		
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
		         //btn:['确定','取消'],
		         area: [width+'px', height+'px'],
		         content: 'sphz/fawen/goEdit.do?WHWYM='+WHWYM+"&FWLXID="+FWLXID,
		         success:function(layero,index){
		               	layerIndex=index;
		               	layerInitWidth  = $("#layui-layer"+layerIndex).width();
		               	layerInitHeight = $("#layui-layer"+layerIndex).height();
	                },
		     });
		}else{
			//弹窗选择是建筑、道桥、管线
			var indext1 = layer.open({
	            type: 1,
	            title: '选择项目类型',
	            shadeClose: true,
	            shade: false,
	            maxmin: false, //开启最大化最小化按钮
	            btn:['确定','取消'],
	            area: ['350px', '350px'],
	            content:$("#typeDiv") ,
	            yes:function(index,layero){
	                var TYPE=$(".radioVal").val();
	                FWLXID= DL+"（"+TYPE+"）";
	                var indext = layer.open({
	       	         type: 2,
	       	         title: '编辑',
	       	         shadeClose: true,
	       	         shade: false,
	       	         zIndex:999,
	       	         maxmin: true, //开启最大化最小化按钮
	       	         //btn:['确定','取消'],
	       	         area: [width+'px', height+'px'],
	       	         content: 'sphz/fawen/goEdit.do?WHWYM='+WHWYM+"&FWLXID="+FWLXID,
	       	         success:function(layero,index){
		               	layerIndex=index;
		               	layerInitWidth  = $("#layui-layer"+layerIndex).width();
		               	layerInitHeight = $("#layui-layer"+layerIndex).height();
	                },
	       	     });
	                layer.close(indext1);
	            },
	            cancel:function(){
	            	layer.close(indext1);
	            }
	        });
			//layer.close(indext);
		}
	}
	
    $("#table_project tbody").delegate("tr","dblclick",function (e) { //会为符合条件的现有标签和未来标签都绑定事件,防止分页无效
    	var aData = parentTable.fnGetData(this);
		var WHWYM = aData.WHWYM;
		var ITEM_NAME=aData.ITEM_NAME;
		showEdit(ITEM_NAME,WHWYM);
    	
    });
	
	
	$(".table tbody").on("click", ".table-edit", function() {
		var WHWYM = $(this).attr("id");
		var ITEM_NAME=$(this).attr("mydata");
		showEdit(ITEM_NAME,WHWYM);
	}); 
	

})
 


 