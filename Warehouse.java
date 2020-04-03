//package project1;
import java.util.*;
import java.io.*;
public class Warehouse implements Serializable{
    private static final long serialVersionUID = 1L;
    private static Warehouse warehouse;
    private SupplierDirectory suppliers;
    private ClientDirectory clients;
    private ProductDirectory products;
    
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
    public Product getProduct(int pid){
        return this.products.contains(pid);
    }
    public Client getClient(int cID){
        return this.clients.getClient(cID);
    }
    public void getClientData(int cID){
        System.out.println(this.clients.contains(cID));
    }
    public Supplier getSupplier(int sID){
        return this.suppliers.contains(sID);
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
        Product tempP = new Product(name,count,price,sup);
        this.products.insert(tempP);
        suppliers.contains(s).addItem(tempP);
    }
    public void adjustProduct(int pId,int count){
        if(this.products.contains(pId)!=null){
            this.products.contains(pId).adjustCount(count);
        }else{
            System.out.println("failed");
        }
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
    public boolean editCart(int cid,int item,int count){
        Client tempC=clients.contains(cid);
        if(tempC!=null){
            tempC.cartAdjust(item, count);
        }
        return false;
    }
    public void displayCart(int cid){
        Client tempC=clients.contains(cid);
        if(tempC!=null){
            tempC.cart();
        }
    }
    public boolean pay(int cid, double amount){
        Client tempC =  warehouse.clients.contains(cid);
        if(tempC!=null){
            tempC.adjustBalance(amount);
            System.out.println(tempC.toString());
            return true;
        }
        return false;
    }
    public boolean displayAtribs(String choice, int id){
        switch(choice){
            case "c":
                Client tempC= warehouse.clients.contains(id);
                if(tempC!=null){
                    tempC.getAtribs();
                    return true;
                }
            break;
            case "s":
                Supplier tempS = warehouse.suppliers.contains(id);
                if(tempS!=null){
                    tempS.getAtribs();
                    return true;
                }
            break;
            case "p":
                Product tempP=warehouse.products.contains(id);
                if(tempP!=null){
                    tempP.getAtribs();
                    return true;
                }
            break;
        }
        return false;
    }
    public void editClient(int cid,int atrib,String val){
        Client tempC=warehouse.clients.contains(cid);
        if(tempC!=null){
            tempC.change(atrib,val);
        }
    }
    public void editSupplier(int sid,int atrib,String val){
        Supplier tempS = warehouse.suppliers.contains(sid);
        if(tempS!=null){
            tempS.change(atrib,val);
        }
    }
    public void editProduct(int pid,int atrib,String val){
        Product tempP=warehouse.products.contains(pid);
        if(tempP!=null){
            tempP.change(atrib,val);
        }
    }
    public int waiting(int pid){
        int x=0;
        Iterator it = warehouse.clients.getClients();
        while(it.hasNext()){
            Client tempC=(Client)it.next();
            x+=tempC.waiting(pid);
        }
        return x;
    }
    public boolean checkWaitList(int pid,int count){
        Iterator it = warehouse.clients.getClients();
        while(it.hasNext()){
            Client tempC=(Client)it.next();
            if(tempC.waiting(pid)<=count){return true;}
        }
        return false;
    }
    public void waitlist(int cid){
        Client tempC= warehouse.clients.contains(cid);
        if(tempC!=null){
            Iterator it = tempC.getWaitList();
            while(it.hasNext()){
                System.out.println(it);
            }
        }
    }
    public void recieve(int id, int count){
        Iterator it = warehouse.clients.getClients();
        while(it.hasNext()){
            Client tempC=(Client) it.next();
            int num = tempC.waiting(id);
            if(num!=0){
                count-=num;
                tempC.waitAdjust(id, num);
                warehouse.adjustProduct(id, count);
                tempC.processOrder();
            }else{
                warehouse.adjustProduct(id, count);
            }

        }

    }
    public boolean existsP(int pid){
        if(this.products.contains(pid)!=null){
            return true;
        }
        return false;
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