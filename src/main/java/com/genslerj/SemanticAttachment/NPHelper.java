package com.genslerj.SemanticAttachment;

import com.healthmarketscience.sqlbuilder.SelectQuery;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbColumn;

/**
 * Created by genslerj on 5/3/16.
 */
public class NPHelper {
    public static DbColumn semanticQueryToColumn(SelectQuery semanticQuery) {
        return null;
    }

    public static DbColumn semanticTextToColumn(String semanticText) {
        return null;
    }

    public static DbColumn nounPhraseToColumn(NNSemanticObject nnSemanticObject) {
        if(nnSemanticObject.isSemanticQueryModified()) {
            return semanticQueryToColumn(nnSemanticObject.semanticQuery);
        }
        else {
            return semanticTextToColumn(nnSemanticObject.semanticText);
        }
    };
}
