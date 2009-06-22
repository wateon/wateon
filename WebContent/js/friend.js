// 친구.
function addFriend() {
	var friendId = prompt("친구 아이디를 입력하세요", "");
	
	if(friendId)
		$.post("friend.do", {friendId: friendId, action: "add"} );
	else
		alert("친구 아이디를 입력하세요.");
}

function deleteFriend(friendId) {
	var answer = confirm("삭제하시겠습니까");
	if (answer)
		$.post("friend.do", {friendId : friendId, action : "delete"} );
}

