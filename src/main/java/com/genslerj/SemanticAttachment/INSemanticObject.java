package com.genslerj.SemanticAttachment;

import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/30/16.
 */
public class INSemanticObject extends SemanticObject {

    public final static String treebankTag = "IN";

    public INSemanticObject(String semanticText){ super(semanticText); }
    public INSemanticObject(Function semanticFunction){ super(semanticFunction); }

    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        return inSemanticObjectSemanticFunction1;
    }

    public static Function<ActualizedSemanticObject, INSemanticObject> inSemanticObjectSemanticFunction1 =
            (ActualizedSemanticObject actualizedSemanticObject) -> new INSemanticObject(actualizedSemanticObject.semanticText);
}
