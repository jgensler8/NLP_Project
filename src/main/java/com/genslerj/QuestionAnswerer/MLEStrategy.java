package com.genslerj.QuestionAnswerer;

import com.genslerj.DatabaseWordNet.DatabaseWordNetResult;
import com.genslerj.QuestionAnswerLibrary.Categories;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by genslerj on 4/9/16.
 */
public class MLEStrategy implements QuestionAnswererStrategy {

    public QuestionAnswererResult predict(String question, DatabaseWordNetResult[] corpus) {
        // turn question into words
        // TODO: Turn this into Standford Parsing
        String[] tokens = question.split("\\\\s+");

        // for each category in our corpus, count if word appears in that database or not
        ArrayList<Pair<String,Number>> answers = new ArrayList<Pair<String, Number>>();
        for(DatabaseWordNetResult r : corpus) {
            int count_matches = 0;
            for(String word : tokens) {
                if(Arrays.asList(r.getRelatedStrings()).contains(word)) {
                    count_matches += 1;
                }
            }
            answers.add(new Pair<String, Number>(r.getCategory(),count_matches));
        }

        // Take the category with the most matches
        // Note: nothing is done in the case of a tie
        Comparator naive_match_comparator = new Comparator<Pair<String,Number>>() {
//            @Override
            public int compare(Pair<String,Number> o1, Pair<String,Number> o2) {
                System.out.println(String.format("matching %s %s", o1.snd.toString(), o2.snd.toString()));
                return o1.snd.intValue() - o2.snd.intValue();
            }
        };
        Collections.sort(answers, naive_match_comparator);
        String bestCategory = answers.get(0).fst;

        return new QuestionAnswererResult.QuestionAnswererResultBuilder()
                .setCategory(bestCategory)
                .build();
    }
}
