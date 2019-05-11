package au.edu.jcu.cp3406.educationapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    int difficultyValue;
    GamePlay gamePlay;
    int questionNumber = 0;
    int correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        difficultyValue = 3;

        gamePlay = new GamePlay(difficultyValue);
        setQuestion();
        setPossibleAnswers();
        this.correctAnswer = gamePlay.getCorrectAnswer();



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
        }
        // check to finish the game event by calling another activity intent
        if (questionNumber > 3) {
            Intent intent = new Intent(this, FinishGame.class);
            //pass score into the activity for display here...
            startActivity(intent);
        }
    }

    private void setQuestion() {
        TextView gameTextView = findViewById(R.id.gameTextView);
        @SuppressLint("DefaultLocale") String question = gamePlay.getQuestion();
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
}