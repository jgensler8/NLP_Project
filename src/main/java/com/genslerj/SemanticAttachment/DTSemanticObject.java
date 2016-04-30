package com.genslerj.SemanticAttachment;

import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/29/16.
 */
public class DTSemanticObject extends SemanticObject {
    public final static String treebankTag = "DT";

    public DTSemanticObject(String semanticText){ super(semanticText); }
    public DTSemanticObject(Function semanticFunction){ super(semanticFunction); }

    @Override
    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        return dtSemanticObjectSemanticFunction1;
    }

    static Function<ActualizedSemanticObject, DTSemanticObject> dtSemanticObjectSemanticFunction1 = (ActualizedSemanticObject actualizeSemanticObject) -> new DTSemanticObject(actualizeSemanticObject.semanticText);
}
