<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	String targetId = request.getParameter("targetId");
	String senderId = request.getParameter("senderId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Instance Message</title>
</head>
<body>
<form action='sendIMsg.do' name='imessage' method='post'>
<textarea name='message' cols='50' rows='10'></textarea>
<br>
<input type='submit' name='submit' value='보내기'>
</form>


</body>
</html>