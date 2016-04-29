package com.genslerj.SemanticAttachment;

import java.util.function.Function;

/**
 * Created by genslerj on 4/28/16.
 */
public class NPSemanticObject extends SemanticObject {
    public NPSemanticObject(String semanticText){ super(semanticText); }
    public NPSemanticObject(Function semanticFunction){ super(semanticFunction); }

    public static Function<NNPSemanticObject, NPSemanticObject> npSemanticObjectSemanticFunction1 = (NNPSemanticObject nnpSemanticObject) -> new NPSemanticObject(nnpSemanticObject.semanticText);
}
