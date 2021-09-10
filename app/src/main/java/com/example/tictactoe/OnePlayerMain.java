package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OnePlayerMain extends AppCompatActivity {
    private Button easy;
    private Button hard;
    private TextView easytxt;
    private TextView hardtxt;
    private String[] playerNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_player_main);
        easy=findViewById(R.id.easy);
        hard=findViewById(R.id.hard);
        easytxt=findViewById(R.id.textViewEasy);
        hardtxt=findViewById(R.id.textViewHard);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                playerNames = getIntent().getStringArrayExtra("PLAYER_NAMES");
                Intent intent = new Intent(OnePlayerMain.this, EasyGame.class);
                intent.putExtra("PLAYER_NAMES", new String[] {playerNames[0], playerNames[1]});
                startActivity(intent);
            }
        });
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                playerNames = getIntent().getStringArrayExtra("PLAYER_NAMES");
                Intent intent = new Intent(OnePlayerMain.this, HardGame.class);
                intent.putExtra("PLAYER_NAMES", new String[] {playerNames[0], playerNames[1]});
                startActivity(intent);
            }
        });
    }
}