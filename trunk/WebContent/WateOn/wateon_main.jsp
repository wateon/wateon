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
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>WateOn</title>
</head>
<body>
<H3>WateOn</H3>
<form name="Login" action="../LoginServlet" method="post">
<input type="text" size="30" name="id" value="id" onfocus="deleteValue('id')"><br>
<input type="password" size="33" name="password" value="password" onfocus="deleteValue('password')"><br>
<input type="hidden" name="action" value="login">
<input type="submit" value="Log in">
</form>
<a href="javascript:connectToRegister()">Get a new NateOn account</a>
</body>
</html>
