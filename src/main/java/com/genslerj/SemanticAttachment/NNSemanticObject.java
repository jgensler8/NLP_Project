package com.genslerj.SemanticAttachment;

import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/29/16.
 */
public class NNSemanticObject extends SemanticObject {
    public final static String treebankTag = "NN";

    public NNSemanticObject(String semanticText){ super(semanticText); }
    public NNSemanticObject(Function semanticFunction){ super(semanticFunction); }

    @Override
    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        return nnSemanticObjectSemanticObjectFunction1;
    }

    static Function<ActualizedSemanticObject, NNSemanticObject> nnSemanticObjectSemanticObjectFunction1 = (ActualizedSemanticObject actualizeSemanticObject) -> new NNSemanticObject(actualizeSemanticObject.semanticText);
}
