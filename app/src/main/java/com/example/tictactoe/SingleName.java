package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SingleName extends AppCompatActivity {

    EditText player1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_name);
        player1= findViewById(R.id.player1n);
    }
    public void submitButtonClick(View view){
        String player1Name = player1.getText().toString();
        String player2Name = "Computer";

        Intent intent = new Intent(this, OnePlayerMain.class);
        intent.putExtra("PLAYER_NAMES", new String[] {player1Name, player2Name});
        startActivity(intent);
    }
}