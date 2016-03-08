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
	<ul class="tb_ul">
			  <li>
			   	<div class="tbul02_left">
				  	<div class="tab_txt01">
				  	   <span class="td_01"><label class="fc_red">*</label>上传文件：</span>
					   <span class="td_02"><input id="excelFile" type="file" name="excelFile" /></span>
				  	</div>
			  	</div>
			  </li>
			</ul>
		</div>
		<div class="btnlist">
			<a href="javascript:void(0);" id="submitButton" class="btn_131 margin_let">导入产品</a> 
			<a href="${contextPath}/downLoad?fileName=家具.xlsx&importType=${importType}" id='resetBtn' class="btn_131 margin_let10">模板下载</a>
			<a href="javascript:window.close()" class="btn_131 margin_let10">关闭</a>
		</div>
</div>

<script type="text/javascript" src="${jsBasePath}/jquery.min.js"></script> 
<script type="text/javascript" src="${jsBasePath}/ajaxfileupload.js"></script> 
<script type="text/javascript" src="${jsBasePath}/validation-proxy.js"></script> 
<script type="text/javascript" src="${libBasePath}/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="${jsBasePath}/H-ui.js"></script> 
<script type="text/javascript" src="${scriptBasePath}/base/validate.expand.js"></script> 
<script type="text/javascript" src="${scriptBasePath}/excel/excel-add.js?r=<%=new Date()%>"></script> 
</body>
</html>