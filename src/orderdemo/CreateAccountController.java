/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderdemo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alexv
 */
public class CreateAccountController implements Initializable {
    public TextField fname;
    public TextField lname;
    public TextField address;
    public TextField phone;
    public void submit(ActionEvent actionEvent) throws SQLException,IOException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String sql = "INSERT INTO customers VALUES('',"+fname.getText()+"','"+lname.getText()+"','" + address.getText() + "','" + phone.getText() + "')";
        System.out.println(sql);
        Statement statement=connection.createStatement();
        statement.executeUpdate(sql);
        changeWelcome(actionEvent);
    }
    public void changeWelcome(ActionEvent event) throws IOException {
	Parent createAccountParent = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
	Scene createAccountScene = new Scene(createAccountParent);

	// This line gets the Stage information
	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	stage.setScene(createAccountScene);
	stage.show();
    }
    public void changeLogin(ActionEvent event) throws IOException {
	Parent createAccountParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
	Scene createAccountScene = new Scene(createAccountParent);

	// This line gets the Stage information
	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	stage.setScene(createAccountScene);
	stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
