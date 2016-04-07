package com.genslerj.CategoryNet;

import org.junit.Test;

import java.util.Map;

/**
 * Created by genslerj on 4/4/16.
 */
public class CategoryNetTest {

    @Test
    public void testCategoryNetMovies() {
        CategoryNet categoryNet = new CategoryNet();

        // read a database and create map with associated categories
        String entity = "oscar";
        String category = "movies";

        categoryNet.train();

        CategoryNetResult result = categoryNet.guessCategory(entity);
        assert(result.getCategory().equals(category));
    }

    @Test
    public void testCategoryNetGeography() {
        CategoryNet categoryNet = new CategoryNet();

        // read a database and create map with associated categories
        String entity = "Poland";
        String category = "geography";

        categoryNet.train();

        CategoryNetResult result = categoryNet.guessCategory(entity);
        assert(result.getCategory().equals(category));
    }

    @Test
    public void testCategoryNetMusic() {
        CategoryNet categoryNet = new CategoryNet();

        // read a database and create map with associated categories
        String entity = "Beyonce";
        String category = "music";

//        categoryNet.train();

//        CategoryNetResult result = categoryNet.guessCategory(entity);
//        assert(result.category.equals(category));
    }
}
