package com.genslerj.SemanticAttachment;

import com.healthmarketscience.sqlbuilder.SelectQuery;
import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/30/16.
 */
public class JJRSemanticObject extends SemanticObject {
    public final static String treebankTag = "JJR";

    public JJRSemanticObject(String semanticText){ super(semanticText); }
    public JJRSemanticObject(Function semanticFunction){ super(semanticFunction); }
    public JJRSemanticObject(SelectQuery semanticQuery){ super(semanticQuery); }

    @Override
    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        return jjrSemanticObjectSemanticFunction1;
    }

    public static Function<ActualizedSemanticObject, JJRSemanticObject> jjrSemanticObjectSemanticFunction1 =
            (ActualizedSemanticObject actualizedSemanticObject) ->
                    new JJRSemanticObject(actualizedSemanticObject.semanticText);
}
