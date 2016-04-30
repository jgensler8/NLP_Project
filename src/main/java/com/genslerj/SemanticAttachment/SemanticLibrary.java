package com.genslerj.SemanticAttachment;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by genslerj on 4/28/16.
 */
public class SemanticLibrary {
    Function<NPSemanticObject, Function<NPSemanticObject, VBDSemanticObject>> directed =
            (NPSemanticObject movie) ->
                    (NPSemanticObject person) ->
                            new VBDSemanticObject(
                                    String.format("SELECT * FROM Person AS p INNER JOIN Director AS d INNER JOIN Movie AS m WHERE p.id = d.director_id AND d.movie_id = m.id AND p.name LIKE '%%%s%%' AND m.name LIKE '%%%s%%';",
                                            person.semanticText,
                                            movie.semanticText));

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
                                String.format("SELECT * FROM Person WHERE name LIKE '%%%s%%' AND name IN (%s)",
                                        recipient.semanticText,
                                        agent.semanticText));
                    };

    public Map<String,Function> actualizedWordToSemanticFunction;
    public Map<String,SemanticObject> treebankTagToSemanticObject;
    public SemanticLibrary() {
        this.actualizedWordToSemanticFunction = new Hashtable<>();
        this.actualizedWordToSemanticFunction.put("directed", directed);
        this.actualizedWordToSemanticFunction.put("is", is);

        this.treebankTagToSemanticObject = new Hashtable<>();
        this.treebankTagToSemanticObject.put(DTSemanticObject.treebankTag, new DTSemanticObject(""));
        this.treebankTagToSemanticObject.put(NNPSemanticObject.treebankTag, new NNPSemanticObject(""));
        this.treebankTagToSemanticObject.put(NNSemanticObject.treebankTag, new NNSemanticObject(""));
        this.treebankTagToSemanticObject.put(NPSemanticObject.treebankTag, new NPSemanticObject(""));
        this.treebankTagToSemanticObject.put(SQSemanticObject.treebankTag, new SQSemanticObject(""));
        this.treebankTagToSemanticObject.put(SSemanticObject.treebankTag, new SSemanticObject(""));
        this.treebankTagToSemanticObject.put(VBDSemanticObject.treebankTag, new VBDSemanticObject(""));
        this.treebankTagToSemanticObject.put(VBZSemanticObject.treebankTag, new VBZSemanticObject(""));
        this.treebankTagToSemanticObject.put(VPSemanticObject.treebankTag, new VPSemanticObject(""));
        this.treebankTagToSemanticObject.put(endOfSentanceSemanticObject.treebankTag, new endOfSentanceSemanticObject(""));
        this.treebankTagToSemanticObject.put(RootSemanticObject.treebankTag, new RootSemanticObject(""));
    }
}
