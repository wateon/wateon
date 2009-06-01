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
	private NateListener listener;

	private Map<String, SwitchBoardSession> chatSessions;

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
		chatSessions = new HashMap<String, SwitchBoardSession>();
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

	public void removeChatSession(String session) {
		chatSessions.remove(session);
	}

	public SwitchBoardSession getChatSession(String session) {
		return chatSessions.get(session);
	}

	public void addChatSession(String session, SwitchBoardSession aSession) {
		chatSessions.put(session, aSession);
	}
}
