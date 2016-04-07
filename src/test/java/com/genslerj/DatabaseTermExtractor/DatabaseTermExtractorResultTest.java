package com.genslerj.DatabaseTermExtractor;

import org.junit.Test;

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
}