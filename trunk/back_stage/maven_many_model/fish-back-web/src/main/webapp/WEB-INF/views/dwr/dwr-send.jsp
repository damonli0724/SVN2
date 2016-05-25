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
    <script type='text/javascript' src='<%=basePath%>dwr/interface/messageSend.js'></script>  
   
    <script type="text/javascript">  
    //推送信息 
    function test(){  
        messageSend.sendMessageAuto("69","fewfewfewfwef");  
    }  
    </script>  
  </head>  
    
  <body>  
    <input type="hidden" name="msgId" id="msgId" /> <br />  
     
   发给所有人:<input type="text"  id ="sendId">  <input type="button"  value="Send" onclick="test()"  /></br>
	<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)" width="100%" color=#987cb9 SIZE=10>
发给指定用户:<c:forEach items="${loginedUser}" var="user">
			用户/ip:<input  type="checkbox" value="${user.principal.userId}">	${user.principal.username}/${user.ip},			
		</c:forEach>
 
   
  </body>  
</html>  