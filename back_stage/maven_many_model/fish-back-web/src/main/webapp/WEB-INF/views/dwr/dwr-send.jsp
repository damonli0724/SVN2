<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<%@ include file="/WEB-INF/views/base/base.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <base href="<%=basePath%>">  
    <title>My JSP 'MyJsp.jsp' starting page</title>  
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">      
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="description" content="This is my page">  
     <script type="text/javascript" src="${jsBasePath}/jquery.min.js"></script> 
    <script type='text/javascript' src='<%=basePath%>dwr/engine.js'></script>  
    <script type='text/javascript' src='<%=basePath%>dwr/util.js'></script>  
    <script type='text/javascript' src='<%=basePath%>dwr/interface/TestPush.js'></script>  
   
    <script type="text/javascript">  
    //推送信息 
    
   	function showMessage(message){
    	$("#contentId").append("<li>"+message+"</li>");
    }
    function test() {  
        var msg = document.getElementById("msgId").value;  
        TestPush.sendMessageAuto(msg,$("#sendId").val());  
    }  
    </script>  
  </head>  
    
  <body>  
    <input type="hidden" name="msgId" id="msgId" /> <br />  
    	<ul id="contentId">
		  <li>Coffee</li>
		</ul>
     
      发送的内容:
   <input type="text"  id ="sendId">  <input type="button"  value="Send" onclick="test()"  />
  </body>  
</html>  