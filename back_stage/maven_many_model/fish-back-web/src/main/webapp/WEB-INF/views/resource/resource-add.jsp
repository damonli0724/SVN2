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
		<input type="hidden" name="resParentId" id="resParentId" value=""><!-- 资源父Id -->
		<input type="hidden" name="resType" id="resType" value="0"><!--添加的资源类型  -->
		
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span> 资源名称 ：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="资源名称" id="resName" name="resName" >
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-3">资源类型：</label>
			<div class="formControls col-5"> 
				<span class="select-box" style="width:307px;">
					<input type="radio" name="type" value="0" checked="checked"/> 主菜单 &nbsp;&nbsp;<input type="radio" name="type" value="1" /> 子菜单&nbsp;&nbsp;<input type="radio" name="type" value="2"/> 按钮
				</span>
			</div>
		</div>
		
		<div class="row cl" style="display:none" id="mainMenuDiv">
			<label class="form-label col-3">所属主菜单：</label>
			<div class="formControls col-5"> 
					<select class="select" name="mainMenu" id="mainMenu"></select>
			</div>
		</div>
		
		<div class="row cl" style="display:none" id="childMenuDiv">
			<label class="form-label col-3">所属子菜单：</label>
			<div class="formControls col-5"> 
					<select class="select" name="childMenu" id="childMenu">
						<option value=''>--请选择--</option>
					</select>
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span> 资源key ：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="资源key" id="resKey" name="resKey" >
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span> Url ：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="Url" id="resUrl" name="resUrl" >
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span> 排序</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" placeholder="排序"  id="resLevel" name="resLevel" >
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-3">描述：</label>
			<div class="formControls col-5">
				<textarea name="resDes" id="resDes" cols="" rows="" class="textarea"  placeholder="说点什么...100个字符以内" onKeyUp="textarealength(this,100)"></textarea>
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
<script type="text/javascript" src="${scriptBasePath}/resource/resource-add.js?r=<%=new Date()%>"></script> 
</body>
</html>