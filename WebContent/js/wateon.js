
// 가운데에 팝업창을 띄운다.
function popUpCenter(url, title, width, height) {
	var left = (screen.width) ? (screen.width - width) / 2 : 0;
	var top = (screen.height) ? (screen.height - height) / 2 : 0;
	var settings = 'top=' + top + ', left=' + left + ', width=' + width + ', height=' + height + ', scrollbar=yes';
	window.open(url, title, settings);
}

// 채팅창을 띄운다.
function popUpChat(targetId) {
	popUpCenter('chat.do?targetId=' + targetId, 'Chat', 500, 600);
}


/////////////////////////////////////////////////////////////////////////////////////////
//Argument	: Message
//Return	: None
//Comment	: 메시지를 입력받아 새로운창을 뛰어서 메시지를 뿌려준다.
/////////////////////////////////////////////////////////////////////////////////////////
function MessageBox(strMessage){

	MsgWin=window.open('','','scrollbar=no,width=300, height=205,top=200,left=200');
	MsgWin.document.write("<html><head><meta http-equiv='content-type' content='text/html; charset=euc-kr'> <title>WateOn Message</title> <meta name='generator' content='Namo WebEditor v6.0'> </head> <body bgcolor='white' text='black' link='blue' vlink='purple' alink='red' leftmargin=0 topmargin=0> <TABLE height=200 cellSpacing=1 cellPadding=0 width=300 bgColor=#85bee0 border=0>  <TBODY>  <TR>   <TD align=middle width=300 bgColor=#e7f3f5 height=50>    <p>  <b><font color='#AEAEFF'><span style='font-size:12pt;'>▒</span></font></b><font color='#0000CC'><span style='font-size:12pt;'><b>&nbsp; Message Box&nbsp;</b></span></font> <b><font color='#AEAEFF'><span style='font-size:12pt;'>▒</span></font></b></p> </TD></TR>  <TR>   <TD align=middle width=300 bgColor=#f4fafb height=100><span style='font-size:10pt;'>"+strMessage+"</span> </TD></TD>  </tr>  <TR>   <TD align=middle width=300 bgColor=#f4fafb height=50>    <table border='1' width='91' style='border-top-width:1; border-left-width:1; border-top-color:rgb(133,190,224); border-left-color:rgb(133,190,224); border-top-style:none; border-left-style:none;'>     <tr>      <td width='81' style='border-top-width:1; border-right-width:2; border-bottom-width:2; border-left-width:1; border-top-style:none; border-right-style:solid; border-bottom-style:solid; border-left-style:none;' onClick='javascript:window.close();'>      <p align='center'><span style='font-size:10pt;'>Close</span></p> </td>     </tr>    </table> </TD></TR></TBODY></TABLE><p>&nbsp;</p> </body> </html>");
}


/////////////////////////////////////////////////////////////////////////////////////////
//Argument	: Message
//Return	: None
//Comment	: 메시지를 입력받아 새로운창을 뛰어서 메시지를 뿌려준다.
/////////////////////////////////////////////////////////////////////////////////////////
function ReceivedMessageBox(strName, strFrom, strMessage){

MsgWin=window.open('','','scrollbar=no,width=300, height=205,top=200,left=200');
MsgWin.document.write("<html> <head> <meta http-equiv='content-type' content='text/html; charset=euc-kr'> <title>WateOn Message</title> </head> <script language='javascript'> 	function goSendMessage(){ 		document.location.href='imessage.jsp?targetId="+strFrom+"' 	} </script> <body bgcolor='white' text='black' link='blue' vlink='purple' 	alink='red' leftmargin=0 topmargin=0> <TABLE height=200 cellSpacing=1 cellPadding=0 width=300 bgColor=#85bee0	border=0> 	<TBODY> 		<TR> 			<TD align=middle width=300 bgColor=#e7f3f5 height=50> 			<p><font color='#0000CC'> <span style='font-size: 12pt;'> 			<b>보낸이 : "+strName+"</b> </span> </font></p> 			</TD> 		</TR> 		<TR> 			<TD align=middle width=300 bgColor=#f4fafb height=100><textarea 				style='font-size: 10pt; width: 100%; height: 100%;' readonly>"+strMessage+"</textarea></TD> 			</TD> 		</tr> 		<TR> 			<TD align=middle width=300 bgColor=#f4fafb height=50> 			<table border='1' width='91' 				style='border-top-width: 1; border-left-width: 1; border-top-color: rgb(133, 190, 224); border-left-color: rgb(133, 190, 224); border-top-style: none; border-left-style: none;'> 				<tr> 					<td width='81' 						style='border-top-width: 1; border-right-width: 2; border-bottom-width: 2; border-left-width: 1; border-top-style: none; border-right-style: solid; border-bottom-style: solid; border-left-style: none;' 						onClick= 'javascript: goSendMessage();'> 					<p align='center'><span style='font-size: 10pt;'>답장</span></p> 					</td> 				</tr> 			</table> 			</TD> 		</TR> 	</TBODY> </TABLE> </body> </html>");
}


// 쪽지 받았을때..
function processInstanceMessage(imsg) {
	// TODO: 쪽지 받았음. 팝업으로 바꾸자!
	ReceivedMessageBox(imsg.name, imsg.from , imsg.msg);
	
}

// 채팅창 열기
function processNewChat(chat) {
	popUpChat(chat.targetId);
}

// 친구가 상태변경 되었을 때..
function processFriendChanged(friend) {
	// TODO: 친구가 상태 변경.
	//alert("[친구 상태 변경]" + friend.id + "\n" + friend.nick + "\n" + friend.status);
}

// 계속 상태 변화를 체크한다.
function startCheckWateOnStatusThread() {
	jQuery.whileAsync( {
		delay : 200,
		bulk : 0,
		test : function() {
			return true;
		},
		loop : function() {
			var url = encodeURI('checkStatus.do');
			$.getJSON(url, {}, function(json, state) {
				if (json.result == 'success') {
					var updated = json.updated;
					for (var i = 0; i < updated.length; i++) {
						var type = updated[i].type;
						
						// 종류에 따라서, 다르게 처리함.
						if (type == 'imessage')
							processInstanceMessage(updated[i]);
						else if (type == 'new_chat')
							processNewChat(updated[i]);
						else if (type == 'friend_changed')
							processFriendChanged(updated[i]);
						else
							alert('error -_-');
					}
				}
				else if (state == 'fail') {
					alert(json.msg);
				}
			});
		}
	})
}

// 메인 윈도우를 닫으려고 할 때
function mainWindowClose() {
	// TODO: 서버에 로그아웃 메시지를 날려주자.
	//alert('끄는구나?');
}

function popUpCyworld(url){
	window.open(url, 'Register', 'top=300, left=500 width=950, height=600, scrollbar=no');
}

