package com.genslerj.TermFilter;

/**
 * Created by genslerj on 4/10/16.
 */
public interface TermFilterer {
    String[] filterTerms(String[] input);
    String[] filterSentence(String input);
}
