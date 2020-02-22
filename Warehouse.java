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
        return this.clients.getClient(cID);
    }
    public Supplier getSupplier(int sID){
        return this.suppliers.contains(sID);
    }
    public Iterator getOrders(){
        return this.orders.getOrders();
    }
    public void getTransactions(int cid){//part 9
        Client target = this.clients.contains(cid);
        if(target!=null){
            Iterator it = target.getTransactions();
            while(it.hasNext()){
                System.out.println(it.next());
            }

        }
    }
    public void getClientsWithBalance(){//part 9
        this.clients.withBalance();
    }
    public void getSuppliersAndProducts(){
        Iterator it = this.suppliers.getSuppliers();
        while(it.hasNext()){
            Supplier s = (Supplier)it.next();
            System.out.println(s.getName());
            Iterator it2 = s.getItems();
            while(it2.hasNext()){
                Map.Entry<Product,Integer> temp = (Map.Entry<Product,Integer>)it2.next();
                System.out.println("\t"+((Product)temp.getKey()).getName()+"|"+temp.getValue());
            }

        }
    }
    public void getAllProducts(){
        Iterator it = this.products.getProducts();
        while(it.hasNext()){
            Product temp = (Product) it.next();
            System.out.println(temp); 
        }
        
    }
    public void addClient(String name){
        this.clients.insert(new Client(name));
    }
    public void addSupplier(String name){
        this.suppliers.insert(new Supplier(name));
    }
    public void addProduct(String name,int count,double price,int s){
        Supplier sup=suppliers.contains(s);
        this.products.insert(new Product(name,count,price,sup));
        suppliers.contains(s).addItem(new Product(name,count,price,sup));
    }
    public void adjustProduct(int pId,int count){
        if(this.products.contains(pId)){
            this.products.contains(pID).adjustProduct(count);
            break;
        }
        System.out.println("failed");
    }
    public boolean processOrder(int cid){
        Client target=this.clients.contains(cid);
        if(target!=null){
            return target.processOrder();
        }
        return false;
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