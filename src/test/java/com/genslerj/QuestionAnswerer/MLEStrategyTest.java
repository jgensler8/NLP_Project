package com.genslerj.QuestionAnswerer;

import com.genslerj.DatabaseWordNet.DatabaseWordNetResult;
import com.genslerj.QuestionAnswerLibrary.Categories;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import java.util.Arrays;
import static org.mockito.Mockito.when;

/**
 * Created by genslerj on 4/9/16.
 */
public class MLEStrategyTest {


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCorpusMatchingIsCorrect() {
        MLEStrategy s = new MLEStrategy();
        DatabaseWordNetResult result = new DatabaseWordNetResult.DatabaseWordNetResultBuilder()
                .setCategory(Categories.MOVIES)
                .setRelatedStrings(new String[]{"oscar", "Daniel Craig", "watch"})
                .build();

        int numberOfMatches = s.countStringMatchesIn("oscar", result);

        assert(numberOfMatches == 1);
    }

    @Test
    public void testCorpusMatchingMultipleIsCorrect() {
        MLEStrategy s = new MLEStrategy();
        DatabaseWordNetResult result = new DatabaseWordNetResult.DatabaseWordNetResultBuilder()
                .setCategory(Categories.MOVIES)
                .setRelatedStrings(new String[]{"oscar", "Daniel Craig", "watch", "oscar"})
                .build();

        int numberOfMatches = s.countStringMatchesIn("oscar", result);

        assert(numberOfMatches == 2);
    }

    @Test
    public void testCountAllStringMatchesInDuplicates() {
        MLEStrategy s = new MLEStrategy();
        DatabaseWordNetResult result = new DatabaseWordNetResult.DatabaseWordNetResultBuilder()
                .setCategory(Categories.MOVIES)
                .setRelatedStrings(new String[]{"oscar", "Daniel Craig", "watch", "oscar"})
                .build();

        int numberOfMatches = s.countAllStringMatchesIn(new String[]{"oscar","watch"}, result);

        assert(numberOfMatches == 3);
    }

    @Test
    public void testCountAllStringMatchesIn() {
        MLEStrategy s = new MLEStrategy();
        DatabaseWordNetResult result = new DatabaseWordNetResult.DatabaseWordNetResultBuilder()
                .setCategory(Categories.MOVIES)
                .setRelatedStrings(new String[]{"oscar", "Daniel Craig", "watch"})
                .build();

        int numberOfMatches = s.countAllStringMatchesIn(new String[]{"oscar","watch"}, result);

        assert(numberOfMatches == 2);
    }

    @Test
    public void testNaiveSplitContainsPerson() {
        MLEStrategy s = new MLEStrategy();

        String[] result = s.naiveSplit("Which person created the world or lake?");

        assert(Arrays.asList(result).contains("person"));
    }

    @Test
    public void testNaiveSplitContainsLake() {
        MLEStrategy s = new MLEStrategy();

        String[] result = s.naiveSplit("Which person created the world or lake?");

        assert(Arrays.asList(result).contains("lake?"));
    }
}