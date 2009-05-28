package wateon;

import java.util.LinkedList;
import java.util.Queue;

import wateon.entity.FriendStatus;
import wateon.entity.InstanceMessage;
import wateon.entity.Message;
import kfmes.natelib.NateonMessenger;
import kfmes.natelib.event.NateListener;

public class WateOnUser {
	private NateonMessenger messenger;
	private NateListener listener;
	
	private Queue<InstanceMessage> instanceMessageQ;
	private Queue<Message> messageQ;
	private Queue<FriendStatus> friendStatusQ;
	
	private String id;
	private NateonMessenger nateOn;
	
	public WateOnUser(String id, NateonMessenger nateOn) {
		this.id = id;
		this.nateOn = nateOn;
		
		listener = new WateOnListener();
		instanceMessageQ = new LinkedList<InstanceMessage>();
		messageQ = new LinkedList<Message>();
		friendStatusQ = new LinkedList<FriendStatus>();
	}
	
	public NateonMessenger getNateonMessenger() {
		return nateOn;
	}

	public boolean isLogged() {
		return nateOn.isLoggedIn();
	}
}
