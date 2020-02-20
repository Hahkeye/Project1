package project1;

import java.util.*;
import java.io.*;

public class Userinterface{
    private static Userinterface ui;
    private static String menu = "\tMain Menu\n1. Add Clients \n2. Add Suppliers\n3. Add Products"+
    "\n4. Add to Shopping cart\n5. Display Clients\n6. Display Suppliers\n7. Display Products"+
    "\n8. Save data\n9. Exit\n10. populate database\n11. Process Order\n12. Remove something";
    private static Warehouse warehouse;
    private Userinterface(){
        if(tOrf("Use save data? y/n?")){
            retrieve();
        }else{
            warehouse = Warehouse.instance();
        }

    }
    public static Userinterface instance(){
        if (ui == null){
            return (ui =  new Userinterface());
        }else{
            return ui;
        }
    }
    public static String getResponse(String query){
        do{
            try {
                System.out.println(query);
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String line = reader.readLine();
                StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
                if (tokenizer.hasMoreTokens()) {
                    return tokenizer.nextToken();
                }
            } 
            catch (IOException ioe) {
                System.exit(0);
            }
        } while(true);
    }
    public static boolean tOrf(String query){
        String answer = getResponse(query);
        if(answer.charAt(0)=='y'||answer.charAt(0)=='Y'){
            return true;
        }
        return false;
    }
    public void remove(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.println("What would you like to remove?(c,p,s)");
            String choice = reader.readLine();
            System.out.println("What is the ID of this?");
            int id = Integer.valueOf(reader.readLine());
            System.out.println(warehouse.remove(choice, id));
        }catch(IOException e){
            System.out.println(e);
        }
    }
    public void processOrder(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.println("Enter Client ID:");
            int cid= Integer.valueOf(reader.readLine());
            System.out.println(warehouse.processOrder(cid));
        }catch(IOException e){
            System.out.println(e);
        }
    }
    public void addToShoppingCart(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.println("Enter Client ID: ");
            int cid = Integer.valueOf(reader.readLine());
            System.out.println("Enter Product ID: ");
            int pid = Integer.valueOf(reader.readLine());
            System.out.println("Enter desired quanity: ");
            int quanity = Integer.valueOf(reader.readLine());
            if(warehouse.addToOrder(cid,pid,quanity)){
                System.out.println("Successfully added to shopping cart.");
            }else{
                System.out.println("Failed to add to shopping cart");
            }
            //reader.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
    public void addClient(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        do{
            try{
                System.out.println("Enter Client name:");
                String name = reader.readLine();
                // System.out.println("Enter Client ID:");
                // int id = Integer.valueOf(reader.readLine());
                warehouse.addClient(name);
                if(!tOrf("Do you want to add another Client? y/n?")){
                    break;
                }
                //reader.close();
            }catch(IOException e){
                System.out.println("client entering error "+e);
            }
        }while(true);
    }
    public void addSupplier(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        do{
            try{
                System.out.println("Enter Supplier name:");
                String name = reader.readLine();
                // System.out.println("Enter Supplier ID:");
                // int id = Integer.valueOf(reader.readLine());
                warehouse.addSupplier(name);
                if(!tOrf("Do you want to add another Supplier? y/n?")){
                    break;
                }
                //reader.close();
            }catch(IOException e){
                System.out.println("Supplier entering error "+e);
            }
        }while(true);
    }
    public void addProduct(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        do{
            try{
                System.out.println("Enter product name:");
                String name = reader.readLine();
                System.out.println("Enter product stock count:");
                int id = Integer.valueOf(reader.readLine());
                System.out.println("Enter product price(as a double):");
                double price=Double.valueOf(reader.readLine());
                warehouse.addProduct(name,id,price);
                if(!tOrf("Do you want to add another product? y/n?")){
                    break;
                }
                //reader.close();
            }catch(IOException e){
                System.out.println("product entering error "+e);
            }
        }while(true);
    }
    public void removeSupplier(int sID){
        do{
            if(warehouse.sExists(sID)!=null){
                
            }
        }while(true);

    }
    public void showClients(){
        Iterator clients = warehouse.getClients();
        while(clients.hasNext()){
            System.out.println(clients.next().toString());
        }
    }
    public void showProducts(){
        Iterator products = warehouse.getProducts();
        while(products.hasNext()){
            System.out.println(products.next().toString());
        }
    }
    public void showSuppliers(){
        Iterator suppliers = warehouse.getSuppliers();
        while(suppliers.hasNext()){
            System.out.println(suppliers.next().toString());
        }
    }

    private static void save(){
        System.out.println(warehouse.save());
        System.out.println("The data has been saved.");
    }
    public void populateDb(){
        warehouse.addClient("client1");
        warehouse.addClient("client2");
        warehouse.addClient("client3");
        warehouse.addClient("client4");
        warehouse.addClient("client5");
        warehouse.addSupplier("Supplier1");
        warehouse.addSupplier("Supplier2");
        warehouse.addSupplier("Supplier3");
        warehouse.addSupplier("Supplier4");
        warehouse.addSupplier("Supplier5");
        warehouse.addProduct("Product1",4,4.0);
        warehouse.addProduct("Product2",7,2.0);
        warehouse.addProduct("Product3",9,1.0);
        warehouse.addProduct("Product4",8,5.0);
        warehouse.addProduct("Product5",1,7.0);
    }
    private void retrieve() {
        try {
            Warehouse tWarehouse = Warehouse.retrieve();
            if (tWarehouse != null) {
                System.out.println(" The warehouse has been successfully retrieved from the file WarehouseData \n" );
                warehouse = tWarehouse;
            }else{
                System.out.println("File doesnt exist; creating new library" );
                warehouse = Warehouse.instance();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void process(){
        do{
            System.out.println(menu);
            switch(Integer.valueOf(getResponse("Select menu option: "))){
                case 1://add clients
                addClient(); 
                break;
                case 2://add suppliers
                addSupplier();
                break;
                case 3://add products
                addProduct();
                break;
                case 4://addToOrder
                addToShoppingCart();
                break;
                case 5://Display clients
                showClients();
                break;
                case 6://Display suppliers
                showSuppliers();
                break;
                case 7://Display products
                showProducts();
                break;
                case 8://save
                save();
                break;
                case 9://exit
                System.exit(0);
                break;
                case 10://populate db
                populateDb();
                break;
                case 11://process order
                processOrder();
                break;
                case 12:
                remove();
                break;
            }
        }while(true);

    }
    public static void main(String[] args) {
        Userinterface.instance().process();
    }
}