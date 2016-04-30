package com.genslerj.SemanticAttachment;

import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/28/16.
 */
public class RootSemanticObject extends SemanticObject {
    public final static String treebankTag = "ROOT";

    public RootSemanticObject(String semanticText){ super(semanticText); }
    public RootSemanticObject(Function semanticFunction){ super(semanticFunction); }

    @Override
    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        if(children_semantic_objects.get(0).getClass().equals(SSemanticObject.class))
            return rootSemanticFunction1;
        else
            return rootSemanticFunction2;
    }

    public static Function<SSemanticObject, RootSemanticObject> rootSemanticFunction1 = (SSemanticObject sSemanticObject) -> new RootSemanticObject(sSemanticObject.semanticText);
    public static Function<SQSemanticObject, RootSemanticObject> rootSemanticFunction2 = (SQSemanticObject sqSemanticObject) -> new RootSemanticObject(sqSemanticObject.semanticText);

}
