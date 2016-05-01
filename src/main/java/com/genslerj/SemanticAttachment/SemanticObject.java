package com.genslerj.SemanticAttachment;

import com.healthmarketscience.sqlbuilder.SelectQuery;
import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/28/16.
 */
abstract public class SemanticObject {
    String semanticText = "";
    Function semanticFunction = null;
    SelectQuery semanticQuery = new SelectQuery();
    // Query semanticQuery = null;

    public SemanticObject(String semanticText) { this.semanticText = semanticText; }
    public SemanticObject(Function semanticFunction) { this.semanticFunction = semanticFunction; }
    public SemanticObject(SelectQuery semanticQuery) { this.semanticQuery = semanticQuery; }

    public String getSemanticText() { return this.semanticText; }
    public String getSemanticTextAsLikeClause() { return String.format("%%%s%%", this.semanticText); }
    public SelectQuery getSemanticQuery() { return this.semanticQuery; }
    abstract public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects);
}
