package project1;
import java.io.*;
import java.util.*;

public class Supplier implements Serializable{
    private String name;
    private int ID;
    private List items= new LinkedList();
    
    public Supplier(String name,int id){
        this.name=name;
        this.ID=id;
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
        this.items.add(p);
    }
    public void removeItem(Product p){
        this.items.remove(p);
    }
    public String toString(){
        return this.name+"|"+this.items;
    }

}