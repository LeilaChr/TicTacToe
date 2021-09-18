package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HardGame extends AppCompatActivity {

    private BoardHard ticTacToeBoard;
    private Button playAgainButton;
    private Button homeButton;
    private TextView playerTurn;
    private String[] playerNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_game);
        playAgainButton = findViewById(R.id.p);
        homeButton = findViewById(R.id.h);
        playerTurn = findViewById(R.id.d);

        playAgainButton.setVisibility(View.GONE);
        homeButton.setVisibility(View.GONE);

        ticTacToeBoard = findViewById(R.id.B);

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
        Intent intent = new Intent(HardGame.this, MainActivity.class);
        startActivity(intent);
    }
}