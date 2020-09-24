/**
 *
 *      NAME :                  Kieran Marcus Williams
 *      Student Number :        C1930510
 *
 */

package com.company;

import java.util.ArrayList;
import java.util.Random;



public class ManipulatingMagicSquare {



    private static Random random = new Random();
    private static int[][] magicSquare;





    // shuffles the magic square and returns it shuffled
    public static int[][] shuffleMagicSquare(int[][] magicSquare ){
        ManipulatingMagicSquare.setMagicSquare(magicSquare); // sets the magic square property to the magicSquare argument

        int bound = magicSquare.length;
        for(int i = 0; i < (bound*bound); i++){
            // random numbers generated between 0 and the size of the magic square
            // stored in array to be used as x and y coordinates
            int[] pos = {random.nextInt(bound), random.nextInt(bound)};
            magicSquare = swapElements(magicSquare, pos, Direction.RANDOM);
        }
        return magicSquare;
    }




    // will make a swap between two elements within the array, one being specified by the pos parameter
    public static int[][] swapElements(int[][] magicSquare, int[] pos, Direction direction){

        ManipulatingMagicSquare.setMagicSquare(magicSquare); // sets the magic square property to the magicSquare argument

        // ensures that the pos variable is valid and throws an error message if it is not
        if (!MagicSquare.validatePosition(magicSquare, pos)) {
            throw new ArrayIndexOutOfBoundsException( "position argument should be an integer array with two " +
                            "elements representing x and y respectively within the bounds of the magic square"
            );
        }


        // checks which directions the selected element could be swapped
        // with. Returns an integer array list containing the directions
        ArrayList<Direction> possibleDirections = checkPossibleSwapDirections(pos);


        // checks if direction is set to random. if it, the direction  will be set randomly
        if(direction == Direction.RANDOM){
            // choose a random number 0 to the number of directions there are available for swaps
            // then use that number to choose a direction for the swap to occur with
            int randomNum = random.nextInt(possibleDirections.size());
            direction = possibleDirections.get(randomNum);
        }

        else if (possibleDirections.indexOf(direction) == -1){
            throw new RuntimeException("direction passed in as argument is not a valid direction");
        }



        int temp = magicSquare[pos[0]][pos[1]];// variable used for swapping

        // swap the elements in the direction based on the random number chosen and
        // the directions in the possible directions array list
        switch (direction){
            case UP:
                // swaps element at pos with the one above it
                magicSquare[pos[0]][pos[1]] = magicSquare[pos[0]-1][pos[1]];
                magicSquare[pos[0]-1][pos[1]] = temp;
                break;
            case RIGHT:
                // swaps element at pos with the one to the right of it
                magicSquare[pos[0]][pos[1]] = magicSquare[pos[0]][pos[1]+1];
                magicSquare[pos[0]][pos[1]+1] = temp;
                break;
            case DOWN:
                // swaps element at pos with the one to the left of it
                magicSquare[pos[0]][pos[1]] = magicSquare[pos[0]+1][pos[1]];
                magicSquare[pos[0]+1][pos[1]] = temp;
                break;
            case LEFT:
                // swaps element at pos with the one below it
                magicSquare[pos[0]][pos[1]] = magicSquare[pos[0]][pos[1]-1];
                magicSquare[pos[0]][pos[1]-1] = temp;
                break;
            default:
                throw new RuntimeException("direction in switch case is not a valid direction. direction = " + direction);
        }
        return magicSquare;
    }





    // checks which directions the selected element could be swapped with
    // utilise exception handling to find out which directions are possible for swaps
    private static ArrayList<Direction> checkPossibleSwapDirections(int[] pos){

        ArrayList<Direction> directionsAvailable = new ArrayList<>();
        directionsAvailable.add(Direction.UP);
        directionsAvailable.add(Direction.RIGHT);
        directionsAvailable.add(Direction.DOWN);
        directionsAvailable.add(Direction.LEFT);

        int i = pos[1];
        int j = pos[0];
        int n = magicSquare.length;
        int[][] arr = new int[n][n];

        // tries to change the value of the array element above, right, down and left
        // the catch statement will run if an Array out of bounds exception is caught
        // this will mean that it can not be swapped in that direction

        try{ // up
            arr[j-1][i] = 2;
        }catch (Exception e){
            directionsAvailable.remove(Direction.UP);
        }

        try{ // right
            arr[j][i+1] = 2;
        }catch (Exception e){
            directionsAvailable.remove(Direction.RIGHT);
        }

        try{ // down
            arr[j+1][i] = 2;
        }catch (Exception e){
            directionsAvailable.remove(Direction.DOWN);
        }

        try{ // left
            arr[j][i-1] = 2;
        }catch (Exception e){
            directionsAvailable.remove(Direction.LEFT);
        }

        return directionsAvailable;
    }


    // can be used for debugging purposes when using a method that relies on it being random
    public static void setRandom(long seed) {
        ManipulatingMagicSquare.random = new Random(seed);
    }

    private static void setMagicSquare(int[][] magicSquare) {
        ManipulatingMagicSquare.magicSquare = magicSquare;
    }
}
