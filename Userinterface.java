package project1;

import java.util.*;

import jdk.internal.jline.internal.InputStreamReader;

import java.beans.PropertyDescriptor;
import java.io.*;

public class Userinterface{
    private static String menu = "\tMain Menu\n1. Add Clients \n2. Add Suppliers\n3. Add Products ";
    private static File checkFile = new File("ClientData");
    private static ClientDirectory clients;
    private static ProductDirectory products;
    private static SupplierDirectory suppliers;
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
    public static void addClient(ClientDirectory clients){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        do{
            try{
                System.out.println("Enter Client name:");
                String name = reader.readLine();
                System.out.println("Enter Client ID:");
                int id = Integer.valueOf(reader.readLine());
                clients.insert(new Client(name,id));
                if(!tOrf("Do you want to add another Client? y/n?")){
                    break;
                }
            }catch(IOError e){
                System.out.println("client entering error "+e);
            }
        }while(true);
        
    }
    public static void addSupplier(){
        System.out.println("asdasd");
    }
    public static void addProduct(){
        System.out.println("asdasd");
    }
    public static void main(String[] args) {
        // ClientDirectory clients=ClientDirectory.instance();
        // ProductDirectory products=ProductDirectory.instance();
        // SupplierDirectory suppliers=SupplierDirectory.instance();
        if(checkFile.exists()){
            try{
                if(tOrf("Save data decteced do you want to load it? y/n?")){
                    FileInputStream file = new FileInputStream("ProdData");
                    ObjectInputStream input = new ObjectInputStream(file);
                    input.readObject();
                    file = new FileInputStream("ClientData");
                    input = new ObjectInputStream(file);
                    input.readObject();
                    file = new FileInputStream("SupData");
                    input = new ObjectInputStream(file);
                    input.readObject();
                }else{
                    clients = ClientDirectory.instance();
                    products = ProductDirectory.instance();
                    suppliers = SupplierDirectory.instance();
                }
            }catch(IOException e){
                e.printStackTrace();
            }catch(ClassNotFoundException e2){
                e2.printStackTrace();
            }
        }

        do{
            System.out.println(menu);
            switch(Integer.valueOf(getResponse("Select menu option: "))){
                case 1://add clients
                addClient(clients); 
                break;
                case 2://add suppliers
                addSupplier();
                break;
                case 3://add products
                addProduct();
                break;
                case 4://Load files
                break;
                case 5://Save files
                break;
            }
        }while(true);
    }
}