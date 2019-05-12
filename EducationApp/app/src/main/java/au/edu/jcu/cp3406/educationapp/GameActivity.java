package au.edu.jcu.cp3406.educationapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;

public class GameActivity extends AppCompatActivity {
    int difficulty;
    private boolean soundIsOn;
    GamePlay gamePlay;
    int questionNumber = 0;
    int correctAnswer;
    int score = 0;
    private int runTimeInSeconds = 0;
    private boolean timerIsRunning = true;
    private boolean timerWasRunning = false;
    private int gameQuestionAmount = 10;
    TextView questionDisplay;
    MediaPlayer sound;
    Context activityContext = this;
    private Animation animate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        checkForSettingsIntent();
        animate =  AnimationUtils.loadAnimation(this, R.anim.bounce);

        if (savedInstanceState != null) {
            savedInstanceState.putInt("seconds", runTimeInSeconds);
            savedInstanceState.putBoolean("running", timerIsRunning);
            savedInstanceState.putBoolean("wasRunning", timerWasRunning);
        }
        // set the initial question for the game
        generateGameInstance();
        setQuestionDisplay();
        // background timer method to alter final scores
        runGameTimer();
    }

    /**
     * Method to handle button navigation functionality in the apps GameActivity. Creates Toast messages
     * when navigation is used before the game ends.
     *
     * @param view takes a view object as argument to assign clicked Button object ID
     */
    public void onClickGameActivityNav(View view) {
        Intent intent;
        int clickedButtonID = view.getId();
        Button clickedButton = findViewById(clickedButtonID);
        String buttonText = clickedButton.getText().toString().toLowerCase();
        clickedButton.startAnimation(animate);
        switch (buttonText) {
            case "leave game":
                intent = new Intent(this, MainActivity.class);
                Toast toast = Toast.makeText(this, "You have left the game", Toast.LENGTH_SHORT);
                toast.show();
                intent.putExtra("difficulty", difficulty);
                intent.putExtra("soundIsOn", soundIsOn);
                startActivity(intent);
                break;
            case "skip":
                generateGameInstance();
                score -= score * difficulty;
                if (score <= 0) {
                    score = 0;
                }
                playSound(activityContext, R.raw.wrong);
                Toast toastSkip = Toast.makeText(this, "Question Skipped...", Toast.LENGTH_SHORT);
                toastSkip.show();
                break;
        }
    }

    /**
     * Method to set event listener for button click events for the possible answers in the game.
     * Checks if the user has clicked the correct answer and moves the game forward. If wrong answer
     * was clicked creates a Toast message and deducts the total score from the user for the game.
     * On game completion score is passed through an Intent object to FinishGame activity.
     *
     * @param view takes a view object as argument to assign clicked Button object ID
     */
    public void onClickGameAnswer(View view) throws InterruptedException {
        int clickedAnswerButtonID = view.getId();
        Button clickedButton = findViewById(clickedAnswerButtonID);
        if (Integer.parseInt(clickedButton.getText().toString()) == correctAnswer) {
            clickedButton.startAnimation(animate);
            generateGameInstance();
            score = score + 25 * difficulty;
            setQuestionDisplay();
            // if statement to stop the display from showing question amount greater than game length when game is complete
            if (questionNumber > gameQuestionAmount) {
                questionDisplay.setText("");
            }
            playSound(activityContext, R.raw.success);
        } else {
            // deduct score point for selecting wrong answer
            score -= 20;
            playSound(activityContext, R.raw.wrong);
            Toast toastSkip = Toast.makeText(this, "Wrong answer, -20 points", Toast.LENGTH_SHORT);
            toastSkip.show();
            if (score <= 0) { // reset score to 0 if value is negative
                score = 0;
            }
        }
        // check to finish the game event by calling another activity intent
        if (questionNumber > gameQuestionAmount) {
            if (score - runTimeInSeconds < 0) {
                score = 0;
            } else {
                score = score - runTimeInSeconds;
            }
            Intent intent = new Intent(this, FinishGame.class);
            intent.putExtra("score", score);
            intent.putExtra("difficulty", difficulty);
            intent.putExtra("soundIsOn", soundIsOn);
            startActivity(intent);
        }
    }

    /**
     * Methodg generates a game question instance by creating a new GamePlay object and sets the
     * question and the possible answers in the GUI for the user to solve and increments the question counter
     * by 1.
     */
    private void generateGameInstance() {
        gamePlay = new GamePlay(difficulty);
        setQuestion();
        setPossibleAnswers();
        this.correctAnswer = gamePlay.getCorrectAnswer();
        questionNumber++;
    }

    /**
     * Method sets the generated random question String from a GamePlay object to the Activity
     * TextView object for display to the user.
     */
    private void setQuestion() {
        TextView gameTextView = findViewById(R.id.gameTextView);
        @SuppressLint("DefaultLocale") String question = gamePlay.currentQuestion;
        gameTextView.setText(question);
    }

    /**
     * Method sets the possible answers for a game instance from the array created by a GamePlay object
     * to the activity game buttons for the user to choose from.
     */
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
     * Method assigns a MediaPlayer object to the class sound attribute and plays a sound if the class
     * attribute soundIsOn is set to true.
     * @param context takes the activity Context object for the MediaPlayer create() method
     * @param soundID takes the resource int ID for the sound file from R.raw.soundfile
     */
    private void playSound(Context context, int soundID) {
        if (soundIsOn) {
            sound = MediaPlayer.create(context, soundID);
            sound.start();
        }
    }

    private void checkForSettingsIntent() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            Intent settingsIntent = getIntent();
            difficulty = settingsIntent.getIntExtra("difficulty", 2);
            soundIsOn = settingsIntent.getBooleanExtra("soundIsOn", true);
        } else {
            difficulty = 2;
            soundIsOn = true;
        }
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

    private void setQuestionDisplay() {
        questionDisplay = findViewById(R.id.questionDisplay);
        String displayString = String.format("Question number %s of %s", Integer.valueOf(questionNumber).toString(), Integer.valueOf(gameQuestionAmount).toString());
        questionDisplay.setText(displayString);
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