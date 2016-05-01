package com.genslerj.SemanticAttachment;

import com.genslerj.DatabaseTermExtractor.DatabaseQueryAnswerer;
import com.genslerj.DatabaseTermExtractor.DatabaseResources;
import com.healthmarketscience.sqlbuilder.SelectQuery;

import java.sql.SQLException;
import java.util.List;
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

class wrapper {
    public void dosomething() throws SQLException, ClassNotFoundException {
        SelectQuery query =
                new SelectQuery()
//                        .addAllColumns()
                        .addColumns(DatabaseResources.person_name)
                        .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Person_Director_Join)
                        .addJoins(SelectQuery.JoinType.INNER, DatabaseResources.Director_Movie_Join)
                        .validate();
        System.out.println(query);

        DatabaseQueryAnswerer moviesDatabaseQueryAnswerer;
        moviesDatabaseQueryAnswerer = new DatabaseQueryAnswerer(DatabaseResources.MOVIES_CONNECTION_STRING, DatabaseResources.DATABASE_NAME, "movies");
        List<String> result = moviesDatabaseQueryAnswerer.runTopNResultQuery(query, 1);
        System.out.println(result);
    }
}