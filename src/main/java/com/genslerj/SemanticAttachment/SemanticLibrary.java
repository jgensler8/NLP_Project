package com.genslerj.SemanticAttachment;

import java.util.function.Function;

/**
 * Created by genslerj on 4/28/16.
 */
public interface SemanticLibrary {
    Function<NPSemanticObject, Function<NPSemanticObject, VBDSemanticObject>> directed =
            (NPSemanticObject npSemanticObject1) ->
                    (NPSemanticObject npSemanticObject2) ->
                            new VBDSemanticObject( String.format("SELECT * FROM Person AS p INNER JOIN Director AS d INNER JOIN Movie AS m WHERE p.id = d.director_id AND p.name LIKE '%%%s%%' AND m.name LIKE '%%%s%%';", npSemanticObject1.semanticText, npSemanticObject2.semanticText));
}
