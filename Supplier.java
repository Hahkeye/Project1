package project1;
import java.io.*;
import java.util.*;

public class Supplier implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private int ID;
    private List<AbstractMap.Entry<Product,Double>> items;
    
    public Supplier(String name){
        this.name=name;
        this.ID=IdServer.instance().getsid();
        this.items =new LinkedList<>();
    }
    public String getName(){return this.name;}
    public int getID(){return this.ID;}
    public Iterator getItems(){return this.items.iterator();}

    public void setName(String name){
        this.name=name;
    }
    public void setID(int id){
        this.ID=id;
    }
    public void addItem(Product p){
        this.items.add(new AbstractMap.SimpleEntry<>(p,p.getPrice()));
    }
    public boolean removeItem(Product p){
        Iterator it = items.iterator();
        while(it.hasNext()){
            Map.Entry<Product,Double> temp = it.next();
            Product tempP = temp.getKey();
            if(tempP.getID()==p.getID()){
                items.remove(temp);
                return true;
            }
        }
        return false;
    }
    public String toString(){
        return this.ID+"|"+this.name+"|"+this.items;
    }

}