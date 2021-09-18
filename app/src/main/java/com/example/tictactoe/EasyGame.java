package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import static com.example.tictactoe.R.id.BoardSingle;

public class EasyGame extends AppCompatActivity {
    private BoardSingle ticTacToeBoard;
    private Button playAgainButton;
    private Button homeButton;
    private TextView playerTurn;
    private String[] playerNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_game);
        playAgainButton = findViewById(R.id.play);
        homeButton = findViewById(R.id.home);
        playerTurn = findViewById(R.id.display);

        playAgainButton.setVisibility(View.GONE);
        homeButton.setVisibility(View.GONE);

        ticTacToeBoard = findViewById(R.id.Board);

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
        Intent intent = new Intent(EasyGame.this, MainActivity.class);
        startActivity(intent);
    }
}