package au.edu.jcu.cp3406.educationapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class GameActivity extends AppCompatActivity {
    int difficultyValue;
    GamePlay gamePlay;
    int questionNumber = 0;
    int correctAnswer;
    int score = 0;
    private int runTimeInSeconds = 0;
    private boolean timerIsRunning = true;
    private boolean timerWasRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        difficultyValue = 2;

        if (savedInstanceState != null) {
            savedInstanceState.putInt("seconds", runTimeInSeconds);
            savedInstanceState.putBoolean("running", timerIsRunning);
            savedInstanceState.putBoolean("wasRunning", timerWasRunning);
        }
        // set the initial question for the game
        gamePlay = new GamePlay(difficultyValue);
        setQuestion();
        setPossibleAnswers();
        this.correctAnswer = gamePlay.getCorrectAnswer();

        runGameTimer();

    }

    public void onClickGameActivityNav(View view) {
        Intent intent;
        int clickedButtonID = view.getId();
        Button clickedButton = findViewById(clickedButtonID);
        String buttonText = clickedButton.getText().toString().toLowerCase();
        switch (buttonText) {
            case "leave game":
                intent = new Intent(this, MainActivity.class);
                //TODO: add Toast popup to show game was left early
                startActivity(intent);
                break;
            case "skip":
                //TODO: add code to skip the question
                //TODO: add Toast popup to show the question was skipped
                break;
        }
    }

    public void onClickGameAnswer(View view) {
        int clickedAnswerButtonID = view.getId();
        Button clickedButton = findViewById(clickedAnswerButtonID);
        if (Integer.parseInt(clickedButton.getText().toString()) == correctAnswer) {
            gamePlay = new GamePlay(difficultyValue);
            setQuestion();
            setPossibleAnswers();
            this.correctAnswer = gamePlay.getCorrectAnswer();
            questionNumber++;
            score = score + 25 * difficultyValue;
        } else {
            // deduct score point for selecting wrong answer
            score -= 20;
            if (score <=0) { // reset score to 0 if value is negative
                score = 0;
            }
        }
            // check to finish the game event by calling another activity intent
            if (questionNumber > 2) {
                if (score - runTimeInSeconds < 0) {
                    score = 0;
                } else {
                    score = score - runTimeInSeconds;
                }
                Intent intent = new Intent(this, FinishGame.class);
                //TODO: pass score into the activity for display here...
                intent.putExtra("score", score);
                startActivity(intent);
            }
    }

    private void setQuestion() {
        TextView gameTextView = findViewById(R.id.gameTextView);
        @SuppressLint("DefaultLocale") String question = gamePlay.currentQuestion;
        gameTextView.setText(question);
    }

    @SuppressLint("SetTextI18n")
    private void setPossibleAnswers() {
        Collections.shuffle(gamePlay.answersList);

        Button buttonLeft = findViewById(R.id.gameButton1);
        buttonLeft.setText(gamePlay.answersList.get(0).toString());

        Button buttonMiddle = findViewById(R.id.gameButton2);
        buttonMiddle.setText(gamePlay.answersList.get(1).toString());

        Button buttonRight = findViewById(R.id.gameButton3);
        buttonRight.setText(gamePlay.answersList.get(2).toString());


    }

    /**
     * Method that keeps the time in seconds running when user is playing the game and has not lef the app.
     * will increment class attribute runTimeInSeconds by 1 every second. Timer is not displayed to user
     * as the app scores more highly in selecting the correct answers first rather than quickly. this hopes
     * to stop users from just quickly picking random answers to generate a higher score.
     */
    private void runGameTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (timerIsRunning) {
                    runTimeInSeconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", runTimeInSeconds);
        savedInstanceState.putBoolean("isRunning", timerIsRunning);
        savedInstanceState.putBoolean("wasRunning", timerWasRunning);
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (timerWasRunning) {
            timerIsRunning = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        timerWasRunning = timerIsRunning;
        timerIsRunning = false;
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        // reset the game if the user left the app completely to avoid cheating
        score = 0;
        questionNumber = 0;
        runTimeInSeconds = 0;
        timerIsRunning = true;
    }
}