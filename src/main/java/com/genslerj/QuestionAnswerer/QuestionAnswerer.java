package com.genslerj.QuestionAnswerer;

import com.genslerj.CategoryNet.CategoryNetResult;
import com.genslerj.DatabaseWordNet.DatabaseWordNet;
import com.genslerj.DatabaseWordNet.DatabaseWordNetResult;

/**
 * Created by genslerj on 4/3/16.
 */
public class QuestionAnswerer {
    DatabaseWordNetResult[] corpus;
    QuestionAnswererStrategy strategy;

    public QuestionAnswerer(QuestionAnswererBuilder b) {
        this.corpus = b.corpus;
        this.strategy = b.strategy;
    }

    QuestionAnswererResult predict(String question) {
        return this.strategy.predict(question, this.corpus);
    }

    public static class QuestionAnswererBuilder {
        DatabaseWordNetResult[] corpus;
        QuestionAnswererStrategy strategy;

        public QuestionAnswererBuilder setCorpus(DatabaseWordNetResult[] corpus) {
            this.corpus = corpus;
            return this;
        }

        public QuestionAnswererBuilder setStrategy(QuestionAnswererStrategy strategy) {
            this.strategy = strategy;
            return this;
        }

        public QuestionAnswerer build() {
            return new QuestionAnswerer(this);
        }
    }
}
