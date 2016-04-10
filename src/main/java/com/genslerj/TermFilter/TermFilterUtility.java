package com.genslerj.TermFilter;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by genslerj on 4/10/16.
 */
public class TermFilterUtility {

    public static String[] naiveSplit(String string) {
        return string.split("\\s+");
    }

    public static String[] stanfordSplit(String string) {
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

    public static String[] stanfordExtractByPOS(String text, String[] stanfordPartsOfSpeech) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text);

        // run all Annotators on this text
        pipeline.annotate(document);
        List<CoreLabel> tokens = document.get(CoreAnnotations.TokensAnnotation.class);

        List<String> result = new ArrayList<String>();
        for (CoreLabel token : tokens) {
            for(String desiredPartOfSpeech : stanfordPartsOfSpeech) {
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                if (pos.equals(desiredPartOfSpeech)) {
                    result.add(token.get(CoreAnnotations.TextAnnotation.class));
                }
            }
        }
        String[] result_array = new String[result.size()];
        result_array = result.toArray(result_array);
        return result_array;
    }
}
