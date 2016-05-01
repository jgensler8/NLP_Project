package com.genslerj.SemanticAttachment;

import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/28/16.
 */
public class NPSemanticObject extends SemanticObject {
    public final static String treebankTag = "NP";

    public NPSemanticObject(String semanticText){ super(semanticText); }
    public NPSemanticObject(Function semanticFunction){ super(semanticFunction); }

    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        if(children_semantic_objects.get(0).getClass().equals(DTSemanticObject.class)) {
            if(children_semantic_objects.size() == 2) {
                if(children_semantic_objects.get(1).getClass().equals(NNSemanticObject.class))
                    return NPSemanticObject.npSemanticObjectSemanticFunction2;
                else
                    return NPSemanticObject.npSemanticObjectSemanticFunction3;
            }
            else return NPSemanticObject.npSemanticObjectSemanticFunction4;
        }
        else if(children_semantic_objects.get(0).getClass().equals(NNPSemanticObject.class)) {
            return NPSemanticObject.npSemanticObjectSemanticFunction1;
        }
        else if(children_semantic_objects.get(0).getClass().equals(NPSemanticObject.class)){
            return NPSemanticObject.npSemanticObjectSemanticFunction5;
        }
        else {
            return NPSemanticObject.npSemanticObjectSemanticFunction6;
        }
    }

    // Proper Noun
    public static Function<NNPSemanticObject, NPSemanticObject> npSemanticObjectSemanticFunction1 =
            (NNPSemanticObject nnpSemanticObject) ->
                    new NPSemanticObject(nnpSemanticObject.semanticText);

    // Determiner + Common Noun
    public static Function<DTSemanticObject, Function<NNSemanticObject, NPSemanticObject>> npSemanticObjectSemanticFunction2 =
            (DTSemanticObject dtSemanticObject) ->
                    (NNSemanticObject nnSemanticObject) ->
                            new NPSemanticObject(nnSemanticObject.semanticText);
//                                    new NPSemanticObject(String.format("SELECT q.name FROM Person AS q WHERE q.name LIKE '%%%s%%'", nnSemanticObject.semanticText));
    // Determiner + Proper Noun
    public static Function<DTSemanticObject, Function<NNPSemanticObject, NPSemanticObject>> npSemanticObjectSemanticFunction3 =
            (DTSemanticObject dtSemanticObject) ->
                    (NNPSemanticObject nnpSemanticObject) ->
                            new NPSemanticObject(String.format("%s", nnpSemanticObject.semanticText));
    // Determiner + Common Noun + Common Noun
    public static Function<DTSemanticObject, Function<NNSemanticObject, Function<NNSemanticObject, NPSemanticObject>>> npSemanticObjectSemanticFunction4 =
            (DTSemanticObject dtSemanticObject) ->
                    (NNSemanticObject nnSemanticObject1) ->
                            (NNSemanticObject nnSemanticObject2) ->
                                    new NPSemanticObject(String.format("%s", nnSemanticObject2.semanticText));

    // Noun Phrase + Prepositional Phrase
    // TODO: might have to figure out some sort of SQL
    // TODO: figure out if NounPhrases will always return subqueries or plain text
    public static Function<NPSemanticObject, Function<PPSemanticObject, NPSemanticObject>> npSemanticObjectSemanticFunction5 =
            (NPSemanticObject npSemanticObject) ->
                    (PPSemanticObject ppSemanticObject) ->
                            new NPSemanticObject(String.format("%s", npSemanticObject.semanticText, ppSemanticObject.semanticText));

    // Adjective (Superlative) + Common Noun (ignores adjective)
    public static Function<JJSSemanticObject, Function<NNSemanticObject, NPSemanticObject>> npSemanticObjectSemanticFunction6 =
            (JJSSemanticObject jjsSemanticObject) ->
                    (NNSemanticObject nnSemanticObject) ->
                            new NPSemanticObject(String.format("%s", nnSemanticObject.semanticText));
}