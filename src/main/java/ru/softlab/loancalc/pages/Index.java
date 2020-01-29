package ru.softlab.loancalc.pages;

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.HttpError;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.slf4j.Logger;
import ru.softlab.loancalc.entity.LoanRequest;
import ru.softlab.loancalc.entity.Payment;
import ru.softlab.loancalc.services.PaymentCalculator;

import java.util.List;

/**
 * Start page of application loancalc.
 */
public class Index {

    private static final int MIN_AMOUNT = 100_000;
    private static final double MIN_INTEREST_RATE = 12.9;
    private static final int MIN_TERM_IN_MONTHS = 12;
    private static final int MAX_AMOUNT = 500_000;
    private static final double MAX_INTEREST_RATE = 23.9;
    private static final int MAX_TERM_IN_MONTHS = 60;

    @Parameter(value = "12")
    private int minTermsInMonths;

    @Inject
    private Logger logger;

    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    @Property
    @Inject
    @Symbol(SymbolConstants.TAPESTRY_VERSION)
    private String tapestryVersion;

    @Persist
    @Property
    private int loanAmount;

    @Persist
    @Property
    private int loanTermInMonths;

    @Persist
    @Property
    private double loanRate;

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
        if (loanRate < MIN_INTEREST_RATE || loanRate > MAX_INTEREST_RATE) {
            loanRate = MIN_INTEREST_RATE;
        }
        if (loanTermInMonths < MIN_TERM_IN_MONTHS || loanTermInMonths > MAX_TERM_IN_MONTHS) {
            loanTermInMonths = MIN_TERM_IN_MONTHS;
        }
    }

    public int getMinAmount() {
        return MIN_AMOUNT;
    }

    void onCalculate() {
        payments = paymentCalculator.getPayments(new LoanRequest(loanAmount, loanRate, loanTermInMonths));
    }

    void onReset() {
        loanAmount = MIN_AMOUNT;
        loanRate = MIN_INTEREST_RATE;
        loanTermInMonths = MIN_TERM_IN_MONTHS;
    }
}
