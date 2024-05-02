package org.example.kr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DAOManager {
    private Connection con;
    private Statement stmt;

    public DAOManager() {
        try {
            String url = "jdbc:postgresql://localhost/KR?user=postgres&password=kivar"; // Пароль, який ви робили при скачуванні postgres
            Class.forName("org.postgresql.Driver");
            System.out.println("Connecting to database...");
            this.con = DriverManager.getConnection(url);
            System.out.println("Connected to database");
            this.stmt = this.con.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() { return con; }

    public Statement getStatement() { return stmt; }
}
