import javax.swing.*;
import java.sql.*;

public class UserLogin {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName(
                "com.mysql.cj.jdbc.Driver");
        Connection con =
                DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/jdbc",
                        "root",
                        "12345");
        String user= JOptionPane.showInputDialog("Enter UserName :");
        String password=JOptionPane.showInputDialog("Enter Password :");
        Statement statement = con.createStatement();
        ResultSet set =
                statement.
                        executeQuery("SELECT * FROM user" +
                                " WHERE username='"+user+
                                "' AND password='"+password+"'");
        if (set.next()){
            System.out.println("Correct");
        }else{
            System.out.println("Try Again");
        }
        // SELECT, (executeQuery)
        // CREATE, UPDATE, DELETE, (executeUpdate)

    }
}