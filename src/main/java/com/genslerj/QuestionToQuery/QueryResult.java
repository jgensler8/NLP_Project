package com.genslerj.QuestionToQuery;

import com.genslerj.DatabaseTermExtractor.DatabaseQueryAnswerer;

/**
 * Created by genslerj on 4/21/16.
 */
public interface QueryResult {
    String query = ";";

    String getQuery();
    String invokeQuery(DatabaseQueryAnswerer a);
}


//SELECT * FROM Person p INNER JOIN Director d ON p.id = d.director_id INNER JOIN Movie m ON d.movie_id = m.id WHERE p.name IN (SELECT name FROM Person WHERE pob LIKE "%USA%");
