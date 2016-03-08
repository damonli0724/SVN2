
var context= $('#globe_context_id').val();
var form =$("#addForm");
var addUrl = context+"/excel/add/page/data"; //添加Url

$(function(){
	$("#submitButton").bind("click", uploadExcel);
});



//上传EXCEL文件
function uploadExcel() {
     $.ajaxFileUpload({
         url : addUrl,
         fileElementId:'excelFile',
         type : 'POST',
         dataType : 'text',
         data:{
      	    excelFile : $("#excelFile").val(),
  	   },
         success : function(data,status) {
        	 layer.msg(data.message,{icon: 5,time:1000});
         },
         error : function(data,status,e) {
        	 layer.msg("导入失败，请确认上传的文件内容合法",{icon: 5,time:1000});
         }
     });
}