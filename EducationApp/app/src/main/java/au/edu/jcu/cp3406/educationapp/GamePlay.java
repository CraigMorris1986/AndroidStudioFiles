package au.edu.jcu.cp3406.educationapp;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePlay {
    private Random random = new Random();
    private int difficulty;
    private int variableNumLarger;
    private int variableNumSmaller;
    private int correctAnswer;
    public String currentQuestion;
    List<Integer> answersList = new ArrayList<>();

    /**
     * Constructor method for the GamePlay class. Sets the game up through a switch statement based on difficulty setting.
     * @param difficultyValue takes an integer value from 1 to 3 for the difficulty setting for the game.
     */
    GamePlay(int difficultyValue) {
        this.difficulty = difficultyValue;

        switch (difficultyValue) {
            case 1:
                //easy mode
                setVariableNumbers(10);
                generateCorrectAnswer();
                getPossibleAnswers();
                break;
            case 2:
                //standard mode
                setVariableNumbers(20);
                generateCorrectAnswer();
                getPossibleAnswers();
                break;
            case 3:
                //hard mode
                setVariableNumbers(10);
                generateCorrectAnswer();
                getPossibleAnswers();
                break;
        }
    }

    /**
     * Method to generate two random integer values off the correct answer value for this class instance.
     * The two random integers are chosen randomly to either be larger or smaller than the correct answer.
     * This is done to ensure that the correct answer is not always the median value.
     * Method appends the correct answer integer and the two other random integers to a List<Integer> array.
     * Method checks if the current integer is already in the list and appends the value slightly up or down
     * to avoid duplicate array values.
     */
    private void getPossibleAnswers() {
        Random randomSelector = new Random();
        for (int i = 0; i < 3; i++) {
            int randomChoice = randomSelector.nextInt(2);
            if (i == 0) {
                answersList.add(correctAnswer);
            } else {
                if (randomChoice == 0) {
                    // makes the random number larger than the correct answer
                    int randomNumber = random.nextInt((int) (correctAnswer + correctAnswer * 0.2)) + correctAnswer + 1;
                    if (answersList.contains(randomNumber)) {
                        randomNumber += random.nextInt(2) + 1;
                    }
                    answersList.add(randomNumber);
                } else {
                    // makes the random number smaller than the correct answer
                    int randomNumber = correctAnswer - random.nextInt((int) (correctAnswer - correctAnswer * 0.2)) + 1;
                    if (answersList.contains(randomNumber)) {
                        randomNumber -= random.nextInt(2) + 1;
                    }
                    answersList.add(randomNumber);
                }

            }
        }
    }

    /**
     * This method generates the correct answer for the question based on the difficulty selected
     * and sets it to GamePlay.correctAnswer as an int. Method also sets the class attribute String
     * currentQuestion when called to set the question in the class as the getQuestion method will
     * is unreliable when called as it creates question randomly.
     */
    private void generateCorrectAnswer() {
        currentQuestion = getQuestion();
        switch (difficulty) {
            case 1:
                this.correctAnswer = variableNumLarger + variableNumSmaller;
                break;
            case 2:
                if (currentQuestion.contains("+")) {
                    this.correctAnswer = variableNumLarger - variableNumSmaller;
                    break;
                } else {
                    this.correctAnswer = variableNumLarger + variableNumSmaller;
                    break;
                }
            case 3:
                if (currentQuestion.contains("+")) {
                    this.correctAnswer = (variableNumLarger - variableNumSmaller);
                    this.correctAnswer = this.correctAnswer / 2;
                    break;
                } else {
                    this.correctAnswer = (variableNumLarger + variableNumSmaller);
                    this.correctAnswer = this.correctAnswer * 3;
                    break;
                }
        }
    }

    /**
     * Method to generate questions for the game activity as formatted Strings depending on the global difficulty setting.
     *
     * @return returns a String value of a formatted question used for display to the user.
     */
    @SuppressLint("DefaultLocale")
    public String getQuestion() {
        String question = "";
        switch (difficulty) {
            case 1:
                question = String.format("X = %d + %d", variableNumLarger, variableNumSmaller);
                break;
            case 2:
                int questionStandardChooser = random.nextInt(2) + 1;
                if (questionStandardChooser == 1) {
                    question = String.format("%d = X + %d", variableNumLarger, variableNumSmaller);
                } else {
                    question = String.format("%d = X - %d", variableNumLarger, variableNumSmaller);
                }
                break;
            case 3:
                int questionHardChooser = random.nextInt(2) + 1;
                if (questionHardChooser == 1) {
                    question = String.format("%d = X + %d\nWhat is half of X rounded down?", variableNumLarger, variableNumSmaller);
                } else {
                    question = String.format("%d = X - %d\nWhat is X multiplied by 3?", variableNumLarger, variableNumSmaller);
                }
                break;
        }
        return question;
    }

    /**
     * Method sets the two variable numbers as integers required for the game arithmetic, first
     * number will be the larger integer selected and random using taking the argument as the upper
     * bound limit, second number is set using the first number as the upper bound thus generating
     * a smaller number than the first. Method will add 10 to the base random number generated to
     * avoid 0 and integers too close to it.
     *
     * @param rangeMax takes an integer value as argument to set the initial upper bound for the random number generator
     */
    private void setVariableNumbers(int rangeMax) {
        this.variableNumLarger = random.nextInt(rangeMax) + 10; // stops random numbers below 5
        this.variableNumSmaller = random.nextInt(variableNumLarger);
    }

    /**
     * getter method to get the correct answer for the current question instance.
     *
     * @return integer value of the correct answer.
     */
    public int getCorrectAnswer() {
        return this.correctAnswer;
    }
}
