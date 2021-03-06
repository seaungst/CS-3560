/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderdemo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class MenuController implements Initializable {
    public UserInfo user;
    public TableView<ModelTable> menu;
    public TableView<ModelTable> cart;
    public TableColumn<ModelTable, String> citem;
    public TableColumn<ModelTable, String> cprice;
    public TableColumn<ModelTable, String> item;
    public TableColumn<ModelTable, String> price;
    public Label sstotal,stax,stotal;
    public double subtotal;
    public ObservableList<OrderedFood> oblist2 = FXCollections.observableArrayList();
    public ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
    public ObservableList<ModelTable> oblist1 = FXCollections.observableArrayList();
    public void changeConfirmOrder(ActionEvent event) throws IOException {
        update();
	oblist1.forEach(item -> {
            subtotal += item.getUnit_price();
        });
	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ConfirmOrder.fxml"));
        Parent createAccountParent = loader.load();
	Scene createAccountScene = new Scene(createAccountParent);

        ConfirmOrderController controller = loader.getController();
        controller.init(oblist2, user,subtotal);
	// This line gets the Stage information
	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	stage.setScene(createAccountScene);
	stage.show();
    }
    public void Add(ActionEvent actionEvent){
        citem.setCellValueFactory(new PropertyValueFactory<>("name"));
        cprice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        ObservableList<ModelTable> selectedItem;
        selectedItem = menu.getSelectionModel().getSelectedItems();
        selectedItem.forEach(oblist1::add);
        cart.setItems(oblist1);
	cost();
    }
    public void Del(ActionEvent actionEvent){
        citem.setCellValueFactory(new PropertyValueFactory<>("name"));
        cprice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        ObservableList<ModelTable> selectedItem;
        selectedItem = cart.getSelectionModel().getSelectedItems();
        selectedItem.forEach(oblist1::remove);
        cart.setItems(oblist1);
        cost();
    }
    public void getUser(UserInfo temp){
        user = temp;
    }
    public void update(){

        HashMap<String, Integer> freqMap = new HashMap<String, Integer>(); 
        for (int i=0;i<oblist1.size();i++) { 
	    String temp = String.valueOf(oblist1.get(i).foodid) +"@"+ oblist1.get(i).name +"@"+ String.valueOf(oblist1.get(i).unit_price);
            if (freqMap.containsKey(temp)) { 
                freqMap.put(temp, freqMap.get(temp) + 1); 
            } 
            else { 
                freqMap.put(temp, 1); 
            } 
        }  
	for (String i : freqMap.keySet()) {
		String[] splitStr = i.split("@");
		oblist2.add(new OrderedFood(splitStr[0],splitStr[1],splitStr[2],freqMap.get(i)));
	}
    }
    public void cost(){
        oblist1.forEach(item -> {
            subtotal += item.getUnit_price();
        });
        sstotal.setText(String.format("$%.2f",subtotal));
        stax.setText(String.format("$%.2f",subtotal*7.25/100));
        stotal.setText(String.format("$%.2f",subtotal + subtotal * 7.25/100));
        subtotal = 0;
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
        try {
            // TODO
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            ResultSet result = connection.createStatement().executeQuery("SELECT * FROM foods");
            while(result.next()){
                oblist.add(new ModelTable(result.getString(2),result.getDouble(3),result.getString(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        item.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        menu.setItems(oblist);
    }    
    
}
