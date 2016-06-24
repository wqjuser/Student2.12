package cn.hsd.student.activity.model;

/**
 * Created by mona on 2016/5/1.
 */
public class Gxq_apply_student {

    public String getLeave_id() {
        return leave_id;
    }

    public void setLeave_id(String leave_id) {
        this.leave_id = leave_id;
    }

    public String leave_id = "";
    public  String student_name="";
    public String counserlor_name = "";//指导教师
    public  String leave_content="";
    public  String leave_begin="";
    public  String leave_end="";
    public  String leave_check="";
    public String create_time = "";

    public String leave_daynum; //请假天数
    public String leave_type; //请假类型
    public String adviser_name;//辅导员 //................................改动一

    public String getAdviser_name() {
        return adviser_name;
    }

    public void setAdviser_name(String adviser_name) {
        this.adviser_name = adviser_name;
    }


    public String getLeave_daynum() {
        return leave_daynum;
    }

    public void setLeave_daynum(String leave_daynum) {
        this.leave_daynum = leave_daynum;
    }

    public String getLeave_type() {
        return leave_type;
    }

    public void setLeave_type(String leave_type) {
        this.leave_type = leave_type;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }



    public String getLeave_begin() {
        return leave_begin;
    }

    public void setLeave_begin(String leave_begin) {
        this.leave_begin = leave_begin;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getCounserlor_name() {
        return counserlor_name;
    }

    public void setCounserlor_name(String counserlor_name) {
        this.counserlor_name = counserlor_name;
    }

    public String getLeave_content() {
        return leave_content;
    }

    public void setLeave_content(String leave_content) {
        this.leave_content = leave_content;
    }

    public String getLeave_end() {
        return leave_end;
    }

    public void setLeave_end(String leave_end) {
        this.leave_end = leave_end;
    }

    public String getLeave_check() {
        return leave_check;
    }

    public void setLeave_check(String leave_check) {
        this.leave_check = leave_check;
    }



}
