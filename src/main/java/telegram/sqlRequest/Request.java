package telegram.sqlRequest;

public class Request {
    public static final String INSERT_INTO_USERS = "insert into users (Id, Firstname, Lastname, Role) values(' %s','%s', '%s', '%s');";
}
