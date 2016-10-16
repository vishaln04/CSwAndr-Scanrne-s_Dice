package com.example.vishalnagarale.hello;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    Button btnRoll, btnHold, btnReset;
    public boolean userTurn = true;
    public int userScore = 0, compScore = 0, currScore = 0;
    public int[] dice = {R.drawable.dice1, R.drawable.dice2,
            R.drawable.dice3, R.drawable.dice4,
            R.drawable.dice5, R.drawable.dice6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = getApplicationContext();
        imageView = (ImageView) findViewById(R.id.img);
        textView = (TextView) findViewById(R.id.score);
        btnRoll = (Button) findViewById(R.id.roll);
        btnHold = (Button) findViewById(R.id.hold);
        btnReset = (Button) findViewById(R.id.reset);

        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rnd = new Random();
                int dieRoll = rnd.nextInt(6);
                imageView.setImageResource(dice[dieRoll]);
                if (dieRoll == 0) {
                    Log.d("HELLO", "die value is 1 computer turn");
                    userTurn = false;
                    userScore = 0;
                    currScore = compScore;
                    Toast.makeText(context, "Computer Turn", Toast.LENGTH_SHORT).show();
                    disp(userScore, compScore);
                    comp();
                } else {
                    currScore += dieRoll + 1;
                    Log.d("HELLO", "currentscore is " + currScore);
                    userScore = currScore;
                    disp(userScore, compScore);
                }
            }
        });

        btnHold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currScore = compScore;
                userTurn = false;
                Toast.makeText(context, "Computer Turn", Toast.LENGTH_SHORT).show();
                Log.d("HELLO", "User Score & Comp Score & currscore " + userScore + " " + compScore + " " + currScore);
                disp(userScore, compScore);
                comp();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
                disp(userScore, compScore);
            }
        });

    }

    // Reset Function
    public void reset() {
        Context context = getApplicationContext();
        userScore = 0;
        currScore = 0;
        compScore = 0;
        userTurn = true;
        Toast.makeText(context, "Game Reset", Toast.LENGTH_SHORT).show();

    }

    //Display Function
    public void disp(int userScore, int compScore) {
        textView.setText("Your score : " + userScore + "\t Computer score : " + compScore);
    }

    // Computer Function
    public void comp() {
        Context context = getApplicationContext();
        int cur = 0;
        while (cur <= 1) {
            Random rnd = new Random();
            int dieRoll = rnd.nextInt(6);
            imageView.setImageResource(dice[dieRoll]);
            if (dieRoll == 0) {
                userTurn = true;
                compScore = 0;
                currScore = userScore;
                Toast.makeText(context, "User Turn", Toast.LENGTH_SHORT).show();
                //  disp(userScore,compScore);
                break;
            } else {
                currScore += dieRoll + 1;
                compScore = currScore;
            }
            disp(userScore, compScore);
            cur++;
        }
        if (cur > 1) {
            currScore = userScore;
            userTurn = true;
            Toast.makeText(context, "Computer Holding, User Turn", Toast.LENGTH_SHORT).show();
        }
    }
}
