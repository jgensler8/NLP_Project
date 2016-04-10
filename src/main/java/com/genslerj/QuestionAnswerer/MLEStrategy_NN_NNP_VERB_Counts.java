package com.genslerj.QuestionAnswerer;

import com.genslerj.DatabaseWordNet.DatabaseWordNetResult;
import com.genslerj.TermFilter.NN_NNP_VERB_Filter;
import com.sun.tools.javac.util.Pair;

import java.util.*;

/**
 * Created by genslerj on 4/9/16.
 */
public class MLEStrategy_NN_NNP_VERB_Counts implements QuestionAnswererStrategy {

    public QuestionAnswererResult predict(String question, DatabaseWordNetResult[] corpus) {
        // turn question into words
        String[] tokens = new NN_NNP_VERB_Filter().filterSentence(question);

        // for each category in our corpus, count if word appears in that database or not
        ArrayList<Pair<String,Number>> answers = new ArrayList<Pair<String, Number>>();
        for(DatabaseWordNetResult r : corpus) {
            int all_matches = this.countAllStringMatchesIn(tokens, r);
            answers.add(new Pair<String, Number>(r.getCategory(), all_matches));
        }

        // Take the category with the most matches
        // Note: nothing is done in the case of a tie
        Comparator naive_match_comparator = new Comparator<Pair<String,Number>>() {
//            @Override
            public int compare(Pair<String,Number> o1, Pair<String,Number> o2) {
                return o1.snd.intValue() - o2.snd.intValue();
            }
        };
        Collections.sort(answers, naive_match_comparator);

        // Note: Collection.sort ascending
        // Collections index from zero
        String bestCategory = answers.get(answers.size()-1).fst;

        return new QuestionAnswererResult.QuestionAnswererResultBuilder()
                .setCategory(bestCategory)
                .build();
    }

    public int countStringMatchesIn(String keyword, DatabaseWordNetResult r) {
        int count_matches = 0;
        for(String corpus_word : r.getRelatedStrings()) {
            if(corpus_word != null && corpus_word.contains(keyword)) {
                count_matches += 1;
            }
        }
        return count_matches;
    }

    public int countAllStringMatchesIn(String[] keywords, DatabaseWordNetResult r) {
        int count_matches = 0;
        for(String word : keywords) {
            count_matches += this.countStringMatchesIn(word, r);
        }
        return count_matches;
    }

}
