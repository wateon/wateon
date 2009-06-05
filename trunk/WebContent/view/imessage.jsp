<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kfmes.natelib.entity.*, java.util.List" %>
<%
	String targetId = request.getParameter("targetId");
	String senderId = (String)request.getSession().getAttribute("id");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Instance Message</title>
	<script language="javascript" type="text/javascript" src="../js/jquery-1.3.2.min.js"></script> 
	<script language="javascript" type="text/javascript" src="../js/jquery.form.js"></script> 

	<script language="javascript" type="text/javascript">

		$(document).ready(function() { 
			$('#imessage').ajaxForm(function() {
				window.close(); 
			});
		}); 
		
    </script> 
</head>

<body>
	<form action='../sendIMsg.do' id='imessage' name='imessage' method='post'>
		<label for='target'>보내는 사람 : </label>
		<input type='text' name='targetId' readonly='readonly' value='<%= targetId %>' /><br />
		<input type='hidden' name='senderId' readonly='readonly' value='<%= senderId %>' />
		<textarea name='message' cols='50' rows='10'></textarea><br />
		<input type='submit' name='submit' value='보내기'>
	</form>
</body>
</html>