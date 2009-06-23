// 주의: 이 파일을 포함하기 전에, jquery와 jquery.async.js 가 포함되어 있어야 함!!

function scrollDown() {
	window.scrollBy(0, window.outerHeight);
}

function message(id, nick, msg, type) {
	return '<p class="msg ' + type + '">' + nick + '<br />' + msg + '</p>';
}

function startCheckMessageThread(targetId) {
	var chatList = $('#chat_list');
	chatList.empty();

	jQuery.whileAsync( {
		delay : 200,
		bulk : 0,
		test : function() {
			return g_checkMessage;
		},
		loop : function() {
			var url = encodeURI('checkMsg.do');
			$.post(url, {"targetId": targetId}, function(json, state) {
				if (state == 'success' && json.result == 'success') {
					
					// 그동안 받은 메시지를 모두 보여준다.
					var msgs = json.messages;
					for (var i = 0; i < msgs.length; i++) {
						var id = msgs[i].id;
						var nick = msgs[i].nick;
						var msg = msgs[i].msg;
						
						chatList.append(message(id, nick, msg, "receive"));
						scrollDown();
					}
					
					// 타이핑 하는 메시지를 보여준다.
					$("#typing").text(json.typing);
				}
				else if (json.result == 'fail') {
					g_checkMessage = false;
					
					// 채팅이 끝났음. (상대가 나갔음)
					if (json.msg == "chat room was closed") {
						$("#message").attr("disabled", true);
						chatList.append("상대방이 나갔습니다.");
					}
					
					// 그 외의 에러
					else {
						alert(json.msg);
					}
				}
			});
		}
	}, "json");
}

function beforeSendMessage(data, option) {
	//alert(data);
}

function successedSendMessage(json, state) {
	if (json.result == 'success') {
		var chatList = $('#chat_list');
		var html = message(json.id, json.nick, json.msg, "send");
		$('#message').val('');
		chatList.append(html);
		scrollDown();
	}
}

function chatWindowClose(e) {
/*
	// Firefox || IE
	e = e || window.event;
	var y = e.pageY || e.clientY;
	
	// window closed
	if (y < 0) {
		alert('닫아?');
	}
	
	else {
		// window refresh -_-;
		alert('새로고침?');
	}
*/
	
/*
	var targetId = g_targetId;
	var url = encodeURI('chat.do');
	$.getJSON(url, {"action": "close", "targetId": targetId}, function(json, state) {
		if (state == 'success') {
			$(window).close();
		}
	});
*/
}

//if (event.keyCode == 13) { alert('-_-'); return false; } else return true;
function checkEnterKeyAndSend(keyCode) {
	if (keyCode == 13) {
		$('#send_msg').submit();
		return true;
	}
	else {
		return false;
	}
}
