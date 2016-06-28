package cn.hsd.college.model;

/**
 * Created by Administrator on 2016/6/14.
 * 指导教师
 */
public class teacher_zhidao {

    private String adviser_name = "";//姓名
    private String adviser_password = "";
    private String adviser_userName = "";//辅导员账号
    private String adviser_major = "";//专业
    private String adviser_college = "";//学院
    private String adviser_email = "";//邮箱


    public String getAdviser_name() {
        return adviser_name;
    }

    public void setAdviser_name(String adviser_name) {
        this.adviser_name = adviser_name;
    }

    public String getAdviser_password() {
        return adviser_password;
    }

    public void setAdviser_password(String adviser_password) {
        this.adviser_password = adviser_password;
    }

    public String getAdviser_userName() {
        return adviser_userName;
    }

    public void setAdviser_userName(String adviser_userName) {
        this.adviser_userName = adviser_userName;
    }

    public String getAdviser_major() {
        return adviser_major;
    }

    public void setAdviser_major(String adviser_major) {
        this.adviser_major = adviser_major;
    }

    public String getAdviser_college() {
        return adviser_college;
    }

    public void setAdviser_college(String adviser_college) {
        this.adviser_college = adviser_college;
    }

    public String getAdviser_email() {
        return adviser_email;
    }

    public void setAdviser_email(String adviser_email) {
        this.adviser_email = adviser_email;
    }
}
