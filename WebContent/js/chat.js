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
		delay : 2000,
		bulk : 0,
		test : function() {
			return true;
		},
		loop : function() {
			var msg = '';
			var url = encodeURI('checkMsg.do');
			$.getJSON(url, {"targetId": targetId}, function(json, state) {
				if (state == 'success' && json.result == 'success') {
					msgs = json.messages;
					for (var i = 0; i < msgs.length; i++) {
						var id = msgs[i].id;
						var nick = msgs[i].nick;
						var msg = msgs[i].msg;
						
						chatList.append(message(id, nick, msg, "receive"));
						scrollDown();
					}
				}
			});
		}
	})
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

function chatWindowClose() {
	var targetId = g_targetId;
	var url = encodeURI('chat.do');
	$.getJSON(url, {"action": "close", "targetId": targetId}, function(json, state) {
		if (state == 'success') {
			$(window).close();
		}
	});
}

// if (event.keyCode == 13) { alert('-_-'); return false; } else return true;
function checkEnterKeyAndSend(keyCode) {
	if (keyCode == 13) {
		$('#send_msg').submit();
		return true;
	}
	else {
		return false;
	}
}
