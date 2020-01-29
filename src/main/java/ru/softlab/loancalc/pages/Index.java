package ru.softlab.loancalc.pages;

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.HttpError;
import ru.softlab.loancalc.entity.LoanRequest;
import ru.softlab.loancalc.entity.Payment;
import ru.softlab.loancalc.services.PaymentCalculator;

import java.util.List;

/**
 * Start page of application loancalc.
 */
public class Index {

    private static final int MIN_AMOUNT = 100_000;
    private static final int MAX_AMOUNT = 500_000;
    private static final int AMOUNT_STEP = 10_000;
    private static final double MIN_INTEREST_RATE = 12.9;
    private static final double MAX_INTEREST_RATE = 23.9;
    private static final double INTEREST_RATE_STEP = 0.1;
    private static final int MIN_TERM_IN_MONTHS = 12;
    private static final int MAX_TERM_IN_MONTHS = 60;
    private static final int TERM_IN_MONTHS_STEP = 1;

    @Persist
    @Property
    private int loanAmount;

    @Persist
    @Property
    private int loanTermInMonths;

    @Persist
    @Property
    private double loanInterestRate;

    @Inject
    private PaymentCalculator paymentCalculator;

    @Persist
    private List<Payment> payments;

    public List<Payment> getPayments() {
        return payments;
    }

    // Handle call with an unwanted context
    Object onActivate(EventContext eventContext) {
        return eventContext.getCount() > 0 ?
                new HttpError(404, "Resource not found") :
                null;
    }

    @SetupRender
    public void initFormValues() {
        if (loanAmount < MIN_AMOUNT || loanAmount > MAX_AMOUNT) {
            loanAmount = MIN_AMOUNT;
        }
        if (loanInterestRate < MIN_INTEREST_RATE || loanInterestRate > MAX_INTEREST_RATE) {
            loanInterestRate = MIN_INTEREST_RATE;
        }
        if (loanTermInMonths < MIN_TERM_IN_MONTHS || loanTermInMonths > MAX_TERM_IN_MONTHS) {
            loanTermInMonths = MIN_TERM_IN_MONTHS;
        }
    }

    public int getMinAmount() {
        return MIN_AMOUNT;
    }

    public int getMaxAmount() {
        return MAX_AMOUNT;
    }

    public int getAmountStep() {
        return AMOUNT_STEP;
    }

    public double getMinInterestRate() {
        return MIN_INTEREST_RATE;
    }

    public double getMaxInterestRate() {
        return MAX_INTEREST_RATE;
    }

    public double getInterestRateStep() {
        return INTEREST_RATE_STEP;
    }

    public int getMinTermInMonths() {
        return MIN_TERM_IN_MONTHS;
    }

    public int getMaxTermInMonths() {
        return MAX_TERM_IN_MONTHS;
    }

    public int getTermInMonthsStep() {
        return TERM_IN_MONTHS_STEP;
    }

    void onCalculate() {
        payments = paymentCalculator.getPayments(new LoanRequest(loanAmount, loanInterestRate, loanTermInMonths));
    }
}
