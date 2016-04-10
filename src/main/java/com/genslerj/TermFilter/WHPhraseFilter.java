package com.genslerj.TermFilter;

/**
 * Created by genslerj on 4/10/16.
 */
public class WHPhraseFilter implements TermFilterer {

    public String[] filterTerms(String[] input) {
        return input;
    }

    public String[] filterSentence(String input) {

        return new String[0];
    }

}
