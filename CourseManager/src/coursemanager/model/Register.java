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

//        if (!Validation.isBooleanInt(state)) {
//            throw new IllegalArgumentException();
//        }
        this.state = (mark >= 5) ? 1 : 0;
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
        this.state = (mark >= 5) ? 1 : 0;
    }

    public int getState() {
        return state;
    }
    
    public void displayRegistrationInfor() {
    	System.out.println("Course Code: " + ccode);
    	System.out.println("Student Code: " + scode);
    	System.out.println("Registration date " + bdate);
    	System.out.println("Mark: " + mark);
    	System.out.println("State: " + state);
    }

    
    //Set gan lien vs mark nen se cho vao set mark luon 
//    public void setState(int state) {
//        if (!Validation.isBooleanInt(state)) {
//            throw new IllegalArgumentException();
//        }
//        this.state = state;
//    }
}
