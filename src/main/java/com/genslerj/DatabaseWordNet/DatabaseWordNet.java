package com.genslerj.DatabaseWordNet;

import com.genslerj.DatabaseTermExtractor.DatabaseTermExtractorResult;
import com.genslerj.QuestionAnswerer.MLEStrategy_BasicCounts;
import com.genslerj.QuestionAnswerer.MLEStrategy_NN_NNP_Critical_Counts;
import com.genslerj.TermFilter.NN_NNP_Filter;
import com.genslerj.TermFilter.TermFilterUtility;
import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.PointerUtils;
import net.sf.extjwnl.data.Word;
import net.sf.extjwnl.data.list.PointerTargetTree;
import net.sf.extjwnl.dictionary.Dictionary;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by genslerj on 4/4/16.
 */
public class DatabaseWordNet {

    Dictionary dictionary;

    public DatabaseWordNet() {
        try {
            this.dictionary = Dictionary.getDefaultResourceInstance();
        } catch (JWNLException e) {
            System.out.println("Can't find WordNet Instance. This could seriously impact performance.");
        }
    }

    public DatabaseWordNetResult searchWith(DatabaseTermExtractorResult r) {
        String[] relatedStrings = this.addWordNetWords(r.getRelatedStrings());

        return new DatabaseWordNetResult.DatabaseWordNetResultBuilder()
                .setCategory(r.getCategory())
                .setRelatedStrings(relatedStrings)
                .build();
    }

    public String[] addWordNetWords(String[] initialStrings) {
        ArrayList<String> results = new ArrayList<String>(Arrays.asList(initialStrings));
        for(String initialString : initialStrings) {
            results.addAll(Arrays.asList(this.getWordNetWords(initialString)));
        }
        String[] result_array = new String[results.size()];
        result_array = results.toArray(result_array);
        return result_array;
//        return initialStrings;
    }

    public String[] getWordNetWords(String initialString) {
        IndexWord initialStringIndexWord;
        try {
            initialStringIndexWord = this.dictionary.lookupIndexWord(POS.NOUN, initialString);
        } catch (JWNLException e) {
            return new String[]{};
        } catch (NullPointerException e) {
            return new String[]{};
        }
        PointerTargetTree hyponyms;
        try {
            hyponyms = PointerUtils.getHyponymTree(initialStringIndexWord.getSenses().get(0));
        } catch (JWNLException e) {
            return new String[]{};
        } catch (NullPointerException e) {
            return new String[]{};
        }
        ArrayList<String> lemmas = new ArrayList<String>();
        String glossary = hyponyms.getRootNode().getSynset().getGloss();
        for(Word word : hyponyms.getRootNode().getSynset().getWords()) {
            lemmas.add(word.getLemma());
        }
        ArrayList<String> l = new ArrayList<String>();
        String[] naive_glossary = new NN_NNP_Filter().filterSentence(glossary);
        l.addAll(Arrays.asList(naive_glossary));
        l.addAll(lemmas);
        String[] result_array = new String[l.size()];
        result_array = l.toArray(result_array);
        return result_array;
    }
}
