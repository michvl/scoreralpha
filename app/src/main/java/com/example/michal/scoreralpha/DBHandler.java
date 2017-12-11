package com.example.michal.scoreralpha;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Michal on 09.12.2017.
 */

public class DBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "scoresInfo";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SCORES = "scores";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_WIN = "win";
    private static final String KEY_LOSE = "lose";
    private static final String KEY_POINTS = "points";
    private static final String KEY_FORM = "form";
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_SCORES +"("
        + KEY_ID + "INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);
// Creating tables again
        onCreate(db);
    }

    public void addPlayer(Player player){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, player.name);
        values.put(KEY_POINTS, player.points);
        values.put(KEY_FORM, player.form);
        values.put(KEY_ID, player.id);
        values.put(KEY_LOSE, player.loses);
        values.put(KEY_WIN, player.wins);

        db.insert(TABLE_SCORES, null, values);
        db.close();
    }
}
