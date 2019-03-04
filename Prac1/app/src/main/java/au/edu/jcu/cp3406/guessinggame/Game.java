package au.edu.jcu.cp3406.guessinggame;

import java.util.Random;

public class Game {
    Random rand = new Random();
    int magicNumber = rand.nextInt(10) + 1;

    public boolean check(int i) {
        if (i == magicNumber) {
            return true;
        } else {
            return false;
        }
    }

}

