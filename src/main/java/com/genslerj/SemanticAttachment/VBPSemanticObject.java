package com.genslerj.SemanticAttachment;

import com.healthmarketscience.sqlbuilder.SelectQuery;
import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/30/16.
 */
public class VBPSemanticObject extends SemanticObject {
    public final static String treebankTag = "VBP";

    public static SemanticLibrary semanticLibrary = new SemanticLibrary();

    public VBPSemanticObject(String semanticText){ super(semanticText); }
    public VBPSemanticObject(Function semanticFunction){ super(semanticFunction); }
    public VBPSemanticObject(SelectQuery semanticQuery){ super(semanticQuery); }

    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        return vbpSemanticObjectSemanticFunction1;
    }

    Function<ActualizedSemanticObject, VBPSemanticObject> vbpSemanticObjectSemanticFunction1 =
            (ActualizedSemanticObject actualizedSemanticObject) ->
                    new VBPSemanticObject(semanticLibrary.VBPtoSemanticFunction.get(actualizedSemanticObject.semanticText.toLowerCase()));
}
