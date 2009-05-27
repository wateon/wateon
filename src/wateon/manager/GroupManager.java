package wateon.manager;

import kfmes.natelib.NateonMessenger;

public class GroupManager {
	private NateonMessenger messenger;

	public GroupManager(NateonMessenger messenger) {
		this.messenger = messenger;
	}
	
	public boolean addGroup(String name) {
		return false;
	}
	
	public boolean removeGroup(String name) {
		return false;
	}
	
	public boolean modifyGroup(String from, String to) {
		return false;
	}
}
