package com.genslerj.CategoryNet;

/**
 * Created by genslerj on 4/4/16.
 */
public class CategoryNet {

    CategoryNet train() {
        return this;
    }

    CategoryNetResult guessCategory(String entity) {
        return new CategoryNetResult.CategoryNetResultBuilder()
                .setCategory("movies")
                .setLikelihood(.98)
                .setQuery("entity")
                .build();
    }
}
