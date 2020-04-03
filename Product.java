//package project1;
import java.io.*;
import java.util.*;

public class Product implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private String description;
    private Supplier supplier;
    private int ID;
    private int stockCount;
    private double price;
    private String atribs = "1. Name\n2. Description\n3. Stock Count\n4. Price";

    public Product(String name,int count,double price){
        this.name=name;
        this.stockCount=count;
        this.price=price;
        this.ID=IdServer.instance().getpid();
    }
    public Product(String name,int count,double price,Supplier s){
        this.name=name; 
        this.stockCount=count;
        this.price=price;
        this.supplier=s;
        this.ID=IdServer.instance().getpid();
    }
    public Product(String name,int count,double price,int sid){
        this.name=name;
        this.stockCount=count;
        this.price=price;
        this.ID=IdServer.instance().getpid();
    }
    // public Product(String name, String description, int id, int sCount, double price,int supplierID){
    //     this.name=name;
    //     this.description=description;
    //     this.ID=id;
    //     this.stockCount=sCount;
    //     this.price=price;
    //     this.supplier=supplierID;
    // }

    public String getName(){return this.name;}
    public String getDescription(){return this.description;}
    public int getID(){return this.ID;}
    public int getStockCount(){return this.stockCount;}
    public double getPrice(){return this.price;}
    public Supplier getSupplier(){return this.supplier;}

    public void setName(String name){
        this.name=name;
    }
    public void setDesc(String desc){
        this.description=desc;
    }
    public void setStockCount(int count){
        this.stockCount=count;
    }
    public void adjustCount(int adj){
        this.stockCount+=adj;
    }
    public void setPrice(Double price){
        this.price=price;
    }
    public void setSupplier(Supplier supplierID){//fix this
        this.supplier=supplierID;
    }
    public void getAtribs(){
        System.out.println(atribs);
    }
    public void change(int index,String val){
        switch(index){
            case 1:
                setName(val);
            break;
            case 2:
                setDesc(val);
            break;
            case 3:
                setStockCount(Integer.valueOf(val));
            break;
            case 4:
                setPrice(Double.valueOf(val));
            break;
        }
    }
    public String toString(){
        return this.name+"|"+this.description+"|ID:"+this.ID+"|StockCount:"+this.stockCount+"|Price:$"+this.price+"|"+this.supplier.getName();
    }

}