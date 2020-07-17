/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderdemo;

/**
 *
 * @author alexv
 */
public class ModelTable {
    String name;
    double unit_price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public ModelTable(String name, double unit_price) {
        this.name = name;
        this.unit_price = unit_price;
    }
   
}
