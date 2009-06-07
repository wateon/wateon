package wateon;

import java.util.ArrayList;
import java.util.Properties;

import wateon.entity.ChatRoom;
import wateon.entity.Message;

import kfmes.natelib.SwitchBoardSession;
import kfmes.natelib.entity.GroupList;
import kfmes.natelib.entity.NateFile;
import kfmes.natelib.entity.NateFriend;
import kfmes.natelib.event.NateListener;
import kfmes.natelib.ftp.FileRecver;
import kfmes.natelib.ftp.FileSender;
import kfmes.natelib.msg.InstanceMessage;
import kfmes.natelib.msg.MimeMessage;
import kfmes.natelib.util.MsgUtil;

public class WateOnListener implements NateListener {
	private WateOnUser self;

	public WateOnListener(WateOnUser wateOnUser) {
		this.self = wateOnUser;
	}

	@Override
	public void OwnerStatusUpdated() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addFailed(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void allListUpdated() {
		// TODO Auto-generated method stub

	}

	@Override
	public void buddyListInit(GroupList arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buddyListModified() {
		// TODO Auto-generated method stub

	}

	@Override
	public void buddyModified(NateFriend arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void chatMessageReceived(SwitchBoardSession session,
			NateFriend other, MimeMessage msg) {
		
		String targetId = other.getID();
		
		// 어느 채팅방인지 찾아낸다.
		ChatRoom room = self.getChatRoom(other.getID());
		
		// 없으면, 리턴.
		if (room == null)
			return;
		
		
		// 일반 메시지..
		if (msg.getType().equals("MSG")) {
			// 메시지 목록에 추가해준다.
			String nick = MsgUtil.getRealString(other.getNickName());
			String m = MsgUtil.getRealString(msg.getMessage());
			room.addNewMessage(new Message(targetId, nick, m));
		}
		
		// 상대방이 창을 닫았음! -_-;;
		else if (msg.getType().equals("QUIT")) {
			self.closeChatRoom(targetId);
		}
		
		// 그 밖의 경우 -_-;;
		else {
			System.out.println("TYPE = " + msg.getType());
		}
	}

	@Override
	public void disConnected() {
		// TODO Auto-generated method stub

	}

	@Override
	public void filePosted(SwitchBoardSession arg0, FileRecver arg1,
			NateFriend arg2, ArrayList<NateFile> arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fileRecved(FileRecver arg0, NateFile arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fileSend(SwitchBoardSession arg0, FileSender arg1,
			NateFriend arg2, ArrayList<NateFile> arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fileSendAccepted(SwitchBoardSession arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fileSendEnded(FileSender arg0, NateFile arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fileSendError(FileSender arg0, Throwable arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fileSendRejected(SwitchBoardSession arg0, String arg1,
			String arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fileSendStarted(FileSender arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fileTransferAborted(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void instanceMessageReceived(InstanceMessage arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void killed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void listAdd(NateFriend arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void listOnline(NateFriend arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loginComplete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loginError(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void logoutNotify() {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyUnreadMail(Properties arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void progressTyping(SwitchBoardSession arg0, NateFriend arg1,
			int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void renameNotify(NateFriend arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void switchboardSessionAbandon(SwitchBoardSession arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void switchboardSessionEnded(SwitchBoardSession arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void switchboardSessionStarted(SwitchBoardSession arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void userOffline(NateFriend arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void userOnline(NateFriend arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void whoAddedMe(NateFriend arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void whoJoinSession(SwitchBoardSession arg0, NateFriend arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void whoPartSession(SwitchBoardSession arg0, NateFriend arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void whoRemovedMe(NateFriend arg0) {
		// TODO Auto-generated method stub

	}

}
