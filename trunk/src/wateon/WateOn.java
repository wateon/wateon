package wateon;

import java.util.HashMap;
import java.util.Map;

public class WateOn {
	private WateOn uniqueInstance = new WateOn();
	private Map<String, WateOnUser> users = new HashMap<String, WateOnUser>();
	
	private WateOn() {
	}
	
	public WateOn getInstance() {
		return uniqueInstance;
	}
	
	public WateOnUser getWateOnUser(String id) {
		return null;
	}
	
	public boolean login(String id, String password) {
		return false;
	}
	
	public boolean logout(String id) {
		return false;
	}
}
