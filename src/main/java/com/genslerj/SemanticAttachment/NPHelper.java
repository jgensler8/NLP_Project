package com.genslerj.SemanticAttachment;

import com.genslerj.DatabaseTermExtractor.DatabaseResources;
import com.healthmarketscience.sqlbuilder.Query;
import com.healthmarketscience.sqlbuilder.SelectQuery;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbColumn;
import com.sun.tools.javac.code.Attribute;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by genslerj on 5/3/16.
 */
public class NPHelper {
    public static List<DbColumn> textToColumns(String text) {
        ArrayList<DbColumn> results = new ArrayList<>();
        if(text.contains("movie")) {
            results.add(DatabaseResources.movie_id);
        }
        if(text.contains("actor")) {
            results.add(DatabaseResources.person_id);
        }
        if(text.contains("oscar")) {
            results.add(DatabaseResources.oscar_person_id);
            results.add(DatabaseResources.oscar_movie_id);
        }
        return results;
    }

    public static List<DbColumn> semanticQueryToColumn(Query semanticQuery) {
        String lower = semanticQuery.toString().toLowerCase();
        List<DbColumn> results = textToColumns(lower);
        if(results.size() == 0)
            return null;
        else
            return results;
    }

    public static List<DbColumn> semanticTextToColumn(String semanticText) {
        String lower = semanticText.toLowerCase();
        List<DbColumn> results = textToColumns(lower);
        if(results.size() == 0)
            return null;
        else
            return results;
    }

    public static List<DbColumn> nounPhraseToColumn(NPSemanticObject nnSemanticObject) {
        if(nnSemanticObject.isSemanticQueryModified()) {
            return semanticQueryToColumn(nnSemanticObject.semanticQuery);
        }
        else {
            return semanticTextToColumn(nnSemanticObject.semanticText);
        }
    };
}
