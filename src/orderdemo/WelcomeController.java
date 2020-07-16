/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderdemo;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;

/**
 * FXML Controller class
 *
 * @author alexv
 */
public class WelcomeController implements Initializable {
    
    @FXML
    public Button closeButton;
    
    public void changeCreateAccount(ActionEvent event) throws IOException {
	Parent createAccountParent = FXMLLoader.load(getClass().getResource("CreateAccount.fxml"));
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
    
    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
    Stage stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
