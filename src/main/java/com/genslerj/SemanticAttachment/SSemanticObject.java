package com.genslerj.SemanticAttachment;

import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/28/16.
 */
public class SSemanticObject extends SemanticObject {
    public final static String treebankTag = "S";

    public SSemanticObject(String semanticText){ super(semanticText); }
    public SSemanticObject(Function semanticFunction){ super(semanticFunction); }

    @Override
    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        return sSemanticFunction1;
    }

    public static Function<NPSemanticObject, Function<VPSemanticObject, SSemanticObject>> sSemanticFunction1 = (NPSemanticObject npSemanticObject) -> (VPSemanticObject vpSemanticObject) -> {
        SemanticObject intermediateSemanticObject = (SemanticObject) vpSemanticObject.semanticFunction.apply(npSemanticObject);
        return new SSemanticObject(intermediateSemanticObject.semanticText);
    };

}
