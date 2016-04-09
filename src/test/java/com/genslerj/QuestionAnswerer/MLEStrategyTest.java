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

    @Spy
    DatabaseWordNetResult spyedResult = new DatabaseWordNetResult();

    @Test
    public void testCorpusMatchingIsCorrect() {
        when(spyedResult.getRelatedStrings()).thenReturn(new String[]{"oscar", "Daniel Craig", "watch"});
        when(spyedResult.getCategory()).thenReturn(Categories.MOVIES);
        MLEStrategy s = new MLEStrategy();

        int numberOfMatches = s.countStringMatchesIn("oscar", spyedResult);

        assert(numberOfMatches == 1);
    }

    @Test
    public void testCorpusMatchingMultipleIsCorrect() {
        when(spyedResult.getRelatedStrings()).thenReturn(new String[]{"oscar", "Daniel Craig", "watch", "oscar"});
        when(spyedResult.getCategory()).thenReturn(Categories.MOVIES);
        MLEStrategy s = new MLEStrategy();

        int numberOfMatches = s.countStringMatchesIn("oscar", spyedResult);

        assert(numberOfMatches == 2);
    }

    @Test
    public void testCountAllStringMatchesInDuplicates() {
        when(spyedResult.getRelatedStrings()).thenReturn(new String[]{"oscar", "Daniel Craig", "watch", "oscar"});
        when(spyedResult.getCategory()).thenReturn(Categories.MOVIES);
        MLEStrategy s = new MLEStrategy();

        int numberOfMatches = s.countAllStringMatchesIn(new String[]{"oscar","watch"}, spyedResult);

        assert(numberOfMatches == 3);
    }

    @Test
    public void testCountAllStringMatchesIn() {
        when(spyedResult.getRelatedStrings()).thenReturn(new String[]{"oscar", "Daniel Craig", "watch"});
        when(spyedResult.getCategory()).thenReturn(Categories.MOVIES);
        MLEStrategy s = new MLEStrategy();

        int numberOfMatches = s.countAllStringMatchesIn(new String[]{"oscar","watch"}, spyedResult);

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