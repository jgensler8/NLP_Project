package com.genslerj.QuestionAnswerer;

import com.genslerj.DatabaseWordNet.DatabaseWordNetResult;
import com.sun.tools.javac.util.Pair;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.*;

/**
 * Created by genslerj on 4/9/16.
 */
public class MLEStrategy implements QuestionAnswererStrategy {

    public QuestionAnswererResult predict(String question, DatabaseWordNetResult[] corpus) {
        // turn question into words
        String[] tokens = this.stanfordSplit(question);

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
        String bestCategory = answers.get(answers.size()-1).fst;

        return new QuestionAnswererResult.QuestionAnswererResultBuilder()
                .setCategory(bestCategory)
                .build();
    }

    public int countStringMatchesIn(String keyword, DatabaseWordNetResult r) {
        int count_matches = 0;
        for(String corpus_word : r.getRelatedStrings()) {
            if(corpus_word.equals(keyword)) {
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

    public String[] naiveSplit(String string) {
        return string.split("\\s+");
    }

    public String[] stanfordSplit(String string) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(string);

        // run all Annotators on this text
        pipeline.annotate(document);
        List<CoreLabel> tokens = document.get(CoreAnnotations.TokensAnnotation.class);

        List<String> result = new ArrayList<String>();
        for (CoreLabel token : tokens) {
            // this is the text of the token
            String word = token.get(CoreAnnotations.TextAnnotation.class);
            result.add(word);
        }
        String[] result_array = new String[result.size()];
        result_array = result.toArray(result_array);
        return result_array;
    }
}
