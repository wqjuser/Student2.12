package cn.hsd.student.activity.model;

public class Counselor {
    private String counselor_userName; //����Ա�˺�
    private String counselor_name;

    public String getCounselor_userName() {
        return counselor_userName;
    }

    public void setCounselor_userName(String counselor_userName) {
        this.counselor_userName = counselor_userName;
    }

    public String getCounselor_name() {
        return counselor_name;
    }

    public void setCounselor_name(String counselor_name) {
        this.counselor_name = counselor_name;
    }

    @Override
    public String toString() {
        return "counselor [counselor_userName=" + counselor_userName
                + ", counselor_name=" + counselor_name + "]";
    }

    public Counselor(String counselor_userName, String counselor_name) {
        super();
        this.counselor_userName = counselor_userName;
        this.counselor_name = counselor_name;
    }

    public Counselor() {
        super();
        // TODO �Զ����ɵĹ��캯�����
    }


}
