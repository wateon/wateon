// 그룹.

function createGroup() {
	var inputtedGroupName = prompt("그룹명을 입력하세요", "");
	
	if (inputtedGroupName)
		$.post("group.do", {groupName: inputtedGroupName, action: "create"});
	else
		alert("그룹명을 입력하세요");
}

function deleteGroup(groupName) {
	var answer = confirm("삭제하시겠습니까?");
	if (answer)
		$.post("group.do", {groupName: groupName, action: "delete"});
}

function modifyGroup(groupName) {
	var inputtedModifyGroupName = prompt("그룹명을 입력하세요", "");
	if (inputtedModifyGroupName)
		$.post("group.do", {groupName: groupName, modifyGroupName: inputtedModifyGroupName, action: "modify"});
	else
		alert("그룹명을 입력하세요");
}
