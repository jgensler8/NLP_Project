package com.genslerj.DatabaseWordNet;

import com.genslerj.DatabaseTermExtractor.DatabaseTermExtractor;
import com.genslerj.DatabaseTermExtractor.DatabaseTermExtractorResult;
import com.genslerj.QuestionAnswerLibrary.Categories;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by genslerj on 4/4/16.
 */
public class DatabaseWordNetTest {

    @Test
    public void testSearchByStringReturnGlossaryWords() {
        DatabaseWordNet net = new DatabaseWordNet();

        String[] result = net.addWordNetWords(new String[]{"oscar"});

        assert(Arrays.asList(result).contains("Academy Award"));
    }

    @Test
    public void testSearchByStringReturnsDefinitionWords() {
        DatabaseWordNet net = new DatabaseWordNet();

        String[] result = net.addWordNetWords(new String[]{"oscar"});

        assert(Arrays.asList(result).contains("award"));
    }

    @Test
    public void testGetWordNetWordsReturnGlossaryWords() {
        DatabaseWordNet net = new DatabaseWordNet();

        String[] result = net.getWordNetWords("oscar");

        assert(Arrays.asList(result).contains("Academy Award"));
    }

    @Test
    public void testGetWordNetWordsReturnsDefinitionWords() {
        DatabaseWordNet net = new DatabaseWordNet();

        String[] result = net.getWordNetWords("oscar");

        assert(Arrays.asList(result).contains("award"));
    }

    @Test
    public void testDatabaseWordNetResultContainsDefintionWord() {
        DatabaseWordNet net = new DatabaseWordNet();
        DatabaseTermExtractorResult termResult = new DatabaseTermExtractorResult.DatabaseTermExtractorResultBuilder()
                .setRelatedStrings(new String[]{"oscar"})
                .setCategory(Categories.MOVIES)
                .build();

        DatabaseWordNetResult result = net.searchWith(termResult);

        assert(Arrays.asList(result.getRelatedStrings()).contains("award"));
    }

    @Test
    public void testDatabaseWordNetResultMatchesCategory() {
        DatabaseWordNet net = new DatabaseWordNet();
        DatabaseTermExtractorResult termResult = new DatabaseTermExtractorResult.DatabaseTermExtractorResultBuilder()
                .setRelatedStrings(new String[]{"oscar"})
                .setCategory(Categories.MOVIES)
                .build();

        DatabaseWordNetResult result = net.searchWith(termResult);

        assert(result.getCategory().equals(termResult.getCategory()));
    }

}
