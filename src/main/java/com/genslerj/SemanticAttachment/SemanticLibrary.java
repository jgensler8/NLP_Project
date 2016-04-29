package com.genslerj.SemanticAttachment;

import java.util.function.Function;

/**
 * Created by genslerj on 4/28/16.
 */
public interface SemanticLibrary {
    Function<NPSemanticObject, Function<NPSemanticObject, VBDSemanticObject>> directed =
            (NPSemanticObject movie) ->
                    (NPSemanticObject person) -> new VBDSemanticObject(String.format("SELECT * FROM Person AS p INNER JOIN Director AS d INNER JOIN Movie AS m WHERE p.id = d.director_id AND d.movie_id = m.id AND p.name LIKE '%%%s%%' AND m.name LIKE '%%%s%%';", person.semanticText, movie.semanticText));
}
