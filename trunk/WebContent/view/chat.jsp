<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String targetId = (String)request.getAttribute("targetId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>채팅: <%= targetId %></title>
	<link rel="stylesheet" href="css/chat.css" type="text/css" media="screen" />
	<script language="javascript" type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
	<script language="javascript" type="text/javascript" src="js/jquery.async.js"></script>
	<script language="javascript" type="text/javascript" src="js/jquery.form.js"></script>
	<script language="javascript" type="text/javascript" src="js/wateon.js"></script>
	<script language="javascript" type="text/javascript" src="js/chat.js"></script>
	<script language="javascript" type="text/javascript">
		var g_targetId = "<%= targetId %>";
		var g_checkMessage = true;
		
		$(document).ready(function() {
			startCheckMessageThread("<%= targetId %>");
			//$(window).unload(chatWindowClose);
			$('#send_msg').ajaxForm({
				dataType: 'json',
				beforeSubmit: beforeSendMessage,
				success: successedSendMessage
			});
		});
	</script>
</head>
<body>
	<div id="chat_list">
	</div>
	
	<div>
		<form id="send_msg" method="post" action="sendMsg.do">
			<textarea id="message" onKeyDown="if (checkEnterKeyAndSend(event.keyCode)) return false;" name="message"></textarea>
			<input type="submit" value="보내기" />
			<input type="hidden" name="targetId" value="<%= targetId %>" />
		</form>
	</div>
	
	<div id="typing">
	</div>
</body>
</html>
