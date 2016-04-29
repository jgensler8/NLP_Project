package com.genslerj.DatabaseTermExtractor;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by genslerj on 4/21/16.
 */
public class DatabaseQueryAnswerer {

    Connection c;
    String databaseName;
    String category;

    public DatabaseQueryAnswerer(String connectionString, String databaseName, String category) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.c = DriverManager.getConnection(connectionString);
        this.databaseName = databaseName;
        this.category = category;
    }

    public boolean runExistsQuery(String query) throws SQLException {
        ResultSet rs = this.runQuery(query);
//        ArrayList<String> results_arraylist = new ArrayList<String>();
        return rs.next();
    }

    public ResultSet runQuery(String query) throws SQLException {
        Statement statement = this.c.createStatement();
        statement.setQueryTimeout(30);  // set timeout to 30 sec.
        return statement.executeQuery( query );
    }
}
