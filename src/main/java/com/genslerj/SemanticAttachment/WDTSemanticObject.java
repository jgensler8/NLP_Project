package com.genslerj.SemanticAttachment;

import com.healthmarketscience.sqlbuilder.Query;
import com.healthmarketscience.sqlbuilder.SelectQuery;
import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 5/1/16.
 */
public class WDTSemanticObject extends SemanticObject {
    public final static String treebankTag = "WDT";

    public WDTSemanticObject(String semanticText){ super(semanticText); }
    public WDTSemanticObject(Function semanticFunction){ super(semanticFunction); }
    public WDTSemanticObject(Query semanticQuery){ super(semanticQuery); }

    @Override
    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        return wdtSemanticObjectSemanticFunction1;
    }

    public static Function<ActualizedSemanticObject, WDTSemanticObject> wdtSemanticObjectSemanticFunction1 =
            (ActualizedSemanticObject actualizedSemanticObject) ->
                    new WDTSemanticObject(actualizedSemanticObject.semanticText);
}
