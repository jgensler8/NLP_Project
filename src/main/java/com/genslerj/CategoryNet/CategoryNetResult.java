package com.genslerj.CategoryNet;

/**
 * Created by genslerj on 4/5/16.
 */
public class CategoryNetResult {
    private String query;
    private String category;
    private double likelihood;

    public CategoryNetResult(CategoryNetResultBuilder builder) {
        this.query = builder.query;
        this.category = builder.category;
        this.likelihood = builder.likelihood;
    }

    public String getQuery() {
        return this.query;
    }

    public String getCategory() {
        return this.category;
    }

    public double getLikelihood() {
        return this.likelihood;
    }

    public static class CategoryNetResultBuilder {
        private String query;
        private String category;
        private double likelihood;

        public CategoryNetResultBuilder() {

        }

        public CategoryNetResultBuilder setQuery(String query) {
            this.query = query;
            return this;
        }

        public CategoryNetResultBuilder setCategory(String category) {
            this.category = category;
            return this;
        }

        public CategoryNetResultBuilder setLikelihood(double likelihood) {
            this.likelihood = likelihood;
            return this;
        }

        public CategoryNetResult build() {
            return new CategoryNetResult(this);
        }
    }
}
