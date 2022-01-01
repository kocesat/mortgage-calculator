package com.kocesat;

import java.text.NumberFormat;
import java.util.Scanner;

public class Console {
    private static Scanner scanner = new Scanner(System.in);

    public static double readNumber(String prompt) {
        return scanner.nextDouble();
    }

    public static double readNumber(String prompt, double min, double max) {
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

    public static String formatCurrency(double value) {
        return NumberFormat.getCurrencyInstance().format(value);
    }
}
