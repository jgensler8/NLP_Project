package com.genslerj.QuestionAnswerer;

import com.genslerj.DatabaseWordNet.DatabaseWordNet;
import com.genslerj.DatabaseWordNet.DatabaseWordNetResult;
import com.genslerj.TermFilter.NN_NNP_Filter;
import com.genslerj.TermFilter.NN_NNP_VERB_Filter;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by genslerj on 4/9/16.
 */
public class MLEStrategy_NN_NNP_Critical_Counts_WithTieBreak implements QuestionAnswererStrategy {

    public QuestionAnswererResult predict(String question, DatabaseWordNetResult[] corpus) {
        // turn question into words
        String[] tokens = new NN_NNP_Filter().filterSentence(question);

        // filter the tokens by only critical words
        ArrayList<String> criticalWords = new ArrayList<String>();
        ArrayList<String> nonCriticalWords = new ArrayList<String>();
        for(String word : tokens) {
            if(this.isCriticalWord(word, corpus))
            {
                criticalWords.add(word);
            }
            else
            {
                nonCriticalWords.add(word);
            }
        }
        if(criticalWords.size() != 0)
        {
            tokens = new String[criticalWords.size()];
            tokens = criticalWords.toArray(tokens);
        } else {
            tokens = new String[nonCriticalWords.size()];
            tokens = nonCriticalWords.toArray(tokens);
        }

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
                return o1.getValue1().intValue() - o2.getValue1().intValue();
            }
        };
        Collections.sort(answers, naive_match_comparator);
        int topScore = answers.get(answers.size()-1).getValue1().intValue();

        // Collect all winners and see which tie actually mattered more
        ArrayList<Pair<String,Number>> tie_break_answers = new ArrayList<Pair<String, Number>>();
        for(Pair<String, Number> answer : answers) {
            if(answer.getValue1().intValue() == topScore) {
                for(DatabaseWordNetResult r : corpus) {
                    if(answer.getValue0().equals(r.getCategory())) {
                        tie_break_answers.add(new Pair<String, Number>(r.getCategory(), (float)topScore / r.getRelatedStrings().length));
                    }
                }
            }
        }

        Comparator tie_break_match_comparator = new Comparator<Pair<String,Number>>() {
            //            @Override
            public int compare(Pair<String,Number> o1, Pair<String,Number> o2) {
                return o1.getValue1().floatValue() - o2.getValue1().floatValue() > 0 ? 1 : -1;
            }
        };
        Collections.sort(tie_break_answers, tie_break_match_comparator);

        // Note: Collection.sort ascending
        // Collections index from zero
        String bestCategory = tie_break_answers.get(tie_break_answers.size()-1).getValue0();

        return new QuestionAnswererResult.QuestionAnswererResultBuilder()
                .setCategory(bestCategory)
                .build();
    }

    public int countStringMatchesIn(String keyword, DatabaseWordNetResult r) {
        if( Arrays.asList(r.getRelatedStrings()).contains(keyword) ) return 1;
        else return 0;
//        int count_matches = 0;
//        for(String corpus_word : r.getRelatedStrings()) {
//            if(corpus_word != null && corpus_word.contains(keyword)) {
//                count_matches += 1;
//            }
//        }
//        return count_matches;
    }

    public int countAllStringMatchesIn(String[] keywords, DatabaseWordNetResult r) {
        int count_matches = 0;
        for(String word : keywords) {
            count_matches += this.countStringMatchesIn(word, r);
        }
        return count_matches;
    }

    public boolean isCriticalWord(String word, DatabaseWordNetResult[] corpus) {
        boolean foundInOne = false;
        for(DatabaseWordNetResult r : corpus) {
            if(countStringMatchesIn(word, r) > 0) {
                if(!foundInOne)
                {
                    foundInOne = true;
                }
                else // we have found it before
                {
                    return false;
                }
            }
        }
        return foundInOne;
    }

}
