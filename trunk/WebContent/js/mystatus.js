function setStatus() {
	var newStatus = document.all.myStatus.value;
	
	if (newStatus) {
		if (newStatus == "O")
			$("#myStatusImage").attr('src', 'image/wateon_state_1.gif');
		else if (newStatus == "A")
			$("#myStatusImage").attr('src', 'image/wateon_state_2.gif');
		else if (newStatus == "B")
			$("#myStatusImage").attr('src', 'image/wateon_state_3.gif');
		else if (newStatus == "P")
			$("#myStatusImage").attr('src', 'image/wateon_state_3.gif');
		else if (newStatus == "M")
			$("#myStatusImage").attr('src', 'image/wateon_state_2.gif');
		else if (newStatus == "X")
			$("#myStatusImage").attr('src', 'image/wateon_state_4.gif');

		$.post("myStatus.do", {newStatus: newStatus, action: "status"});
	}
	else
		alert("상태가 올바르지 않습니다.");
}

function setNickName() {
	var newNickName = prompt("닉네임을 입력하세요", "");
	
	if (newNickName){
		$.post("myStatus.do", {newNickName: newNickName, action: "nickName"});
		document.all.myNickName.value=newNickName;
	}
}

