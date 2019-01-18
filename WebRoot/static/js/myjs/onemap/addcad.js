/**
 * 一张图--统计分析页面的js
 */
$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		upload();
	}
});
var mapApp=window.parent.document.getElementById("mainFrame").contentWindow.mapApp;
$(function(){
	$("#file").fileinput({
        language:'zh',
        showPreview:false,
        showUpload:false
     });  
});
function upload(){
	//提交
   	 var formData=new FormData($('#signupForm')[0]);
   		$.ajax({
   			type : "POST",
   			url : "map/convert.do",
   			data : formData,
   			async : true,
   	        processData:false,
   	        contentType:false,
   			error : function(request) {
   				parent.layer.alert("网络超时");
   			},
   			success : function(data) {
   				if (data.geometry) {
   					parent.layer.msg("操作成功");
   					var index = parent.layer.getFrameIndex(window.name);
   					parent.layer.close(index);
   					mapApp.showCadLayer(data.geometry,data.fileName);
   				} else {
   					parent.layer.alert("数据转换出错");
   				}
   			}
   		});
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			file : {
				required : true
			}
		},
		messages : {
			file : {
				required : icon + "请选择CAD文件"
			}
		}
	})
}