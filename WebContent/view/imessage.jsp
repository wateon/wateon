<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="kfmes.natelib.entity.*, java.util.List" %>
<%
	String targetId = request.getParameter("targetId");
	String senderId = (String)request.getSession().getAttribute("id");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Instance Message</title>
</head>

<body>
	<form action='/wateon/sendIMsg.do' name='imessage' method='post'>
		<input type='text' name='targetId' readonly='readonly' value='<%= targetId %>' /><br />
		<input type='text' name='senderId' readonly='readonly' value='<%= senderId %>' /><br />
		<textarea name='message' cols='50' rows='10'></textarea><br />
		<input type='submit' name='submit' value='������'>
	</form>
</body>
</html>