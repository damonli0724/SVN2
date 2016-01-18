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
		<input type="hidden"  name="userId" value="${user.userId}"/>
	
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>账号：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  readonly="readonly" placeholder="账号" id="name" name="name" value="${user.name}" >
			</div>
		</div>
		
		<div class="row cl"  style="display:none;">
			<label class="form-label col-3"><span class="c-red">*</span>初始密码：</label>
			<div class="formControls col-5">
				<input type="password" placeholder="密码" id="originalPassword" name="originalPassword" value="${user.password}" class="input-text" >
			</div>
		</div>
		
		
		<div class="row cl"  style="display:none;">
			<label class="form-label col-3"><span class="c-red">*</span>确认密码：</label>
			<div class="formControls col-5">
				<input type="password" placeholder="确认新密码" id="confirmPassword" name="confirmPassword"  value="${user.password}" class="input-text" >
			</div>
		</div>
		
		<div class="row cl" >
			<label class="form-label col-3"><span class="c-red">*</span>真实姓名：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="${user.username}"  placeholder="" id="userName" name="userName" >
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>性别：</label>
			<div class="formControls col-5 skin-minimal">
				<div class="radio-box">
					<input type="radio" id="sex-1" value="1" name="sex"
					<c:choose>  
   					 	<c:when test="${user.sex=='1'}">  
   							 checked  
    					</c:when>  
  				 	</c:choose>   
					/>
					<label for="sex-1">男</label>
				</div>
				<div class="radio-box">
					<input type="radio" id="sex-2" value="0" name="sex"
						<c:choose>  
   					 		<c:when test="${user.sex=='0'}">  
   								 checked  
    						</c:when>  
  				 		</c:choose>   
					/>
					<label for="sex-2">女</label>
				</div>
			</div>
			 
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>手机：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="${user.mobile}" placeholder="" id="user-tel" name="mobile" >
			</div>
			 
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>邮箱：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" placeholder="@" value="${user.email}"   name="email" id="email">
			</div>
			 
		</div>
		<div class="row cl">
			<label class="form-label col-3">角色：</label>
			<div class="formControls col-5"> <span class="select-box" style="width:150px;">
				<select class="select" name="roleId" size="1">
					<c:forEach var="roles" items="${roles}">
						<option value="${roles.roleId}"  <c:if test="${roles.roleId eq user.roleId}">selected="selected"</c:if> >${roles.roleName}</option>
					</c:forEach>
				</select>
				</span> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3">备注：</label>
			<div class="formControls col-5">
				<textarea name="description" id="description" cols="" rows=""  class="textarea"  placeholder="说点什么...100个字符以内" onKeyUp="textarealength(this,100)">${user.description} 
				</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">${fn:length(user.description)}</em>/100</p>
			</div>
		</div>
		<div class="row cl">
			<div class="col-9 col-offset-3">
				<input class="btn btn-primary radius" id="submitBtn" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
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

var context= $('#globe_context_id').val();
var form =$("#addForm");
var addUrl = context+"/background/admin/add/page/data"; //添加Url

$(function(){
	$('#submitBtn').bind("click",addAdmin);
});

function addAdmin(){
	  if (validateAddForm()) {
		$.ajax({
	  		url: addUrl,
	  		type:"POST",
	  		data: form.serialize(),
	  		async : false,
	  		success:function(res){
	  			if(res.status==1){
	  				layer.msg('修改成功!',{icon:1,time:1000});
	  				closeWindow();  //跳转到列表页面
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
				isAccount:true
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

function closeWindow(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.location.reload(); //父窗口刷新
	parent.layer.close(index);//关闭弹窗
}

</script>
</body>
</html>