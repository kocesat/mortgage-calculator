package com.kocesat;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {

        int principal = (int)readNumber("Principal: ", 1000, 1_000_000);
        float annualInterest = (float)readNumber("Annual Interest Rate: ", 1, 30);
        byte years = (byte)readNumber("Period (years): ", 1, 30);
        double mortgage = calculateMortgage(principal, annualInterest, years);

        printMortgage(mortgage);
        printPaymentSchedule(principal, annualInterest, years);
    }

    private static void printMortgage(double mortgage) {
        String mortgageFormatted = formatCurrency(mortgage);
        System.out.println("MORTGAGE");
        System.out.println("--------------");
        System.out.println("Monthly payment: " + mortgageFormatted);
        System.out.println();
    }

    private static void printPaymentSchedule(int principal, float annualInterest, byte years) {
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("--------------");
        short numberOfPayments = getNumberOfPaymentsInMonth(years);
        for(short i = 0; i <= numberOfPayments; i++) {
            short remainingNumberOfPayments = (short)(numberOfPayments - i);
            String balanceFormatted = formatCurrency(getBalance(principal, annualInterest, years, remainingNumberOfPayments));
            System.out.println(balanceFormatted);
        }
        System.out.println();
    }

    public static double calculateMortgage(
            int principal,
            float annualInterest,
            byte years) {

        short numberOfPayments = getNumberOfPaymentsInMonth(years);
        float monthlyInterest = getMonthlyInterestFromAnnual(annualInterest);

        double mortgage = principal *
                (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return mortgage;
    }

    public static double getBalance(int principal, float annualInterest, byte years, short remainingNumberOfPayments) {
        short numberOfPayments = getNumberOfPaymentsInMonth(years);
        float monthlyInterest = getMonthlyInterestFromAnnual(annualInterest);
        double balance;

        balance = principal * (Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, remainingNumberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
        return (double)principal - balance;
    }

    public static double readNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextDouble();
            if (value <= max && value >= min)
                break;
            System.out.println("Enter a value between " + min + " and " + max);
        }
        return value;
    }

    public static float getMonthlyInterestFromAnnual(float annualInterest) {
        return annualInterest / MONTHS_IN_YEAR / PERCENT;
    }

    public static short getNumberOfPaymentsInMonth(byte years) {
        return (short)(years * MONTHS_IN_YEAR);
    }

    public static String formatCurrency(double value) {
        return NumberFormat.getCurrencyInstance().format(value);
    }
}








