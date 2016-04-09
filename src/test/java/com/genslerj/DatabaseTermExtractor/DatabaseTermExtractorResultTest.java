package com.genslerj.DatabaseTermExtractor;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by genslerj on 4/7/16.
 */
public class DatabaseTermExtractorResultTest {

    @Test
    public void testDatabaseTermExtractorResultAvailableMethods() {
        DatabaseTermExtractorResult r = new DatabaseTermExtractorResult();
        r.getCategory();
        r.getRelatedStrings();
    }

    @Test
    public void testDatabaseTermExtractorBuilderShouldBuildObject() {
        // arrange
        String category = "sports";
        String[] relatedStrings = new String[]{"soccer", "football", "cricket"};

        // test
        DatabaseTermExtractorResult r = new DatabaseTermExtractorResult.DatabaseTermExtractorResultBuilder()
                .setCategory(category)
                .setRelatedStrings(relatedStrings)
                .build();

        // assert
        assert(r.getCategory().equals(category));
        assert(Arrays.asList(r.getRelatedStrings()).contains(relatedStrings[0]));
        assert(Arrays.asList(r.getRelatedStrings()).contains(relatedStrings[1]));
        assert(Arrays.asList(r.getRelatedStrings()).contains(relatedStrings[2]));
    }
}