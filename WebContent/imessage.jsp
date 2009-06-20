<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kfmes.natelib.entity.*, java.util.List" %>
<%
	String targetId = request.getParameter("targetId");
	String senderId = (String)request.getSession().getAttribute("id");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Instance Message</title>
	<script language="javascript" type="text/javascript" src="js/jquery-1.3.2.min.js"></script> 
	<script language="javascript" type="text/javascript" src="js/jquery.form.js"></script> 

	<script language="javascript" type="text/javascript">

		$(document).ready(function() { 
			$('#imessage').ajaxForm(function() {
				window.close(); 
			});
		}); 
		
    </script> 
</head>
<body bgcolor='white' text='black' link='blue' vlink='purple' alink='red' leftmargin=0 topmargin=0>
<form action='sendIMsg.do' id='imessage' name='imessage' method='post'>
<TABLE height=200 cellSpacing=1 cellPadding=0 width=300 bgColor=#85bee0	border=0>
	<TBODY>
		<TR>
			<TD align=middle width=300 bgColor=#e7f3f5 height=50>
			<p><font color='#0000CC'> <span style='font-size: 12pt;'>
			<b>받는사람 : </b><input type='text' name='targetId' readonly='readonly' size='20' value='<%= targetId %>' /> </span> </font></p>
			</TD>
		</TR>
		<TR>
			<TD align=middle width=300 bgColor=#f4fafb height=100>
			<textarea name='message' style='font-size: 10pt; width: 100%; height: 100%;' ></textarea>
			</TD>
		</tr>
		<TR>
			<TD align=middle width=300 bgColor=#f4fafb height=50>
			<table border='1' width='91'
				style='border-top-width: 1; border-left-width: 1; border-top-color: rgb(133, 190, 224); border-left-color: rgb(133, 190, 224); border-top-style: none; border-left-style: none;'>
				<tr>
					<td width='81'
						style='border-top-width: 1; border-right-width: 2; border-bottom-width: 2; border-left-width: 1; border-top-style: none; border-right-style: solid; border-bottom-style: solid; border-left-style: none;'
						onClick= 'javascript:document.all.imessage.submit();javascript:window.close();'>
					<p align='center'><span style='font-size: 10pt;'>답장</span></p>
					</td>
				</tr>
			</table>
			</TD>
		</TR>
	</TBODY>
</TABLE>
<input type='hidden' name='senderId' readonly='readonly' value='<%= senderId %>' />
</form>
</body>



