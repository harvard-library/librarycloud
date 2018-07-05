package edu.harvard.lib.librarycloud.items;


public class ISBN {


    public static String convert13to10(String longISBN) {
        String result;
        String baseISBN = longISBN.substring(3, 12);
        String checkDigit = generateDigit(baseISBN);
        System.out.println("BASE: "+baseISBN);
        System.out.println("DIGIT: "+checkDigit);
        result =  baseISBN + checkDigit;
        System.out.println(result);
        return result;
    }

    private static String generateDigit(String base) {

        // read in one command-line argument
        int n = Integer.parseInt(base);

        // compute the weighted sum of the digits, from right to left
        int sum = 0;
        for (int i = 2; i <= 10; i++) {
            int digit = n % 10;                // rightmost digit
            sum = sum + i * digit;
            n = n / 10;
        }

        String result;
        // print out check digit, use X for 10
        if (sum % 11 == 1) {
            result = "X";
        } else if (sum % 11 == 0) {
            result = "0";
        } else {
            result = String.valueOf(11 - (sum % 11));
        }
        return result;
    }
}
