package ru.softlab.loancalc.pages;


import org.apache.tapestry5.Block;
import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.annotations.*;
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
    @Inject
    private Logger logger;

    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    @Property
    @Inject
    @Symbol(SymbolConstants.TAPESTRY_VERSION)
    private String tapestryVersion;

    @InjectPage
    private About about;

    @Inject
    private Block block;

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

    @Log
    void onSubmitFromRequestForm() {
        payments = paymentCalculator.getPayments(new LoanRequest(loanAmount, loanRate, loanTermInMonths));
        logger.debug("Submit button was pressed! {}", loanAmount);
    }

//    Object onActionFromLearnMore() {
//        about.setLearn("LearnMore");
//
//        return about;
//    }

//    @Log
//    void onComplete() {
//        logger.info("Complete call on Index page");
//    }
//
//    @Log
//    void onAjax() {
//        logger.info("Ajax call on Index page");
//
//        ajaxResponseRenderer.addRender("middlezone", block);
//    }


//    public Date getCurrentTime() {
//        return new Date();
//    }

}
