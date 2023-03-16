package tfip.lovecalculator.day16homework.model;

public class LoveCalculatorInput {
    protected String fname;
    protected String sname;

    public LoveCalculatorInput() {
    }

    public LoveCalculatorInput(String fname, String sname) {
        this.fname = fname;
        this.sname = sname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

}
