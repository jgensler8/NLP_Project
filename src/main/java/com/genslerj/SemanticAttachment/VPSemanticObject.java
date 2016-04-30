package com.genslerj.SemanticAttachment;

import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/21/16.
 */
public class VPSemanticObject extends SemanticObject {
    public final static String treebankTag = "VP";

    public VPSemanticObject(String semanticText){ super(semanticText); }
    public VPSemanticObject(Function semanticFunction){ super(semanticFunction); }

    @Override
    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        return vpSemanticObjectSemanticFunction1;
    }

    public static Function<VBDSemanticObject, Function<NPSemanticObject, VPSemanticObject>> vpSemanticObjectSemanticFunction1 = (VBDSemanticObject vbdSemanticObject) -> (NPSemanticObject npSemanticObject) -> {
        VPSemanticObject vp = new VPSemanticObject(vbdSemanticObject.semanticFunction);
        vp.semanticFunction = (Function) vp.semanticFunction.apply(npSemanticObject);
        return vp;
    };
}
