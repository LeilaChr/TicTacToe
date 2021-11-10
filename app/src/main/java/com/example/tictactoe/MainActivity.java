package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button onePlayer;
    private Button twoPlayer;
    private Button ultimate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onePlayer= (Button) findViewById(R.id.button2);
        twoPlayer= (Button) findViewById(R.id.button);
        ultimate=(Button)findViewById(R.id.buttonU);
        onePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Intent intent = new Intent(MainActivity.this, SingleName.class);
                startActivity(intent);
            }
        });
        twoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Intent intent = new Intent(MainActivity.this, TwoPlayerMain.class);
                startActivity(intent);
            }
        });
        ultimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Intent intent = new Intent(MainActivity.this, UltimateMain.class);
                startActivity(intent);
            }
        });
    }
}