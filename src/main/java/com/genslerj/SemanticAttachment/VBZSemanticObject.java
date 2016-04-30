package com.genslerj.SemanticAttachment;

import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/29/16.
 */
public class VBZSemanticObject extends SemanticObject {
    public final static String treebankTag = "VBZ";

    public VBZSemanticObject(String semanticText){ super(semanticText); }
    public VBZSemanticObject(Function semanticFunction){ super(semanticFunction); }

    @Override
    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        return vbzSemanticObjectSemanticFunction1;
    }

    public static Function<ActualizedSemanticObject, VBZSemanticObject> vbzSemanticObjectSemanticFunction1 = (ActualizedSemanticObject actualizedSemanticObject) -> {
        return new VBZSemanticObject(actualizedSemanticObject.semanticFunction);
    };
}
