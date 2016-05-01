package com.genslerj.SemanticAttachment;

import com.healthmarketscience.sqlbuilder.SelectQuery;
import edu.stanford.nlp.trees.Tree;
import org.joda.time.ReadWritableDateTime;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 5/1/16.
 */
public class WHNPSemanticObject extends SemanticObject {
    public final static String treebankTag = "WHNP";

    public WHNPSemanticObject(String semanticText){ super(semanticText); }
    public WHNPSemanticObject(Function semanticFunction){ super(semanticFunction); }
    public WHNPSemanticObject(SelectQuery semanticQuery){ super(semanticQuery); }

    @Override
    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        if(children_semantic_objects.get(0).getClass().equals(WPSemanticObject.class))
            return whnpSemanticObjectSemanticFunction1;
        else if (children_semantic_objects.get(0).getClass().equals(WDTSemanticObject.class))
            return whnpSemanticObjectSemanticFunction2;
        else
            return whnpSemanticObjectSemanticFunction3;
    }

    public static Function<WPSemanticObject, WHNPSemanticObject> whnpSemanticObjectSemanticFunction1 =
            (WPSemanticObject wpSemanticObject) ->
                    new WHNPSemanticObject(wpSemanticObject.semanticText);

    public static Function<WDTSemanticObject, WHNPSemanticObject> whnpSemanticObjectSemanticFunction2 =
            (WDTSemanticObject wpSemanticObject) ->
                    new WHNPSemanticObject(wpSemanticObject.semanticText);

    public static Function<WHNPSemanticObject, Function<PPSemanticObject, WHNPSemanticObject>> whnpSemanticObjectSemanticFunction3 =
            (WHNPSemanticObject wpSemanticObject) ->
                    (PPSemanticObject ppSemanticObject) ->
                        new WHNPSemanticObject(wpSemanticObject.semanticText);
}
