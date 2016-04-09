package main;

import edu.stanford.nlp.dcoref.CorefChain;
import edu.stanford.nlp.dcoref.CorefCoreAnnotations.CorefChainAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.*;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class StanfordNLPExample {

    public static List<String> tokenize(String text) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text);

        // run all Annotators on this text
        pipeline.annotate(document);
        List<CoreLabel> tokens = document.get(TokensAnnotation.class);

        List<String> result = new ArrayList<String>();
        for (CoreLabel token : tokens) {
            // this is the text of the token
            String word = token.get(TextAnnotation.class);
            result.add(word);
        }

        return result;
    }

    public static List<String> sentenceSplit(String text) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text);

        // run all Annotators on this text
        pipeline.annotate(document);
        List<CoreMap> sentences = document.get(SentencesAnnotation.class);

        List<String> result = new ArrayList<String>();
        for (CoreMap sentence : sentences) {
            String sentenceString = sentence.get(TextAnnotation.class);
            result.add(sentenceString);

            // see tokenize(String) method
            List<CoreLabel> tokens = sentence.get(TokensAnnotation.class);
            for (CoreLabel token : tokens) {
                String word = token.get(TextAnnotation.class);
                // ...
            }
        }

        return result;
    }

    public static List<String> posTagging(String text) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text);

        // run all Annotators on this text
        pipeline.annotate(document);
        List<CoreLabel> tokens = document.get(TokensAnnotation.class);

        List<String> result = new ArrayList<String>();
        for (CoreLabel token : tokens) {
            // this is the text of the token
            String pos = token.get(PartOfSpeechAnnotation.class);
            result.add(pos);
        }

        return result;
    }

    public static List<String> lemmatize(String text) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text);

        // run all Annotators on this text
        pipeline.annotate(document);
        List<CoreLabel> tokens = document.get(TokensAnnotation.class);

        List<String> result = new ArrayList<String>();
        for (CoreLabel token : tokens) {
            // this is the text of the token
            String lemma = token.get(LemmaAnnotation.class);
            result.add(lemma);
        }

        return result;
    }

    public static List<String> ner(String text) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text);

        // run all Annotators on this text
        pipeline.annotate(document);
        List<CoreLabel> tokens = document.get(TokensAnnotation.class);

        List<String> result = new ArrayList<String>();
        for (CoreLabel token : tokens) {
            // this is the text of the token
            String nerTag = token.get(NamedEntityTagAnnotation.class);
            result.add(nerTag);
        }

        return result;
    }

    public static List<Tree> parse(String text) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, parse");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text);

        // run all Annotators on this text
        pipeline.annotate(document);
        List<CoreMap> sentences = document.get(SentencesAnnotation.class);

        List<Tree> result = new ArrayList<Tree>();
        for (CoreMap sentence : sentences) {
            Tree tree = sentence.get(TreeAnnotation.class);
            result.add(tree);
        }

        return result;
    }

    public static Map<Integer, CorefChain> coreferenceResolution(String text) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text);

        // run all Annotators on this text
        pipeline.annotate(document);

        return document.get(CorefChainAnnotation.class);
    }

    public static void main(String[] args) {



        String text =
                "Which album by Swift was released in 2014?";

        List<Tree> trees = parse(text);
        for (Tree tree : trees) {
            System.out.println(tree);
        }
        System.out.println("=====================================================");

        text =
                "Victoria Chen, Chief Financial Officer of Megabucks Banking Corp since 2004, saw her pay jump 20%, to $1.3 million, as the 37-year-old also became the Denver-based financial-services company’s president. It has been ten years since she came to Megabucks from rival Lotsabucks.";
        Map<Integer, CorefChain> graph = coreferenceResolution(text);
        for (Entry<Integer, CorefChain> entry : graph.entrySet()) {
            System.out.println(entry);
        }
    }
}