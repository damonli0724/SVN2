<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@ include file="/WEB-INF/views/base/base.jsp"%>
<html>
<head>
<LINK rel="Bookmark" href="${imagesBasePath}/favicon.ico" >
<LINK rel="Shortcut Icon" href="${imagesBasePath}/favicon.ico" />
<link href="${cssBasePath}/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${libBasePath}/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<link href="${cssBasePath}/style.css" rel="stylesheet" type="text/css" />
<link href="${cssBasePath}/jqpagination.css"  rel="stylesheet" type="text/css"/>
<title>管理员列表</title>


</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span class="c-gray en">&gt;</span> 管理员列表 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
	
	<div class="cl pd-5 bg-1 bk-gray mt-20"> 
	
	<span class="l">
	<a href="javascript:;" onclick="admin_role_add('添加角色','${contextPath}/background/role/add/page','800','600')" class="btn btn-primary radius">
	<i class="Hui-iconfont">&#xe600;</i> 添加角色
	</a>
	</span>
	<span class="r">共有数据：<strong>${count}</strong> 条</span>  
	
	<table class="table table-border table-bordered table-hover table-bg">
		<thead>
			<tr>
				<th scope="col" colspan="6">角色管理</th>
			</tr>
			<tr class="text-c">
				<th width="200">ID</th>
				<th>角色名称</th>
				<th width="300">描述</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${roles}" var="roles">
			
			<tr class="text-c">
				<td>${roles.roleId}</td>
				<td>${roles.roleName}</td>
				<td>${roles.roleDesc}</td>
				<td class="f-14"><a title="编辑" roleId="${roles.roleId}" href="javascript:;" onclick="admin_role_edit('角色编辑','admin-role-add.html','1')" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> <a title="删除" href="javascript:;" onclick="admin_role_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
			
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<script type="text/javascript" src="${jsBasePath}/jquery.min.js"></script> 
<script type="text/javascript" src="${scriptBasePath}/role/role-query.js?r=<%=new Date()%>"></script> 
<script type="text/javascript" src="${libBasePath}/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="${jsBasePath}/H-ui.js"></script> 
<script type="text/javascript" src="${jsBasePath}/H-ui.admin.js"></script> 

</body>
</html>