package cn.hsd.school.model;

/**
 * Created by mona on 2016/5/16.
 */
public class college_message {

    String   c_admin_onlynumber= "" ; //院级管理工号
    String   c_admin_name= "" ;//姓名
    String  c_admin_password= ""; //密码
    String  c_admin_college= "";//学院

    public String getC_admin_onlynumber() {
        return c_admin_onlynumber;
    }

    public void setC_admin_onlynumber(String c_admin_onlynumber) {
        this.c_admin_onlynumber = c_admin_onlynumber;
    }



    public String getC_admin_password() {
        return c_admin_password;
    }

    public void setC_admin_password(String c_admin_password) {
        this.c_admin_password = c_admin_password;
    }

    public String getC_admin_college() {
        return c_admin_college;
    }

    public void setC_admin_college(String c_admin_college) {
        this.c_admin_college = c_admin_college;
    }

    public String getC_admin_name() {
        return c_admin_name;
    }

    public void setC_admin_name(String c_admin_name) {
        this.c_admin_name = c_admin_name;
    }



}
