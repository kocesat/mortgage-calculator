package com.kocesat;

public class Main {

    public static void main(String[] args) {

        int principal = (int) Console.readNumber("Principal: ", 1000, 1_000_000);
        float annualInterest = (float) Console.readNumber("Annual Interest Rate: ", 1, 30);
        byte years = (byte) Console.readNumber("Period (years): ", 1, 30);

        MortgageCalculator calculator = new MortgageCalculator(principal, annualInterest, years);

        MortgageReport reporter = new MortgageReport(calculator);
        reporter.printMortgage();
        reporter.printPaymentSchedule();
    }
}








