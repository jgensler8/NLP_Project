package com.genslerj.SemanticAttachment;

import com.healthmarketscience.sqlbuilder.SelectQuery;
import edu.stanford.nlp.trees.Tree;

import java.util.List;
import java.util.function.Function;

/**
 * Created by genslerj on 4/21/16.
 */
public class VPSemanticObject extends SemanticObject {
    public final static String treebankTag = "VP";

    public VPSemanticObject(String semanticText){ super(semanticText); }
    public VPSemanticObject(Function semanticFunction){ super(semanticFunction); }
    public VPSemanticObject(SelectQuery semanticQuery){ super(semanticQuery); }

    @Override
    public Function getCreationFunction(Tree t, List<SemanticObject> children_semantic_objects) {
        if(children_semantic_objects.get(0).getClass().equals(VBDSemanticObject.class)) {
            if(children_semantic_objects.size() == 2) return vpSemanticObjectSemanticFunction1;
            else if (children_semantic_objects.size() == 3) return vpSemanticObjectSemanticFunction2;
            else return vpSemanticObjectSemanticFunction3;
        }
        else if(children_semantic_objects.get(0).getClass().equals(VBZSemanticObject.class)) {
            if( children_semantic_objects.size() == 1)
                return vpSemanticObjectSemanticFunction10;
            else if(children_semantic_objects.get(1).getClass().equals(SBARSemanticObject.class))
                return vpSemanticObjectSemanticFunction7;
            else if (children_semantic_objects.get(1).getClass().equals(PPSemanticObject.class))
                return vpSemanticObjectSemanticFunction4;
            else
                return vpSemanticObjectSemanticFunction8;
        }
        else if(children_semantic_objects.get(0).getClass().equals(VBSemanticObject.class)){
            if(children_semantic_objects.size() == 1)
                return vpSemanticObjectSemanticFunction9;
            else if(children_semantic_objects.get(1).getClass().equals(NPSemanticObject.class))
                return vpSemanticObjectSemanticFunction5;
            else
                return vpSemanticObjectSemanticFunction11;
        }
        else return vpSemanticObjectSemanticFunction6;
    }

    public static Function<VBDSemanticObject, Function<NPSemanticObject, VPSemanticObject>> vpSemanticObjectSemanticFunction1 =
            (VBDSemanticObject vbdSemanticObject) ->
                    (NPSemanticObject npSemanticObject) -> {
                        VPSemanticObject vp = new VPSemanticObject(vbdSemanticObject.semanticFunction);
                        vp.semanticFunction = (Function) vp.semanticFunction.apply(npSemanticObject);
                        return vp;
                    };

    public static Function<VBDSemanticObject, Function<NPSemanticObject, Function<PPSemanticObject, VPSemanticObject>>> vpSemanticObjectSemanticFunction2 =
            (VBDSemanticObject vbdSemanticObject) ->
                    (NPSemanticObject npSemanticObject) ->
                            (PPSemanticObject ppSemanticObject) -> {
                                VPSemanticObject vp = new VPSemanticObject(vbdSemanticObject.semanticFunction);
                                vp.semanticFunction = (Function) vp.semanticFunction.apply(npSemanticObject);
                                return vp;
                            };

    public static Function<VBDSemanticObject, Function<NPSemanticObject, Function<PPSemanticObject, Function<PPSemanticObject, VPSemanticObject>>>> vpSemanticObjectSemanticFunction3 =
            (VBDSemanticObject vbdSemanticObject) ->
                    (NPSemanticObject npSemanticObject) ->
                            (PPSemanticObject ppSemanticObject1) ->
                                    (PPSemanticObject ppSemanticObject2 ) -> {
                                        VPSemanticObject vp = new VPSemanticObject(vbdSemanticObject.semanticFunction);
                                        vp.semanticFunction = (Function) vp.semanticFunction.apply(npSemanticObject);
                                        return vp;
                                    };

    public static Function<VBZSemanticObject, Function<PPSemanticObject, VPSemanticObject>> vpSemanticObjectSemanticFunction4 =
            (VBZSemanticObject vbzSemanticObject) ->
                    (PPSemanticObject ppSemanticObject) ->
                        new VPSemanticObject((Function) vbzSemanticObject.semanticFunction.apply(ppSemanticObject));

    public static Function<VBZSemanticObject, Function<NPSemanticObject, VPSemanticObject>> vpSemanticObjectSemanticFunction8 =
            (VBZSemanticObject vbzSemanticObject) ->
                    (NPSemanticObject npSemanticObject) ->
                        new VPSemanticObject((Function) vbzSemanticObject.semanticFunction.apply(npSemanticObject));

    public static Function<VBZSemanticObject, Function<SBARSemanticObject, VPSemanticObject>> vpSemanticObjectSemanticFunction7 =
            (VBZSemanticObject vbzSemanticObject) ->
                    (SBARSemanticObject sbarSemanticObject) ->
                        new VPSemanticObject(sbarSemanticObject.semanticQuery);

    public static Function<VBSemanticObject, Function<NPSemanticObject, VPSemanticObject>> vpSemanticObjectSemanticFunction5 =
            (VBSemanticObject vbSemanticObject) ->
                    (NPSemanticObject npSemanticObject) ->
                            new VPSemanticObject((Function) vbSemanticObject.semanticFunction.apply(npSemanticObject));

    public static Function<VBSemanticObject, Function<PPSemanticObject, VPSemanticObject>> vpSemanticObjectSemanticFunction11 =
            (VBSemanticObject vbSemanticObject) ->
                    (PPSemanticObject ppSemanticObject) -> {
                        NPSemanticObject npSemanticObject = new NPSemanticObject(ppSemanticObject.semanticText);
                        return new VPSemanticObject((Function) vbSemanticObject.semanticFunction.apply(npSemanticObject));
                    };

    public static Function<VBSemanticObject, VPSemanticObject> vpSemanticObjectSemanticFunction9 =
            (VBSemanticObject vbSemanticObject) ->
                            new VPSemanticObject(vbSemanticObject.semanticFunction);

    public static Function<VBPSemanticObject, Function<NPSemanticObject, VPSemanticObject>> vpSemanticObjectSemanticFunction6 =
            (VBPSemanticObject vbpSemanticObject) ->
                    (NPSemanticObject npSemanticObject) -> {
                        VPSemanticObject vp = new VPSemanticObject(vbpSemanticObject.semanticFunction);
                        vp.semanticFunction = (Function) vp.semanticFunction.apply(npSemanticObject);
                        return vp;
                    };

    public static Function<VBZSemanticObject, VPSemanticObject> vpSemanticObjectSemanticFunction10 =
            (VBZSemanticObject vbSemanticObject) ->
                    new VPSemanticObject(vbSemanticObject.semanticFunction);

}
