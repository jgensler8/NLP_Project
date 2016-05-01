package com.genslerj.SemanticAttachment;

import com.healthmarketscience.sqlbuilder.SelectQuery;
import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/30/16.
 */
public class VBSemanticObject extends SemanticObject {
    public final static String treebankTag = "VB";

    public VBSemanticObject(String semanticText){ super(semanticText); }
    public VBSemanticObject(Function semanticFunction){ super(semanticFunction); }
    public VBSemanticObject(SelectQuery semanticQuery){ super(semanticQuery); }

    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        return vbSemanticObjectSemanticFunction1;
    }

    Function<ActualizedSemanticObject, VBSemanticObject> vbSemanticObjectSemanticFunction1 =
            (ActualizedSemanticObject actualizedSemanticObject) ->
                    new VBSemanticObject(actualizedSemanticObject.semanticFunction);
}
