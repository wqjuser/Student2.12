package cn.hsd.school.model;

/**
 * Created by mona on 2016/5/17.校级给院级留言的东西
 */
public class college_notes {

    String message_sendName = ""; //发件人
    String message_detail = ""; //内容
    String message_receiveName= ""; //收件人
    String message_time = "";//时间

    public String getMessage_receiveName() {
        return message_receiveName;
    }

    public void setMessage_receiveName(String message_receiveName) {
        this.message_receiveName = message_receiveName;
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

    public String getMessage_sendName() {
        return message_sendName;
    }

    public void setMessage_sendName(String message_sendName) {
        this.message_sendName = message_sendName;
    }



}
