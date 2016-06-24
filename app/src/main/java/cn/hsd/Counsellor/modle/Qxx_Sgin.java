package cn.hsd.Counsellor.modle;

public class Qxx_Sgin {
    private String sign_time = "";//ǩ��ʱ��
    private String sign_address = ""; //ǩ���ص�
    private String student_no = "";//ѧ��ѧ��
    private String counselor_name; //����ԱID

    public String getSign_time() {
        return sign_time;
    }

    public void setSign_time(String sign_time) {
        this.sign_time = sign_time;
    }

    public String getSign_address() {
        return sign_address;
    }

    public void setSign_address(String sign_address) {
        this.sign_address = sign_address;
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

    @Override
    public String toString() {
        return "Qxx_Sgin [sign_time=" + sign_time + ", sign_address="
                + sign_address + ", student_no=" + student_no
                + ", counselor_name=" + counselor_name + "]";
    }

    public Qxx_Sgin(String sign_time, String sign_address, String student_no,
                    String counselor_name) {
        super();
        this.sign_time = sign_time;
        this.sign_address = sign_address;
        this.student_no = student_no;
        this.counselor_name = counselor_name;
    }

    public Qxx_Sgin() {
        super();
        // TODO �Զ����ɵĹ��캯�����
    }


}
