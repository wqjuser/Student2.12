package cn.hsd.student.activity.model;

public class Qxx_Message {

	private String message_time="";
	private String message_detail="";
	private String message_receiveNo = "";//�����˵�ѧ�Ż�̹���
	private String message_sendNo = "";//�����˵�ѧ�Ż�̹���
	private String message_receiveName = "";//�����˵�ѧ�Ż�̹���
	private String message_sendName = "";//�����˵�ѧ�Ż�̹���
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

	public String getMessage_receiveNo() {
		return message_receiveNo;
	}

	public void setMessage_receiveNo(String message_receiveNo) {
		this.message_receiveNo = message_receiveNo;
	}

	public String getMessage_sendNo() {
		return message_sendNo;
	}

	public void setMessage_sendNo(String message_sendNo) {
		this.message_sendNo = message_sendNo;
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
		return "Qxx_Message [message_time=" + message_time
				+ ", message_detail=" + message_detail + ", message_receiveNo="
				+ message_receiveNo + ", message_sendNo=" + message_sendNo
				+ ", message_receiveName=" + message_receiveName
				+ ", message_sendName=" + message_sendName + "]";
	}

	public Qxx_Message(String message_time, String message_detail,
					   String message_receiveNo, String message_sendNo,
					   String message_receiveName, String message_sendName) {
		super();
		this.message_time = message_time;
		this.message_detail = message_detail;
		this.message_receiveNo = message_receiveNo;
		this.message_sendNo = message_sendNo;
		this.message_receiveName = message_receiveName;
		this.message_sendName = message_sendName;
	}
	public Qxx_Message() {
		super();
		// TODO �Զ����ɵĹ��캯�����
	}


}
