package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import static com.example.tictactoe.R.id.Board;
public class TwoPlayerGame extends AppCompatActivity {
    private Board ticTacToeBoard;
    private Button playAgainButton;
    private Button homeButton;
    private TextView playerTurn;
    private String[] playerNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player_game2);
        playAgainButton = findViewById(R.id.playAgainButton);
        homeButton = findViewById(R.id.homeButton);
        playerTurn = findViewById(R.id.displayTurn);

        playAgainButton.setVisibility(View.GONE);
        homeButton.setVisibility(View.GONE);

        ticTacToeBoard = findViewById(R.id.TicTacToeGameBoard);

        playerNames = getIntent().getStringArrayExtra("PLAYER_NAMES");

        if (playerNames != null) {
            playerTurn.setText((playerNames[0] + "'s turn"));
        }

        ticTacToeBoard.setUpGame(playAgainButton, homeButton, playerTurn, playerNames);
    }
    public void playAgainPress(View view){
        ticTacToeBoard.resetGame();
        ticTacToeBoard.invalidate();
    }

    public void homePress(View view){
        Intent intent = new Intent(TwoPlayerGame.this, MainActivity.class);
        startActivity(intent);
    }
}