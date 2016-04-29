package com.genslerj.DatabaseTermExtractor;

import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by genslerj on 4/21/16.
 */
public class DatabaseQueryAnswererTest {

    @Test
    public void testBooleanQueryShouldReturnTrueWhenGivenTrueQuery() throws SQLException, ClassNotFoundException {
        DatabaseQueryAnswerer l = new DatabaseQueryAnswerer(DatabaseResources.MOVIES_CONNECTION_STRING, DatabaseResources.DATABASE_NAME, "movies");
        boolean result = l.runExistsQuery("SELECT * FROM Movie as m WHERE m.name LIKE '%dream%';");
        assert(result == true);
    }

    @Test
    public void testBooleanQueryShouldReturnFalseWhenGivenFalseQuery() throws SQLException, ClassNotFoundException {
        DatabaseQueryAnswerer l = new DatabaseQueryAnswerer(DatabaseResources.MOVIES_CONNECTION_STRING, DatabaseResources.DATABASE_NAME, "movies");
        boolean result = l.runExistsQuery("SELECT * FROM Movie as m WHERE m.name LIKE '%zzzzz%';");
        assert(result == false);
    }
}