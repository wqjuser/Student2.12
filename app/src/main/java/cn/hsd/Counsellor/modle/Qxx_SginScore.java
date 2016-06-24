package cn.hsd.Counsellor.modle;

public class Qxx_SginScore {

    private String student_no;
    private String student_SginScore;//ѧ�����ڳɼ�

    public String getStudent_no() {
        return student_no;
    }

    public void setStudent_no(String student_no) {
        this.student_no = student_no;
    }

    public String getStudent_SginScore() {
        return student_SginScore;
    }

    public void setStudent_SginScore(String student_SginScore) {
        this.student_SginScore = student_SginScore;
    }

    public Qxx_SginScore(String student_no, String student_SginScore) {
        super();
        this.student_no = student_no;
        this.student_SginScore = student_SginScore;
    }

    @Override
    public String toString() {
        return "Student_Sginscore [student_no=" + student_no
                + ", student_SginScore=" + student_SginScore + "]";
    }

    public Qxx_SginScore() {
        super();
        // TODO �Զ����ɵĹ��캯�����
    }


}
