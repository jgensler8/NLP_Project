package com.genslerj.SemanticAttachment;

import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/29/16.
 */
public class endOfSentanceSemanticObject extends SemanticObject {
    public final static String treebankTag = ".";

    public endOfSentanceSemanticObject(String semanticText){ super(semanticText); }
    public endOfSentanceSemanticObject(Function semanticFunction){ super(semanticFunction); }

    @Override
    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        return null;
    }
}
