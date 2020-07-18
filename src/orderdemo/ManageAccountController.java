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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alexv
 */
public class ManageAccountController implements Initializable {
    public Label fname,lname,address,phone;
    public UserInfo user;
    public void init(UserInfo temp){
        user = temp;
        fname.setText(temp.getFname());
        lname.setText(temp.getLname());
        address.setText(temp.getAddress());
        phone.setText(temp.getPhone());
    }

    /**
     * Initializes the controller class.
     */
    public void changeWelcome(ActionEvent event) throws IOException {
	Parent createAccountParent = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
	Scene createAccountScene = new Scene(createAccountParent);

	// This line gets the Stage information
	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	stage.setScene(createAccountScene);
	stage.show();
    }
    public void changeMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Menu.fxml"));
        Parent createAccountParent = loader.load();
	Scene createAccountScene = new Scene(createAccountParent);

        MenuController controller = loader.getController();
        controller.getUser(user);
	// This line gets the Stage information
	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	stage.setScene(createAccountScene);
	stage.show();
    }
    public void changeAllOrderView(ActionEvent event) throws IOException {
	Parent createAccountParent = FXMLLoader.load(getClass().getResource("AllOrderView.fxml"));
	Scene createAccountScene = new Scene(createAccountParent);

	// This line gets the Stage information
	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	stage.setScene(createAccountScene);
	stage.show();
    }
    public void changeEditAccount(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("EditAccount.fxml"));
        Parent createAccountParent = loader.load();
	Scene createAccountScene = new Scene(createAccountParent);

        EditAccountController controller = loader.getController();
        controller.getUser(user);
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
