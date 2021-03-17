package telegram;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PostgreSQLJDBC
{



    public static void main(String[] args) {
        PostgreSQLJDBC m = new PostgreSQLJDBC();
        //m.insertData(5, "Danil", "Kazakov", "teacher");
        //m.insertData(6, "Lola", "Marlow", "student");
        //m.insertData(7, "Hit", "Liner", "student");
        //m.insertData(8, "Bob", "Ciker", "student");
        //System.out.println(getUserData.getListOfId());
        //System.out.println(getUserData.getRole(2));
        //System.out.println(getUserData.getRole(2));
        //System.out.println(getUserData.getFirstName(4));
    }

    //CURD - Create Update Read Delete
    public void deleteData(int id) {
        Connection c = null;
        Statement stmt = null;
        String role_new = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/" + System.getenv("DATABASE_NAME"),
                            System.getenv("SQLUSER"), System.getenv("SQLPASSWORD"));
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "DELETE FROM users WHERE id = " +  id;
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void insertData(int id, String firstname, String lastname, String role) {
        Connection c = null;
        Statement stmt = null;
        String role_new = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/" + System.getenv("DATABASE_NAME"),
                            System.getenv("SQLUSER"), System.getenv("SQLPASSWORD"));
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "insert into users (Id, Firstname, Lastname, Role) values('" + id + "','" + firstname + "', '" + lastname + "', '" + role + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void updateData(int id, String newRole) {
        Connection c = null;
        Statement stmt = null;
        String role_new = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/" + System.getenv("DATABASE_NAME"),
                            System.getenv("SQLUSER"), System.getenv("SQLPASSWORD"));
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "UPDATE users SET Role = '" + newRole + "' WHERE id = " + id;
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void setTypeOfMsg(int id, String type) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/" + System.getenv("DATABASE_NAME"),
                            System.getenv("SQLUSER"), System.getenv("SQLPASSWORD"));
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "UPDATE users SET type = '" + type + "' WHERE id = " + id;
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public String getRole(int id) {
        Connection c = null;
        Statement stmt = null;
        String role_new = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/" + System.getenv("DATABASE_NAME"),
                            System.getenv("SQLUSER"), System.getenv("SQLPASSWORD"));
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users;");
            while (rs.next()) {
                int id_new = rs.getInt("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String role = rs.getString("role");
                if (id_new == id) {
                    role_new = role;
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return role_new;
    }

    public String getFirstName(int id) {
        Connection c = null;
        Statement stmt = null;
        String firstname_new = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/" + System.getenv("DATABASE_NAME"),
                            System.getenv("SQLUSER"), System.getenv("SQLPASSWORD"));
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users;");
            while (rs.next()) {
                int id_new = rs.getInt("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String role = rs.getString("role");
                if (id_new == id) {
                    firstname_new = firstname;
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return firstname_new;
    }

    public ArrayList<String> getListOfAll() {
        ArrayList<String> listOfId = new ArrayList<>();
        Connection c = null;
        Statement stmt = null;
        String firstname_new = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/" + System.getenv("DATABASE_NAME"),
                            System.getenv("SQLUSER"), System.getenv("SQLPASSWORD"));
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users;");

            while (rs.next()) {
                listOfId.add("/" + rs.getString("id") + " " + rs.getString("firstname") + " " + rs.getString("lastname") + " " +  rs.getString("role"));

            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return listOfId;
    }

    public ArrayList<Integer> getListOfId() {
        ArrayList<Integer> listOfId = new ArrayList<>();
        Connection c = null;
        Statement stmt = null;
        String firstname_new = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/" + System.getenv("DATABASE_NAME"),
                            System.getenv("SQLUSER"), System.getenv("SQLPASSWORD"));
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users;");

            while (rs.next()) {
                listOfId.add(Integer.parseInt(rs.getString("id")));
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return listOfId;
    }

    public String getLastName(int id) {
        Connection c = null;
        Statement stmt = null;
        String lastname_new = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/" + System.getenv("DATABASE_NAME"),
                            System.getenv("SQLUSER"), System.getenv("SQLPASSWORD"));
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users;");
            while (rs.next()) {
                int id_new = rs.getInt("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String role = rs.getString("role");
                if (id_new == id) {
                    lastname_new = lastname;
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return lastname_new;
    }

    public String getTypeOfMsg(int id) {
        Connection c = null;
        Statement stmt = null;
        String type_new = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/" + System.getenv("DATABASE_NAME"),
                            System.getenv("SQLUSER"), System.getenv("SQLPASSWORD"));
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users;");
            while (rs.next()) {
                int id_new = rs.getInt("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String type = rs.getString("type");
                if (id_new == id) {
                    type_new = type;
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return type_new;
    }
}