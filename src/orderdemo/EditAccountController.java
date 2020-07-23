/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderdemo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
 * @author selenabean
 */
public class EditAccountController implements Initializable {
    public UserInfo user;
    public TextField fname,lname,address,phone;
    public Label input;
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
    public void getUser(UserInfo temp){
        user = temp;

    }
    public static boolean phone(String phoneNo)
    {
        //Check NULL
        if (phoneNo == null) return false;
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")) return true;
        return false;
    }
    public void update(ActionEvent actionEvent)throws IOException{
	boolean valid = phone(phone.getText());
	if(valid){
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
		try {
			Statement statement = connection.createStatement();
			String sql = "UPDATE customers set fname='"+fname.getText()+"',lname='"+lname.getText()+"',"
			+ "address='"+address.getText()+"',phone='"+phone.getText()+"' WHERE phone='"+user.getPhone()+"'";  
			statement.executeUpdate(sql);
			user.setFname(fname.getText());
			user.setLname(lname.getText());
			user.setAddress(address.getText());
			user.setPhone(phone.getText());
			changeManageAccount(actionEvent);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}else{
		System.out.print("input is invalid");
		input.setVisible(true);
		input.setText("input is invalid");

	}
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        input.setVisible(false);
    }    
    
}
