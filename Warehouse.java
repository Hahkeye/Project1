package project1;
import java.util.*;
import java.io.*;
public class Warehouse implements Serializable{
    private static final long serialVersionUID = 1L;
    private static Warehouse warehouse;
    private SupplierDirectory suppliers;
    private ClientDirectory clients;
    private ProductDirectory products;
    private OrderDirectory orders;
    
    private Warehouse(){
        suppliers=SupplierDirectory.instance();
        clients=ClientDirectory.instance();
        products=ProductDirectory.instance();
        
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
    public void addClient(String name,int ID){
        this.clients.insert(new Client(name,ID));
    }
    public void addSupplier(String name,int ID){
        this.suppliers.insert(new Supplier(name,ID));
    }
    public void addProduct(String name,int ID){
        this.products.insert(new Product(name,ID));
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