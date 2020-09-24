/**
 *
 *      NAME :                  Kieran Marcus Williams
 *      Student Number :        C1930510
 *
 */

package com.company;

public class StringUtil {
    public static String numberToSmallNumber(String numAsString){

        int numAsInt = 0;
        String[] numberSplitUp = new String[numAsString.length()];
        StringBuilder smallNumber = new StringBuilder();

        // checks that the argument is a string that can be casted to an integer
        try {
            numAsInt = Integer.parseInt(numAsString);
        }catch (NumberFormatException e){
            System.out.println("argument is not an integer");
            throw e;
        }



        if (numAsInt > 9){
            numberSplitUp = numAsString.split("");
            for(int i = 0; i < numberSplitUp.length; i++){
                smallNumber.append(digitToSmallString(numberSplitUp[i]));
            }

        }else{
            return digitToSmallString(numAsString);
        }
        return smallNumber.toString();
    }

    private static String digitToSmallString(String digit){
        String smallDigit = "";
        switch (digit){
            case "0":
                smallDigit = "₀";
                break;
            case "1":
                smallDigit = "₁";
                break;
            case "2":
                smallDigit = "₂";
                break;
            case "3":
                smallDigit = "₃";
                break;
            case "4":
                smallDigit = "₄";
                break;
            case "5":
                smallDigit = "₅";
                break;
            case "6":
                smallDigit = "₆";
                break;
            case "7":
                smallDigit = "₇";
                break;
            case "8":
                smallDigit = "₈";
                break;
            case "9":
                smallDigit = "₉";
                break;
        }
        return smallDigit;
    }
}
