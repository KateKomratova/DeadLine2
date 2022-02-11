package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;


import java.sql.Connection;
import java.sql.DriverManager;

public class DbInteractionDbUtils {

    @SneakyThrows
    public static String getCode() {
        QueryRunner runner = new QueryRunner();
        String codeSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";

        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mariadb://localhost:3306/app", "app", "pass");

        ) {
            String code = runner.query(conn, codeSQL, new ScalarHandler<>());
            return code;
        }
    }

    @SneakyThrows
    public static void deleteTables() {
        QueryRunner runner = new QueryRunner();
        String deleteUsers = "DELETE FROM users";
        String deleteCards = "DELETE FROM cards";
        String deleteAuth_codes = "DELETE FROM auth_codes";
        String deleteCard_transactions = "DELETE FROM card_transactions";

        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mariadb://localhost:3306/app", "app", "pass");

        ) {
            runner.update(conn, deleteCards);
            runner.update(conn, deleteAuth_codes);
            runner.update(conn, deleteCard_transactions);
            runner.update(conn, deleteUsers);
        }

    }
}
