package com.genslerj.DatabaseWordNet;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by genslerj on 4/7/16.
 */
public class DatabaseWordNetResultTest {

    @Test
    public void testDatabaseWordNetResultFunctions() {
        DatabaseWordNetResult r = new DatabaseWordNetResult();
        r.getCategory();
        r.getRelatedStrings();
    }
}