function setStatus() {
	var newStatus = document.all.myStatus.value;
	
	if (newStatus)
		$.post("myStatus.do", {newStatus: newStatus, action: "status"});
	else
		alert("상태가 올바르지 않습니다.");
}

function setNickName() {
	var newNickName = prompt("닉네임을 입력하세요", "");
	
	if (newNickName){
		$.post("myStatus.do", {newNickName: newNickName, action: "nickName"});
		document.all.myNickName.value=newNickName;
	}else
		alert("닉네임이 올바르지 않습니다.");
}

