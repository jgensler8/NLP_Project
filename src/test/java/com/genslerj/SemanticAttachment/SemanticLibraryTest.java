package com.genslerj.SemanticAttachment;

import com.genslerj.DatabaseTermExtractor.DatabaseQueryAnswerer;
import com.genslerj.DatabaseTermExtractor.DatabaseResources;
import edu.stanford.nlp.trees.Tree;
import main.StanfordNLPExample;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by genslerj on 4/29/16.
 */
public class SemanticLibraryTest {

    @Test
    public void testDirectedQuery() throws SQLException, ClassNotFoundException {
        Tree kubrikTree = StanfordNLPExample.parse("Kubrik directed Hugo?").get(0);
        SemanticObject kubrik_semantic_object = ParseTreeToSemanticObject.parse(kubrikTree);
        DatabaseQueryAnswerer l = new DatabaseQueryAnswerer(DatabaseResources.MOVIES_CONNECTION_STRING, DatabaseResources.DATABASE_NAME, "movies");
        boolean result = l.runExistsQuery(kubrik_semantic_object.getSemanticText());
        System.out.println(result);
        assert(false);
    }
}