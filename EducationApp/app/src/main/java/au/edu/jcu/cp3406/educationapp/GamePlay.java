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

    GamePlay(int difficultyValue) {
        this.difficulty = difficultyValue;

        switch (difficultyValue) {
            case 1:
                //easy mode
                setVariableNumbers(10);
                getCorrectAnswer();
                getPossibleAnswers();
                break;
            case 2:
                //standard mode
                setVariableNumbers(20);
                getCorrectAnswer();
                getPossibleAnswers();
                break;
            case 3:
                //hard mode
                break;
        }
    }

    private void getPossibleAnswers() {
        for (int i = 0; i < 3 ; i++) {
            if (i == 0) {
                answersList.add(correctAnswer);
            } else if (i == 1) {
                int randomNumber = random.nextInt((int) (correctAnswer + correctAnswer * 0.2)) + correctAnswer;
                if (answersList.contains(randomNumber)) {
                    randomNumber += random.nextInt(2) + 1;
                }
                answersList.add(randomNumber);

            } else {
                int randomNumber = random.nextInt((int) (correctAnswer - correctAnswer * 0.2)) + correctAnswer;
                if (answersList.contains(randomNumber)) {
                    randomNumber -= random.nextInt(1) + 1;
                }
                answersList.add(randomNumber);
            }
        }
    }

    private void getCorrectAnswer() {
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

                break;
        }
    }

    private void setVariableNumbers(int rangeMax) {
        this.variableNumLarger = random.nextInt(rangeMax) + 10; // stops random numbers below 10
        this.variableNumSmaller = random.nextInt(variableNumLarger);
    }

    public String getQuestion() {
        String question = "";
        switch (difficulty) {
            case 1:
                question = String.format("X = %d + %d", variableNumLarger, variableNumSmaller);
                break;
            case 2:
                int questionChooser = random.nextInt(2) + 1;
                if (questionChooser == 1) {
                    question = String.format("%d = X + %d", variableNumLarger, variableNumSmaller);
                } else {
                    question = String.format("%d = X - %d", variableNumLarger, variableNumSmaller);
                }
        }
        return question;
    }
}
