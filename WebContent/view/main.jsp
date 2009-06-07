<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kfmes.natelib.entity.*, java.util.List" %>
<%
	List<NateGroup> groups = (List<NateGroup>)request.getAttribute("groups");
	NateFriend myself = (NateFriend)request.getAttribute("myself");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>WateOn</title>
	<script language="javascript" type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
	<script language="javascript" type="text/javascript" src="js/group.js"></script>
	<script language="javascript" type="text/javascript" src="js/wateon.js"></script>
	<script language="javascript" type="text/javascript" src="js/friend.js"></script>
</head>
<body>
	<h3>Login</h3>
	<a href="logout.do">Logout</a>
	
	<ul>
		<li>아이디: <%= myself.getNateID() %></li>
		<li>닉네임: <%= myself.getNickName() %></li>
		<li>상태: <%= myself.getFormattedStatus() %></li>
	</ul>
	<br />
	<input type="button" onclick="createGroup()" value="그룹 추가">
	<input type="button" onclick="addFriend()" value="친구추가">
	
	<br />
	<br />
	
<%
	for (NateGroup group : groups) {
		String groupName = group.getName();
		out.println(groupName + " <input type='button' onclick='deleteGroup(\"" + groupName + "\")' value='삭제'>" + 
				" <input type='button' onclick='modifyGroup(\"" + groupName + "\")' value='변경'>" + "<ul>");
		for (NateFriend user : group.getList()) {
			if (user.getStatus().equals("F") == false) {
				out.println("<li>");
%>
					<a href="chat.do?targetId=<%=user.getID()%>"
					onclick="javascript:popUpCenter('chat.do?targetId=<%=user.getID()%>', 'Chat', 500, 600); return false;">
					<%= user.getNameNick() %>
					</a>
					<a href="./main.jsp"
					onclick="javascript:popUpCenter('imessage.jsp?targetId=<%=user.getID() %>', '쪽지', 500, 255); return false;"><쪽지></a>
					<a href="#" onclick="deleteFriend('<%=user.getID()%>')">(삭제)</a>
					<a href="#" onclick="banFriend('<%=user.getID()%>')">(차단)</a>
<%
				out.println("</li>");
			}
		}
		out.println("</ul>");
	}
%>
	
</body>
</html>
