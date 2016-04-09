package com.genslerj.QuestionAnswerLibrary;

/**
 * Created by genslerj on 4/3/16.
 */
public class QuestionAnswerPair {
    String question;
    String answer;
    String category;

    public QuestionAnswerPair(String question, String answer, String category) {
        this.question = question;
        this.answer = answer;
        this.category = category;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswer() {
        return this.answer;
    }

    public String getCategory() { return this.category; }
}
