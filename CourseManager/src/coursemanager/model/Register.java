package coursemanager.model;

import java.util.Date;

public class Register {
    private String ccode;  // Course Code
    private String scode;  // Student Code
    private Date bdate;  // Registration Date;
    private double mark;
    private int state;

    private boolean isValidMark(double mark) {
        return 0 <= mark && mark <= 10;
    }

    public Register() {}

    public Register(String ccode, String scode, Date bdate, double mark, int state) {
        this.ccode = ccode;
        this.scode = scode;
        this.bdate = bdate;

        if (!isValidMark(mark)) {
            throw new IllegalArgumentException();
        }
        this.mark = mark;

        this.state = (mark >= 5) ? 1 : 0;
    }

    public Register(Register register) {
        this(register.ccode, register.scode, (Date) register.bdate.clone(), register.mark, register.state);
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public String getScode() {
        return scode;
    }

    public void setScode(String scode) {
        this.scode = scode;
    }

    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        if (!isValidMark(mark)) {
            throw new IllegalArgumentException();
        }

        this.mark = mark;
        this.state = (mark >= 5) ? 1 : 0;
    }

    public int getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Register{" +
                "ccode='" + ccode + '\'' +
                ", scode='" + scode + '\'' +
                ", bdate=" + bdate +
                ", mark=" + mark +
                ", state=" + state +
                '}';
    }

    public void displayRegistrationInfo() {
        System.out.println("Course Code: " + ccode);
        System.out.println("Student Code: " + scode);
        System.out.println("Registration date: " + bdate);
        System.out.println("Mark: " + mark);
        System.out.println("State: " + state);
    }

    public String toDataString() {
        return String.format(
                "%s, %s, %s, %f, %d",
                this.ccode,
                this.scode,
                this.bdate.toString(),
                this.mark,
                this.state
        );
    }
}
