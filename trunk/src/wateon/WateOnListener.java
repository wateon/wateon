package wateon;

import java.util.ArrayList;
import java.util.Properties;

import wateon.entity.Message;
import wateon.entity.ChatRoom;

import kfmes.natelib.*;
import kfmes.natelib.entity.*;
import kfmes.natelib.event.NateListener;
import kfmes.natelib.ftp.*;
import kfmes.natelib.msg.*;
import kfmes.natelib.util.*;

public class WateOnListener implements NateListener {
	private WateOnUser self;

	public WateOnListener(WateOnUser wateOnUser) {
		this.self = wateOnUser;
	}

	@Override
	public void OwnerStatusUpdated() {
	}

	@Override
	public void addFailed(int arg0) {
	}

	@Override
	public void allListUpdated() {
	}

	@Override
	public void buddyListInit(GroupList arg0) {
	}

	@Override
	public void buddyListModified() {
		// TODO: 친구 목록이 변경 되었음.
	}


	// 상태가 변경된 친구에 대한 정보를 큐에 저장한다.
	@Override
	public void buddyModified(NateFriend friend) {
		//System.out.println("친구 상태 변경: " + friend.getID());
		self.addFriendModified(friend);
	}


	// 채팅 메시지를 받았을 때..
	@Override
	public void chatMessageReceived(SwitchBoardSession session, NateFriend other, MimeMessage msg) {
		
		String targetId = other.getID();
		
		// 어느 채팅방인지 찾아낸다.
		ChatRoom room = self.getChatRoom(targetId);
		
		// 해당 채팅방이 있는지 확인한다.
		if (room == null && self.hasChatRoom(targetId) == false) {
			room = self.setChatSession(targetId, session);
			
			// 상대방이 말 걸어서 만들어진 목록에 추가한다.
			self.addNewReceivedChatRoom(targetId);
			//System.out.println("채팅창 추가 : " + targetId);
		}
		
		// 일반 메시지..
		if (msg.getType().equals("MSG")) {
			// 메시지 목록에 추가해준다.
			String nick = MsgUtil.getRealString(other.getNickName());
			String m = MsgUtil.getRealString(msg.getMessage());
			//System.out.println("MSG : " + m);
			room.addNewMessage(new Message(targetId, nick, m));
		}
		
		// 상대방이 창을 닫았음! -_-;;
		else if (msg.getType().equals("QUIT")) {
			//System.out.println("QUIT : " + targetId);
			self.closeChatRoom(targetId);
		}
		
		// 그 밖의 경우 -_-;;
		else {
			System.out.println("TYPE = " + msg.getType());
		}
	}

	@Override
	public void disConnected() {
		self.logout();
	}

	@Override
	public void filePosted(SwitchBoardSession arg0, FileRecver arg1,
			NateFriend arg2, ArrayList<NateFile> arg3) {
	}

	@Override
	public void fileRecved(FileRecver arg0, NateFile arg1) {
	}

	@Override
	public void fileSend(SwitchBoardSession arg0, FileSender arg1,
			NateFriend arg2, ArrayList<NateFile> arg3) {
	}

	@Override
	public void fileSendAccepted(SwitchBoardSession arg0, String arg1) {
	}

	@Override
	public void fileSendEnded(FileSender arg0, NateFile arg1) {
	}

	@Override
	public void fileSendError(FileSender arg0, Throwable arg1) {
	}

	@Override
	public void fileSendRejected(SwitchBoardSession arg0, String arg1,
			String arg2) {
	}

	@Override
	public void fileSendStarted(FileSender arg0) {
	}

	@Override
	public void fileTransferAborted(String arg0) {
	}


	// 쪽지 받았음.
	@Override
	public void instanceMessageReceived(InstanceMessage imessage) {
		//System.out.println("쪽지 받았음 : " + imessage.getFrom() + " : " + imessage.getMessage());
		self.addInstanceMessage(imessage);
	}

	@Override
	public void killed() {
	}

	@Override
	public void listAdd(NateFriend friend) {
		// TODO: 친구 추가??
	}

	@Override
	public void listOnline(NateFriend friend) {
		// 구현할 필요없음. buddyModified() 에서 해결 됨.
	}

	@Override
	public void loginComplete() {
	}

	@Override
	public void loginError(String arg0) {
	}

	@Override
	public void logoutNotify() {
		// TODO: 딴데서 로그인해서, 로그아웃.
	}

	@Override
	public void notifyUnreadMail(Properties arg0, int arg1) {
	}

	@Override
	public void progressTyping(SwitchBoardSession session, NateFriend friend, int typingNow) {
		//System.out.println(friend.getNateID() + " : " + typingNow);
		ChatRoom room = self.getChatRoom(friend.getNateID());
		if (room != null)
			room.addTyping(friend, typingNow == 1);
	}

	@Override
	public void renameNotify(NateFriend friend) {
		// TODO: 친구가 대화명 변경했음.
	}

	@Override
	public void switchboardSessionAbandon(SwitchBoardSession arg0, String arg1) {
	}

	@Override
	public void switchboardSessionEnded(SwitchBoardSession session) {
		// 채팅방 끝났음.
	}

	@Override
	public void switchboardSessionStarted(SwitchBoardSession session) {
		// 채팅방 시작했음.
	}

	@Override
	public void userOffline(NateFriend friend) {
		// 구현할 필요없음. buddyModified() 에서 해결 됨.
	}

	@Override
	public void userOnline(NateFriend friend) {
		// 구현할 필요없음. buddyModified() 에서 해결 됨.
	}

	@Override
	public void whoAddedMe(NateFriend friend) {
		// TODO: 누가 날 친구추가 했음.
	}

	@Override
	public void whoJoinSession(SwitchBoardSession session, NateFriend friend) {
		// TODO: 누가 대화방에 난입?
	}

	@Override
	public void whoPartSession(SwitchBoardSession session, NateFriend friend) {
	}

	@Override
	public void whoRemovedMe(NateFriend friend) {
		// TODO: 누가 날 제거?;; (친구 삭제 말하는건가??)
	}
}
