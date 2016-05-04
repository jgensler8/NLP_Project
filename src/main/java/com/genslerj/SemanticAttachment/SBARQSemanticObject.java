package com.genslerj.SemanticAttachment;

import com.healthmarketscience.sqlbuilder.Query;
import com.healthmarketscience.sqlbuilder.SelectQuery;
import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 5/1/16.
 */
public class SBARQSemanticObject extends SemanticObject {
    public final static String treebankTag = "SBARQ";

    public SBARQSemanticObject(String semanticText){ super(semanticText); }
    public SBARQSemanticObject(Function semanticFunction){ super(semanticFunction); }
    public SBARQSemanticObject(Query semanticQuery){ super(semanticQuery); }

    @Override
    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        if(children_semantic_objects.get(0).getClass().equals(WHNPSemanticObject.class))
            return sbarqSemanticObjectSemanticFunction1;
        else
            return sbarqSemanticObjectSemanticFunction2;
    }

    public static Function<WHNPSemanticObject, Function<SQSemanticObject, SBARQSemanticObject>> sbarqSemanticObjectSemanticFunction1 =
            (WHNPSemanticObject wpSemanticObject) ->
                    (SQSemanticObject sqSemanticObject) -> {
                        NPSemanticObject npSemanticObject = new NPSemanticObject("");
                        SemanticObject semanticObject = (SemanticObject) sqSemanticObject.semanticFunction.apply(npSemanticObject);
                        return new SBARQSemanticObject(semanticObject.semanticQuery);
                    };

    public static Function<WHPPSemanticObject, Function<SQSemanticObject, SBARQSemanticObject>> sbarqSemanticObjectSemanticFunction2 =
            (WHPPSemanticObject wpSemanticObject) ->
                    (SQSemanticObject sqSemanticObject) -> {
                        NPSemanticObject npSemanticObject = new NPSemanticObject("");
                        SemanticObject semanticObject = (SemanticObject) sqSemanticObject.semanticFunction.apply(npSemanticObject);
                        return new SBARQSemanticObject(semanticObject.semanticQuery);
                    };
}
