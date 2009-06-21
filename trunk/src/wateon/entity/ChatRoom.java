package wateon.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import kfmes.natelib.SwitchBoardSession;
import kfmes.natelib.entity.NateFriend;
import kfmes.natelib.util.MsgUtil;

public class ChatRoom {
	private static final int CHATROOM_KEEPALIVE = 1500;	// 핑이 1.5초동안 없어도 채팅방 유지
	
	private String targetId;
	private List<Message> receivedMessageQ;
	private SwitchBoardSession session;
	private Date lasttime;

	private String typingMessage = "";

	public ChatRoom(String targetId, SwitchBoardSession session) {
		receivedMessageQ = new LinkedList<Message>();
		this.targetId = targetId;
		this.session = session;
		lasttime = new Date();
	}

	public String getTargetId() {
		return targetId;
	}

	public List<Message> getReceivedMessageQ() {
		return receivedMessageQ;
	}

	/**
	 * 채팅방을 닫는다. (반드시, WateOnUser 를 통해서 호출되야 함.)
	 */
	public synchronized void close() {
		receivedMessageQ.clear();
		session.close();
	}

	/**
	 * 한 개의 새 메시지를 추가한다.
	 * @param msg 새 메시지
	 */
	public synchronized void addNewMessage(Message msg) {
		receivedMessageQ.add(msg);
		addTyping(null, false);
	}

	/**
	 * 모든 메시지를 가져오고나서, 저장되어있던 메시지를 비운다.
	 * @return 그동안 쌓인 모든 메시지
	 */
	public synchronized List<Message> getAllMessages() {
		List<Message> queue = receivedMessageQ;
		receivedMessageQ = new LinkedList<Message>();
		return queue;
	}

	public boolean sendMessage(String message) {
		return session.sendMessage(message);
	}

	public boolean isConnected() {
		Date now = new Date();
		long diff = now.getTime() - lasttime.getTime();
		
		//System.out.println("채팅방 : " + diff);
		
		// 마지막 체크 후, ?초 이내인지 확인.
		return session.isConnected() && diff < CHATROOM_KEEPALIVE;
	}

	public void updateTime() {
		lasttime = new Date();
	}

	public void addTyping(NateFriend friend, boolean typing) {
		if (typing)
			typingMessage = "글을 쓰고 있습니다: " + MsgUtil.getRealString(friend.getNickName());
		else
			typingMessage = "";
	}

	public String getTypingMessage() {
		return typingMessage;
	}
}
