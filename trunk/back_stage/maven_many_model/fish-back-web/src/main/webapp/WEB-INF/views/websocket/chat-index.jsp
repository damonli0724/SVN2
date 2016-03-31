<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/base/base.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script type="text/javascript" src="${jsBasePath}/jquery.min.js"></script> 
<link href="${cssBasePath}/chat.css" rel="stylesheet" type="text/css" />
</head>
<body>
	欢迎：<div id="userName">${user.username}</div>
	<div id="content" style="width:50%;float:left"></div>
	<div id="" style="width:20%;height:400px;float:right;border:1px solid #00F;margin-right:29%">
		在线用户:
	</div>
	<input type="text" placeholder="请输入要发送的信息" id="msg" class="msg" onkeydown="send(event)">
	<input type="button" value="发送" class="send" onclick="sendMsg()" >
	<input type="button" value="清空" class="clear" onclick="clearAll()">
</body>


<script>
	 	var path = window.location.host+$("#globe_context_id").val()+"/";
		var sendUserId = ${user.userId};  //发送者的Id
		var sendUserName ='${user.username}';
		var reciveUserId=0; //默认发给所有人
		var content="";
		
		
		
		//创建websocket
		var websocket;
		if ('WebSocket' in window) {
			websocket = new WebSocket("ws://" + path + "/ws?userId="+sendUserId);
		} else if ('MozWebSocket' in window) {
			websocket = new MozWebSocket("ws://" + path + "/userId"+sendUserId);
		} else {
			websocket = new SockJS("http://" + path + "/ws/sockjs"+sendUserId);
		}
		
		//创建连接时
		websocket.onopen = function(event) {
			console.log("WebSocket:已连接");
			console.log(event);
		};
		//接收信息时
		websocket.onmessage = function(event) {
			var data=JSON.parse(event.data);
			console.log("WebSocket:收到一条消息",data);
			var textCss=data.sendUserId==-1?"sfmsg_text":"fmsg_text";  //判断是不是系统消息
			$("#content").append("<div class='fmsg'><label class='name'>"+data.sendUserName+"&nbsp;"+data.date+"</label><div class='"+textCss+"'>"+data.content+"</div></div>");	
			scrollToBottom();
		}; 
		
		//出现错误时
		websocket.onerror = function(event) {
			console.log("WebSocket:发生错误 ");
			console.log(event);
		};
		//websocket关闭时
		websocket.onclose = function(event) {
			console.log("WebSocket:已关闭");
			console.log(event);
		}
		//*******************************************************************************************		
		function sendMsg(){
			var v=$("#msg").val();
			if(v==""){
				return;
			}else{
				var data={};
				data.sendUserId = sendUserId;
				data.sendUserName = sendUserName;
				data.reciveUserId = reciveUserId;
				data.content = content;
				/* data["sendUserId"]=from;
				data["sendUserName"]=fromName;
				data["reciveUserId"]=to;
				data["content"]=v; */
				websocket.send(JSON.stringify(data));
				/* if(to!=0) */
				/* $("#content").append("<div class='tmsg'><label class='name'>我&nbsp;"+new Date().Format("yyyy-MM-dd hh:mm:ss")+"</label><div class='tmsg_text'>"+data.content+"</div></div>"); */
				/* scrollToBottom(); */
				$("#msg").val("");
			}
		}
			
		function scrollToBottom(){
			var div = document.getElementById('content');
			div.scrollTop = div.scrollHeight;
		}
		
			Date.prototype.Format = function (fmt) { //author: meizz 
			    var o = {
			        "M+": this.getMonth() + 1, //月份 
			        "d+": this.getDate(), //日 
			        "h+": this.getHours(), //小时 
			        "m+": this.getMinutes(), //分 
			        "s+": this.getSeconds(), //秒 
			        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
			        "S": this.getMilliseconds() //毫秒 
			    };
			    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
			    for (var k in o)
			    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
			    return fmt;
			}
			
		function send(event){
			var code;
			 if(window.event){
				 code = window.event.keyCode; // IE
			 }else{
				 code = e.which; // Firefox
			 }
			if(code==13){ 
				sendMsg();            
			}
		}
			
			function clearAll(){
				$("#content").empty();
			}
		</script>
</html>
