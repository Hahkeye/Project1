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
    public AbstractMap.SimpleEntry<Product,Integer> contains(int p){
        Iterator it = cart.iterator();
        while(it.hasNext()){
            AbstractMap.SimpleEntry<Product,Integer> temp= (AbstractMap.SimpleEntry<Product,Integer>) it.next();
            if((temp.getKey().getID())==p){
                return temp;
            }
        }
        return null;
    }
    public AbstractMap.SimpleEntry<Product,Integer> contains(Product p){
        Iterator it = cart.iterator();
        while(it.hasNext()){
            AbstractMap.SimpleEntry<Product,Integer> temp= (AbstractMap.SimpleEntry<Product,Integer>) it.next();
            if(temp.getKey().equals(p)){
                return temp;
            }
        }
        return null;
    }
    public void addProduct(Product p,int quaity){
        AbstractMap.SimpleEntry<Product,Integer> temp = contains(p);
        if(temp!=null){
            temp.setValue(temp.getValue()+quaity);
            System.out.println("Product already was in cart adding to that ");
        }else{
            this.cart.add(new AbstractMap.SimpleEntry<>(p,quaity));
            System.out.println("Product was not already in cart adding to cart");
            System.out.println(this.cart);
        }
        
    }
    public void addProduct(int pid,int quaity){
        AbstractMap.SimpleEntry<Product,Integer> temp = contains(pid);
        if(temp!=null){
            temp.setValue(temp.getValue()+quaity);
        }else{
            cart.add(new AbstractMap.SimpleEntry<>(p,quaity));
        }
        
    }
    public double adjustBalance(double adjustNumber){
        return(this.balance+=adjustNumber);
    }

    public String toString(){
        return this.ID+"|"+this.name+"|$"+this.balance+"|"+this.cart.toString();
    }
}