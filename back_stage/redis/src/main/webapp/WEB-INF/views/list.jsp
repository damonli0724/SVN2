<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>抢标秒杀</title>
<style type="text/css">
* {
	margin: 0;
}

html, body {
	height: 100%;
}

.wrapper {
	min-height: 100%;
	height: auto !important;
	height: 100%;
	margin: 0 auto -155px;
}

.footer, .push {
	height: 155px;
}

.middle {
	text-align: center;
	margin: 0 auto;
	width: 600px;
	height: auto;
}
</style>
</head>
<body>
	<%
		java.util.Map<String, String> mapBean = (java.util.Map<String, String>) request.getSession()
				.getAttribute("user");
	%>

	<form name="formList" action="list" method="post">
		<div class="wrapper">
			<div class="middle">
				<br> <br> <br> <br> <br> <br> <br>
				<br>
				<h1 style="padding: 0px 0 10px;">
					中奖名单<%
					if (mapBean != null) {
				%>
					<%=mapBean.size()%></h1>
				<%
					}
				%>
				<br>
				<%
					if (mapBean != null) {
						for (String key : mapBean.keySet()) {
				%>
				<%=key%>--<%=mapBean.get(key)%><br>
				<%
					}
					}
				%>
			</div>
		</div>
	</form>
</body>
</html>