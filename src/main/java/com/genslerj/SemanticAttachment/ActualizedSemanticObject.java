package com.genslerj.SemanticAttachment;

import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/28/16.
 */
public class ActualizedSemanticObject extends SemanticObject {
    public ActualizedSemanticObject(String semanticText){ super(semanticText); }
    public ActualizedSemanticObject(Function semanticFunction){ super(semanticFunction); }

    @Override
    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        return actualizedSemanticObjectSemanticFunction1;
    }

    public static Function<String, ActualizedSemanticObject> actualizedSemanticObjectSemanticFunction1 = (String actualizedWord) -> new ActualizedSemanticObject(actualizedWord);
}
