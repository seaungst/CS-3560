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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alexv
 */
public class ConfirmOrderController implements Initializable {
    public UserInfo user;
    public TableView<OrderedFood> cart;
    public TableColumn<ModelTable, String> citem;
    public TableColumn<ModelTable, String> cprice;
    public TableColumn<ModelTable, String> cquantity;
    public Label sstotal,stax,stotal,address;
    public double subtotal,totalAmount;
    public ObservableList<OrderedFood> oblist;
    public String paymethod;
    public RadioButton radio1,radio2;
    
    public void init(ObservableList<OrderedFood> items, UserInfo temp,double temp1){
	user = temp;
	oblist = items;
        citem.setCellValueFactory(new PropertyValueFactory<>("name"));
        cprice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
	cquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        cart.setItems(items);
	System.out.println("suptotal "+temp1);
	subtotal = temp1;
        sstotal.setText(String.format("$%.2f",subtotal));
        stax.setText(String.format("$%.2f",subtotal*7.5/100));
        stotal.setText(String.format("$%.2f",subtotal + subtotal * 7.5/100));
	totalAmount = subtotal + subtotal * 7.5/100;
        address.setText(temp.getAddress());
    };
    public void getRadio(){
        if(radio1.isSelected()){
		paymethod = "1";
	}else{
		paymethod = "2";
	}
    }
    public void placeOrder(ActionEvent event) throws IOException{
	getRadio();
	try {
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();
		String sql1 = "INSERT INTO orders(customer_id,date) VALUES(" + String.valueOf(user.getId()) + ",'2019-12-01');";
		String sql2 = "INSERT INTO payments(order_id,amount,payment_method) VALUES(LAST_INSERT_ID(),"+ String.valueOf(totalAmount)+","+ paymethod+");";
		connection.createStatement().executeUpdate(sql1);
		
		System.out.println("here");
		oblist.forEach(item->{
			System.out.println("start loop");
			String sql3 = "INSERT INTO order_items(order_id,food_id,quantity,unit_price)"
				+" VALUES(LAST_INSERT_ID(),"+ item.getFoodid() +","+ item.getQuantity() +","+ item.getUnit_price()+");";
			System.out.println(sql3);
			try {
				connection.createStatement().executeUpdate(sql3);
			} catch (SQLException ex) {
				Logger.getLogger(ConfirmOrderController.class.getName()).log(Level.SEVERE, null, ex);
			}
			
		});
		connection.createStatement().executeUpdate(sql2);
	} catch (SQLException ex) {
		Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
	}
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
        // TODO
    }    
    
}
