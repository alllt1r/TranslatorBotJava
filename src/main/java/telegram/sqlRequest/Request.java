package telegram.sqlRequest;

public class Request {
    public static final String INSERT_INTO_USERS = "insert into users (Id, Firstname, Lastname, Role) values('%s','%s', '%s', '%s')";
    public static final String UPDATE_ROLE = "UPDATE users SET Role = '%s' WHERE id = %s";
    public static final String SET_TYPE_OF_MSG = "UPDATE users SET type = '%s' WHERE id = %s";
    public static final String SET_LAST_ID = "UPDATE users SET lastid = '%s' WHERE id = %s";
    public static final String DELETE_USER = "DELETE FROM users WHERE id = %s";
    public static final String GET_ALL_DATA = "SELECT * FROM users";
}
