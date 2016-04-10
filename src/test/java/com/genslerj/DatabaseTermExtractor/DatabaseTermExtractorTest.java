package com.genslerj.DatabaseTermExtractor;

import net.sf.extjwnl.data.Exc;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by genslerj on 4/7/16.
 */
public class DatabaseTermExtractorTest {

    @Test
    public void connectToFilesystemDatabaseShouldNotThrowException() {
        try {
            DatabaseTermExtractor d = new DatabaseTermExtractor(DatabaseResources.MUSIC_CONNECTION_STRING, DatabaseResources.DATABASE_NAME, "music");
        } catch (Exception e)
        {
            System.out.println(e.toString());
            assert(false);
        }
        assert(true);
    }

    @Test
    public void testGeographyDataBaseShouldReturnRome() throws Exception {
        // arrange
        DatabaseTermExtractor d = new DatabaseTermExtractor(DatabaseResources.GEOGRAPHY_CONNECTION_STRING, DatabaseResources.DATABASE_NAME, "geography");

        // test
        DatabaseTermExtractorResult result = d.generateResult();

        // assert
        assert(Arrays.asList(result.getRelatedStrings()).contains("Rome"));
    }

    @Test
    public void testGeographyDataBaseShouldReturnParis() throws Exception {
        // arrange
        DatabaseTermExtractor d = new DatabaseTermExtractor(DatabaseResources.GEOGRAPHY_CONNECTION_STRING, DatabaseResources.DATABASE_NAME, "geography");

        // test
        DatabaseTermExtractorResult result = d.generateResult();

        // assert
        assert(Arrays.asList(result.getRelatedStrings()).contains("Paris"));
    }

    @Test
    public void testGeographyDataBaseShouldReturnOttawa() throws Exception {
        // arrange
        DatabaseTermExtractor d = new DatabaseTermExtractor(DatabaseResources.GEOGRAPHY_CONNECTION_STRING, DatabaseResources.DATABASE_NAME, "geography");

        // test
        DatabaseTermExtractorResult result = d.generateResult();

        // assert
        assert(Arrays.asList(result.getRelatedStrings()).contains("Ottawa"));
    }

    @Test
    public void testMoviesDataBaseShouldContainDanielCraig() throws Exception {
        // arrange
        DatabaseTermExtractor d = new DatabaseTermExtractor(DatabaseResources.MOVIES_CONNECTION_STRING, DatabaseResources.DATABASE_NAME, "movies");

        // test
        DatabaseTermExtractorResult result = d.generateResult();

        // assert
        assert(Arrays.asList(result.getRelatedStrings()).contains("Daniel Craig"));
    }

    @Test
    public void testGetTablesFromGeographyDatabaseShouldContainCounties() throws Exception {
        // arrange
        DatabaseTermExtractor d = new DatabaseTermExtractor(DatabaseResources.GEOGRAPHY_CONNECTION_STRING, DatabaseResources.DATABASE_NAME, "geography");

        // test
        String[] tableNames = d.getTableNames();

        // assert
        assert(Arrays.asList(tableNames).contains("Countries"));
    }

    @Test
    public void testGetTablesFromMoviesDatabaseShouldContainsActor() throws Exception {
        // arrange
        DatabaseTermExtractor d = new DatabaseTermExtractor(DatabaseResources.MOVIES_CONNECTION_STRING, DatabaseResources.DATABASE_NAME, "movies");

        // test
        String[] tableNames = d.getTableNames();

        // assert
        assert(Arrays.asList(tableNames).contains("Actor"));
    }

    @Test
    public void testGetColumnsThatAreStringsFromCountriesDBShouldBeName() throws Exception {
        // arrange
        DatabaseTermExtractor d = new DatabaseTermExtractor(DatabaseResources.GEOGRAPHY_CONNECTION_STRING, DatabaseResources.DATABASE_NAME, "geography");

        // test
        String[] columnNames = d.getColumnNameByType("Countries", SQLiteColumnTypes.VARCHAR);

        // assert
        assert(Arrays.asList(columnNames).contains("Name"));
    }

    @Test
    public void testGetElementsFromCountriesDatabaseShouldContainFrance() throws Exception {
        // arrange
        DatabaseTermExtractor d = new DatabaseTermExtractor(DatabaseResources.GEOGRAPHY_CONNECTION_STRING, DatabaseResources.DATABASE_NAME, "geography");

        // test
        String[] columnNames = d.getStringElementsFromTable("Countries");

        // assert
        assert(Arrays.asList(columnNames).contains("France"));
    }
}