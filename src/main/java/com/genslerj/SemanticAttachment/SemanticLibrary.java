package com.genslerj.SemanticAttachment;

import com.genslerj.DatabaseTermExtractor.DatabaseResources;
import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.SelectQuery;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by genslerj on 4/28/16.
 */
public class SemanticLibrary {
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

    Function<NPSemanticObject, Function<NPSemanticObject, VBDSemanticObject>> did =
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
    Function<NPSemanticObject, Function<NPSemanticObject, VBZSemanticObject>> is =
            (NPSemanticObject recipient) ->
                    (NPSemanticObject agent) -> {
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

        this.treebankTagToSemanticObject = new Hashtable<>();
        this.treebankTagToSemanticObject.put(DTSemanticObject.treebankTag, new DTSemanticObject(""));
        this.treebankTagToSemanticObject.put(INSemanticObject.treebankTag, new INSemanticObject(""));
        this.treebankTagToSemanticObject.put(JJSSemanticObject.treebankTag, new JJSSemanticObject(""));
        this.treebankTagToSemanticObject.put(NNPSemanticObject.treebankTag, new NNPSemanticObject(""));
        this.treebankTagToSemanticObject.put(NNSemanticObject.treebankTag, new NNSemanticObject(""));
        this.treebankTagToSemanticObject.put(NPSemanticObject.treebankTag, new NPSemanticObject(""));
        this.treebankTagToSemanticObject.put(PPSemanticObject.treebankTag, new PPSemanticObject(""));
        this.treebankTagToSemanticObject.put(RootSemanticObject.treebankTag, new RootSemanticObject(""));
        this.treebankTagToSemanticObject.put(SQSemanticObject.treebankTag, new SQSemanticObject(""));
        this.treebankTagToSemanticObject.put(SSemanticObject.treebankTag, new SSemanticObject(""));
        this.treebankTagToSemanticObject.put(VBDSemanticObject.treebankTag, new VBDSemanticObject(""));
        this.treebankTagToSemanticObject.put(VBZSemanticObject.treebankTag, new VBZSemanticObject(""));
        this.treebankTagToSemanticObject.put(VPSemanticObject.treebankTag, new VPSemanticObject(""));
        this.treebankTagToSemanticObject.put(endOfSentanceSemanticObject.treebankTag, new endOfSentanceSemanticObject(""));
    }
}
