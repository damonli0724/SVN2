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
	<form action="${contextPath}/background/admin/add/page/data" method="post" class="form form-horizontal" id="addForm">
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>账号：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="账号" id="name" name="name" >
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>初始密码：</label>
			<div class="formControls col-5">
				<input type="password" placeholder="密码" id="originalPassword" name="originalPassword" value="" class="input-text" >
			</div>
		</div>
		
		
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>确认密码：</label>
			<div class="formControls col-5">
				<input type="password" placeholder="确认新密码" id="confirmPassword" name="confirmPassword"  class="input-text" >
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>真实姓名：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="" placeholder="" id="userName" name="userName" >
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>性别：</label>
			<div class="formControls col-5 skin-minimal">
				<div class="radio-box">
					<input type="radio" id="sex" value="1" name="sex">
					<label for="sex-1">男</label>
				</div>
				<div class="radio-box">
					<input type="radio" id="sex" value="0" name="sex">
					<label for="sex-2">女</label>
				</div>
			</div>
			 
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>手机：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="" placeholder="" id="mobile" name="mobile" >
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>邮箱：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" placeholder="@" name="email" id="email">
			</div>
			 
		</div>
		<div class="row cl">
			<label class="form-label col-3">角色：</label>
			<div class="formControls col-5"> <span class="select-box" style="width:150px;">
				<select class="select" name="roleId" id="roleId" size="1">
					<option value="">==请选择角色==</option>
					<c:forEach var="roles" items="${roles}">
						<option value="${roles.roleId}">${roles.roleName}</option>
					</c:forEach>
					
				</select>
				</span> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3">描述：</label>
			<div class="formControls col-5">
				<textarea name="description" id="description" cols="" rows="" class="textarea"  placeholder="说点什么...100个字符以内" onKeyUp="textarealength(this,100)"></textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
			</div>
		</div>
		<div class="row cl">
			<div class="col-9 col-offset-3">
				<input class="btn btn-primary radius" id="submitBtn" type="button" onclick="javascript:void(0)" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</div>
<script type="text/javascript" src="${jsBasePath}/jquery.min.js"></script> 
<script type="text/javascript" src="${jsBasePath}/validation-proxy.js"></script> 
<script type="text/javascript" src="${libBasePath}/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="${jsBasePath}/H-ui.js"></script> 
<script type="text/javascript" src="${scriptBasePath}/base/validate.expand.js"></script> 




<script type="text/javascript">
var form =$("#addForm");
var checkUrl = $('#globe_context_id').val()+"/backgrond/admin/check/query"  //判断用户唯一性URL
var conditions= form.serialize();
var submitUrl=$('#globe_context_id').val()+"/background/admin/add/page/data"; //添加Url

$(function(){
	$('#submitBtn').bind("click",addAdmin);
	
  	jQuery.validator.addMethod("checkPwd", function(value, element) {      
  		
         return value==$("#originalPassword").val();       
    }, "初始密码和确认密码不同");  
  	
  	jQuery.validator.addMethod("checkAccountUnique", function(value, element) { 
  		var  flag = false;
  		$.ajax({
  		url: checkUrl,
  		type:"POST",
  		data:{loginName:value},
  		async : false,
  		success:function(res){
  			if(res.status==1){
  				if(res.result==0)flag=true;
  			}else{
  			  layer.msg('网络异常!',{icon: 5,time:1000});
  				 }
  			}
  		})
        return flag;       
   }, "改账号已存在");  
  	
  	
});

function addAdmin(){
	if (validateAddForm()) {
		$.ajax({
	  		url: submitUrl,
	  		type:"POST",
	  		data: conditions,
	  		async : false,
	  		success:function(res){
	  			if(res.status==1){
	  				layer.msg('添加成功!',{icon:1,time:1000});
	  				refresh();
	  			}else{
	  			  layer.msg('网络异常!',{icon: 5,time:1000});
	  				 }
	  			}
	  		})
	}
}

function validateAddForm() {
	form.validate({
		rules : {
			name : {
				required:true,
				isAccount:true,
				checkAccountUnique:true
			},
			originalPassword : {
				required : true,
				isPwd:true
			},
			confirmPassword : {
				required : true,
				isPwd:true,
				checkPwd:true
			},
			userName : {
				required:true,
			},
			sex : {
				required : true,
			},
			mobile : {
				required : true,
				isMobile:true
			},
			email:{
				required:true,
				isEmail:true
			},
			roleId:{
				required:true,
			},
			description:{
				required:true,
			}
		},
		messages : {
			name : {
				required:"账户不能为空",
			},
			originalPassword : {
				required : "初始密码不能为空",
			},
			confirmPassword : {
				required : "确认密码不能为空",
			},
			userName : {
				required:"真实姓名不能为空",
			},
			sex : {
				required :"性别不能为空",
			},
			mobile : {
				required : "手机号码不能为空",
			},
			email:{
				required:"邮箱不能为空",
			},
			roleId:{
				required:"角色不能为空",
			},
			description:{
				required:"描述不能为空",
			}
		},
		errorPlacement : function(error, element) {
			var p = element.parent().parent();
			error.addClass("Validform_checktip Validform_wrong");
			error.appendTo(p); 
		},
		validClass : "success",
		onkeyup : false
	});
	return form.valid();
}

function refresh(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
	location=location;
}
</script>
</body>
</html>