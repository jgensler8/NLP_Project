package com.genslerj.SemanticAttachment;

import com.healthmarketscience.sqlbuilder.Query;
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
    Query semanticQuery = new SelectQuery();
    // Query semanticQuery = null;

    public SemanticObject(String semanticText) { this.semanticText = semanticText; }
    public SemanticObject(Function semanticFunction) { this.semanticFunction = semanticFunction; }
    public SemanticObject(Query semanticQuery) { this.semanticQuery = semanticQuery; }

    public String getSemanticText() { return this.semanticText; }
    public String getSemanticTextAsLikeClause() { return String.format("%%%s%%", this.semanticText); }
    public Query getSemanticQuery() { return this.semanticQuery; }
    abstract public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects);

    public static final int defaultInitializedQueryLength = new SelectQuery().toString().length();
    public boolean isSemanticQueryModified() {
        return this.semanticQuery.toString().length() > defaultInitializedQueryLength;
    }
}
