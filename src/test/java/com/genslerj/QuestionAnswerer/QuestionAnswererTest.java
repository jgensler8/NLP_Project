package com.genslerj.QuestionAnswerer;

import com.genslerj.QuestionAnswerLibrary.Library;
import com.genslerj.QuestionAnswerLibrary.QuestionAnswerPair;
import org.junit.Test;

/**
 * Created by genslerj on 4/3/16.
 */
public class QuestionAnswererTest {

    @Test
    public void answerAllQuestions() throws Exception {
        QuestionAnswerer a = new QuestionAnswerer();
        for (QuestionAnswerPair p : Library.questions) {
            System.out.println( String.format("%s || %s", p.getQuestion(), p.getAnswer()));
            assert(a.answerQuestion(p.getQuestion()).equals(p.getAnswer()));
        }
    }
}