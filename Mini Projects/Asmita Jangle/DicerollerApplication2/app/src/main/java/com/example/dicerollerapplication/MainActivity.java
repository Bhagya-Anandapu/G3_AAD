package com.example.dicerollerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.badge.BadgeUtils;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.scoreText);
        button = findViewById(R.id.buttonRoll);
        imageView = findViewById(R.id.diceImage);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int score = random.nextInt(6) + 1;
                textView.setText(String.valueOf(score));

                switch(score){
                    case 1:
                        imageView.setImageResource(R.drawable.dice1);
                        break;
                    case 2:
                        imageView.setImageResource(R.drawable.dice2);
                        break;
                    case 3:
                        imageView.setImageResource(R.drawable.dice3);
                        break;
                    case 4:
                        imageView.setImageResource(R.drawable.dice4);
                        break;
                    case 5:
                        imageView.setImageResource(R.drawable.dice5);
                        break;
                    case 6:
                        imageView.setImageResource(R.drawable.dice6);
                        break;
                }
            }
        });

    }
}