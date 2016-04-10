package com.genslerj.TermFilter;

/**
 * Created by genslerj on 4/10/16.
 */
public class NN_NNP_VERB_Filter implements TermFilterer {

    public String[] filterTerms(String[] input) {
        return input;
    }

    public String[] filterSentence(String input) {
        return TermFilterUtility.stanfordExtractByPOS(input, new String[]{
                StandfordPartsOfSpeech.NOUN,
                StandfordPartsOfSpeech.PROPER_NOUN,
                StandfordPartsOfSpeech.NOUN_PLURAL,
                StandfordPartsOfSpeech.VERB_PAST_TENSE,
                StandfordPartsOfSpeech.VERB_BASE_FORM });
    }

}
