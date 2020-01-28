package ru.softlab.loancalc.entity;

public class Payment {

    private int number;                 // Номер платежа
    private String monthOfYear;                  // Месяц/год
    private double principalPayment;    // Платеж по основному долгу
    private double interestPayment;     // Платеж по процентам
    private double balance;    // Остаток основного долга
    private double totalPayment;        // Общая сумма платежа

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getMonthOfYear() {
        return monthOfYear;
    }

    public void setMonthOfYear(String monthOfYear) {
        this.monthOfYear = monthOfYear;
    }

    public double getPrincipalPayment() {
        return principalPayment;
    }

    public void setPrincipalPayment(double principalPayment) {
        this.principalPayment = principalPayment;
    }

    public double getInterestPayment() {
        return interestPayment;
    }

    public void setInterestPayment(double interestPayment) {
        this.interestPayment = interestPayment;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }
}
