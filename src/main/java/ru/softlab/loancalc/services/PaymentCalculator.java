package ru.softlab.loancalc.services;

import ru.softlab.loancalc.entity.LoanRequest;
import ru.softlab.loancalc.entity.Payment;

import java.util.List;

public interface PaymentCalculator {

    List<Payment> getPayments(LoanRequest loanRequest);
}
