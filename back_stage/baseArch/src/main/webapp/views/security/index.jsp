<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<html>
<body>
<h2>Hello World!</h2></br>
<h2>当前用户名为:  <sec:authentication property="name"/>| <a href="loginout">安全退出</a> </h2>





<sec:authorize ifAllGranted="ROLE_ADMIN">
admin 页面,其他权限是看不到的哦
</sec:authorize>

<%-- 
<sec:authorize access="isRememberMe()">
是否 记住我页面，你没点 记住 是看不到的 啊啊啊
</sec:authorize>
 --%>
</body>
</html>
