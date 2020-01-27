package ru.softlab.loancalc.entity;

public class LoanRequest {

    private long amount;
    private double interestRate;
    private int termInMonths;

    public LoanRequest() {
    }

    public LoanRequest(long amount, double interestRate, int termInMonths) {
        this.amount = amount;
        this.interestRate = interestRate;
        this.termInMonths = termInMonths;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getTermInMonths() {
        return termInMonths;
    }

    public void setTermInMonths(int termInMonths) {
        this.termInMonths = termInMonths;
    }
}
