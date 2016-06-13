package cn.hsd.student.activity.model;

public class Qxx_Message {

	private String message_id="";
	private String message_time="";
	private String message_detail="";
	private String message_receiveName="";
	private String message_sendName="";
	public String getMessage_id() {
		return message_id;
	}
	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}
	public String getMessage_time() {
		return message_time;
	}
	public void setMessage_time(String message_time) {
		this.message_time = message_time;
	}
	public String getMessage_detail() {
		return message_detail;
	}
	public void setMessage_detail(String message_detail) {
		this.message_detail = message_detail;
	}
	public String getMessage_receiveName() {
		return message_receiveName;
	}
	public void setMessage_receiveName(String message_receiveName) {
		this.message_receiveName = message_receiveName;
	}
	public String getMessage_sendName() {
		return message_sendName;
	}
	public void setMessage_sendName(String message_sendName) {
		this.message_sendName = message_sendName;
	}
	@Override
	public String toString() {
		return "Qxx_Message [message_id=" + message_id + ", message_time="
				+ message_time + ", message_detail=" + message_detail
				+ ", message_receiveName=" + message_receiveName
				+ ", message_sendName=" + message_sendName + "]";
	}
	public Qxx_Message(String message_time,
			String message_detail, String message_receiveName,
			String message_sendName) {
		super();
		this.message_time = message_time;
		this.message_detail = message_detail;
		this.message_receiveName = message_receiveName;
		this.message_sendName = message_sendName;
	}
	public Qxx_Message() {
		super();
		// TODO �Զ����ɵĹ��캯�����
	}
	
}
