package au.edu.jcu.cp3406.guesstheceleb;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;

import org.junit.Test;

import java.io.IOException;

import au.edu.jcu.cp3406.guesstheceleb.game.Game;
import au.edu.jcu.cp3406.guesstheceleb.game.Question;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GameUnitTest extends AppCompatActivity {
//    AssetManager manager = getAssets();
//    calling the getAssets() method for an AssetManager throws an exception for ContextThemeWrapper not mocked
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testGame() {
        Question[] questions = new Question[3];
        String[] answers = new String[]{"bob", "jane", "harry"};
        for (int i = 0; i < 3 ; ++i) {
            questions[i] = new Question(answers[i], null, answers);
        }

        Game game = new Game(questions);
        while (!game.isGameOver()) {
            Question question = game.next();
            game.updateScore(question.check("bob"));
        }
        assertEquals("Score: 1/3", game.getScore());
    }
}