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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span class="c-gray en">&gt;</span> 管理员列表 <a class="btn btn-success radius r mr-20" id="refreshBtn" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
	<form id="searchForm" > 
	<div class="text-c"> 日期范围： 
		<input type="text" name="beginDate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})" id="datemin" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" name="endDate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d'})" id="datemax" class="input-text Wdate" style="width:120px;">
		<input type="text" class="input-text" style="width:250px" placeholder="输入管理员名称" id="" name="loginName">
		<button type="button" class="btn btn-success" id="searchBtn" name=""><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
	</div>
	</form>
	
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"> <a href="javascript:;" onclick="admin_add('添加管理员','${contextPath}/background/admin/add/page','800','500')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加管理员</a></span> 
	<span class="r">共有数据：<strong id="count"></strong> 条</span> </div>

		  <div  class="hide" id="J_DataList"></div>
		  <div class="nodata hide" id="J_NoDataMsg">
		            <div class="prompt"> 暂无数据 </div>
		  </div>
</div>
<script type="text/javascript" src="${libBasePath}/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="${libBasePath}/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="${jsBasePath}/jquery.min.js"></script> 
<script type="text/javascript" src="${libBasePath}/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="${jsBasePath}/H-ui.js"></script> 
<script type="text/javascript" src="${jsBasePath}/H-ui.admin.js"></script> 

<script type="text/javascript" src="${libBasePath}/handlebars.js"></script>
<script type="text/javascript" src="${scriptBasePath}/base/jquery.jqpagination.js"></script>

<script type="text/javascript" src="${scriptBasePath}/admin/admin-query.js?r=<%=new Date()%>"></script> 

<script id="dataListTemplate"  type="text/x-handlebars-template">
<div class="table_list" >
    <table class="table table-border table-bordered table-bg">
		<thead>
			<tr>
				<th scope="col" colspan="9">管理员列表</th>
			</tr>
			<tr class="text-c">
				<th>登录名</th>
				<th>手机</th>
				<th>邮箱</th>
				<th>性别</th>
				<th>角色</th>
				<th>加入时间</th>
				<th>是否已启用</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
        {{#result}}
       		<tr class="text-c">
				<td>{{name}}</td>
				<td>{{mobile}}</td>
				<td>{{email}}</td>
				<td>{{formatSex sex}}</td>
				<td>{{roleName}}</td>
				<td>{{formatTime dtCreate}}</td>
				<td class="td-status">{{fromatEnable enabled}}</td>
				<td class="td-manage">
					
						{{#if enabled}}
							<a style="text-decoration:none"  onClick="admin_stop(this,{{userId}})" href="javascript:;" title="禁用">
								<i class="Hui-iconfont">&#xe631;</i>
							</a> 
						{{else}} 
							<a style="text-decoration:none"  onClick="admin_start(this,{{userId}})" href="javascript:;" title="启用">
								<i class="Hui-iconfont">&#xe615;</i>
							</a> 
						{{/if}}

					<a title="编辑" href="javascript:;" onclick="admin_edit('管理员编辑','${contextPath}/background/admin/update/page?userId={{userId}}','1','800','500')" class="ml-5" style="text-decoration:none">
						<i class="Hui-iconfont">&#xe6df;</i>
					</a> 
 					
					<a title="删除" href="javascript:;" onclick="admin_del(this,{{userId}})" class="ml-5" style="text-decoration:none">
						<i class="Hui-iconfont">&#xe6e2;</i>
					</a>
				</td>
			</tr>
        {{/result}}
        </tbody>
    </table>
</div>
<div class="pagination" style="float:right">
    	<a href="#" class="first" data-action="first">&laquo;</a> 
		<a href="#" class="previous" data-action="previous">&lsaquo;</a> 
		<input id="cruPage" readonly="readonly" data-max-page="" type="text"> 
		<a href="#" class="next" data-action="next">&rsaquo;</a> 
		<a href="#" class="last" data-action="last">&raquo;</a>
</div>
</script>
</body>
</html>