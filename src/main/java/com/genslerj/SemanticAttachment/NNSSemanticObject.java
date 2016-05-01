package com.genslerj.SemanticAttachment;

import com.healthmarketscience.sqlbuilder.SelectQuery;
import edu.stanford.nlp.tagger.maxent.ASBCunkDict;
import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 5/1/16.
 */
public class NNSSemanticObject extends SemanticObject {
    public final static String treebankTag = "NNS";

    public NNSSemanticObject(String semanticText){ super(semanticText); }
    public NNSSemanticObject(Function semanticFunction){ super(semanticFunction); }
    public NNSSemanticObject(SelectQuery semanticQuery){ super(semanticQuery); }

    @Override
    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        return nnsSemanticObjectSemanticFunction1;
    }

    public static Function<ActualizedSemanticObject, NNSSemanticObject> nnsSemanticObjectSemanticFunction1 =
            (ActualizedSemanticObject actualizedSemanticObject) ->
                    new NNSSemanticObject(actualizedSemanticObject.semanticText);
}
