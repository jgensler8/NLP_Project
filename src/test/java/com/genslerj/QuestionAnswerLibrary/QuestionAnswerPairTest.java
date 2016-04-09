package com.genslerj.QuestionAnswerLibrary;

import org.junit.Test;

/**
 * Created by genslerj on 4/3/16.
 */
public class QuestionAnswerPairTest {

    private String question = "this is the question";
    private String answer = "this is the answer";
    private String category = "this is the category";

    @Test
    public void getQuestion() throws Exception {
        QuestionAnswerPair p = new QuestionAnswerPair(question, answer, category);
        assert(p.getQuestion().equals(question));
    }

    @Test
    public void getAnswer() throws Exception {
        QuestionAnswerPair p = new QuestionAnswerPair(question, answer, category);
        assert(p.getAnswer().equals(answer));
    }

    @Test
    public void testgetCategoryShouldReturnCategory() {
        QuestionAnswerPair p = new QuestionAnswerPair(question, answer, category);
        assert(p.getCategory().equals(category));
    }

    @Test
    public void constructorTest() {
        QuestionAnswerPair p = new QuestionAnswerPair(question, answer, category);
        assert(p.question.equals(question));
        assert(p.answer.equals(answer));

        // check that QuestionAnswerPair has its own copy
        question = "new question";
        answer = "new answer";
        assert(!p.question.equals(question));
        assert(!p.answer.equals(answer));
    }
}