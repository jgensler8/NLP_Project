package com.genslerj.SemanticAttachment;

import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 5/1/16.
 */
public class JJSemanticObject extends SemanticObject {
    public final static String treebankTag = "JJ";

    public JJSemanticObject(String semanticText){ super(semanticText); }
    public JJSemanticObject(Function semanticFunction){ super(semanticFunction); }

    @Override
    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        return jjSemanticObjectSemanticFunction1;
    }

    public static Function<ActualizedSemanticObject, JJSemanticObject> jjSemanticObjectSemanticFunction1 =
            (ActualizedSemanticObject actualizedSemanticObject) ->
                    new JJSemanticObject(actualizedSemanticObject.semanticText);
}
