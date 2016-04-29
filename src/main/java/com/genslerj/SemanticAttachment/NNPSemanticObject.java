package com.genslerj.SemanticAttachment;

import java.util.function.Function;

/**
 * Created by genslerj on 4/28/16.
 */
public class NNPSemanticObject extends SemanticObject {
    public NNPSemanticObject(String semanticText){ super(semanticText); }
    public NNPSemanticObject(Function semanticFunction){ super(semanticFunction); }

    public static Function<ActualizedSemanticObject, NNPSemanticObject> nnpSemanticObjectSemanticFunction1 = (ActualizedSemanticObject actualizedSemanticObject) -> new NNPSemanticObject(actualizedSemanticObject.semanticText);
}
