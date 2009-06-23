function setStatus() {
	var newMyStatus = document.all.myStatus.value;
	
	if (newMyStatus) {
		if (newMyStatus == "O")
			$("#myStatusImage").attr('src', 'image/wateon_state_1.gif');
		else if (newMyStatus == "A")
			$("#myStatusImage").attr('src', 'image/wateon_state_2.gif');
		else if (newMyStatus == "B")
			$("#myStatusImage").attr('src', 'image/wateon_state_3.gif');
		else if (newMyStatus == "P")
			$("#myStatusImage").attr('src', 'image/wateon_state_3.gif');
		else if (newMyStatus == "M")
			$("#myStatusImage").attr('src', 'image/wateon_state_2.gif');
		else if (newMyStatus == "X")
			$("#myStatusImage").attr('src', 'image/wateon_state_4.gif');

		$.post("myStatus.do", {newStatus: newMyStatus, action: "status"});
	}
	else
		alert("상태가 올바르지 않습니다.");
}

function setNickName() {
	var newMyNickName = prompt("닉네임을 입력하세요", "");
	
	if (newMyNickName) {
		$.post("myStatus.do", {newNickName: newMyNickName, action: "nickName"});
		document.all.myNickName.value = newMyNickName;
	}
}

