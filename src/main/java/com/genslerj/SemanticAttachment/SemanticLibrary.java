package com.genslerj.SemanticAttachment;

import com.genslerj.DatabaseTermExtractor.DatabaseResources;
import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.SelectQuery;

import java.util.function.Function;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by genslerj on 4/28/16.
 */
public class SemanticLibrary {
    // VB
    Function<NPSemanticObject, Function<NPSemanticObject, VBSemanticObject>> win =
            (NPSemanticObject recipient) ->
                    (NPSemanticObject agent) ->
                            new VBSemanticObject(
                                    new SelectQuery()
                                            .addAllColumns()
                                            .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Person_Director_Join)
                                            .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Director_Movie_Join)
                                            .addCondition(BinaryCondition.like(DatabaseResources.person_name, agent.getSemanticTextAsLikeClause() ))
                                            .addCondition(BinaryCondition.like(DatabaseResources.movie_name,  recipient.getSemanticTextAsLikeClause() ))
                            );

    Function<NPSemanticObject, Function<NPSemanticObject, VBSemanticObject>> sing =
            (NPSemanticObject recipient) ->
                    (NPSemanticObject agent) ->
                            new VBSemanticObject(
                                    new SelectQuery()
                                            .addAllColumns()
                                            .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Person_Director_Join)
                                            .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Director_Movie_Join)
                                            .addCondition(BinaryCondition.like(DatabaseResources.person_name, agent.getSemanticTextAsLikeClause() ))
                                            .addCondition(BinaryCondition.like(DatabaseResources.movie_name,  recipient.getSemanticTextAsLikeClause() ))
                            );


    // VBD
    Function<NPSemanticObject, Function<NPSemanticObject, VBDSemanticObject>> directed =
            (NPSemanticObject movie) ->
                    (NPSemanticObject person) ->
                            new VBDSemanticObject(
                                    new SelectQuery()
                                            .addAllColumns()
                                            .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Person_Director_Join)
                                            .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Director_Movie_Join)
                                            .addCondition(BinaryCondition.like(DatabaseResources.person_name, person.getSemanticTextAsLikeClause() ))
                                            .addCondition(BinaryCondition.like(DatabaseResources.movie_name,  movie.getSemanticTextAsLikeClause() ))
                            );

    Function<NPSemanticObject, Function<SemanticObject, VBDSemanticObject>> did =
            (NPSemanticObject recipient) ->
                    (SemanticObject agent) ->
                            new VBDSemanticObject(
                                    // Noun Phrase or Verb Phrase
                                    new SelectQuery()
                                            .addAllColumns()
                                            .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Person_Director_Join)
                                            .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Director_Movie_Join)
                                            .addCondition(BinaryCondition.like(DatabaseResources.person_name, agent.getSemanticTextAsLikeClause() ))
                                            .addCondition(BinaryCondition.like(DatabaseResources.movie_name,  recipient.getSemanticTextAsLikeClause() ))
                            );

    Function<NPSemanticObject, Function<NPSemanticObject, VBDSemanticObject>> won =
            (NPSemanticObject recipient) ->
                    (NPSemanticObject agent) ->
                            new VBDSemanticObject(
                                    new SelectQuery()
                                            .addAllColumns()
                                            .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Person_Director_Join)
                                            .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Director_Movie_Join)
                                            .addCondition(BinaryCondition.like(DatabaseResources.person_name, agent.getSemanticTextAsLikeClause() ))
                                            .addCondition(BinaryCondition.like(DatabaseResources.movie_name,  recipient.getSemanticTextAsLikeClause() ))
                            );
    // VBZ
    Function<SemanticObject, Function<NPSemanticObject, VBZSemanticObject>> is =
            (SemanticObject recipient) ->
                    (NPSemanticObject agent) -> {
                        // TODO: deal with PP vs VP
                        // do some sort of domain resolution

                        /*
                        if recipient.domain is geography
                            SELECT * FROM Countries
                        else if recipient.domain is movies
                            SELECT * FROM Director
                         */

                        // I need to create the query based on the Noun phrases
                        // noun phrases with determiners will have sub-queries ( IN (SELECT * FROM ASDF) )
                        // noun phrases with proper nouns will be entities ( LIKE "%%ASDF%%" )
                        return new VBZSemanticObject(
                                new SelectQuery()
                                        .addAllColumns()
                                        .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Person_Director_Join)
                                        .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Director_Movie_Join)
                                        .addCondition(BinaryCondition.like(DatabaseResources.person_name, recipient.getSemanticTextAsLikeClause() ))
                                        .addCondition(BinaryCondition.like(DatabaseResources.movie_name,  agent.getSemanticTextAsLikeClause() ))
                        );
                    };

    Function<NPSemanticObject, Function<NPSemanticObject, VBZSemanticObject>> sings =
            (NPSemanticObject recipient) ->
                    (NPSemanticObject agent) ->
                            new VBZSemanticObject(
                                    new SelectQuery()
                                            .addAllColumns()
                                            .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Person_Director_Join)
                                            .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Director_Movie_Join)
                                            .addCondition(BinaryCondition.like(DatabaseResources.person_name, agent.getSemanticTextAsLikeClause() ))
                                            .addCondition(BinaryCondition.like(DatabaseResources.movie_name,  recipient.getSemanticTextAsLikeClause() ))
                            );

    // VBP
    // TODO: SBAR
    Function<SemanticObject, VBPSemanticObject> does =
        (SemanticObject sbar) ->
                new VBPSemanticObject(
                        new SelectQuery()
                                .addAllColumns()
                                .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Person_Director_Join)
                                .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Director_Movie_Join)
                                .addCondition(BinaryCondition.like(DatabaseResources.person_name, sbar.getSemanticTextAsLikeClause() ))
                                .addCondition(BinaryCondition.like(DatabaseResources.movie_name,  sbar.getSemanticTextAsLikeClause() ))
                );

    Function<NPSemanticObject, Function<NPSemanticObject, VBPSemanticObject>> include =
            (NPSemanticObject recipient) ->
                    (NPSemanticObject agent) ->
                        new VBPSemanticObject(
                                new SelectQuery()
                                        .addAllColumns()
                                        .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Person_Director_Join)
                                        .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Director_Movie_Join)
                                        .addCondition(BinaryCondition.like(DatabaseResources.person_name, recipient.getSemanticTextAsLikeClause() ))
                                        .addCondition(BinaryCondition.like(DatabaseResources.movie_name,  agent.getSemanticTextAsLikeClause() ))
                        );


    public Map<String,Function> actualizedWordToSemanticFunction;
    public Map<String,SemanticObject> treebankTagToSemanticObject;
    public SemanticLibrary() {
        this.actualizedWordToSemanticFunction = new Hashtable<>();
        // VBD
        this.actualizedWordToSemanticFunction.put("directed", directed);
        this.actualizedWordToSemanticFunction.put("did", did);
        this.actualizedWordToSemanticFunction.put("won", won);
        // VBZ
        this.actualizedWordToSemanticFunction.put("is", is);
        this.actualizedWordToSemanticFunction.put("sings", sings);
        // VB
        this.actualizedWordToSemanticFunction.put("win", win);
        this.actualizedWordToSemanticFunction.put("sing", sing);
        // VBP
        this.actualizedWordToSemanticFunction.put("does", does);
        this.actualizedWordToSemanticFunction.put("include", include);



        this.treebankTagToSemanticObject = new Hashtable<>();
        this.treebankTagToSemanticObject.put(ADVPSemanticObject.treebankTag, new ADVPSemanticObject(""));
        this.treebankTagToSemanticObject.put(CDSemanticObject.treebankTag, new CDSemanticObject(""));
        this.treebankTagToSemanticObject.put(DTSemanticObject.treebankTag, new DTSemanticObject(""));
        this.treebankTagToSemanticObject.put(INSemanticObject.treebankTag, new INSemanticObject(""));
        this.treebankTagToSemanticObject.put(JJSemanticObject.treebankTag, new JJSemanticObject(""));
        this.treebankTagToSemanticObject.put(JJRSemanticObject.treebankTag, new JJRSemanticObject(""));
        this.treebankTagToSemanticObject.put(JJSSemanticObject.treebankTag, new JJSSemanticObject(""));
        this.treebankTagToSemanticObject.put(NNPSemanticObject.treebankTag, new NNPSemanticObject(""));
        this.treebankTagToSemanticObject.put(NNSemanticObject.treebankTag, new NNSemanticObject(""));
        this.treebankTagToSemanticObject.put(NPSemanticObject.treebankTag, new NPSemanticObject(""));
        this.treebankTagToSemanticObject.put(PPSemanticObject.treebankTag, new PPSemanticObject(""));
        this.treebankTagToSemanticObject.put(ROOTSemanticObject.treebankTag, new ROOTSemanticObject(""));
        this.treebankTagToSemanticObject.put(SBARSemanticObject.treebankTag, new SBARSemanticObject(""));
        this.treebankTagToSemanticObject.put(SBARQSemanticObject.treebankTag, new SBARQSemanticObject(""));
        this.treebankTagToSemanticObject.put(SQSemanticObject.treebankTag, new SQSemanticObject(""));
        this.treebankTagToSemanticObject.put(SSemanticObject.treebankTag, new SSemanticObject(""));
        this.treebankTagToSemanticObject.put(VBSemanticObject.treebankTag, new VBSemanticObject(""));
        this.treebankTagToSemanticObject.put(VBDSemanticObject.treebankTag, new VBDSemanticObject(""));
        this.treebankTagToSemanticObject.put(VBPSemanticObject.treebankTag, new VBPSemanticObject(""));
        this.treebankTagToSemanticObject.put(VBZSemanticObject.treebankTag, new VBZSemanticObject(""));
        this.treebankTagToSemanticObject.put(VPSemanticObject.treebankTag, new VPSemanticObject(""));
        this.treebankTagToSemanticObject.put(WDTSemanticObject.treebankTag, new WDTSemanticObject(""));
        this.treebankTagToSemanticObject.put(WPSemanticObject.treebankTag, new WPSemanticObject(""));
        this.treebankTagToSemanticObject.put(WHNPSemanticObject.treebankTag, new WHNPSemanticObject(""));
        this.treebankTagToSemanticObject.put(endOfSentanceSemanticObject.treebankTag, new endOfSentanceSemanticObject(""));
    }
}
