/**
 *
 *      NAME :                  Kieran Marcus Williams
 *      Student Number :        C1930510
 *
 */

package com.company;
import java.util.Scanner;

public class MagicSquare {

    private int n;
    private int[][] magicSquare;


    private static Scanner in = new Scanner(System.in);




    MagicSquare(int n){
        this.n = n;
        createMagicSquare();
    }




    // static method that returns -1 if input is not valid and returns the number if it is
    public static int inputAndValidation(){
        boolean isIntFlag = true;
        int input;

        try{ // exception handling to check input is integer
            System.out.println("\t\tEnter an odd integer to create a magic square: ");
            input = in.nextInt(); // gets input from user
        }catch(Exception e){
            System.out.println("\t\tValue entered was not an Integer");
            return -1;
        }

        if (checkMagicSquareSizeIsValid(input)){
            return input;
        }
        else {
            System.out.println("\t\tn is not a valid number");
            return -1;
        }

    }


    // validation method that checks that position is in the correct
    // format and is within the bounds of the magic square
    public static boolean validatePosition(int[][] magicSquare, int[] pos){
        // checks the array is of length 2
        if (pos.length != 2) return false;
        // checks the pos coordinates are within the bounds of the magic square
        return ((pos[0] <= magicSquare.length && pos[0] >= 0)
                && (pos[1] <= magicSquare.length && pos[1] >= 0));
    }


//    // prints out the 2D array in an easy to view format
//    public static void printMagicSquare(int[][] array){
//        String line;
//        for (int[] innerArray : array) {
//            System.out.print("\t\t");
//            for (int j = 0; j < array.length; j++) {
//                System.out.print(innerArray[j] + "\t");
//            }
//            System.out.println("\n");
//        }
//    }



    // prints out the 2D array in an easy to view format
    public static void printMagicSquare(int[][] array){

        StringBuilder headerNumbers = new StringBuilder();
        headerNumbers.append("\t\t");

        for (int i = 0; i < array.length; i++){
            headerNumbers.append(StringUtil.numberToSmallNumber(Integer.toString(i))+"\t");
        }
        System.out.println(headerNumbers.toString() + "\n\n");

        for (int i = 0; i < array.length; i++) {
            System.out.print(StringUtil.numberToSmallNumber(Integer.toString(i)) + "\t\t");

            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println("\n");
        }
    }




    // returns true if argument is odd and not 0
    private static boolean checkMagicSquareSizeIsValid(int n){
        return (((n+1)%2) == 0) && (n > 0);
    }


    // generates the magic square and stores it in the magicSquare attribute
    private void createMagicSquare(){
        // create array
        int[][] arr2D = new int[n][n];

        // set initial coordinates within array
        int x = 0;
        int y = n/2;
        int currentX;
        int currentY;


        for(int i = 1; i < n*n+1; i++){

            arr2D[x][y] = i;
            currentX = x;
            currentY = y;
            x--;
            y++;
            if (x == -1) {
                x = n - 1;
            }
            if (y == n) {
                y = 0;
            }
            if (arr2D[x][y] != 0) {
                x = currentX + 1;
                y = currentY;
                if (x == -1) {
                    x = n - 1;
                }
            }

        }
        magicSquare = arr2D;
    }




    public int getN() {
        return n;
    }

    public int[][] getMagicSquare() {
        return magicSquare;
    }

}
