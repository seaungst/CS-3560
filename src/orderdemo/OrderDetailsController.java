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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alexv
 */
public class OrderDetailsController implements Initializable {
    public UserInfo user;
    public TableView<OrderDetailsTable> orderdetails;
    public TableColumn<OrderDetailsTable, String> item;
    public TableColumn<OrderDetailsTable, String> price;
    public TableColumn<OrderDetailsTable, String> quantity;
    public ObservableList<OrderDetailsTable> oblist = FXCollections.observableArrayList();
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
    public void changeYourOrders(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("YourOrders.fxml"));
        Parent createAccountParent = loader.load();
	Scene createAccountScene = new Scene(createAccountParent);

        YourOrdersController controller = loader.getController();
        controller.init(String.valueOf(user.getId()),user);
	// This line gets the Stage information
	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	stage.setScene(createAccountScene);
	stage.show();
    }
    public void init(String temp,UserInfo temp1){
	user = temp1;
    	try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            String sql = "SELECT name, order_items.unit_price,quantity" 
            +" FROM order_items" 
            +" JOIN foods ON order_items.food_id = foods.food_id"
            +" WHERE order_items.order_id = '" + temp + "';";
	    System.out.println(sql);
            ResultSet result = connection.createStatement().executeQuery(sql);
            while(result.next()){
                oblist.add(new OrderDetailsTable(result.getString(1),result.getString(2),result.getString(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
	System.out.println("success");
        item.setCellValueFactory(new PropertyValueFactory<>("item"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        orderdetails.setItems(oblist);
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }	
}
