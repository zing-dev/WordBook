package com.zing.wordbook.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zing.wordbook.MainActivity;
import com.zing.wordbook.dao.WordsOpenHelper;
import com.zing.wordbook.domain.Words;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 2017/1/3.
 */

public class WordsService {
    private WordsOpenHelper wordsOpenHelper = null;
    public WordsService(Context context){
        wordsOpenHelper = new WordsOpenHelper(context,null,null);
    }
    public List<Words> getAllWords(){
        List<Words> words = new ArrayList<>();;
        SQLiteDatabase readableDatabase = wordsOpenHelper.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery("SELECT * FROM words",null);
        int i = 0;
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("wordsname"));
            String desc = cursor.getString(cursor.getColumnIndex("wordsdesc"));
            words.add(i++, new Words(name ,desc));
        }
        cursor.close();
        readableDatabase.close();
        wordsOpenHelper.close();
        wordsOpenHelper = null;
        return words;
    }
    public void addWord(Words word){
        SQLiteDatabase writableDatabase = wordsOpenHelper.getWritableDatabase();
        writableDatabase.execSQL("INSERT INTO words(wordsname,wordsdesc) values(?,?)", new String[]{word.getWordName(),word.getWordDesc()});
        writableDatabase.close();
        wordsOpenHelper.close();
        wordsOpenHelper = null;
    }
}
