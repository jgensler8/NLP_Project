package com.genslerj.QuestionAnswerLibrary;

/**
 * Created by genslerj on 4/3/16.
 */
public class QuestionAnswerPair {
    String question;
    String answer;

    public QuestionAnswerPair(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswer() {
        return this.answer;
    }
}
