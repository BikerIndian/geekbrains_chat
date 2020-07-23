package server;

import java.sql.*;

public class DbHandler implements AuthService {

    private Connection connection;
    private Statement stmt;

    public DbHandler() {
        connect();
    }

    public void connect() {

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:chat_db.db");
            stmt = connection.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        login = login.trim();
        password = password.trim();

        String nickname = "";
        try {
            ResultSet rs = stmt.executeQuery(String.format("SELECT nickname FROM users WHERE login = '%s' AND password = '%s';", login, password));
            rs.next();
            nickname = rs.getString("nickname");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nickname;
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        login = login.trim();
        password = password.trim();
        nickname = nickname.trim();


        try {

            // Проверка одинаковых логинов и ников
            String requestLogin = String.format("SELECT login FROM users WHERE login = '%s';", login);
            String requestNickname  = String.format("SELECT nickname FROM users WHERE nickname = '%s';", nickname);

            if (isAttend(requestLogin) || isAttend(requestNickname)) {
                return false;
            }

            String request = String.format("INSERT INTO users (login, password, nickname) VALUES ('%s', '%s','%s');", login, password, nickname);
            stmt.executeUpdate(request);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    private boolean isAttend(String request) throws SQLException {
        ResultSet rs = null;
        rs = stmt.executeQuery(request);
        rs.next();
        if (0 < rs.getRow()) {
            rs.close();
            return true;
        }
        rs.close();
        return false;
    }


    public void disconnect() {
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
