package wateon;

import java.util.LinkedList;
import java.util.Queue;
import javax.servlet.http.HttpSession;

import wateon.entity.FriendStatus;
import wateon.entity.InstanceMessage;
import wateon.entity.Message;
import kfmes.natelib.NateonMessenger;
import kfmes.natelib.event.NateListener;

public class WateOnUser {
	private NateonMessenger messenger;
	private NateListener listener;
	private HttpSession session;
	
	private Queue<InstanceMessage> instanceMessageQ;
	private Queue<Message> messageQ;
	private Queue<FriendStatus> friendStatusQ;
	
	private String id;
	private String password;
	
	public WateOnUser(String id, String password) {
		this.id = id;
		this.password = password;
		
		listener = new WateOnListener();
		instanceMessageQ = new LinkedList<InstanceMessage>();
		messageQ = new LinkedList<Message>();
		friendStatusQ = new LinkedList<FriendStatus>();
	}
}
