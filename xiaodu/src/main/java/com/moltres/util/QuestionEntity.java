package com.moltres.util;

public class QuestionEntity {
    private String answer;
    private String question;

    public QuestionEntity(String answer, String question) {
        this.answer = answer;
        this.question = question;
    }

    public QuestionEntity() {

    }

    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
