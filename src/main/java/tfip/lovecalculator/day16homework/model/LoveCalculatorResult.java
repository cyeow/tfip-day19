package tfip.lovecalculator.day16homework.model;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class LoveCalculatorResult extends LoveCalculatorInput {

    private Integer percentage;
    private String result;

    public LoveCalculatorResult() {
        super();
    }

    public LoveCalculatorResult(String fname, String sname, Integer percentage, String result) {
        super(fname, sname);
        this.percentage = percentage;
        this.result = result;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "LoveCalculatorResult [fname=" + fname + ", sname=" + sname + ", percentage=" + percentage + ", result="
                + result + "]";
    }

    public static LoveCalculatorResult createFromJSON(JsonObject o) {
        String fname = o.getString("fname");
        String sname = o.getString("sname");
        Integer percentage = Integer.parseInt(o.getString("percentage"));
        String result = o.getString("result");

        return new LoveCalculatorResult(fname, sname, percentage, result);
    }

    public static LoveCalculatorResult createFromJSON(String json) {
        JsonReader r = Json.createReader(new StringReader(json));
        return createFromJSON(r.readObject());
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("fname", this.getFname())
                .add("sname", this.getSname())
                .add("percentage", this.getPercentage())
                .add("result", this.getResult())
                .build();
    }
}
