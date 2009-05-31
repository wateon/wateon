package wateon;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import kfmes.natelib.NateonMessenger;

public class WateOn {
	private static WateOn uniqueInstance = new WateOn();
	private Map<String, WateOnUser> users = new HashMap<String, WateOnUser>();
	
	private WateOn() {
	}
	
	public static WateOn getInstance() {
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
			try {
				NateonMessenger msger = wateOnUser.getNateonMessenger();
				if (msger != null && msger.isLoggedIn())
					msger.logout();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			users.remove(id);
			return true;
		}
	}
}
