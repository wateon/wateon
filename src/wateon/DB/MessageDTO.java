package wateon.DB;

public class MessageDTO {

	private int number;
	private String sender;
	private String receiver;
	private String message;
	private String date;
	
	public MessageDTO(int number, String sender, String receiver, String message, String date) {
		this.setNumber(number);
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.date = date;
	}

	public MessageDTO(String sender, String receiver, String message) {
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
	}

	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getNumber() {
		return number;
	}
}
