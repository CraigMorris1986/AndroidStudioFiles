package au.edu.jcu.cp3406.educationapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ScoresActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private Cursor cursor;
    TextView[] viewArray = new TextView[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        makeTextViewArray();

        SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
        try {
            db = databaseHelper.getReadableDatabase();
            Log.i("tag", db.toString());
            cursor = db.query("SCORES", new String[]{"NAME", "SCORE"}, null,
                    null, null, null, "SCORE DESC");
            if (cursor != null) {
                cursor.moveToFirst();
                for (int i = 0; i < viewArray.length; i++) {
                    setScoreText(viewArray[i]);
                    if (cursor.moveToNext()) {
                        setScoreText(viewArray[i]);
                    } else {
                        break;
                    }
                }
            }
        } catch (SQLiteException e) {
            Log.i("tag", e.toString());
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    /**
     * Method assigns TextView objects for the class viewArray for iteration. This is used to loop
     * through the top 5 scores and set them from the SQLite database.
     */
    private void makeTextViewArray() {
        viewArray[0] = findViewById(R.id.score1);
        viewArray[1] = findViewById(R.id.score2);
        viewArray[2] = findViewById(R.id.score3);
        viewArray[3] = findViewById(R.id.score4);
        viewArray[4] = findViewById(R.id.score5);
    }

    /**
     * Method to set the score values to a TextView object in the Activity by using String.format().
     * This method uses a Cursor object to retireve values from an SQLite database but the Cursor
     * object is not taken as an argument, thus thius method must exist within scope of a Cursor object.
     * @param textView takes a TextView object to set the text value of.
     */
    private void setScoreText(TextView textView) {
        String nameString = cursor.getString(0);
        int score = cursor.getInt(1);
        String scoreString = Integer.toString(score);
        textView.setText(String.format("%s : %s", nameString, scoreString));
    }

    /**
     * Method to handle the onClick event to return back to the main menu from this Activity. Used
     * only for navigation.
     * @param view
     */
    public void onClickScoresActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
