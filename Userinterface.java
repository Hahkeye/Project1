package project1;

import java.util.*;
import java.io.*;

public class Userinterface{
    private static Userinterface ui;
    private static String menu = "\tMain Menu\n1. Add something \n2. Remove something\n3. Edit something"+
    "\n4. Display something\n5. Accept Payment\n6. Recieve Shipment"+
    "\n7. Add to cart\n8. Edit Cart\n9. Process order\n10. Save\n11. Exit\n12. Populate db";
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
    public void add(){
        String choice = getResponse("What would you like to add?(c,p,s)");
        switch(choice){
            case "c":
                addClient();
            break;
            case "p":
                addProduct();
            break;
            case "s":
                addSupplier();

            break;
        }
    }
    public void edit(){
        String choice = getResponse("Enter the type of thing you want to edit(c,p,s)?");
        int id =  Integer.valueOf(getResponse("Enter the Id of the thing you want to edit:"));
        if(warehouse.displayAtribs(choice,id)){
            int index = Integer.valueOf(getResponse("Enter the index of the attribute you want to change:"));
            String value = getResponse("Enter the new value:");
            switch(choice){
                case "c":
                    warehouse.editClient(id,index,value);
                break;
                case "s":
                    warehouse.editSupplier(id,index,value);
                break;
                case "p":
                    warehouse.editProduct(id,index,value);
                break;
            }
        }
    }
    public void editCart(){
        int cid = Integer.valueOf(getResponsed("Enter the clients id:"));
        warehouse.displayCart(cid);
        int item = Integer.valueOf(getResponse("Enter product id:"));
        int adjust = Integer.valueOf(getResponse("Adjust quanity by(0 for none): "));
        System.out.println(warehouse.editCart(cid, item, adjust));
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
    public void show(){
        String choice = getResponse("What would you like to display?(c,p,s)");
        switch(choice){
            case "c":
                showClients();
            break;
            case "p":
                showProducts();
            break;
            case "s":
                showSuppliers();
            break;
        }
    }
    public void pay(){
        int cid = Integer.valueOf(getResponse("Enter client id:"));
        Double price = Double.valueOf(getResponse("Enter amount being payed: "));
        System.out.println("the payment was "+warehouse.pay(cid, price));
    }
    public void recevie(){

    }

    private static void save(){
        System.out.println(warehouse.save());
        System.out.println("The data has been saved.");
    }
    public void populateDb(){// Testing method to populate the DB quickly
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
                case 1://add 
                add(); 
                break;
                case 2://remove 
                remove();
                break;
                case 3://edit 
                edit();
                break;
                case 4://Display something
                show();
                break;
                case 5://Accept payment
                pay();
                break;
                case 6://recive shipment
                recevie();
                break;
                case 7://add to cart
                addToShoppingCart();
                break;
                case 8://edit cart
                editCart();
                break;
                case 9://process order
                processOrder();
                break;
                case 10://save
                save();
                break;
                case 11://exit
                System.exit(0);
                break;
                case 12://populate db
                populateDb();
                break;
            }
        }while(true);

    }
    public static void main(String[] args) {
        Userinterface.instance().process();
    }
}