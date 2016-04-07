package com.genslerj.DatabaseWordNet;

import com.genslerj.DatabaseTermExtractor.DatabaseTermExtractorResult;
import org.junit.Test;

/**
 * Created by genslerj on 4/4/16.
 */
public class DatabaseWordNetTest {

    @Test
    public void testWordToDatabase() {
        DatabaseWordNet dbWordNet = new DatabaseWordNet();
        DatabaseTermExtractorResult result1 = new DatabaseTermExtractorResult();
        DatabaseWordNetResult result2 = dbWordNet.searchWith(result1);
    }
}
