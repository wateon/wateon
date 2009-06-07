<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kfmes.natelib.entity.*, java.util.List" %>
<%
	List<NateGroup> groups = (List<NateGroup>)request.getAttribute("groups");
	NateFriend myself = (NateFriend)request.getAttribute("myself");
	
	String currentStatus = myself.getStatus();
	String selected[] = { "", "", "", "", "", "" };
	if (currentStatus.equals("X"))
		selected[0] = "selected";
	else if (currentStatus.equals("A"))
		selected[1] = "selected";
	else if (currentStatus.equals("B"))
		selected[2] = "selected";
	else if (currentStatus.equals("P"))
		selected[3] = "selected";
	else if (currentStatus.equals("M"))
		selected[4] = "selected";
	else if (currentStatus.equals("X"))
		selected[5] = "selected";
	
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>WateOn</title>
	<script language="javascript" type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
	<script language="javascript" type="text/javascript" src="js/group.js"></script>
	<script language="javascript" type="text/javascript" src="js/mystatus.js"></script>
	<script language="javascript" type="text/javascript" src="js/wateon.js"></script>
	<script language="javascript" type="text/javascript" src="js/friend.js"></script>
</head>
<body>
	<a href="logout.do">Logout</a>
	<table cellspacing="0" border="0">
	<tr>
		<td>아이디</td>
		<td>:&nbsp;<%=myself.getNateID()%></td>
	</tr>
	<tr>
		<td>닉네임</td>
		<td>:&nbsp;<input type="text" name="myNickName"OnClick=setNickName(); ReadOnly value="<%=myself.getNickName()%>" />
		</td>
	</tr>
	<tr>
		<td>상태</td>
		<td>:&nbsp;<select name="myStatus" id="myStatus"
			onchange="setStatus()">
			<option value="O" <%=selected[0]%>>온라인</option>
			<option value="A" <%=selected[1]%>>자리비움</option>
			<option value="B" <%=selected[2]%>>다른용무중</option>
			<option value="P" <%=selected[3]%>>통화중</option>
			<option value="M" <%=selected[4]%>>회의중</option>
			<option value="X" <%=selected[5]%>>오프라인으로 표시</option>
		</select></td>
	</tr>
	</table>
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
