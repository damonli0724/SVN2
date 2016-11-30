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
    $.ajaxFileUpload
    (
        {
            url: addUrl, //用于文件上传的服务器端请求地址
            type: 'post',
            data: { id: '123', name: 'lunis' }, //此参数非常严谨，写错一个引号都不行
            secureuri: false, //一般设置为false
            fileElementId: 'file1', //文件上传空间的id属性  <input type="file" id="file" name="file" />
            dataType: 'json', //返回值类型 一般设置为json
            success: function (data, status)  //服务器成功响应处理函数
            {
                alert(data);
                $("#img1").attr("src", data.imgPath1);
                alert("你请求的Id是" + data.Id + "     " + "你请求的名字是:" + data.name);
                if (typeof (data.error) != 'undefined') {
                    if (data.error != '') {
                        alert(data.error);
                    } else {
                        alert(data.msg);
                    }
                }
            },
            error: function (data, status, e)//服务器响应失败处理函数
            {
                alert(e);
            }
        }
    )
    return false;
}




</script> 



</body>
</html>