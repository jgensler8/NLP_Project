package com.genslerj.SemanticAttachment;

import com.genslerj.DatabaseTermExtractor.DatabaseQueryAnswerer;
import com.genslerj.DatabaseTermExtractor.DatabaseResources;
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
    public void testKubrikDirectingSpartacusShouldBeTrue() throws SQLException, ClassNotFoundException {
        Tree kubrikTree = StanfordNLPExample.parse("Kubrick directed Spartacus?").get(0);
        SemanticObject kubrick_spartacus = ParseTreeToSemanticObject.parse(kubrikTree);
        boolean result = moviesDatabaseQueryAnswerer.runExistsQuery(kubrick_spartacus.getSemanticText());
        assert(result == true);
    }

    @Test
    public void testKubrickDirectingNemoShouldBeFalse() throws SQLException, ClassNotFoundException {
        Tree kubrikTree = StanfordNLPExample.parse("Kubrick directed Nemo?").get(0);
        SemanticObject kubrik_nemo = ParseTreeToSemanticObject.parse(kubrikTree);
        boolean result = moviesDatabaseQueryAnswerer.runExistsQuery(kubrik_nemo.getSemanticText());
        assert(result == false);
    }

    @Test
    public void testStantonDirectingNemoShouldBeTrue() throws SQLException, ClassNotFoundException {
        Tree kubrikTree = StanfordNLPExample.parse("Stanton directed Nemo?").get(0);
        SemanticObject stanton_nemo = ParseTreeToSemanticObject.parse(kubrikTree);
        boolean result = moviesDatabaseQueryAnswerer.runExistsQuery(stanton_nemo.getSemanticText());
        assert(result == true);
    }
}