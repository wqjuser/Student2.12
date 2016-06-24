package cn.hsd.Counsellor.modle;

public class Qxx_Leave {

    private String leave_id = "";
    private String student_name = "";
    private String student_no = "";
    private String counselor_name = "";
    private String counselor_no = "";
    private String adviser_name = "δ��д";
    private String adviser_no = "";
    private String leave_daynum = "";
    private String leave_check = "δ����";
    private String leave_type = "δ��д";
    private String leave_content = "δ��д";
    private String leave_begin = "";
    private String leave_end = "";
    private String create_time = "";

    public String getLeave_id() {
        return leave_id;
    }

    public void setLeave_id(String leave_id) {
        this.leave_id = leave_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_no() {
        return student_no;
    }

    public void setStudent_no(String student_no) {
        this.student_no = student_no;
    }

    public String getCounselor_name() {
        return counselor_name;
    }

    public void setCounselor_name(String counselor_name) {
        this.counselor_name = counselor_name;
    }

    public String getCounselor_no() {
        return counselor_no;
    }

    public void setCounselor_no(String counselor_no) {
        this.counselor_no = counselor_no;
    }

    public String getAdviser_name() {
        return adviser_name;
    }

    public void setAdviser_name(String adviser_name) {
        this.adviser_name = adviser_name;
    }

    public String getAdviser_no() {
        return adviser_no;
    }

    public void setAdviser_no(String adviser_no) {
        this.adviser_no = adviser_no;
    }

    public String getLeave_daynum() {
        return leave_daynum;
    }

    public void setLeave_daynum(String leave_daynum) {
        this.leave_daynum = leave_daynum;
    }

    public String getLeave_check() {
        return leave_check;
    }

    public void setLeave_check(String leave_check) {
        this.leave_check = leave_check;
    }

    public String getLeave_type() {
        return leave_type;
    }

    public void setLeave_type(String leave_type) {
        this.leave_type = leave_type;
    }

    public String getLeave_content() {
        return leave_content;
    }

    public void setLeave_content(String leave_content) {
        this.leave_content = leave_content;
    }

    public String getLeave_begin() {
        return leave_begin;
    }

    public void setLeave_begin(String leave_begin) {
        this.leave_begin = leave_begin;
    }

    public String getLeave_end() {
        return leave_end;
    }

    public void setLeave_end(String leave_end) {
        this.leave_end = leave_end;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "Qxx_Leave [leave_id=" + leave_id + ", student_name="
                + student_name + ", student_no=" + student_no
                + ", counselor_name=" + counselor_name + ", counselor_no="
                + counselor_no + ", adviser_name=" + adviser_name
                + ", adviser_no=" + adviser_no + ", leave_daynum="
                + leave_daynum + ", leave_check=" + leave_check
                + ", leave_type=" + leave_type + ", leave_content="
                + leave_content + ", leave_begin=" + leave_begin
                + ", leave_end=" + leave_end + ", create_time=" + create_time
                + "]";
    }

    public Qxx_Leave(String leave_id, String student_name, String student_no,
                     String counselor_name, String counselor_no, String adviser_name,
                     String adviser_no, String leave_daynum, String leave_check,
                     String leave_type, String leave_content, String leave_begin,
                     String leave_end, String create_time) {
        super();
        this.leave_id = leave_id;
        this.student_name = student_name;
        this.student_no = student_no;
        this.counselor_name = counselor_name;
        this.counselor_no = counselor_no;
        this.adviser_name = adviser_name;
        this.adviser_no = adviser_no;
        this.leave_daynum = leave_daynum;
        this.leave_check = leave_check;
        this.leave_type = leave_type;
        this.leave_content = leave_content;
        this.leave_begin = leave_begin;
        this.leave_end = leave_end;
        this.create_time = create_time;
    }

    public Qxx_Leave() {
        super();
        // TODO Auto-generated constructor stub
    }


}
