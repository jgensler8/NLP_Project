package com.genslerj.DatabaseTermExtractor;

/**
 * Created by genslerj on 4/7/16.
 */
public class DatabaseTermExtractorResult {

    private String category;
    private String[] relatedStrings;

    public DatabaseTermExtractorResult() {
        category = "";
    }

    public DatabaseTermExtractorResult(DatabaseTermExtractorResultBuilder b) {
        this.category = b.category;
        this.relatedStrings = b.relatedStrings;
    }

    public String getCategory() {
        return this.category;
    }

    public String[] getRelatedStrings() {
        return this.relatedStrings;
    }

    public static class DatabaseTermExtractorResultBuilder {
        private String category;
        private String[] relatedStrings;

        public DatabaseTermExtractorResultBuilder setCategory(String category) {
            this.category = category;
            return this;
        }

        public DatabaseTermExtractorResultBuilder setRelatedStrings(String[] relatedStrings) {
            this.relatedStrings = relatedStrings;
            return this;
        }

        public DatabaseTermExtractorResult build() {
            return new DatabaseTermExtractorResult(this);
        }
    }
}
