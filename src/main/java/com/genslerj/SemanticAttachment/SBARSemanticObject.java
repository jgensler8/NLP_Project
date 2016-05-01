package com.genslerj.SemanticAttachment;

import com.healthmarketscience.sqlbuilder.SelectQuery;
import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 5/1/16.
 */
public class SBARSemanticObject extends SemanticObject {
    public final static String treebankTag = "SBAR";

    public SBARSemanticObject(String semanticText){ super(semanticText); }
    public SBARSemanticObject(Function semanticFunction){ super(semanticFunction); }
    public SBARSemanticObject(SelectQuery semanticQuery){ super(semanticQuery); }

    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        return sbarSemanticObjectSemanticFunction1;
    }

    public static Function<SSemanticObject, SBARSemanticObject> sbarSemanticObjectSemanticFunction1 =
            (SSemanticObject sSemanticObject) ->
                    new SBARSemanticObject(sSemanticObject.semanticQuery);
}
