import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.*;

public class CustomerFormController {
    public TextField cusId;
    public TextField cusSlry;
    public TextField cusAdres;
    public TextField cusName;

    public void saveCustomerButtonOnAction(ActionEvent actionEvent)  {
        int id= Integer.parseInt(cusId.getText());
        String name=cusName.getText();
        String address=cusAdres.getText();
        double salary= Double.parseDouble(cusSlry.getText());

        try{
            // load the driver class
            Class.forName(
                    "com.mysql.cj.jdbc.Driver");
            // create the Connection
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/jdbc",
                    "root","12345"
            );
            // create the query
            String sql="INSERT INTO customer VALUES(?,?,?,?)";
            // create the statement
            PreparedStatement statement =
                    con.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, address);
            statement.setDouble(4, salary);
            int isSaved = statement.executeUpdate();
            if(isSaved>0){
                new Alert(Alert.AlertType.INFORMATION,
                        "Customer Saved").show();
                clearField();
            }else{
                new Alert(Alert.AlertType.WARNING,
                        "Try Again").show();
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public void gerCustomerOnAction(ActionEvent actionEvent) {
        int customerId=
                Integer.parseInt(cusId.getText());
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","12345");
            String sql ="SELECT * FROM customer WHERE id=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1,customerId);
            ResultSet set = stm.executeQuery();
            if(set.next()){
              /*  System.out.println(set.getInt("id"));
                System.out.println(set.getString("address"));
                System.out.println(set.getInt(1));
                System.out.println(set.getString(2));*/
                cusName.setText(set.getString(2));
                cusAdres.setText(set.getString(3));
                cusSlry.setText(String.valueOf(set.getDouble(4)));
            }else{
                new Alert(Alert.AlertType.WARNING,"Customer Not Found").show();

            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public void clearTextFeildOnActio(ActionEvent actionEvent) {
       clearField();
    }
    public void clearField(){
        cusId.setText("");
        cusName.setText("");
        cusAdres.setText("");
        cusSlry.setText("");
    }

    public void customerUpdateOnAction(ActionEvent actionEvent) {
        int id = Integer.parseInt(cusId.getText());
        String name= cusName.getText();
        String address=cusAdres.getText();
        double salary= Double.parseDouble(cusSlry.getText());

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","12345");
            String sql = "UPDATE customer SET name=?, address=?, salary=? WHERE id=?";
            PreparedStatement stm=con.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, address);
            stm.setDouble(3, salary);
            stm.setInt(4, id);
            int isSaved = stm.executeUpdate();
            if(isSaved>0){
                new Alert(Alert.AlertType.INFORMATION,
                        "Customer Updated").show();
                clearField();
            }else{
                new Alert(Alert.AlertType.WARNING,
                        "Try Again").show();
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,
                    e.getMessage()).show();
        }
    }

    public void customerDeleteOnAction(ActionEvent actionEvent) {
    }
}
