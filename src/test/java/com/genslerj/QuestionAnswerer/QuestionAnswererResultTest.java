package com.genslerj.QuestionAnswerer;

import com.genslerj.QuestionAnswerLibrary.Categories;
import com.genslerj.QuestionAnswerLibrary.Library;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by genslerj on 4/9/16.
 */
public class QuestionAnswererResultTest {

    @Test
    public void testQuestionAnswererResultBuilderShouldBuildCorrectCategory() {
        String category = Categories.GEOGRAPHY;
        QuestionAnswererResult r = new QuestionAnswererResult.QuestionAnswererResultBuilder()
                .setCategory(category)
                .build();
        assert(r.getCategory().equals(category));
    }

}