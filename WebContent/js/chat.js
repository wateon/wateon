// 주의: 이 파일을 포함하기 전에, jquery와 jquery.async.js 가 포함되어 있어야 함!!

function startCheckMessageThread(targetId) {
	var chatList = jQuery('#chat_list');
	chatList.empty();

	jQuery.whileAsync( {
		delay : 200,
		bulk : 0,
		test : function() {
			return true;
		},
		loop : function() {
			var msg = '';
			var url = encodeURI('checkMsg.do?targetId=' + targetId);
			$.getJSON(url, {}, function(json_results, state) {
				
			});
			
			chatList.text(chatList.text() + ' ' + n);
			n--;
		}
	})
}
