package org.estatio.dom.lease;

import java.math.BigDecimal;

public enum InvoicingFrequency {

    WEEKLY_IN_ADVANCE("Weekly","RRULE:FREQ=WEEKLY;INTERVAL=1", true, BigDecimal.valueOf(7), BigDecimal.valueOf(365.25)),
    WEEKLY_IN_ARREARS("Weekly","RRULE:FREQ=WEEKLY;INTERVAL=1", false, BigDecimal.valueOf(7), BigDecimal.valueOf(365.25)),
    MONTHLY_IN_ADVANCE("Monthly","RRULE:FREQ=MONTHLY;INTERVAL=1", true, BigDecimal.valueOf(1), BigDecimal.valueOf(12)), 
    MONTHLY_IN_ARREARS("Monthly","RRULE:FREQ=MONTHLY;INTERVAL=1", false, BigDecimal.valueOf(1), BigDecimal.valueOf(12)), 
    QUARTERLY_IN_ADVANCE("Quarterly","RRULE:FREQ=MONTHLY;INTERVAL=3", true, BigDecimal.valueOf(3), BigDecimal.valueOf(12)), 
    QUARTERLY_IN_ADVANCE_PLUS1M("Quarterly","RRULE:FREQ=MONTHLY;INTERVAL=3;BYMONTH=2,5,8,11", true, BigDecimal.valueOf(3), BigDecimal.valueOf(12)), 
    QUARTERLY_IN_ARREARS("Quarterly","RRULE:FREQ=MONTHLY;INTERVAL=3", false, BigDecimal.valueOf(3), BigDecimal.valueOf(12)), 
    SEMI_YEARLY_IN_ADVANCE("Semi-yearly","RRULE:FREQ=MONTHLY;INTERVAL=6", true, BigDecimal.valueOf(1), BigDecimal.valueOf(1)),
    SEMI_YEARLY_IN_ARREARS("Semi-yearly","RRULE:FREQ=MONTHLY;INTERVAL=6", false, BigDecimal.valueOf(1), BigDecimal.valueOf(1)),
    YEARLY_IN_ADVANCE("Yearly","RRULE:FREQ=YEARLY;INTERVAL=1", true, BigDecimal.valueOf(1), BigDecimal.valueOf(1)),
    YEARLY_IN_ARREARS("Yearly","RRULE:FREQ=YEARLY;INTERVAL=1", false, BigDecimal.valueOf(1), BigDecimal.valueOf(1));

    private InvoicingFrequency(String title, String rrule, Boolean inAdvance, BigDecimal numerator, BigDecimal denominator){
        this.rrule = rrule;
        this.title = title;
        this.numerator = numerator;
        this.denominator = denominator;
        this.inAdvance = inAdvance;
    }

    public final Boolean inAdvance;
    public final BigDecimal numerator;
    public final BigDecimal denominator;
    public final String title;
    public final String rrule;

}
