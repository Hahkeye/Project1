import java.text.DecimalFormat;
import java.util.*;
import java.io.*;


class ShoppingCart implements Serializable {
    private Product item;
    private int quantity;
        
    ShoppingCart(Product item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }//end constructor
        
    public Product getItem(){
        return item;
    }//end getItem

    public int getQuantity(){
        return quantity;
    }//end getQuantity
        
    public void setItem(Product item){
        this.item = item;
    }//end setItem

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }//end setId
        
    public String toString(){
        return "Name: " + item.getName() + " Quantity: " + quantity;
    }//end display
