package cn.hsd.college.model;

/**
 * Created by Administrator on 2016/6/14.
 * 辅导员
 */
public class teacher_leader {

    private String counselor_name = "";//姓名
    private String counselor_password = "";
    private String counselor_userName = "";//辅导员账号
    private String counselor_major = "";//专业
    private String counselor_college = "";//学院
    private String counselor_email = "";//邮箱


    public String getCounselor_name() {
        return counselor_name;
    }

    public void setCounselor_name(String counselor_name) {
        this.counselor_name = counselor_name;
    }

    public String getCounselor_password() {
        return counselor_password;
    }

    public void setCounselor_password(String counselor_password) {
        this.counselor_password = counselor_password;
    }

    public String getCounselor_userName() {
        return counselor_userName;
    }

    public void setCounselor_userName(String counselor_userName) {
        this.counselor_userName = counselor_userName;
    }

    public String getCounselor_major() {
        return counselor_major;
    }

    public void setCounselor_major(String counselor_major) {
        this.counselor_major = counselor_major;
    }

    public String getCounselor_college() {
        return counselor_college;
    }

    public void setCounselor_college(String counselor_college) {
        this.counselor_college = counselor_college;
    }

    public String getCounselor_email() {
        return counselor_email;
    }

    public void setCounselor_email(String counselor_email) {
        this.counselor_email = counselor_email;
    }
}
