package com.genslerj.DatabaseTermExtractor;

import java.sql.*;

/**
 * Created by genslerj on 4/7/16.
 */
public class DatabaseTermExtractor {

    Connection c;

    public DatabaseTermExtractor(String connectionString) throws Exception {
        Class.forName("org.sqlite.JDBC");
        this.c = DriverManager.getConnection(connectionString);
    }

}
