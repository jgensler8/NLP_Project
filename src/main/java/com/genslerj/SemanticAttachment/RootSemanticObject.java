package com.genslerj.SemanticAttachment;

import com.healthmarketscience.sqlbuilder.Query;
import com.healthmarketscience.sqlbuilder.SelectQuery;
import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/28/16.
 */
public class ROOTSemanticObject extends SemanticObject {
    public final static String treebankTag = "ROOT";

    public ROOTSemanticObject(String semanticText){ super(semanticText); }
    public ROOTSemanticObject(Function semanticFunction){ super(semanticFunction); }
    public ROOTSemanticObject(Query semanticQuery){ super(semanticQuery); }

    @Override
    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        if(children_semantic_objects.get(0).getClass().equals(SSemanticObject.class))
            return rootSemanticFunction1;
        else if(children_semantic_objects.get(0).getClass().equals(SQSemanticObject.class))
            return rootSemanticFunction2;
        else
            return rootSemanticFunction3;
    }

    public static Function<SSemanticObject, ROOTSemanticObject> rootSemanticFunction1 =
            (SSemanticObject sSemanticObject) ->
                    new ROOTSemanticObject(sSemanticObject.semanticQuery);
    public static Function<SQSemanticObject, ROOTSemanticObject> rootSemanticFunction2 =
            (SQSemanticObject sqSemanticObject) ->
                    new ROOTSemanticObject(sqSemanticObject.semanticQuery);
    public static Function<SBARQSemanticObject, ROOTSemanticObject> rootSemanticFunction3 =
            (SBARQSemanticObject sqSemanticObject) ->
                    new ROOTSemanticObject(sqSemanticObject.semanticQuery);

}
