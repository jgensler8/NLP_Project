package com.genslerj.SemanticAttachment;

import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/28/16.
 */
abstract public class SemanticObject {
    String semanticText = "";
    Function semanticFunction = null;
    // Query semanticQuery = null;

    public SemanticObject(String semanticText) { this.semanticText = semanticText; }
    public SemanticObject(Function semanticFunction) { this.semanticFunction = semanticFunction; }

    public String getSemanticText() { return this.semanticText; }
    abstract public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects);
}
