package com.genslerj.DatabaseTermExtractor;

/**
 * Created by genslerj on 4/9/16.
 */
public class DatabaseResources {
    public static String SQLITE_AFFIX = "jdbc:sqlite:";

    public static String MUSIC_DB_PATH = "src/main/resources/SqliteDatabases/music.sqlite";
    public static String GEOGRAPHY_DB_PATH = "src/main/resources/SqliteDatabases/WorldGeography.sqlite";
    public static String MOVIE_DB_PATH = "src/main/resources/SqliteDatabases/oscar-movie_imdb.sqlite";

    public static String MUSIC_CONNECTION_STRING = String.format("%s%s", SQLITE_AFFIX, MUSIC_DB_PATH);
    public static String GEOGRAPHY_CONNECTION_STRING = String.format("%s%s", SQLITE_AFFIX, GEOGRAPHY_DB_PATH);
    public static String MOVIES_CONNECTION_STRING = String.format("%s%s", SQLITE_AFFIX, MOVIE_DB_PATH);

    // This is used inside SQLite to "reflect" on the database (and get columns, tables, etc)
    public static String DATABASE_NAME = "main";

}
