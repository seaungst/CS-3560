/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderdemo;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author alexv
 */
public class ConfirmOrderController implements Initializable {
    public TableView<ModelTable> cart;
    public TableColumn<ModelTable, String> citem;
    public TableColumn<ModelTable, String> cprice;
    public Label sstotal,stax,stotal,address;
    public double subtotal;
    public void init(ObservableList<ModelTable> items, UserInfo temp){
        System.out.println("hello");
        citem.setCellValueFactory(new PropertyValueFactory<>("name"));
        cprice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        cart.setItems(items);
        items.forEach(item -> {
            subtotal += item.getUnit_price();
        });
        sstotal.setText(String.format("$%.2f",subtotal));
        stax.setText(String.format("$%.2f",subtotal*2.5/100));
        stotal.setText(String.format("$%.2f",subtotal + subtotal * 2.5/100));
        address.setText(temp.getAddress());
        subtotal = 0;
    };
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
