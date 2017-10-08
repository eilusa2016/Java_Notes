<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>chat</title>
<style type="text/css">
#content {
	border: 1px solid #CCCCCC;
	border-right-color: #999999;
	border-bottom-color: #999999;
	height: 170px;
	width:500px;
	overflow-y: scroll;
	padding: 5px;
	float: left;
	/*width: 100%;*/
}

#userList {
	border: 1px solid #CCCCCC;
	border-right-color: #999999;
	border-bottom-color: #999999;
	height: 170px;
	overflow-y: scroll;
	padding: 5px;
	width:200px;
	float: left;
	margin-left: 10px;
	font-size: 12px;
}
</style>
</head>
<body>
	<div>
		<button id="connect" onclick="connect();">链接</button>
		<button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
	</div>
	<!-- 内容 -->
	<div id="content"></div>
	
	<!-- 用户列表 -->
	<div id="userList"></div>
	<div style="clear: both;"></div>
	<div>
		消息:<input type="text" id="message" /><input onclick="send()" type="button" value="发送" />
	</div>
	<script type="text/javascript">
		var ws = null;
		//var target = 'ws://localhost:8080/JavaWebSocket/websocket/echoAnnotation?username=${sessionScope.username}&psw=test_null';
		var target = 'ws://localhost:8080/JavaWebSocket/websocket/echoAnnotation';
		var userName="${sessionScope.username}";
		/**
		 *链接
		 */
		function connect() {
			if ('WebSocket' in window) {
				ws = new WebSocket(target);
			} else if ('MozWebSocket' in window) {
				ws = new MozWebSocket(target);
			} else {
				alert("浏览器不支持websocket，请升级浏览器...");
				return;
			}
			document.getElementById("connect").disabled = true;
			document.getElementById("disconnect").disabled = false;
			ws.onopen = function() {
				var message="{'type':open,'who':${sessionScope.username},'witch':users}";
				ws.send(message);
			};
			ws.onmessage = function(event) {
				var objs= JSON.parse(event.data);
				console.log(objs);
				var cc = document.getElementById('userList');
				var mess="";
				console.log(event);
            	if(objs.witch=="content"){
            		cc = document.getElementById('content');
            		mess=objs.who+" <br/>"+objs.mess;
            		var p = document.createElement('p');
    				p.style.wordWrap = 'break-word';
    				p.appendChild(document.createTextNode(mess));
    				cc.appendChild(p);
            	}else{
            		cc.innerHTML="";
            		var list=objs.nameList;
            		for(var ii=0;ii<list.length;ii++){
            			var p = document.createElement('p');
        				p.style.wordWrap = 'break-word';
        				p.appendChild(document.createTextNode(list[ii]+"在线!"));
        				cc.appendChild(p);
            		}
            	}
				

			};
			ws.onclose = function(event) {
				document.getElementById("connect").disabled = false;
				document.getElementById("disconnect").disabled = true;
				console.log(event.code, event.reason);
			};

		}
		//发送消息
		function send() {
			var message = document.getElementById("message").value;
			message="{'type':chat,'mess':"+message+",'who':"+userName+",'witch':content}";
			ws.send(message);
			document.getElementById("message").value = "";
		}

		//断开连接
		function disconnect() {
			if (ws != null) {
				ws.close();
				ws = null;
			}
			document.getElementById("connect").disabled = false;
			document.getElementById("disconnect").disabled = true;
			//document.getElementById("echo").disabled = true;
		}
	</script>
</body>
</html>