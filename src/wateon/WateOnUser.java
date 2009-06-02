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
	private static int nextSessionNo = 0;

	private NateListener listener;

	private Map<String, SwitchBoardSession> chatSessions;

	private Queue<InstanceMessage> instanceMessageQ;
	private Queue<Message> messageQ;
	private Queue<FriendStatus> friendStatusQ;

	private String id;
	private NateonMessenger nateOn;

	/**
	 * WateOnUser 생성자
	 * @param id 네이트 아이디
	 * @param nateOn (로그인된) 네이트온 메신저 인스턴스
	 */
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
	
	/**
	 * 로그인할 때 썼던 네이트 아이디를 가져온다.
	 * @return 네이트 아이디
	 */
	public String getId() {
		return id;
	}

	/**
	 * 네이트온 라이브러리의 인스턴스를 가져온다.
	 * @return 네이트온 메신저 인스턴스
	 */
	public NateonMessenger getNateonMessenger() {
		return nateOn;
	}

	/**
	 * 로그인 되었는지 확인한다.
	 * @return 로그인 여부
	 */
	public boolean isLogged() {
		return nateOn != null && nateOn.isLoggedIn();
	}

	/**
	 * 해당 대화상대와의 채팅방을 닫는다.
	 * @param targetId 대화상대 아이디
	 */
	public void closeChatSession(String targetId) {
		if (hasChatSession(targetId) == false)
			return;
		
		getChatSession(targetId).close();
		chatSessions.remove(targetId);
	}

	/**
	 * 해당 대화상대와 열린 채팅방이 있는지 확인한다.
	 * @param targetId 대화상대 아이디
	 * @return 채팅방이 열려있는지 여부
	 */
	public boolean hasChatSession(String targetId) {
		return chatSessions.containsKey(targetId);
	}

	/**
	 * 대화상대 아이디로, 실제 채팅방 인스턴스를 가져온다.
	 * @param targetId 대화상대 아이디
	 * @return 채팅방 인스턴스
	 */
	public SwitchBoardSession getChatSession(String targetId) {
		return chatSessions.get(targetId);
	}

	/**
	 * 대화상대 아이디로 새로운 채팅방을 만든다. 이미 있거나, 안 만들어지면 실패.
	 * @param targetId 상대방의 네이트 아이디
	 * @return 생성 되었는지 여부
	 */
	public boolean createNewChatSession(String targetId) {
		// 이미 있으면, 실패
		if (hasChatSession(targetId))
			return false;
		
		// 상대방 아이디를 이용해서, 채팅방을 만들어주고,
		SwitchBoardSession chatSession = getNateonMessenger().getNS().procOpenChat(getId(), targetId);
		
		// 안 만들어졌으면, 실패.
		if (chatSession == null)
			return false;
		
		// 생성된 채팅방 인스턴스를 목록에 추가한다.
		addChatSession(targetId, chatSession);
		return true;
	}

	/**
	 * 새로운 채팅방 세션을 생성한다.
	 * @param targetId
	 * @param chatRoom
	 */
	private void addChatSession(String targetId, SwitchBoardSession aSession) {
		chatSessions.put(targetId, aSession);
	}
}
