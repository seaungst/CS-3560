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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alexv
 */
public class OrderDetailController implements Initializable {

    public void changeManageAccount(ActionEvent event) throws IOException {
	Parent createAccountParent = FXMLLoader.load(getClass().getResource("ManageAccount.fxml"));
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