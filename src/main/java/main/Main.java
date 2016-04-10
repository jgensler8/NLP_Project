package main;

import com.genslerj.DatabaseTermExtractor.DatabaseResources;
import com.genslerj.DatabaseTermExtractor.DatabaseTermExtractor;
import com.genslerj.DatabaseTermExtractor.DatabaseTermExtractorResult;
import com.genslerj.DatabaseWordNet.DatabaseWordNet;
import com.genslerj.DatabaseWordNet.DatabaseWordNetResult;
import com.genslerj.QuestionAnswerLibrary.Categories;
import com.genslerj.QuestionAnswerer.MLEStrategy_NN_NNP_VERB_Counts;
import com.genslerj.QuestionAnswerer.QuestionAnswerer;
import com.genslerj.QuestionAnswerer.QuestionAnswererResult;
import com.genslerj.TermFilter.TermFilterUtility;
import com.sanchez.QuestionFileReader.QuestionFileReader;
import com.sanchez.QuestionPrinter.QuestionPrinter;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by genslerj on 4/10/16.
 */
public class Main {

    public static void main(String[] args) throws Exception {
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
                .setStrategy(new MLEStrategy_NN_NNP_VERB_Counts())
                .build();

        // Try answering a questions
        for(String question : questions) {
            QuestionAnswererResult result = answerer.predict(question);
            String outputString = QuestionPrinter.getQuestionOutput(question, result.getCategory(), TermFilterUtility.standfordGetParseTree(question));
            System.out.println(outputString);
        }
    }
}
