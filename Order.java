package Project1;

import java.util.*;

public class Order{
    private List<Product> productList = new LinkedList<Product>();
    private Client orderOwner;
    private double orderTotal;

    public Order(Client owner,double price){
        this.orderOwner=owner;
        this.orderTotal=price;
    }

    public void add(Product p){
        this.productList.add(p);
    }
    public void remove(Product p){
        this.productList.remove(p);
    }
    public boolean isIn(Product p){
        return this.productList.contains(p);
    }

    public Iterator getIterator(){
        return this.productList.iterator();
    }

    
}