package com.genslerj.SemanticAttachment;

import java.util.function.Function;

/**
 * Created by genslerj on 4/27/16.
 */
@FunctionalInterface
public interface Semantics<T> {

//    interface RecursiveCurryableSemanticFunction<A, B> {
//        public B apply(final A a);
//    }

    Function<Semantics<T>, Semantics<T>> applySemanticObject(T o);
}

class Query {
    String selectClause = "";
    String fromClause = "";
    String whereClause = "";

    @Override
    public String toString() {
        return String.format("%s %s %s", selectClause, fromClause, whereClause);
    }
}

class wrapper {
    public void dosomething(){

        /*
(ROOT
  (S
    (NP (NNP Kubrik))
    (VP (VBD directed)
      (NP (NNP Hugo)))
    (. ?)))
         */

//        Query q = new Query();
//        q.fromClause = "FROM director AS d INNERJOIN Movies AS m";
//        q.whereClause = String.format("WHERE director.name LIKE %s AND Movie.name LIKE %s", director.semantics, movie.semantics);

        Function<NPSemanticObject, Function<NPSemanticObject, VBDSemanticObject>> directed_function =
                (NPSemanticObject npSemanticObject1) ->
                        (NPSemanticObject npSemanticObject2) ->
                                new VBDSemanticObject( "WHERE name like " + npSemanticObject1.semanticText + " AND LIKE " + npSemanticObject2.semanticText);
        ActualizedSemanticObject directed = new ActualizedSemanticObject(directed_function);

        ActualizedSemanticObject hugo = ActualizedSemanticObject.actualizedSemanticObjectSemanticFunction1
                .apply("Hugo");
            NNPSemanticObject hugo_nnp_intermediate = NNPSemanticObject.nnpSemanticObjectSemanticFunction1
                    .apply(hugo);
                NPSemanticObject hugo_np = NPSemanticObject.npSemanticObjectSemanticFunction1
                        .apply(hugo_nnp_intermediate);
        VBDSemanticObject directed_vbd_intermediate = VBDSemanticObject.vbdSemanticObjectSemanticFunction1
                .apply(directed);
            VPSemanticObject directed_vb_intermediate = VPSemanticObject.vpSemanticObjectSemanticFunction1
                    .apply(directed_vbd_intermediate)
                    .apply(hugo_np);
        ActualizedSemanticObject kubrik = ActualizedSemanticObject.actualizedSemanticObjectSemanticFunction1
                .apply("Kubrik");
            NNPSemanticObject kubrik_intermediate = NNPSemanticObject.nnpSemanticObjectSemanticFunction1
                    .apply(kubrik);
                NPSemanticObject kubrik_np = NPSemanticObject.npSemanticObjectSemanticFunction1
                        .apply(kubrik_intermediate);
        SSemanticObject directed_s_intermediate = SSemanticObject.sSemanticFunction1
                .apply(kubrik_np).apply(directed_vb_intermediate);
        RootSemanticObject root_semantic_obj = RootSemanticObject.rootSemanticFunction1.apply(directed_s_intermediate);

        System.out.println(root_semantic_obj.semanticText);
    }
}