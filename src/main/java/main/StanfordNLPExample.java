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
import java.io.*;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

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

    public static void connectToDatabase() {

        // load the sqlite-JDBC driver using the current class loader
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e)
        {
            System.out.println("Couldn't load the JDBC Driver");
        }

        Connection connection = null;
        try
        {
            // create a database connection
            //connection = DriverManager.getConnection("jdbc:sqlite:/Users/genslerj/Downloads/NLPProject/sqlite-db-dep/SqliteDatabases/WorldGeography.sqlite");
            connection = DriverManager.getConnection("jdbc:sqlite:/Users/Oscar/Downloads/NLP_Project/src/main/resources/SqliteDatabases/WorldGeography.sqlite");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

//            statement.executeUpdate("drop table if exists person");
//            statement.executeUpdate("create table person (id integer, name string)");
//            statement.executeUpdate("insert into person values(1, 'leo')");
//            statement.executeUpdate("insert into person values(2, 'yui')");
            ResultSet rs = statement.executeQuery("select * from Borders");
//            ResultSet rs = statement.executeQuery("select * from person");
            while(rs.next())
            {
                // read the result set
                System.out.println(rs.getString("Country1"));
                System.out.println(rs.getString("Country2"));
//                System.out.println("id = " + rs.getInt("id"));
            }
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //            Run by TA via: java -jar <filename>.jar input.txt

            // File reader taken from: [STACKOVERFLOW LINK]
            String inputFile;
            String inputLine;
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/input.txt"));
            //BufferedReader br = new BufferedReader(new FileReader(args[3]));
            try {
                StringBuilder sb = new StringBuilder();
                inputLine = br.readLine();

            while (inputLine != null) {
                sb.append(inputLine);
                sb.append(System.lineSeparator());
                inputLine = br.readLine();
            }

            inputFile = sb.toString();
        }
        finally {
            br.close();
        }

        // Quick check of file contents
        //System.out.println(inputFile);

        // Spit out a single line (question) from the file
        int questionNo = 0; String text;
        String[] lines = inputFile.split("\n");
        for (String s : lines) {
            text = s;
            questionNo++;
            // Quick check line-by-line
            System.out.println("<QUESTION> " + questionNo + ": " + text);
            //}

            //String text =
            //        "Which album by Swift was released in 2014?";

            List<Tree> trees = parse(text);
            for (Tree tree : trees) {
                System.out.println("<PARSETREE>\n" + tree);
            }
            System.out.println("=====================================================");

            text =
                    "Victoria Chen, Chief Financial Officer of Megabucks Banking Corp since 2004, saw her pay jump 20%, to $1.3 million, as the 37-year-old also became the Denver-based financial-services company’s president. It has been ten years since she came to Megabucks from rival Lotsabucks.";
            Map<Integer, CorefChain> graph = coreferenceResolution(text);
            //for (Entry<Integer, CorefChain> entry : graph.entrySet()) {
            //    System.out.println(entry);
            //}
        }
    }
}