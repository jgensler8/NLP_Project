package com.genslerj.SemanticAttachment;

import java.util.function.Function;

/**
 * Created by genslerj on 4/28/16.
 */
public class VBDSemanticObject extends SemanticObject {
    public VBDSemanticObject(String semanticText){ super(semanticText); }
    public VBDSemanticObject(Function semanticFunction){ super(semanticFunction); }

    public static Function<ActualizedSemanticObject, VBDSemanticObject> vbdSemanticObjectSemanticFunction1 = (ActualizedSemanticObject actualizedSemanticObject) -> new VBDSemanticObject(actualizedSemanticObject.semanticFunction);
}
