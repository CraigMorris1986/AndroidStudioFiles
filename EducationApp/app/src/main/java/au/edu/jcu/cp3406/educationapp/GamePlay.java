package au.edu.jcu.cp3406.educationapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePlay {
    Random random = new Random();
    int difficulty;
    int variableNumLarger;
    int variableNumSmaller;
    int correctAnswer;
    List<Integer> answersList = new ArrayList<>();

    /**
     * Constructor method for the GamePlay class.
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
     * The first random incorrect answer is generated to be larger than the correct answer by a min value
     * of 1 up to 20% of the correct answer.
     * The second value is the same as the first except to be a lower value than the correct answer.
     * Method appends the correct answer integer and the two other random integers to a List<Integer> array.
     * Method checks if the current integer is already in the list and appends the value slightly up or down
     * to avoid duplicate array values.
     */
    private void getPossibleAnswers() {
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                answersList.add(correctAnswer);
            } else if (i == 1) {
                int randomNumber = random.nextInt((int) (correctAnswer + correctAnswer * 0.2)) + correctAnswer + 1;
                if (answersList.contains(randomNumber)) {
                    randomNumber += random.nextInt(2) + 1;
                }
                answersList.add(randomNumber);

            } else {
                int randomNumber = correctAnswer - random.nextInt((int) (correctAnswer - correctAnswer * 0.2)) + 1;
                if (answersList.contains(randomNumber)) {
                    randomNumber -= random.nextInt(1) + 1;
                }
                answersList.add(randomNumber);
            }
        }
    }

    /**
     * This method generates the correct answer for the question based on the difficulty selected
     * and sets it to GamePlay.correctAnswer as an int
     */
    private void generateCorrectAnswer() {
        switch (difficulty) {
            case 1:
                this.correctAnswer = variableNumLarger + variableNumSmaller;
                break;
            case 2:
                String currentQuestion = getQuestion();
                if (currentQuestion.contains("+")) {
                    this.correctAnswer = variableNumLarger - variableNumSmaller;
                } else {
                    this.correctAnswer = variableNumLarger + variableNumSmaller;
                }
                break;
            case 3:
                if (getQuestion().contains("+")) {
                    this.correctAnswer = (variableNumLarger - variableNumSmaller);
                    this.correctAnswer = this.correctAnswer / 2;
                } else {
                    this.correctAnswer = (variableNumLarger + variableNumSmaller);
                    this.correctAnswer = this.correctAnswer * 3;
                }
                break;
        }
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
     * Method to generate questions for the game activity as formatted Strings depending on the global difficulty setting.
     * @return returns a String value of a formatted question used for display to the user.
     */
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
     * getter method to get the correct answer for the current question instance.
     * @return integer value of the correct answer.
     */
    public int getCorrectAnswer() {
        return this.correctAnswer;
    }
}
