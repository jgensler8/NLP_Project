package com.genslerj.SemanticAttachment;

import java.util.function.Function;

/**
 * Created by genslerj on 4/28/16.
 */
public class SSemanticObject extends SemanticObject {
    public SSemanticObject(String semanticText){ super(semanticText); }
    public SSemanticObject(Function semanticFunction){ super(semanticFunction); }

    public static Function<NPSemanticObject, Function<VPSemanticObject, SSemanticObject>> sSemanticFunction1 = (NPSemanticObject npSemanticObject) -> (VPSemanticObject vpSemanticObject) -> {
        SemanticObject intermediateSemanticObject = (SemanticObject) vpSemanticObject.semanticFunction.apply(npSemanticObject);
        return new SSemanticObject(intermediateSemanticObject.semanticText);
    };

}
