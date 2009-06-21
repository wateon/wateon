<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>WateOn</title>
	<script language="javascript" type="text/javascript">
		function deleteValue(str) {
			document.getElementById(str).value="";
		}
		function connectToRegister() {
			window.open("http://member.nate.com/sccustomer/join/nate/nateon/regist.jsp",'Register', 'top=300, left=500 width=450, height=450, scrollbar=no');
		}
	</script>
	<style type="text/css">
	<!--
		body {
			font-size: 12px;
		}
	-->
	</style>
</head>
<body>
	<img src="image/wateon_logo.gif" />
	<br />
	<br />
	
	<form name="Login" action="login.do" method="post">
		<label for="id">아이디 : </label>
		<input id="id" name="id" type="text" size="25" value="gsk1047@nate.com" onfocus="deleteValue('id')">
		<br />
		
		<label for="password">비밀번호 : </label>
		<input id="password" name="password" type="password" size="27" value="tjsrua" onfocus="deleteValue('password')">
		<br />
		
		<input type="submit" value="Log in">
	</form>
	
	<br />
	<hr />
	<br />
	
	<div>
		<a href="javascript:connectToRegister()">네이트온 가입하기</a>
	</div>
</body>
</html>









