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
	<form name="formBid" action="bid" method="get">
		<div class="wrapper">
			<div class="middle">
				<h1 style="padding: 0px 0 10px;">秒标</h1>
				<br> <br> <br> <br> <br> <br> <br>
				<br> <br> <br> <input type="submit"
					style="width: 600px; height: 200px" value="秒杀" /> <br> <br>
				<br> <br> <br>
			</div>
		</div>
	</form>
	<form name="formList" action="list" method="post">
		<div class="wrapper">
			<div class="middle">
				<br> <br> <br> <br> <br> <input
					type="submit" style="width: 200px; height: 50px" value="查看秒杀结果" />
			</div>
		</div>
	</form>
</body>
</html>