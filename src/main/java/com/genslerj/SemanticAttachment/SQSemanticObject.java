package com.genslerj.SemanticAttachment;

import com.healthmarketscience.sqlbuilder.SelectQuery;
import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/29/16.
 */
public class SQSemanticObject extends SemanticObject {
    public final static String treebankTag = "SQ";

    public SQSemanticObject(String semanticText){ super(semanticText); }
    public SQSemanticObject(Function semanticFunction){ super(semanticFunction); }
    public SQSemanticObject(SelectQuery semanticQuery){ super(semanticQuery); }

    @Override
    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        if(children_semantic_objects.get(0).getClass().equals(VBZSemanticObject.class)) {
            if(children_semantic_objects.size() == 2)
                return sqSemanticObjectSemanticFunction5;
            else if(children_semantic_objects.get(2).getClass().equals(NPSemanticObject.class))
                return sqSemanticObjectSemanticFunction1;
            else
                return sqSemanticObjectSemanticFunction2;
        }
        else if (children_semantic_objects.get(0).getClass().equals(VBDSemanticObject.class))
            return sqSemanticObjectSemanticFunction3;
        else
            return sqSemanticObjectSemanticFunction4;
    }

    public static Function<VBZSemanticObject, Function<NPSemanticObject, Function<NPSemanticObject, SQSemanticObject>>> sqSemanticObjectSemanticFunction1 =
            (VBZSemanticObject vbzSemanticObject) ->
                    (NPSemanticObject npSemanticObject1) ->
                            (NPSemanticObject npSemanticObject2) -> {
                                Function intermediateSemanticFunction = (Function) vbzSemanticObject.semanticFunction.apply(npSemanticObject1);
                                SemanticObject intermediateSemanticObject = (SemanticObject) intermediateSemanticFunction.apply(npSemanticObject2);
                              return new SQSemanticObject(intermediateSemanticObject.semanticQuery);
                            };

    public static Function<VBZSemanticObject, Function<NPSemanticObject, Function<ADVPSemanticObject, Function<NPSemanticObject, SQSemanticObject>>>> sqSemanticObjectSemanticFunction2 =
            (VBZSemanticObject vbzSemanticObject) ->
                    (NPSemanticObject npSemanticObject1) ->
                            (ADVPSemanticObject advpSemanticObject) ->
                                (NPSemanticObject npSemanticObject2) -> {
                                    Function intermediateSemanticFunction = (Function) vbzSemanticObject.semanticFunction.apply(npSemanticObject1);
                                    SemanticObject intermediateSemanticObject = (SemanticObject) intermediateSemanticFunction.apply(npSemanticObject2);
                                    return new SQSemanticObject(intermediateSemanticObject.semanticQuery);
                                };

    public static Function<VBDSemanticObject, Function<NPSemanticObject, Function<VPSemanticObject, SQSemanticObject>>> sqSemanticObjectSemanticFunction3 =
            (VBDSemanticObject vbdSemanticObject) ->
                    (NPSemanticObject npSemanticObject) ->
                            (VPSemanticObject vpSemanticObject) ->{
                                        Function intermediateSemanticFunction = (Function) vbdSemanticObject.semanticFunction.apply(npSemanticObject);
                                        SemanticObject intermediateSemanticObject = (SemanticObject) intermediateSemanticFunction.apply(vpSemanticObject);
                                        return new SQSemanticObject(intermediateSemanticObject.semanticQuery);
                                    };

    public static Function<VPSemanticObject, SQSemanticObject> sqSemanticObjectSemanticFunction4 =
            (VPSemanticObject vbdSemanticObject) ->
                new SQSemanticObject(vbdSemanticObject.semanticFunction);

    public static Function<VBZSemanticObject, Function<NPSemanticObject, SQSemanticObject>> sqSemanticObjectSemanticFunction5 =
            (VBZSemanticObject vbdSemanticObject) ->
                    (NPSemanticObject npSemanticObject) ->
                            new SQSemanticObject((Function) vbdSemanticObject.semanticFunction.apply(npSemanticObject));
}
