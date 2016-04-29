package com.genslerj.SemanticAttachment;

import com.genslerj.DatabaseTermExtractor.DatabaseQueryAnswerer;
import com.genslerj.DatabaseTermExtractor.DatabaseResources;
import edu.stanford.nlp.trees.Tree;
import main.StanfordNLPExample;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by genslerj on 4/29/16.
 */
public class ParseTreeToSemanticObjectTest {

    Tree kubrikTree = StanfordNLPExample.parse("Kubrik directed Hugo?").get(0);

    @Test
    public void testKubrikTestContainsKubrik() {
        SemanticObject kubrik_semantic_object = ParseTreeToSemanticObject.parse(kubrikTree);
        assert(kubrik_semantic_object.getSemanticText().contains("Kubrik"));
    }

    @Test
    public void testKubrikTestContainsHugo() {
        SemanticObject kubrik_semantic_object = ParseTreeToSemanticObject.parse(kubrikTree);
        assert(kubrik_semantic_object.getSemanticText().contains("Hugo"));
    }

    @Test
    public void testKubrikTestContainsSELECT() {
        SemanticObject kubrik_semantic_object = ParseTreeToSemanticObject.parse(kubrikTree);
        assert(kubrik_semantic_object.getSemanticText().contains("SELECT"));
    }
}