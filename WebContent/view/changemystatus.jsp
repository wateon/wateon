<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String currentNickName="김선겸";
	String currentStatus="M";
	String selected[] = {"","","","","",""} ;
	if(currentStatus.equals("X"))
		selected[0]="selected";
	else if(currentStatus.equals("A"))
		selected[1]="selected";
	else if(currentStatus.equals("B"))
		selected[2]="selected";
	else if(currentStatus.equals("P"))
		selected[3]="selected";
	else if(currentStatus.equals("M"))
		selected[4]="selected";
	else if(currentStatus.equals("X"))
		selected[5]="selected";
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>내상태 변경</title>
	<link rel="stylesheet" href="css/chat.css" type="text/css" media="screen" />
	<script language="javascript" type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
	<script language="javascript" type="text/javascript" src="js/jquery.async.js"></script>
	<script language="javascript" type="text/javascript" src="js/jquery.form.js"></script>
	<script language="javascript" type="text/javascript" src="js/wateon.js"></script>
	<script language="javascript" type="text/javascript" src="js/chat.js"></script>
	<script language="javascript" type="text/javascript">
		
	</script>
</head>
<body>
<form id="mystatus" method="post" action="action.do">
<table cellspacing="0" border="1">
	<tr>
		<td>NickName</td>
		<td><input type="text" name="대화명" value="<%=currentNickName%>"></input></td>
	</tr>
	<tr>
		<td>상태</td>
		<td><select name="currentStatus">
			<option value="O" <%=selected[0]%>>온라인</option>
			<option value="A" <%=selected[1]%>>자리비움</option>
			<option value="B" <%=selected[2]%>>다른용무중</option>
			<option value="P" <%=selected[3]%>>통화중</option>
			<option value="M" <%=selected[4]%>>회의중</option>
			<option value="X" <%=selected[5]%>>오프라인으로 표시</option>
		</select></td>
	</tr>
	<tr>
		<td colspan="2" align="right"><input type="submit" value="보내기"></input></td>
	</tr>
</table>

</form>
</body>
</html>