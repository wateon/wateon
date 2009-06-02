package wateon;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import wateon.entity.FriendStatus;
import wateon.entity.InstanceMessage;
import wateon.entity.Message;
import kfmes.natelib.NateonMessenger;
import kfmes.natelib.SwitchBoardSession;
import kfmes.natelib.event.NateListener;

public class WateOnUser {
	private static int sessionNo = 0;

	private NateListener listener;

	private Map<Long, SwitchBoardSession> chatSessions;

	private Queue<InstanceMessage> instanceMessageQ;
	private Queue<Message> messageQ;
	private Queue<FriendStatus> friendStatusQ;

	private String id;
	private NateonMessenger nateOn;

	public WateOnUser(String id, NateonMessenger nateOn) {
		this.id = id;
		this.nateOn = nateOn;
		
		// 리스너 연결
		listener = new WateOnListener(this);
		nateOn.addNateListener(listener);
		
		// 큐, 맵 생성
		chatSessions = new HashMap<Long, SwitchBoardSession>();
		instanceMessageQ = new LinkedList<InstanceMessage>();
		messageQ = new LinkedList<Message>();
		friendStatusQ = new LinkedList<FriendStatus>();
	}
	
	public String getId() {
		return id;
	}

	public NateonMessenger getNateonMessenger() {
		return nateOn;
	}

	public boolean isLogged() {
		return nateOn.isLoggedIn();
	}

	public void removeChatSession(long sessionNo) {
		chatSessions.remove(sessionNo);
	}

	public SwitchBoardSession getChatSession(long sessionNo) {
		return chatSessions.get(sessionNo);
	}

	public long addChatSession(SwitchBoardSession aSession) {
		long session = nextSessionNo();
		chatSessions.put(session, aSession);
		return session;
	}
	
	/**
	 * 새로운 채팅방의 세션 번호를 가져온다.
	 * @return
	 */
	private synchronized static long nextSessionNo() {
		return ++sessionNo;
	}
}
