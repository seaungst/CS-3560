/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderdemo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alexv
 */
public class LoginController implements Initializable {
    public TextField loginPhone;   
    public Label isConnected;
    public UserInfo user;
    public void login(ActionEvent actionEvent) throws IOException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM customers WHERE phone = '" + loginPhone.getText() + "';";
            System.out.println(sql);
            ResultSet result = statement.executeQuery(sql);
            if (result.next()) {
                isConnected.setText("Connected");
                user = new UserInfo(result.getInt(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5));
                changeManageAccount(actionEvent);
            } else {
                isConnected.setVisible(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void changeWelcome(ActionEvent event) throws IOException {
	Parent createAccountParent = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
	Scene createAccountScene = new Scene(createAccountParent);

	// This line gets the Stage information
	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	stage.setScene(createAccountScene);
	stage.show();
    }
    public void changeManageAccount(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ManageAccount.fxml"));
        Parent createAccountParent = loader.load();
	Scene createAccountScene = new Scene(createAccountParent);

        ManageAccountController controller = loader.getController();
        controller.init(user);


	// This line gets the Stage information
	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	stage.setScene(createAccountScene);
	stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isConnected.setVisible(false);
    }    
    
}
