<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kfmes.natelib.entity.*, java.util.List" %>
<%
	List<NateGroup> groups = (List<NateGroup>)request.getAttribute("groups");
	NateFriend myself = (NateFriend)request.getAttribute("myself");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language="javascript" type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script language="javascript" type="text/javascript">
function hello() {
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WateOn</title>
</head>
<body>
<H3>Login</H3>
<a href="logout.do">Logout</a>

<ul>
	<li>아이디: <%= myself.getNateID() %></li>
	<li>닉네임: <%= myself.getNickName() %></li>
	<li>상태: <%= myself.getFormattedStatus() %></li>
</ul>

<br />
<br />

<%
	for (NateGroup group : groups) {
		out.println(group.getName() + "<ul>");
		for (NateFriend user : group.getList()) {
			if (user.getStatus().equals("F") == false) {
				out.println("<li>");
%>
				<a href="createChat.do?targetId=<%=user.getID()%>" target="_blank"><%= user.getNameNick() %></a>
<%
				out.println("</li>");
			}
		}
		out.println("</ul>");
	}
%>

</body>
</html>
