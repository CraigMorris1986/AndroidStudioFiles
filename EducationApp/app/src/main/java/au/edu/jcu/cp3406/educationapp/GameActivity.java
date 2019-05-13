package au.edu.jcu.cp3406.educationapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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

public class GameActivity extends AppCompatActivity implements SensorEventListener {
    int difficulty;
    private boolean soundIsOn;
    GamePlay gamePlay;
    TextView questionDisplay;
    MediaPlayer sound;
    Context activityContext = this;
    int questionNumber = 0;
    int correctAnswer;
    int score = 0;
    private int runTimeInSeconds = 0;
    private boolean timerIsRunning = true;
    private boolean timerWasRunning = false;
    private int gameQuestionAmount = 2;
    private long lastTimeShook = 0;
    private Animation animate;
    private SensorManager sensorManager;
    private Sensor sensorAccelerometer;
    private BouceInterpolatorHelper interpolator;

    /**
     * Class onCreate method that is automatically called when the activity starts. Method checks
     * for Intent objects from other activities for the apps difficulty and sound setting value.
     * Method then sets the accelerometer for use within the class as a class attribute and does the same with the
     * animation for buttons. Method then calls generateGameInstance() and setQuestionDisplay() to set the game instance
     * and then begins the background timer for scoring.
     * @param savedInstanceState takes a Bundle object as a parameter as the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        checkForSettingsIntent();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        interpolator = new BouceInterpolatorHelper(0.35, 20);
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
    public void onClickGameAnswer(View view) {
        int clickedAnswerButtonID = view.getId();
        Button clickedButton = findViewById(clickedAnswerButtonID);
        if (Integer.parseInt(clickedButton.getText().toString()) == correctAnswer) {
            clickedButton.startAnimation(animate);
            generateGameInstance();
            // resets the buttons clickable attribute to true and colour back to blue
            makeAnswersButtonsClickable();
            score = score + 25 * difficulty;
            setQuestionDisplay();
            // if statement to stop the display from showing question amount greater than game length when game is complete
            if (questionNumber > gameQuestionAmount) {
                questionDisplay.setText("");
            }
            playSound(activityContext, R.raw.success);
        } else {
            // deduct score point for selecting wrong answer and make the button un-clickable
            clickedButton.setClickable(false);
            clickedButton.setTextColor(Color.parseColor("red"));
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
     * to the activity game buttons for the user to choose from. Method also sets animation effect
     * for the possible answers when text is set to the button object
     */
    @SuppressLint("SetTextI18n")
    private void setPossibleAnswers() {
        Collections.shuffle(gamePlay.answersList);
        animate = AnimationUtils.loadAnimation(this, R.anim.bounce_long);
        animate.setInterpolator(interpolator);

        Button buttonLeft = findViewById(R.id.gameButton1);
        buttonLeft.setText(gamePlay.answersList.get(0).toString());
        buttonLeft.startAnimation(animate);

        Button buttonMiddle = findViewById(R.id.gameButton2);
        buttonMiddle.setText(gamePlay.answersList.get(1).toString());
        buttonMiddle.startAnimation(animate);

        Button buttonRight = findViewById(R.id.gameButton3);
        buttonRight.setText(gamePlay.answersList.get(2).toString());
        buttonRight.startAnimation(animate);

    }

    /**
     * Method creates a array of Button objects and stores the possible answers button objects
     * within it. Method then iterates through the array and sets all buttons clickable attribute to true.
     * Method also resets colour value of the buttons text to #3F51B5 (blue).
     */
    private void makeAnswersButtonsClickable() {
        Button[] buttonArray = new Button[3];
        buttonArray[0] = findViewById(R.id.gameButton1);
        buttonArray[1] = findViewById(R.id.gameButton2);
        buttonArray[2] = findViewById(R.id.gameButton3);
        for (Button button :
                buttonArray) {
            button.setClickable(true);
            button.setTextColor(Color.parseColor("#3F51B5"));
        }
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

    /**
     * Method that checks if an Intent object was send from another activity within the app. If not
     * it sets the difficulty level to an int of 2 and the soundIsOn boolean to true.
     */
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

    /**
     * A method to hold the class values needed for the background timer into a saved instance state.
     * @param savedInstanceState takes a Bundle object as a parameter
     */
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
        sensorManager.registerListener(this, sensorAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
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

    /**
     * Method to handle the event changes for Sensor objects as an event listener. Method checks if the
     * device accelerometer is available and if so gets the current system time and checks if the
     * device registered a shake event in the last two seconds. If the device hasn't registered a shake event in the last two seconds
     * and the accelerometers acceleration value is greater than 800 it animates the possible answers
     * in the game instance using the bounce_long animation.
     * @param event takes a SensorEvent object as a parameter
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            if ((curTime - lastTimeShook) > 2000) {

                float xValue = event.values[0];
                float yValue = event.values[1];
                float zValue = event.values[2];

                double acceleration = Math.sqrt(Math.pow(xValue, 2) + Math.pow(yValue, 2) + Math.pow(zValue, 2)) - SensorManager.GRAVITY_EARTH;
                if (acceleration > 800) {
                    lastTimeShook = curTime;
                    // sets animation values
                    animate = AnimationUtils.loadAnimation(this, R.anim.bounce_long);
                    animate.setInterpolator(interpolator);
                    // perform animation same as the setPossibleAnswers() class method.
                    Button buttonLeft = findViewById(R.id.gameButton1);
                    buttonLeft.startAnimation(animate);
                    Button buttonMiddle = findViewById(R.id.gameButton2);
                    buttonMiddle.startAnimation(animate);
                    Button buttonRight = findViewById(R.id.gameButton3);
                    buttonRight.startAnimation(animate);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Method must be implemented by the SensorEventListener interface but doesn't need to do anything.
    }
}