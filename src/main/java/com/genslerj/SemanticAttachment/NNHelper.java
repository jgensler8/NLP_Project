package com.genslerj.SemanticAttachment;

import com.genslerj.DatabaseTermExtractor.DatabaseResources;
import com.healthmarketscience.sqlbuilder.SelectQuery;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbColumn;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbTable;

/**
 * Created by genslerj on 5/2/16.
 */
public class NNHelper {
    public static DbTable commonNounToTableName(NNSemanticObject nnSemanticObject) {
        String semanticText = nnSemanticObject.semanticText.toLowerCase();
        if(semanticText.contains("winner") || semanticText.contains("actor"))
            return DatabaseResources.actorTable;
        else if (semanticText.contains("oscar"))
            return DatabaseResources.oscarTable;
        else if (semanticText.contains("movie"))
            return DatabaseResources.movieTable;
        else
            return null;
    }

    public static DbColumn commonNounToColumnName(NNSemanticObject nnSemanticObject) {
        String semanticText = nnSemanticObject.semanticText.toLowerCase();
        if(semanticText.contains("winner") || semanticText.contains("actor"))
            return DatabaseResources.person_id;
        else if (semanticText.contains("oscar"))
            return DatabaseResources.oscar_movie_id;
        else if (semanticText.contains("movie"))
            return DatabaseResources.movie_id;
        else
            return null;
    }
}
