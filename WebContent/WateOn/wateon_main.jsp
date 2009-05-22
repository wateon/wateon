<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language="javascript" type="text/javascript">
function deleteValue(str) {
	document.getElementById(str).value="";
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>WateOn</title>
</head>
<body>
<H3>WateOn</H3>
<form name="Login" action="/WateOnServlet" method="post">
<input type="text" size="30" name="id" value="id" onfocus="deleteValue('id')"><br>
<input type="password" size="33" name="password" value="password" onfocus="deleteValue('password')"><br>
<input type="hidden" value="login">
<input type="submit" value="Log in">
</form>
<a href="http://member.nate.com/sccustomer/join/nate/nateon/regist.jsp" target="_blank">Get a new NateOn account</a>
</body>
</html>
