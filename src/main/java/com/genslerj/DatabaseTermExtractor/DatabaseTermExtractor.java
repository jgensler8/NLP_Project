package com.genslerj.DatabaseTermExtractor;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by genslerj on 4/7/16.
 */
public class DatabaseTermExtractor {

    Connection c;
    String databaseName;
    String category;

    public DatabaseTermExtractor(String connectionString, String databaseName, String category) throws Exception {
        Class.forName("org.sqlite.JDBC");
        this.c = DriverManager.getConnection(connectionString);
        this.databaseName = databaseName;
        this.category = category;
    }

    public DatabaseTermExtractorResult generateResult() throws SQLException {
        // get all tables
        String[] tables = this.getTableNames();
        // get all strings from those tables
        ArrayList<String> tables_arraylist = new ArrayList<String>(Arrays.asList(tables));
        ArrayList<String> results_arraylist = new ArrayList<String>();
        for(String tableName : tables_arraylist) {
            String[] strings = this.getStringElementsFromTable(tableName);
            results_arraylist.addAll(Arrays.asList(strings));
        }
        // construct a DatabaseTermExtractorResult
        String[] result_array = new String[results_arraylist.size()];
        result_array = results_arraylist.toArray(result_array);
        return new DatabaseTermExtractorResult.DatabaseTermExtractorResultBuilder()
                .setCategory(this.category)
                .setRelatedStrings(result_array)
                .build();
    }

    public ResultSet runQuery(String query) throws SQLException {
        Statement statement = this.c.createStatement();
        statement.setQueryTimeout(30);  // set timeout to 30 sec.
        return statement.executeQuery( query );
    }

    public String[] getTableNames() throws SQLException {
        ResultSet rs = this.runQuery(String.format("SELECT name FROM %s.sqlite_master WHERE type='table';", this.databaseName) );
        ArrayList<String> results_arraylist = new ArrayList<String>();
        while(rs.next())
        {
            // read the result set
            results_arraylist.add(rs.getString("name"));
        }
        String[] result_array = new String[results_arraylist.size()];
        result_array = results_arraylist.toArray(result_array);
        return result_array;
    }

    public String[] getStringElementsFromTable(String tableName) throws SQLException {
        // get columns that are strings
        String[] validColumns = this.getColumnNameByType(tableName, SQLiteColumnTypes.VARCHAR);
        // get all strings from that table
        ArrayList<String> validColumns_arraylist = new ArrayList<String>(Arrays.asList(validColumns));
        ArrayList<String> results_arraylist = new ArrayList<String>();
        for(String columnName : validColumns_arraylist) {
            ResultSet rs = this.runQuery(String.format("SELECT %s FROM %s", columnName, tableName));
            while(rs.next())
            {
                results_arraylist.add(rs.getString(columnName));
            }
        }
        String[] result_array = new String[results_arraylist.size()];
        result_array = results_arraylist.toArray(result_array);
        return result_array;
    }

    public String[] getColumnNameByType(String tableName, String columnType) throws SQLException {
        ResultSet rs = this.runQuery(String.format("PRAGMA table_info(%s);", tableName));
        ArrayList<String> results_arraylist = new ArrayList<String>();
        while(rs.next())
        {
            if(rs.getString("type").toUpperCase().contains(columnType))
            {
                results_arraylist.add(rs.getString("name"));
            }
        }
        String[] result_array = new String[results_arraylist.size()];
        result_array = results_arraylist.toArray(result_array);
        return result_array;
    }

//    public String[] ge
}
