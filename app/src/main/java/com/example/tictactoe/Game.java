package com.example.tictactoe;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

class Game {
    private final int[][] gameBoard;

    //first element = row, seconded element = col, third element = line type
    private int[] winType = {-1, -1, -1};
    private int[] lastmove = new int[2];
    private int player = 1;
    String[] playerNames = {"Player 1", "Player 2"};

    private Button playAgainBTN;
    private Button homeBTN;
    private TextView playerTurn;

    Game(){
        //sets up game board
        gameBoard = new int[3][3];

        for (int r = 0; r < 3; r++){
            for (int c = 0; c < 3; c++){
                gameBoard[r][c] = 0;
            }
        }
    }

    public boolean updateGameBoard(int row, int col){
        //An X will be a 1
        //An O will be a 2
        //Player will be a 1 or 2. if 1 --> X. if 2 --> O

        if (gameBoard[row-1][col-1] == 0){
            gameBoard[row-1][col-1] = player;
            if(player == 1) {
                lastmove[0] = row;
                lastmove[1] = col;
            }
            //happens in the opposite order because the turn is flipped after updateGameBoard
            if (player == 1){
                playerTurn.setText((playerNames[1] + "'s Turn"));
            }
            else{
                playerTurn.setText((playerNames[0] + "'s Turn"));
            }

            return true;
        }
        else {
            return false;
        }
    }
    public HashMap doComputer()
    {
        int i=1;
        //return random move
        HashMap d=new HashMap<Integer,Integer>();
        for (int r = 0; r < 3; r++){
            for (int e = 0; e < 3; e++){
                if(gameBoard[r][e] == 0)
                {
                    d.put(r,e);
                }
            }
        }

        return d;
    }


    public boolean winnerCheck(){
        boolean isWinner = false;

        //Horizontal check
        for (int r = 0; r<3; r++){
            //player 1 win check
            if (gameBoard[r][0] == gameBoard[r][1] && gameBoard[r][0] == gameBoard[r][2] &&
                    gameBoard[r][0] != 0){

                winType = new int[]{r, 0, 1};
                isWinner = true;
                break;
            }
        }

        //Vertical check
        for (int c=0; c<3; c++){
            //player 1 win check
            if (gameBoard[0][c] == gameBoard[1][c] && gameBoard[2][c] == gameBoard[0][c] &&
                    gameBoard[0][c] != 0){

                winType = new int[]{0, c, 2};
                isWinner = true;
                break;
            }
        }

        //negative diagonal check
        if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[2][2] == gameBoard[0][0] &&
                gameBoard[0][0] != 0){

            winType = new int[]{0, 2, 3};
            isWinner = true;
        }

        //positive diagonal check
        if (gameBoard[2][0] == gameBoard[1][1] && gameBoard[0][2] == gameBoard[2][0] &&
                gameBoard[2][0] != 0){

            winType = new int[]{2, 2, 4};
            isWinner = true;
        }

        int boardFilled = 0;

        for (int row=0; row<3; row++){
            for (int col=0; col<3; col++){
                if(gameBoard[row][col] != 0){
                    boardFilled += 1;
                }
            }
        }

        if (isWinner){
            playAgainBTN.setVisibility(View.VISIBLE);
            homeBTN.setVisibility(View.VISIBLE);
            playerTurn.setText((playerNames[player-1] + " won!"));
            return true;
        }
        else if (boardFilled == 9){
            playAgainBTN.setVisibility(View.VISIBLE);
            homeBTN.setVisibility(View.VISIBLE);
            playerTurn.setText("TieGame :(");
            return true;
        }

        return false;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public void setPlayAgainBTN(Button playAgainBTN) {
        this.playAgainBTN = playAgainBTN;
    }

    public void setHomeBTN(Button homeBTN) {
        this.homeBTN = homeBTN;
    }

    public void setPlayerTurn(TextView playerTurn) {
        this.playerTurn = playerTurn;
    }

    public int[] getWinType() {
        return winType;
    }

    public void setPlayerNames(String[] playerNames) {
        this.playerNames = playerNames;
    }

    public void resetGame(){
        for (int r = 0; r < 3; r++){
            for (int c = 0; c < 3; c++){
                gameBoard[r][c] = 0;
            }
        }

        winType[0] = -1;
        winType[1] = -1;
        winType[2] = -1;

        player = 1;

        playAgainBTN.setVisibility(View.GONE);
        homeBTN.setVisibility(View.GONE);
        playerTurn.setText((playerNames[0] + "'s turn"));
    }

    public int findBestMove (int visited[][]) {
        int bestValue = -1000;
        int move = -1;

        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                // 0 for unvisited 1 for cross and 2 for zero
                if (visited[i][j] == 0) {
                    visited[i][j] = 2;
                    int currentVal = minimax(visited,0,false);
                    visited[i][j] = 0;
                    if (currentVal > bestValue) {
                        bestValue = currentVal;
                        move = (i*3) + j%3;
                    }
                }
            }
        }

        return move;
    }
    private static int minimax (int visited[][],int depth,boolean isMax) {
        int score = evaluvate(visited);

        if (score == 10 || score == -10)
            return score;

        if (!isMovesLeft(visited))
            return 0;

        if (isMax) {
            int best = -1000;

            for (int i=0;i<3;i++) {
                for (int j=0;j<3;j++) {
                    if (visited[i][j] == 0) {
                        visited[i][j] = 2;
                        best = Math.max(best,minimax(visited,depth+1,!isMax));
                        visited[i][j] = 0;
                    }
                }
            }
            return best - depth;
        } else {
            int best = 1000;

            for (int i=0;i<3;i++) {
                for (int j=0;j<3;j++) {
                    if (visited[i][j] == 0) {
                        visited[i][j] = 1;
                        best = Math.min(best,minimax(visited,depth+1,!isMax));
                        visited[i][j] = 0;
                    }
                }
            }
            return best + depth;
        }
    }

    private static int evaluvate (int visited[][]) {

        int humanWin = 10;
        int aiWin = -10;
        int draw = 0;

        //check for row
        for (int i=0;i<3;i++) {
            int temp = visited[i][0] * visited[i][1] * visited[i][2];
            if (temp == 1) {//all fields are 1 mean all cross
                return aiWin;
            } else if (temp == 8) {//all fields are 2 means all zero
                return humanWin;
            }
        }

        //check for column
        for (int i=0;i<3;i++) {
            int temp = visited[0][i] * visited[1][i] * visited[2][i];
            if (temp == 1) {//all fields are 1 mean all cross
                return aiWin;
            } else if (temp == 8) {//all fields are 2 means all zero
                return humanWin;
            }
        }

        //check for diagonals
        int d1 = visited[0][0] * visited[1][1] * visited[2][2];
        int d2 = visited[2][0] * visited[1][1] * visited[0][2];
        if (d1 == 1 || d1 == 8) {
            if (d1 == 1) return aiWin;
            return humanWin;
        } else if (d2 == 1 || d2 == 8) {
            if(d2 == 1) return aiWin;
            return humanWin;
        }

        return draw;
    }

    private static boolean isMovesLeft (int visited[][]) {
        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                if (visited[i][j] == 0)
                    return true;
            }
        }
        return false;
    }

}