package com.genslerj.QuestionAnswerer;

import com.genslerj.DatabaseWordNet.DatabaseWordNetResult;
import com.genslerj.QuestionAnswerLibrary.Categories;
import com.genslerj.TermFilter.TermFilterUtility;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

/**
 * Created by genslerj on 4/9/16.
 */
public class MLEStrategy_BasicCountsTest {


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCorpusMatchingIsCorrect() {
        MLEStrategy_BasicCounts s = new MLEStrategy_BasicCounts();
        DatabaseWordNetResult result = new DatabaseWordNetResult.DatabaseWordNetResultBuilder()
                .setCategory(Categories.MOVIES)
                .setRelatedStrings(new String[]{"oscar", "Daniel Craig", "watch"})
                .build();

        int numberOfMatches = s.countStringMatchesIn("oscar", result);

        assert(numberOfMatches == 1);
    }

    @Test
    public void testCorpusMatchingMultipleIsCorrect() {
        MLEStrategy_BasicCounts s = new MLEStrategy_BasicCounts();
        DatabaseWordNetResult result = new DatabaseWordNetResult.DatabaseWordNetResultBuilder()
                .setCategory(Categories.MOVIES)
                .setRelatedStrings(new String[]{"oscar", "Daniel Craig", "watch", "oscar"})
                .build();

        int numberOfMatches = s.countStringMatchesIn("oscar", result);

        assert(numberOfMatches == 2);
    }

    @Test
    public void testCountAllStringMatchesInDuplicates() {
        MLEStrategy_BasicCounts s = new MLEStrategy_BasicCounts();
        DatabaseWordNetResult result = new DatabaseWordNetResult.DatabaseWordNetResultBuilder()
                .setCategory(Categories.MOVIES)
                .setRelatedStrings(new String[]{"oscar", "Daniel Craig", "watch", "oscar"})
                .build();

        int numberOfMatches = s.countAllStringMatchesIn(new String[]{"oscar","watch"}, result);

        assert(numberOfMatches == 3);
    }

    @Test
    public void testCountAllStringMatchesIn() {
        MLEStrategy_BasicCounts s = new MLEStrategy_BasicCounts();
        DatabaseWordNetResult result = new DatabaseWordNetResult.DatabaseWordNetResultBuilder()
                .setCategory(Categories.MOVIES)
                .setRelatedStrings(new String[]{"oscar", "Daniel Craig", "watch"})
                .build();

        int numberOfMatches = s.countAllStringMatchesIn(new String[]{"oscar","watch"}, result);

        assert(numberOfMatches == 2);
    }
}