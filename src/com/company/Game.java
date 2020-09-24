/**
 *
 *      NAME :                  Kieran Marcus Williams
 *      Student Number :        C1930510
 *
 */

package com.company;


import java.util.Arrays;
import java.util.Scanner;

public class Game {



    private int[][] originalMagicSquare;
    private int[][] shuffledMagicSquare;
    private int numberOfMoves = 0;
    private String currentInput = "";
    private int[] currentPos = new int[2];
    private Direction currentDirection;

    private String inputErrorMessage =
            "\n\t\tInput should be in the form: i j direction\n" +
            "\t\t-------------------------------------------\n\n" +
            "\t\tWhere 'i' is the row and 'j' is the column of the element being swapped.\n" +
            "\t\tThe 'direction' (either ['U', 'D', 'L', 'R'] representing up, down, left and right)\n" +
            "\t\tspecifies which direction the element should be swapped with\n";


    Game(){

        MagicSquare magicSquare;
        MagicSquare magicSquareForShuffling;

        gameIntro();
        int input = -1;

        // gets user input and makes sure it is valid to create a magic square
        while (input == -1){
            input = MagicSquare.inputAndValidation();
        }

        magicSquare = new MagicSquare(input);
        magicSquareForShuffling = new MagicSquare(input);
        originalMagicSquare = magicSquare.getMagicSquare();
        shuffledMagicSquare = magicSquareForShuffling.getMagicSquare();
        shuffledMagicSquare = ManipulatingMagicSquare.shuffleMagicSquare(shuffledMagicSquare);

        playGame();
    }


    public void playGame(){
        boolean gameOver = false;
        boolean validMove = false;
        // pushConsoleTextUp(100);
        while (!gameOver){
            numberOfMoves++;
            MagicSquare.printMagicSquare(shuffledMagicSquare);

            // gets move input and checks if it is possible. if the move is not possible,
            // the user will be required to try again until the move is valid
            while(!validMove){
                try{
                    moveInput();
                    shuffledMagicSquare = ManipulatingMagicSquare.swapElements(
                            shuffledMagicSquare, currentPos, currentDirection);
                    validMove = true;
                }catch (Exception e){
                    System.out.println(inputErrorMessage+"\n\n");
                    MagicSquare.printMagicSquare(shuffledMagicSquare);
                }
            }
            validMove = false; // resets the flag variable so the user has to enter the variable again

            if (Arrays.deepEquals(shuffledMagicSquare, originalMagicSquare)) {
                gameOver = true;
                System.out.println("\n\n\t\t Completed in " + numberOfMoves +
                        " moves\n\n\t\tCompleted Magic Square: \n\n\t\t\t\t\t\t");
                MagicSquare.printMagicSquare(shuffledMagicSquare);
            }else{
               // pushConsoleTextUp();
            }
        }
    }


    // introduces player to the game
    private void gameIntro(){
        System.out.println("\n\n\t\t\t\t\t\t\t\t\tMAGIC SQUARE\n\t\t\t\t\t\t\t\t-------------------\n\n");
        System.out.println("\t\tHow To Play: \n\n\t\t\t\t\t* A magic square is a grid of numbers in which \n\t\t\t\t\t" +
                "  every row, column and diagonal adds up to the same number\n\n\t\t\t\t\t" +
                "* You will be given a shuffled magic square of a dimension \n\t\t\t\t\t" +
                "  chosen by you (the larger the harder)\n\n\t\t\t\t\t"+
                "* The goal is to make the shuffled magic square a magic square again.\n\t\t\t\t\t" +
                "  you will make moves by entering the position by row and then column\n\t\t\t\t\t" +
                "  with the direction in the format [i j direction] (i and j are integer values \n\t\t\t\t\t" +
                "  and the direction should be one of the following characters: U, D, L, R for \n\t\t\t\t\t" +
                "  up, down, left or right respectively. Coordinate (0, 0) is the top left element \n\n\n\t\t" +
                "Example: \n\t\t\t\t\tFor a square such as this:\n\n\t\t\t\t\t\t\t\t\t\t\t\t" +
                "8\t1\t6\t\n\n\t\t\t\t\t\t\t\t\t\t\t\t3\t5\t7\t\n\n\t\t\t\t\t\t\t\t\t\t\t\t4\t9\t2\n\n\n\t\t\t\t\t" +
                "To swap the middle number (5) with the one above: [1 1 U] \n\t\t\t\t\t" +
                "would be entered into the console without the [],\n\t\t\t\t\t" +
                "giving the result: \n\n\t\t\t\t\t\t\t\t\t\t\t\t" +
                "8\t5\t6\t\n\n\t\t\t\t\t\t\t\t\t\t\t\t3\t1\t7\t\n\n\t\t\t\t\t\t\t\t\t\t\t\t4\t9\t2\n\n");
    }


    // used to make the game more enjoyable by making the console less cluttered
    private void pushConsoleTextUp(int numberOfLines){
        for (int i=0; i < numberOfLines; i++){
            System.out.println();
        }
    }
    private void pushConsoleTextUp(){
        pushConsoleTextUp(50);
    }


    // gets input for the move from the user
    // will re request input from the user if it is not in the correct format
    private void moveInput(){

        boolean validInput = false;
        String input = "";

        while (!validInput){
            System.out.println("\n\n\t\tEnter your move: ");
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            // set the global variable of current input so that it
            // can be referenced by other methods
            currentInput = input;
            validInput = validateMoveInputFormat(input);
            if (!validInput) throw new RuntimeException("Input not in the correct format");
        }
        String[] inputArr = input.split(" ");
        currentPos[0] = Integer.parseInt(inputArr[0]);
        currentPos[1] = Integer.parseInt(inputArr[1]);
        switch (inputArr[2]){
            case "U":
                currentDirection = Direction.UP;
                break;
            case "D":
                currentDirection = Direction.DOWN;
                break;
            case "L":
                currentDirection = Direction.LEFT;
                break;
            case "R":
                currentDirection = Direction.RIGHT;
                break;
        }
    }

    // validates the input for the move to the format [x y direction]
    private boolean validateMoveInputFormat(String input){



        // split input into array by spaces
        String[] inputArr = input.split(" ");

        // check the first 2 elements are integers
        try{
            int i = Integer.parseInt(inputArr[0]);
            int j = Integer.parseInt(inputArr[1]);
            int[] pos = {i,j};
            // check the position is valid (in the bounds of the magic square)
            if (!MagicSquare.validatePosition(shuffledMagicSquare, pos)) return false;
        }catch (RuntimeException e){
            return false;
        }




        // check that last element is one of these characters 'U', 'D', 'L', or 'R'
        switch (inputArr[2]){
            case "U":
                return true;
            case "D":
                return true;
            case "L":
                return true;
            case "R":
                return true;
            default:
                System.out.println(inputErrorMessage);
                return false;
        }
    }



}