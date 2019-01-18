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
	//初始化图层下拉框
	var html="";
	for(var i=0;i<mapApp.dynamicLayersUrls.length;i++){
		html+="<option value='"+mapApp.dynamicLayersUrls[i].layerUrl+"'>"+mapApp.dynamicLayersUrls[i].layer.layerInfos[0].name+"</option>";
	}
	$("#select").append(html);
	$("#file").fileinput({
        language:'zh',
        showPreview:false,
        showUpload:false
     });
     $("#select").select2({
        language:'zh',
        placeholder:'请选择图层'
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
   					mapApp.getFeaturesByGeometry(data.geometry,$("#select").val());
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
			layerUrl : {
				required : true
			},
			file : {
				required : true
			}
		},
		messages : {
			layerUrl : {
				required : icon + "请选择图层"
			},
			file : {
				required : icon + "请选择CAD文件"
			}
		}
	})
}