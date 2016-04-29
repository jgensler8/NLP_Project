package com.genslerj.SemanticAttachment;

import java.util.function.Function;

/**
 * Created by genslerj on 4/28/16.
 */
public class RootSemanticObject extends SemanticObject {
    public RootSemanticObject(String semanticText){ super(semanticText); }
    public RootSemanticObject(Function semanticFunction){ super(semanticFunction); }

    public static Function<SSemanticObject, RootSemanticObject> rootSemanticFunction1 = (SSemanticObject sSemanticObject) -> new RootSemanticObject(sSemanticObject.semanticText);
}
