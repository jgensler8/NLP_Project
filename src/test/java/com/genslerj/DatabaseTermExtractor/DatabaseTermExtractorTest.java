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

    String sqliteAffix = "jdbc:sqlite:";
    String musicDBPath = "/Users/genslerj/Downloads/NLP_Project/src/main/resources/SqliteDatabases/music.sqlite";
    String geographyDBPath = "/Users/genslerj/Downloads/NLP_Project/src/main/resources/SqliteDatabases/WorldGeography.sqlite";
    String moviesDBPath = "/Users/genslerj/Downloads/NLP_Project/src/main/resources/SqliteDatabases/oscar-movie_imdb.sqlite";
//    String databasePath = "resources/SqliteDatabases/music.sqlite";
    String musicConnectionString = String.format("%s%s", sqliteAffix, musicDBPath);
    String geographyConnectionString = String.format("%s%s", sqliteAffix, geographyDBPath);
    String moviesConnectionString = String.format("%s%s", sqliteAffix, moviesDBPath);
    String databaseName = "main";

    @Test
    public void connectToFilesystemDatabaseShouldNotThrowException() {
        try {
            DatabaseTermExtractor d = new DatabaseTermExtractor(musicConnectionString, databaseName, "music");
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
        DatabaseTermExtractor d = new DatabaseTermExtractor(geographyConnectionString, databaseName, "geography");

        // test
        DatabaseTermExtractorResult result = d.generateResult();

        // assert
        assert(Arrays.asList(result.getRelatedStrings()).contains("Rome"));
    }

    @Test
    public void testGeographyDataBaseShouldReturnParis() throws Exception {
        // arrange
        DatabaseTermExtractor d = new DatabaseTermExtractor(geographyConnectionString, databaseName, "geography");

        // test
        DatabaseTermExtractorResult result = d.generateResult();

        // assert
        assert(Arrays.asList(result.getRelatedStrings()).contains("Paris"));
    }

    @Test
    public void testGeographyDataBaseShouldReturnOttawa() throws Exception {
        // arrange
        DatabaseTermExtractor d = new DatabaseTermExtractor(geographyConnectionString, databaseName, "geography");

        // test
        DatabaseTermExtractorResult result = d.generateResult();

        // assert
        assert(Arrays.asList(result.getRelatedStrings()).contains("Ottawa"));
    }

    @Test
    public void testMoviesDataBaseShouldContainDanielCraig() throws Exception {
        // arrange
        DatabaseTermExtractor d = new DatabaseTermExtractor(moviesConnectionString, databaseName, "movies");

        // test
        DatabaseTermExtractorResult result = d.generateResult();

        // assert
        assert(Arrays.asList(result.getRelatedStrings()).contains("Daniel Craig"));
    }

    @Test
    public void testGetTablesFromGeographyDatabaseShouldContainCounties() throws Exception {
        // arrange
        DatabaseTermExtractor d = new DatabaseTermExtractor(geographyConnectionString, databaseName, "geography");

        // test
        String[] tableNames = d.getTableNames();

        // assert
        assert(Arrays.asList(tableNames).contains("Countries"));
    }

    @Test
    public void testGetTablesFromMoviesDatabaseShouldContainsActor() throws Exception {
        // arrange
        DatabaseTermExtractor d = new DatabaseTermExtractor(moviesConnectionString, databaseName, "movies");

        // test
        String[] tableNames = d.getTableNames();

        // assert
        assert(Arrays.asList(tableNames).contains("Actor"));
    }

    @Test
    public void testGetColumnsThatAreStringsFromCountriesDBShouldBeName() throws Exception {
        // arrange
        DatabaseTermExtractor d = new DatabaseTermExtractor(geographyConnectionString, databaseName, "geography");

        // test
        String[] columnNames = d.getColumnNameByType("Countries", SQLiteColumnTypes.VARCHAR);

        // assert
        assert(Arrays.asList(columnNames).contains("Name"));
    }

    @Test
    public void testGetElementsFromCountriesDatabaseShouldContainFrance() throws Exception {
        // arrange
        DatabaseTermExtractor d = new DatabaseTermExtractor(geographyConnectionString, databaseName, "geography");

        // test
        String[] columnNames = d.getStringElementsFromTable("Countries");

        // assert
        assert(Arrays.asList(columnNames).contains("France"));
    }
}