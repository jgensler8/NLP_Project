package com.genslerj.SemanticAttachment;

import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/28/16.
 */
public class NNPSemanticObject extends SemanticObject {
    public final static String treebankTag = "NNP";

    public NNPSemanticObject(String semanticText){ super(semanticText); }
    public NNPSemanticObject(Function semanticFunction){ super(semanticFunction); }

    @Override
    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        return nnpSemanticObjectSemanticFunction1;
    }

    public static Function<ActualizedSemanticObject, NNPSemanticObject> nnpSemanticObjectSemanticFunction1 = (ActualizedSemanticObject actualizedSemanticObject) -> new NNPSemanticObject(actualizedSemanticObject.semanticText);
}
