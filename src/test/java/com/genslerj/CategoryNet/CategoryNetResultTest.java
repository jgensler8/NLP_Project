package com.genslerj.CategoryNet;

import org.junit.Test;

/**
 * Created by genslerj on 4/5/16.
 */
public class CategoryNetResultTest {
    @Test
    public void testCategoryNetResultCreation() {
        String query = "Poland";
        String category = "geography";
        double likelihood = .94;

        CategoryNetResult result = new CategoryNetResult.CategoryNetResultBuilder()
                .setQuery(query)
                .setCategory(category)
                .setLikelihood(likelihood)
                .build();

        assert(result.getQuery().equals(query));
        assert(result.getCategory().equals(category));
        assert(result.getLikelihood() == likelihood);
    }
}
