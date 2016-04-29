package com.genslerj.SemanticAttachment;

import java.util.function.Function;

/**
 * Created by genslerj on 4/21/16.
 */
public class VPSemanticObject extends SemanticObject {
    public VPSemanticObject(String semanticText){ super(semanticText); }
    public VPSemanticObject(Function semanticFunction){ super(semanticFunction); }

    public static Function<VBDSemanticObject, Function<NPSemanticObject, VPSemanticObject>> vpSemanticObjectSemanticFunction1 = (VBDSemanticObject vbdSemanticObject) -> (NPSemanticObject npSemanticObject) -> {
        VPSemanticObject vp = new VPSemanticObject(vbdSemanticObject.semanticFunction);
        vp.semanticFunction = (Function) vp.semanticFunction.apply(npSemanticObject);
        return vp;
    };
}
