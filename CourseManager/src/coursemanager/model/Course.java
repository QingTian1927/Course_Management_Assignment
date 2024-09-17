package coursemanager.model;

import coursemanager.util.Validation;

public class Course {
    private String ccode;  // Course Code
    private String scode;  // Subject Code
    private String sname;  // Subject Name
    private String semester;
    private String year;
    private int seats;
    private int registered;
    private double price;

    private boolean isValidRegistered(int seats, int registered) {
        return registered <= seats;
    }

    public Course(String ccode, String scode, String sname, String semester, String year, int seats, int registered, double price) {
        this.ccode = ccode;
        this.scode = scode;
        this.sname = sname;
        this.semester = semester;
        this.year = year;

        if (!isValidRegistered(seats, registered)) {
            throw new IllegalArgumentException();
        }

        if (!Validation.isNonNegative(price)) {
            throw new IllegalArgumentException();
        }

        this.price = price;
        this.seats = seats;
        this.registered = registered;
    }

    public Course() {}

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

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        if (Validation.isNonNegative(seats)) {
            throw new IllegalArgumentException();
        }
        this.seats = seats;
    }

    public int getRegistered() {
        return registered;
    }

    public void setRegistered(int registered) {
        if (Validation.isNonNegative(registered)) {
            throw new IllegalArgumentException();
        }
        if (!isValidRegistered(this.seats, registered)) {
            throw new IllegalArgumentException();
        }
        this.registered = registered;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (!Validation.isNonNegative(price)) {
            throw new IllegalArgumentException();
        }
        this.price = price;
    }
}
