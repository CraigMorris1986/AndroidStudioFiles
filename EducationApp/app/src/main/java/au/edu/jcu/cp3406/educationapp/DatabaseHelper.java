package au.edu.jcu.cp3406.educationapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "user_scores";
    private static final int DATABASE_VERSION = 2;

    /**
     * Constructor method for SQLiteOpenHelper
     * @param context
     */
    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Method that is called when the database is created, code is commented out within the block to
     * create the database table initially and is not required once the table exists.
     * @param db takes a SQLiteDatabase object to operate on
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE SCORES (_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + "NAME TEXT, "
//                + "SCORE INTEGER);");
        updateDatabase(db, 0, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateDatabase(db, oldVersion, newVersion);
    }

    /**
     * Method is called when the database is being upgraded via versioning.
     * @param db takes a SQLiteDatabase object to operate on
     * @param oldVersion takes an int value for the old database version number
     * @param newVersion takes an int value for the new database version number
     */
    private void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE SCORES (_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "SCORE INTEGER);");
        }
    }
}
