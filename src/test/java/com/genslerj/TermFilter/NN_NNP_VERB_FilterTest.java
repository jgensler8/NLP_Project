package com.genslerj.TermFilter;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by genslerj on 4/10/16.
 */
public class NN_NNP_VERB_FilterTest {

    @Test
    public void testFilterReturnsFrance() {
        String question = "IS France in Europe?";

        String[] result = new NN_NNP_VERB_Filter()
                .filterSentence(question);

        assert(Arrays.asList(result).contains("France"));
    }

    @Test
    public void testFilterReturnsEurope() {
        String question = "Is France in Europe?";

        String[] result = new NN_NNP_VERB_Filter()
                .filterSentence(question);

        assert(Arrays.asList(result).contains("Europe"));
    }

    @Test
    public void testFilterShouldNotContainIs() {
        String question = "Is France in Europe?";

        String[] result = new NN_NNP_VERB_Filter()
                .filterSentence(question);

        assert(!Arrays.asList(result).contains("Is"));
    }

    @Test
    public void testFilterReturnsDanielCraig() {
        String question = "Did Daniel Craig win an oscar?";

        String[] result = new NN_NNP_VERB_Filter()
                .filterSentence(question);

        assert(Arrays.asList(result).contains("Daniel"));
    }

    @Test
    public void testFilterDoesNotReturnAn() {
        String question = "Did Daniel Craig win an oscar?";

        String[] result = new NN_NNP_VERB_Filter()
                .filterSentence(question);

        assert(!Arrays.asList(result).contains("an"));
    }
}