package com.rahul.diceroller2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rahul.diceroller2.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView diceImage;
    Random random=new Random();
    TextView textView;
    ImageView imageView;
    Button button;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.scoretext);
        imageView = findViewById(R.id.diceImage);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random=new Random();
                int score= random.nextInt(6)+1;

                textView.setText(String.valueOf(score));

                switch(score){

                    case 1:
                        imageView.setImageResource(R.drawable.img1);
                        break;
                    case 2:
                        imageView.setImageResource(R.drawable.img2);
                        break;
                    case 3:
                        imageView.setImageResource(R.drawable.img3);
                        break;
                    case 4:
                        imageView.setImageResource(R.drawable.img4);
                        break;
                    case 5:
                        imageView.setImageResource(R.drawable.img5);
                        break;
                    case 6:
                        imageView.setImageResource(R.drawable.img6);
                        break;

                }


            }
        });
    }
}