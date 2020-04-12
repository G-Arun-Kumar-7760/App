package com.example.arunkumar.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    Button newGame;
    Button option1;
    Button option2;
    Button option3;
    Button option4;
    TextView quesText;
    TextView resultText;
    TextView scoreText;
    TextView timerText;
    int LocationofAnswer;
    CountDownTimer countdown;
    int flag;
    Boolean goon;
    ArrayList<Integer> options = new ArrayList<Integer>();
    int timeleft;
    int score=0;
    int total=0;
    Boolean gameon=true;
    public void newgame(View view)
    {
        view.setVisibility(View.INVISIBLE);
        score=0;
        total=0;
        gameon=true;
        goon=true;
        newQuestion();
        timerText.setText("30s");
        setTimer();
        scoreText.setText("0/0");
        resultText.setText("ATB:)");


    }
 void setTimer()
 {
    CountDownTimer problem= new CountDownTimer(30200,1000)
     {
         @Override
         public void onTick(long l) {
             timeleft=(int)l/1000;
             timerText.setText(timeleft+"s");
         }

         @Override
         public void onFinish() {
             resultText.setText("Done!");
             newGame.setVisibility(View.VISIBLE);
             gameon=false;
             goon=false;
         }
     }.start();

 }
    void setTimer1()
    {
        countdown = new CountDownTimer(3500, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                newQuestion();
            }
        }.start();

    }

    public void chooseAns(View view) {
        if (gameon) {
            if (LocationofAnswer == Integer.parseInt(view.getTag().toString())) {
                score++;
                resultText.setText("Correct:)");
            } else {
                resultText.setText("Wrong:(");
            }
            total++;
            scoreText.setText(score + "/" + total);
            countdown.cancel();
            newQuestion();
        }
    }
    public void play(View view)
    {
        goButton.setVisibility(View.INVISIBLE);
        timerText.setVisibility(View.VISIBLE);
        quesText.setVisibility(View.VISIBLE);
        scoreText.setVisibility(View.VISIBLE);
        option1.setVisibility(View.VISIBLE);
        option2.setVisibility(View.VISIBLE);
        option3.setVisibility(View.VISIBLE);
        option4.setVisibility(View.VISIBLE);
        resultText.setVisibility(View.VISIBLE);
        newQuestion();
        setTimer();
        timerText.setText("30s");
    }
    public void newQuestion() {


        if(goon==true) {
            Random rand = new Random();
            int a = rand.nextInt(41);
            int b = rand.nextInt(41);
            int ans = 0;
            int operation = rand.nextInt(3);
            LocationofAnswer = rand.nextInt(4);
            int wronganswer = 0;
            switch (operation) {
                case 0:
                    ans = a + b;
                    quesText.setText(a + "+" + b);
                    break;
                case 1:
                    ans = a - b;
                    quesText.setText(a + "-" + b);
                    break;
                case 2:
                    a = rand.nextInt(15);
                    b = rand.nextInt(15);
                    ans = a * b;
                    quesText.setText(a + "*" + b);
                    break;
            }
            for (int i = 0; i < 4; i++) {
                if (i == LocationofAnswer) {
                    options.add(ans);
                } else {
                    wronganswer = rand.nextInt(100);
                    while (wronganswer == ans)
                        wronganswer = rand.nextInt(100);
                    options.add(wronganswer);
                }
            }
            option1.setText(Integer.toString(options.get(0)));
            option2.setText(Integer.toString(options.get(1)));
            option3.setText(Integer.toString(options.get(2)));
            option4.setText(Integer.toString(options.get(3)));
            options.clear();
            //if (flag == 1)
                setTimer1();
            //else
              //  flag = 1;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton=findViewById(R.id.gobutton);
        newGame=findViewById(R.id.newButton);
        quesText=findViewById(R.id.quesText);
        resultText=findViewById(R.id.resultText);
        scoreText=findViewById(R.id.scoreText);
        timerText=findViewById(R.id.timerText);
        option1=findViewById(R.id.button0);
        option2=findViewById(R.id.button1);
        option3=findViewById(R.id.button2);
        option4=findViewById(R.id.button3);
        flag=0;
        goon=true;
    }
}
