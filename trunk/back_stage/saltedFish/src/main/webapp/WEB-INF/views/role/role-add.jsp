<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@ include file="/WEB-INF/views/base/base.jsp"%>
<html>
<head>
<link href="${cssBasePath}/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${cssBasePath}/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="${libBasePath}/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<link href="${libBasePath}/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css" rel="stylesheet" >
<title>添加管理员</title>
</head>

<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="addForm">
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>角色名称：</label>
			<div class="formControls col-10">
				<input type="text" class="input-text" value="" placeholder="" id="roleName" name="roleName" datatype="*4-16" nullmsg="用户账户不能为空">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-2">描述：</label>
			<div class="formControls col-10">
				<input type="text" class="input-text" value="" placeholder="" id="roleDesc" name="roleDesc">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-2">权限：</label>
			<div class="formControls col-10">
				<div id="tree" class="ztree"  roleId="">
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
<script type="text/javascript" src="${libBasePath}/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script> 
<script type="text/javascript" src="${libBasePath}/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="${scriptBasePath}/base/tree.dataLoad.js?r=<%=new Date()%>"></script> 
<script type="text/javascript" src="${scriptBasePath}/role/role-add.js?r=<%=new Date()%>"></script> 
</body>
</html>