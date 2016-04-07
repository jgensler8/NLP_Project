package com.genslerj.QuestionAnswerLibrary;

import org.junit.Test;

/**
 * Created by genslerj on 4/3/16.
 */
public class QuestionAnswerPairTest {

    private String question = "this is the question";
    private String answer = "this is the answer";

    @Test
    public void getQuestion() throws Exception {
        QuestionAnswerPair p = new QuestionAnswerPair(question, answer);
        assert(p.getQuestion().equals(question));
    }

    @Test
    public void getAnswer() throws Exception {
        QuestionAnswerPair p = new QuestionAnswerPair(question, answer);
        assert(p.getAnswer().equals(answer));
    }

    @Test
    public void constructorTest() {
        QuestionAnswerPair p = new QuestionAnswerPair(question, answer);
        assert(p.question.equals(question));
        assert(p.answer.equals(answer));

        // check that QuestionAnswerPair has its own copy
        question = "new question";
        answer = "new answer";
        assert(!p.question.equals(question));
        assert(!p.answer.equals(answer));
    }
}