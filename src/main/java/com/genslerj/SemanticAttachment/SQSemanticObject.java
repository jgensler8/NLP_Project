package com.genslerj.SemanticAttachment;

import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/29/16.
 */
public class SQSemanticObject extends SemanticObject {
    public final static String treebankTag = "SQ";

    public SQSemanticObject(String semanticText){ super(semanticText); }
    public SQSemanticObject(Function semanticFunction){ super(semanticFunction); }

    @Override
    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        return sqSemanticObjectSemanticFunction1;
    }

    public static Function<VBZSemanticObject, Function<NPSemanticObject, Function<NPSemanticObject, SQSemanticObject>>> sqSemanticObjectSemanticFunction1 =
            (VBZSemanticObject vbzSemanticObject) ->
                    (NPSemanticObject npSemanticObject1) ->
                            (NPSemanticObject npSemanticObject2) -> {
                                Function intermediateSemanticFunction = (Function) vbzSemanticObject.semanticFunction.apply(npSemanticObject1);
                                SemanticObject intermediateSemanticObject = (SemanticObject) intermediateSemanticFunction.apply(npSemanticObject2);
                              return new SQSemanticObject(intermediateSemanticObject.semanticText);
                            };
}
