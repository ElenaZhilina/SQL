package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLHelper {
    private static final QueryRunner queryRunner = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(System.getProperty("db.url"), "app", "pass");
    }

    @SneakyThrows
    public static String getCode() {
        var codeSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";
        var conn = getConnection();
        return queryRunner.query(conn, codeSQL, new ScalarHandler<>());
    }

    @SneakyThrows
    public static void cleanDB() {
        var conn = getConnection();
        queryRunner.execute(conn, "DELETE FROM auth_codes");
        queryRunner.execute(conn, "DELETE FROM card_transactions");
        queryRunner.execute(conn, "DELETE FROM cards");
        queryRunner.execute(conn, "DELETE FROM users");
    }

    @SneakyThrows
    public static void cleanCode() {
        var conn = getConnection();
        queryRunner.execute(conn, "DELETE FROM auth_codes");
    }

}
