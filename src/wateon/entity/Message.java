package wateon.entity;

public class Message {
	private String id;
	private String nick;
	private String message;
	
	public Message(String id, String nick, String message) {
		this.id = id;
		this.nick = nick;
		this.message = message;
	}
	
	public String getId() {
		return id;
	}

	public String getNick() {
		return nick;
	}

	public String getMessage() {
		return message;
	}
}
