package wateon;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import kfmes.natelib.NateonMessenger;

public class WateOn {
	private volatile static WateOn uniqueInstance = new WateOn();
	private volatile static ConnectionCloserThread closerThread;
	private Map<String, WateOnUser> users = new HashMap<String, WateOnUser>();
	
	private WateOn() {
	}
	
	public static WateOn getInstance() {
		// 처음 실행하는거면, 쓰레드를 만든다.
		if (closerThread == null) {
			synchronized (WateOn.class) {
				if (closerThread == null) {
					closerThread = new ConnectionCloserThread();
					new Thread(closerThread).start();
				}
			}
		}
		
		// 인스턴스를 넘겨줌.
		return uniqueInstance;
	}
	
	public WateOnUser getWateOnUser(String id) {
		return users.get(id);
	}

	public boolean login(String id, String password, HttpSession session) {
		if (id == null || password == null)
			return false;
		
		NateonMessenger nateOn = new NateonMessenger();
		nateOn.login(id, password);
		
		// 로그인 성공 했으면,
		boolean logged = nateOn.isLoggedIn();
		if (logged) {
			session.setAttribute("id", id);
			
			WateOnUser newUser = new WateOnUser(id, nateOn);
			users.put(id, newUser);
		}
		
		return logged;
	}

	public boolean logout(String id) {
		WateOnUser wateOnUser = users.get(id);
		
		if (wateOnUser == null)
			return false;
		
		else {
			wateOnUser.logout();
			users.remove(id);
			return true;
		}
	}

	public void checkAllConnection() {
		for (WateOnUser user : users.values()) {
			synchronized (user) {
				// 로그인 연결 여부 체크
				if (user.isConnected() == false) {
					this.logout(user.getId());
				}
				// 채팅방들이 닫혔는지 확인.
				user.checkRooms();
			}
		}
	}
}
