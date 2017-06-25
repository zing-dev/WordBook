package com.zing.wordbook.domain;

/**
 * Created by zhang on 2016/12/27.
 */

public class Word {

    private int wordId;
    private String wordName;
    private String wordDesc;

    public Word() {
    }

    public Word(String wordName, String wordDesc) {
        this.wordName = wordName;
        this.wordDesc = wordDesc;
    }

    public Word(int wordId, String wordName, String wordDesc) {
        this.wordId = wordId;
        this.wordName = wordName;
        this.wordDesc = wordDesc;
    }

    public int getWordId() {
        return wordId;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public String getWordDesc() {
        return wordDesc;
    }

    public void setWordDesc(String wordDesc) {
        this.wordDesc = wordDesc;
    }

    @Override
    public String toString() {
        return "Word{" +
                "wordId=" + wordId +
                ", wordName='" + wordName + '\'' +
                ", wordDesc='" + wordDesc + '\'' +
                '}';
    }
}
