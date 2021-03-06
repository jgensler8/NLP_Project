package com.genslerj.SemanticAttachment;

import com.healthmarketscience.sqlbuilder.Query;
import com.healthmarketscience.sqlbuilder.SelectQuery;
import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/28/16.
 */
public class VBDSemanticObject extends SemanticObject {
    public final static String treebankTag = "VBD";

    public static SemanticLibrary semanticLibrary = new SemanticLibrary();

    public VBDSemanticObject(String semanticText){ super(semanticText); }
    public VBDSemanticObject(Function semanticFunction){ super(semanticFunction); }
    public VBDSemanticObject(Query semanticQuery){ super(semanticQuery); }

    @Override
    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        return vbdSemanticObjectSemanticFunction1;
    }

    public static Function<ActualizedSemanticObject, VBDSemanticObject> vbdSemanticObjectSemanticFunction1 =
            (ActualizedSemanticObject actualizedSemanticObject) ->
                    new VBDSemanticObject(semanticLibrary.VBDtoSemanticFunction.get(actualizedSemanticObject.semanticText.toLowerCase()));
}
