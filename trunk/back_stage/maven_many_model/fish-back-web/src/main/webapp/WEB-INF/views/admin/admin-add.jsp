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
<script type="text/javascript" src="${scriptBasePath}/admin/admin-add.js?r=<%=new Date()%>"></script> 
</body>
</html>