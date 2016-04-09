package com.genslerj.QuestionAnswerer;

/**
 * Created by genslerj on 4/9/16.
 */
public class QuestionAnswererResult {

    String category;

    public QuestionAnswererResult(QuestionAnswererResultBuilder b) {
        this.category = b.category;
    }

    public String getCategory() {
        return this.category;
    }


    public static class QuestionAnswererResultBuilder {
        String category;

        public QuestionAnswererResultBuilder setCategory(String category) {
            this.category = category;
            return this;
        }

        public QuestionAnswererResult build() {
            return new QuestionAnswererResult(this);
        }
    }
}
