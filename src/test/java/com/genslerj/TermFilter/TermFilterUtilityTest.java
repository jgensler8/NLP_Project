package com.genslerj.TermFilter;

import com.genslerj.QuestionAnswerer.MLEStrategy_BasicCounts;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by genslerj on 4/10/16.
 */
public class TermFilterUtilityTest {
    @Test
    public void testNaiveSplitContainsPerson() {
        String[] result = TermFilterUtility.naiveSplit("Which person created the world or lake?");

        assert(Arrays.asList(result).contains("person"));
    }

    @Test
    public void testNaiveSplitContainsLake() {
        String[] result = TermFilterUtility.naiveSplit("Which person created the world or lake?");

        assert(Arrays.asList(result).contains("lake?"));
    }

    String[] onlyNouns = new String[]{StandfordPartsOfSpeech.NOUN};

    @Test
    public void testNNFinderShouldReturnPerson() {
        String[] result = TermFilterUtility.stanfordExtractByPOS("Which person created the world or lake?", onlyNouns);

        assert(Arrays.asList(result).contains("person"));
    }

    @Test
    public void testNNFinderShouldReturnWorld() {
        String[] result = TermFilterUtility.stanfordExtractByPOS("Which person created the world or lake?", onlyNouns);

        assert(Arrays.asList(result).contains("world"));
    }

    @Test
    public void testNNFinderShouldReturnLake() {
        String[] result = TermFilterUtility.stanfordExtractByPOS("Which person created the world or lake?", onlyNouns);

        assert(Arrays.asList(result).contains("lake"));
    }

    @Test
    public void testNNFinderShouldNotReturnWhich() {
        String[] result = TermFilterUtility.stanfordExtractByPOS("Which person created the world or lake?", onlyNouns);

        assert(!Arrays.asList(result).contains("Which"));
    }

    String[] onlyVerbs = new String[]{StandfordPartsOfSpeech.VERB_PAST_TENSE};

    @Test
    public void testVBFinderShouldReturnCreated() {
        String[] result = TermFilterUtility.stanfordExtractByPOS("Which person created the world or lake?", onlyVerbs);

        assert(Arrays.asList(result).contains("created"));
    }

    @Test
    public void testVBFinderShouldNotReturnLake() {
        String[] result = TermFilterUtility.stanfordExtractByPOS("Which person created the world or lake?", onlyVerbs);

        assert(!Arrays.asList(result).contains("lake"));
    }

    String[] onlyProperNouns = new String[]{StandfordPartsOfSpeech.PROPER_NOUN};

    @Test
    public void testNNPShouldReturnDaniel() {
        String[] result = TermFilterUtility.stanfordExtractByPOS("Did Daniel Craig win an oscar?", onlyProperNouns);

        assert(Arrays.asList(result).contains("Daniel"));
    }

    @Test
    public void testNNPShouldReturnCraig() {
        String[] result = TermFilterUtility.stanfordExtractByPOS("Did Daniel Craig win an oscar?", onlyProperNouns);

        assert(Arrays.asList(result).contains("Craig"));
    }

    @Test
    public void testNNPShouldNotReturnWin() {
        String[] result = TermFilterUtility.stanfordExtractByPOS("Did Daniel Craig win an oscar?", onlyProperNouns);

        assert(!Arrays.asList(result).contains("win"));
    }
}