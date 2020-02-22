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
        String choice = getResponse("What would you like to remove?(c,p,s)");
        int id = Integer.valueOf(getResponse("What is the Id of this?:"));
        System.out.println(warehouse.remove(choice, id));
    }
    public void processOrder(){
        int cid = Integer.valueOf(getResponse("Enter client ID: "));
        System.out.println(warehouse.processOrder(cid));
    }
    public void addToShoppingCart(){
        do{
            int cid = Integer.valueOf(getResponse("Enter the Client ID:"));
            int pid = Integer.valueOf(getResponse("Enter the product ID:"));
            int quanity = Integer.valueOf(getResponse("Enter desired quanity: "));
            if(warehouse.addToOrder(cid,pid,quanity)){
                System.out.println("Successfully added to shopping cart.");
            }else{
                System.out.println("Failed to add to shopping cart");
            }
            if(!tOrf("Do you want to add another item to shopping cart? y/n?")){
                break;
            }
        }while(true);
    }
    public void addClient(){
        do{
            String name = getResponse("Enter name:");
            warehouse.addClient(name);
            if(!tOrf("Do you want to add another Client? y/n?")){
                break;
            }
        }while(true);
    }
    public void addSupplier(){
        do{
            String name = getResponse("Enter Supplier name:");
            warehouse.addSupplier(name);
            if(!tOrf("Do you want to add another supplier? y/n?")){
                break;
            }
        }while(true);
    }
    public void addProduct(){
        do{
            String name = getResponse("Enter product name:");
            int count = Integer.valueOf(getResponse("Enter Product Stock count: "));
            double price = Double.valueOf(getResponse("Enter product price: "));
            int sid = Integer.valueOf(getResponse("Enter Supplier ID:"));
            warehouse.addProduct(name, count, price,sid);
            if(!tOrf("Do you want to add another product? y/n?")){
                break;
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
        warehouse.addProduct("Product1",4,4.0,1);
        warehouse.addProduct("Product2",7,2.0,2);
        warehouse.addProduct("Product3",9,1.0,3);
        warehouse.addProduct("Product4",8,5.0,4);
        warehouse.addProduct("Product5",1,7.0,1);
        warehouse.addProduct("Product6",1,7.0,2);
        warehouse.addProduct("Product7",1,7.0,3);
        warehouse.addProduct("Product8",1,7.0,4);
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