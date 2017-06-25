package com.zing.wordbook.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by zhang on 2016/12/27.
 * 
 */

public class WordOpenHelper extends SQLiteOpenHelper {

    public WordOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, "words.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
//                "DROP TABLE `word`;" +
                "CREATE TABLE word(" +
                        "word_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "word_name VARCHAR(30)," +
                        "word_desc VARCHAR(100)" +
                        ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(
                "CREATE TABLE word(" +
                        "word_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "word_name VARCHAR(30)," +
                        "word_desc VARCHAR(100)" +
                        ")");
    }
}
