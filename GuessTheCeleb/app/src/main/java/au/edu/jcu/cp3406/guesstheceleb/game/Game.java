package au.edu.jcu.cp3406.guesstheceleb.game;

import java.util.ArrayList;

public class Game {
    private int questionNumber = 0;
    private int score = 0;
    private Question[] questions;

    public Game(Question[] questions) {
        this.questions = new Question[questions.length];
        this.questions = questions;

    }

    public String getScore() {
//        return String.format("Score: {}/{}", score, questions.length);
        return "Score: " + score + "/" + questions.length;
    }

    public boolean isGameOver() {
        return questionNumber == questions.length;
    }

    public Question next() {
        questionNumber += 1;
        return questions[questionNumber - 1];
    }

    public void updateScore(boolean guess) {
        if (guess) {
            score += 1;
        }
    }
}
