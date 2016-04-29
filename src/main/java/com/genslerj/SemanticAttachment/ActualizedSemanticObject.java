package com.genslerj.SemanticAttachment;

import java.util.function.Function;

/**
 * Created by genslerj on 4/28/16.
 */
public class ActualizedSemanticObject extends SemanticObject {
    public ActualizedSemanticObject(String semanticText){ super(semanticText); }
    public ActualizedSemanticObject(Function semanticFunction){ super(semanticFunction); }

    public static Function<String, ActualizedSemanticObject> actualizedSemanticObjectSemanticFunction1 = (String actualizedWord) -> new ActualizedSemanticObject(actualizedWord);
}
