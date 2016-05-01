package com.genslerj.SemanticAttachment;

import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/30/16.
 */
public class CDSemanticObject extends SemanticObject {
    public final static String treebankTag = "CD";

    public CDSemanticObject(String semanticText){ super(semanticText); }
    public CDSemanticObject(Function semanticFunction){ super(semanticFunction); }

    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        return cdSemanticObjectSemanticFunction1;
    }

    Function<ActualizedSemanticObject, CDSemanticObject> cdSemanticObjectSemanticFunction1 =
            (ActualizedSemanticObject actualizedSemanticObject) ->
                    new CDSemanticObject(actualizedSemanticObject.semanticText);
}
