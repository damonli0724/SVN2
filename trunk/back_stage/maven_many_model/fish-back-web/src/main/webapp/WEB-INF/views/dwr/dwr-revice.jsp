<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<%@ include file="/WEB-INF/views/base/base.jsp"%>
<html>  
  <head>  
    <title>DWR  DEMO</title>  
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">      
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="description" content="This is my page">  
  </head>  
  <script type="text/javascript" src="${jsBasePath}/jquery.min.js"></script> 
  <script type='text/javascript' src='${contextPath}/dwr/engine.js'></script>  
  <script type='text/javascript' src='${contextPath}/dwr/util.js'></script>  
  <script type="text/javascript" src="${contextPath}/dwr/interface/messageLoad.js"></script>  
  
  <script type="text/javascript">  
         function onPageLoad(){  
            var userId = ${user.userId};
            messageLoad.onPageLoad(userId);  
          }  
         //推送信息  
          function showMessage(autoMessage){ 
			alert(autoMessage);             
        	}   
  </script>  
  <body onload="onPageLoad();dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);;">   
    This is my DWR DEOM page. <hr>  
    <br>  
    <div id="DemoDiv">----</div>  
  </body>  
</html>  