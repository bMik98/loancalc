package ru.softlab.loancalc.services;

import ru.softlab.loancalc.entity.LoanRequest;
import ru.softlab.loancalc.entity.Payment;

import java.util.Arrays;
import java.util.List;

public class AnnuityLoanCalculator implements PaymentCalculator {

    @Override
    public List<Payment> getPayments(LoanRequest loanRequest) {
        return Arrays.asList(new Payment(), new Payment());
    }
}
