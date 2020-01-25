package ru.softlab.loancalc.entity;

import org.apache.tapestry5.annotations.Property;

public class LoanRequest {

    @Property
    private long amount;

    @Property
    private double rate;

    @Property
    private int term;
}
