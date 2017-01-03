package com.zing.wordbook.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by zhang on 2016/12/27.
 */

public class WordsOpenHelper extends SQLiteOpenHelper {
    public WordsOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, "words.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE words(" +
                "wordsid INTEGER PRIMARY KEY AUTOINCREMENT," +
                "wordsname VARCHAR(30)," +
                "wordsdesc VARCHAR(100)" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
