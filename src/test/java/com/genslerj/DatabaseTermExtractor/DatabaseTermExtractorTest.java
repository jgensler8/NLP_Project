package com.genslerj.DatabaseTermExtractor;

import org.apache.xerces.impl.xs.SchemaSymbols;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by genslerj on 4/7/16.
 */
public class DatabaseTermExtractorTest {

    String sqliteAffix = "jdbc:sqlite:";
//    String databasePath = "/Users/genslerj/Downloads/NLP_Project/src/main/resources/SqliteDatabases/music.sqlite";
    String databasePath = "/Users/Oscar/Downloads/NLP_Project/src/main/resources/SqliteDatabases/music.sqlite";
//    String databasePath = "resources/SqliteDatabases/music.sqlite";

    @Test
    public void connectToFilesystemDatabaseShouldNotThrowException() {
        String connectionString = String.format("%s%s", sqliteAffix, databasePath);
        try {
            DatabaseTermExtractor d = new DatabaseTermExtractor(connectionString);
        } catch (Exception e)
        {
            System.out.println(e.toString());
            assert(false);
        }
        assert(true);
    }
}