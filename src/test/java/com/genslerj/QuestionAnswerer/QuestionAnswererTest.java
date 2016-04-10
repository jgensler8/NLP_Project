package com.genslerj.QuestionAnswerer;

import com.genslerj.DatabaseTermExtractor.DatabaseResources;
import com.genslerj.DatabaseTermExtractor.DatabaseTermExtractor;
import com.genslerj.DatabaseTermExtractor.DatabaseTermExtractorResult;
import com.genslerj.DatabaseWordNet.DatabaseWordNet;
import com.genslerj.DatabaseWordNet.DatabaseWordNetResult;
import com.genslerj.QuestionAnswerLibrary.Categories;
import com.genslerj.QuestionAnswerLibrary.Library;
import com.genslerj.QuestionAnswerLibrary.QuestionAnswerPair;
import com.genslerj.TermFilter.TermFilterUtility;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Arrays;

/**
 * Created by genslerj on 4/3/16.
 */
public class QuestionAnswererTest {

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void answerAllQuestions() throws Exception {
//        QuestionAnswerer a = new QuestionAnswerer();
//        for (QuestionAnswerPair p : Library.questions) {
//            System.out.println( String.format("%s || %s", p.getQuestion(), p.getAnswer()));
//            assert(a.answerQuestion(p.getQuestion()).equals(p.getAnswer()));
//        }
//    }

    @Spy
    DatabaseWordNetResult mockResult = new DatabaseWordNetResult.DatabaseWordNetResultBuilder()
            .setCategory(Categories.GEOGRAPHY)
            .setRelatedStrings(new String[]{"mountain", "lake", "world"})
            .build();
    @Spy
    DatabaseWordNetResult mockResult2 = new DatabaseWordNetResult.DatabaseWordNetResultBuilder()
            .setCategory(Categories.MOVIES)
            .setRelatedStrings(new String[]{"screen", "oscar", "Daniel Craig"})
            .build();

    @Test
    public void testQuestionAnswererBuilderShouldBuildQuestionAnswererBuilder() {
        DatabaseWordNetResult[] corpus = new DatabaseWordNetResult[]{mockResult};
        QuestionAnswerer b = new QuestionAnswerer.QuestionAnswererBuilder()
                .setCorpus(corpus)
                .setStrategy(new MLEStrategy_BasicCounts())
                .build();
    }

    @Test
    public void testGeographyQuestionShouldGuessGeography() {
        DatabaseWordNetResult[] corpus = new DatabaseWordNetResult[]{mockResult};
        String question = "Is the mountain next to the lake?";

        QuestionAnswererResult result = new QuestionAnswerer.QuestionAnswererBuilder()
                .setCorpus(corpus)
                .setStrategy(new MLEStrategy_BasicCounts())
                .build()
                .predict(question);

        assert( result.getCategory().equals(Categories.GEOGRAPHY));
    }

    @Test
    public void testMovieQuestionShouldGuessMovieCategory() {
        DatabaseWordNetResult[] corpus = new DatabaseWordNetResult[]{mockResult2};
        String question = "Did Daniel Craig play in a movie?";

        QuestionAnswererResult result = new QuestionAnswerer.QuestionAnswererBuilder()
                .setCorpus(corpus)
                .setStrategy(new MLEStrategy_BasicCounts())
                .build()
                .predict(question);

        assert( result.getCategory().equals(Categories.MOVIES));
    }

    @Test
    public void testMultiCategory_MovieQuestionShouldGuessMovieCategory() {
        DatabaseWordNetResult[] corpus = new DatabaseWordNetResult[]{mockResult, mockResult2};
        String question = "Did Daniel Craig win an oscar?";

        QuestionAnswererResult result = new QuestionAnswerer.QuestionAnswererBuilder()
                .setCorpus(corpus)
                .setStrategy(new MLEStrategy_BasicCounts())
                .build()
                .predict(question);

        assert( result.getCategory().equals(Categories.MOVIES));
    }

    @Test
    public void testMultiCategory_GeographyQuestionShouldGuessGeographyCategory() {
        DatabaseWordNetResult[] corpus = new DatabaseWordNetResult[]{mockResult, mockResult2};
        String question = "Is the mountain next to the lake?";

        QuestionAnswererResult result = new QuestionAnswerer.QuestionAnswererBuilder()
                .setCorpus(corpus)
                .setStrategy(new MLEStrategy_BasicCounts())
                .build()
                .predict(question);

        assert( result.getCategory().equals(Categories.GEOGRAPHY));
    }

    @Test
    public void testWordOverlap_MovieQuestionShouldGuessMovieCategory() {
        DatabaseWordNetResult[] corpus = new DatabaseWordNetResult[]{mockResult, mockResult2};
        String question = "Which person won the oscar?";

        QuestionAnswererResult result = new QuestionAnswerer.QuestionAnswererBuilder()
                .setCorpus(corpus)
                .setStrategy(new MLEStrategy_BasicCounts())
                .build()
                .predict(question);

        assert( result.getCategory().equals(Categories.MOVIES));
    }

    @Test
    public void testWordOverlap_GeographyQuestionShouldGuessGeographyCategory() {
        DatabaseWordNetResult[] corpus = new DatabaseWordNetResult[]{mockResult, mockResult2};
        String question = "Which person created the world or lake?";

        QuestionAnswererResult result = new QuestionAnswerer.QuestionAnswererBuilder()
                .setCorpus(corpus)
                .setStrategy(new MLEStrategy_BasicCounts())
                .build()
                .predict(question);

        assert( result.getCategory().equals(Categories.GEOGRAPHY));
    }

    @Test
    public void testStanfordSplitting() {
        MLEStrategy_BasicCounts s = new MLEStrategy_BasicCounts();

        String[] result = TermFilterUtility.stanfordSplit("Which person created the world or lake?");

        assert(Arrays.asList(result).contains("lake"));
    }

    //@Test
    public void addingAllDatabasesShouldPassSimpleMovieExample() throws Exception {
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

        System.out.println(Arrays.toString(moviesWordNetResult.getRelatedStrings()));

        // Create the Question Answerer
        QuestionAnswerer answerer = new QuestionAnswerer.QuestionAnswererBuilder()
                .setCorpus(new DatabaseWordNetResult[]{moviesWordNetResult, musicWordNetResult, geographyWordNetResult})
                .setStrategy(new MLEStrategy_BasicCounts())
                .build();

        // Try answering a questions
        QuestionAnswererResult questionResult = answerer.predict("What year did DiCaprio win an oscar?");
        assert(questionResult.getCategory().equals(Categories.MOVIES));
    }

    @Test
    public void addingAllDatabasesShouldPassMostLibraryExmaples() throws Exception {
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

        System.out.println(Arrays.toString(moviesWordNetResult.getRelatedStrings()));
        System.out.println(Arrays.toString(musicTermExtractor.getRelatedStrings()));
        System.out.println(Arrays.toString(geographyTermExtractor.getRelatedStrings()));

        // Create the Question Answerer
        QuestionAnswerer answerer = new QuestionAnswerer.QuestionAnswererBuilder()
                .setCorpus(new DatabaseWordNetResult[]{moviesWordNetResult, musicWordNetResult, geographyWordNetResult})
                .setStrategy(new MLEStrategy_NN_NNP_VERB_Counts())
                .build();

        // Try answering a questions
        int correct = 0;
        int incorrect = 0;
        for(QuestionAnswerPair p : Library.questions) {
            QuestionAnswererResult questionResult = answerer.predict(p.getQuestion());
            if(questionResult.category.equals(p.getCategory())) {
                correct += 1;
            }
            else {
                System.out.println(String.format("Incorrectly guessed question (%s) with category (%s) as category (%s)",p.getQuestion(),p.getCategory(),questionResult.getCategory()));
                incorrect += 1;
            }
        }
        float percentCorrect = ((float)correct)/(correct + incorrect);
        System.out.println(String.format("Correct: %d, Incorrect: %d, Percent Correct %f", correct, incorrect, percentCorrect));
        assert(percentCorrect >= .9);
    }
}