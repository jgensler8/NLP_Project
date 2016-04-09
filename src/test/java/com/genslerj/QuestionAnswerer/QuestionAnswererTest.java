package com.genslerj.QuestionAnswerer;

import com.genslerj.DatabaseWordNet.DatabaseWordNetResult;
import com.genslerj.QuestionAnswerLibrary.Categories;
import com.genslerj.QuestionAnswerLibrary.Library;
import com.genslerj.QuestionAnswerLibrary.QuestionAnswerPair;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Arrays;

import static org.mockito.Mockito.when;

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
    DatabaseWordNetResult mockResult = new DatabaseWordNetResult();
    @Spy
    DatabaseWordNetResult mockResult2 = new DatabaseWordNetResult();

    @Test
    public void testQuestionAnswererBuilderShouldBuildQuestionAnswererBuilder() {
        DatabaseWordNetResult[] corpus = new DatabaseWordNetResult[]{mockResult};
        QuestionAnswerer b = new QuestionAnswerer.QuestionAnswererBuilder()
                .setCorpus(corpus)
                .setStrategy(new MLEStrategy())
                .build();
    }

    @Test
    public void testGeographyQuestionShouldGuessGeography() {
        when(mockResult.getCategory()).thenReturn(Categories.GEOGRAPHY);
        when(mockResult.getRelatedStrings()).thenReturn(new String[]{"mountain", "lake", "world"});
        DatabaseWordNetResult[] corpus = new DatabaseWordNetResult[]{mockResult};
        String question = "Is the mountain next to the lake?";

        QuestionAnswererResult result = new QuestionAnswerer.QuestionAnswererBuilder()
                .setCorpus(corpus)
                .setStrategy(new MLEStrategy())
                .build()
                .predict(question);

        assert( result.getCategory().equals(Categories.GEOGRAPHY));
    }

    @Test
    public void testMovieQuestionShouldGuessMovieCategory() {
        when(mockResult.getCategory()).thenReturn(Categories.MOVIES);
        when(mockResult.getRelatedStrings()).thenReturn(new String[]{"screen", "oscar", "Daniel Craig"});
        DatabaseWordNetResult[] corpus = new DatabaseWordNetResult[]{mockResult};
        String question = "Did Daniel Craig play in a movie?";

        QuestionAnswererResult result = new QuestionAnswerer.QuestionAnswererBuilder()
                .setCorpus(corpus)
                .setStrategy(new MLEStrategy())
                .build()
                .predict(question);

        assert( result.getCategory().equals(Categories.MOVIES));
    }

    @Test
    public void testMultiCategory_MovieQuestionShouldGuessMovieCategory() {
        when(mockResult.getCategory()).thenReturn(Categories.MOVIES);
        when(mockResult.getRelatedStrings()).thenReturn(new String[]{"screen", "oscar", "Daniel Craig"});
        when(mockResult2.getCategory()).thenReturn(Categories.GEOGRAPHY);
        when(mockResult2.getRelatedStrings()).thenReturn(new String[]{"mountain", "lake", "world"});
        DatabaseWordNetResult[] corpus = new DatabaseWordNetResult[]{mockResult, mockResult2};
        String question = "Did Daniel Craig win an oscar?";

        QuestionAnswererResult result = new QuestionAnswerer.QuestionAnswererBuilder()
                .setCorpus(corpus)
                .setStrategy(new MLEStrategy())
                .build()
                .predict(question);

        assert( result.getCategory().equals(Categories.MOVIES));
    }

    @Test
    public void testMultiCategory_GeographyQuestionShouldGuessGeographyCategory() {
        when(mockResult.getCategory()).thenReturn(Categories.MOVIES);
        when(mockResult.getRelatedStrings()).thenReturn(new String[]{"screen", "oscar", "Daniel Craig"});
        when(mockResult2.getCategory()).thenReturn(Categories.GEOGRAPHY);
        when(mockResult2.getRelatedStrings()).thenReturn(new String[]{"mountain", "lake", "world"});
        DatabaseWordNetResult[] corpus = new DatabaseWordNetResult[]{mockResult, mockResult2};
        String question = "Is the mountain next to the lake?";

        QuestionAnswererResult result = new QuestionAnswerer.QuestionAnswererBuilder()
                .setCorpus(corpus)
                .setStrategy(new MLEStrategy())
                .build()
                .predict(question);

        assert( result.getCategory().equals(Categories.GEOGRAPHY));
    }

    @Test
    public void testWordOverlap_MovieQuestionShouldGuessMovieCategory() {
        when(mockResult.getCategory()).thenReturn(Categories.MOVIES);
        when(mockResult.getRelatedStrings()).thenReturn(new String[]{"person", "oscar", "Daniel Craig"});
        when(mockResult2.getCategory()).thenReturn(Categories.GEOGRAPHY);
        when(mockResult2.getRelatedStrings()).thenReturn(new String[]{"person", "lake", "world"});
        DatabaseWordNetResult[] corpus = new DatabaseWordNetResult[]{mockResult, mockResult2};
        String question = "Which person won the oscar?";

        QuestionAnswererResult result = new QuestionAnswerer.QuestionAnswererBuilder()
                .setCorpus(corpus)
                .setStrategy(new MLEStrategy())
                .build()
                .predict(question);

        assert( result.getCategory().equals(Categories.MOVIES));
    }

    @Test
    public void testWordOverlap_GeographyQuestionShouldGuessGeographyCategory() {
        when(mockResult.getCategory()).thenReturn(Categories.MOVIES);
        when(mockResult.getRelatedStrings()).thenReturn(new String[]{"person", "oscar", "Daniel Craig"});
        when(mockResult2.getCategory()).thenReturn(Categories.GEOGRAPHY);
        when(mockResult2.getRelatedStrings()).thenReturn(new String[]{"person", "lake", "world"});
        DatabaseWordNetResult[] corpus = new DatabaseWordNetResult[]{mockResult, mockResult2};
        String question = "Which person created the world or lake?";

        QuestionAnswererResult result = new QuestionAnswerer.QuestionAnswererBuilder()
                .setCorpus(corpus)
                .setStrategy(new MLEStrategy())
                .build()
                .predict(question);

        assert( result.getCategory().equals(Categories.GEOGRAPHY));
    }

    @Test
    public void testStanfordSplitting() {
        MLEStrategy s = new MLEStrategy();

        String[] result = s.stanfordSplit("Which person created the world or lake?");

        assert(Arrays.asList(result).contains("lake"));
    }
}