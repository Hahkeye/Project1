package project1;
import java.util.*;
import java.io.*;
public class Warehouse implements Serializable{
    private static final long serialVersionUID = 1L;
    private static Warehouse warehouse;
    private SupplierDirectory suppliers;
    private ClientDirectory clients;
    private ProductDirectory products;
    //private OrderDirectory orders;
    //private idServer ids;
    
    private Warehouse(){
        suppliers=SupplierDirectory.instance();
        clients=ClientDirectory.instance();
        products=ProductDirectory.instance();
        //orders=OrderDirectory.instance();
        //ids=idServer.instnace();       
    }

    public static Warehouse instance(){
        if(warehouse==null){
            return(warehouse=new Warehouse());
        }else{
            return warehouse;
        }
    }
    public Iterator getClients(){
        return this.clients.getClients();
    }
    public Iterator getSuppliers(){
        return this.suppliers.getSuppliers();
    }
    public Iterator getProducts(){
        return this.products.getProducts();
    }
    public Client getClient(int cID){
        return this.clients.getClient(cId);
    }
    public Iterator getOrders(){
        return this.orders.getOrders();
    }
    public void addClient(String name){
        this.clients.insert(new Client(name));
    }
    public void addSupplier(String name){
        this.suppliers.insert(new Supplier(name));
    }
    public void addProduct(String name,int count,double price){
        this.products.insert(new Product(name,count,price));
    }
    public void adjustProduct(int pId,int count){
        if(this.products.contains(pId)){
            this.products.contains(pID).adjustProduct(count);
            break;
        }
        System.out.println("failed");
    }
    public boolean processOrder(int cid){
        Client temp = clients.contains(cid);
        Double deduc = 0.0;
        if(temp!=null){
            Iterator it=temp.getCart();
            while(it.hasNext()){
                AbstractMap.SimpleEntry<Product,Integer> ent = (AbstractMap.SimpleEntry<Product,Integer>)it.next();
                if(ent.getKey().getStockCount()>=ent.getValue()){
                    ent.getKey().adjustCount(-ent.getValue());
                    deduc-=ent.getKey().getPrice();
                    it.remove();
                }else{
                    System.out.println("Not enough product to satisfiy order. adding to waitlist");
                }
            }
        }else{
            return false;
        }
        temp.adjustBalance(deduc);
        return true;
    }
    public boolean remove(String s,int id){
        boolean result=false;
        switch(s){
            case "c":
            result= warehouse.clients.remove(id);
            break;
            case "s":
            result=  warehouse.suppliers.remove(id);
            break;
            case "p":
            result= warehouse.products.remove(id);
            break;
        }
        return result;
    }
    public boolean addToOrder(int cid,int pid,int quan){
        if(clients.contains(cid)!=null){
            warehouse.clients.contains(cid).addProduct(products.contains(pid),quan);
            return true;
        }
        return false;
    }
    public Supplier sExists(int sID){
        return this.suppliers.exists(sID);
    }
    public Client cExists(int cID){
        return this.clients.exists(cID);
    }
    public Product pExists(int pID){
        return this.products.exists(pID);
    }

    public static Warehouse retrieve(){
        try {
            FileInputStream file = new FileInputStream("WarehouseData");
            ObjectInputStream input = new ObjectInputStream(file);
            input.readObject();
            IdServer.retrieve(input);
            return warehouse;
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static  boolean save() {
        try {
            FileOutputStream file = new FileOutputStream("WarehouseData");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(warehouse);
            output.writeObject(IdServer.instance());
            return true;
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    private void writeObject(java.io.ObjectOutputStream output) {
        try {
          output.defaultWriteObject();
          output.writeObject(warehouse);
        } catch(IOException e) {
          System.out.println(e);
        }
    }
    private void readObject(java.io.ObjectInputStream input) {
        try {
            input.defaultReadObject();
            if (warehouse == null) {
                warehouse = (Warehouse) input.readObject();
            } else {
                input.readObject();
            }
        } catch(IOException e) {
          e.printStackTrace();
        } catch(Exception e) {
          e.printStackTrace();
        }
    }
}