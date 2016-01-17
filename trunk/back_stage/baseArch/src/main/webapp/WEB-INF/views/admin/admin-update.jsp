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
				<input type="text" class="input-text"  placeholder="账号" id="name" name="name" value="${user.name}" >
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
				<textarea name="description" cols="" rows=""  class="textarea"  placeholder="说点什么...100个字符以内" onKeyUp="textarealength(this,100)">${user.description} 
				</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">${fn:length(user.description)}</em>/100</p>
			</div>
		</div>
		<div class="row cl">
			<div class="col-9 col-offset-3">
				<input class="btn btn-primary radius" id="submitBtn" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</div>
<script type="text/javascript" src="${jsBasePath}/jquery.min.js"></script> 
<script type="text/javascript" src="${jsBasePath}/validation-proxy.js"></script> 
<script type="text/javascript" src="${libBasePath}/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="${jsBasePath}/H-ui.js"></script> 


<script type="text/javascript">
$(function(){
	
	$('#submitBtn').bind("click",function(){
		$("#form-admin-add").submit();		
	})
	
	$('#submitBtn').bind("click",addAdmin);
/* 	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form-admin-add").Validform({
		tiptype:2,
		callback:function(form){
			alert(21312321)
			form[0].submit();
			var index = parent.layer.getFrameIndex(window.name);
			parent.$('.btn-refresh').click();
			parent.layer.close(index);
		}
	}); */
});

function addAdmin(){
	if (validateAddForm()) {
		$("#form-admin-add").submit();		
	}
}

function validateAddForm() {
	$("#addForm").validate({
		rules : {
			name : {
				required:true,
			},
			username : {
				required : true,
			},
			moblie : {
				required : true,
			}
		},
		messages : {
			name : {
				required:"账户不能为空",
			},
			username : {
				required : "真实姓名不能为空",
			},
			moblie : {
				required : "手机号码不能为空",
			}
		},
		errorPlacement : function(error, element) {
			var p = element.parent();
			
		/* 	var div ="<div class='col-4'><span class='Validform_checktip Validform_wrong'>"+error.html()+"</span></div>"; */
			
			error.appendTo(p); 
		},
		validClass : "success",
		onkeyup : false
	});
	return $("#addForm").valid();
}

</script>
</body>
</html>