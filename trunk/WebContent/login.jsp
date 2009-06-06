<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language="javascript" type="text/javascript">
function deleteValue(str) {
	document.getElementById(str).value="";
}
function connectToRegister() {
	window.open("http://member.nate.com/sccustomer/join/nate/nateon/regist.jsp",'Register', 'top=300, left=500 width=450, height=370, scrollbar=no');
}

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WateOn</title>
</head>
<body>
<H3>WateOn</H3>
<form name="Login" action="login.do" method="post">
<table cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td>아이디</td>
		<td>:&nbsp;<input type="text" size="23" name="id"
			value="gsk1047@nate.com" onfocus="deleteValue('id')"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td>:&nbsp;<input type="password" size="25" name="password"
			value="tjsrua" onfocus="deleteValue('password')"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit"
			value="Log in"></td>
	</tr>
</table>
<input type="hidden" name="action" value="login"></form>
<a href="javascript:connectToRegister()">Get a new NateOn account</a>
</body>
</html>
