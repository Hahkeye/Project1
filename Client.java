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
    private List transactionList;
    private List<Map.Entry<Product,Integer>> waitlist;


    public Client(String name){
        this.name=name;
        this.ID=(IdServer.instance()).getcid();
        this.balance=0.0;
        this.cart = new LinkedList<>();
        this.transactionList=new LinkedList();
    }

    public int getID(){return this.ID;}
    public String getName(){return this.name;}
    public String getAddress(){return this.address;}
    public double getBalance(){return this.balance;}
    public Iterator getCart(){return this.cart.iterator();}
    public Iterator getTransactions(){return this.transactionList.iterator();}
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
    public AbstractMap.SimpleEntry<Product,Integer> contains(Product p){//depreciated
        Iterator it = cart.iterator();
        while(it.hasNext()){
            AbstractMap.SimpleEntry<Product,Integer> temp= (AbstractMap.SimpleEntry<Product,Integer>) it.next();
            if(temp.getKey().equals(p)){
                return temp;
            }
        }
        return null;
    }
    public boolean processOrder(){//fix to return order
        if(cart.size()!=0){
            Double deduc = 0.0;
            Iterator it=getCart();
            while(it.hasNext()){
                AbstractMap.SimpleEntry<Product,Integer> ent = (AbstractMap.SimpleEntry<Product,Integer>)it.next();
                if(ent.getKey()==null){return false;}
                if(ent.getKey().getStockCount()>=ent.getValue()){
                    ent.getKey().adjustCount(-ent.getValue());
                    deduc-=ent.getKey().getPrice();
                    it.remove();
                }else{
                    System.out.println("Not enough product to satisfiy order. adding to waitlist");
                    this.waitlist.add(new AbstractMap.SimpleEntry<>(ent.getKey(),ent.getValue()));
                }
            }
            transactionList.add("Order for: "+deduc);
            adjustBalance(deduc);
            return true;
        }
        return false;
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