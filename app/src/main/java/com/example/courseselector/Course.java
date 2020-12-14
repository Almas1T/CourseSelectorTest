package com.example.courseselector;

public class Course {
    private String cName;
    private double cFees;
    private int cHrs;

    public Course(String cName, double cFees, int cHrs) {
        this.cName = cName;
        this.cFees = cFees;
        this.cHrs = cHrs;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public double getcFees() {
        return cFees;
    }

    public void setcFees(double cFees) {
        this.cFees = cFees;
    }

    public int getcHrs() {
        return cHrs;
    }

    public void setcHrs(int cHrs) {
        this.cHrs = cHrs;
    }
}
