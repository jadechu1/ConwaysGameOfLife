package com.playground.game;

/**
 * Created by chutungyu11 on 1/24/17.
 *
 * Rules of Game

 Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
 Any live cell with two or three live neighbours lives on to the next generation.
 Any live cell with more than three live neighbours dies, as if by overpopulation.
 Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
 */

public class GameOfLife {


    private int [][] gameboard; // 0 dead, 1 alive

    // constructor i, j
    // create board and seed dead or alive
    GameOfLife(int i, int j) {
        gameboard = new int[i][j];
        for (int ii=0; ii < i; ii++)
            for (int jj=0; jj < j; jj++)
                gameboard[ii][jj] = (int) Math.round(Math.random());

    }

    /*

    method amIDeadOrAlive
        input coordinates i, j gameboard cell
        returns int isAlive

        //Checks neighbors for given cell [i,j]
            Determine start and end point for a 3x3 - make sure to check for boundaries
            Nested for loops for 3x3
                if current cell is [i,j], set isAlive (true/falue - true is alive)
                    break;
                // check current cell for dead or alive
                if dead, increment int dead
                if alive, increment int alive
        //Determine live or die
            if (isAlive and (alive > 3 or alive < 2)) then isAlive = false
            else if (isAlive is false and alive = 3) then isAlive = true
        return isAlive;

     */

    private int amIDeadOrAlive(int i, int j) {

        // figure out start/end i and j for 3x3 cell
        int starti = (i == 0) ? i : i-1;
        int startj = (j == 0) ? j : j-1;
        int endi = (i+1 == gameboard.length) ? i : i+1;
        int endj = (j+1 == gameboard[i].length) ? j : j+1;

        int numAliveNeighbors=0;
        int result = 0;

        // loop through 3x3
        for (int ii=starti; ii<endi; ii++) {
            for (int jj=startj; jj<endj; jj++) {

                // if current position is same as input [i, j], then skip it
                if (ii==i && jj==j) {
                    result = gameboard[ii][jj];
                    break;
                }

                // check cell for dead or alive
                numAliveNeighbors += gameboard[ii][jj];

            }
        }


        if (result==1 && (numAliveNeighbors>3 || numAliveNeighbors<2))
            result = 0;
        else if (result == 0 && numAliveNeighbors == 3)
            result = 1;

        return result;
    }

    /*

      playGame
        returns newGameboard


        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                newGameboard[i,j] = amIDeadOrAlive(i, j);
            }
        }
        returns newGameboard;

     */

    private int[][] playGame() {

        int rows = gameboard.length;
        int cols = gameboard[0].length;
        int[][] newGameboard = new int[rows][cols];

        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                newGameboard[i][j] = amIDeadOrAlive(i, j);
            }
        }
        return newGameboard;


    }

    /*
        method printGameboard
        print out game board values
     */
    private void printGameboard() {
        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard[0].length; j++) {
                System.out.print(gameboard[i][j] + " ");
            }
            System.out.println();
        }
    }

    /*
        main()
        input gameRounds;
        //populate gameboard with inital board
     */
    public static void main(String[] args) {
        System.out.println("Let's play");

        int gameRounds=5;
        int rows=5;
        int cols=5;

        // invalid
        if (gameRounds < 1 || rows == 0 || cols == 0) {
            System.out.println("Invalid input.");
            System.exit(0);
        } 

        System.out.println("We are going to play " + gameRounds + " with a " + rows + " by " + cols + " gameboard.");
        GameOfLife game = new GameOfLife(rows, cols);
        System.out.println("Printing initial game board.");
        game.printGameboard();

        for (int round=0; round<gameRounds; round++) {

            game.gameboard = game.playGame();
            System.out.println("Printing gameboard after Round: " + (round+1) + ".");
            game.printGameboard();

        }

        System.out.println("Thanks for playing.");

    }

}
