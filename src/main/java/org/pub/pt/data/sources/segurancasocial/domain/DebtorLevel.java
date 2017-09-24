package org.pub.pt.data.sources.segurancasocial.domain;

/**
 * Debtor domain object that holds information regarding a social security debtor
 * Created by balhau on 4/16/17.
 */
public class DebtorLevel {
    /**
     * Debts between 7 500 and 25 000 euros
     */
    public static final int FIRST_DEGREE=1;
    /**
     * Debts between 25 000 and 50 000  euros
     */
    public static final int SECOND_DEGREE=2;
    /**
     * Debts between 50 000 and 100 000 euros
     */
    public static final int THIRD_DEGREE=3;
    /**
     * Debts between 100 000 and 250 000
     */
    public static final int FOURTH_DEGREE=4;
    /**
     * Debts between 250 000 and 1 000 000
     */
    public static final int FIFTH_DEGREE=5;
    /**
     * Debts higher than 1 000 000 euros
     */
    public static final int SIXTY_DEGREE=6;



}
