package com.genslerj.SemanticAttachment;

import com.genslerj.DatabaseTermExtractor.DatabaseQueryAnswerer;
import com.genslerj.DatabaseTermExtractor.DatabaseResources;
import com.genslerj.QuestionAnswerLibrary.Library;
import com.genslerj.QuestionAnswerLibrary.QuestionAnswerPair;
import edu.stanford.nlp.trees.Tree;
import main.StanfordNLPExample;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by genslerj on 4/29/16.
 */
public class SemanticLibraryTest {

    DatabaseQueryAnswerer moviesDatabaseQueryAnswerer;

    @Before
    public void before_all() throws SQLException, ClassNotFoundException {
        moviesDatabaseQueryAnswerer = new DatabaseQueryAnswerer(DatabaseResources.MOVIES_CONNECTION_STRING, DatabaseResources.DATABASE_NAME, "movies");
    }

    @Test
    public void testKubrikDirectingSpartacusShouldBeTrue() throws SQLException, ClassNotFoundException, TreebankTagNotSupportedException {
        Tree kubrikTree = StanfordNLPExample.parse("Kubrick directed Spartacus?").get(0);
        SemanticObject kubrick_spartacus = ParseTreeToSemanticObject.parse(kubrikTree);
        boolean result = moviesDatabaseQueryAnswerer.runExistsQuery(kubrick_spartacus.getSemanticText());
        assert(result == true);
    }

    @Test
    public void testKubrickDirectingNemoShouldBeFalse() throws SQLException, ClassNotFoundException, TreebankTagNotSupportedException {
        Tree kubrikTree = StanfordNLPExample.parse("Kubrick directed Nemo?").get(0);
        SemanticObject kubrik_nemo = ParseTreeToSemanticObject.parse(kubrikTree);
        boolean result = moviesDatabaseQueryAnswerer.runExistsQuery(kubrik_nemo.getSemanticText());
        assert(result == false);
    }

    @Test
    public void testStantonDirectingNemoShouldBeTrue() throws SQLException, ClassNotFoundException, TreebankTagNotSupportedException {
        Tree kubrikTree = StanfordNLPExample.parse("Stanton directed Nemo?").get(0);
        SemanticObject stanton_nemo = ParseTreeToSemanticObject.parse(kubrikTree);
        boolean result = moviesDatabaseQueryAnswerer.runExistsQuery(stanton_nemo.getSemanticText());
        assert(result == true);
    }

    @Test
    public void testAllActualLibraryQuestions() throws SQLException, ClassNotFoundException {
        for(QuestionAnswerPair pair : Library.questions) {
            Tree tree = StanfordNLPExample.parse(pair.getQuestion()).get(0);
            SemanticObject object = null;
            System.out.println(String.format("About to parse: %s", pair.getQuestion()));
            try {
                object = ParseTreeToSemanticObject.parse(tree);
            } catch (TreebankTagNotSupportedException c) {
                System.out.println(c.getMessage());
                continue;
            }
            boolean result = moviesDatabaseQueryAnswerer.runExistsQuery(object.getSemanticText());

            if(! String.valueOf(result).equals(pair.getAnswer())) {
                System.out.println(String.format("Answered: %s wrong", pair.getQuestion()));
            }
        }
        assert(false);
    }
}