package com.genslerj.DatabaseTermExtractor;

import com.healthmarketscience.sqlbuilder.dbspec.basic.*;
import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;

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

    // create default schema
    public static DbSpec spec = new DbSpec();
    public static DbSchema schema = spec.addDefaultSchema();

    // Person
    public static DbTable personTable = schema.addTable("Person");
    public static DbColumn person_id      = personTable.addColumn("id",   "char", 7);
    public static DbColumn person_name    = personTable.addColumn("name", "varchar", 255);
    public static DbColumn person_dob     = personTable.addColumn("dob",  "timestamp", null);
    public static DbColumn person_pob     = personTable.addColumn("pob",  "varchar", 128);

    // Director
    public static DbTable directorTable           = schema.addTable("Director");
    public static DbColumn director_director_id   = directorTable.addColumn("director_id",    "char", 7);
    public static DbColumn director_movie_id      = directorTable.addColumn("movie_id",       "char", 7);

    // Movie
    public static DbTable movieTable           = schema.addTable("Movie");
    public static DbColumn movie_id            = movieTable.addColumn("id",            "char", 7);
    public static DbColumn movie_name          = movieTable.addColumn("name",          "varchar", 64);
    public static DbColumn movie_year          = movieTable.addColumn("year",          "number", 64);
    public static DbColumn movie_rating        = movieTable.addColumn("rating",        "varchar", 5);
    public static DbColumn movie_runtime       = movieTable.addColumn("runtime",       "number", null);
    public static DbColumn movie_genre         = movieTable.addColumn("genre",         "varchar", 16);
    public static DbColumn movie_earnings_rank = movieTable.addColumn("earnings_rank", "number", null);

    // Actor
    public static DbTable actorTable      = schema.addTable("Actor");
    public static DbColumn actor_actor_id = actorTable.addColumn("actor_id", "char", 7);
    public static DbColumn actor_movie_id = actorTable.addColumn("movie_id", "char", 7);

    // Oscar
    public static DbTable oscarTable          = schema.addTable("Oscar");
    public static DbColumn oscar_movie_id     = oscarTable.addColumn("movie_id",  "char", 7);
    public static DbColumn oscar_person_id    = oscarTable.addColumn("person_id", "char", 7);
    public static DbColumn oscar_type         = oscarTable.addColumn("type",      "varchar", 23);
    public static DbColumn oscar_year         = oscarTable.addColumn("year",      "number", null);

    public static DbJoin Person_Director_Join = new DbJoin(spec, personTable, directorTable, new DbColumn[]{person_id}, new DbColumn[]{director_director_id});
    public static DbJoin Director_Movie_Join = new DbJoin(spec, directorTable, movieTable, new DbColumn[]{director_movie_id}, new DbColumn[]{movie_id});

    public static DbJoin Person_Actor_Join = new DbJoin(spec, personTable, actorTable, new DbColumn[]{person_id}, new DbColumn[]{actor_actor_id});
    public static DbJoin Actor_Movie_Join = new DbJoin(spec, actorTable, movieTable, new DbColumn[]{actor_movie_id}, new DbColumn[]{movie_id});

    public static DbJoin Oscar_Person_Join = new DbJoin(spec, oscarTable, personTable, new DbColumn[]{oscar_person_id}, new DbColumn[]{person_id});
}
