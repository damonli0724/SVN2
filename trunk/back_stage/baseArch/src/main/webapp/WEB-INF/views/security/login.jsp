<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>spring security Login</title>
</head>
<body>
  <div class="container">
    <h1>请登录 = = = !</h1>
    <p>
      Hello <b><c:out value="${pageContext.request.remoteUser}"/></b>
    </p>
    <form class="form-inline" action="j_spring_security_check" method="post">
    <c:url var="logoutUrl" value="/logout"/>
    	账号:<input type="text" name="j_username"/></br>
    	密码:<input type="password" name="j_password"/></br>
      <%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> --%>
        <input id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox" value="true"/> :记住我<br/>
    	<input type="submit" value="登陆">
    	<input name="reset" type="reset" value="重置" />  
    </form>
  </div>
    <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />. </font>  
      
     
        
</body>