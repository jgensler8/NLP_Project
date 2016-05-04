package com.genslerj.SemanticAttachment;

import com.genslerj.DatabaseTermExtractor.DatabaseResources;
import com.healthmarketscience.sqlbuilder.*;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbColumn;

import javax.xml.crypto.Data;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by genslerj on 4/28/16.
 */
public class SemanticLibrary {
    // VB
    Function<NPSemanticObject, Function<NPSemanticObject, VBSemanticObject>> vb_win =
            (NPSemanticObject recipient) ->
                    (NPSemanticObject agent) -> {
                        Condition recipientCondition = null;
                        if(recipient.isSemanticQueryModified()) {
                            List<DbColumn> recipient_columns = NPHelper.nounPhraseToColumn(recipient);
                            List<Condition> recipientConditions = new ArrayList<>();
                            recipient_columns.stream().forEach(
                                    (DbColumn column) -> recipientConditions.add(new InCondition(column, recipient.semanticQuery)) );
                            recipientCondition = ComboCondition.or(recipientConditions.toArray(new Condition[recipientConditions.size()]));
                        }
                        else {
                            recipientCondition = BinaryCondition.like(DatabaseResources.oscar_type, "%%" );
                        }
                        Condition agentCondition= null;
                        if(agent.isSemanticQueryModified()) {
                            List<Condition> agentConditions = new ArrayList<>();
                            List<DbColumn> agent_columns = NPHelper.nounPhraseToColumn(agent);
                            agent_columns.stream().forEach(
                                    (DbColumn column) -> agentConditions.add(new InCondition(column, agent.semanticQuery)) );
                            agentCondition = ComboCondition.or(agentConditions.toArray(new Condition[agentConditions.size()]));
                        }
                        else {
                            agentCondition = BinaryCondition.like(DatabaseResources.person_name,  agent.getSemanticTextAsLikeClause() );
                        }
                        SelectQuery part1 = new SelectQuery()
                                .addAllColumns()
                                .addFromTable(DatabaseResources.oscarTable)
                                .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Oscar_Person_Join)
                                .addCondition(recipientCondition)
                                .addCondition(agentCondition);
                        SelectQuery part2 = new SelectQuery()
                                .addAllColumns()
                                .addFromTable(DatabaseResources.oscarTable)
                                .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Oscar_Movie_Join)
                                .addCondition(recipientCondition)
                                .addCondition(agentCondition);
                        // use validate to find the right query
                        List<SelectQuery> queries = new ArrayList<>(2);
                        queries.add(part1);
                        queries.add(part2);
                        SelectQuery[] valid_queries = queries.stream().filter(q -> DatabaseResources.isValidQuery(q)).toArray(SelectQuery[]::new);
                        if(valid_queries.length > 1)
                            return new VBSemanticObject( new UnionQuery( SetOperationQuery.Type.UNION, valid_queries) );
                        else if(valid_queries.length == 1)
                            return new VBSemanticObject( valid_queries[0] );
                        else return new VBSemanticObject("");
                    };

    Function<NPSemanticObject, Function<NPSemanticObject, VBSemanticObject>> vb_sing =
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

    Function<NPSemanticObject, Function<NPSemanticObject, VBSemanticObject>> vb_lie =
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

    Function<NPSemanticObject, Function<NPSemanticObject, VBSemanticObject>> vb_have =
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

    Function<NPSemanticObject, Function<NPSemanticObject, VBSemanticObject>> vb_star =
            (NPSemanticObject recipient) ->
                    (NPSemanticObject agent) ->
                            new VBSemanticObject(
                                    new SelectQuery()
                                            .addAllColumns()
                                            .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Person_Actor_Join)
                                            .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Actor_Movie_Join)
                                            .addCondition(BinaryCondition.like(DatabaseResources.person_name, agent.getSemanticTextAsLikeClause() ))
                                            .addCondition(BinaryCondition.like(DatabaseResources.movie_name,  recipient.getSemanticTextAsLikeClause() ))
                            );

    // VBD
    Function<NPSemanticObject, Function<NPSemanticObject, VBDSemanticObject>> vbd_directed =
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

    Function<NPSemanticObject, Function<SemanticObject, VBDSemanticObject>> vbd_did =
            (NPSemanticObject recipient) ->
                    (SemanticObject agent) -> {
                        if(agent.getClass().equals(VPSemanticObject.class)) {
                            return new VBDSemanticObject( ((SemanticObject)agent.semanticFunction.apply(recipient)).semanticQuery );
                        }
                        else {
                            return new VBDSemanticObject("ZZZZZ");
                        }
                    };

    Function<NPSemanticObject, Function<NPSemanticObject, VBDSemanticObject>> vbd_won =
            (NPSemanticObject recipient) ->
                    (NPSemanticObject agent) -> {
                        Condition recipientCondition = null;
                        if(recipient.isSemanticQueryModified()) {
                            List<DbColumn> recipient_columns = NPHelper.nounPhraseToColumn(recipient);
                            List<Condition> recipientConditions = new ArrayList<>();
                            recipient_columns.stream().forEach(
                                    (DbColumn column) -> recipientConditions.add(new InCondition(column, recipient.semanticQuery)) );
                            recipientCondition = ComboCondition.or(recipientConditions.toArray(new Condition[recipientConditions.size()]));
                        }
                        else {
                            recipientCondition = BinaryCondition.like(DatabaseResources.oscar_type, "%%" );
                        }
                        Condition agentCondition= null;
                        if(agent.isSemanticQueryModified()) {
                            List<Condition> agentConditions = new ArrayList<>();
                            List<DbColumn> agent_columns = NPHelper.nounPhraseToColumn(agent);
                            agent_columns.stream().forEach(
                                    (DbColumn column) -> agentConditions.add(new InCondition(column, agent.semanticQuery)) );
                            agentCondition = ComboCondition.or(agentConditions.toArray(new Condition[agentConditions.size()]));
                        }
                        else {
                            agentCondition = BinaryCondition.like(DatabaseResources.person_name,  agent.getSemanticTextAsLikeClause() );
                        }
                        SelectQuery part1 = new SelectQuery()
                                .addAllColumns()
                                .addFromTable(DatabaseResources.oscarTable)
                                .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Oscar_Person_Join)
                                .addCondition(recipientCondition)
                                .addCondition(agentCondition);
                        SelectQuery part2 = new SelectQuery()
                                .addAllColumns()
                                .addFromTable(DatabaseResources.oscarTable)
                                .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Oscar_Movie_Join)
                                .addCondition(recipientCondition)
                                .addCondition(agentCondition);
                        // use validate to find the right query
                        List<SelectQuery> queries = new ArrayList<>(2);
                        queries.add(part1);
                        queries.add(part2);
                        SelectQuery[] valid_queries = queries.stream().filter(q -> DatabaseResources.isValidQuery(q)).toArray(SelectQuery[]::new);
                        if(valid_queries.length > 1)
                            return new VBDSemanticObject( new UnionQuery( SetOperationQuery.Type.UNION, valid_queries) );
                        else if(valid_queries.length == 1)
                            return new VBDSemanticObject( valid_queries[0] );
                        else return new VBDSemanticObject("");
                    };
    // VBZ
    Function<SemanticObject, Function<NPSemanticObject, VBZSemanticObject>> vbz_is =
            (SemanticObject recipient) ->
                    (NPSemanticObject agent) -> {
                        // TODO: deal with PP vs VP
                        // TODO: Semantic Object or NP to table/join resolution

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

    Function<NPSemanticObject, Function<NPSemanticObject, VBZSemanticObject>> vbz_sings =
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

    Function<NPSemanticObject, Function<NPSemanticObject, VBZSemanticObject>> vbz_does =
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
    Function<SBARQSemanticObject, VBPSemanticObject> vbp_does =
        (SBARQSemanticObject sbarqSemanticObject) ->
                new VBPSemanticObject(
                            new SelectQuery()
                                    .addAllColumns()
                                    .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Person_Director_Join)
                                    .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Director_Movie_Join)
                                    .addCondition(BinaryCondition.like(DatabaseResources.person_name, sbarqSemanticObject.getSemanticTextAsLikeClause() ))
                                    .addCondition(BinaryCondition.like(DatabaseResources.movie_name,  sbarqSemanticObject.getSemanticTextAsLikeClause() ))
                    );

    Function<NPSemanticObject, Function<NPSemanticObject, VBPSemanticObject>> vbp_include =
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

    public Map<String,Function> VBDtoSemanticFunction;
    public Map<String,Function> VBZtoSemanticFunction;
    public Map<String,Function> VBtoSemanticFunction;
    public Map<String,Function> VBPtoSemanticFunction;
    public Map<String,SemanticObject> treebankTagToSemanticObject;
    public SemanticLibrary() {
        // VBD
        VBDtoSemanticFunction = new Hashtable<>();
        this.VBDtoSemanticFunction.put("directed", vbd_directed);
        this.VBDtoSemanticFunction.put("did", vbd_did);
        this.VBDtoSemanticFunction.put("won", vbd_won);
        // VBZ
        VBZtoSemanticFunction = new Hashtable<>();
        this.VBZtoSemanticFunction.put("is", vbz_is);
        this.VBZtoSemanticFunction.put("sings", vbz_sings);
        this.VBZtoSemanticFunction.put("does", vbz_does);
        // VB
        VBtoSemanticFunction = new Hashtable<>();
        this.VBtoSemanticFunction.put("win", vb_win);
        this.VBtoSemanticFunction.put("sing", vb_sing);
        this.VBtoSemanticFunction.put("lie", vb_lie);
        this.VBtoSemanticFunction.put("have", vb_have);
        this.VBtoSemanticFunction.put("star", vb_star);
        // VBP
        VBPtoSemanticFunction = new Hashtable<>();
        this.VBPtoSemanticFunction.put("does", vbp_does);
        this.VBPtoSemanticFunction.put("include", vbp_include);


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
        this.treebankTagToSemanticObject.put(NNSSemanticObject.treebankTag, new NNSSemanticObject(""));
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
        this.treebankTagToSemanticObject.put(WHPPSemanticObject.treebankTag, new WHPPSemanticObject(""));
        this.treebankTagToSemanticObject.put(endOfSentanceSemanticObject.treebankTag, new endOfSentanceSemanticObject(""));
    }
}
