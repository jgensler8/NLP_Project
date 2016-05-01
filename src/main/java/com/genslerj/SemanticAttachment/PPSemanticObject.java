package com.genslerj.SemanticAttachment;

import com.sun.tools.javac.util.Pair;
import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/30/16.
 */
public class PPSemanticObject extends SemanticObject {
    public final static String treebankTag = "PP";

    public PPSemanticObject(String semanticText){ super(semanticText); }
    public PPSemanticObject(Function semanticFunction){ super(semanticFunction); }

    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        if(children_semantic_objects.get(1).getClass().equals(NPSemanticObject.class))
            return ppSemanticObjectSemanticFunction1;
        else
            return ppSemanticObjectSemanticFunction2;
    }

    public static Function<INSemanticObject, Function<NPSemanticObject, PPSemanticObject>> ppSemanticObjectSemanticFunction1 =
            (INSemanticObject inSemanticObject) ->
                    (NPSemanticObject npSemanticObject) ->
                            new PPSemanticObject(npSemanticObject.semanticText);

    public static Function<INSemanticObject, Function<SBARSemanticObject, PPSemanticObject>> ppSemanticObjectSemanticFunction2 =
            (INSemanticObject inSemanticObject) ->
                    (SBARSemanticObject sbarSemanticObject) ->
                            new PPSemanticObject(sbarSemanticObject.semanticFunction);
}
