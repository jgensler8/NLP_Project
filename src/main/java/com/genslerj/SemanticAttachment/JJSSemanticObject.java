package com.genslerj.SemanticAttachment;

import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/30/16.
 */
public class JJSSemanticObject extends SemanticObject {
    public final static String treebankTag = "JJS";

    public JJSSemanticObject(String semanticText){ super(semanticText); }
    public JJSSemanticObject(Function semanticFunction){ super(semanticFunction); }

    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        return jjsSemanticObjectSemanticFunction1;
    }

    Function<ActualizedSemanticObject, JJSSemanticObject> jjsSemanticObjectSemanticFunction1 =
            (ActualizedSemanticObject actualizedSemanticObject) ->
                    new JJSSemanticObject(actualizedSemanticObject.semanticText);
}
