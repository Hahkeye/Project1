//package project1;

import java.util.*;
import java.io.*;
//import java.lang.invoke.VarHandle.AccessMode;

public class Order implements Serializable{
    private static final long serialVersionUID = 1L;
    private List<Map.Entry<Product, Integer>> manifest;
    private Client orderOwner;
    private double orderTotal;

    public Order(Client owner,double price){
        this.orderOwner=owner;
        this.orderTotal=price;
        manifest= new LinkedList<>();
    }

    public void add(Product p,int quan){
        this.manifest.add(new AbstractMap.SimpleEntry<>(p,quan));
    }
    public void remove(Product p){
        Iterator it = manifest.iterator();  
        while(it.hasNext()){
            AbstractMap.SimpleEntry<Product,Integer> temp= (AbstractMap.SimpleEntry<Product,Integer>) it.next();
            if(temp.getKey().getID()==p.getID()){
                manifest.remove(temp);
            }
        }
    }
    public void remove(Product p,int count){
        Iterator it = manifest.iterator();  
        while(it.hasNext()){
            AbstractMap.SimpleEntry<Product,Integer> temp= (AbstractMap.SimpleEntry<Product,Integer>) it.next();
            if(temp.getKey().getID()==p.getID()){
                temp.setValue(temp.getValue()-count);
            }
        }
    }
    public boolean contains(Product p){
        Iterator it = manifest.iterator();
        while(it.hasNext()){
            AbstractMap.SimpleEntry<Product,Integer> temp= (AbstractMap.SimpleEntry<Product,Integer>) it.next();
            if(temp.getKey().equals(p)){
                return true;
            }
        }
        return false;
    }

    public Iterator getIterator(){
        return this.manifest.iterator();
    }
    public String toString(){
        return this.orderOwner.getID()+"|"+this.orderTotal+"|"+this.manifest;
    }
}