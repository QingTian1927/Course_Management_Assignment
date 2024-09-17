package coursemanager.model;

import coursemanager.util.Validation;

import java.util.Date;

public class Register {
    private String ccode;  // Course Code
    private String scode;  // Student Code
    private Date bdate;  // Registration Date;
    private double mark;
    private int state;

    // TODO: liên hệ mark với state (mark >= 5, state = 1)

    private boolean isValidMark(double mark) {
        return 0 <= mark && mark <= 10;
    }

    public Register(String ccode, String scode, Date bdate, double mark, int state) {
        this.ccode = ccode;
        this.scode = scode;
        this.bdate = bdate;

        if (!isValidMark(mark)) {
            throw new IllegalArgumentException();
        }
        this.mark = mark;

        if (!Validation.isBooleanInt(state)) {
            throw new IllegalArgumentException();
        }
        this.state = state;
    }

    public Register() {}

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
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        if (!Validation.isBooleanInt(state)) {
            throw new IllegalArgumentException();
        }
        this.state = state;
    }
}
