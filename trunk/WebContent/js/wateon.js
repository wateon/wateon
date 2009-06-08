
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


// 쪽지 받았을때..
function processInstanceMessage(imsg) {
	// TODO: 쪽지 받았음. 팝업으로 바꾸자!
	alert("[쪽지]" + "\n" + imsg.from + "\n" + imsg.msg);
}

// 채팅창 열기
function processNewChat(chat) {
	popUpChat(chat.targetId);
}

// 친구가 상태변경 되었을 때..
function processFriendChanged(friend) {
	// TODO: 친구가 상태 변경.
	alert("[친구 상태 변경]" + friend.id + "\n" + friend.nick + "\n" + friend.status);
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
	alert('끄는구나?');
}
