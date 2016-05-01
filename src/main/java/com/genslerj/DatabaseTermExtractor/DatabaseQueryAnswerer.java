package com.genslerj.DatabaseTermExtractor;

import com.healthmarketscience.sqlbuilder.SelectQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public boolean runExistsQuery(SelectQuery query) throws SQLException {
        ResultSet rs = this.runQuery(query.toString());
        return rs.next();
    }

    public List<String> runTopNResultQuery(SelectQuery query, int topN) throws SQLException {
        ResultSet rs = this.runQuery(query.toString());
        ArrayList<String> results = new ArrayList<>();
        while(rs.next()) {
            results.add(rs.getString(1));
        }
        return results.subList(0, topN);
    }

    public ResultSet runQuery(String query) throws SQLException {
        System.out.println(query);
        Statement statement = this.c.createStatement();
        statement.setQueryTimeout(30);  // set timeout to 30 sec.
        return statement.executeQuery( query );
    }
}
