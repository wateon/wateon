// 그룹.
function createGroup() {
	var groupName = prompt("그룹명을 입력하세요", "");
	
	if (groupName)
		$.post("group.do", {groupName: groupName, action: "create"});
	else
		alert("그룹명을 입력하세요");
}

function deleteGroup(groupName) {
	var answer = confirm("삭제하시겠습니까?");
	if (answer)
		$.post("group.do", {groupName: groupName, action: "delete"});
}

function modifyGroup(groupName) {
	var modifyGroupName = prompt("그룹명을 입력하세요", "");
	if (modifyGroupName)
		$.post("group.do", {groupName: groupName, modifyGroupName: modifyGroupName, action: "modify"});
	else
		alert("그룹명을 입력하세요");
}
