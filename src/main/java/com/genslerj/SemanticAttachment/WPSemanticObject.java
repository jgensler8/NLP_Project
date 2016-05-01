package com.genslerj.SemanticAttachment;

import com.healthmarketscience.sqlbuilder.SelectQuery;
import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 5/1/16.
 */
public class WPSemanticObject extends SemanticObject {
    public final static String treebankTag = "WP";

    public WPSemanticObject(String semanticText){ super(semanticText); }
    public WPSemanticObject(Function semanticFunction){ super(semanticFunction); }
    public WPSemanticObject(SelectQuery semanticQuery){ super(semanticQuery); }

    @Override
    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        return wpSemanticObjectSemanticFunction1;
    }

    public static Function<ActualizedSemanticObject, WPSemanticObject> wpSemanticObjectSemanticFunction1 =
            (ActualizedSemanticObject actualizedSemanticObject) ->
                    new WPSemanticObject(actualizedSemanticObject.semanticText);
}
