package com.example.vote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "voting_db";

    // Table name
    private static final String TABLE_VOTES = "votes";

    // Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_OPTION = "option";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table
        String CREATE_VOTES_TABLE = "CREATE TABLE " + TABLE_VOTES + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_OPTION + " TEXT" + ")";
        db.execSQL(CREATE_VOTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VOTES);

        // Create tables again
        onCreate(db);
    }

    // Insert vote into database
    public void addVote(String option) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_OPTION, option);

        // Inserting Row
        db.insert(TABLE_VOTES, null, values);
        db.close(); // Closing database connection
    }

    // Get vote count for a particular option
    public int getVoteCount(String option) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {KEY_ID};
        String selection = KEY_OPTION + " = ?";
        String[] selectionArgs = {option};

        Cursor cursor = db.query(TABLE_VOTES, projection, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }

}

