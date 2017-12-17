package com.example.michal.scoreralpha;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Michal on 14.12.2017.
 */

public class DBAdapter {
    private static final String DEBUG_TAG = "SqLiteScorer";

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "database.db";
    private static final String DB_SCORE_TABLE = "score";


    public static final String KEY_ID = "_id";
    public static final String ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final int ID_COLUMN = 0;

    public static final String KEY_NAME = "name";
    public static final String NAME_OPTIONS = "TEXT NOT NULL";
    public static final int NAME_COLUMN = 1;

    public static final String KEY_POINTS = "points";
    public static final String POINTS_OPTIONS = "INTEGER DEFAULT 0";
    public static final int POINTS_COLUMN = 2;

    public static final String KEY_WINS = "wins";
    public static final String WINS_OPTIONS = "INTEGER DEFAULT 0";
    public static final int WINS_COLUMN = 3;

    public static final String KEY_LOSES = "loses";
    public static final String LOSES_OPTIONS = "INTEGER DEFAULT 0";
    public static final int LOSES_COLUMN = 4;

    public static final String KEY_MATCHES = "matches";
    public static final String MATCHES_OPTIONS = "INTEGER DEFAULT 0";
    public static final int MATCHES_COLUMN = 5;

    public static final String KEY_GOALSSHOT = "goalsshot";
    public static final String GOALSSHOT_OPTIONS = "INTEGER DEFAULT 0";
    public static final int GOALSSHOT_COLUMN = 6;

    public static final String KEY_GOALSLOST = "goalslost";
    public static final String GOALSLOST_OPTIONS = "INTEGER DEFAULT 0";
    public static final int GOALSLOST_COLUMN = 7;


    private static final String DB_CREATE_SCORE_TABLE = "CREATE TABLE " + DB_SCORE_TABLE + "( " +
            KEY_ID + " " + ID_OPTIONS + ", " + KEY_NAME + " " + NAME_OPTIONS + ", " + KEY_POINTS +
            " " + POINTS_OPTIONS + ", " + KEY_WINS + " " + WINS_OPTIONS + ", " + KEY_LOSES + " " +
            LOSES_OPTIONS + ", " + KEY_MATCHES + " " + MATCHES_OPTIONS + ", " + KEY_GOALSSHOT + " " +
            GOALSSHOT_OPTIONS + ", " + KEY_GOALSLOST + " " + GOALSLOST_OPTIONS + ");";

    private static final String DROP_SCORE_TABLE =
            "DROP TABLE IF EXISTS" + DB_SCORE_TABLE;



    private SQLiteDatabase db;
    private Context context;
    private DatabaseHelper dbHelper;



    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE_SCORE_TABLE);

            Log.d(DEBUG_TAG, "Database creating...");
            Log.d(DEBUG_TAG, "Table " + DB_SCORE_TABLE + " ver." + DB_VERSION + " created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_SCORE_TABLE);

            Log.d(DEBUG_TAG, "Database updating...");
            Log.d(DEBUG_TAG, "Table " + DB_SCORE_TABLE + " updated from ver." + oldVersion + " to ver." + newVersion);
            Log.d(DEBUG_TAG, "All data is lost.");

            onCreate(db);
        }
    }

    public DBAdapter(Context context){
        this.context = context;
    }

    public DBAdapter open(){
        dbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
        try{
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            db = dbHelper.getReadableDatabase();
        }
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public long insertPlayer(String name){
        ContentValues newScoreValues = new ContentValues();
        newScoreValues.put(KEY_NAME, name);
        return db.insert(DB_SCORE_TABLE, null, newScoreValues);
    }

    public Cursor getAllPlayers(){
        String[] columns = {KEY_ID, KEY_NAME, KEY_POINTS};
        return db.query(DB_SCORE_TABLE, columns, null,null,null,null,null);
    }

    public Player getPlayer(long id){
        String[] columns = {KEY_ID, KEY_NAME, KEY_POINTS};
        String where = KEY_ID + "=" + id;
        Cursor cursor = db.query(DB_SCORE_TABLE, columns, where, null, null, null, null);
        Player player = null;
        if(cursor!=null && cursor.moveToFirst()){
            String name = cursor.getString(NAME_COLUMN);
            player = new Player(id, name);
        }
        return player;
    }

    public boolean updatePlayer(Player player){
        long id = player.id;
        String name = player.name;
        return updatePlayer(id, name);
    }

    public boolean updatePlayer(long id, String name){
        String where = KEY_ID + "=" + id;
        ContentValues updatePlayerValues = new ContentValues();
        updatePlayerValues.put(KEY_NAME, name);

        return db.update(DB_SCORE_TABLE, updatePlayerValues, where, null) > 0;
    }

    public boolean deletePlayer(long id){
        String where = KEY_ID + "=" + id;
        return db.delete(DB_SCORE_TABLE, where, null) > 0;
    }

}
