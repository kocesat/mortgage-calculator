package com.kocesat;

public class MortgageReport {

    private MortgageCalculator calculator;

    public MortgageReport(MortgageCalculator calculator) {
        this.calculator = calculator;
    }

    public void printPaymentSchedule() {
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("--------------");
        System.out.println();
        for(double balance: calculator.getRemainingBalances()) {
            System.out.println(Console.formatCurrency(balance));
        }
    }

    public void printMortgage() {
        double mortgage = calculator.calculateMortgage();
        String mortgageFormatted = Console.formatCurrency(mortgage);
        System.out.println("MORTGAGE");
        System.out.println("--------------");
        System.out.println("Monthly payment: " + mortgageFormatted);
        System.out.println();
    }
}
