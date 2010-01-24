package wateon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wateon.entity.ChatRoom;
import kfmes.natelib.NateonMessenger;
import kfmes.natelib.SwitchBoardSession;
import kfmes.natelib.entity.NateFriend;
import kfmes.natelib.event.NateListener;
import kfmes.natelib.msg.InstanceMessage;


public class WateOnUser {
	private static final int LOGIN_KEEPALIVE = 5000;	// 인터넷 끊긴지 5초동안은 로그인 유지.

	private NateListener listener;

	private Map<String, ChatRoom> chatRooms;

	private List<InstanceMessage> instanceMessageQ;
	private List<NateFriend> friendModifiedQ;
	private List<String> receivedChatRoomQ;

	private String id;
	private NateonMessenger nateOn;

	private Date lastdate;

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
		chatRooms = new HashMap<String, ChatRoom>();
		instanceMessageQ = new ArrayList<InstanceMessage>();
		friendModifiedQ = new ArrayList<NateFriend>();
		receivedChatRoomQ = new ArrayList<String>();
		
		// 생성된 시간을 넣어줌.
		this.lastdate = new Date();
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
	public void closeChatRoom(String targetId) {
		if (hasChatRoom(targetId) == false)
			return;
		
		getChatRoom(targetId).close();
		chatRooms.remove(targetId);
	}

	/**
	 * 해당 대화상대와 열린 채팅방이 있는지 확인한다.
	 * @param targetId 대화상대 아이디
	 * @return 채팅방이 열려있는지 여부
	 */
	public boolean hasChatRoom(String targetId) {
		return chatRooms.containsKey(targetId);
	}

	/**
	 * 대화상대 아이디로, 실제 채팅방 인스턴스를 가져온다.
	 * @param targetId 대화상대 아이디
	 * @return 채팅방 인스턴스
	 */
	public ChatRoom getChatRoom(String targetId) {
		return chatRooms.get(targetId);
	}

	/**
	 * 대화상대 아이디로 새로운 채팅방을 만든다. 이미 있거나, 안 만들어지면 실패.
	 * @param targetId 상대방의 네이트 아이디
	 * @return 생성 되었는지 여부
	 */
	public boolean createNewChatSession(String targetId) {
		// 이미 있으면, 실패
		if (hasChatRoom(targetId))
			return false;
		
		// 상대방 아이디를 이용해서, 채팅방을 만들어주고,
		SwitchBoardSession chatSession = getNateonMessenger().getNS().procOpenChat(getId(), targetId);
		
		// 안 만들어졌으면, 실패.
		if (chatSession == null)
			return false;
		
		// 생성된 채팅방 인스턴스를 목록에 추가한다.
		chatRooms.put(targetId, new ChatRoom(this, targetId, chatSession));
		return true;
	}

	/**
	 * 받은 쪽지를 하나 추가한다.
	 * @param imessage
	 */
	public void addInstanceMessage(InstanceMessage imessage) {
		synchronized (instanceMessageQ) {
			instanceMessageQ.add(imessage);
		}
	}

	/**
	 * 지금까지 쌓인 모든 쪽지를 가져온다.
	 * @return 쪽지 객체들의 리스트
	 */
	public List<InstanceMessage> getAllInstanceMessages() {
		synchronized (instanceMessageQ) {
			List<InstanceMessage> imessages = instanceMessageQ;
			instanceMessageQ = new ArrayList<InstanceMessage>();
			return imessages;
		}
	}

	/**
	 * 친구의 변동사항 (대화명 변경 같은거)을 하나 추가 한다.
	 * @param status
	 */
	public void addFriendModified(NateFriend friend) {
		synchronized (friendModifiedQ) {
			friendModifiedQ.add(friend);
		}
	}

	/**
	 * 지금까지 쌓인 친구의 변동사항들을 모두 가져온다.
	 * @return 변동된 친구 객체 목록
	 */
	public List<NateFriend> getAllFriendModified() {
		synchronized (friendModifiedQ) {
			List<NateFriend> statuses = friendModifiedQ;
			friendModifiedQ = new ArrayList<NateFriend>();
			return statuses;
		}
	}

	/**
	 * 상대방이 먼저 말을 걸어서 만들어진 채팅방의 방 이름 (상대방 아이디) 를 넣어놓는다.
	 * @param targetId 상대방 네이트온 아이디
	 */
	public void addNewReceivedChatRoom(String targetId) {
		synchronized (receivedChatRoomQ) {
			receivedChatRoomQ.add(targetId);
		}
	}

	/**
	 * 나에게 말을 걸었는데, 아직 안 열린 채팅방들의 이름들을 모두 가져온다.
	 * @return 채팅방들의 이름 목록.
	 */
	public List<String> getAllReceivedChatRooms() {
		synchronized (receivedChatRoomQ) {
			List<String> targets = receivedChatRoomQ;
			receivedChatRoomQ = new ArrayList<String>();
			return targets;
		}
	}

	/**
	 * 해당 채팅 세션과 해당 상대방 아이디를 이어서, 채팅방 객체에 담아준다.
	 * @param targetId
	 * @param session
	 * @return
	 */
	public ChatRoom setChatSession(String targetId, SwitchBoardSession session) {
		if (hasChatRoom(targetId) == false)
			chatRooms.put(targetId, new ChatRoom(this, targetId, session));
		return getChatRoom(targetId);
	}

	/**
	 * 채팅방을 모두 닫아주고, 실제로 로그아웃을 한다. 
	 */
	public void logout() {
		// 열려진 채팅방을 모두 닫는다.
		for (ChatRoom room : chatRooms.values())
			room.close();
		
		// 실제로, 네이트온에서 로그아웃 한다.
		try {
			NateonMessenger msger = this.getNateonMessenger();
			if (msger != null && msger.isLoggedIn())
				msger.logout();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isConnected() {
		Date now = new Date();
		long diff = now.getTime() - lastdate.getTime();
		
		//System.out.println("[" + id + "] diff = " + diff);
		
		// 마지막 체크 후, 특정 시간 이내인지 확인.
		return diff < LOGIN_KEEPALIVE;
	}

	public void checkRooms() {
		for (ChatRoom room : chatRooms.values()) { 
			synchronized (this.chatRooms) {
				if (room.isConnected() == false) {
					this.closeChatRoom(room.getTargetId());
				}
			}
		}
	}

	public void updateTime() {
		lastdate = new Date();
	}
}
