<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kfmes.natelib.entity.*, kfmes.natelib.util.*, java.util.List" %>
<%
	List<NateGroup> groups = (List<NateGroup>)request.getAttribute("groups");
	NateFriend myself = (NateFriend)request.getAttribute("myself");
	
	
	
	
	String myCyworld = (String) request.getAttribute("cyworldUrl");
	String myEmail = myself.getEmail();
	
	String currentStatus = myself.getStatus();
	String selected[] = { "", "", "", "", "", "" };
	if (currentStatus.equals("O"))
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
	<link rel="stylesheet" href="css/main.css" type="text/css" media="screen" />
	<script language="javascript" type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
	<script language="javascript" type="text/javascript" src="js/jquery.async.js"></script>
	<script language="javascript" type="text/javascript" src="js/group.js"></script>
	<script language="javascript" type="text/javascript" src="js/mystatus.js"></script>
	<script language="javascript" type="text/javascript" src="js/wateon.js"></script>
	<script language="javascript" type="text/javascript" src="js/friend.js"></script>
	<script language="javascript" type="text/javascript">
		$(document).ready(function() {
			startCheckWateOnStatusThread();
			$(window).unload(mainWindowClose);

			var status = $('#myStatus').attr('value');
			
			if (status == "O")
				$("#myStatusImage").attr('src', 'image/wateon_state_1.gif');
			else if (status == "A")
				$("#myStatusImage").attr('src', 'image/wateon_state_2.gif');
			else if (status == "B")
				$("#myStatusImage").attr('src', 'image/wateon_state_3.gif');
			else if (status == "P")
				$("#myStatusImage").attr('src', 'image/wateon_state_3.gif');
			else if (status == "M")
				$("#myStatusImage").attr('src', 'image/wateon_state_2.gif');
			else if (status == "X")
				$("#myStatusImage").attr('src', 'image/wateon_state_1.gif');
		});
	</script>
</head>
<body topmargin="0" leftmargin="0">
	<div>
		<table cellspacing="0" border="0" width="100%">
			<tr>
				<td align="left">
				<img src="image/wateon_logo_small.gif">				
				</td>
				<td align="center">
				<a href="logout.do">Logout</a>
				</td>
			</tr>
		</table>
	</div>
	<div class="menu" >
		<table cellspacing="0" border="0">
			<tr>
				<td>닉네임 :</td>
				<td colspan="2">&nbsp;<input type="text" name="myNickName" OnClick=setNickName(); ReadOnly value="<%=MsgUtil.getRealString(myself.getNickName())%>" size="30" border="0"/>
				</td>
			</tr>
			<tr>
				<td>상태 :</td>
				<td>
					<select name="myStatus" id="myStatus" onchange="setStatus()">
						<option value="O" <%=selected[0]%>>온라인</option>
						<option value="A" <%=selected[1]%>>자리비움</option>
						<option value="B" <%=selected[2]%>>다른용무중</option>
						<option value="P" <%=selected[3]%>>통화중</option>
						<option value="M" <%=selected[4]%>>회의중</option>
						<option value="X" <%=selected[5]%>>오프라인으로 표시</option>
					</select>
				</td>
				<td>
					<img src="image/wateon_cyworld.gif" onclick="popUpCyworld('<%=myCyworld%>')">
				</td>
			</tr>
			<tr>
				<td align="left">
					<img id="myStatusImage" src="" style="margin: 0px 0px 0px 0px;"/>
				</td>
				<td colspan="2" align="right">
					<input type="button" onclick="createGroup()" value="그룹 추가">
					<input type="button" onclick="addFriend()" value="친구추가">
				</td>
			</tr>
		</table>
	</div>
	<br />
	<div class="friend_list">
<%
	for (NateGroup group : groups) {
		String groupName = group.getName();
		
		out.println("<ul>");
		out.println("<li>");
		out.println("<h3 onclick=\"javascript: $('#group_" + groupName + "').slideToggle('fast');\">");
		out.println(groupName);
		out.println("<input type='button' onclick='deleteGroup(\"" + groupName + "\")' value='삭제'>");
		out.println("<input type='button' onclick='modifyGroup(\"" + groupName + "\")' value='변경'>");
		out.println("</h3>");
		
		out.println("<ul id=\"group_" + groupName + "\">");
		
		
		for (NateFriend user : group.getList()) {
			if (user.getStatus().equals("F") == false) {
				out.println("<li>");
%>
					<a href="chat.do?targetId=<%=user.getID()%>"
						onclick="javascript:popUpChat('<%=user.getID()%>'); return false;">
						<%= user.getNameNick() %>
					</a>
					<a href="./main.jsp"
						onclick="javascript:popUpCenter('imessage.jsp?targetId=<%=user.getID() %>', '쪽지', 500, 255); return false;">
						&lt;쪽지&gt;
					</a>
					<a href="#" onclick="deleteFriend('<%=user.getID()%>')">(삭제)</a>
					<a href="#" onclick="banFriend('<%=user.getID()%>')">(차단)</a>
<%
				out.println("</li>");
			}
		}
		out.println("</ul>");
		out.println("</li>");
		out.println("</ul>");
	}
%>
	</div>
	
</body>
</html>









