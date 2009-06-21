<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, wateon.DB.* " %>
<%
	Vector<MessageDTO> v = (Vector<MessageDTO>)request.getAttribute("v");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script language="javascript" type="text/javascript">
	function go(type) {

		if(type=="sendIMessages") {

		} else if(type=="receiveImessages") {

		} else if(type=="messages") {

		}

	}
	</script>
</head>
<body>
<form method="post" action="">
	<input type="radio" name="messagetype" value="sendIMessages" onclick="javascript:go('sendIMessages');">보낸 메세지	<input type="radio" name="messagetype" value="receiveIMessages" onclick="javascript:go('receiveIMessages');">받은 메세지
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
			out.print("<td>"+dto.getNumber()+"</td>");
			out.print("<td>"+dto.getSender()+"</td>");
			out.print("<td>"+dto.getReceiver()+"</td>");
			out.print("<td>"+dto.getMessage()+"</td>");
			out.print("<td>"+dto.getDate()+"</td>");
			out.print("</tr>");
		}
	}
%>
</table>
</body>
</html>
