package com.moltres.util;

import java.io.Serializable;

public class RobotEntity implements Serializable{


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//qq号不在构造方法中获取，在传递给服务器数据后返回客户端额外设置
    private String name;
    private int qqID;
    //性格常量在CharacterDetail里
    private int character;
    private int stopSpeakingNum;
    private boolean isSpeakAuto;

    public RobotEntity() {

    }

    public RobotEntity(String name,int qqID,int character, int stopSpeakingNum, boolean isSpeakAoto) {
        this.qqID = qqID;
        this.name = name;
        this.character = character;
        this.stopSpeakingNum = stopSpeakingNum;
        this.isSpeakAuto = isSpeakAoto;
    }
    //不含qq号的构造方法
    public RobotEntity(String name,int character, int stopSpeakingNum, boolean isSpeakAoto) {

        this.name = name;
        this.character = character;
        this.stopSpeakingNum = stopSpeakingNum;
        this.isSpeakAuto = isSpeakAoto;
    }

    public int getCharacter() {
        return character;
    }

    public int getStopSpeakingNum() {
        return stopSpeakingNum;
    }

    public boolean isSpeakAuto() {
        return isSpeakAuto;
    }

    public void setCharacter(int character) {
        this.character = character;
    }

    public void setSpeakAuto(boolean speakAuto) {
        isSpeakAuto = speakAuto;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStopSpeakingNum(int stopSpeakingNum) {
        this.stopSpeakingNum = stopSpeakingNum;
    }
    public int getQqID() {
        return qqID;
    }

    public void setQqID(int qqID) {
        this.qqID = qqID;
    }
}
