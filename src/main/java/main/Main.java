package main;

import com.genslerj.DatabaseTermExtractor.DatabaseQueryAnswerer;
import com.genslerj.DatabaseTermExtractor.DatabaseResources;
import com.genslerj.DatabaseTermExtractor.DatabaseTermExtractor;
import com.genslerj.DatabaseTermExtractor.DatabaseTermExtractorResult;
import com.genslerj.DatabaseWordNet.DatabaseWordNet;
import com.genslerj.DatabaseWordNet.DatabaseWordNetResult;
import com.genslerj.QuestionAnswerLibrary.Categories;
import com.genslerj.QuestionAnswerer.MLEStrategy_NN_NNP_Critical_Counts_WithTieBreak;
import com.genslerj.QuestionAnswerer.QuestionAnswerer;
import com.genslerj.QuestionAnswerer.QuestionAnswererResult;
import com.genslerj.SemanticAttachment.ActualizedSemanticObject;
import com.genslerj.SemanticAttachment.ParseTreeToSemanticObject;
import com.genslerj.SemanticAttachment.SemanticObject;
import com.genslerj.SemanticAttachment.TreebankTagNotSupportedException;
import com.genslerj.TermFilter.TermFilterUtility;
import com.sanchez.QuestionFileReader.QuestionFileReader;
import com.sanchez.QuestionPrinter.QuestionPrinter;
import edu.stanford.nlp.trees.Tree;

import java.io.IOException;

/**
 * Created by genslerj on 4/10/16.
 */
public class Main {

    public static void part1_main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("ERROR You've haven't specified the correct number of arguments. Please supply a file name.");
            throw new Exception("ERROR: Too Few Arguments");
        }

        String questions_file = args[0];

        // Get the questions
        String[] questions;
        try {
            questions = new QuestionFileReader(questions_file).parseQuestions();
        } catch (IOException e) {
            System.out.println("ERROR It appears we can't find that file.");
            throw e;
        }

        // First, pull strings from the database
        DatabaseTermExtractorResult moviesTermExtractor = new DatabaseTermExtractor(DatabaseResources.MOVIES_CONNECTION_STRING, DatabaseResources.DATABASE_NAME, Categories.MOVIES)
                .generateResult();
        DatabaseTermExtractorResult musicTermExtractor = new DatabaseTermExtractor(DatabaseResources.MUSIC_CONNECTION_STRING, DatabaseResources.DATABASE_NAME, Categories.MUSIC)
                .generateResult();
        DatabaseTermExtractorResult geographyTermExtractor = new DatabaseTermExtractor(DatabaseResources.GEOGRAPHY_CONNECTION_STRING, DatabaseResources.DATABASE_NAME, Categories.GEOGRAPHY)
                .generateResult();

        // Next, get related strings
        DatabaseWordNetResult moviesWordNetResult = new DatabaseWordNet().searchWith(moviesTermExtractor);
        DatabaseWordNetResult musicWordNetResult = new DatabaseWordNet().searchWith(musicTermExtractor);
        DatabaseWordNetResult geographyWordNetResult = new DatabaseWordNet().searchWith(geographyTermExtractor);

        // Create the Question Answerer
        QuestionAnswerer answerer = new QuestionAnswerer.QuestionAnswererBuilder()
                .setCorpus(new DatabaseWordNetResult[]{moviesWordNetResult, musicWordNetResult, geographyWordNetResult})
                .setStrategy(new MLEStrategy_NN_NNP_Critical_Counts_WithTieBreak())
                .build();

        // Try answering a questions
        for (String question : questions) {
            QuestionAnswererResult result = answerer.predict(question);
            // Format the output nicely, then print
            String outputString = QuestionPrinter.getPart1Output(question, result.getCategory(), TermFilterUtility.standfordGetParseTree(question));
            System.out.println(outputString);
        }
    }

    public static void part2_main(String[] args) throws Exception {
        if(args.length == 0) {
            System.out.println("ERROR You've haven't specified the correct number of arguments. Please supply a file name.");
            throw new Exception("ERROR: Too Few Arguments");
        }

        String questions_file = args[0];

        // Get the questions
        String[] questions;
        try {
            questions = new QuestionFileReader(questions_file).parseQuestions();
        } catch (IOException e) {
            System.out.println("ERROR It appears we can't find that file.");
            throw e;
        }

        // Try answering a questions
        DatabaseQueryAnswerer moviesDatabaseQueryAnswerer = new DatabaseQueryAnswerer(DatabaseResources.MOVIES_CONNECTION_STRING, DatabaseResources.DATABASE_NAME, "movies");
        for(String question : questions) {

            // parse the questions
            Tree tree = StanfordNLPExample.parse(question).get(0);
            SemanticObject query_object = new ActualizedSemanticObject("");
            String answer;
            try {
                // turn the questions into a semantic object (and hopefully a query)
                query_object = ParseTreeToSemanticObject.parse(tree);
                // query the
                answer = moviesDatabaseQueryAnswerer.runExistsQuery(query_object.getSemanticQuery()) ? "Yes" : "No";
            } catch (TreebankTagNotSupportedException c) {
                answer = String.format("Sorry, we can't answer than question because: %s", c.getMessage());
            } catch (Exception e) {
                answer = String.format("Sorry, a more serious error has occured: %s", e.getMessage());
            }

            // Format the output nicely, then print
            String outputString = QuestionPrinter.getPart2Output(question, query_object.getSemanticQuery().toString(), answer);
            System.out.println(outputString);
        }

    }

    public static void main(String[] args) throws Exception {
//        part1_main(args);
        part2_main(args);
    }
}
