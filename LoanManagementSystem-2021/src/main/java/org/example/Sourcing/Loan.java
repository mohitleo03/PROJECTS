package org.example.Sourcing;

public class Loan {

    String type;
    private int duration;
    private double amount;
    private double roi;
    private int score;

    public Loan(String type, int duration, double amount, double roi, int score) {
        this.type = type;
        this.duration = duration;
        this.amount = amount;
        this.roi = roi;
        this.score = score;
    }

    public String getType() {
        return type;
    }
    public void setType(String type)
    {
        this.type = type;
    }


    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRoi() {
        return roi;
    }

    public void setRoi(double roi) {
        this.roi = roi;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
