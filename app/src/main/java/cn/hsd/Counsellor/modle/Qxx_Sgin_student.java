package cn.hsd.Counsellor.modle;

public class Qxx_Sgin_student {
    private String student_name = "";
    private String student_no = "";
    private String sgin_num = "";

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

    public String getSgin_num() {
        return sgin_num;
    }

    public void setSgin_num(String sgin_num) {
        this.sgin_num = sgin_num;
    }

    @Override
    public String toString() {
        return "Qxx_Sgin_student [student_name=" + student_name
                + ", student_no=" + student_no + ", sgin_num=" + sgin_num + "]";
    }

    public Qxx_Sgin_student(String student_name, String student_no,
                            String sgin_num) {
        super();
        this.student_name = student_name;
        this.student_no = student_no;
        this.sgin_num = sgin_num;
    }

    public Qxx_Sgin_student() {
        super();
        // TODO Auto-generated constructor stub
    }


}
