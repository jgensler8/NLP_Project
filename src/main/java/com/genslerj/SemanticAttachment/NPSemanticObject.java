package com.genslerj.SemanticAttachment;

import com.genslerj.DatabaseTermExtractor.DatabaseResources;
import com.healthmarketscience.sqlbuilder.SelectQuery;
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
    public NPSemanticObject(SelectQuery semanticQuery){ super(semanticQuery); }

    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        if(children_semantic_objects.get(0).getClass().equals(DTSemanticObject.class)) {
            if(children_semantic_objects.size() == 2) {
                if(children_semantic_objects.get(1).getClass().equals(NNSemanticObject.class))
                    return NPSemanticObject.npSemanticObjectSemanticFunction2;
                else
                    return NPSemanticObject.npSemanticObjectSemanticFunction3;
            }
            else if (children_semantic_objects.get(1).getClass().equals(NNSemanticObject.class))
                return NPSemanticObject.npSemanticObjectSemanticFunction4;
            else if (children_semantic_objects.get(1).getClass().equals(JJSemanticObject.class))
                return NPSemanticObject.npSemanticObjectSemanticFunction9;
            else
                return NPSemanticObject.npSemanticObjectSemanticFunction10;
        }
        else if(children_semantic_objects.get(0).getClass().equals(NNPSemanticObject.class)) {
            return NPSemanticObject.npSemanticObjectSemanticFunction1;
        }
        else if(children_semantic_objects.get(0).getClass().equals(NPSemanticObject.class)){
            return NPSemanticObject.npSemanticObjectSemanticFunction5;
        }
        else if(children_semantic_objects.get(0).getClass().equals(JJSSemanticObject.class)){
            return NPSemanticObject.npSemanticObjectSemanticFunction6;
        }
        else if(children_semantic_objects.get(0).getClass().equals(JJRSemanticObject.class)){
            return NPSemanticObject.npSemanticObjectSemanticFunction7;
        }
        else if(children_semantic_objects.get(0).getClass().equals(CDSemanticObject.class)){
            return npSemanticObjectSemanticFunction8;
        }
        else if (children_semantic_objects.get(0).getClass().equals(NNSemanticObject.class))
            return npSemanticObjectSemanticFunction11;
        else
            return npSemanticObjectSemanticFunction12;
    }

    // Proper Noun
    public static Function<NNPSemanticObject, NPSemanticObject> npSemanticObjectSemanticFunction1 =
            (NNPSemanticObject nnpSemanticObject) ->
                    new NPSemanticObject(nnpSemanticObject.semanticText);

    // Determiner + Common Noun
    public static Function<DTSemanticObject, Function<NNSemanticObject, NPSemanticObject>> npSemanticObjectSemanticFunction2 =
            (DTSemanticObject dtSemanticObject) ->
                    (NNSemanticObject nnSemanticObject) -> {
                        // TODO: some sort of domain resolution
                        return new NPSemanticObject(new SelectQuery()
                                .addAllColumns()
                                .addFromTable(DatabaseResources.actorTable));
                    };

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
    // Determiner + Adjective + Common Noun
    public static Function<DTSemanticObject, Function<JJSemanticObject, Function<NNSemanticObject, NPSemanticObject>>> npSemanticObjectSemanticFunction9 =
            (DTSemanticObject dtSemanticObject) ->
                    (JJSemanticObject jjSemanticObject) ->
                            (NNSemanticObject nnSemanticObject2) -> {
                                // TODO: some sort of domain resolution
                                return new NPSemanticObject(new SelectQuery()
                                        .addAllColumns()
                                        .addFromTable(DatabaseResources.actorTable));
                            };
    // Determiner + Adjective (Superlative) + Common Noun
    public static Function<DTSemanticObject, Function<JJSSemanticObject, Function<NNSemanticObject, NPSemanticObject>>> npSemanticObjectSemanticFunction10 =
            (DTSemanticObject dtSemanticObject) ->
                    (JJSSemanticObject jjSemanticObject) ->
                            (NNSemanticObject nnSemanticObject2) -> {
                                // TODO: some sort of domain resolution
                                return new NPSemanticObject(new SelectQuery()
                                        .addAllColumns()
                                        .addFromTable(DatabaseResources.actorTable));
                            };



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

    // Adjective (Comparative)
    public static Function<JJRSemanticObject, NPSemanticObject> npSemanticObjectSemanticFunction7 =
            (JJRSemanticObject jjrSemanticObject) ->
                    new NPSemanticObject(jjrSemanticObject.semanticText);

    // Cardinal Number
    public static Function <CDSemanticObject, NPSemanticObject> npSemanticObjectSemanticFunction8 =
            (CDSemanticObject cdSemanticObject) ->
                    new NPSemanticObject(cdSemanticObject.semanticText);

    // Common Noun
    public static Function <NNSemanticObject, NPSemanticObject> npSemanticObjectSemanticFunction11 =
            (NNSemanticObject cdSemanticObject) ->
                    new NPSemanticObject(cdSemanticObject.semanticText);

    // Plural Noun
    public static Function <NNSSemanticObject, NPSemanticObject> npSemanticObjectSemanticFunction12 =
            (NNSSemanticObject nnsSemanticObject) ->
                    new NPSemanticObject(nnsSemanticObject.semanticText);
}