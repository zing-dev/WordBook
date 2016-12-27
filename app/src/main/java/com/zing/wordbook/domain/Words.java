package com.zing.wordbook.domain;

/**
 * Created by zhang on 2016/12/27.
 */

public class Words {
    private String wordName;
    private String wordDesc;

    public Words() {
    }

    public Words(String wordName, String wordDesc) {
        this.wordName = wordName;
        this.wordDesc = wordDesc;
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
        return "Words{" +
                "wordName='" + wordName + '\'' +
                ", wordDesc='" + wordDesc + '\'' +
                '}';
    }
}
