<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@ include file="/WEB-INF/views/base/base.jsp"%>
<html>
<head>
<link href="${cssBasePath}/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${cssBasePath}/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="${libBasePath}/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<title>添加管理员</title>
</head>

<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="addForm">
		<div class="row cl">
			<label class="form-label col-2">文件：</label>
			<div class="formControls col-10">
				<input type="file" id="file1" name="file" />
			</div>
		</div>
		<div class="row cl">
			<div class="col-10 col-offset-2">
				<button type="button" class="btn btn-success radius" id="addBtn" name="addBtn"><i class="icon-ok"></i> 确定</button>
			</div>
		</div>
				
		<div class="row cl">
			<div class="col-10 col-offset-2">
				<img id="pic"  src=""  width="500px" height="500px"  style="background:blue"/> 
			</div> 
		</div>
		
		
	</form>
</div>

		
<script type="text/javascript" src="${jsBasePath}/jquery.min.js"></script> 
<script type="text/javascript" src="${jsBasePath}/ajaxfileupload.js"></script> 
<script type="text/javascript" src="${libBasePath}/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script> 
<script type="text/javascript" src="${libBasePath}/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" >
var context= $('#globe_context_id').val();
var addUrl = context+"/upload/file/test"; //上传Url

$(function(){
	
	
	$("#addBtn").bind("click",function(){
		  if ($("#file1").val().length > 0) {
              ajaxFileUpload();
          }
          else {
              alert("请选择图片");
          }
	})
	
	
});


function ajaxFileUpload() {
	$.ajaxFileUpload({
		url : addUrl,
		fileElementId : 'file1',
		type : 'POST',
		dataType : 'text',
		data : {},
		success : function(data) {
			alert(data.message);
			if (data.status == 1) {	
				console.log(data.picPath);
				$("#pic").attr("src",data.picPath);
			}
		},
		error : function(XMLHttpRequest, textStatus,errorThrown) {
			alert("服务异常，请稍后尝试");
		}
	});
    return false;
}




</script> 



</body>
</html>