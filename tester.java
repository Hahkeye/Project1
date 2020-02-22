package project1;

import java.io.*;
import java.util.*;
import javax.lang.model.element.QualifiedNameable;

public class tester{
    public static boolean question(String question){
        try{
            System.out.println(question);
            BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
            String answer = reader.readLine();
            if(answer.charAt(0)=='y' || answer.charAt(0)=='Y'){
                return true;
            }
            return false;
        }catch(IOException e){
            System.out.println("borke");
        }
        return false;
        
    }
    public static void main(String args[]){
        
        Warehouse w = Warehouse.instance();
        w.addClient("client1");
        w.addClient("client2");
        w.addClient("client3");
        w.addClient("client4");
        w.addClient("client5");
        w.addSupplier("Supplier1");
        w.addSupplier("Supplier2");
        w.addSupplier("Supplier3");
        w.addSupplier("Supplier4");
        w.addSupplier("Supplier5");
        w.addProduct("Product1",4,4.0,1);
        w.addProduct("Product2",7,2.0,2);
        w.addProduct("Product3",9,1.0,3);
        w.addProduct("Product4",8,5.0,4);
        w.addProduct("Product5",1,7.0,1);
        w.addToOrder(1, 2, 3);
        w.processOrder(1);
        // Iterator it = w.getClients();   
        // while(it.hasNext()){
        //     System.out.println(((Client)it.next()));
        // }
        System.out.println("Clients with balance");
        w.getClient(1).adjustBalance(-4);
        w.getClientsWithBalance();
        System.out.println("single client transaction");
        w.getTransactions(1);
        System.out.println("All manufactuers and items");
        w.getSuppliersAndProducts();
        System.out.println("All products and stuff");
        w.getAllProducts();





        
       
    }
}