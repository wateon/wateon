// 주의: 이 파일을 포함하기 전에, jquery와 jquery.async.js 가 포함되어 있어야 함!!

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
						
						var to_append_str = '<p>' + nick + ' : ' + msg + '</p>';
						
						chatList.html(chatList.html() + to_append_str);
						window.scrollBy(0, window.outerHeight);
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
	//alert(result.msg);
	var msg = '';
	var chatList = $('#chat_list');
	
	if (json.result == 'success') {
		msg = json.msg;
		chatList.html(chatList.html() + '<p size="15">' + msg + '</p>');
	}
}
