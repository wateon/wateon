<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, wateon.DB.* " %>
<%
	Vector<MessageDTO> v = (Vector<MessageDTO>)request.getAttribute("v");
	String id = (String)request.getSession().getAttribute("id");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>ChatBox</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script language="javascript" type="text/javascript">
	function go(type) {

		if(type=="sendIMessages") {
			document.contents.type.value="sendIMessages";
		} else if(type=="receiveIMessages") {
			document.contents.type.value="receiveIMessages";
		} else if(type=="messages") {
			document.contents.type.value="messages";
		}
		document.contents.submit();
	}
	</script>
</head>
<body>
<form name="contents" method="post" action="chatbox.do">
	<input type="radio" name="messagetype" value="sendIMessages" onclick="javascript:go('sendIMessages');">보낸 메세지
	<input type="radio" name="messagetype" value="messages" onclick="javascript:go('messages');">대화
	<input type="hidden" name="type" value="none">
</form>
<table>
<%
	if(v!=null) {
		MessageDTO dto = null;
		for(int i=0; i<v.size(); i++) {
		
			dto = v.get(i);
			out.print("<tr>");
			out.print("<td align='right'>"+dto.getNumber()+"</td>");
			out.print("<td align='center'>"+dto.getSender()+"</td>");
			out.print("<td align='center'>"+dto.getReceiver()+"</td>");
			if(dto.getMessage().length() < 8) {
				out.print("<td align='center'>"+dto.getMessage()+"</td>");
			} else if(dto.getMessage().length() >= 8) {
				out.print("<td align='center'>"+dto.getMessage().substring(0,5)+"</td>");
			}
			out.print("<td align='center'>"+dto.getDate()+"</td>");
			out.print("<td><a href='#'>"+" - Show detail - "+"</a></td>");
			out.print("</tr>");
		}
	}
%>
</table>
</body>
</html>