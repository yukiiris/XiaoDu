package com.moltres.util;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/30 0030.
 */

public class KeyWordEntity implements Serializable{
    String keyWord;
    int keyWordNumber;

    public KeyWordEntity(String keyWord, int keyWordNumber) {
        this.keyWord = keyWord;
        this.keyWordNumber = keyWordNumber;
    }

    public KeyWordEntity() {
    }

    public void setKeyWordNumber(int keyWordNumber) {
        this.keyWordNumber = keyWordNumber;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public int getKeyWordNumber() {
        return keyWordNumber;
    }
}