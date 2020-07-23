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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alexv
 */
public class YourOrdersController implements Initializable {
    public UserInfo user;
    public TableView<OrderTable> orderview;
    public TableColumn<OrderTable, String> number;
    public TableColumn<OrderTable, String> date;
    public TableColumn<OrderTable, String> status;
    public TableColumn<OrderTable, String> method;
    public TableColumn<OrderTable, String> amount;
    public String customerid,orderid;
    public ObservableList<OrderTable> oblist = FXCollections.observableArrayList();
    @FXML
    public void changeManageAccount(ActionEvent event) throws IOException {
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(getClass().getResource("ManageAccount.fxml"));
	    Parent createAccountParent = loader.load();
	    Scene createAccountScene = new Scene(createAccountParent);

	    ManageAccountController controller = loader.getController();
	    controller.init(user);

	    // This line gets the Stage information
	    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setScene(createAccountScene);
	    stage.show();
    }
    @FXML
    public void changeOrderDetails(ActionEvent event) throws IOException {
	    getOrderid();
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(getClass().getResource("OrderDetails.fxml"));
	    Parent createAccountParent = loader.load();
	    Scene createAccountScene = new Scene(createAccountParent);

	    OrderDetailsController controller = loader.getController();
	    controller.init(orderid,user);
	    // This line gets the Stage information
	    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setScene(createAccountScene);
	    stage.show();
    }
    public void getOrderid(){
	TablePosition pos = orderview.getSelectionModel().getSelectedCells().get(0);
	int row = pos.getRow();

	// Item here is the table view type:
	OrderTable item = orderview.getItems().get(row);

	TableColumn col = pos.getTableColumn();
	// this gives the value in the selected cell:
	orderid = String.valueOf(col.getCellObservableValue(item).getValue());
	System.out.println(orderid);
    }
    public void init(String temp,UserInfo temp1){
	user = temp1;
	try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            String sql = "SELECT orders.order_id,date,order_statuses.name,payment_methods.type,amount" 
            +" FROM orders" 
            +" JOIN payments ON orders.order_id = payments.order_id"
            +" JOIN order_statuses ON orders.status = order_statuses.order_status_id"
            +" JOIN payment_methods ON payment_methods.payment_method_id = payments.payment_method"
            +" WHERE customer_id = '" + temp + "';";
	    System.out.println(customerid);
	    System.out.println(sql);
            ResultSet result = connection.createStatement().executeQuery(sql);
            while(result.next()){
                oblist.add(new OrderTable(result.getInt(1),result.getString(2),result.getString(3),result.getString(4),result.getDouble(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
	System.out.println("success");
        number.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        date.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        status.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
        method.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        amount.setCellValueFactory(new PropertyValueFactory<>("paymentAmount"));
        orderview.setItems(oblist);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

}
