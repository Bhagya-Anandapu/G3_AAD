package com.self.dice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    private Button rollbtn;
    private ImageView rollimg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rollbtn=findViewById(R.id.btn);
        rollimg=findViewById(R.id.roll);

        rollbtn.setOnClickListener(v -> rollDice());
        rollDice();

    }
    private void rollDice(){
        rollbtn=findViewById(R.id.btn);
        rollimg=findViewById(R.id.roll);
        rollbtn.setText(getString(R.string.try_again));
        int min=1;
        int max=6;
        Random rand = new Random();

// nextInt as provided by Random is exclusive of the top value so you need to add 1

        int randomNum = rand.nextInt((max - min) + 1) + min;
        switch(randomNum){
            case 1:
                rollimg.setImageResource(R.drawable.dice_1);
                break;
            case 2:
                rollimg.setImageResource(R.drawable.dice_2);
                break;
            case 3:
                rollimg.setImageResource(R.drawable.dice_3);
                break;
            case 4:
                rollimg.setImageResource(R.drawable.dice_4);
                break;
            case 5:
                rollimg.setImageResource(R.drawable.dice_5);
                break;
            case 6:
                rollimg.setImageResource(R.drawable.dice_6);
                break;
        }
    }
}