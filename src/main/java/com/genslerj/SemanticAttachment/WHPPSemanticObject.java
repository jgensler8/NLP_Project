package com.genslerj.SemanticAttachment;

import com.healthmarketscience.sqlbuilder.Query;
import com.healthmarketscience.sqlbuilder.SelectQuery;
import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 5/1/16.
 */
public class WHPPSemanticObject extends SemanticObject {
    public final static String treebankTag = "WHPP";

    public WHPPSemanticObject(String semanticText){ super(semanticText); }
    public WHPPSemanticObject(Function semanticFunction){ super(semanticFunction); }
    public WHPPSemanticObject(Query semanticQuery){ super(semanticQuery); }

    @Override
    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        return whppSemanticObjectSemanticFunction1;
    }

    public static Function<INSemanticObject, Function<WHNPSemanticObject, WHPPSemanticObject>> whppSemanticObjectSemanticFunction1 =
            (INSemanticObject inSemanticObject) ->
                    (WHNPSemanticObject whnpSemanticObject) ->
                            new WHPPSemanticObject(whnpSemanticObject.semanticText);
}
