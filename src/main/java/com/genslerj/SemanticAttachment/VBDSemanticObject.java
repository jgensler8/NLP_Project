package com.genslerj.SemanticAttachment;

import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/28/16.
 */
public class VBDSemanticObject extends SemanticObject {
    public final static String treebankTag = "VBD";

    public VBDSemanticObject(String semanticText){ super(semanticText); }
    public VBDSemanticObject(Function semanticFunction){ super(semanticFunction); }

    @Override
    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        return vbdSemanticObjectSemanticFunction1;
    }

    public static Function<ActualizedSemanticObject, VBDSemanticObject> vbdSemanticObjectSemanticFunction1 = (ActualizedSemanticObject actualizedSemanticObject) -> new VBDSemanticObject(actualizedSemanticObject.semanticFunction);
}
