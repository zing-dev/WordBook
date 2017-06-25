package com.zing.wordbook.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zing.wordbook.dao.WordOpenHelper;
import com.zing.wordbook.domain.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 2017/1/3.
 *
 */

public class WordService {
    private WordOpenHelper wordOpenHelper = null;

    public WordService(Context context) {
        wordOpenHelper = new WordOpenHelper(context, null, null);
    }

    public List<Word> getAllWords() {
        List<Word> word = new ArrayList<>();
        SQLiteDatabase readableDatabase = wordOpenHelper.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery("SELECT * FROM word", null);
        int i = 0;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("word_id"));
            String name = cursor.getString(cursor.getColumnIndex("word_name"));
            String desc = cursor.getString(cursor.getColumnIndex("word_desc"));
            word.add(i++, new Word(id, name, desc));
        }
        cursor.close();
        readableDatabase.close();
        wordOpenHelper.close();
        return word;
    }

    public void addWord(Word word) {
        SQLiteDatabase writableDatabase = wordOpenHelper.getWritableDatabase();
        writableDatabase.execSQL("INSERT INTO word(word_name,word_desc) values(?,?)", new String[]{word.getWordName(), word.getWordDesc()});
        writableDatabase.close();
        wordOpenHelper.close();
    }

    public void deleteWord(Word word) {
        SQLiteDatabase writableDatabase = wordOpenHelper.getWritableDatabase();
        writableDatabase.delete("word", "word_id=?", new String[]{String.valueOf(word.getWordId())});
        writableDatabase.close();
        wordOpenHelper.close();
    }

    public int count(){
        SQLiteDatabase readableDatabase = wordOpenHelper.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery("SELECT COUNT(*) FROM word", null);
        int count = cursor.getCount();
        cursor.close();
        readableDatabase.close();
        wordOpenHelper.close();
        return count;
    }
}
