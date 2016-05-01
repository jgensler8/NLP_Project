package com.genslerj.DatabaseTermExtractor;

import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.SelectQuery;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by genslerj on 4/21/16.
 */
public class DatabaseQueryAnswererTest {

    @Test
    public void testBooleanQueryShouldReturnTrueWhenGivenTrueQuery() throws SQLException, ClassNotFoundException {
        DatabaseQueryAnswerer l = new DatabaseQueryAnswerer(DatabaseResources.MOVIES_CONNECTION_STRING, DatabaseResources.DATABASE_NAME, "movies");
        SelectQuery selectQuery = new SelectQuery()
                .addAllColumns()
                .addFromTable(DatabaseResources.movieTable)
                .addCondition(BinaryCondition.like(DatabaseResources.movie_name, "%dream%"));
        boolean result = l.runExistsQuery(selectQuery);
        assert(result == true);
    }

    @Test
    public void testBooleanQueryShouldReturnFalseWhenGivenFalseQuery() throws SQLException, ClassNotFoundException {
        DatabaseQueryAnswerer l = new DatabaseQueryAnswerer(DatabaseResources.MOVIES_CONNECTION_STRING, DatabaseResources.DATABASE_NAME, "movies");
        SelectQuery selectQuery = new SelectQuery()
                .addAllColumns()
                .addFromTable(DatabaseResources.movieTable)
                .addCondition(BinaryCondition.like(DatabaseResources.movie_name, "%zzzzzzz%"));
        boolean result = l.runExistsQuery(selectQuery);
        assert(result == false);
    }
}