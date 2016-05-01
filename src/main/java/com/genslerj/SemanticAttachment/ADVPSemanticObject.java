package com.genslerj.SemanticAttachment;

import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/30/16.
 */
public class ADVPSemanticObject extends SemanticObject {
    public final static String treebankTag = "ADVP";

    public ADVPSemanticObject(String semanticText){ super(semanticText); }
    public ADVPSemanticObject(Function semanticFunction){ super(semanticFunction); }

    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        return advpSemanticObjectSemanticFunction1;
    }

    public static Function<INSemanticObject, ADVPSemanticObject> advpSemanticObjectSemanticFunction1 =
            (INSemanticObject nnSemanticObject) -> new ADVPSemanticObject(nnSemanticObject.semanticText);
}
