package com.genslerj.SemanticAttachment;

import com.healthmarketscience.sqlbuilder.Query;
import com.healthmarketscience.sqlbuilder.SelectQuery;
import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/29/16.
 */
public class VBZSemanticObject extends SemanticObject {
    public final static String treebankTag = "VBZ";

    public static SemanticLibrary semanticLibrary = new SemanticLibrary();

    public VBZSemanticObject(String semanticText){ super(semanticText); }
    public VBZSemanticObject(Function semanticFunction){ super(semanticFunction); }
    public VBZSemanticObject(Query semanticQuery){ super(semanticQuery); }

    @Override
    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        return vbzSemanticObjectSemanticFunction1;
    }

    public static Function<ActualizedSemanticObject, VBZSemanticObject> vbzSemanticObjectSemanticFunction1 =
            (ActualizedSemanticObject actualizedSemanticObject) -> new VBZSemanticObject(
                    semanticLibrary.VBZtoSemanticFunction.get(actualizedSemanticObject.semanticText.toLowerCase()));
}
