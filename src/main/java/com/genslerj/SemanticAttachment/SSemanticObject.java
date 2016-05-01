package com.genslerj.SemanticAttachment;

import com.healthmarketscience.sqlbuilder.SelectQuery;
import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/28/16.
 */
public class SSemanticObject extends SemanticObject {
    public final static String treebankTag = "S";

    public SSemanticObject(String semanticText){ super(semanticText); }
    public SSemanticObject(Function semanticFunction){ super(semanticFunction); }
    public SSemanticObject(SelectQuery semanticQuery){ super(semanticQuery); }

    @Override
    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        if(children_semantic_objects.get(0).getClass().equals(NPSemanticObject.class))
            return sSemanticFunction1;
        else if (children_semantic_objects.get(0).getClass().equals(VPSemanticObject.class))
            return sSemanticFunction2;
        else
            return sSemanticFunction3;
    }

    public static Function<NPSemanticObject, Function<VPSemanticObject, SSemanticObject>> sSemanticFunction1 =
            (NPSemanticObject npSemanticObject) ->
                    (VPSemanticObject vpSemanticObject) -> {
                        Object intermediateObject = vpSemanticObject.semanticFunction.apply(npSemanticObject);
                        if(intermediateObject.getClass().getSuperclass().equals(SemanticObject.class))
                            return new SSemanticObject(((SemanticObject)intermediateObject).semanticQuery);
                        else
                            return new SSemanticObject((Function) intermediateObject);
                    };

    public static Function<VPSemanticObject, SSemanticObject> sSemanticFunction2 =
            (VPSemanticObject vpSemanticObject) ->
                    new SSemanticObject(vpSemanticObject.semanticQuery);


    public static Function<PPSemanticObject, Function<NPSemanticObject, Function<VPSemanticObject, SSemanticObject>>> sSemanticFunction3 =
            (PPSemanticObject ppSemanticObject) ->
                    (NPSemanticObject npSemanticObject) ->
                            (VPSemanticObject vpSemanticObject) -> {
                                SemanticObject semanticObject = (SemanticObject) vpSemanticObject.semanticFunction.apply(npSemanticObject);
                                return new SSemanticObject(semanticObject.semanticQuery);
                            };
}
