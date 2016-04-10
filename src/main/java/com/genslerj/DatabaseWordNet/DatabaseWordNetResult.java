package com.genslerj.DatabaseWordNet;

/**
 * Created by genslerj on 4/7/16.
 */
public class DatabaseWordNetResult {

    String category;
    String[] relatedStrings;

    public DatabaseWordNetResult(DatabaseWordNetResultBuilder b) {
        this.category = b.category;
        this.relatedStrings = b.relatedStrings;
    }

    public String getCategory() {
        return this.category;
    }

//    public String getCategory(String question) {
//        int geographyTally = 0;
//        int musicTally = 0;
//        int movieTally = 0;
//        String category = "";
//
//        return category;
//    }

    // TODO: Oscar
    public String[] getRelatedStrings() {
        return this.relatedStrings;
    }

    public static class DatabaseWordNetResultBuilder {
        String category;
        String[] relatedStrings;

        public DatabaseWordNetResultBuilder setCategory(String category) {
            this.category = category;
            return this;
        }

        public DatabaseWordNetResultBuilder setRelatedStrings(String[] relatedStrings) {
            this.relatedStrings = relatedStrings;
            return this;
        }

        public DatabaseWordNetResult build() {
            return new DatabaseWordNetResult(this);
        }
    }
}
