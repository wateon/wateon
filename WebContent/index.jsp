<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WateOn</title>
<script language="javascript" type="text/javascript">
function openWateOn() {
	var width = 300;
	var height = 500;
	var left = (screen.width) ? (screen.width - width) / 2 : 0;
	var top = (screen.height) ? (screen.height - height) / 2 : 0;
	var settings = 'top=' + top + ', left=' + left + ', width=' + width + ', height=' + height + ', scrollbar=yes';
	window.open('./view/login_form.jsp', 'WateOn', settings);
}
</script>
</head>

<body>
<p>subject : Advanced Web Programming Design and Practice (SoongSil Univ.)</p>
<H1>WateOn</H1>
<p>WateOn is the NateOn that is serviced in SK using web browser.</p>

<a href="javascript:openWateOn()">Run WateOn Client</a>
</body>
</html>