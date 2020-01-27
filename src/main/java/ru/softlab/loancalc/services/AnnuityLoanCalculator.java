package ru.softlab.loancalc.services;

import ru.softlab.loancalc.entity.LoanRequest;
import ru.softlab.loancalc.entity.Payment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnnuityLoanCalculator implements PaymentCalculator {

    @Override
    public List<Payment> getPayments(LoanRequest loanRequest) {
        final int numberOfMonths = loanRequest.getTermInMonths();
        List<Payment> result = new ArrayList<>(numberOfMonths);
        final BigDecimal monthlyInterestRate = BigDecimal.valueOf(loanRequest.getInterestRate() / 100 / 12);
        final BigDecimal monthlyPayment = calculateMonthlyPayment(loanRequest.getAmount(), monthlyInterestRate, loanRequest.getTermInMonths());
        BigDecimal balance = BigDecimal.valueOf(loanRequest.getAmount());
        for (int i = 1; i <= numberOfMonths; i++) {
            Payment payment = new Payment();
            payment.setNumber(i);
            payment.setDate(new Date());
            BigDecimal interest = calculateMonthlyInterest(balance, monthlyInterestRate);
            payment.setInterestPayment(interest.doubleValue());
            BigDecimal principalPayment = monthlyPayment.subtract(interest).setScale(2, RoundingMode.HALF_UP);
            payment.setBalance(balance.doubleValue());
            payment.setPrincipalPayment(principalPayment.doubleValue());
            payment.setTotalPayment(monthlyPayment.doubleValue());
            result.add(payment);
            balance = balance.subtract(principalPayment);
        }
        return result;
    }

    /**
     * Calculates monthly payment.
     *
     * Based on formula:
     * P = S * (i * (1 + i)^n / ((1 + i)^n - 1))
     * where:
     *      S - starting loan amount
     *      i - monthly interest rate
     *      n - number of month
     */
    private BigDecimal calculateMonthlyPayment(double startingAmount, BigDecimal monthlyInterestRate, int numberOfMonths) {
        BigDecimal amount = BigDecimal.valueOf(startingAmount);
        BigDecimal poweredRate = monthlyInterestRate.add(BigDecimal.ONE).pow(numberOfMonths);
        BigDecimal divisor = poweredRate.subtract(BigDecimal.ONE);
        return amount.multiply(monthlyInterestRate).multiply(poweredRate).divide(divisor, 2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateMonthlyInterest(BigDecimal balance, BigDecimal monthlyInterestRate) {
        return balance.multiply(monthlyInterestRate).setScale(2, RoundingMode.HALF_UP);
    }
}
