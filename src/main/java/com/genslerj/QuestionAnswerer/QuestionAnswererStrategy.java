package com.genslerj.QuestionAnswerer;

import com.genslerj.DatabaseWordNet.DatabaseWordNetResult;

/**
 * Created by genslerj on 4/9/16.
 */
public interface QuestionAnswererStrategy {
    QuestionAnswererResult predict(String question, DatabaseWordNetResult[] corpus);
}
