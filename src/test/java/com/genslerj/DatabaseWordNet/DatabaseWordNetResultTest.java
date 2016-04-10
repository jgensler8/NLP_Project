package com.genslerj.DatabaseWordNet;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by genslerj on 4/7/16.
 */
public class DatabaseWordNetResultTest {

    @Test
    public void testDatabaseWordNetResultFunctions() {
        DatabaseWordNetResult r = new DatabaseWordNetResult.DatabaseWordNetResultBuilder()
                .setCategory("category")
                .setRelatedStrings(new String[]{"one", "two"})
                .build();

        assert(r.getCategory().equals("category"));
        assert(Arrays.asList(r.getRelatedStrings()).contains("one"));
    }
}