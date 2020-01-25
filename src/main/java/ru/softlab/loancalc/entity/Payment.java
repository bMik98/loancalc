package ru.softlab.loancalc.entity;

import java.util.Date;

public class Payment {

    private int number;                 // Номер платежа
    private Date date;                  // Месяц/год
    private double principalPayment;    // Платеж по основному долгу
    private double interestPayment;     // Платеж по процентам
    private double principalBalance;    // Остаток основного долга
    private double totalPayment;        // Общая сумма платежа

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public double getPrincipalBalance() {
        return principalBalance;
    }

    public void setPrincipalBalance(double principalBalance) {
        this.principalBalance = principalBalance;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }
}
