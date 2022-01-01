package com.kocesat;

public class MortgageCalculator {
    private final static byte MONTHS_IN_YEAR = 12;
    private final static byte PERCENT = 100;

    private int principal;
    private float annualInterest;
    private byte years;

    public MortgageCalculator(int principal, float annualInterest, byte years) {
        this.principal = principal;
        this.annualInterest = annualInterest;
        this.years = years;
    }

    public double[] getRemainingBalances() {
        double[] balances = new double[getNumberOfPayments(getYears())];
        for(short month = 1; month <= balances.length; month++) {
            balances[month - 1] = calculateBalance(month);
        }
        return balances;
    }

    public double calculateMortgage() {
        short numberOfPayments = getNumberOfPayments(years);
        float monthlyInterest = getMonthlyInterest(annualInterest);

        double mortgage = principal *
                (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return mortgage;
    }

    public double calculateBalance(short numberOfPaymentsMade) {
        short numberOfPayments = getNumberOfPayments(years);
        float monthlyInterest = getMonthlyInterest(annualInterest);
        double balance;

        balance = principal * (Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
        return balance;
    }

    private byte getYears() {
        return years;
    }

    private float getMonthlyInterest(float annualInterest) {
        return annualInterest / MONTHS_IN_YEAR / PERCENT;
    }

    private short getNumberOfPayments(byte years) {
        return (short)(years * MONTHS_IN_YEAR);
    }
}
