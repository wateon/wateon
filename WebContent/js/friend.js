// 친구.
function addFriend() {
	var addedFriendId = prompt("친구 아이디를 입력하세요", "");
	
	if (addedFriendId)
		$.post("friend.do", {friendId: addedFriendId, action: "add"} );
	else
		alert("친구 아이디를 입력하세요.");
}

function deleteFriend(toDeleteFriendId) {
	var answer = confirm("삭제하시겠습니까");
	if (answer)
		$.post("friend.do", {friendId : toDeleteFriendId, action : "delete"} );
}
