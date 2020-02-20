package project1;
import java.io.*;
import java.security.KeyStore.Entry;
import java.util.*;

public class Client implements Serializable{
    private static final long serialVersionUID = 1L;
    private int ID;
    private String address;
    private String name;
    private double balance;
    private List<Map.Entry<Product,Integer>> cart;

    public Client(String name,int id){
        this.name=name;
        this.ID=id;
        this.balance=0.0;
        this.cart = new LinkedList<>();
    }

    public int getID(){return this.ID;}
    public String getName(){return this.name;}
    public String getAddress(){return this.address;}
    public double getBalance(){return this.balance;}
    public void addProduct(Product p,int quaity){
        this.cart.add(new AbstractMap.SimpleEntry<>(p,quaity));
    }
    public double adjustBalance(double adjustNumber){
        return(this.balance+=adjustNumber);
    }

    public String toString(){
        return this.ID+"|"+this.name+"|$"+this.balance+"|"+this.cart.toString();
    }
}