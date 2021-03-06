package telegram.sql;

import lombok.SneakyThrows;

import javax.ws.rs.GET;
import java.sql.*;
import java.util.ArrayList;

import static java.sql.DriverManager.getConnection;
import static telegram.sqlRequest.Request.*;

public class PostgreSQLJDBC {

    @SneakyThrows
    public Connection connect() {
        Connection c = null;
        c = getConnection("jdbc:postgresql://localhost:5432/" + System.getenv("DATABASE_NAME"),
                System.getenv("SQLUSER"), System.getenv("SQLPASSWORD"));
        c.setAutoCommit(false);
        return c;
    }

    /*
    CREATE TABLE IF NOT EXISTS users (
        id INTEGER PRIMARY KEY,
        firstname TEXT,
        lastname TEXT,
        role TEXT,
        type TEXT default 'normal',
        lastid TEXT default ''
    );
     */

    @SneakyThrows
    public Statement createStatementFromConnection(Connection connection) {
        Statement stmt = null;
        stmt = connection.createStatement();
        return stmt;
    }

    @SneakyThrows
    public void sendRequest(String sql, Statement statement) {
        statement.executeUpdate(sql);
        statement.close();
        statement.getConnection().commit();
        statement.getConnection().close();
    }

    @SneakyThrows
    public String sendGetRequest(String sql, Statement statement, String item, int id) {
        String res = "";
        ResultSet results = statement.executeQuery(sql);
        while (results.next()) {
            if (results.getInt("id") == id) {
                res = results.getString(item);
            }
        }
        statement.close();
        statement.getConnection().commit();
        statement.getConnection().close();
        return res;
    }

    @SneakyThrows
    public void createTimeTable(int id) {
        Statement statement = createStatementFromConnection(connect());
        sendRequest(String.format(CREATE_TIME_TABLE, id), statement );
    }

    //Set methods

    @SneakyThrows
    public void deleteData(int id) {
        Statement statement = createStatementFromConnection(connect());
        sendRequest(String.format(DELETE_USER, id), statement );
        deleteTimeTable(id);
    }

    @SneakyThrows
    public void insertData(int id, String firstname, String lastname, String role) {
        Statement statement = createStatementFromConnection(connect());
        sendRequest(String.format(INSERT_INTO_USERS, id, firstname, lastname, role), statement );
    }

    @SneakyThrows
    public void updateData(int id, String newRole) {
        Statement statement = createStatementFromConnection(connect());
        sendRequest(String.format(UPDATE_ROLE, newRole, id), statement );
    }

    @SneakyThrows
    public void setTypeOfMsg(int id, String type) {
        Statement statement = createStatementFromConnection(connect());
        sendRequest(String.format(SET_TYPE_OF_MSG, type, id), statement );
    }

    @SneakyThrows
    public void setLastid(int id, String lastid) {
        Statement statement = createStatementFromConnection(connect());
        sendRequest(String.format(SET_LAST_ID, lastid, id), statement );
    }

    @SneakyThrows
    public void setToTimeTable(int id, String time) {
        Statement statement = createStatementFromConnection(connect());
        sendRequest(String.format(INSERT_INTO_TIME_TABLE, id, time), statement );
    }

    public void deleteTimeTable(int id) {
        Statement statement = createStatementFromConnection(connect());
        sendRequest(String.format(DELETE_TIME_TABLE, id), statement );
    }

    //Get methods

    @SneakyThrows
    public String getRole(int id) {
        Statement statement = createStatementFromConnection(connect());
        return sendGetRequest(GET_ALL_DATA, statement, "role", id);
    }

    @SneakyThrows
    public String getFirstName(int id) {
        Statement statement = createStatementFromConnection(connect());
        return sendGetRequest(GET_ALL_DATA, statement, "firstname", id);
    }

    @SneakyThrows
    public String getLastName(int id) {
        Statement statement = createStatementFromConnection(connect());
        return sendGetRequest(GET_ALL_DATA, statement, "lastname", id);
    }

    @SneakyThrows
    public String getTypeOfMsg(int id) {
        Statement statement = createStatementFromConnection(connect());
        return sendGetRequest(GET_ALL_DATA, statement, "type", id);
    }

    @SneakyThrows
    public String getLastId(int id) {
        Statement statement = createStatementFromConnection(connect());
        return sendGetRequest(GET_ALL_DATA, statement, "lastid", id);
    }
    @SneakyThrows
    public ArrayList<String> getListOfTimeTable(int id) {
        ArrayList<String> listOfTimeTable = new ArrayList<>();
        Statement statement = createStatementFromConnection(connect());
        ResultSet results = statement.executeQuery(String.format(GET_TIME_TABLE_DATA, id));
        while (results.next()) {
            listOfTimeTable.add(results.getString("time"));
        }
        statement.close();
        statement.getConnection().commit();
        statement.getConnection().close();
        return listOfTimeTable;
    }

    @SneakyThrows
    public ArrayList<String> getListOfAll() {
        ArrayList<String> listOfAll = new ArrayList<>();
        Statement statement = createStatementFromConnection(connect());
        ResultSet results = statement.executeQuery(String.format(GET_ALL_DATA));
        while (results.next()) {
            listOfAll.add("/" + results.getString("id") + " " + results.getString("firstname") + " " + results.getString("lastname") + " " + results.getString("role"));
        }
        statement.close();
        statement.getConnection().commit();
        statement.getConnection().close();
        return listOfAll;
    }

    @SneakyThrows
    public ArrayList<Integer> getListOfId() {
        ArrayList<Integer> listOfId = new ArrayList<>();
        Statement statement = createStatementFromConnection(connect());
        ResultSet results = statement.executeQuery(String.format(GET_ALL_DATA));
        while (results.next()) {
            listOfId.add(Integer.parseInt(results.getString("id")));
        }
        statement.close();
        statement.getConnection().commit();
        statement.getConnection().close();
        return listOfId;
    }


}